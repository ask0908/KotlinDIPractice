package com.ulternativetechnology.kotlinwithkointest.util

import com.ulternativetechnology.kotlinwithkointest.util.StringUtils.getString
import org.json.JSONArray
import org.json.JSONObject

object JSONUtil {
    /**
     * JSONObject를 HashMap<String, String>으로 바꿔 리턴하는 메서드
     */
    fun jsonObjectToHashMap(obj: JSONObject): HashMap<String, String> {
        val map = HashMap<String, String>()
        if (obj.length() > 0) {
            val keys: JSONArray = obj.names()
            for (i in 0 until keys.length()) {
                map[keys.optString(i)] = getString(obj.optString(keys.optString(i)))
            }
        }

        return map
    }

    /**
     * JSONArray를 ArrayList<HashMap<String, String>>로 바꿔 리턴하는 메서드
     */
    fun jsonArrayToArrayList(jsonArray: JSONArray): ArrayList<HashMap<String, String>> {
        val arrayList = ArrayList<HashMap<String, String>>()
        for (i in 0 until jsonArray.length())
            arrayList.add(jsonObjectToHashMap(jsonArray.optJSONObject(i)))
        return arrayList
    }

    /**
     * ArrayList<HashMap<String, String>> 안의 JSONArray를 파싱해서 HashMap에 넣어 리턴하는 메서드
     */
    fun parseJSONArrayToHashMap(list: ArrayList<HashMap<String, String>>, key: String): HashMap<String, String> {
        val jsonArray = JSONArray(list)
        val list: ArrayList<HashMap<String, String>> = jsonArrayToArrayList(jsonArray)
        val map = HashMap<String, String>()
        for ((index, i) in (0 until list.size).withIndex()) {
            map[(index).toString()] = list[i][key]!!
        }

        return map
    }

    /**
     * HashMap의 아이템 중 key만 꺼내 List로 바꿔 리턴하는 메서드
     * parseJSONArrayToHashMap()의 결과를 넣어서 사용한다
     */
    fun extractKeys(map: HashMap<String, String>): List<String> = ArrayList(map.keys)

    /**
     * HashMap의 아이템 중 value만 꺼내 List로 바꿔 리턴하는 메서드
     * parseJSONArrayToHashMap()의 결과를 넣어서 사용한다
     */
    fun extractValues(map: HashMap<String, String>): List<String> = ArrayList(map.values)

    fun parserForJSON(obj: Any): Any {
        return when (obj) {
            is JSONArray -> jsonArrayToArrayList(obj)
            is JSONObject -> jsonObjectToHashMap(obj)
            else -> "데이터가 없어요"
        }
    }
}