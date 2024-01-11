package com.yavin.beeracademy.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ArrayListConverter {

    @TypeConverter
    fun arrayListToString(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun stringToArrayList(value: String): List<String> {
        return try {
            Gson().fromJson<List<String>>(value) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    private inline fun <reified T> Gson.fromJson(json: String): T =
        fromJson(json, object : TypeToken<T>() {}.type)
}