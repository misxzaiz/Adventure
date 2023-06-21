# 综合能力题：

数据库中有一个表（例如下面的 act_card_result），现在需要在后台页面上，做一个可以灵活**筛选数据**的功能（从SQL的角度来说，就是拼接 **WHERE** 后面的查询语句），方便业务人员导出表里面的数据，类似数据库管理工具 Navicat 的筛选功能，如下图所示：

![image-20230621121601425](D:\Github\Adventure\assets\image-20230621121601425.png)

可以看到，Navicat 提供了一个灵活方便去填写过滤条件的界面，通过它，我们可以不用写SQL，也可以操作一些简单的筛选逻辑。

现在后台也需要这么一个功能，实现一个 SQL 过滤筛选器，**通过勾选条件自动生成sql然后将数据显示在同一个界面下方（记得要写查询得到的数据的显示界面噢~~~）**，UI 选型可以参考 https://querybuilder.js.org/ 组件，该组件已经实现了一个UI，界面操作已经可以自动生成一个SQL语句，请自学该组件，然后基于 Spring Boot 实现这个功能。

![image-20230621121810026](D:\Github\Adventure\assets\image-20230621121810026.png)

# 相关要求：

1）答题时长不设限制，**答完需要录制实现效果的视频和提交源代码**；

2）使用Vue + Element + Spring boot + Mybatis plus + MySQL实现一个 SQL 过滤筛选器，同时实现的过程必须可以灵活组合 AND OR 和括号的逻辑，如果只是实现 AND 或者 OR，技术难度将大大降低

3）编码上需结合hutool工具包：https://www.hutool.cn/做参数校验和lombok插件替换get、set方法