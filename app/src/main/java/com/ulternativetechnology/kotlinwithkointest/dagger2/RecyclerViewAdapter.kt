package com.ulternativetechnology.kotlinwithkointest.dagger2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ulternativetechnology.kotlinwithkointest.R
import com.ulternativetechnology.kotlinwithkointest.dagger2.model.RecyclerData
import kotlinx.android.synthetic.main.recycler_view_item_row.view.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listData: List<RecyclerData>? = null

    fun setUpdatedData(listData: List<RecyclerData>) {
        this.listData = listData
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imageView = view.imageView
        private val textviewName = view.textviewName
        private val textviewDescription = view.textviewDescription

        fun bind(data: RecyclerData) {
            textviewName.text = data.name
            textviewDescription.text = data.description

            Glide.with(imageView)
                .load(data.owner?.avatar_url)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(listData?.get(position)!!)

    override fun getItemCount(): Int {
        return if (listData == null) 0
        else listData?.size!!
    }

}