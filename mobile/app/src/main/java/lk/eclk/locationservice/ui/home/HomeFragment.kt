package lk.eclk.locationservice.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.home_fragment.*

import lk.eclk.locationservice.R
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HomeFragment : Fragment(),KodeinAware {
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
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)
        navController = Navigation.findNavController(view)
        bindUI()
    }

    private fun bindUI() {
        fab_add_location.setOnClickListener { navController.navigate(R.id.action_homeFragment_to_searchFragment) }
    }
}
