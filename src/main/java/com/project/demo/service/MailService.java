package com.project.demo.service;

import org.springframework.stereotype.Service;

import com.project.demo.entities.MailData;

@Service
public interface MailService {
    public void sendEmail(MailData mail);
}