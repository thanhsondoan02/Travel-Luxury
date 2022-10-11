package ai.ftech.travelluxury.ui.login

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.model.login.AccountData
import ai.ftech.travelluxury.data.repo.hotel.HotelRepositoryImpl
import ai.ftech.travelluxury.data.repo.hotel.IHotelRepository
import ai.ftech.travelluxury.data.repo.hotel.IResult
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity

class LoginPresenter : LoginContract.Presenter {

    var view: LoginActivity? = null

    private var repo: IHotelRepository? = null

    override fun checkPreferences() {
        repo = HotelRepositoryImpl().apply {
            result = object : IResult {
                override fun onRepoSuccess(data: Any) {

                    val accountData = data as AccountData
                    AccountData.INSTANCE = accountData

                    view?.onPreferencesSuccess()
                }

                override fun onRepoFail(message: String) {
                    view?.onPreferencesFail()
                }
            }
        }

        val sharedPreferences = view?.getSharedPreferences(
            "myPreferences",
            AppCompatActivity.MODE_PRIVATE
        )

        val userEmail = sharedPreferences?.getString("email", null)
        val userPassword = sharedPreferences?.getString("password", null)

        if (userEmail != null && userPassword != null) {
            repo?.login(userEmail, userPassword)
        } else {
            view?.onPreferencesFail()
        }
    }

    override fun handleLogin(email: String, password: String) {

        repo = HotelRepositoryImpl().apply {
            result = object : IResult {
                override fun onRepoSuccess(data: Any) {

                    savePreferences(email, password)

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
            else -> repo?.login(email, password)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    private fun savePreferences(email: String, password: String) {
        val sharedPreferences = view?.getSharedPreferences(
            "myPreferences",
            AppCompatActivity.MODE_PRIVATE
        )
        val editor = sharedPreferences?.edit()
        editor?.putString("email", email)
        editor?.putString("password", password)
        editor?.apply()
    }
}
