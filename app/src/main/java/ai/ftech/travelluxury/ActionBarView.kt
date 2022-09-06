package ai.ftech.travelluxury

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout

class ActionBarView @JvmOverloads constructor(
    ctx: Context,
    attributes: AttributeSet
) : LinearLayout(ctx, attributes) {


    private var title : TextView

    init {
//        setBackgroundColor(Color.YELLOW)
        val view = LayoutInflater.from(ctx).inflate(R.layout.login_register_toolbar_layout, this, true)


        title = view.findViewById(R.id.tool_bar_title)
    }


    fun setTitle(title: String) {
        this.title.text = title
    }
}
