package kopo.poly.config;

import kopo.poly.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.Arrays;

@Configuration
@EnableWebSocket
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 로그인 체크를 해야하는 URL에 대해 정의
        String[] loginCheckURI = {"/updatePwPage", "/myPage","/boardReg"};
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(Arrays.asList(loginCheckURI));

    }
}