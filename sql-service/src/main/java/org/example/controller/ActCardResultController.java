package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Result;
import org.example.dto.SQLDto;
import org.example.mapper.ActCardResultMapper;
import org.example.pojo.ActCardResult;
import org.example.service.ActCardResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/actCardResult")
public class ActCardResultController {
    @Autowired
    private ActCardResultMapper actCardResultMapper;
    @Autowired
    private ActCardResultService actCardResultService;

    @PostMapping("/queryBuilder")
    public Result queryBySql(@RequestBody SQLDto sqlDto){
        return actCardResultService.queryBySqlDto(sqlDto);
    }

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
