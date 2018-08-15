package com.xx.application.config;

import java.util.Properties;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSourceFactory;

@Configuration    //该注解类似于spring配置文件  
@MapperScan(basePackages="com.zb.application.dao")  
public class MybatisConfig {
	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${mybatis.typeAliasesPackage}")
	private String typeAliasesPackage;
	@Value("${mybatis.mapperLocations}")
	private String mapperLocations;
	/**
	 * 创建数据源
	 * @throws Exception 
	 * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错   
	 */
	@Bean
	public DataSource getDataSource() throws Exception {
		Properties prop = new Properties();
		prop.put("driverClassName", driverClassName);
		prop.put("url", url);
		prop.put("username", username);
		prop.put("password", password);
		return DruidDataSourceFactory.createDataSource(prop);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();  
		//指定数据源(这个必须有，否则报错) 
        fb.setDataSource(ds); 
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加  
        fb.setTypeAliasesPackage(typeAliasesPackage);//指定基包  
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));//指定xml文件位置  
        return fb.getObject(); 
	}
			
}
