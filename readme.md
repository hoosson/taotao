入云龙 2015年9月


配置环境:

eclipse mars版本

JDK 1.7  (OpenJDK or Oracle不限)

Tomcat 7 (除solr外, 可以直接使用yum源中的安装包)

RHEL7 & CentOS7   (推荐centos7, 可以直接使用epel源)


推荐的业务模块部署顺序:

1. taotao-manager （淘淘商城后台管理系统）

功能：
	商品管理 => 新增商品、查询商品、规格参数
	  (访问数据库，编辑商品类目、标头、卖点、价格、数量条形码、图片、商品描述、商品规格等信息)
	网站内容管理 => 内容分类管理，内容管理（访问数据库，影响portal页面各区域的显示内容）

关联关系:
    Mysql DB(db.properties)
    FTP/Nginx图片服务器(resource.properties)

前端页面：有
测试条件: 
	1.1 taotao-parent 工程上右键 -------> Run As -------> Maven install 
	1.2 taotao-common 工程上右键 -------> Run As -------> Maven install
	1.3 taotao-manager工程上右键 -------> Run As -------> Maven build... ------->Goals栏输入 clean tomcat7:run -------> 勾上Skip Tests ----> Run
	1.4 浏览器输入： http://localhost:8080 （本地测试）

           

2. taotao-rest （前台系统服务层）

功能：发布服务，便于适配Android，iOS和PC等不同的portal门户

关联关系:
　　Mysql DB(db.properties)
　　Redis(applicationContext-jedis.xml)

前端页面：无
测试条件： 
	http://rest.taotao.com/rest/itemcat/list (全部商品分类展示json)
	本地测试： http://localhost:8081/rest/itemcat/list
	http://localhost:8081/rest/item/desc/商品id   //商品描述
	http://localhost:8081/rest/item/param/商品id  //规格参数


3. taotao-search （搜索）
关联关系:
　　Mysql DB(db.properties)
　　sorl(resource.properties)
  
前端页面：无
测试条件:
　　http://search.taotao.com/search/query?q=手机
　　http://search.taotao.com/search/manager/importall

本地测试
	http://localhost:8083/search/query?q=手机
　　http://localhost:8083/search/manager/importall   //导入所有搜索数据

 

4. taotao-sso （单点登录）

功能：单点登录
关联关系:
　　Mysql DB(db.properties)
　　Redis(applicationContext-jedis.xml)

前端页面：有
测试条件： http://sso.taotao.com/page/login
本地测试： http://localhost:8084/page/login
 

5. taotao-order

功能：购物车、订单
关联关系:
　　Mysql DB(db.properties)
　　Redis(applicationContext-jedis.xml)

前端页面： 无
测试条件： 单元功能就不测了，留到最终测试

 

6. taotao-portal

功能：前台系统表现层，淘淘商城首页，没有业务逻辑，依靠调用taotao-rest完成服务

关联关系:
　　resource.properties
　　其他的所有localhost

前端页面：无
测试条件： http://www.taotao.com
本地测试： http://localhost:8082

　　
