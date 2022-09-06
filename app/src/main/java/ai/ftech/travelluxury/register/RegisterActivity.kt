package ai.ftech.travelluxury.register

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.login.LoginActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

fun resetErrorTextOnInputTextChange(inputText: EditText, errorText: TextView) {
    inputText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            errorText.text = ""
        }
    })
}

class RegisterActivity : AppCompatActivity(), RegisterContract.View, View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        setUpTextInLayout()

        findViewById<Button>(R.id.button_register).setOnClickListener(this)
        findViewById<TextView>(R.id.footer_right_text_view).setOnClickListener(this)

        // email change -> remove error text
        resetErrorTextOnInputTextChange(findViewById(R.id.email_edit_text), findViewById(R.id.email_error_text_view))
    }

//    private fun resetErrorTextOnEmailChange() {
//        findViewById<TextView>(R.id.edit_text_email).addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable) {
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                findViewById<TextView>(R.id.email_error_text_view).text = ""
//            }
//        })
//    }

    private fun setUpTextInLayout() {
//        findViewById<TextView>(R.id.tool_bar_title).text = "Become a Travel Luxury Member"
        findViewById<TextView>(R.id.footer_left_text_view).text = "Already have a Travel Luxury account?"
        findViewById<TextView>(R.id.footer_right_text_view).text = "Log in now"
        findViewById<TextView>(R.id.email_error_text_view).text = ""
    }

    override fun onRegisterResult(state: REGISTER_STATE, message: String) {
        when (state) {
            REGISTER_STATE.EMPTY_EMAIL_FIELD -> {
                findViewById<TextView>(R.id.email_error_text_view).text = message
            }
            REGISTER_STATE.INVALID_EMAIL_FORMAT -> {
                findViewById<TextView>(R.id.email_error_text_view).text = message
            }
            REGISTER_STATE.CONFIRM_EMAIL -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.footer_right_text_view -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.button_register -> {
                val email = findViewById<TextView>(R.id.email_edit_text).text.toString()
                val presenter = RegisterPresenter(this)
                presenter.handleRegister(email)
            }
        }
    }
}
