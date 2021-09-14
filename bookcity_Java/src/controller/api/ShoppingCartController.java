package controller.api;

import com.alibaba.fastjson.JSON;
import domain.dto.CartSelectedMerVO;
import domain.dto.CartVO;
import domain.dto.MemberVO;
import domain.dto.OrdersVO;
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
        } else if ("add".equals(method)) {
            add(req,resp);
        } else if ("delete".equals(method)){
            delete(req,resp);
        } else if ("clear".equals(method)){
            clear(req,resp);
        } else if ("updateNum".equals(method)){
            updateNum(req,resp);
        } else if ("submit".equals(method)){
            submitShoppingCart(req,resp);
        }
    }

    //提交购物车
    private void submitShoppingCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartVO cartVO = SessionUtil.getShoppingCartInfo(req);
        MemberVO memberVO = SessionUtil.getLoginMemberInfo(req);
        OrdersVO ordersVO = shoppingCartService.submitShoppingCart(cartVO.getId(),memberVO.getId());
        if(ordersVO!=null){
            AjaxResult result = AjaxResult.success("订单生成成功");
            //需要重新绑定购物车
            //创建购物车信息
            CartVO vo = shoppingCartService.getShoppingCart(memberVO.getId());
            HttpSession session = req.getSession();
            session.setAttribute("cart",vo);
            result.put("data",ordersVO);
            resp.getWriter().write(JSON.toJSONString(result));
        }else{
            resp.getWriter().write(JSON.toJSONString(AjaxResult.error("订单生成失败")));
        }
    }

    //修改商品数量
    private void updateNum(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String num = req.getParameter("num");
        CartVO cartVO = SessionUtil.getShoppingCartInfo(req);
        boolean b = shoppingCartService.updateCartSelectedMerNumById(id, Integer.parseInt(num), cartVO.getId());
        if (b) {
            AjaxResult result = AjaxResult.success("购物车项目修改成功");
            resp.getWriter().write(JSON.toJSONString(result));
        } else {
            resp.getWriter().write(JSON.toJSONString(AjaxResult.error("购物车项目修改失败")));
        }
    }

    //清空购物车
    private void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartVO cartVO = SessionUtil.getShoppingCartInfo(req);
        boolean b = shoppingCartService.clearCartSelectedMerByCart(cartVO.getId());
        if (b) {
            AjaxResult result = AjaxResult.success("购物车清空成功");
            resp.getWriter().write(JSON.toJSONString(result));
        } else {
            resp.getWriter().write(JSON.toJSONString(AjaxResult.error("购物车清空失败")));
        }
    }

    //购物车删除商品
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        CartVO cartVO = SessionUtil.getShoppingCartInfo(req);
        boolean b = shoppingCartService.deleteCartSelectedMerById(id, cartVO.getId());
        if (b) {
            AjaxResult result = AjaxResult.success("购物车商品删除成功, id="+id);
            resp.getWriter().write(JSON.toJSONString(result));
        } else {
            resp.getWriter().write(JSON.toJSONString(AjaxResult.error("购物车商品删除失败")));
        }
    }

    //购物车添加商品
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String merId = req.getParameter("merId");
        CartVO cartVO = SessionUtil.getShoppingCartInfo(req);
        MemberVO memberVO = SessionUtil.getLoginMemberInfo(req);
        boolean b = shoppingCartService.addShoppingCart(cartVO.getId(), merId,memberVO.getFavourable());
        if (b) {
            AjaxResult result = AjaxResult.success("购物车添加商品成功,id="+merId);
            resp.getWriter().write(JSON.toJSONString(result));
        } else {
            resp.getWriter().write(JSON.toJSONString(AjaxResult.error("添加购物车失败")));
        }

    }

    //获取购物车列表
    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AjaxResult result = AjaxResult.success("成功获得购物车列表");
        MemberVO memberVO = SessionUtil.getLoginMemberInfo(req);
        CartVO cartVO = SessionUtil.getShoppingCartInfo(req);
        List<CartSelectedMerVO> cartSelectedMerList = shoppingCartService.getCartSelectedMerListByCart(cartVO.getId());
        result.put("data",cartSelectedMerList);
        resp.getWriter().write(JSON.toJSONString(result));
    }
}
