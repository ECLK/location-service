package lk.eclk.locationservice.ui.listitems

import android.content.Context
import android.util.Base64
import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.image_item_row.*
import lk.eclk.locationservice.R
import lk.eclk.locationservice.models.MediaItem

class ImageListItem(val mediaItem: MediaItem, private val context: Context) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            Glide.with(context)
                .load(Base64.decode(mediaItem.media, Base64.DEFAULT))
                .placeholder(R.drawable.ic_photo_size_select_actual)
                .into(img)
            tv_title.text = mediaItem.title
            tv_description.text = mediaItem.description
            tv_created_date.text = mediaItem.createdTime.toString()
        }
    }

    override fun getLayout() = R.layout.image_item_row
}