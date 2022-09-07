package ai.ftech.travelluxury.login

import ai.ftech.travelluxury.view.ActionBarView
import ai.ftech.travelluxury.main.MainActivity
import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.register.RegisterActivity
import ai.ftech.travelluxury.register.resetErrorTextOnInputTextChange
import ai.ftech.travelluxury.view.FooterView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginContract.View {

    private val presenter = LoginPresenter(this)
    private lateinit var actionBar: ActionBarView
    private lateinit var footer: FooterView
    private lateinit var buttonLogin: Button
    private lateinit var footerRightTextView: TextView
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailErrorTextView: TextView
    private lateinit var passwordErrorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        initView()

        buttonLogin.setOnClickListener(this)
        footerRightTextView.setOnClickListener(this)

        // email change -> remove error text
        resetErrorTextOnInputTextChange(
            emailEditText,
            emailErrorTextView
        )

        // password change -> remove error text
        resetErrorTextOnInputTextChange(
            passwordEditText,
            passwordErrorTextView
        )
    }


    private fun initView() {
        actionBar = findViewById(R.id.action_bar)
        footer = findViewById(R.id.footer)
        buttonLogin = findViewById(R.id.button_login)
        footerRightTextView = findViewById(R.id.footer_right_text_view)
        emailEditText = findViewById(R.id.email_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)
        emailErrorTextView = findViewById(R.id.email_error_text_view)
        passwordErrorTextView = findViewById(R.id.password_error_text_view)

        // init text
        actionBar.setTitle("Login")
        footer.setLeftTextView("Don't have a Travel Luxury account?")
        footer.setRightTextView("Register now")
        emailErrorTextView.text = ""
        passwordErrorTextView.text = ""
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_login -> {
                val userName = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                presenter.handleLogin(userName, password)
            }
            R.id.footer_right_text_view -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }

    override fun onLoginResult(state: LOGIN_STATE, message: String) {
        when (state) {
            LOGIN_STATE.EMPTY_EMAIL_FIELD -> {
                emailErrorTextView.text = message
            }
            LOGIN_STATE.INVALID_EMAIL_FORMAT -> {
                emailErrorTextView.text = message
            }
            LOGIN_STATE.EMPTY_PASSWORD_FIELD -> {
                passwordErrorTextView.text = message
            }
            LOGIN_STATE.INVALID_PASSWORD_FORMAT -> {
                passwordErrorTextView.text = message
            }
            LOGIN_STATE.WRONG_EMAIL_OR_PASSWORD -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
            LOGIN_STATE.SUCCESS -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

}
