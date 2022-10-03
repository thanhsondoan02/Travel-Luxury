package ai.ftech.travelluxury.data

interface IHotelHandler<TYPE> {
    val map: Map<String, TYPE>

    fun isValidType(type: String): Boolean {
        return map.containsKey(type)
    }

    fun getType(type: String): TYPE {
        return map[type]!!
    }

    fun getIcon(type: TYPE): Int
    fun getTitle(type: TYPE): String
}
