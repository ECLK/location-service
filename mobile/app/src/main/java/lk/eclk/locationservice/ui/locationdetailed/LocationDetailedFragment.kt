package lk.eclk.locationservice.ui.locationdetailed

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson

import lk.eclk.locationservice.R
import lk.eclk.locationservice.models.Location
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class LocationDetailedFragment : Fragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: LocationDetailedViewModelFactory by instance()
    private lateinit var viewModel: LocationDetailedViewModel
    private lateinit var location: Location

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.location_detailed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs = arguments?.let { LocationDetailedFragmentArgs.fromBundle(it) }
        location = Gson().fromJson(safeArgs!!.locationJson, Location::class.java)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(LocationDetailedViewModel::class.java)
    }
}
