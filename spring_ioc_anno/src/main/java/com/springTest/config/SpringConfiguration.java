package com.springTest.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

//标志该类是Spring核心配置类
@Configuration
//<context:component-scan base-package="com.springTest"/>
@ComponentScan("com.springTest")

@PropertySource({"classpath:atest.properties","classpath:jdbc.properties","classpath:druid.properties"})

@Import(DataSourceSpringConfiguration.class)
public class SpringConfiguration {

}
