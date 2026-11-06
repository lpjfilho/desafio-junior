package service

import (
	"testing"
	"time"

	"desafio-junior/model"
)

func TestCalculateCommission(t *testing.T) {
	priceTables := []model.PriceTable{
		{
			PartnerID: "1",
			Ranges: []model.PriceRange{
				{Start: 0, End: 30, Percentage: 5},
				{Start: 31, End: 9999, Percentage: 10},
			},
		},
	}

	paidDebts := []model.PaidDebt{
		{
			Value:       100,
			DueDate:     time.Date(2025, 1, 1, 0, 0, 0, 0, time.UTC),
			PaymentDate: time.Date(2025, 1, 10, 0, 0, 0, 0, time.UTC), // 9 dias de atraso -> 5%
			PartnerID:   "1",
		},
		{
			Value:       200,
			DueDate:     time.Date(2025, 1, 1, 0, 0, 0, 0, time.UTC),
			PaymentDate: time.Date(2025, 2, 10, 0, 0, 0, 0, time.UTC), // 40 dias de atraso -> 10%
			PartnerID:   "1",
		},
		{
			// dívida de outro parceiro, não deve entrar no cálculo
			Value:       999,
			DueDate:     time.Date(2025, 1, 1, 0, 0, 0, 0, time.UTC),
			PaymentDate: time.Date(2025, 1, 1, 0, 0, 0, 0, time.UTC),
			PartnerID:   "2",
		},
	}

	result, err := CalculateCommission("1", paidDebts, priceTables)
	if err != nil {
		t.Fatalf("erro inesperado: %v", err)
	}

	wantDebts := 300.0
	wantCommission := 5.0 + 20.0 // 5% de 100 + 10% de 200

	if result.TotalDebts != wantDebts {
		t.Errorf("TotalDebts = %v, esperado %v", result.TotalDebts, wantDebts)
	}
	if result.TotalCommission != wantCommission {
		t.Errorf("TotalCommission = %v, esperado %v", result.TotalCommission, wantCommission)
	}
}

func TestCalculateCommission_PartnerSemTabela(t *testing.T) {
	_, err := CalculateCommission("inexistente", nil, nil)
	if err == nil {
		t.Fatal("esperava erro para parceiro sem tabela de preço")
	}
}
