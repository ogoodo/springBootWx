package com.ogoodo.wx.test.mybatis.config;

//@Configuration
//@EnableTransactionManagement
public class MyBatisConfig {

//    @Autowired
//    private DataSource dataSource;
//
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager() throws Exception {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        //sql的xml文件的存放路径
//        Resource[] mapperLocations = resolver.getResources("classpath:com/ogoodo/wx/test/mybatis/mapper/*.xml");
//        sessionFactory.setMapperLocations(mapperLocations);
//        //将数据查询的数据转成一个java的model对象，即对象所在的包路径
//        sessionFactory.setTypeAliasesPackage("com.ogoodo.wx.test.mybatis.entity");
//
//        //mybatis的一些个性化的配置
//        Resource configLocation = resolver.getResource("classpath:config/mybatis-config.xml");
//        sessionFactory.setConfigLocation(configLocation);
//        //这边必须要这样写，如果不使用spring boot 的方式的话，是不需要这么写的，具体详见spring-mybatis的官方文档
//        return sessionFactory.getObject();
//    }


}

/**
 * MyBatis基础配置
 *
 * @author liuzh
 * @since 2015-12-19 10:11
 */
//@Configuration
//@EnableTransactionManagement
//public class MyBatisConfig implements TransactionManagementConfigurer {
//
//    @Autowired
//    DataSource dataSource;
//
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactoryBean() {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setTypeAliasesPackage("tk.mybatis.springboot.model");
//
//        //分页插件
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
//        properties.setProperty("params", "count=countSql");
//        pageHelper.setProperties(properties);
//
//        //添加插件
//        bean.setPlugins(new Interceptor[]{(Interceptor) pageHelper});
//
//        //添加XML目录
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        try {
//            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
//            return bean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//    @Bean
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//}
