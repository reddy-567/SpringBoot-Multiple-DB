package com.example.springbootmultipledb.book.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="bookEntityManagerFactory",
							basePackages= {"com.example.springbootmultipledb.book.repository"},
								transactionManagerRef = "bookTransactionManager")
public class BookDBConfig {
	
	
	
	@Bean
	@ConfigurationProperties(prefix="spring.book.datasource")
	public DataSource bookDataSource() {
		return  DataSourceBuilder.create().build();
	}
	
	
	/*public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,@Qualifier("datasource") javax.sql.DataSource dataSource)
	{
		Map<String,Object> properties=new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect","org.hibernate.dialect.MYSQL5Dialect");
		return builder.dataSource(dataSource).properties(properties).packages("com.example.springbootmultipledb.model.user").persistenceUnit("User").build();
	}*/
	
	
	@Bean(name="bookEntityManagerFactory")
	//@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(bookDataSource());
        em.setPackagesToScan(
                new String[] { "com.example.springbootmultipledb.model.book"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","update");
              
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
               
        em.setJpaPropertyMap(properties);

        return em;
    }
	
	
	@Bean(name="bookTransactionManager")
	//@Primary
	public PlatformTransactionManager userTransactionManager(@Qualifier("bookEntityManagerFactory")EntityManagerFactory entityManagerFactory)
	{
		JpaTransactionManager transactionManager=new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	

}
