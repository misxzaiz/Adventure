## 后端

```java
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
```

## 前端

```java
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="../staic/js/jquery-3.7.0.js"></script>
    <script src="../staic/js/1.0.0_jquery-extendext.js"></script>
    <script src="../staic/js/doT.js"></script>
    <link rel="stylesheet" href="../staic/css/query-builder.default.min.css">
    <script src="../staic/js/query-builder.min.js"></script>
    <link rel="stylesheet" href="../staic/css/bootstrap.min.css">
    <script src="../staic/js/bootstrap.min.js"></script>
    <script src="../staic/js/query-builder.zh-CN.js"></script>
    <script src="../staic/js/sql-parser.js"></script>
    <script src="../staic/js/moment.min.js"></script>
</head>
<body>
    <div id="builder-basic" class="query-builder form-inline"></div>

    <div class="btn-group">
        <button id="btn-reset" class="btn btn-warning reset" data-target="basic">重置</button>
        <button id="btn-set" class="btn btn-success set-json" data-target="basic">默认</button>
        <button id="btn-get-sql" class="btn btn-primary parse-json" data-target="basic">获取 SQL</button>
    </div>
    <div id="builder-rule"></div>
    <!-- <script src="index.js"></script> -->
    <script>
        var rules_basic = {
            condition: 'AND',
            rules: [{
                id: 'id',
                operator: 'is_not_null'
            }]
        };

        $('#builder-basic').queryBuilder({
            plugins: [
                'bt-tooltip-errors',
                'not-group'
            ],
            
            filters: [{
                    id: 'id',
                    label: 'ID',
                    type: 'integer',
                    operators: ['equal', 'not_equal', 'less', 'less_or_equal', 'greater', 'greater_or_equal', 'in', 'not_in']
                }, {
                    id: 'userId',
                    label: '用户ID',
                    type: 'integer',
                    operators: ['equal', 'not_equal', 'less', 'less_or_equal', 'greater', 'greater_or_equal', 'in', 'not_in']
                }, {
                    id: 'name',
                    label: '名称',
                    type: 'string',
                    operators: ['equal', 'not_equal', 'contains', 'not_contains', 'starts_with', 'ends_with']
                }, {
                    id: 'create_time',
                    label: '创建时间',
                    type: 'date',
                    operators: ['between', 'not_between', 'less', 'less_or_equal', 'greater', 'greater_or_equal', 'is_null', 'is_not_null'],
                    validation: {
                    format: 'yyyy-mm-dd hh:ii:ss'
                }
                }, {
                    id: 'type',
                    label: '类型',
                    type: 'integer',
                    input: 'select',
                    values: {
                        0: '类型1',
                        1: '类型2'
                    },
                    operators: ['equal', 'not_equal']
            }],

            rules: rules_basic
        });

        $('#btn-reset').on('click', function() {
        $('#builder-basic').queryBuilder('reset');
        });

        $('#btn-set').on('click', function() {
        $('#builder-basic').queryBuilder('setRules', rules_basic);
        });


        $('#btn-get-sql').on('click', function() {
            var result = $('#builder-basic').queryBuilder('getSQL', null);
            alert(result.sql);
        });
    </script>

</body>
</html>
```

