package model

import "time"

// PaidDebt representa uma dívida que já foi paga pelo consumidor.
type PaidDebt struct {
	Value       float64
	DueDate     time.Time
	PaymentDate time.Time
	PartnerID   string
}
