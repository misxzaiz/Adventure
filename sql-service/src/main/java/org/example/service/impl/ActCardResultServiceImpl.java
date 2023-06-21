package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.ActCardResultMapper;
import org.example.pojo.ActCardResult;
import org.example.service.ActCardResultService;
import org.springframework.stereotype.Service;

@Service
public class ActCardResultServiceImpl extends ServiceImpl<ActCardResultMapper, ActCardResult> implements ActCardResultService {
}
