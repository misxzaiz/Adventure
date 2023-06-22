package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dto.Result;
import org.example.dto.SQLDto;
import org.example.pojo.ActCardResult;

public interface ActCardResultService extends IService<ActCardResult> {
    Result queryBySqlDto(SQLDto sqlDto);
}
