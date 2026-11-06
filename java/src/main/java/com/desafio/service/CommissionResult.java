package com.desafio.service;

/** Resultado do cálculo de comissão de um parceiro. */
public class CommissionResult {
    private final String partnerId;
    private final double totalDebts;
    private final double totalCommission;

    public CommissionResult(String partnerId, double totalDebts, double totalCommission) {
        this.partnerId = partnerId;
        this.totalDebts = totalDebts;
        this.totalCommission = totalCommission;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public double getTotalDebts() {
        return totalDebts;
    }

    public double getTotalCommission() {
        return totalCommission;
    }

    @Override
    public String toString() {
        return String.format("CommissionResult{partnerId='%s', totalDebts=%.2f, totalCommission=%.2f}",
                partnerId, totalDebts, totalCommission);
    }
}
