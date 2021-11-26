package main

import "fmt"

// go run .
func main() {
	fmt.Printf("API Serasa iniciada...\n")
	controller := Controllers{}
	controller.iniciarApi()
}
