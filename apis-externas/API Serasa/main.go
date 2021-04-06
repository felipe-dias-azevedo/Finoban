package main

import (
	"database/sql"
	"fmt"
	"log"
	"net/http"

	"encoding/json"
	_ "encoding/json"

	_ "github.com/mattn/go-sqlite3" // Add This
)

func homePage(w http.ResponseWriter, r *http.Request) {
	// fmt.Fprint(w, "Homepage Endpoint Hit")
	var user User
	var response Response
	err := json.NewDecoder(r.Body).Decode(&user)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	response.Ok = true
	response.Status = 200
	response.Data.Score = selectBancoDeDados(user.CNPJ).Score
	w.WriteHeader(200)
	jsonResponse, err := json.Marshal(response)
	w.Write(jsonResponse)
}

func handleRequests() {
	http.HandleFunc("/consultas/v1/score", homePage)
	log.Fatal(http.ListenAndServe(":8082", nil))

}

func main() {
	handleRequests()

}

func selectBancoDeDados(cnpjCliente int) Usuario {
	database, _ := sql.Open("sqlite3", "./serasa.db")
	rows, _ := database.Query("SELECT * FROM Cliente")
	var usuario Usuario
	var cnpj int
	var nome string
	var patrimonio float64
	var data string
	var score int
	for rows.Next() {
		rows.Scan(&cnpj, &nome, &patrimonio, &data, &score)
		if cnpj == cnpjCliente {
			fmt.Println(cnpj, ":", nome, ", ", patrimonio, ", ", data, ", ", score)
			usuario.CNPJ = cnpj
			usuario.Nome = nome
			usuario.Patrimonio = patrimonio
			usuario.Data = data
			usuario.Score = score
			return usuario
		}
	}
	return usuario
}

type Usuario struct {
	CNPJ       int
	Nome       string
	Patrimonio float64
	Data       string
	Score      int
}

type User struct {
	CNPJ int
}

type Response struct {
	Ok     bool
	Status int
	Data   Data
}
type Data struct {
	Score int
}
