package ai.ftech.travelluxury.login

import ai.ftech.travelluxury.main.MainActivity
import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.register.RegisterActivity
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
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.button_login).setOnClickListener(this)
        findViewById<TextView>(R.id.text_view_button_in_footer).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_login -> {
                login()
            }
            R.id.text_view_button_in_footer -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }

    override fun onLogin(state: LOGIN_STATE, message: String) {
        when (state) {
            LOGIN_STATE.SUCCESS -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            LOGIN_STATE.EMPTY_FIELD -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
            LOGIN_STATE.FAILURE -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login() {
        val userName = findViewById<TextView>(R.id.edit_text_email).text.toString()
        val password = findViewById<TextView>(R.id.edit_text_password).text.toString()
        presenter.handleLogin(userName, password)
    }
}