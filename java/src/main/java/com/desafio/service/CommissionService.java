package com.desafio.service;

import com.desafio.model.PaidDebt;
import com.desafio.model.PriceTable;

import java.util.List;

public class CommissionService {

    /**
     * Calcula o total de dívidas pagas e o total de comissão devida por um
     * parceiro, de acordo com a tabela de preços dele.
     *
     * <p>TODO: implemente este método.
     *
     * <p>Passos sugeridos:
     * <ol>
     *   <li>Encontre a {@link PriceTable} do parceiro em {@code priceTables}
     *       (compare o partnerId). Se não encontrar, lance
     *       {@link PriceTableNotFoundException}.</li>
     *   <li>Para cada {@link PaidDebt} que pertence a esse parceiro:
     *     <ol>
     *       <li>Calcule os dias de atraso: paymentDate - dueDate (em dias).
     *           Se o pagamento foi antes ou no vencimento, considere 0 dias de atraso.</li>
     *       <li>Descubra em qual faixa (PriceRange) esses dias de atraso se
     *           encaixam (start &lt;= dias &lt;= end).</li>
     *       <li>Some o valor da dívida ao total de dívidas.</li>
     *       <li>Some (valor da dívida * percentual / 100) ao total de comissão.</li>
     *     </ol>
     *   </li>
     *   <li>Retorne um {@link CommissionResult} com os totais calculados.</li>
     * </ol>
     */
    public CommissionResult calculate(String partnerId, List<PaidDebt> paidDebts, List<PriceTable> priceTables) {
        throw new UnsupportedOperationException("not implemented");
    }
}
