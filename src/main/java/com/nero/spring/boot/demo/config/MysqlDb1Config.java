package com.nero.spring.boot.demo.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Nero
 * @since 2019-07-17 15:44
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.nero.spring.boot.demo.mapper.db1", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class MysqlDb1Config{

    @Value("${db.driver.class.name}")
    private String driver;
    @Value("${db1.url}")
    private String url;
    @Value("${db1.username}")
    private String username;
    @Value("${db1.password}")
    private String password;
    @Value("${db.defaultAutoCommit}")
    private boolean defaultAutoCommit;
    @Value("${db.login.timeout}")
    private int loginTimeOut;
    @Value("${db.pool.time.to.wait}")
    private int poolTimeToWait;
    @Value("${db.pool.maximum.active.connections}")
    private int poolMaximumActiveConnections;
    @Value("${db.pool.maximum.idle.connections}")
    private int poolMaximumIdleConnections;

    @Bean(name = "db1TransactionManager")
    @Primary
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "db1DataSorce")
    @Qualifier(value = "db1DataSorce")
    public DataSource dataSource(){
        PooledDataSource ds = new PooledDataSource();
        ds.setDriver(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDefaultAutoCommit(defaultAutoCommit);
        ds.setLoginTimeout(loginTimeOut);
        ds.setPoolTimeToWait(poolTimeToWait);
        ds.setPoolMaximumActiveConnections(poolMaximumActiveConnections);
        ds.setPoolMaximumIdleConnections(poolMaximumIdleConnections);
        return ds;
    }

    @Bean(name = "db1SqlSessionFactory")
    @Qualifier("db1SqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        VFS.addImplClass(SpringBootVFS.class);
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        //添加分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "false");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[]{pageHelper});

        // 设置默认别名
        sessionFactory.setTypeAliasesPackage("com.nero.spring.boot.demo.entities");
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:/mapper/db1/*.xml"));

        return sessionFactory;
    }
}
