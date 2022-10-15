package ai.ftech.travelluxury.ui.setting

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseVH(view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(position: Int) {}
}
