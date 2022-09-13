package ai.ftech.travelluxury.view

import ai.ftech.travelluxury.R
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView

class FooterView @JvmOverloads constructor(
    ctx: Context,
    attributes: AttributeSet
) : LinearLayout(ctx, attributes) {


    private val leftTextView : TextView
    private val rightTextView : TextView

    init {
        val view = LayoutInflater.from(ctx).inflate(R.layout.footer_layout, this, true)

        leftTextView = view.findViewById(R.id.tvFooterLeft)
        rightTextView = view.findViewById(R.id.tvFooterRight)
    }

    fun setLeftTextView(text: String) {
        leftTextView.text = text
    }

    fun setRightTextView(text: String) {
        rightTextView.text = text
    }
}
