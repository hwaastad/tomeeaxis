/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mockitotomee.activation;

import com.mycompany.mockitotomee.service.GreetingService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author helge
 */
@ApplicationPath("services")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
//        return new HashSet<Class<?>>(Arrays.asList(SimpleRESTPojo.class, SimpleRESTEJB.class));
        return new HashSet<Class<?>>(Arrays.asList(GreetingService.class));
    }
}
