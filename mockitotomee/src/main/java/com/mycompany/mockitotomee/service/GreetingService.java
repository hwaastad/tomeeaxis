/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mockitotomee.service;

import com.mycompany.mockitotomee.ejb.BusinessBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author helge
 */
@Singleton
@Path("greeting")
public class GreetingService {

    @EJB
    private BusinessBeanLocal businessBean;

    @GET
    public String message() {
        return businessBean.sayHello();
    }
}
