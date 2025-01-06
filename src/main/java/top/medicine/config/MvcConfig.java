package top.medicine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.medicine.component.LoginHandlerInterceptor;


@Configuration
public class MvcConfig implements WebMvcConfigurer, ErrorPageRegistrar {

    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/400").setViewName("error/400");
        registry.addViewController("/401").setViewName("error/401");
        registry.addViewController("/404").setViewName("error/404");
        registry.addViewController("/500").setViewName("error/500");

        registry.addViewController("/feedback.html").setViewName("feedback");
    }

    // 注册错误页面
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/400");
        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401");
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");
        registry.addErrorPages(
                error400Page,
                error401Page,
                error404Page,
                error500Page
        );
    }

    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/profile", "/doctor", "/videoSupport", "/all-article", "/identification");
    }
}
