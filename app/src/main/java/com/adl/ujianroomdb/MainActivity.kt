package com.adl.ujianroomdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.adl.adlroomdb.adapter.UserAdapter
import com.adl.adlroomdb.adapter.idussr
import com.adl.adlroomdb.database.UserDatabase
import com.adl.adlroomdb.database.model.UserModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


var jmlUsr:Int=0
class MainActivity : AppCompatActivity(),UserAdapter.ItemClickListener{
    lateinit var useradapter: UserAdapter


    var lstUser = ArrayList<UserModel>()
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == Activity.RESULT_OK){

//            if(result.data?.hasExtra("data")!!){
//                lstUser.add(result.data!!.extras?.getParcelable<UserModel>("data")!!)
//            }
            GlobalScope.launch {
                jmlUsr = getJumlahUser()

                lstUser.clear()
                lstUser.addAll(ArrayList(getAllData()))

                this@MainActivity.runOnUiThread({
                    txtCountUser.setText("${jmlUsr.toString()} User")
                    useradapter.notifyDataSetChanged()
                })


            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            jmlUsr = getJumlahUser()

            lstUser = ArrayList(getAllData())


            this@MainActivity.runOnUiThread({

                txtCountUser.setText("${jmlUsr.toString()} User")
                useradapter = UserAdapter(lstUser, this@MainActivity)
                lstItemUser.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = useradapter
                }
            })



        }

        btnAddUser.setOnClickListener({
            val intent = Intent(this@MainActivity,FormInput::class.java)
            resultLauncher.launch(intent)

        })
    }

    fun getAllData():List<UserModel>{

        return UserDatabase.getInstance(this@MainActivity).userDao().getAll()

    }

    fun getJumlahUser():Int {
        return UserDatabase.getInstance(this@MainActivity).userDao().getCount()
    }

    override fun onItemClick(userModel: UserModel) {
        Toast.makeText(this@MainActivity,"posisi = ${userModel.id}",Toast.LENGTH_LONG).show()
        idussr =userModel.id.toInt()
        val intent = Intent( this@MainActivity, UpdateUser::class.java)
        resultLauncher.launch(intent)
        //.startActivity(intent)
    }
}