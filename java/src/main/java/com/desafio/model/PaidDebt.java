package com.desafio.model;

import java.time.LocalDate;

/** Representa uma dívida que já foi paga pelo consumidor. */
public class PaidDebt {
    private final double value;
    private final LocalDate dueDate;
    private final LocalDate paymentDate;
    private final String partnerId;

    public PaidDebt(double value, LocalDate dueDate, LocalDate paymentDate, String partnerId) {
        this.value = value;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.partnerId = partnerId;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getPartnerId() {
        return partnerId;
    }
}
