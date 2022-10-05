package ai.ftech.travelluxury.ui.hoteldetail.allphotos

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.loadUrl
import ai.ftech.travelluxury.data.model.hoteldetail.HotelDetailModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AllPhotosAdapter : RecyclerView.Adapter<AllPhotosAdapter.PhotoVH>() {

    private val imageList = HotelDetailModel.INSTANCE.imageList ?: listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return PhotoVH(view)
    }

    override fun onBindViewHolder(holder: PhotoVH, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class PhotoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivPhoto = itemView.findViewById<ImageView>(R.id.ivPhoto)

        fun bind(imageUrl: String) {
            ivPhoto.loadUrl(imageUrl)
        }
    }
}
