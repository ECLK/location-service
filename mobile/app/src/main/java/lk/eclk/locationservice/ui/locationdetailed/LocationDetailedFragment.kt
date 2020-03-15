package lk.eclk.locationservice.ui.locationdetailed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import kotlinx.android.synthetic.main.location_detailed_fragment.*

import lk.eclk.locationservice.R
import lk.eclk.locationservice.models.Location
import lk.eclk.locationservice.models.MediaItem
import lk.eclk.locationservice.ui.locationdetailed.bottomsheet.PickImageBottomSheetDialog
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class LocationDetailedFragment : Fragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: LocationDetailedViewModelFactory by instance()
    private lateinit var viewModel: LocationDetailedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.location_detailed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs = arguments?.let { LocationDetailedFragmentArgs.fromBundle(it) }
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(LocationDetailedViewModel::class.java)
        viewModel.setLocation(Gson().fromJson(safeArgs!!.locationJson, Location::class.java))
        bindUI()
    }

    private fun bindUI() {
        viewModel.location.observe(viewLifecycleOwner, Observer { location ->
            if (location == null) return@Observer
            tv_name_sinhala.text = location.nameSinhala
            tv_name_english.text = location.nameEnglish
            tv_name_tamil.text = location.nameTamil
        })

        viewModel.saved.observe(viewLifecycleOwner, Observer {
            fab_save.visibility = if (it) View.GONE else View.VISIBLE
            fab_add_images.visibility = if (it) View.VISIBLE else View.GONE
        })

        fab_save.setOnClickListener { viewModel.insertLocation() }
        fab_add_images.setOnClickListener {
            val bottomSheet = PickImageBottomSheetDialog(viewModel.location.value!!)
            bottomSheet.listener = object : PickImageBottomSheetDialog.SaveListener {

                override fun onSave(mediaItem: MediaItem, dialog: PickImageBottomSheetDialog) {
                    Log.e("item", mediaItem.toString())
                    dialog.dismiss()
                }
            }
            bottomSheet.show(activity!!.supportFragmentManager, "bottom_sheet")
        }
    }
}
