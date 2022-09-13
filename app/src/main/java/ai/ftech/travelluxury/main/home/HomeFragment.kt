package ai.ftech.travelluxury.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.main.myaccount.MyAccountAdapter
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rlHomeFragment)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerView.adapter = HomeAdapter()

        return view
    }


}