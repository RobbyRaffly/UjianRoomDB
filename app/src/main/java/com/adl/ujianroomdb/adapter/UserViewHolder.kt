package com.adl.adlroomdb.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.adl.ujianroomdb.UpdateUser

//import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user.view.*
import kotlinx.android.synthetic.main.item_user.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


var idussr:Int=0

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val nama = view.txtUsername
    val Umur = view.txtGndage
    val status = view.txtStatus
    val edit = view.btnEdit



    val context = view.context

    fun bindData(adapter:UserAdapter, position: Int, mItemClickListener: UserAdapter.ItemClickListener) {

        nama.setText(adapter.data.get(position).usernama)
        Umur.setText(adapter.data.get(position).gnder+"/"+adapter.data.get(position).age)
        status.setText(adapter.data.get(position).stat)
        //Glide.with(context).load(adapter.data.get(position).photo).into(image)

        edit.setOnClickListener {
            mItemClickListener.onItemClick(adapter.data.get(position))
        }
    }
}