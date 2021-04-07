package main

import (
	"encoding/json"
	"log"
	"net/http"
)

type Controllers struct{}

func (c Controllers) homePage(w http.ResponseWriter, r *http.Request) {

	var user User
	var response Response
	var errorMessage ErrorMessage
	database := Database{}

	err := json.NewDecoder(r.Body).Decode(&user)
	defer r.Body.Close()
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	var score int = database.selectBancoDeDados(user.CNPJ).Score
	if score == 0 {
		errorMessage.Ok = false
		errorMessage.Status = 400
		errorMessage.Message = "Cliente não encontrado em nossa base."
		jsonError, err := json.Marshal(errorMessage)
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusBadRequest)
		w.Write(jsonError)
	} else {
		response.Ok = true
		response.Status = 200
		response.Data.Score = score
		jsonResponse, err := json.Marshal(response)
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}

		w.Header().Set("Content-Type", "application/json")
		w.Write(jsonResponse)

	}
}

func (c Controllers) iniciarApi() {
	controller := Controllers{}
	http.HandleFunc("/consultas/v1/score", controller.homePage)
	log.Fatal(http.ListenAndServe(":8082", nil))

}
