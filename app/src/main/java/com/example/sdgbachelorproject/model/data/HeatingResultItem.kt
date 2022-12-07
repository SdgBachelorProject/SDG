package com.example.sdgbachelorproject.model.data

data class HeatingResultItem(
    val buildingType: Int,
    val heatingConsumption: Int,
    val id: Int,
    val numberOfRooms: Int,
    val temperatureInYourHoushold: Int,
    val user: String
)