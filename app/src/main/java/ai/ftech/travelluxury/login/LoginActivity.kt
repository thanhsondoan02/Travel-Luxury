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
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginContract.View {

    private val presenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        setUpTextInLayout()

        findViewById<Button>(R.id.button_login).setOnClickListener(this)
        findViewById<TextView>(R.id.footer_right_text_view).setOnClickListener(this)

        // email change -> remove error text
        resetErrorTextOnInputTextChange(
            findViewById(R.id.email_edit_text),
            findViewById(R.id.email_error_text_view)
        )

        // password change -> remove error text
        resetErrorTextOnInputTextChange(
            findViewById(R.id.password_edit_text),
            findViewById(R.id.password_error_text_view)
        )
    }

    private fun setUpTextInLayout() {
        // action bar
        findViewById<ActionBarView>(R.id.action_bar).setTitle("Login")

        // footer
        findViewById<FooterView>(R.id.footer).setLeftTextView("Don't have a Travel Luxury account?")
        findViewById<FooterView>(R.id.footer).setRightTextView("Register now")

        // error text
        findViewById<TextView>(R.id.email_error_text_view).text = ""
        findViewById<TextView>(R.id.password_error_text_view).text = ""
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_login -> {
                val userName = findViewById<TextView>(R.id.email_edit_text).text.toString()
                val password = findViewById<TextView>(R.id.password_edit_text).text.toString()
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
                findViewById<TextView>(R.id.email_error_text_view).text = message
            }
            LOGIN_STATE.INVALID_EMAIL_FORMAT -> {
                findViewById<TextView>(R.id.email_error_text_view).text = message
            }
            LOGIN_STATE.EMPTY_PASSWORD_FIELD -> {
                findViewById<TextView>(R.id.password_error_text_view).text = message
            }
            LOGIN_STATE.INVALID_PASSWORD_FORMAT -> {
                findViewById<TextView>(R.id.password_error_text_view).text = message
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
