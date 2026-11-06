package com.desafio.model;

/**
 * Representa uma faixa de dias de atraso e o percentual de comissão cobrado
 * para essa faixa.
 */
public class PriceRange {
    private final int start;
    private final int end;
    private final double percentage;

    public PriceRange(int start, int end, double percentage) {
        this.start = start;
        this.end = end;
        this.percentage = percentage;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public double getPercentage() {
        return percentage;
    }
}
