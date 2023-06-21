## act_card_result 数据表（db数据库）

```java
CREATE TABLE act_card_result (
  id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  userId INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  type TINYINT(1) NOT NULL
);

INSERT INTO act_card_result (userId, name, type) VALUES
(1001, '张三', 1),
(1002, '李四', 0),
(1003, '王五', 1),
(1004, '赵六', 0),
(1005, '钱七', 1);
```

