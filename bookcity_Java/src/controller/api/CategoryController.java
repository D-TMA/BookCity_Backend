package controller.api;

import com.alibaba.fastjson.JSON;
import domain.dto.CategoryVO;
import service.CategoryServiceIface;
import service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/category")
public class CategoryController extends HttpServlet {
    CategoryServiceIface categoryService = new CategoryServiceImpl();
    String method = "";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        method = req.getParameter("m"); //m 自定义的key
        if ("list".equals(method)) {
            list(req,resp);
        } else if ("delete".equals(method)) {
            delete(req,resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("add".equals(method)) {
            add(req,resp);
        } else if ("update".equals(method)) {
            update(req,resp);
        }
    }

    //添加商品类型数据
    private void add(HttpServletRequest req, HttpServletResponse resp) {
    }

    //删除商品类型数据
    private void delete(HttpServletRequest req, HttpServletResponse resp) {
    }

    //更新商品类型数据
    private void update(HttpServletRequest req, HttpServletResponse resp) {
    }

    //查询商品类型数据
    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryVO> categoryList = categoryService.getCategoryList();
        //把对象转换为json字符串
        String data = JSON.toJSONString(categoryList);
        //写回数据
        resp.getWriter().write(data);
    }
}
