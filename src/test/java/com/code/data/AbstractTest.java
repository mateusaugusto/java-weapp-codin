package com.code.data;


import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.code.data.ConnectorsDataApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConnectorsDataApplication.class)
@WebAppConfiguration   
@IntegrationTest("server.port:0")  

/**
 * The AbstractTest class is the parent of all JUnit test classes. This class
 * configures the test Application and test runner environment.
 * 
 */
public abstract class AbstractTest {

    /**
     * The Logger instance for all classes in the unit test framework.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

}