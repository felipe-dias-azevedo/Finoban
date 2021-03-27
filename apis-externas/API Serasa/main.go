package main

import (
	"database/sql"
	"fmt"
	"log"
	"net/http"

	_ "github.com/mattn/go-sqlite3"
)

func homePage(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, "Homepage Endpoint Hit")
}

func handleRequests() {
	http.HandleFunc("/consultas/v1/score", homePage)
	log.Fatal(http.ListenAndServe(":8082", nil))
}

func main() {
	handleRequests()
	criarBancoDados()
}

func criarBancoDados() {
	database, _ := sql.Open("sqlite3", "./serada.db")
	statement, _ := database.Prepare("CREATE TABLE IF NOT EXISTS Cliente (CNPJ INTEGER PRIMARY KEY, nome TEXT, patrimonio REAL, date DATE")
	statement.Exec()
	statement, _ = database.Prepare("INSERT INTO Cliente values (12345678900, 'Carlos Roberto', 50000.0, 1985-12-16), (12345, 'João da Silva', 10000.0, 1993-05-24), (123, 'Silvio Santos',  1000000.0, 1970-01-01), (456, 'Gabriel Rodrigues',  50000.0, 1995-03-27), (789, 'Fernando Brandão',  10000.0, 1998-06-14);")
	statement.Exec()
	rows, _ := database.Query("SELECT * FROM Cliente")
	var cnpj string
	var nome string
	var patrimonio float64
	var data string
	for rows.Next() {
		rows.Scan(&cnpj, &nome, &patrimonio, &data)
		fmt.Println(cnpj, ":", nome, ", ", patrimonio, ", ", data)
	}
}

type User struct {
	CNPJ string
}
