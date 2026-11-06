package service

import (
	"errors"

	"desafio-junior/model"
)

// ErrPriceTableNotFound é retornado quando o parceiro não possui tabela de preço cadastrada.
var ErrPriceTableNotFound = errors.New("tabela de preço não encontrada para o parceiro")

// Result é o resultado do cálculo de comissão de um parceiro.
type Result struct {
	PartnerID       string
	TotalDebts      float64
	TotalCommission float64
}

// CalculateCommission calcula o total de dívidas pagas e o total de comissão
// devida por um parceiro, de acordo com a tabela de preços dele.
//
// TODO: implemente esta função.
//
// Passos sugeridos:
//  1. Encontre a PriceTable do parceiro em priceTables (compare PartnerID).
//     Se não encontrar, retorne ErrPriceTableNotFound.
//  2. Para cada PaidDebt que pertence a esse parceiro:
//     a. Calcule os dias de atraso: PaymentDate - DueDate (em dias).
//        Se o pagamento foi antes ou no vencimento, considere 0 dias de atraso.
//     b. Descubra em qual PriceRange esses dias de atraso se encaixam
//        (Start <= dias <= End).
//     c. Some o valor da dívida em TotalDebts.
//     d. Some (valor da dívida * percentual / 100) em TotalCommission.
//  3. Retorne o Result preenchido.
func CalculateCommission(partnerID string, paidDebts []model.PaidDebt, priceTables []model.PriceTable) (Result, error) {
	return Result{}, errors.New("not implemented")
}
