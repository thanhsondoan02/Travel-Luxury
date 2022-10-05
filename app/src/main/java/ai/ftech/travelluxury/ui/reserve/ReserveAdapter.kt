package ai.ftech.travelluxury.ui.reserve

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.getPriceString
import ai.ftech.travelluxury.data.model.reserve.ReserveModel
import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ReserveAdapter : RecyclerView.Adapter<ReserveVH>() {

    interface IListener {
        fun onContactClick()
        fun onContinueClick()
    }

    var listener: IListener? = null

    private val presenter by lazy {
        ReservePresenter()
    }

    /**
     * 0: Summary
     * 1: AccountInfo
     * 2: Contact
     * 3: Request
     * 4: Price
     * 5: PriceExtend
     * 60: Label Contact
     * 61: Label Request
     * 62: Label Price
     */
    private val dataList = mutableListOf(0, 1, 60, 2, 61, 3, 62, 4, 7)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReserveVH {
        val resource = when (viewType) {
            0 -> R.layout.reserve_summary_item
            1 -> R.layout.reserve_account_info_item
            2 -> R.layout.reserve_contact_item
            3 -> R.layout.reserve_request_item
            4 -> R.layout.reserve_price_item
            5 -> R.layout.reserve_price_extend_item
            60, 61, 62 -> R.layout.reserve_label_item
            7 -> R.layout.reserve_button_item
            else -> throw IllegalArgumentException("Invalid view type")
        }
        val inflateView = View.inflate(parent.context, resource, null)
        return when (viewType) {
            0 -> SummaryVH(inflateView)
            1 -> AccountInfoVH(inflateView)
            2 -> ContactVH(inflateView)
            3 -> RequestVH(inflateView)
            4 -> PriceVH(inflateView)
            5 -> PriceExtendVH(inflateView)
            60, 61, 62 -> LabelVH(inflateView)
            7 -> ButtonVH(inflateView)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: ReserveVH, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position]
    }

    inner class SummaryVH(itemView: View) : ReserveVH(itemView) {
        override fun bind(position: Int) {
            super.bind(position)
        }
    }

    inner class AccountInfoVH(itemView: View) : ReserveVH(itemView) {
        override fun bind(position: Int) {
            super.bind(position)
        }
    }

    inner class ContactVH(itemView: View) : ReserveVH(itemView) {

        private val rdGrpBookType: RadioGroup =
            itemView.findViewById(R.id.rdGrpReserveContactBookType)
        private val rlTitle: RelativeLayout = itemView.findViewById(R.id.rlReserveContactTitle)

        init {
            rdGrpBookType.check(R.id.rdBtnReserveContactBookMyself)
            rlTitle.setOnClickListener {
                listener?.onContactClick()
            }
        }

        override fun bind(position: Int) {
            super.bind(position)
        }
    }

    inner class RequestVH(itemView: View) : ReserveVH(itemView) {
        override fun bind(position: Int) {
            super.bind(position)
        }
    }

    inner class LabelVH(itemView: View) : ReserveVH(itemView) {

        private val tvLabel = itemView.findViewById<TextView>(R.id.tvReserveLabel)

        override fun bind(position: Int) {
            when (getItemViewType(position)) {
                60 -> {
                    tvLabel.text = itemView.context.getString(R.string.contact_details)
                }
                61 -> {
                    tvLabel.text = itemView.context.getString(R.string.special_request)
                }
                62 -> {
                    tvLabel.text = itemView.context.getString(R.string.price_details)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    inner class PriceVH(itemView: View) : ReserveVH(itemView) {

        private val rlTotalPrice = itemView.findViewById<RelativeLayout>(R.id.rlReservePriceTotal)
        private val ivChevron = itemView.findViewById<ImageView>(R.id.rlReservePriceTotalIcon)

        init {
            rlTotalPrice.setOnClickListener {
                presenter.onTotalPriceClick()

                if (ReserveModel.INSTANCE.totalIsOpen) {
                    ivChevron.animate().rotation(-180f).start()
                    val positionInsert = dataList.size - 1
                    dataList.add(positionInsert, 5)
                    notifyItemInserted(positionInsert)
                } else {
                    ivChevron.animate().rotation(0f).start()
                    val positionRemove = dataList.indexOf(5)
                    dataList.remove(5)
                    notifyItemRemoved(positionRemove)
                }
            }
        }

        override fun bind(position: Int) {

        }
    }

    inner class PriceExtendVH(itemView: View) : ReserveVH(itemView) {

        private val tvRoomName = itemView.findViewById<TextView>(R.id.tvReservePriceRoomName)
        private val tvRoomPrice = itemView.findViewById<TextView>(R.id.tvReservePriceRoomPrice)
        private val tvTaxName = itemView.findViewById<TextView>(R.id.tvReservePriceTaxName)
        private val tvTaxPrice = itemView.findViewById<TextView>(R.id.tvReservePriceTaxPrice)

        override fun bind(position: Int) {
            tvTaxName.text = ReserveModel.INSTANCE.taxName
            tvTaxPrice.text = getPriceString(ReserveModel.INSTANCE.taxPrice)
            tvRoomName.text = ReserveModel.INSTANCE.roomName
            tvRoomPrice.text = getPriceString(ReserveModel.INSTANCE.roomPrice)
        }
    }

    inner class ButtonVH(itemView: View) : ReserveVH(itemView) {

        private val btnContinue: Button = itemView.findViewById(R.id.btnReserveContinue)

        init {
            btnContinue.setOnClickListener {
                listener?.onContinueClick()
            }
        }
    }
}
