package ai.ftech.travelluxury.ui.selectroom

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.data.dateInString
import ai.ftech.travelluxury.data.dateStringToCalendar
import ai.ftech.travelluxury.data.model.selectroom.SelectRoomModel
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ChooseDateActivity : AppCompatActivity() {

    private lateinit var btnClose: Button
    private lateinit var clvCalendar: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_date_activity)

        initView()
        setCurrentDate()

        btnClose.setOnClickListener { onBackPressed() }

        disablePastDate()

        clvCalendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val intent = Intent()
            intent.putExtra("date", dateInString(year, month + 1, dayOfMonth))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun initView() {
        btnClose = findViewById(R.id.btnChooseDateClose)
        clvCalendar = findViewById(R.id.clvChooseDateCalendar)
    }

    private fun setCurrentDate() {
        val oldDate = SelectRoomModel.INSTANCE.checkInDate
        if (oldDate == getString(R.string.check_in_date)) {
            clvCalendar.date = Calendar.getInstance().timeInMillis
        } else {
            if (oldDate != null) {
                clvCalendar.date = dateStringToCalendar(oldDate).timeInMillis
            }
        }
    }

    private fun disablePastDate() {
        clvCalendar.minDate = Calendar.getInstance().timeInMillis
    }
}
