package com.spring.dallija.common.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

import java.sql.SQLException;

import static com.spring.dallija.common.constants.DataSourceKey.MASTER;
import static com.spring.dallija.common.constants.DataSourceKey.SLAVE;
import static org.springframework.transaction.support.TransactionSynchronizationManager.isCurrentTransactionReadOnly;

@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        boolean currentTransactionReadOnly = isCurrentTransactionReadOnly();
        if (currentTransactionReadOnly){
            log.debug("start slave db");
        }else {
            log.debug("start master db");
        }

        return currentTransactionReadOnly ? SLAVE : MASTER;
    }
}