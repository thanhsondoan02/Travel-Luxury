package ai.ftech.travelluxury.view

import ai.ftech.travelluxury.R
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

class SocialNetworkView @JvmOverloads constructor(
    ctx: Context,
    attributes: AttributeSet
) : LinearLayout(ctx, attributes) {

    init {
        LayoutInflater.from(ctx).inflate(R.layout.social_network_layout, this, true)
    }
}