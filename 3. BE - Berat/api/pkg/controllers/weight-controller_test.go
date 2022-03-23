package controllers

import (
	"testing"

	"github.com/luvnyen/be-berat/pkg/models"
	"github.com/luvnyen/be-berat/pkg/utils"
	"github.com/stretchr/testify/assert"
)

func TestGetData(t *testing.T) {
	utils.ReadData(&weights)
	
	// at this time when I created this test, there is only 3 data in the list
	assert.Equal(t, len(weights), 3, "Length of data should be 3")
}

func TestCreateData(t *testing.T) {
	utils.ReadData(&weights)
	curr_weight_len := len(weights)

	weight.Date = "2020-05-02"
	weight.Max = 100
	weight.Min = 90
	if models.CheckDateExist(weight.Date) {
		models.DeleteData(weight.Date)
		t.Fatal("Date " + weight.Date + " already exist! Deleting the date...")
	}
	models.CreateData(weight)

	utils.ReadData(&weights)

	assert.Contains(t, weights, weight, "Data should be in the list")
	assert.Equal(t, len(weights), curr_weight_len + 1, "Length of data should be increased by 1")
}

func TestUpdateData(t *testing.T) {
	TestCreateData(t)

	weight.Max = 110
	weight.Min = 95
	models.UpdateData(weight.Date, weight)

	utils.ReadData(&weights)

	if assert.Contains(t, weights, weight, "Data should be in the list") {
		models.DeleteData(weight.Date)
	}
}

func TestDeleteData(t *testing.T) {
	TestCreateData(t)
	curr_weight_len := len(weights)

	models.DeleteData(weight.Date)
	utils.ReadData(&weights)

	assert.NotContains(t, weights, weight, "Data should not be in the list")
	assert.Equal(t, len(weights), curr_weight_len - 1, "Length of data should be decreased by 1")
}