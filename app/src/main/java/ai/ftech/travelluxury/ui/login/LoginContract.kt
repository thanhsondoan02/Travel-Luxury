package ai.ftech.travelluxury.ui.login

interface LoginContract {

    interface View {
        fun onPreferencesSuccess()
        fun onPreferencesFail()
        fun onLoginResult(state: LOGIN_STATE, message: String)
    }

    interface Presenter {
        fun checkPreferences()
        fun handleLogin(email: String, password: String)
    }
}
