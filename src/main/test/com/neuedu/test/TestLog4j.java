package com.neuedu.test;

import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class TestLog4j {
    @Test
    public void test1(){
        Logger log = (Logger) LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        log.trace("trace level");
        log.debug("debug level");
        log.info("info level");
        log.error("error level");
        log.fatal("fatal level");


    }
}
