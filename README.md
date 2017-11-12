# HttpClient
这个demo为调用后台服务的接口，使用SSM框架搭建

1、使用浏览器测试登陆功能(以我本机为例)

打开登陆页面：http://localhost:8080/httpserver/showLogin  然后进行登陆


2、使用restclient-ui-3.5-jar-with-dependencies.jar或者Postman工具测试全部功能

登陆操作：使用POST方式，携带用户名和密码这两个参数访问网址：http://localhost:8080/httpserver/login

删除操作：使用DELETE方式，根据id值删除某个用户：http://localhost:8080/httpserver/user/4


3、使用提供的API类测试
