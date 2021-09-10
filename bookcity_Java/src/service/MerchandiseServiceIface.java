package service;

import domain.dto.MerchandiseVO;

import java.util.List;

public interface MerchandiseServiceIface {
    /**
     * 根据是否特价查询商品列表
     */
    List<MerchandiseVO> getMerchandiseListBySpecial(String special, String query);

    /**
     * 根据ID查询商品列表
     */
    MerchandiseVO getMerchandiseListById(String id);
}
