入云龙 2015年9月

 

环境要求:
RHEL7 & CentOS7   (推荐centos7, 可以直接使用epel源)

JDK 1.7                  (OpenJDK or Oracle不限)

Tomcat 7                (除solr外, 可以直接使用yum源中的安装包)

推荐的业务模块部署顺序:
1. taotao-manager

功能:

　　淘淘商城后台管理系统

商品管理

　　新增商品、查询商品、规格参数

　　(访问数据库，编辑商品类目、标头、卖点、价格、数量条形码、图片、商品描述、商品规格等信息)

网站内容管理

　　内容分类管理，内容管理（访问数据库，影响portal页面各区域的显示内容）

关联关系:

　　Mysql DB(db.properties)

　　FTP/Nginx图片服务器(resource.properties)

前端页面:

　　有

测试用例:

           

2. taotao-rest

功能:

　　前台系统服务层，功能是发布服务，便于适配Android，iOS和PC等不同的portal门户

关联关系:

　　Mysql DB(db.properties)

　　Redis(applicationContext-jedis.xml)

前端页面:

　　无

测试条件:

　　http://rest.taotao.com/rest/itemcat/list (全部商品分类展示json)

 

3. taotao-search

功能:

　　xxx

关联关系:

　　Mysql DB(db.properties)

　　sorl(resource.properties)

前端页面:

　　无

测试条件:

　　http://search.taotao.com/search/query?q=手机

　　http://search.taotao.com/search/manager/importall

 

4. taotao-sso

功能:

　　xxx

关联关系:

　　Mysql DB(db.properties)

　　Redis(applicationContext-jedis.xml)

前端页面:

　　有

测试条件:

　　http://sso.taotao.com/page/login

 

5. taotao-order

功能:

　　xxx

关联关系:

　　Mysql DB(db.properties)

　　Redis(applicationContext-jedis.xml)

前端页面:

　　无

测试条件:

　　单元功能就不测了，留到最终测试

 

6. taotao-portal

功能:

　　前台系统表现层，淘淘商城首页，没有业务逻辑，依靠调用taotao-rest完成服务

关联关系:

　　resource.properties

　　其他的所有localhost

前端页面:

　　无

测试条件:

　　http://www.taotao.com

　　完整下单流程
