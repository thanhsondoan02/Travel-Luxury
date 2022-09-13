package ai.ftech.travelluxury.data

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.main.home.HomeAdapter

fun getCategoryData(): MutableList<Any> {

    val dataList = mutableListOf<Any>()

    dataList.add(HomeAdapter.BigFeaturesData())

    dataList.add(HomeAdapter.CategoryData(R.drawable.flight_icon_white, "#2dc5f7", "A diverse selection of flights", "Easily choose to fly anywhere!"))
    dataList.add(HomeAdapter.CategoryData(R.drawable.hotel_icon_white, "#225c9f", "Plenty of hotel selection", "Comfortable stay from neighborhood to cross-border"))

    return dataList
}
