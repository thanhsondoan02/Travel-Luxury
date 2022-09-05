package ai.ftech.travelluxury.login

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {


    override fun handleLogin(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            view.onLogin(LOGIN_STATE.EMPTY_FIELD, "Username and password can not be empty")
        } else if (username == "admin" && password == "admin") {
            view.onLogin(LOGIN_STATE.SUCCESS, "Login success")
        } else {
            view.onLogin(LOGIN_STATE.FAILURE, "Login failure")
        }
    }


}