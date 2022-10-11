package ai.ftech.travelluxury.ui.setting

import ai.ftech.travelluxury.R
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val LABEL_TYPE = 0
const val LABEL_WO_TEXT_TYPE = 1
const val CONTENT_TYPE = 2
const val CONTENT_TYPE_WO_ICON = 3

data class SettingData(
    var type: Int,
    var icon: Int?,
    var text: String?,
)

class SettingAdapter : RecyclerView.Adapter<BaseVH>() {

    var view: SettingActivity? = null
    var listener: SettingActivity.IListener? = null

    private val dataList: List<SettingData>
        get() = getSettingDataList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        val view = View.inflate(parent.context, getLayout(viewType), null)
        return when (viewType) {
            LABEL_TYPE -> LabelVH(view)
            LABEL_WO_TEXT_TYPE -> LabelWithoutText(view)
            CONTENT_TYPE -> ContentVH(view)
            CONTENT_TYPE_WO_ICON -> ContentWithoutIconVH(view)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }

    private fun getLayout(viewType: Int): Int {
        return when (viewType) {
            LABEL_TYPE -> R.layout.setting_label_item
            LABEL_WO_TEXT_TYPE -> R.layout.setting_label_without_text_item
            CONTENT_TYPE -> R.layout.setting_content_item
            CONTENT_TYPE_WO_ICON -> R.layout.setting_content_no_icon_item
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    private fun getSettingDataList(): List<SettingData> {
        return listOf(
            SettingData(LABEL_TYPE, null, view!!.getString(R.string.account_security)),
            SettingData(
                CONTENT_TYPE,
                R.drawable.ic_my_account_gray,
                view!!.getString(R.string.account_information)
            ),
            SettingData(
                CONTENT_TYPE,
                R.drawable.ic_shield_gray,
                view!!.getString(R.string.password_security)
            ),
            SettingData(
                CONTENT_TYPE,
                R.drawable.ic_lock_gray,
                view!!.getString(R.string.profile_privacy)
            ),
            SettingData(LABEL_TYPE, null, view!!.getString(R.string.account_security)),
            SettingData(
                CONTENT_TYPE_WO_ICON,
                null,
                view!!.getString(R.string.country)
            ),
            SettingData(
                CONTENT_TYPE_WO_ICON,
                null,
                view!!.getString(R.string.currency)
            ),
            SettingData(
                CONTENT_TYPE_WO_ICON,
                null,
                view!!.getString(R.string.language)
            ),
            SettingData(LABEL_WO_TEXT_TYPE, null, null),
            SettingData(
                CONTENT_TYPE,
                R.drawable.ic_users_gray,
                view!!.getString(R.string.passenger_quick_pick)
            ),
            SettingData(
                CONTENT_TYPE,
                R.drawable.ic_mobile_gray,
                view!!.getString(R.string.push_notification)
            ),
            SettingData(
                CONTENT_TYPE,
                R.drawable.ic_mail_gray,
                view!!.getString(R.string.newsletter_promo_info)
            ),
            SettingData(LABEL_WO_TEXT_TYPE, null, null),
            SettingData(
                CONTENT_TYPE_WO_ICON,
                null,
                view!!.getString(R.string.app_version)
            ),
            SettingData(
                CONTENT_TYPE_WO_ICON,
                null,
                view!!.getString(R.string.term_conditions)
            ),
            SettingData(
                CONTENT_TYPE_WO_ICON,
                null,
                view!!.getString(R.string.privacy_policy)
            ),
            SettingData(
                CONTENT_TYPE_WO_ICON,
                null,
                view!!.getString(R.string.about_us)
            ),
            SettingData(LABEL_WO_TEXT_TYPE, null, null),
            SettingData(
                CONTENT_TYPE,
                R.drawable.ic_switch_gray,
                view!!.getString(R.string.log_out)
            ),
        )
    }

    inner class LabelVH(view: View) : BaseVH(view) {

        private val tvLabel = view.findViewById<TextView>(R.id.tvSettingLabel)

        override fun bind(position: Int) {
            tvLabel.text = dataList[position].text
        }

    }

    inner class LabelWithoutText(view: View) : BaseVH(view) {

        override fun bind(position: Int) {
            // do no thing
        }

    }

    inner class ContentVH(view: View) : BaseVH(view) {

        private val ivIcon: ImageView = view.findViewById(R.id.ivSettingContentIcon)
        private val tvTitle: TextView = view.findViewById(R.id.tvSettingContentText)
        private val rlContainer: RelativeLayout = view.findViewById(R.id.rlSettingContentContainer)

        override fun bind(position: Int) {
            ivIcon.setImageResource(dataList[position].icon!!)
            tvTitle.text = dataList[position].text

            if (dataList[position].icon == R.drawable.ic_switch_gray) {
                rlContainer.setOnClickListener {
                    listener?.onLogout()
                }
            }
        }

    }

    inner class ContentWithoutIconVH(view: View) : BaseVH(view) {

        private val tvTitle = view.findViewById<TextView>(R.id.tvSettingContentNoIconText)

        override fun bind(position: Int) {
            tvTitle.text = dataList[position].text
        }

    }

}
