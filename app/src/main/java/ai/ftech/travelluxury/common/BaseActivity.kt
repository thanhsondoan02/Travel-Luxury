package ai.ftech.travelluxury.common

import ai.ftech.travelluxury.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity(), IBaseView {

    private var layoutView: View? = null
    private var loadingView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutView = layoutInflater.inflate(getLayoutId(), null)
        setContentView(layoutView)
    }

    @SuppressLint("InflateParams")
    override fun showLoading(bigMessage: String, smallMessage: String) {
        loadingView = layoutInflater.inflate(R.layout.loading_layout, null).apply {
            findViewById<TextView>(R.id.tvLoadingBig).text = bigMessage
            findViewById<TextView>(R.id.tvLoadingSmall).text = smallMessage
        }

        val params = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ).apply {
            addRule(RelativeLayout.CENTER_IN_PARENT)
        }

        (layoutView as ViewGroup).addView(loadingView, params)
    }

    override fun hideLoading() {
        loadingView?.visibility = View.GONE
    }

    abstract fun getLayoutId(): Int
}
