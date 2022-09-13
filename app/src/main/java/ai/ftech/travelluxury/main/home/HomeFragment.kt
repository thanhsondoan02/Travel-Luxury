package ai.ftech.travelluxury.main.home

import ai.ftech.travelluxury.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var inflateView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflateView = inflater.inflate(R.layout.home_fragment, container, false)

        recyclerView = inflateView.findViewById(R.id.rlHomeFragment)

        linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        homeAdapter = HomeAdapter()
        recyclerView.adapter = homeAdapter

        return inflateView
    }


}
