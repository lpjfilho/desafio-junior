package model

// PriceRange representa uma faixa de dias de atraso e o percentual de
// comissão cobrado para essa faixa.
type PriceRange struct {
	Start      int
	End        int
	Percentage float64
}

// PriceTable é a tabela de preços (faixas de comissão) de um parceiro.
type PriceTable struct {
	PartnerID string
	Ranges    []PriceRange
}
