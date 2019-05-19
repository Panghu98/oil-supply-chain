package group.uchain.oilsupplychain.config;

import group.uchain.oilsupplychain.interceptor.AuthorityInterceptor;
import group.uchain.oilsupplychain.service.UserService;
import group.uchain.oilsupplychain.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author dgh
 * @date 19-1-19 下午8:11
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Autowired
    private AuthorityInterceptor authorityInterceptor;

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {

    }

    /**
     * 在这个方法当中配置拦截器需要拦截的页面
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityInterceptor).addPathPatterns("/**")
                //设置拦截器拦截的位置
                .excludePathPatterns("");
    }


    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }


}
