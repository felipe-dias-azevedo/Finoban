package main

import "fmt"

// go run .
func main() {
	fmt.Printf("API Serasa iniciado...\n")
	controller := Controllers{}
	controller.iniciarApi()
}
