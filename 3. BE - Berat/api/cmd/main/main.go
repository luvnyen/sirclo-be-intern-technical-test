package main

import (
	"log"
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/luvnyen/be-berat/pkg/routes"
	"github.com/luvnyen/be-berat/pkg/utils"
)

func main() {
	router := gin.Default()
	
	router.Use(utils.CORSMiddleware())
	routes.RegisterWeightRoutes(router)

	log.Fatal(http.ListenAndServe(":8080", router))
}