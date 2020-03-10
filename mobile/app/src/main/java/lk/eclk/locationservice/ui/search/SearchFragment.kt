package lk.eclk.locationservice.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.search_fragment.*

import lk.eclk.locationservice.R
import lk.eclk.locationservice.models.Location
import lk.eclk.locationservice.ui.listitems.LocationListItem
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SearchFragment : Fragment(), KodeinAware, SearchMessageEvents {

    override val kodein: Kodein by closestKodein()
    private lateinit var viewModel: SearchViewModel
    private val viewModelFactory: SearchViewModelFactory by instance()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(view)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        bindUI()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_toolbar_menu, menu)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.setIconifiedByDefault(false)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchLocations(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchLocations(newText)
                return false
            }
        })
    }

    private fun bindUI() {
        viewModel.liveMessageEvent.setEventReceiver(this, this)

        viewModel.searching.observe(viewLifecycleOwner, Observer {
            progress_horizontal.visibility = if (it) View.VISIBLE else View.GONE
            if (it) initRecyclerView(emptyList())
        })

        viewModel.locations.observe(viewLifecycleOwner, Observer {
            if(it == null) return@Observer
            initRecyclerView(it.toLocationListItems())
        })
    }

    override fun initRecyclerView(items: List<LocationListItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
            setOnItemClickListener { item, _ ->
                (item as? LocationListItem)?.let {
                    val action =
                        SearchFragmentDirections.actionSearchFragmentToLocationDetailedFragment(
                            Gson().toJson(it.location)
                        )
                    navController.navigate(action)
                }
            }
        }
        rv_locations.apply {
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = groupAdapter
        }
    }
    private fun List<Location>.toLocationListItems() = this.map {
        LocationListItem(
            it
        )
    }
}
