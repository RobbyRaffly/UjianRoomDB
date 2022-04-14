package com.adl.ujianroomdb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.adl.adlroomdb.database.UserDatabase
import com.adl.adlroomdb.database.model.UserModel
import kotlinx.android.synthetic.main.activity_form_input.*
import kotlinx.android.synthetic.main.activity_form_input.btnSend
import kotlinx.android.synthetic.main.activity_form_input.editName
import kotlinx.android.synthetic.main.activity_form_input.editUmur
import kotlinx.android.synthetic.main.activity_form_input.spinGender
import kotlinx.android.synthetic.main.activity_form_input.spinStatus
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_update_user.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

var idussr:Int=0
class UpdateUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)
        val gender:Array<String> = arrayOf("Pria", "Wanita")
        val statues:Array<String> = arrayOf("Single", "Married")
        lateinit var genderInput:String
        lateinit var StatusInput:String

        val arrayAdapter = ArrayAdapter(this@UpdateUser, android.R.layout.simple_spinner_dropdown_item, gender)
        spinGenderupdate.adapter = arrayAdapter
        spinGenderupdate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                genderInput = gender[position]
                Toast.makeText(this@UpdateUser," Gender ${gender[position]}", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val arrayAdapterstat = ArrayAdapter(this@UpdateUser, android.R.layout.simple_spinner_dropdown_item, statues)
        spinStatusupdate.adapter = arrayAdapterstat
        spinStatusupdate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                StatusInput=statues[position]
                Toast.makeText(this@UpdateUser," Status ${statues[position]}", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        btnSend.setOnClickListener({
            val userdata = UserModel(
                idussr.toLong(),updateName.text.toString(), genderInput,
                updateUmur.text.toString(),StatusInput)


            GlobalScope.launch {
                UserDatabase.getInstance(this@UpdateUser).userDao().updateUser(idussr.toLong(),updateName.text.toString(),genderInput,updateUmur.toString(), StatusInput)
                //UserDatabase.getInstance(this@UpdateUser).userDao().updateUser(userdata)
                val intent = Intent()
                intent.putExtra("data",userdata)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        })
}}