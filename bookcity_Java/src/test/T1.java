package test;

import com.alibaba.fastjson.JSON;
import domain.dto.CategoryVO;

import java.util.ArrayList;
import java.util.List;

public class T1 {
    public static void main(String[] args) {
        List<CategoryVO> categoryList = new ArrayList<>();
        CategoryVO vo = new CategoryVO();
        vo.setId(1);
        vo.setCateName("计算机类");
        vo.setCateDesc("计算机类型书籍");
        CategoryVO vo2 = new CategoryVO();
        vo2.setId(2);
        vo2.setCateName("英语类");
        vo2.setCateDesc("英语类型书籍");
        CategoryVO vo3 = new CategoryVO();
        vo3.setId(3);
        vo3.setCateName("历史类");
        vo3.setCateDesc("历史类型书籍");

        categoryList.add(vo);
        categoryList.add(vo2);
        categoryList.add(vo3);

        //fastjson
        String data = JSON.toJSONString(categoryList);
        System.out.println(data);
    }
}
