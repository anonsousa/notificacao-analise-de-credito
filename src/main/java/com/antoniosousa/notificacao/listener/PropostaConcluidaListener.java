package com.antoniosousa.notificacao.listener;

import com.antoniosousa.notificacao.domain.Proposta;
import com.antoniosousa.notificacao.messages.ConstantMessage;
import com.antoniosousa.notificacao.service.NotifySnsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    @Autowired
    private NotifySnsService notifySnsService;

    @RabbitListener(queues = "${rabbit.queue.proposta.concluida}")
    public void propostaConcluida(Proposta proposta) {
        String message = "";

        if (!proposta.getAprovada()) {
            message = String.format(ConstantMessage.PROPOSTA_CONCLUIDA_ERRO,
                    proposta.getUsuario().getNome(),
                    proposta.getValorSolicitado(),
                    proposta.getObservacao());
        } else {
            message = String.format(ConstantMessage.PROPOSTA_CONCLUIDA_SUCESSO,
                    proposta.getUsuario().getNome(),
                    proposta.getValorSolicitado());
        }
        notifySnsService.notify(proposta.getUsuario().getTelefone(), message);


    }


}
