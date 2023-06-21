package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.crypto.Data;

@lombok.Data
@TableName("act_card_result")
public class ActCardResult {
    private Long id;
    @TableField(value = "userId")
    private Integer userId;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Data createTime;
    private Integer type;
}
