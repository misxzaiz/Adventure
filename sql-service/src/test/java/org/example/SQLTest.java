package org.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.mapper.ActCardResultMapper;
import org.example.pojo.ActCardResult;
import org.example.service.ActCardResultService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootTest
public class SQLTest {
    @Autowired
    private ActCardResultMapper actCardResultMapper;
    @Autowired
    private ActCardResultService actCardResultService;
    @Test
    public void testApply(){
        String sql = "NOT ( userId > 1004 AND type = 1)";
        QueryWrapper<ActCardResult> wrapper = new QueryWrapper<>();
        wrapper.apply(sql);
        List<ActCardResult> actCardResultList = actCardResultMapper.selectList(wrapper);
        System.out.println(actCardResultList);
    }
    @Test
    public void test1(){
        String sqlTemplate = "sqlTemplate=create_time < ?";
        System.out.println(sqlTemplate.split("\\s+")[1]);
        Map<String,String> map = new HashMap<>();
        map.put("id","Long");
        map.put("userId","Integer");
        map.put("name","String");
        map.put("create_time","Date");
        map.put("type","Integer");
        System.out.println(map);
    }
    @Test
    public void test2(){
        Class c = ActCardResult.class;
        Map<String,String> map = new HashMap<>();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();
            map.put(fieldName, fieldType);
        }
        System.out.println(map);
    }


}
