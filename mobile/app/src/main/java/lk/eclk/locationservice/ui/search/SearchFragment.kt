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
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.search_fragment.*

import lk.eclk.locationservice.R
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SearchFragment : Fragment(), KodeinAware, SearchMessageEvents {

    override val kodein: Kodein by closestKodein()
    private lateinit var viewModel: SearchViewModel
    private val viewModelFactory: SearchViewModelFactory by instance()

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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
    }

    override fun initRecyclerView(items: List<LocationListItem>) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }
        rv_locations.apply {
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = groupAdapter
        }
    }
}
