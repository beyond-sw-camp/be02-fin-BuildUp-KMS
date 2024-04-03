package com.example.bootshelf.config.replication;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;

public class RoutingDataSource extends AbstractRoutingDataSource {
    public RoutingDataSource(Map<Object, Object> targetDataSources, Object defaultTargetDataSource) {
        super.setTargetDataSources(targetDataSources);
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        afterPropertiesSet(); // InitializingBean을 구현하므로 스프링이 빈을 초기화할 때 호출됨
    }
    @Override
    protected Object determineCurrentLookupKey() {

        String dataSourceKey = TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "slave" : "master";

        return dataSourceKey;

//        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "slave" : "master";
    }
}
