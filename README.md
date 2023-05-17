# SUSTech-CS209A-Project

## Introduction



## 技术栈

### 后端

+ spring boot
+ Restful 风格
+ MybatisPlus
+ ApiFox

### 前端

+ Vue
+ Element UI
+ Axios
+ Vue Router



## 数据库设计

![ER graph](img\ER graph.png)



## Spring Boot 项目结构

+ config 配置类
+ controller 控制层
+ entity 实体类: 与数据库表一一对应
+ model: 对entity中的数据进行解释和封装
+ domain: 对model中的数据进行整合, 最终发送给前端
+ interceptor 拦截器: 
+ mapper: 用以 mybatis 映射数据库 
+ service: 处理业务逻辑



## Vue 项目结构



## Basic Requirements

### Number of Answers

1. 没有答案的问题占比: 饼状图
2. answer 数量的 avg 和 max: 表格
3. answer 数量分布: 直方图, 横坐标为answer数量范围, 纵坐标为question数量
4. 问题数, 回答数分布随时间的变化: 点线图

### Accepted Answers

1. 展示有 accepted answer 问题的百分比: 旭日图
2. 问题解决时间的分布: 直方图, 横坐标为时长范围, 纵坐标为问题数量
3. 展示 non-accepted answer 的 upvote 更高的问题数量及百分比: 同一旭日图

### Tags	

1. 经常和java一起出现的tag: 词云图
2. 展示tag得到最多的upvotes: 极坐标系下的柱状图
3. 展示tags得到最多的upvotes: 关系图
4. 展示tag得到最多的views: 极坐标系下的柱状图
5. 展示tags得到最多的views: 关系图

### Users

1. 用户数量分布+回答者+评论者: 3个直方图 + 表格(avg+max+min)
2. 用户数量变化: 数量随时间变化: 点线图
3. 活跃用户(取前十): 环形柱状图

