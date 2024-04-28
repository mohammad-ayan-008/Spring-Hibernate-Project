package org.example;



import java.util.*;
import javax.persistence.Entity;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.MySQL57Dialect;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("system.properties")
@ComponentScan("org.example")
public class config {

    @Value("${hibernate.dialect}")
    private String Dialect;
    
    @Value("${hibernate.show_sql}")
    private String show_sql;
    
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;
    
    @Value("${hibernate.database.driver}")
    private String driver;
    
    @Value("${hibernate.database.url}")
    private String url;
    
    @Value("${hibernate.database.username}")
    private String userName;
    
    @Value("${hibernate.database.password}")
    private String password;
    
    @Bean("dao")
    public DAO getDAO() {
        return new DAO();
    }
    

    @Bean("student")
    public HibernateTemplate getStudent(SessionFactory sessionFactory) {
        HibernateTemplate temp = new HibernateTemplate();
        temp.setSessionFactory(sessionFactory);
        return temp;
    }

    @Bean
    public SessionFactory getFactory() throws Exception {
        LocalSessionFactoryBean fac = new LocalSessionFactoryBean();
        fac.setDataSource(getDatasource());
        Properties p = new Properties();
        p.setProperty("hibernate.dialect", Dialect);
        p.setProperty("hibernate.show_sql", show_sql);
        p.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
        fac.setPackagesToScan(new String[]{"org.example"});
        fac.setHibernateProperties(p);
        fac.setAnnotatedClasses(new Class<?>[]{Student2.class});
        fac.setAnnotatedPackages(new String[]{"org.example"});
        fac.afterPropertiesSet(); // Ensure properties are properly set
        return fac.getObject();
    }

    @Bean
    public DriverManagerDataSource getDatasource() {
        DriverManagerDataSource d = new DriverManagerDataSource();
        d.setDriverClassName(driver);
        d.setUrl(url);
        d.setUsername(userName);
        d.setPassword(password);
        return d;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
    
 
   
}