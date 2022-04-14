package com.adl.adlroomdb.adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adl.ujianroomdb.UpdateUser

//import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user.view.*
import kotlinx.android.synthetic.main.item_user.view.*

import com.adl.ujianroomdb.idussr

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val nama = view.txtUsername
    val Umur = view.txtGndage
    val status = view.txtStatus
    val edit = view.btnEdit

    val context = view.context

    fun bindData(adapter:UserAdapter, position: Int) {

        nama.setText(adapter.data.get(position).usernama)
        Umur.setText(adapter.data.get(position).gnder+"/"+adapter.data.get(position).age)
        status.setText(adapter.data.get(position).stat)
        //Glide.with(context).load(adapter.data.get(position).photo).into(image)

        edit.setOnClickListener {
            idussr=position
            val intent = Intent( context, UpdateUser::class.java)
            context.startActivity(intent)
        }
    }
}