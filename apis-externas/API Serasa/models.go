package main

type Usuario struct {
	CPF        int     `json:"cpf"`
	Nome       string  `json:"nome"`
	Patrimonio float64 `json:"patrimonio"`
	Data       string  `json:"data"`
	Score      int     `json:"score"`
}

type User struct {
	CPF int `json:"cpf"`
}

type Response struct {
	Ok     bool `json:"ok"`
	Status int  `json:"status"`
	Data   Data `json:"data"`
}
type Data struct {
	Score int `json:"score"`
}
type ErrorMessage struct {
	Ok      bool   `json:"ok"`
	Status  int    `json:"status"`
	Message string `json:"message"`
}
