package main

import (
	"database/sql"
	"fmt"

	_ "github.com/mattn/go-sqlite3"
)

type Database struct{}

func (d Database) selectBancoDeDados(cpfCliente int) Usuario {
	fmt.Println(cpfCliente)
	database, _ := sql.Open("sqlite3", "./serasa.db")
	var query string = fmt.Sprintf("SELECT * FROM Cliente where CPF = %d", cpfCliente)
	rows, _ := database.Query(query)
	var usuario Usuario
	var cpf int
	var nome string
	var patrimonio float64
	var data string
	var score int
	for rows.Next() {
		rows.Scan(&cpf, &nome, &patrimonio, &data, &score)
		fmt.Println(cpf, ":", nome, ", ", patrimonio, ", ", data, ", ", score)
		usuario.CPF = cpf
		usuario.Nome = nome
		usuario.Patrimonio = patrimonio
		usuario.Data = data
		usuario.Score = score
		return usuario
	}
	return usuario
}
