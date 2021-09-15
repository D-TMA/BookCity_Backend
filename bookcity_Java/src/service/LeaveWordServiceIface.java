package service;

import domain.dto.LeaveWordVO;

import java.util.List;

public interface LeaveWordServiceIface {

    /**
     * 查询留言列表
     * @return
     */
    List<LeaveWordVO> getLeaveWordList();
}
