/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mockitotomee.service;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.mycompany.mockitotomee.ejb.BusinessBeanLocal;
import java.net.URL;
import java.util.Properties;
import javax.annotation.processing.Messager;
import javax.naming.Context;
import org.apache.openejb.OpenEjbContainer;
import org.apache.openejb.core.LocalInitialContextFactory;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.junit.Configuration;
import org.apache.openejb.junit.MockInjector;
import org.apache.openejb.junit.Module;
import org.apache.openejb.loader.IO;
import org.apache.openejb.mockito.MockitoInjector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;
import static org.mockito.Mockito.*;

/**
 *
 * @author helge
 */
@RunWith(ApplicationComposer.class)
public class GreetingServiceIT {

    @Mock
    private BusinessBeanLocal mockBusinessBean;
    
    @Configuration
    public Properties configuration() {
        return new Properties() {
            private static final long serialVersionUID = 3109256773218160485L;

            {
                setProperty(OpenEjbContainer.OPENEJB_EMBEDDED_REMOTABLE, Boolean.TRUE.toString());
                setProperty("MailProvider", "new://Resource=type=javax.mail.Session");
                setProperty("MailProvider.mail.smtp.host", "127.0.0.1");
                setProperty("MailProvider.mail.smtp.port", "25");
                setProperty("MailProvider.mail.transport.protocol", "smtp");
                setProperty("MailProvider.mail.smtp.auth", "false");
                setProperty("MailProvider.mail.smtp.user", "someuser");
                setProperty("MailProvider.password", "mypassword");
                setProperty(Context.INITIAL_CONTEXT_FACTORY, LocalInitialContextFactory.class.getName());
                String providers = String.format("%s", JacksonJsonProvider.class.getName());
                setProperty("cxf.jaxrs.providers", providers);
            }
        };
    }

    @MockInjector
    public Class<?> mockitoInjector() {
        return MockitoInjector.class;
    }

    @Module
    public Class<?>[] app() {
        return new Class<?>[]{GreetingService.class, BusinessBeanLocal.class};
    }

    public GreetingServiceIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of message method, of class GreetingService.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testMessage() throws Exception {
        when(mockBusinessBean.sayHello()).thenReturn("mockito");
        final String message = IO.slurp(new URL("http://localhost:4204/GreetingServiceIT/greeting/"));
        Assert.assertEquals("mockito", message);
        verify(mockBusinessBean,times(1)).sayHello();
    }

}
