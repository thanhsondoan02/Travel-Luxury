package ai.ftech.travelluxury.ui.login

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.customview.ActionBarView
import ai.ftech.travelluxury.ui.customview.FooterView
import ai.ftech.travelluxury.ui.main.MainActivity
import ai.ftech.travelluxury.ui.register.RegisterActivity
import ai.ftech.travelluxury.ui.register.RegisterActivity.Companion.resetErrorTextOnInputTextChange
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginContract.View {

    private lateinit var abvActionBar: ActionBarView
    private lateinit var fvFooter: FooterView
    private lateinit var btnLogin: Button
    private lateinit var tvFooterRight: TextView
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var tvEmailError: TextView
    private lateinit var tvPasswordError: TextView
    private lateinit var btnGoBack: ImageButton
    private lateinit var tvActionBarTitle: TextView

    private var savedInstanceState: Bundle? = null

    private val presenter by lazy {
        LoginPresenter().apply { view = this@LoginActivity }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.checkPreferences()
    }

    override fun onBackPressed() {
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnLogin -> {
                val userEmail = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                presenter.handleLogin(userEmail, password)
            }
            R.id.tvFooterRight -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }

    override fun onPreferencesSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onPreferencesFail() {
        setContentView(R.layout.login_activity)

        initView()

        // delete go back button
        btnGoBack.visibility = View.GONE

        // set margin start action bar title
        val params = tvActionBarTitle.layoutParams as RelativeLayout.LayoutParams
        params.marginStart = (params.topMargin * 1.5).toInt()
        tvActionBarTitle.layoutParams = params

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
            LOGIN_STATE.EMPTY_EMAIL_FIELD, LOGIN_STATE.INVALID_EMAIL_FORMAT -> {
                tvEmailError.text = message
            }
            LOGIN_STATE.EMPTY_PASSWORD_FIELD, LOGIN_STATE.INVALID_PASSWORD_FORMAT -> {
                tvPasswordError.text = message
            }
            LOGIN_STATE.FAIL -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
            LOGIN_STATE.SUCCESS -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
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
        btnGoBack = findViewById(R.id.btnActionBarGoBack)
        tvActionBarTitle = findViewById(R.id.tvActionBarTitle)

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
