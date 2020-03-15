package lk.eclk.locationservice.ui.locationdetailed.bottomsheet

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_upload_image.*
import lk.eclk.locationservice.R
import lk.eclk.locationservice.models.Location
import lk.eclk.locationservice.models.MediaItem
import java.io.File

class PickImageBottomSheetDialog(private val location: Location) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_upload_image, container, false)
    }

    private var file: File? = null
    var listener: SaveListener? = null

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
        ) {
            override fun getCount() = 7
        }
        spinner_title.setSelection(7)

        btn_cancel.setOnClickListener { dismiss() }
        btn_add.setOnClickListener {
            if (spinner_title.selectedItemPosition == 7) {
                //TODO:show error
                return@setOnClickListener
            }

            if (et_description.text.isNullOrEmpty()) {
                til_description.error = "Description can't be empty!"
                return@setOnClickListener
            } else {
                til_description.error = null
            }

            if (file == null) {
                //TODO: show error
                return@setOnClickListener
            }

            listener?.onSave(
                MediaItem(
                    0,
                    spinner_title.selectedItem.toString(),
                    et_description.text.toString(),
                    file!!.path,
                    file!!.extension, 0.0, 0.0, 1, "0", "0", location.code, 0
                ), this
            )
        }
        img.setOnClickListener {
            ImagePicker.create(this@PickImageBottomSheetDialog)
                .folderMode(true)
                .toolbarImageTitle("Select an Image")
                .single()
                .imageDirectory("eclk")
                .start()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            val image = ImagePicker.getFirstImageOrNull(data)
            this.file = image.toFile()
            Glide.with(context!!)
                .load(image.path)
                .centerCrop()
                .into(img)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    interface SaveListener {
        fun onSave(mediaItem: MediaItem, dialog: PickImageBottomSheetDialog)
    }

    private fun com.esafirm.imagepicker.model.Image.toFile(): File {
        return File(this.path)
    }
}