package models

import (
	"errors"

	"github.com/luvnyen/be-berat/pkg/utils"
)

type Weight struct {
	Date string  `json:"date"`
	Max  int     `json:"max"`
	Min  int     `json:"min"`
}

var weight Weight
var weights []Weight

func GetData() []Weight {
	utils.ReadData(&weights)
	return weights
}

func GetDataByDate(date string) (Weight, error) {
	utils.ReadData(&weights)
	for _, w := range weights {
		if w.Date == date {
			weight = w
			return weight, nil
		}
	}
	return weight, errors.New("Not Found")
}

func CheckDateExist(date string) bool {
	utils.ReadData(&weights)
	for _, w := range weights {
		if w.Date == date {
			return true
		}
	}
	return false
}

func SortByDate(weights []Weight) {
	for i := 0; i < len(weights)-1; i++ {
		for j := i + 1; j < len(weights); j++ {
			if weights[i].Date < weights[j].Date {
				weights[i], weights[j] = weights[j], weights[i]
			}
		}
	}
}

func CreateData(weight Weight) {
	utils.ReadData(&weights)
	weights = append(weights, weight)
	SortByDate(weights)
	utils.WriteData(weights)
}

func UpdateData(date string, weight Weight) error {
	utils.ReadData(&weights)
	for i, w := range weights {
		if w.Date == date {
			weights[i] = weight
			utils.WriteData(weights)
			return nil
		}
	}
	return errors.New("Not Found")
}

func DeleteData(date string) error {
	utils.ReadData(&weights)
	for i, w := range weights {
		if w.Date == date {
			weights = append(weights[:i], weights[i+1:]...)
			utils.WriteData(weights)
			return nil
		}
	}
	return errors.New("Not Found")
}