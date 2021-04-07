package main

import (
	"database/sql"
	"fmt"

	_ "github.com/mattn/go-sqlite3"
)

type Database struct{}

func (d Database) selectBancoDeDados(cnpjCliente int) Usuario {
	fmt.Println(cnpjCliente)
	database, _ := sql.Open("sqlite3", "./serasa.db")
	var query string = fmt.Sprintf("SELECT * FROM Cliente where CNPJ = %d", cnpjCliente)
	rows, _ := database.Query(query)
	var usuario Usuario
	var cnpj int
	var nome string
	var patrimonio float64
	var data string
	var score int
	for rows.Next() {
		rows.Scan(&cnpj, &nome, &patrimonio, &data, &score)
		fmt.Println(cnpj, ":", nome, ", ", patrimonio, ", ", data, ", ", score)
		usuario.CNPJ = cnpj
		usuario.Nome = nome
		usuario.Patrimonio = patrimonio
		usuario.Data = data
		usuario.Score = score
		return usuario
	}
	return usuario
}
