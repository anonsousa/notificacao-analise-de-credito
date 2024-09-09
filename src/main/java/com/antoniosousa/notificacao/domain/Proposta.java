package com.antoniosousa.notificacao.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Proposta {

    private Long id;
    private BigDecimal valorSolicitado;
    private int prazoPagamento;
    private Boolean aprovada;
    private boolean integrada;
    private String observacao;
    private Usuario usuario;
}
