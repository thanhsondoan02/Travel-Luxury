package ai.ftech.travelluxury.register

import ai.ftech.travelluxury.view.ActionBarView
import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.login.LoginActivity
import ai.ftech.travelluxury.view.FooterView
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
        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            errorText.text = ""
        }
    })
}

class RegisterActivity : AppCompatActivity(), RegisterContract.View, View.OnClickListener {

    private lateinit var buttonRegister: Button
    private lateinit var footerRightTextView: TextView
    private lateinit var emailEditText: EditText
    private lateinit var emailErrorTextView: TextView
    private lateinit var actionBar: ActionBarView
    private lateinit var footer: FooterView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        initView()

        buttonRegister.setOnClickListener(this)
        footerRightTextView.setOnClickListener(this)

        // email change -> remove error text
        resetErrorTextOnInputTextChange(
            emailEditText,
            emailErrorTextView
        )
    }

    private fun initView() {
        buttonRegister = findViewById(R.id.button_register)
        footerRightTextView = findViewById(R.id.footer_right_text_view)
        emailEditText = findViewById(R.id.email_edit_text)
        emailErrorTextView = findViewById(R.id.email_error_text_view)
        actionBar = findViewById(R.id.action_bar)
        footer = findViewById(R.id.footer)

        // init text
        actionBar.setTitle("Become a Travel Luxury Member")
        footer.setLeftTextView("Already have a Travel Luxury account?")
        footer.setRightTextView("Log in now")
        emailErrorTextView.text = ""
    }

    override fun onRegisterResult(state: REGISTER_STATE, message: String) {
        when (state) {
            REGISTER_STATE.EMPTY_EMAIL_FIELD -> {
                emailErrorTextView.text = message
            }
            REGISTER_STATE.INVALID_EMAIL_FORMAT -> {
                emailErrorTextView.text = message
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
                val email = emailEditText.text.toString()
                val presenter = RegisterPresenter(this)
                presenter.handleRegister(email)
            }
        }
    }
}
