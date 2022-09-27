package ai.ftech.travelluxury.main.home

import ai.ftech.travelluxury.R
import ai.ftech.travelluxury.hotellist.HotelListActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        HOME_MODEL.getHotelListApi()

        recyclerView = view.findViewById(R.id.rlHomeFragment)
        recyclerView.layoutManager = LinearLayoutManager(context)

        homeAdapter = HomeAdapter().apply {
            listener = object : HomeAdapter.Listener {
                override fun onCityClick() {
                    startActivity(Intent(context, HotelListActivity::class.java))
                }
            }
        }
        recyclerView.adapter = homeAdapter
    }
}
