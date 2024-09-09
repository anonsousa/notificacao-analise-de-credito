package com.antoniosousa.notificacao.listener;

import com.antoniosousa.notificacao.domain.Proposta;
import com.antoniosousa.notificacao.messages.ConstantMessage;
import com.antoniosousa.notificacao.service.NotifySnsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaPendenteListener {

    @Autowired
    private NotifySnsService notifySnsService;

    @RabbitListener(queues = "${rabbit.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta) {
        String message = String.format(ConstantMessage.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
        notifySnsService.notify(proposta.getUsuario().getTelefone(), message);
    }
}
