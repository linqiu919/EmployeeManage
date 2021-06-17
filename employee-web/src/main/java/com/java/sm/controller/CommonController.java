package com.java.sm.controller;

import com.java.sm.entity.Employee;
import com.java.sm.exception.LoginException;
import com.java.sm.result.AxiosResult;
import com.java.sm.result.AxiosStatus;
import com.java.sm.service.EmployeeService;
import com.java.sm.util.SmsUtils;
import com.java.sm.util.UploadUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName LoginController.java
 * @DescriPtion TODO
 * @CreateTime 2021年06月14日 22:15:00
 */
@RestController
@RequestMapping("common")
//@CrossOrigin //此注解表示此类中的所有方法都解决跨域问题,全局配置之后可以取消
public class CommonController {

    @Autowired
    private EmployeeService employeeService;
//
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @GetMapping("getCode/{phone}")
    public AxiosResult<Void> getCode(@PathVariable String phone) throws Exception {
        Employee employee = employeeService.doLogin(phone);
        if(employee==null){
//            return AxiosResult.error();
            throw new LoginException(AxiosStatus.ERROR_PHONE);
        }

        int code = (int)(int)(Math.random()* (999999-100000+1)+100000);
        stringRedisTemplate.opsForValue().set(phone,code+"",2, TimeUnit.MINUTES);
        //TODO 将发送短信方法放到异步中
        SmsUtils.sendSms(phone,code+"");
        return AxiosResult.success();
    }

    @PostMapping("doLogin")
    public AxiosResult<AxiosStatus> doLogin(@RequestBody Map<String,String> map, HttpSession httpSession){
        String phone = map.get("phone");
        String code = map.get("code");
        String getCode = stringRedisTemplate.opsForValue().get(phone);
        if(code.equals(getCode)){
            //登录成功，清除验证码
            stringRedisTemplate.delete(phone);
            httpSession.setAttribute("userInfo","userInfo");
            System.out.println(AxiosResult.success());
            return AxiosResult.success(AxiosStatus.OK);
        }else{
            throw  new LoginException(AxiosStatus.ERROR);
        }
    }

    @PostMapping("uploadAvatar")
    public AxiosResult<String> uploadAvatar(@RequestPart Part avatar) throws IOException {
        //重命名上传文件
        String fileName = UUID.randomUUID().toString()+ StringUtils.getFilenameExtension(avatar.getSubmittedFileName());
        //获取文件输入流
        InputStream inputStream = avatar.getInputStream();
        //上传文件
        UploadUtils.upload(fileName,inputStream);
        String url = "https://shangmasanshiqi.oss-cn-beijing.aliyuncs.com/"+fileName;
        return AxiosResult.success(url);
    }

    /**
     * 导出表格数据
     */
    @GetMapping("writeExcel")
    public ResponseEntity<byte[]> writeExcel(HttpSession httpSession) throws Exception{
        //获取数据
        List<Employee> list = employeeService.findList();
        //创建workBooks
        Workbook workbook = new XSSFWorkbook();
        //创建sheet
        Sheet sheet = workbook.createSheet("员工信息表");
        //创建标题行
        Row titleRow = sheet.createRow(0);

        List<String> strings = Arrays.asList("员工ID", "员工姓名", "员工地址", "员工手机", "员工入职时间", "员工薪资", "员工头像");
        for (int i = 0; i < strings.size(); i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(strings.get(i));
        }
        //创建行
        for (int i = 0; i < list.size(); i++) {
            Employee employee = list.get(i);
            Row row = sheet.createRow(i+1);
            //创建每一行中的单元格并填充单元格数据
           Cell idCell =  row.createCell(0);
           idCell.setCellValue(employee.getEmployeeId());

           Cell nameCell =  row.createCell(1);
           nameCell.setCellValue(employee.getEmployeeName());

           Cell addressCell =  row.createCell(2);
           addressCell.setCellValue(employee.getEmployeeAddress());

           Cell phoneCell =  row.createCell(3);
           phoneCell.setCellValue(employee.getEmployeePhone());

           Cell timeCell =  row.createCell(4);
           timeCell.setCellValue(sdf.format(employee.getEmployeeTime()));

           Cell salaryCell =  row.createCell(5);
           salaryCell.setCellValue(employee.getEmployeeSalary());

           Cell avatarCell =  row.createCell(6);
           avatarCell.setCellValue(employee.getEmployeeAvatar());
        }
        //写入本地
//        FileOutputStream fileOutputStream = new FileOutputStream("E:\\employee.xlsx");
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        workbook.write(byteStream);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode("员工信息表.xlsx","utf-8"));
        byte[] bytes = byteStream.toByteArray();
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes,httpHeaders, HttpStatus.OK);
        byteStream.close();
        workbook.close();
        return responseEntity;
    }


}
