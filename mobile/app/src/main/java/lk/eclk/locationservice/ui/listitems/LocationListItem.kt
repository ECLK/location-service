package lk.eclk.locationservice.ui.listitems

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.location_search_row.*
import lk.eclk.locationservice.R
import lk.eclk.locationservice.models.Location

class LocationListItem(
    val location: Location
) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            tv_name_sinhala.text = location.nameSinhala
            tv_name_english.text = location.nameEnglish
            tv_name_tamil.text = location.nameTamil
            tv_path.text =
                "${location.gdn.polingDivision.nameEnglish} : ${location.gdn.polingDivision.electoralDistrict.nameEnglish}"
        }
    }

    override fun getLayout() = R.layout.location_search_row
}