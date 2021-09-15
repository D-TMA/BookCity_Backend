package service.impl;

import dao.LeaveWordDao;
import domain.dto.LeaveWordVO;
import service.LeaveWordServiceIface;

import java.util.List;

public class LeaveWordServiceImpl implements LeaveWordServiceIface {
    LeaveWordDao leaveWordDao = new LeaveWordDao();
    @Override
    public List<LeaveWordVO> getLeaveWordList() {
        return leaveWordDao.selectLeaveWordList();
    }
}
