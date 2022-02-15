package com.example.admitad.configuration;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
 public class DataSourceConfig {
    private final Environment environment;

    @Bean
    public DataSource dataSource() {
        return getDataSourceBuilder().build();
    }

    private DataSourceBuilder getDataSourceBuilder() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username(environment.getProperty("spring.datasource.username"));
        dataSourceBuilder.password(environment.getProperty("spring.datasource.password"));
        dataSourceBuilder.url(environment.getProperty("spring.datasource.url"));
        return dataSourceBuilder;
    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource());
//        return factoryBean.getObject();
//    }

    @Primary
    @Bean("db1SqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean b1 = new MybatisSqlSessionFactoryBean();
        System.out.println("dataSourceLyz" + dataSource.toString());
        b1.setDataSource(dataSource);
        b1.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        return b1.getObject();
    }
}