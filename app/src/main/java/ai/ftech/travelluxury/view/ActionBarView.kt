package ai.ftech.travelluxury.view

import ai.ftech.travelluxury.R
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

class ActionBarView @JvmOverloads constructor(
    ctx: Context,
    attributes: AttributeSet
) : RelativeLayout(ctx, attributes) {


    private val title : TextView

    init {
        val view = LayoutInflater.from(ctx).inflate(R.layout.action_bar_layout, this, true)

        title = view.findViewById(R.id.action_bar_title)
    }


    fun setTitle(title: String) {
        this.title.text = title
    }
}
