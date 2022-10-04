package ai.ftech.travelluxury.ui.hoteldetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun bindData() {}
}
