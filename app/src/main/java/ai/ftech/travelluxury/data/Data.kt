package ai.ftech.travelluxury.data

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.main.home.HomeAdapter
import ai.ftech.travelluxury.ui.main.home.HorizontalListAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.DecimalFormat

const val TAG = "Peswoc"

fun getCategoryData(): MutableList<Any> {

    val dataList = mutableListOf<Any>()

    dataList.add(HomeAdapter.BigFeaturesData())

    dataList.add(
        HomeAdapter.CategoryData(
            R.drawable.flight_icon_white,
            "#2dc5f7",
            "A diverse selection of flights",
            "Easily choose to fly anywhere!"
        )
    )

    dataList.add(HomeAdapter.FlightListData())

    dataList.add(
        HomeAdapter.CategoryData(
            R.drawable.hotel_icon_white,
            "#225c9f",
            "Plenty of hotel selection",
            "Comfortable stay from neighborhood to cross-border"
        )
    )

    dataList.add(HomeAdapter.FlightListData())
    dataList.add(HomeAdapter.DoubleButtonData())
    dataList.add(HomeAdapter.FlightListData())

    return dataList
}

fun getFlightList(): MutableList<Any> {

    val dataList = mutableListOf<Any>()

    dataList.add(
        HorizontalListAdapter.FlightData(
            R.drawable.tphcm,
            "Hanoi → Ho Chi Minh City",
            "12/9 - 31/12/2022",
            "VND760.840"
        )
    )

    dataList.add(
        HorizontalListAdapter.FlightData(
            R.drawable.hn,
            "Ho Chi Minh City → Hanoi",
            "12/9 - 31/12/2022",
            "VND762.920"
        )
    )

    dataList.add(
        HorizontalListAdapter.FlightData(
            R.drawable.dn,
            "Ho Chi Minh City → Hanoi",
            "12/9 - 31/12/2022",
            "VND760.840"
        )
    )

    dataList.add(
        HorizontalListAdapter.FlightData(
            R.drawable.dn,
            "Ho Chi Minh City → Hanoi",
            "12/9 - 31/12/2022",
            "VND760.840"
        )
    )

    dataList.add(
        HorizontalListAdapter.FlightData(
            R.drawable.phu_quoc,
            "Ho Chi Minh City → Hanoi",
            "12/9 - 31/12/2022",
            "VND760.840"
        )
    )

    dataList.add(
        HorizontalListAdapter.FlightData(
            R.drawable.nha_trang,
            "Ho Chi Minh City → Hanoi",
            "12/9 - 31/12/2022",
            "VND760.840"
        )
    )

    dataList.add(
        HorizontalListAdapter.FlightData(
            R.drawable.vinh,
            "Ho Chi Minh City → Hanoi",
            "12/9 - 31/12/2022",
            "VND760.840"
        )
    )

    dataList.add(
        HorizontalListAdapter.FlightData(
            R.drawable.hn,
            "Ho Chi Minh City → Hanoi",
            "12/9 - 31/12/2022",
            "VND760.840"
        )
    )

    dataList.add(
        HorizontalListAdapter.FlightData(
            R.drawable.nha_trang,
            "Ho Chi Minh City → Hanoi",
            "12/9 - 31/12/2022",
            "VND760.840"
        )
    )

    dataList.add(
        HorizontalListAdapter.FlightData(
            R.drawable.tphcm,
            "Ho Chi Minh City → Hanoi",
            "12/9 - 31/12/2022",
            "VND760.840"
        )
    )

    dataList.add(
        HorizontalListAdapter.FlightData(
            R.drawable.hn,
            "Ho Chi Minh City → Hanoi",
            "12/9 - 31/12/2022",
            "VND760.840"
        )
    )

    dataList.add(
        HorizontalListAdapter.FlightData(
            R.drawable.hn,
            "Ho Chi Minh City → Hanoi",
            "12/9 - 31/12/2022",
            "VND760.840"
        )
    )

    return dataList
}

fun getHotelPromosList(): MutableList<Any> {

    val dataList = mutableListOf<Any>()

    dataList.add(
        HorizontalListAdapter.HotelPromosData(
            R.drawable.hotel_list_rec1,
        )
    )

    dataList.add(
        HorizontalListAdapter.HotelPromosData(
            R.drawable.hotel_list_rec2,
        )
    )

    return dataList
}

fun ImageView.loadUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

fun getPriceString(price: Int?): String {
    if (price == null) return ""

    val dec = DecimalFormat("#,###")
    var tempFormat = dec.format(price)
    for (i in tempFormat.indices) {
        if (tempFormat[i] == ',') {
            tempFormat = tempFormat.substring(0, i) + '.' + tempFormat.substring(i + 1)
        }
    }
    return "VND $tempFormat"
}

fun getHotelRatingCount(rating: Int): String {
    return "($rating)"
}

fun setStar(star: Float, listStarImage: List<ImageView>) {
    for (i in 0 until star.toInt()) {
        listStarImage[i].setImageResource(R.drawable.ic_full_star)
    }

    if (star - star.toInt() > 0) {
        listStarImage[star.toInt()].setImageResource(R.drawable.ic_half_star)
    }
}
