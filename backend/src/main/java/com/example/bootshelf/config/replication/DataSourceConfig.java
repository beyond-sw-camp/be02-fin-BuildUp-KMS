package com.example.bootshelf.config.replication;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.Map;

@EnableConfigurationProperties(DataSourceProperties.class)
@Configuration
@AllArgsConstructor
public class DataSourceConfig {

    private final DataSourceProperties dataSourceProperties;
    public static final String MASTER_DATASOURCE = "masterDataSource";
    public static final String SLAVE_DATASOURCE = "slaveDataSource";

    @Bean(MASTER_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(dataSourceProperties.getDriverClassName())
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .build();
    }

    @Bean(SLAVE_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.slaves.slave1")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(dataSourceProperties.getSlaves().get("slave1").getDriverClassName())
                .url(dataSourceProperties.getSlaves().get("slave1").getUrl())
                .username(dataSourceProperties.getSlaves().get("slave1").getUsername())
                .password(dataSourceProperties.getSlaves().get("slave1").getPassword())
                .build();
    }

    @Bean
    @Primary
    @DependsOn({MASTER_DATASOURCE, SLAVE_DATASOURCE})
    public DataSource routingDataSource(
            @Qualifier(MASTER_DATASOURCE) DataSource masterDataSource,
            @Qualifier(SLAVE_DATASOURCE) DataSource slaveDataSource) {

        RoutingDataSource routingDataSource = new RoutingDataSource(
                Map.of("master", masterDataSource, "slave", slaveDataSource),
                masterDataSource
        );
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}
