package com.adl.adlroomdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.adl.adlroomdb.database.model.UserModel
import com.adl.adlroomdb.database.UserDatabase
import com.adl.ujianroomdb.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserAdapter(val data :ArrayList<UserModel>, val mItemClickListener:ItemClickListener) : RecyclerView.Adapter<UserViewHolder>() {
    interface ItemClickListener{
        fun onItemClick(user:UserModel)
    }


    lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        this.parent =parent

        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))


    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
      holder.bindData(this@UserAdapter,position, mItemClickListener)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    fun deleteDataAt( position: Int){
        GlobalScope.launch {

            val deleteData = UserDatabase.getInstance(parent.context).userDao()
                .deleteUser(data.get(position))

            data.clear()
            data.addAll( ArrayList(UserDatabase.getInstance(parent.context).userDao().getAll()))
            val mainExecutor = ContextCompat.getMainExecutor(parent.context)

            // Execute a task in the main thread
            mainExecutor.execute {
                // You code logic goes here.
                notifyDataSetChanged()
            }

        }

    }

    fun ambilIdAt(position: Int){
        //val Idnya = UserDatabase.getInstance(parent.context).userDao().getIdUser()
        data.clear()
        data.addAll( ArrayList(UserDatabase.getInstance(parent.context).userDao().getAll()))
        val mainExecutor = ContextCompat.getMainExecutor(parent.context)

        // Execute a task in the main thread
        mainExecutor.execute {
            // You code logic goes here.
            notifyDataSetChanged()
        }


    }





}