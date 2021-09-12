package controller.api;

import com.alibaba.fastjson.JSON;
import domain.dto.CartVO;
import domain.dto.MemberVO;
import service.MemberServiceIface;
import service.ShoppingCartServiceIface;
import service.impl.MemerServiceImpl;
import service.impl.ShoppingCartServiceImpl;
import utils.AjaxResult;
import utils.LogOutputUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/member")
public class MemberController extends HttpServlet {
    MemberServiceIface memberService = new MemerServiceImpl();
    ShoppingCartServiceIface shoppingCartService = new ShoppingCartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("m");
        if ("login".equals(method)) {
            login(req,resp);
        } else if("getLoginMember".equals(method)) {
            getLoginMember(req,resp);
        } else if ("logout".equals(method)) {
            logout(req,resp);
        }
    }

    private void getLoginMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("loginMember");
        if (obj!=null) {
            MemberVO memberVO = (MemberVO) obj;
            AjaxResult result = AjaxResult.success("登录成功",memberVO);
            resp.getWriter().write(JSON.toJSONString(result));
        } else {
            AjaxResult result = AjaxResult.error(502,"session中不存在登录信息");
            resp.getWriter().write(JSON.toJSONString(result));
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginName = req.getParameter("loginName");
        String loginPwd = req.getParameter("loginPwd");
        MemberVO memberVO = memberService.login(loginName,loginPwd);
        AjaxResult result = null;
        if (memberVO!=null) {
            CartVO cartVO = shoppingCartService.getShoppingCart(memberVO.getId());
            LogOutputUtil.logger.info(loginName+"登录成功");
            result = AjaxResult.success("登录成功",memberVO);
            HttpSession session = req.getSession(true);
            session.setAttribute("loginMember",memberVO);
            session.setAttribute("cart",memberVO);
        } else  {
            LogOutputUtil.logger.error(loginName+"登录失败");
            result = AjaxResult.error(501,"用户名或密码错误");
        }
        resp.getWriter().write(JSON.toJSONString(result));
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session!=null) {
            //删除所有session内容
            session.invalidate();
            //删除指定session内容
            //session.removeAttribute("loginMember");
        }
        AjaxResult result = AjaxResult.success("会员已安全登出");
        resp.getWriter().write(JSON.toJSONString(result));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
