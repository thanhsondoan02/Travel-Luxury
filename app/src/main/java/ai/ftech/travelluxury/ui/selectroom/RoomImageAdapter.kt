package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.loadUrl
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class RoomImageAdapter(private val imageList: List<String>) : PagerAdapter() {

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflateView = View.inflate(container.context, R.layout.room_image_item, null)

        val ivRoomImage = inflateView.findViewById<ImageView>(R.id.ivRoomImage)
        ivRoomImage.loadUrl(imageList[position])

        container.addView(inflateView)

        return inflateView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
