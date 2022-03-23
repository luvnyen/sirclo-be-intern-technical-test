package routes

import (
	"github.com/gin-gonic/gin"
	"github.com/luvnyen/be-berat/pkg/controllers"
)

var RegisterWeightRoutes = func(router *gin.Engine) {
	router.GET("/", func(c *gin.Context) {
		c.JSON(404, gin.H{
			"message": "Not Found!",
		})
	})

	router.GET("/weight", controllers.GetData)
	router.POST("/weight", controllers.CreateData)
	router.GET("/weight/:date", controllers.GetDataByDate)
	router.PUT("/weight/:date", controllers.UpdateData)
	router.DELETE("/weight/:date", controllers.DeleteData)
}