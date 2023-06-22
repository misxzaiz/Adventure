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
          id: 'createTime',
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

// $('#btn-reset').on('click', function() {
//   $('#builder-basic').queryBuilder('reset');
// });

// $('#btn-set').on('click', function() {
//   $('#builder-basic').queryBuilder('setRules', rules_basic);
// });

// $('#btn-get-sql').on('click', function() {
//   var result = $('#builder-basic').queryBuilder('getSQL', null);
//   alert(result.sql);
// });

