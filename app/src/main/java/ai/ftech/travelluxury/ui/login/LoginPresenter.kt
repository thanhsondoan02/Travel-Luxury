package ai.ftech.travelluxury.ui.login

import android.util.Patterns

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    override fun handleLogin(email: String, password: String) {
        when {
            email.isEmpty() -> view.onLoginResult(
                LOGIN_STATE.EMPTY_EMAIL_FIELD,
                "Email is required"
            )
            !isValidEmail(email) -> view.onLoginResult(
                LOGIN_STATE.INVALID_EMAIL_FORMAT,
                "Invalid email format"
            )
            password.isEmpty() -> view.onLoginResult(
                LOGIN_STATE.EMPTY_PASSWORD_FIELD,
                "Password is required"
            )
            !isValidPassword(password) -> view.onLoginResult(
                LOGIN_STATE.INVALID_PASSWORD_FORMAT,
                "Minimum length 6 character(s)"
            )
            isTrueAccount(email, password) -> view.onLoginResult(
                LOGIN_STATE.SUCCESS,
                "Awesome! You have been logged in successfully"
            )
            else -> view.onLoginResult(
                LOGIN_STATE.WRONG_EMAIL_OR_PASSWORD,
                "Your email and password combination is incorrect. Please try again."
            )
        }
    }

    private fun isTrueAccount(username: String, password: String): Boolean {
        return username == "admin@gmail.com" && password == "12345678"
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
}
