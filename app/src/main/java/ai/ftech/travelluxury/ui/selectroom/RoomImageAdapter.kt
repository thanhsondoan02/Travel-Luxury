package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.loadUrl
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class RoomImageAdapter : PagerAdapter() {

    var imageList: List<String> = listOf()
    var scaleType: ImageView.ScaleType = ImageView.ScaleType.CENTER_CROP

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflateView = View.inflate(container.context, R.layout.room_image_item, null)

        val ivRoomImage = inflateView.findViewById<ImageView>(R.id.ivRoomImage)
        ivRoomImage.scaleType = scaleType
        ivRoomImage.loadUrl(imageList[position])

        container.addView(inflateView)

        return inflateView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
