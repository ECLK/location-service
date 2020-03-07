package lk.eclk.locationservice.ui.home

import lk.eclk.locationservice.internal.eventexecutor.MessageEvents

interface HomeMessageEvents {
    fun initRecyclerView(items: List<LocationListItem>)
}