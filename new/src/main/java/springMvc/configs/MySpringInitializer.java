package springMvc.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Auther: ybl
 * @Date: 2018/12/14 0014 09:56
 * @Description:
 */
public class MySpringInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{Rootconfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{Webconfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
