package com.desafio.service;

import com.desafio.model.PaidDebt;
import com.desafio.model.PriceRange;
import com.desafio.model.PriceTable;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommissionServiceTest {

    private final CommissionService service = new CommissionService();

    @Test
    void calculaComissaoCorretamente() {
        List<PriceTable> priceTables = List.of(
                new PriceTable("1", List.of(
                        new PriceRange(0, 30, 5),
                        new PriceRange(31, 9999, 10)
                ))
        );

        List<PaidDebt> paidDebts = List.of(
                // 9 dias de atraso -> 5%
                new PaidDebt(100, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 10), "1"),
                // 40 dias de atraso -> 10%
                new PaidDebt(200, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 2, 10), "1"),
                // dívida de outro parceiro, não deve entrar no cálculo
                new PaidDebt(999, LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 1), "2")
        );

        CommissionResult result = service.calculate("1", paidDebts, priceTables);

        assertEquals(300.0, result.getTotalDebts(), 0.001);
        assertEquals(25.0, result.getTotalCommission(), 0.001); // 5% de 100 + 10% de 200
    }

    @Test
    void lancaExcecaoQuandoParceiroNaoTemTabela() {
        assertThrows(PriceTableNotFoundException.class,
                () -> service.calculate("inexistente", List.of(), List.of()));
    }
}
