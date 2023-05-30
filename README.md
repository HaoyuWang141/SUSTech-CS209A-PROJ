# SUSTech-CS209A-Project

## Instruction

本次Project主要内容为使用java爬虫技术在StackOverflow中爬取java相关数据并利用网页进行可视化展示。

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

#### 爬虫

+ Http Request Generator
+ Json & Json Object Transfer
+ Java API Extractor

#### 后端

+ SpringBoot
+ MybatisPlus + PostgreSQL

#### 前端

+ Vue
+ Vue Router
+ Axios
+ Element Plus
+ Echarts

### 数据库设计

![ER graph](img\ER graph.png)

### Spring Boot 项目结构

+ config: 配置类
+ controller: 控制层
+ crawler & crawler_model: 爬虫相关，包括生成Http请求、生成json文件、转换json文本与对象
+ entity: 实体类，与数据库表一一对应
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

## Insights

### 统计范围

600个问题，共计10306条回答，22259条评论

### 统计分析

1. 从问题、回答的发布时间来看，2015年之前java比较火爆，高质量问题较多；
2. 从问题解决时间来看，某些问题发布后1s内就被解决了，且发布问题的用户和解决问题的用户是用一个人，存在刷分现象；
3. 从与java相关的标签来看，Android、Spring、SpringBoot出现频率较多，表示移动端开发及SpringBoot框架比较热门；另外，arrays，list，string等java常用的工具出现频率也比较高。
