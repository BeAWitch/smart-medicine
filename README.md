# smart-medicine
课程设计 - 智慧医药系统



本项目是基于[这个项目](https://github.com/373675032/smart-medicine)完成的。

## 项目介绍

智慧医药系统（smart-medicine）是一个基于 SpringBoot 开发的标准 Java Web 项目。整合阿里的通义千问大语言模型技术充当**智能医生**，使用自训练的图像识别模型实现**中草药识别**功能，使用腾讯地图提供**医院查询服务**。整体来看是一个偏向百科查询类的系统，功能设计的较为简单，便于初学者理解和学习，也适合学校中的项目答辩或者毕业设计。

### 角色介绍

系统共设计了五个角色：游客、普通用户、信息管理员、文章管理员、用户管理员。

1. 游客：尚未进行注册和登录。具备登录注册、疾病、药品的搜索、查询详情等权限。
2. 普通用户：除了游客的功能权限外，还具备登录、个人资料的修改、登录密码修改、意见反馈、智能医生咨询等权限。
3. 信息管理员：除了用户的功能权限外，还具备疾病、药品、科普视频、用户反馈管理权限。
4. 文章管理员：除了用户的功能权限外，还具备文章管理权限。
5. 用户管理员：除了用户的功能权限外，还具备用户管理权限。

### 功能介绍

| 功能模块   | 功能描述                                                     |
| ---------- | ------------------------------------------------------------ |
| 登录注册   | 填写用户信息进行账号注册（邮件接收验证码）、使用账号密码进行登录。 |
| 个人资料   | 修改个人资料（姓名、年龄、手机号、头像等）、修改登录密码。   |
| 信息查询   | 查询疾病、药品、科普视频、文章信息。                         |
| 提交反馈   | 提交对系统的反馈信息。                                       |
| 智能医生   | 与智能医生进行交流聊天。                                     |
| 中草药识别 | 上传图片判别草药类型。                                       |
| 医院查询   | 根据用户位置显示周边医院。                                   |
| 疾病管理   | 发布疾病、编辑（名称、原因、症状、分类等）、删除药品等。     |
| 药品管理   | 发布药品、编辑（名称、搜索关键词、功效、用法用量、类型等）、关联疾病、删除药品等。 |
| 视频管理   | 发布和编辑文章。                                             |
| 用户管理   | 对用户的增删改操作。                                         |
| 反馈管理   | 管理用户提交的反馈信息。                                     |

>对于中草药识别功能，使用的模型是我们随便训练的。训练时只使用了5类中草药，且效果一般。
>
>对于医院查询功能，这个功能并不完善，只能在地图上显示出医院的位置。

### 技术介绍

#### 前端

| 名称               | 描述                                                         |
| ------------------ | ------------------------------------------------------------ |
| HTML、CSS          | 用于设计网页的内容和样式。                                   |
| Thymeleaf          | Thymeleaf是一款可在浏览器直接显示的模板引擎，与Spring MVC等集成，用于渲染XML/HHTML/HTML5内容。 |
| JavaScript、JQuery | 作为开发 Web 页面的脚本语言，为网页添加各式各样的动态功能，为用户提供更流畅美观的浏览效果。 |
| Bootstrap          | 基于 HTML、CSS、JavaScript 开发的简洁、直观、强悍的前端开发框架，使得 Web 开发更加快捷。 |

#### 后端

| 名称         | 描述                                                         |
| ------------ | ------------------------------------------------------------ |
| SpringBoot   | SpringBoot 是由 Pivotal 团队提供的全新框架，其设计目的是用来简化新 Spring 应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。通过这种方式，SpringBoot 致力于在蓬勃发展的快速应用开发领域成为领导者。 |
| SpringMVC    | Spring MVC 属于 SpringFrameWork 的后续产品，已经融合在 Spring Web Flow 里面。Spring 框架提供了构建 Web 应用程序的全功能 MVC 模块。 |
| MyBatis-plus | MyBatis-Plus是MyBatis的增强工具，用于简化开发和提高效率，它只做增强不做改变。 |
| Thymeleaf    | Thymeleaf 是一个流行的模板引擎，该模板引擎采用 Java 语言开发的，用于渲染 XML/XHTML/HTML5 内容的模板引擎。类似 JSP、Velocity、FreeMaker 等，它也可以轻易的与 Spring MVC 等 Web 框架进行集成作为 Web 应用的模板引擎。 |
| Druid        | Druid 是一个高效的数据查询系统，主要解决的是对于大量的基于时序的数据进行聚合查询。数据可以实时摄入，进入到 Druid 后立即可查，同时数据是几乎是不可变。通常是基于时序的事实事件，事实发生后进入 Druid，外部系统就可以对该事实进行查询。 |

#### 开发工具与环境

- **开发工具**

  - IntelliJ IDEA 2023.3.4：主要进行系统的开发、系统调试等。
    - 使用到的插件
      - Lombok：使用注解代替在实体类中添加的 get/set/toString 方法等。
  - VS code：主要进行前端页面的开发和模型训练。
  - Navcat：主要进行数据库的连接、建库建表、系统调试等。

- **开发环境**

  - JDK 17
  - Maven 4.0
  - MySQL 5.7

- **阿里云 OSS 对象存储**

  阿里云对象存储 OSS（Object Storage Service）是一款海量、安全、低成本、高可靠的云存储服务，对于我们这个项目而言，所有的二进制文件，包括头像、用户上传的文件都是存储到了 OSS 里面（调用相关的工具类），在数据库中只是保存了文件的 URL 地址。通过这个 URL 地址就可以获取、下载指定文件

- **阿里云 AIGC 通义千问大语言模型**

  通义千问，是阿里云推出的一个超大规模的语言模型，功能包括多轮对话、文案创作、逻辑推理、多模态理解、多语言支持。能够跟人类进行多轮的交互，也融入了多模态的知识理解，且有文案创作能力，能够续写小说，编写邮件等。

- 图像识别

- 腾讯地图

### 页面效果

前端的修改不多，具体的页面效果请看原项目。
