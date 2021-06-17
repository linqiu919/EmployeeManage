import com.java.sm.config.MapperConfig;
import com.java.sm.config.ServiceConfig;
import com.java.sm.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName MyWebAppInitializer.java
 * @DescriPtion
 * @CreateTime 2021年06月14日 21:11:00
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //加载父容器的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MapperConfig.class, ServiceConfig.class};
    }
    //加载子容器的配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }
    //设置dispatcherServlet的路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //配置multipartConfig 开启part方式上传
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement(""));
    }
}
