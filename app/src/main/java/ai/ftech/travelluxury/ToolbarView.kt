package ai.ftech.travelluxury

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout

class ToolbarView constructor(
    ctx: Context,
    attributes: AttributeSet?
) : RelativeLayout(ctx, attributes) {


    init {
//        setBackgroundColor(Color.YELLOW)
        LayoutInflater.from(ctx).inflate(R.layout.login_register_toolbar_layout, this, false)
    }


}