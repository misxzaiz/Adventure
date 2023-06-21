### index.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./static/css/element.css" />
    <script src="./static/js/vue@3.js"></script>
    <script src="./static/js/element-plus.js"></script>
    <script src="./static/js/axios.min.js"></script>
    <script src="./static/js/icon-element.js"></script>
    <script src="./static/js/jquery-3.7.0.js"></script>

    <script src="./static/js/1.0.0_jquery-extendext.js"></script>
    <script src="./static/js/doT.js"></script>
    <link rel="stylesheet" href="./static/css/query-builder.default.min.css">
    <script src="./static/js/query-builder.min.js"></script>
    <link rel="stylesheet" href="./static/css/bootstrap.min.css">
    <script src="./static/js/bootstrap.min.js"></script>
    <script src="./static/js/query-builder.zh-CN.js"></script>
    <script src="./static/js/sql-parser.js"></script>
    <script src="./static/js/moment.min.js"></script>
    <link rel="stylesheet" href="index.css">
</head>

<body>
    <div id="builder-basic" class="query-builder form-inline"></div>

    <div id="app">
        <div>
            <div class="btn-group">
                <button @click="reset" class="btn btn-warning reset" data-target="basic">重置</button>
                <button @click="setDefault" class="btn btn-success set-json" data-target="basic">默认</button>
                <button @click="querySQL" class="btn btn-primary parse-json" data-target="basic">获取 SQL</button>
            </div>
        </div>

        <table v-if="actCardResults!=null">
            <tr>
                <th>id</th>
                <th>userId</th>
                <th>name</th>
                <th>createTime</th>
                <th>type</th>
              </tr>
            <tr v-for="(actCardResult, index) in actCardResults" :key="index">
              <td>{{actCardResult.id}}</td>
              <td>{{actCardResult.userId}}</td>
              <td>{{actCardResult.name}}</td>
              <td>{{actCardResult.createTime}}</td>
              <td>{{actCardResult.type}}</td>
            </tr>
        </table>

    </div>

    <script src="index.js"></script>
    <script>
        let reqUrl = "http://127.0.0.1"
        
     
        
        const App = {
            data() {
                return {
                    sql: '',
                    actCardResults: null
                }
            },
            methods: {
                reset() {
                    $('#builder-basic').queryBuilder('reset');
                },
                setDefault() {
                    $('#builder-basic').queryBuilder('setRules', rules_basic);
                },
                querySQL() {
                    let that = this;
                    var result = $('#builder-basic').queryBuilder('getSQL', null);
                    axios.get(reqUrl + "/actCardResult/queryBuilder/"+result.sql)
                        .then(res => {
                            console.log(res);
                            that.actCardResults = res.data.data
                        })
                        .catch(err => {
                            console.log(err);
                        });
                }
            },
            created() {
                
            },
            mounted() {

            }
        };
        const app = Vue.createApp(App);
        app.use(ElementPlus);
        app.mount("#app");

    </script>
</body>
</html>
```

### index.js

```js
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
```

### index.css

```css
/* 设置表格边框和间距 */
table {
    border-collapse: collapse;
    border-spacing: 0;
}

/* 设置表头的样式 */
th {
    background-color: #f2f2f2;
    font-weight: bold;
    text-align: left;
    padding: 8px;
    border: 1px solid #ddd;
}

/* 设置数据单元格的样式 */
td {
    padding: 8px;
    border: 1px solid #ddd;
}

/* 设置鼠标悬停时行的背景色 */
tr:hover {
    background-color: #f5f5f5;
}
```

