package com.antoniosousa.notificacao.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifySnsService {

    @Autowired
    private AmazonSNS amazonSNS;

    public void notify(String telefone, String message) {
        PublishRequest publishRequest = new PublishRequest().withMessage(message).withPhoneNumber(telefone);
        amazonSNS.publish(publishRequest);
    }
}
