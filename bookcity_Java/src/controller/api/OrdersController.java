package controller.api;

import com.alibaba.fastjson.JSON;
import domain.dto.CartSelectedMerVO;
import domain.dto.CartVO;
import domain.dto.MemberVO;
import domain.dto.OrdersVO;
import service.OrdersServiceIface;
import service.ShoppingCartServiceIface;
import service.impl.OrdersServiceImpl;
import service.impl.ShoppingCartServiceImpl;
import utils.AjaxResult;
import utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/auth/orders")
public class OrdersController extends HttpServlet {
    OrdersServiceIface ordersService = new OrdersServiceImpl();
    ShoppingCartServiceIface shoppingCartService = new ShoppingCartServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("m");
        if ("list".equals(method)) {
            list(req,resp);
        } else if ("delete".equals(method)) {
            deleteOrdersById(req,resp);
        } else if ("detail".equals(method)){
            orderDetail(req, resp);
        } else if ("merchandiseList".equals(method)){
            merchandiseList(req, resp);
        }
    }

    private void merchandiseList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        //获得登录信息
        OrdersVO ordersVO = ordersService.getOrderById(id);
        AjaxResult result = AjaxResult.success("订单中商品详情列表获取成功");
        List<CartSelectedMerVO> cartSelectedMerList = shoppingCartService.getCartSelectedMerListByCart(ordersVO.getCart());
        result.put("data",cartSelectedMerList);
        resp.getWriter().write(JSON.toJSONString(result));
    }

    private void orderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id  = req.getParameter("id");
        AjaxResult result = AjaxResult.success("查询单个订单成功");
        //获得登录信息
        OrdersVO ordersVO = ordersService.getOrderById(id);
        result.put("data", ordersVO);
        result.put("data1", ordersVO);
        resp.getWriter().write(JSON.toJSONString(result));
    }

    private void deleteOrdersById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        AjaxResult result = AjaxResult.success("订单删除成功");
        //获得登录信息
        boolean b = ordersService.deleteOrdersById(id);
        result.put("data", b);
        response.getWriter().write(JSON.toJSONString(result));
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AjaxResult result = AjaxResult.success("成功获得订单列表");
        MemberVO memberVO = SessionUtil.getLoginMemberInfo(req);
        List<OrdersVO> ordersList = ordersService.getOrderListByMid(memberVO.getId());
        result.put("data",ordersList);
        resp.getWriter().write(JSON.toJSONString(result));
    }
}
