package ai.ftech.travelluxury.login

import android.util.Patterns

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    override fun handleLogin(email: String, password: String) {
        if (email.isEmpty()) {
            view.onLoginResult(LOGIN_STATE.EMPTY_EMAIL_FIELD, "Email is required")
        } else if (!isValidEmail(email)) {
            view.onLoginResult(LOGIN_STATE.INVALID_EMAIL_FORMAT, "Invalid email format")
        } else if (password.isEmpty()) {
            view.onLoginResult(LOGIN_STATE.EMPTY_PASSWORD_FIELD, "Password is required")
        } else if (!isValidPassword(password)) {
            view.onLoginResult(LOGIN_STATE.INVALID_PASSWORD_FORMAT, "Invalid password format")
        } else if (isTrueAccount(email, password)) {
            view.onLoginResult(LOGIN_STATE.SUCCESS, "Awesome! You have been logged in successfully")
        } else {
            view.onLoginResult(LOGIN_STATE.WRONG_EMAIL_OR_PASSWORD, "Your email and password combination is incorrect. Please try again.")
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