package org.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.mapper.ActCardResultMapper;
import org.example.pojo.ActCardResult;
import org.example.service.ActCardResultService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SQLTest {
    @Autowired
    private ActCardResultMapper actCardResultMapper;

    @Test
    public void testApply(){
        String sql = "NOT ( userId > 1004 AND type = 1)";
        QueryWrapper<ActCardResult> wrapper = new QueryWrapper<>();
        wrapper.apply(sql);
        List<ActCardResult> actCardResultList = actCardResultMapper.selectList(wrapper);
        System.out.println(actCardResultList);
    }
}
