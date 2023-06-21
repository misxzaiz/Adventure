package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.dto.Result;
import org.example.mapper.ActCardResultMapper;
import org.example.pojo.ActCardResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/actCardResult")
public class ActCardResultController {

    @Autowired
    private ActCardResultMapper actCardResultMapper;

    @GetMapping("/queryBuilder/{sql}")
    public Result queryBySql(@PathVariable String sql){
        QueryWrapper<ActCardResult> wrapper = new QueryWrapper<>();
        wrapper.apply(sql);
        List<ActCardResult> actCardResultList = actCardResultMapper.selectList(wrapper);
        System.out.println(actCardResultList);
        // 设置允许跨域请求的源
        return Result.ok(actCardResultList,"获取成功！");
    }

}
