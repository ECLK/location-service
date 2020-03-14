package lk.eclk.locationservice.ui.locationdetailed.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_upload_image.*
import lk.eclk.locationservice.R

class PickImageBottomSheetDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_upload_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner_title.adapter = object : ArrayAdapter<String>(
            this.context!!, android.R.layout.simple_spinner_dropdown_item, listOf(
                "Main entrance ",
                " Road from main gate to location",
                " location doors",
                " Location inside 360",
                " Staff accommodation",
                " Toilets - Washroom",
                "Other",
                "Select"
            )
        ){
            override fun getCount() = 7
        }
        spinner_title.setSelection(7)
    }

}