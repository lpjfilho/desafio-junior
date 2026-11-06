package com.desafio.service;

/** Lançada quando o parceiro não possui tabela de preço cadastrada. */
public class PriceTableNotFoundException extends RuntimeException {
    public PriceTableNotFoundException(String partnerId) {
        super("tabela de preço não encontrada para o parceiro: " + partnerId);
    }
}
