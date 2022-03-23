package controllers

import (
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/luvnyen/be-berat/pkg/models"
	"github.com/luvnyen/be-berat/pkg/utils"
)

var weight models.Weight
var weights []models.Weight

func GetData(c *gin.Context) {
	utils.ReadData(&weights)
	c.JSON(http.StatusOK, gin.H{
		"message": "Success",
		"data": weights,
	})
}

func GetDataByDate(c *gin.Context) {
	date := c.Param("date")
		
	var err error
	if weight, err = models.GetDataByDate(date); err != nil {
		c.JSON(http.StatusNotFound, gin.H{
			"message": "Not Found",
		})
		return
	}

	c.JSON(http.StatusOK, gin.H{
		"message": "Success",
		"data": weight,
	})
}

func CreateData(c *gin.Context) {
	c.BindJSON(&weight)

	if weight.Date == "" || weight.Max == 0 || weight.Min == 0 {
		c.JSON(http.StatusBadRequest, gin.H{
			"message": "Invalid data!",
		})
		return
	}
	utils.ReadData(&weights)
	if models.CheckDateExist(weight.Date) {
		c.JSON(http.StatusConflict, gin.H{
			"message": "Date already exist!",
		})
		return
	}
	if weight.Min > weight.Max {
		c.JSON(http.StatusBadRequest, gin.H{
			"message": "Min weight cannot be greater than max weight!",
		})
		return
	}

	models.CreateData(weight)

	c.JSON(http.StatusOK, gin.H{
		"message": "Successfully created new data!",
	})
}

func UpdateData(c *gin.Context) {
	date := c.Param("date")
	c.BindJSON(&weight)

	if weight.Date == "" || weight.Max == 0 || weight.Min == 0 {
		c.JSON(http.StatusBadRequest, gin.H{
			"message": "Invalid data!",
		})
		return
	} 
	if weight.Min > weight.Max {
		c.JSON(http.StatusBadRequest, gin.H{
			"message": "Min weight cannot be greater than max weight!",
		})
		return
	}
	
	err := models.UpdateData(date, weight)
	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{
			"message": "Not Found",
		})
		return
	}

	c.JSON(http.StatusOK, gin.H{
		"message": "Successfully updated data!",
	})
}

func DeleteData(c *gin.Context) {
	date := c.Param("date")

	err := models.DeleteData(date)
	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{
			"message": "Not Found",
		})
		return
	}
	
	c.JSON(http.StatusOK, gin.H{
		"message": "Successfully deleted data!",
	})
}