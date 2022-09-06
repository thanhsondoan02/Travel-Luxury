package ai.ftech.travelluxury.register

import ai.ftech.travelluxury.login.LOGIN_STATE

class RegisterContract {

    interface View {
        fun onRegisterResult(state: REGISTER_STATE, message: String)
    }

    interface Presenter {
        fun handleRegister(email: String)
    }
}