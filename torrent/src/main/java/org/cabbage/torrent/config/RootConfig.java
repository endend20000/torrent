package org.cabbage.torrent.config;
import java.util.Properties;  
import javax.sql.DataSource;  
import org.mybatis.spring.SqlSessionFactoryBean;  
import org.mybatis.spring.mapper.MapperScannerConfigurer;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.ComponentScan;  
import org.springframework.context.annotation.ComponentScan.Filter;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;  
import org.springframework.core.io.Resource;  
import org.springframework.jdbc.datasource.DataSourceTransactionManager;  
import org.springframework.stereotype.Repository;  
import org.springframework.stereotype.Service;  
import org.springframework.transaction.PlatformTransactionManager;  
import org.springframework.transaction.annotation.EnableTransactionManagement;  
import org.springframework.transaction.annotation.TransactionManagementConfigurer;  
  
@Configuration  
//定义Spring 扫描的包  
//@ComponentScan(value= "com.*", includeFilters= {@Filter(type = FilterType.ANNOTATION, value ={Service.class})})  
//使用事务驱动管理器  
//@EnableTransactionManagement  
@ImportResource("classpath*:spring.xml")
//实现接口TransactionManagementConfigurer，这样可以配置注解驱动事务  
public class RootConfig{  

}
