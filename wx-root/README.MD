
参考 http://blog.csdn.net/qqhjqs/article/details/68941934

wx-root/pom.xml  项目用到的依赖写在主项目的pom里

wx-dao层  这里代码都用generator自动生成 主要提供实体类，CURD接口和xml映射文件

wx-service层  一定要在wx-service和wx-service2配置数据库的相关信息，并添加spring的相关配置 






