package ai.ftech.travelluxury.data

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.main.home.HomeAdapter
import ai.ftech.travelluxury.main.home.HorizontalListAdapter
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

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
            R.drawable.vung_tau,
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

fun getHotelList(): MutableList<Any> {

    val dataList = mutableListOf<Any>()

    dataList.add(
        HorizontalListAdapter.HotelData(
            R.drawable.ha_long,
        )
    )

    dataList.add(
        HorizontalListAdapter.HotelData(
            R.drawable.vung_tau,
        )
    )

    dataList.add(
        HorizontalListAdapter.HotelData(
            R.drawable.da_nang
        )
    )

    dataList.add(
        HorizontalListAdapter.HotelData(
            R.drawable.ha_long,
        )
    )

    dataList.add(
        HorizontalListAdapter.HotelData(
            R.drawable.vung_tau,
        )
    )

    dataList.add(
        HorizontalListAdapter.HotelData(
            R.drawable.da_nang
        )
    )

    dataList.add(
        HorizontalListAdapter.HotelData(
            R.drawable.ha_long,
        )
    )

    dataList.add(
        HorizontalListAdapter.HotelData(
            R.drawable.vung_tau,
        )
    )

    dataList.add(
        HorizontalListAdapter.HotelData(
            R.drawable.da_nang
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

fun loadUrlToImageView(url: String, imageView: ImageView, context: Context) {
    Glide.with(context).load(url).into(imageView)
}
