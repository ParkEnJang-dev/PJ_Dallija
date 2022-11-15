package com.spring.dallija.common.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import static com.spring.dallija.common.constants.DataSourceKey.MASTER;
import static com.spring.dallija.common.constants.DataSourceKey.SLAVE;
import static org.springframework.transaction.support.TransactionSynchronizationManager.isCurrentTransactionReadOnly;


public class RoutingDataSource extends AbstractRoutingDataSource {
    //현재 트랜잭션이 readonly=true 인지 판단.
    @Override
    protected Object determineCurrentLookupKey() {
        return isCurrentTransactionReadOnly() ? SLAVE : MASTER;
    }

}
