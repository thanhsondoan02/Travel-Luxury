package ai.ftech.travelluxury.ui.login

interface LoginContract {

    interface View {
        fun onLoginResult(state: LOGIN_STATE, message: String)
    }

    interface Presenter {
        fun handleLogin(email: String, password: String)
    }
}
