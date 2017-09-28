
参考 http://blog.csdn.net/qqhjqs/article/details/68941934

wx-root/pom.xml  项目用到的依赖写在主项目的pom里

wx-utils  自定义公共方法

wx-db  这里代码都用generator自动生成, 禁止人为改动,  主要提供实体类，CURD接口和xml映射文件

wx-service   逻辑处理 一定要在wx-service和wx-service2配置数据库的相关信息，并添加spring的相关配置

wx-job  任务调度

wx-api  供前端和其他系统调用


打包jar包
cd cd ~/github/springBootWx/wx-root/
wx-root >   mvn clean package --D maven.test.skip=true    // 打包前测试
wx-root >   mvn clean package                             // 直接打包
运行jar包
wx-root >   java -jar ./wx-api/target/wx.jar --server.port=8011 --spring.profiles.active=prod


tool_____________________________________________

### tools
参考: https://github.com/astarring/mybatis-generator-gui
cd ~/Documents/tools/mybatis-generator-gui-0.8.4/target/jfx/app/
java -jar mybatis-generator-gui.jar





