package com.huawei.test_session.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> pramMap = request.getParameterMap();//参数
        Map<String,String> map = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        for (Map.Entry<String,String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "---------" + entry.getValue());
        }
        return super.preHandle(request, response, handler);
    }
}
