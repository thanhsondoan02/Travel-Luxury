package ai.ftech.travelluxury.main

import ai.ftech.travelluxury.BigFeaturesViewHolder
import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.SmallFeaturesViewHolder
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val BIG_FEATURES = 0
        private const val SMALL_FEATURES = 1
    }

    class ViewHolder0(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> BIG_FEATURES
            else -> SMALL_FEATURES
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BIG_FEATURES -> BigFeaturesViewHolder(
                View.inflate(
                    parent.context,
                    R.layout.big_features,
                    null
                )
            )
            else -> SmallFeaturesViewHolder(
                View.inflate(
                    parent.context,
                    R.layout.small_features,
                    null
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
}

