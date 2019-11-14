package com.imooc.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        System.out.println("preHandle");
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        httpServletRequest.setAttribute("startTime",new Date().getTime());
//        return false; // 返回false 不执行后续的方法
        return true;
    }

    /**
     * 控制器方法执行之后调用，抛出异常不执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时:" + (new Date().getTime() - start));
    }

    /**
     * 控制器方法执行之后调用，抛出异常也执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
        long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时:" + (new Date().getTime() - start));
        System.out.println("ex is " + e);
    }
}
