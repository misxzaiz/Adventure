package org.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Result;
import org.example.dto.SQLDto;
import org.example.mapper.ActCardResultMapper;
import org.example.pojo.ActCardResult;
import org.example.service.ActCardResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

@Slf4j
@Service
public class ActCardResultServiceImpl extends ServiceImpl<ActCardResultMapper, ActCardResult> implements ActCardResultService {
    @Autowired
    private ActCardResultMapper actCardResultMapper;

    @Override
    public Result queryBySqlDto(SQLDto sqlDto) {
        // 参数校验
        boolean b = verifySql(sqlDto);
        // 使用 hutool 校验是否为 true
        if (Validator.isFalse(b)){
            return Result.fail("参数错误");
        }
        log.info("【SQLDto】{}",sqlDto);
        QueryWrapper<ActCardResult> wrapper = new QueryWrapper<>();
        wrapper.apply(sqlDto.getSql());
        List<ActCardResult> actCardResultList = actCardResultMapper.selectList(wrapper);
        System.out.println(actCardResultList);
        return Result.ok(actCardResultList,"获取成功！");
    }

    /**
     * 参数校验
     * @param type
     * @param parameter
     * @return
     */
    private boolean verifyParameter(String type,String parameter){
        if (StrUtil.isBlank(type) || StrUtil.isBlank(parameter)) {
            return false;
        }

        Map<String, Predicate<String>> validators = new HashMap<>();
        validators.put("String", StrUtil::isNotBlank);
        validators.put("Integer", NumberUtil::isInteger);
        validators.put("Long", NumberUtil::isLong);
        validators.put("Double", NumberUtil::isDouble);
        validators.put("Date", s -> {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            try {
                df.parse(s);
                return true;
            } catch (ParseException e) {
                // 如果解析失败，则返回false
                return false;
            }
        });
        Predicate<String> validator = validators.get(type);
        if (validator == null) {
            // 其他类型的参数校验
            return true;
        }
        return validator.test(parameter);
    }

    private boolean verifySql(SQLDto sqlDto){
        // 参数
        List<String> parameters = sqlDto.getParameters();
        int size = parameters.size();
        log.info("【parameters】{}",parameters);
        int count = 0; // 记录当前的参数
        // 切割字符串
        String[] sqlTemplates = cutSqlTemplate(sqlDto.getSqlTemplate());
        // 获取实体类的属性及参数
        Map<String, String> classType = getClassTypeReflect(ActCardResult.class);
        // 遍历sqlTemplates，以其元素为map的key，如果存在，就进行处理
        // 如果是 BETWEEN ，那么他会有两个参数
        // 记录上一个值类型，在template.equals("BETWEEN")时使用
        String classWithBETTWEEN = null;
        for (String template : sqlTemplates){
            if (template.equals("NULL")){
                count--;
            }
            // 如果存在BETWEEN，继续校验下一个参数
            if (template.equals("BETWEEN")){
                if (count<size){
                    String parameter = parameters.get(count);
                    boolean b = verifyParameter(classWithBETTWEEN, parameter);
                    if (Validator.isFalse(b)){
                        return false;
                    }
                    count++;
                }
                continue;
            }

            if (classType.containsKey(template)){
                String type = classType.get(template);
                classWithBETTWEEN = type;
                log.info("【type】{}",type);
                // 获取参数
                if (count<size){
                    String parameter = parameters.get(count);
                    log.info("【parameter】{}", parameter);
                    // TODO 参数校验
                    boolean b = verifyParameter(type, parameter);
                    if (Validator.isFalse(b)){
                        return false;
                    }
                    log.info("【参数校验】");
                    count++;
                }
            }
        }
        return true;
    }

    /**
     * 通过空格分割字符串
     * @param sqlTemplate
     * @return
     */
    private String[] cutSqlTemplate(String sqlTemplate){
        // \\s+ 表示匹配一个或多个连续的空格字符。
        return sqlTemplate.split("\\s+");
    }

    /**
     * 获取类的属性及类型（可使用反射修改）
     * @return
     */
    private Map<String,String> getClassType(){
        Map<String,String> map = new HashMap<>();
        map.put("id","Long");
        map.put("userId","Integer");
        map.put("name","String");
        map.put("create_time","Date");
        map.put("type","Integer");
        return map;
    }

    /**
     * 使用反射获取类的属性及类型
     * @return
     */
    private Map<String,String> getClassTypeReflect(Class c){
        Map<String,String> map = new HashMap<>();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();
            map.put(fieldName, fieldType);
        }
        return map;
    }
}
