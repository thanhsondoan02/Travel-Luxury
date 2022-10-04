package ai.ftech.travelluxury.ui.main.myaccount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ai.ftech.travelluxury.R
import androidx.recyclerview.widget.RecyclerView

class MyAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.my_account_fragment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMyAccountFragment)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerView.adapter = MyAccountAdapter()

        return view
    }


}
