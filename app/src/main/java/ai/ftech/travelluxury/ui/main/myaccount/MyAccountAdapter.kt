package ai.ftech.travelluxury.ui.main.myaccount

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.common.BaseAdapter
import ai.ftech.travelluxury.common.BaseVH
import ai.ftech.travelluxury.data.model.login.AccountData
import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.RelativeLayout.LayoutParams
import android.widget.TextView

class MyAccountAdapter : BaseAdapter() {

    var listener: MyAccountFragment.IListener? = null

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> 0
            1, 5, 7, 9, 12 -> 1
            else -> 2
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getViewHolder(inflateView: View, viewType: Int): BaseVH<Any> {
        return when (viewType) {
            0 -> MyAccountProfileVH(inflateView) as BaseVH<Any>
            1 -> MyAccountCategoryVH(inflateView) as BaseVH<Any>
            else -> MyAccountFeatureVH(inflateView) as BaseVH<Any>
        }
    }

    override fun initData(): MutableList<Any> {
        val newDataList = mutableListOf<Any>()

        newDataList.add(ProfileData("Jennie Kim", "nini@gmail.com"))

        newDataList.add(CategoryData("My Rewards", -1))

        newDataList.add(
            FeatureData(
                "My Coupons",
                "View coupons that you can use now.",
                R.drawable.ic_my_coupon,
                0
            )
        )
        newDataList.add(
            FeatureData(
                "Special Rewards",
                "See available rewards that are just for you!",
                R.drawable.ic_special_rewards,
                2
            )
        )
        newDataList.add(
            FeatureData(
                "0 Points",
                "Trade points for coupons and learn how to earn more!",
                R.drawable.ic_points,
                1
            )
        )

        newDataList.add(CategoryData("Member Features", -1))

        newDataList.add(
            FeatureData(
                "My Refunds",
                "Mange your refund in one place",
                R.drawable.ic_my_refunds,
                3
            )
        )

        newDataList.add(CategoryData("", 40))

        newDataList.add(
            FeatureData(
                "My Cards",
                "Pay your booking in one single tap",
                R.drawable.ic_my_cards,
                3
            )
        )

        newDataList.add(CategoryData("", 40))

        newDataList.add(
            FeatureData(
                "Settings",
                "View and set your account preferences",
                R.drawable.ic_settings,
                0
            )
        )
        newDataList.add(
            FeatureData(
                "Help Center",
                "Find the best answer for your question",
                R.drawable.ic_help_center,
                1
            )
        )

        newDataList.add(CategoryData("", 100))

        return newDataList
    }

    override fun getLayoutResource(viewType: Int): Int {
        return when (viewType) {
            0 -> R.layout.my_account_profile_item
            1 -> R.layout.my_account_category_item
            else -> R.layout.my_account_feature_item
        }
    }

    class MyAccountProfileVH(itemView: View) : BaseVH<ProfileData>(itemView) {

        private val tvFullName = itemView.findViewById<TextView>(R.id.tvMyAccountTitleName)
        private val tvEmail = itemView.findViewById<TextView>(R.id.tvMyAccountTitleEmail)

        override fun onBind(data: ProfileData) {
            tvFullName.text = AccountData.INSTANCE?.fullName
            tvEmail.text = AccountData.INSTANCE?.email
        }

    }

    class MyAccountCategoryVH(itemView: View) : BaseVH<CategoryData>(itemView) {

        override fun onBind(data: CategoryData) {
            itemView.findViewById<TextView>(R.id.tvMyAccountCategoryTitle).text = data.title

            // Resize height of category item if height != -1
            if (data.height != -1) {
                val container =
                    itemView.findViewById<RelativeLayout>(R.id.rlMyAccountCategoryContainer)
                container.layoutParams =
                    LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        data.height
                    )
            }
        }

    }

    inner class MyAccountFeatureVH(itemView: View) : BaseVH<FeatureData>(itemView) {

        private val tvTitle: TextView = itemView.findViewById(R.id.tvMyAccountFeatureTitle)
        private val tvDes: TextView = itemView.findViewById(R.id.tvMyAccountFeatureDescription)
        private val ivIcon: ImageView = itemView.findViewById(R.id.ivMyAccountFeatureIcon)
        private val rlContainer: RelativeLayout =
            itemView.findViewById(R.id.rlMyAccountFeatureItemContainer)

        @SuppressLint("UseCompatLoadingForDrawables")
        override fun onBind(data: FeatureData) {
            tvTitle.text = data.title
            tvDes.text = data.description
            ivIcon.setImageResource(data.iconSrc)

            // Choose border type of feature item
            val container = itemView.findViewById<View>(R.id.rlMyAccountFeatureItemContainer)
            when (data.type) {
                0 -> container.background =
                    itemView.context.getDrawable(R.drawable.my_account_feature_item_top_border_shape)
                1 -> container.background =
                    itemView.context.getDrawable(R.drawable.my_account_feature_item_bottom_boder_shape)
                3 -> container.background =
                    itemView.context.getDrawable(R.drawable.my_account_feature_item_4_border_shape)
            }

            if (data.iconSrc == R.drawable.ic_settings) {
                rlContainer.setOnClickListener {
                    listener?.onSettingClick()
                }
            }
        }

    }

    data class ProfileData(val name: String, val email: String)
    data class CategoryData(val title: String, val height: Int)

    /**
     * 0: top border
     * 1: bottom border
     * 2: no border
     * 3: four corner border
     */
    data class FeatureData(
        val title: String,
        val description: String,
        val iconSrc: Int,
        val type: Int
    )
}
