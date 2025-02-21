package com.vincle.ahl.demo.infraestructure.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.vincle.ahl.demo.application.port.out.ItemRepository;
import com.vincle.ahl.demo.application.service.ItemService;
import com.vincle.ahl.demo.domain.service.ItemDomainService;

/**
 * Configuration class for the application.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.vincle.ahl.demo.infraestructure.adapter.out.persistence")
public class AppConfig {

    /**
     * Creates an ItemService bean.
     *
     * @param itemRepository the item repository
     * @param itemDomainService the item domain service
     * @return the item service
     */
    @Bean
    public ItemService itemService(ItemRepository itemRepository, ItemDomainService itemDomainService) {
        return new ItemService(itemRepository, itemDomainService);
    }

    /**
     * Creates an ItemDomainService bean.
     *
     * @return the item domain service
     */
    @Bean
    public ItemDomainService itemDomainService() {
        return new ItemDomainService();
    }

    /**
     * Configures the data source for the application.
     *
     * @return the data source
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:./data/items.db");
        return dataSource;
    }
    
    /**
     * Configures CORS settings for the application.
     *
     * @return the WebMvcConfigurer with CORS settings
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }
}
