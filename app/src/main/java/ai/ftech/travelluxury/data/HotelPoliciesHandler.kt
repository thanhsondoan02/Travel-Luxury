package ai.ftech.travelluxury.data

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.hoteldetail.type.POLICY_TYPE

class HotelPoliciesHandler : IHotelHandler<POLICY_TYPE> {
    override val map: Map<String, POLICY_TYPE>
        get() = mapOf(
            "check_in_out" to POLICY_TYPE.CHECK_IN_OUT,
            "additional" to POLICY_TYPE.ADDITIONAL,
            "general" to POLICY_TYPE.GENERAL
        )

    override fun getIcon(type: POLICY_TYPE): Int {
        return when (type) {
            POLICY_TYPE.CHECK_IN_OUT -> R.drawable.ic_clock
            POLICY_TYPE.ADDITIONAL -> R.drawable.ic_note
            POLICY_TYPE.GENERAL -> R.drawable.ic_note_refer
        }
    }

    override fun getTitle(type: POLICY_TYPE): String {
        return when (type) {
            POLICY_TYPE.CHECK_IN_OUT -> "Check-in/Check-out Time"
            POLICY_TYPE.ADDITIONAL -> "Additional Policy"
            POLICY_TYPE.GENERAL -> "General Check-in Instructions"
        }
    }
}
