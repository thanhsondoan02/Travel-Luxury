package ai.ftech.travelluxury.data

interface HotelHandler<TYPE> {
    val map: Map<String, TYPE>

    fun isValidType(type: String): Boolean {
        return map.containsKey(type)
    }

    fun getType(type: String): TYPE {
        return map[type]!!
    }

    abstract fun initMap()
    abstract fun getIcon(type: TYPE): Int
    abstract fun getTitle(type: TYPE): String
}
