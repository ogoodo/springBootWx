
参考 http://blog.csdn.net/qqhjqs/article/details/68941934

wx-root/pom.xml  项目用到的依赖写在主项目的pom里

wx-dao  这里代码都用generator自动生成 主要提供实体类，CURD接口和xml映射文件

wx-service   逻辑处理 一定要在wx-service和wx-service2配置数据库的相关信息，并添加spring的相关配置 

wx-job  任务调度

wx-api  供前端和其他系统调用


打包jar包
wx-root >   mvn clean package --D maven.test.skip=true
     mvn clean package Ptest  这样子好像是打测试包
运行jar包
wx-root >   java -jar ./wx-service/target/wx.jar --server.port=9000


