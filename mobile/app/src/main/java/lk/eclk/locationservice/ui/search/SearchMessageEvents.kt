package lk.eclk.locationservice.ui.search

import lk.eclk.locationservice.ui.listitems.LocationListItem

interface SearchMessageEvents {
    fun initRecyclerView(items: List<LocationListItem>)
}