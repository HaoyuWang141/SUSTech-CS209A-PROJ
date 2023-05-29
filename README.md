# SUSTech-CS209A-Project

## Instruction



## Start

1. git clone 仓库
2. 运行 SpringBoot 项目
3. 运行 Vue 项目
   1. 安装 node.js
   2. 进入 vue-project 文件夹
   3. 在 git bash 中运行 `npm install`. 该命令用于安装 vue-project 的依赖包
   4. 在 git bash 中运行 `npm run dev`. 该命令用于启动开发环境(即dev), 然后可以用浏览器在指定端口打开页面



## 设计与结构

### 技术栈

#### 后端

+ spring boot
+ Restful 风格
+ MybatisPlus + PostgreSQL

#### 前端

+ Vue
+ Vue Router
+ Axios
+ Element UI
+ Echarts

### 数据库设计

![ER graph](img\ER graph.png)

### Spring Boot 项目结构

+ config 配置类
+ controller 控制层
+ entity 实体类: 与数据库表一一对应
+ mapper: 用以 Mybatis 映射数据库

### Vue 项目结构

+ assets: 全局设置
+ components: 通用组件. 主要包含 Echarts 在 Vue 中的实现
+ icons: 图标
+ layout: 整体布局
  + Sidebar: 侧边栏
  + AppMain: 主体内容显示
  + Navbar: 导航栏
+ router: 路由配置
+ views: 主要视图, 将加载到 AppMain 中



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



## Task

### 2023-5-18

**已完成:**

1. 数据库部分: ER图, 建表语句, 大部分mapper;
2. 后端部分: SpringBoot 项目结构, 绝大部分entity, 大部分controller;
3. 前端部分: 绝大部分网页结构

**待完成:**

1. 爬虫: 爬取数据并存入数据库 (使用云数据库?)
2. 后端: 需完成controller, 在此过程中顺手更新 数据库表, entity, mapper
3. 前端: 在后端完全搞定之后, 再小修小改一下



## 2023-5-29

待完成：

1. 爬取更多comment
2. 展示哪些 Java APIs 在 Stack Overflow 上最频繁被讨论
