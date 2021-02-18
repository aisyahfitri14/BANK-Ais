/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bni.nasabah.service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import bni.nasabah.model.*;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 *
 * @author Aisyah
 */
@Service
public class NotificationMailer {
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private SpringTemplateEngine templateEngine;
    
    @Value("${spring.mail.host}")
    private String emailHost;
    
    public void sendEmail(Mailer mail,List<File> resources,Context context,String template) {

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
                MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                                StandardCharsets.UTF_8.name());
                
                String html = templateEngine.process(template, context);
                
                helper.setTo(mail.getTo());
                helper.setFrom(emailHost);
                helper.setSubject(mail.getSubject());
                helper.setText(html, true);
                
                if(resources != null){
                    for (File resource : resources) {
                        helper.addAttachment(resource.getName(), resource);
                    }
                }

                javaMailSender.send(message);
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
     }
}
