package service.impl;

import dao.MerchandiseDao;
import domain.dto.MerchandiseVO;
import service.MerchandiseServiceIface;

import java.util.List;

public class MerchandiseServiceImpl implements MerchandiseServiceIface {
    MerchandiseDao merchandiseDao = new MerchandiseDao();

    @Override
    public List<MerchandiseVO> getMerchandiseListBySpecial(String special, String query) {
        int s = Integer.parseInt(special);
        return merchandiseDao.selectMerchandiseListBySpecial(s,query);
    }

    @Override
    public MerchandiseVO getMerchandiseListById(String id) {
        int int_id = Integer.parseInt(id);
        return merchandiseDao.selectMerchandiseListById(int_id);
    }
}
