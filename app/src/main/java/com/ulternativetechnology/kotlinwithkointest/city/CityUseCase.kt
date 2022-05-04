package com.ulternativetechnology.kotlinwithkointest.city

class CityUseCase(private val cityRepository: CityRepository) {

    fun getCities(): List<String> {
        return cityRepository.getCities()
    }

    fun getCity(name: String): String {
        return cityRepository.getCity(name)
    }

    fun isExistsCity(name: String): Boolean {
        return cityRepository.isExistsCity(name)
    }
}