package com.gupaoedu.sso.intercept;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gupaoedu.sso.controller.Anoymous;
import com.gupaoedu.sso.controller.BaseController;
import com.gupaoedu.sso.utils.CookieUtil;
import com.gupaoedu.user.IUserCoreService;
import com.gupaoedu.user.dto.CheckAuthRequest;
import com.gupaoedu.user.dto.CheckAuthResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class TokenIntercepter extends HandlerInterceptorAdapter {

    private final String ACCESS_TOKEN="access_token";

    @Autowired
    IUserCoreService iUserCoreService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Object bean=handlerMethod.getBean();

        if(!(bean instanceof BaseController)){
            throw new RuntimeException("must extend basecontroller");
        }

        if(isAnoymous(handlerMethod)){
            return true;
        }

        String token=CookieUtil.getCookieValue(request,ACCESS_TOKEN);
        if(StringUtils.isEmpty(token)){
            if(CookieUtil.isAjax(request)){
                response.getWriter().write("{'code':'-1';'msg':'errro'}");
                return false;
            }
            response.sendRedirect("/pages/login.html");
            return false;
        }
        CheckAuthRequest checkAuthRequest=new CheckAuthRequest();
        checkAuthRequest.setToken(token);
        CheckAuthResponse checkAuthResponse=iUserCoreService.validToken(checkAuthRequest);
        if("000000".equals(checkAuthResponse.getCode())){
            BaseController baseController=(BaseController)bean;
            baseController.setUid(checkAuthResponse.getUid());
            return super.preHandle(request, response, handler);
        }
        ObjectMapper objectMapper=new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(checkAuthResponse));
        return false;
    }

    private boolean isAnoymous(HandlerMethod handlerMethod){
        Object bean=handlerMethod.getBean();
        Class clazz=bean.getClass();
        if(clazz.getAnnotation(Anoymous.class)!=null){
            return true;
        }
        Method method=handlerMethod.getMethod();
        return method.getAnnotation(Anoymous.class)!=null;
    }
}
