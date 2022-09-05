package ai.ftech.travelluxury.login

interface LoginContract {

    interface View {
        fun onLogin(state: LOGIN_STATE, message: String)
    }

    interface Presenter {
        fun handleLogin(username: String, password: String)
    }
}