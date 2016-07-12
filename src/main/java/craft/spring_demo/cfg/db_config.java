package craft.spring_demo.cfg;

import craft.spring_demo.model.RecordEntity;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.hsqldb.jdbcDriver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by phdnk on 10-Jul-16.
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = { "craft.spring_demo.controller"
                              , "craft.spring_demo.service"
                              , "craft.spring_demo.model"
                              , "craft.spring_demo.dao"
                              })
@PropertySource("classpath:application.properties")
public class db_config {

    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /** Sets up the hibernate transaction manager */
    @Bean
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }


    /** Sets up a data source using HyperSQL (hsqldb)*/
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:schema.sql")
                .build();
    }

    /** Sets up the session factory */
    @Bean
    public LocalSessionFactoryBean sessionFactory(Environment environment, DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan(RecordEntity.class.getPackage().getName());
        factoryBean.setHibernateProperties(buildHibernateProperties());
        return factoryBean;
    }


    /** Loading all the hibernate properties from a properties file */
    private Properties buildHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect"                , environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql"               , environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql"             , environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.use_sql_comments"       , environment.getProperty("hibernate.use_sql_comments"));
        properties.put("hibernate.hbm2ddl.auto"           , environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.generate_statistics"    , environment.getProperty("hibernate.generate_statistics"));
        properties.put("javax.persistence.validation.mode", environment.getProperty("javax.persistence.validation.mode"));

        //Audit History flags
        properties.put("org.hibernate.envers.store_data_at_delete"     , environment.getProperty("org.hibernate.envers.store_data_at_delete"));
        properties.put("org.hibernate.envers.global_with_modified_flag", environment.getProperty("org.hibernate.envers.global_with_modified_flag"));
        return properties;
    }

}
