package ai.ftech.travelluxury.ui.register

import ai.ftech.travelluxury.ui.customview.ActionBarView
import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.login.LoginActivity
import ai.ftech.travelluxury.ui.customview.FooterView
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

class RegisterActivity : AppCompatActivity(), RegisterContract.View, View.OnClickListener {

    companion object {
        fun resetErrorTextOnInputTextChange(inputText: EditText, errorText: TextView) {
            inputText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    errorText.text = ""
                }
            })
        }
    }

    private lateinit var btnRegister: Button
    private lateinit var edtEmail: EditText
    private lateinit var tvEmailError: TextView
    private lateinit var abvActionBar: ActionBarView
    private lateinit var fvFooter: FooterView
    private lateinit var tvFooterRight: TextView

    private val presenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        initView()
        setOnClickListener()

        // email change -> remove error text
        resetErrorTextOnInputTextChange(
            edtEmail,
            tvEmailError
        )
    }

    override fun onRegisterResult(state: REGISTER_STATE, message: String) {
        when (state) {
            REGISTER_STATE.EMPTY_EMAIL_FIELD -> {
                tvEmailError.text = message
            }
            REGISTER_STATE.INVALID_EMAIL_FORMAT -> {
                tvEmailError.text = message
            }
            REGISTER_STATE.CONFIRM_EMAIL -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tvFooterRight -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.btnRegister -> {
                val email = edtEmail.text.toString()

                presenter.handleRegister(email)
            }
        }
    }

    private fun initView() {
        btnRegister = findViewById(R.id.btnRegister)
        edtEmail = findViewById(R.id.edtRegisterEmail)
        tvEmailError = findViewById(R.id.tvRegisterEmailError)
        abvActionBar = findViewById(R.id.abvRegisterActionBar)
        fvFooter = findViewById(R.id.fvRegisterFooter)
        tvFooterRight = findViewById(R.id.tvFooterRight)

        // init text
        abvActionBar.setTitle(getString(R.string.register_activity_action_bar_title))
        fvFooter.setLeftTextView(getString(R.string.register_activity_footer_left_text))
        fvFooter.setRightTextView(getString(R.string.register_activity_footer_right_text))
        tvEmailError.text = ""
    }

    private fun setOnClickListener() {
        btnRegister.setOnClickListener(this)
        tvFooterRight.setOnClickListener(this)
    }
}
