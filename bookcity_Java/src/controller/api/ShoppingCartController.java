package controller.api;

import com.alibaba.fastjson.JSON;
import domain.dto.MemberVO;
import service.ShoppingCartServiceIface;
import service.impl.ShoppingCartServiceImpl;
import utils.AjaxResult;
import utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/auth/cart2")
public class ShoppingCartController extends HttpServlet {
    ShoppingCartServiceIface shoppingCartService = new ShoppingCartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("m");
        System.out.println("10086");
        if ("list".equals(method)) {
            list(req,resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("写回json");
        AjaxResult result = AjaxResult.success("成功获得list列表");
        MemberVO memberVO = SessionUtil.getLoginMemberInfo(req);
        //shoppingCartService.getCartSelectedMerList(memberVO.getId());
        resp.getWriter().write(JSON.toJSONString(result));
    }
}
