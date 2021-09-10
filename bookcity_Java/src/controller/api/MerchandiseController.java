package controller.api;

import com.alibaba.fastjson.JSON;
import domain.dto.MerchandiseVO;
import service.MerchandiseServiceIface;
import service.impl.MerchandiseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/merchandise")
public class MerchandiseController extends HttpServlet {
    String method;
    MerchandiseServiceIface merchandiseService = new MerchandiseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        method = req.getParameter("m");
        if ("getMerchandiseListBySpecial".equals(method)) {
            getMerchandiseListBySpecial(req,resp);
        } else if ("getMerchandiseListById".equals(method)) {
            getMerchandiseListById(req,resp);
        }
    }

    public void getMerchandiseListBySpecial(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String special = req.getParameter("special");
        String query = req.getParameter("query");
        List<MerchandiseVO> list = merchandiseService.getMerchandiseListBySpecial(special,query);
        String listJson = JSON.toJSONString(list);
        resp.getWriter().write(listJson);
    }

    private void getMerchandiseListById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        MerchandiseVO merchandiseVO = merchandiseService.getMerchandiseListById(id);
        String data = JSON.toJSONString(merchandiseVO);
        resp.getWriter().write(data);
    }
}
