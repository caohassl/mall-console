package com.cmr.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by Administrator on 2017/12/6.
 */
@Configuration
public class DBConfig {

    /**
     * c3p0 configure
     * @return
     * @throws PropertyVetoException
     */
    @Bean(name="dataSource")
    @ConfigurationProperties(prefix = "cmr.c3p0")
    public DataSource dataSource() throws PropertyVetoException {

       return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
    }


    /**
     * scan mapper.xml and the packages,
     * it can be also written on the yml file
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis-mapper/**/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.cmr.entities");
        return sqlSessionFactoryBean.getObject();
    }

}
