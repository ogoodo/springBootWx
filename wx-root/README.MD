
参考 http://blog.csdn.net/qqhjqs/article/details/68941934

本项目是作为微信公众号后台

项目配置文件
  wx-api/src/main/resources/application.properties
  wx-api/src/main/resources/config/application.properties




各模块说明______________
  wx-root/pom.xml  项目用到的依赖写在主项目的pom里
  wx-utils  自定义公共方法
  wx-db  这里代码都用generator自动生成, 禁止人为改动,  主要提供实体类，CURD接口和xml映射文件
  wx-service   逻辑处理 一定要在wx-service和wx-service2配置数据库的相关信息，并添加spring的相关配置
  wx-job  任务调度，定时任务
  wx-api  供前端和其他系统调用



启动项目————----
1、启动mysql

2、打包jar包
cd ~/github/springBootWx/wx-root/
wx-root >   mvn clean package --D maven.test.skip=true    // 打包前测试
wx-root >   mvn clean package                             // 直接打包
3.1、运行jar包
wx-root >   java -jar ./wx-api/target/wx.jar --server.port=8011 --spring.profiles.active=prod --logging.level=DEBUG
3.2、eclipse里启动项目
3.2.1、运行【wx-api WxApp】


验证地址_____________________________________________

http://localhost:8011/swagger-ui.html  wagger地址
http://localhost:8011/test/mybatis/select?userid=13


tool_____________________________________________

### tools
参考: https://github.com/astarring/mybatis-generator-gui
cd ~/Documents/tools/mybatis-generator-gui-0.8.4/target/jfx/app/
java -jar mybatis-generator-gui.jar



todo
[第三章 EnumUtil根据值获取枚举对象](http://www.cnblogs.com/jian-xiao/p/6036925.html)


