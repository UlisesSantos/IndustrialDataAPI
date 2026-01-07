package com.IndustrialDataAPI.common;

import org.apache.logging.log4j.ThreadContext;
import java.util.UUID;

public class LogConfig {

    private static final String COMPONENT = "component";
    private static final String TRANSACTION_ID = "transactionId";

    public static void initLog4j2(){
        ThreadContext.put(COMPONENT, App.APPLICATION_NAME);
        ThreadContext.put(TRANSACTION_ID, UUID.randomUUID().toString());
    }
}
