package ai.ftech.travelluxury.data

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.hotel.POLICY_TYPE

val policyMap = mapOf(
    "check_in_out" to POLICY_TYPE.CHECK_IN_OUT,
    "additional" to POLICY_TYPE.ADDITIONAL,
    "general" to POLICY_TYPE.GENERAL
)

fun isValidPolicyType(type: String): Boolean {
    return policyMap.containsKey(type)
}

fun getPolicyType(type: String): POLICY_TYPE {
    return policyMap[type]!!
}

fun getIcon(type: POLICY_TYPE): Int {
    return when (type) {
        POLICY_TYPE.CHECK_IN_OUT -> R.drawable.ic_clock
        POLICY_TYPE.ADDITIONAL -> R.drawable.ic_note
        POLICY_TYPE.GENERAL -> R.drawable.ic_note_refer
    }
}

fun getTitle(type: POLICY_TYPE): String {
    return when (type) {
        POLICY_TYPE.CHECK_IN_OUT -> "Check-in/Check-out Time"
        POLICY_TYPE.ADDITIONAL -> "Additional Policy"
        POLICY_TYPE.GENERAL -> "General Check-in Instructions"
    }
}
