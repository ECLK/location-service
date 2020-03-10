package lk.eclk.locationservice.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.launch

import lk.eclk.locationservice.R
import lk.eclk.locationservice.internal.ScopedFragment
import lk.eclk.locationservice.models.Location
import lk.eclk.locationservice.ui.listitems.LocationListItem
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HomeFragment : ScopedFragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private lateinit var viewModel: HomeViewModel
    private val viewModelFactory: HomeViewModelFactory by instance()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        navController = Navigation.findNavController(view)
        bindUI()
    }

    private fun bindUI() = launch {
        fab_add_location.setOnClickListener { navController.navigate(R.id.action_homeFragment_to_searchFragment) }
        viewModel.locations.await().observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            progress_horizontal.visibility = View.GONE
            initRecyclerView(it.toLocationListItems())
        })
    }

    private fun initRecyclerView(items: List<LocationListItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
            setOnItemClickListener { item, _ ->
                (item as? LocationListItem)?.let {
                    //                    val action =
//                        SearchFragmentDirections.actionSearchFragmentToLocationDetailedFragment(
//                            Gson().toJson(it.location)
//                        )
//                    navController.navigate(action)
                }
            }
        }
        rv_locations.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            adapter = groupAdapter
        }
    }

    private fun List<Location>.toLocationListItems() = this.map {
        LocationListItem(
            it
        )
    }
}
