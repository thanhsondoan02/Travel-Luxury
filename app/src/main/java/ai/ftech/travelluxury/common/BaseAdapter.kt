package ai.ftech.travelluxury.common

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter : RecyclerView.Adapter<BaseVH<Any>>(){

    protected val dataList by lazy {
        initData()
    }

    abstract override fun getItemViewType(position: Int) : Int

    abstract fun getLayoutResource(viewType: Int) : Int

    abstract fun initData() : MutableList<Any>

    /**
     * choose type of view holder and return new instance
     */
    abstract fun getViewHolder(inflateView: View, viewType: Int) : BaseVH<Any>

    override fun onBindViewHolder(holder: BaseVH<Any>, position: Int) {
        if (dataList.isEmpty()) initData()
        holder.onBind(dataList[position])
    }

    override fun getItemCount(): Int {
        if (dataList.isEmpty()) initData()
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH<Any> {
        val inflateView = View.inflate(parent.context, getLayoutResource(viewType), null)

        return getViewHolder(inflateView, viewType)
    }

}