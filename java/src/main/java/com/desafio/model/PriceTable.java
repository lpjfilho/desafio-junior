package com.desafio.model;

import java.util.List;

/** Tabela de preços (faixas de comissão) de um parceiro. */
public class PriceTable {
    private final String partnerId;
    private final List<PriceRange> ranges;

    public PriceTable(String partnerId, List<PriceRange> ranges) {
        this.partnerId = partnerId;
        this.ranges = ranges;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public List<PriceRange> getRanges() {
        return ranges;
    }
}
