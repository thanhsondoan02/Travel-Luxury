package ai.ftech.travelluxury.ui.main.myaccount

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.ui.setting.SettingActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class MyAccountFragment : Fragment() {

    interface IListener {
        fun onSettingClick()
    }

    lateinit var listener: IListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_account_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMyAccountFragment)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerView.adapter = MyAccountAdapter().apply {
            listener = this@MyAccountFragment.listener
        }
    }

    private fun initListener() {
        listener = object : IListener {
            override fun onSettingClick() {
                startActivity(Intent(context, SettingActivity::class.java))
            }
        }
    }

}
