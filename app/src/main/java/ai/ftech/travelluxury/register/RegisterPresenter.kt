package ai.ftech.travelluxury.register

import ai.ftech.travelluxury.login.LOGIN_STATE

class RegisterPresenter(private val view: RegisterContract.View) : RegisterContract.Presenter {

    override fun handleRegister(email: String) {
        if (email.isEmpty()) {
            view.onRegisterResult(REGISTER_STATE.EMPTY_EMAIL_FIELD, "Email is required")
        } else if (!isValidEmail(email)) {
            view.onRegisterResult(REGISTER_STATE.INVALID_EMAIL_FORMAT, "Invalid email format")
        } else {
            view.onRegisterResult(REGISTER_STATE.CONFIRM_EMAIL, "Please confirm email!")
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}
