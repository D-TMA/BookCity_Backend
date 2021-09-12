package utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/api/auth/*")
public class UserLoginFilterUtil implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //在session中找用户的登录信息 如果存在登录信息则放弃请求 如果不存在就放回一个AjaxResult对象 code=505
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session!=null) {
            Object obj = session.getAttribute("loginMember");
            if (obj!=null) {
                System.out.println("1");
                System.out.println("用户已登录");
                filterChain.doFilter(servletRequest,servletResponse);
            } else {
                System.out.println("2");
                AjaxResult result = AjaxResult.error(505,"用户未登录");
                response.getWriter().write(JSON.toJSONString(result));
            }
        } else {
            System.out.println("3");
            AjaxResult result = AjaxResult.error(506,"session不存在");
            response.getWriter().write(JSON.toJSONString(result));
        }
    }

    @Override
    public void destroy() {

    }
}
