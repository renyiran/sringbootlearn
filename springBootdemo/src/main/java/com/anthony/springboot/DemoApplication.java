package com.anthony.springboot;

import com.anthony.springboot.configbean.UserConfig;
import com.anthony.springboot.mapper.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties({UserConfig.class})
// 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableTransactionManagement
@MapperScan(basePackages = "com.anthony.springboot.dao", markerInterface = MyMapper.class)
public class DemoApplication {

    //Environment可以加载application.properties文件中的属性
//    @Autowired
//    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    //Spring Boot默认使用tomcat-jdbc数据源  改用其他数据源要添加下面的方法：
    //destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
//    @Bean(destroyMethod = "close")
//    public DataSource datasource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(env.getProperty("spring.datasource.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource.password"));
//        //初始化时建立物理连接的个数
//        dataSource.setInitialSize(2);
//        //最大连接池数量
//        dataSource.setMaxActive(20);
//        //最小连接池数量
//        dataSource.setMinIdle(1);
//        //获取连接时最大等待时间，单位毫秒。
//        dataSource.setMaxWait(60000);
//        //用来检测是否连接有效的sql
//        dataSource.setValidationQuery("SELECT 1");
//        //申请连接时执行validationQuery检测连接是否有效
//        dataSource.setTestOnBorrow(false);
//        //归还连接时执行validationQuery检测连接是否有效，
//        dataSource.setTestOnReturn(false);
//        //申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
//        dataSource.setTestWhileIdle(true);
//        dataSource.setTimeBetweenEvictionRunsMillis(1000);
//        //是否缓存preparedStatement，也就是PSCache
//        dataSource.setPoolPreparedStatements(false);
//        return dataSource;
//    }
}
