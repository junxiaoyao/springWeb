package com.jxy.springMvc.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * @Auther: ybl
 * @Date: 2018/12/14 0014 09:56
 * @Description:
 */
public class MySpringInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //非Web的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{Rootconfig.class};
    }
    //Web配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{Webconfig.class};
    }
    //映射DispatchServlet到/
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    //设置多文件上传，路径
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
       registration.setMultipartConfig(new MultipartConfigElement("D:/data/file"));
    }

}
