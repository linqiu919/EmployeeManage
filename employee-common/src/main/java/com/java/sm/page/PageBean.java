package com.java.sm.page;

import lombok.Data;

import java.util.List;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName PageBean.java
 * @DescriPtion TODO
 * @CreateTime 2021年06月15日 13:11:00
 */
@Data
public class PageBean<T> {
    //返回总页数
    private long total;
    //返回当前页的数据
    private List<T> list;

    private PageBean() {
    }

    public static <T> PageBean<T> init(long total,List<T> list){
        PageBean<T> tPageBean = new PageBean<>();
        tPageBean.setTotal(total);
        tPageBean.setList(list);
        return tPageBean;
    }
 }
