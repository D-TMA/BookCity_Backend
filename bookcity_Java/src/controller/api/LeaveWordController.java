package controller.api;

import com.alibaba.fastjson.JSON;
import domain.dto.LeaveWordVO;
import domain.dto.MemberVO;
import domain.dto.OrdersVO;
import service.LeaveWordServiceIface;
import service.impl.LeaveWordServiceImpl;
import utils.AjaxResult;
import utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/leaveWord")
public class LeaveWordController extends HttpServlet {
    LeaveWordServiceIface leaveWordService = new LeaveWordServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("m");
        if ("list".equals(method)) {
            list(req,resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AjaxResult result = AjaxResult.success("成功获得留言列表");
        MemberVO memberVO = SessionUtil.getLoginMemberInfo(req);
        List<LeaveWordVO> leaveWordList = leaveWordService.getLeaveWordList();
        result.put("data",leaveWordList);
        resp.getWriter().write(JSON.toJSONString(result));
    }
}
