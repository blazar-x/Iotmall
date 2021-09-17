package com.liang.iotmall.order.interceptor;

import com.liang.common.constant.AuthServerConstant;
import com.liang.common.vo.MemberRespVo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Mr.Liang
 * @date: 2021/6/2 10:25
 * @Version: 1.0
 */
@Component
public class LoginUserInterceptor implements HandlerInterceptor {

    public static ThreadLocal<MemberRespVo> loginUser=new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MemberRespVo attribute = (MemberRespVo) request.getSession().getAttribute(AuthServerConstant.LOGIN_USER);
        if(attribute!=null){
            //登录了就放行
            loginUser.set(attribute);
            return  true;
        }else {
            //没登录去登录
            request.getSession().setAttribute("msg","请先进行登录");
            response.sendRedirect("http://auth.iotmall.com/login.html");
            return false;
        }

    }
}
