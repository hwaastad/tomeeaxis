/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mockitotomee.ejb;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author helge
 */
@Stateless
public class BusinessBean implements BusinessBeanLocal {

    @Resource(name = "MailProvider")
    private Session mailSession;

    @Resource
    SessionContext context;

    @Override
    public String sayHello() {
        Message message = new MimeMessage(mailSession);
        return "Hello";
    }

}
