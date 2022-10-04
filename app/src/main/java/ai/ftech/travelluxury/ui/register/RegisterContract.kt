package ai.ftech.travelluxury.ui.register

import ai.ftech.travelluxury.ui.login.LOGIN_STATE

class RegisterContract {

    interface View {
        fun onRegisterResult(state: REGISTER_STATE, message: String)
    }

    interface Presenter {
        fun handleRegister(email: String)
    }
}
