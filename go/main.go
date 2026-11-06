package main

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"time"

	"desafio-junior/model"
	"desafio-junior/service"
)

func main() {
	partnerID := "1"
	result, err := service.CalculateCommission(partnerID, getPaidDebts(), getPriceTables())
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("Parceiro %s: total de dívidas = %.2f, total de comissão = %.2f\n",
		partnerID, result.TotalDebts, result.TotalCommission)

	// Bônus: endpoint HTTP simples.
	// Experimente: go run . e depois acesse http://localhost:8080/comissao?partnerId=1
	http.HandleFunc("/comissao", handleComissao)
	log.Println("servidor rodando em http://localhost:8080")
	log.Fatal(http.ListenAndServe(":8080", nil))
}

func handleComissao(w http.ResponseWriter, r *http.Request) {
	partnerID := r.URL.Query().Get("partnerId")
	if partnerID == "" {
		http.Error(w, "informe o parâmetro partnerId", http.StatusBadRequest)
		return
	}

	result, err := service.CalculateCommission(partnerID, getPaidDebts(), getPriceTables())
	if err != nil {
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(result)
}

func getPartners() []model.Partner {
	return []model.Partner{
		{ID: "1", Name: "Parceiro 1"},
		{ID: "2", Name: "Parceiro 2"},
	}
}

func getPriceTables() []model.PriceTable {
	return []model.PriceTable{
		{
			PartnerID: "1",
			Ranges: []model.PriceRange{
				{Start: 0, End: 30, Percentage: 5},
				{Start: 31, End: 9999, Percentage: 10},
			},
		},
		{
			PartnerID: "2",
			Ranges: []model.PriceRange{
				{Start: 0, End: 30, Percentage: 3},
				{Start: 31, End: 9999, Percentage: 7},
			},
		},
	}
}

func getPaidDebts() []model.PaidDebt {
	return []model.PaidDebt{
		{
			Value:       100,
			DueDate:     time.Date(2025, 2, 1, 0, 0, 0, 0, time.UTC),
			PaymentDate: time.Date(2025, 2, 10, 0, 0, 0, 0, time.UTC),
			PartnerID:   "1",
		},
		{
			Value:       100,
			DueDate:     time.Date(2025, 2, 1, 0, 0, 0, 0, time.UTC),
			PaymentDate: time.Date(2025, 4, 2, 0, 0, 0, 0, time.UTC),
			PartnerID:   "1",
		},
		{
			Value:       100,
			DueDate:     time.Date(2025, 1, 1, 0, 0, 0, 0, time.UTC),
			PaymentDate: time.Date(2025, 4, 2, 0, 0, 0, 0, time.UTC),
			PartnerID:   "2",
		},
	}
}
