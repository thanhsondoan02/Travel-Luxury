package ai.ftech.travelluxury.login

import ai.ftech.travelluxury.view.ActionBarView
import ai.ftech.travelluxury.main.MainActivity
import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.register.RegisterActivity
import ai.ftech.travelluxury.register.RegisterActivity.Companion.resetErrorTextOnInputTextChange
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

    private lateinit var abvActionBar: ActionBarView
    private lateinit var fvFooter: FooterView
    private lateinit var btnLogin: Button
    private lateinit var tvFooterRight: TextView
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var tvEmailError: TextView
    private lateinit var tvPasswordError: TextView

    private val presenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        initView()
        setOnClickListener()

        // email change -> remove error text
        resetErrorTextOnInputTextChange(
            edtEmail,
            tvEmailError
        )

        // password change -> remove error text
        resetErrorTextOnInputTextChange(
            edtPassword,
            tvPasswordError
        )
    }

    override fun onLoginResult(state: LOGIN_STATE, message: String) {
        when (state) {
            LOGIN_STATE.EMPTY_EMAIL_FIELD -> {
                tvEmailError.text = message
            }
            LOGIN_STATE.INVALID_EMAIL_FORMAT -> {
                tvEmailError.text = message
            }
            LOGIN_STATE.EMPTY_PASSWORD_FIELD -> {
                tvPasswordError.text = message
            }
            LOGIN_STATE.INVALID_PASSWORD_FORMAT -> {
                tvPasswordError.text = message
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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnLogin -> {
                val userName = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                presenter.handleLogin(userName, password)
            }
            R.id.tvFooterRight -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }

    private fun initView() {
        abvActionBar = findViewById(R.id.abvLoginActionBar)
        fvFooter = findViewById(R.id.fvLoginFooter)
        btnLogin = findViewById(R.id.btnLogin)
        tvFooterRight = findViewById(R.id.tvFooterRight)
        edtEmail = findViewById(R.id.edtLoginEmail)
        edtPassword = findViewById(R.id.edtLoginPassword)
        tvEmailError = findViewById(R.id.tvLoginEmailError)
        tvPasswordError = findViewById(R.id.tvLoginPasswordError)

        // init text
        abvActionBar.setTitle(getString(R.string.login_activity_action_bar_title))
        fvFooter.setLeftTextView(getString(R.string.login_activity_footer_left_text))
        fvFooter.setRightTextView(getString(R.string.login_activity_footer_right_text))
        tvEmailError.text = ""
        tvPasswordError.text = ""
    }

    private fun setOnClickListener() {
        btnLogin.setOnClickListener(this)
        tvFooterRight.setOnClickListener(this)
    }

}
