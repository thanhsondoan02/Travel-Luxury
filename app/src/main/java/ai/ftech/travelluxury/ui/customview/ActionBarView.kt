package ai.ftech.travelluxury.ui.customview

import ai.ftech.travelluxury.R
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView

class ActionBarView @JvmOverloads constructor(
    ctx: Context,
    attributes: AttributeSet
) : RelativeLayout(ctx, attributes) {


    private val title : TextView

    init {
        val view = LayoutInflater.from(ctx).inflate(R.layout.action_bar_layout, this, true)

        title = view.findViewById(R.id.tvActionBarTitle)
    }


    fun setTitle(title: String) {
        this.title.text = title
    }
}
