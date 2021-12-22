package cn.edu.sjtu.ist.ecssbackendcloud.config;

import cn.edu.sjtu.ist.ecssbackendcloud.utils.AuthenticationInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @brief 拦截配置
 * @author rsp
 * @version 0.1
 * @date 2021-12-21
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**"); // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
    }

}