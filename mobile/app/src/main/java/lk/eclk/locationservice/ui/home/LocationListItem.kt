package lk.eclk.locationservice.ui.home

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.location_search_row.*
import lk.eclk.locationservice.R
import lk.eclk.locationservice.models.Location

class LocationListItem(
    private val location: Location
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            tv_name_sinhala.text = location.nameSinhala
        }
    }

    override fun getLayout() = R.layout.location_search_row
}