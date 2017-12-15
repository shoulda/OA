package com.siemens.oa.controller;

import com.siemens.oa.enums.Auth;
import com.siemens.oa.service.PermissionService;
import com.siemens.oa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.siemens.oa.annotation.AuthDetec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gxurn9 on 12/13/2017.
 */
@Configuration
public class AuthDetecConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Bean
    public AuthenticationInterceptor getAuthenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthenticationInterceptor()).addPathPatterns("/**");
    }

    public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession();
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 从方法处理器中获取出要调用的方法
            Method method = handlerMethod.getMethod();
            // 获取出方法上的AuthDetec注解
            AuthDetec authDetec = method.getAnnotation(AuthDetec.class);
            if (authDetec == null) {
                // 如果注解为null, 说明不需要拦截, 直接放过
                return true;
            }

            if (authDetec.authorities().length > 0) {
                // 如果权限配置不为空, 则取出配置值
                Auth[] authorities = authDetec.authorities();
                Set<Auth> authSet = new HashSet<>();
                for (Auth authority : authorities) {
                    authSet.add(authority);
                }
                String Auth = permissionService.selectAuthById(userService.selectUserIdByName(session.getAttribute(WebSecurityConfig.SESSION_KEY).toString()));
                System.out.println(Auth);
                if (authSet.contains(Auth)) return true;
            }
            return false;
        }
    }


}

