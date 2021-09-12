package controller.api;

import com.alibaba.fastjson.JSON;
import domain.CartSelectedMerPO;
import domain.dto.CartSelectedMerVO;
import domain.dto.CartVO;
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
import java.util.List;

@WebServlet("/api/auth/cart")
public class ShoppingCartController extends HttpServlet {
    ShoppingCartServiceIface shoppingCartService = new ShoppingCartServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("m");
        if ("list".equals(method)) {
            list(req,resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AjaxResult result = AjaxResult.success("成功获得购物车列表");
        MemberVO memberVO = SessionUtil.getLoginMemberInfo(req);
        CartVO cartVO = SessionUtil.getShoppingCartInfo(req);
        List<CartSelectedMerVO> cartSelectedMerList = shoppingCartService.getCartSelectedMerListByCart(cartVO.getId());
        result.put("data",cartSelectedMerList);
        resp.getWriter().write(JSON.toJSONString(result));
    }
}
