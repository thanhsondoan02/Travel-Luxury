package ai.ftech.travelluxury.ui.login

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.model.login.AccountData
import ai.ftech.travelluxury.data.repo.hotel.HotelRepositoryImpl
import ai.ftech.travelluxury.data.repo.hotel.IHotelRepository
import ai.ftech.travelluxury.data.repo.hotel.IResult
import android.util.Patterns

class LoginPresenter : LoginContract.Presenter {

    var view: LoginActivity? = null

    private val repo: IHotelRepository by lazy {
        HotelRepositoryImpl().apply {
            result = object : IResult {
                override fun onRepoSuccess(data: Any) {

                    val accountData = data as AccountData
                    AccountData.INSTANCE = accountData

                    view?.onLoginResult(
                        LOGIN_STATE.SUCCESS,
                        view?.getString(R.string.login_success) ?: ""
                    )
                }

                override fun onRepoFail(message: String) {
                    view?.onLoginResult(
                        LOGIN_STATE.FAIL,
                        message
                    )
                }
            }
        }
    }

    override fun handleLogin(email: String, password: String) {
        when {
            email.isEmpty() -> view?.onLoginResult(
                LOGIN_STATE.EMPTY_EMAIL_FIELD,
                view?.getString(R.string.email_empty) ?: ""
            )
            !isValidEmail(email) -> view?.onLoginResult(
                LOGIN_STATE.INVALID_EMAIL_FORMAT,
                view?.getString(R.string.email_invalid) ?: ""
            )
            password.isEmpty() -> view?.onLoginResult(
                LOGIN_STATE.EMPTY_PASSWORD_FIELD,
                view?.getString(R.string.password_empty) ?: ""
            )
            !isValidPassword(password) -> view?.onLoginResult(
                LOGIN_STATE.INVALID_PASSWORD_FORMAT,
                view?.getString(R.string.password_invalid) ?: ""
            )
            else -> repo.login(email, password)
//            isTrueAccount(email, password) -> view?.onLoginResult(
//                LOGIN_STATE.SUCCESS,
//                "Awesome! You have been logged in successfully"
//            )
//            else -> view?.onLoginResult(
//                LOGIN_STATE.WRONG_EMAIL_OR_PASSWORD,
//                "Your email and password combination is incorrect. Please try again."
//            )
        }
    }

//    private fun isTrueAccount(username: String, password: String): Boolean {
//        return username == "admin@gmail.com" && password == "12345678"
//    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
}
