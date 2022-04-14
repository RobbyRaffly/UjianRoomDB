package com.adl.ujianroomdb

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adl.adlroomdb.database.UserDatabase
import com.adl.adlroomdb.database.model.UserModel

import kotlinx.android.synthetic.main.activity_form_input.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.android.synthetic.main.activity_main.*


class FormInput : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_input)
        val gender:Array<String> = arrayOf("Pria", "Wanita")
        val statues:Array<String> = arrayOf("Single", "Married")
        lateinit var genderInput:String
        lateinit var StatusInput:String

        val arrayAdapter = ArrayAdapter(this@FormInput, android.R.layout.simple_spinner_dropdown_item, gender)
        spinGender.adapter = arrayAdapter
        spinGender.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                genderInput = gender[position]
                Toast.makeText(this@FormInput," Gender ${gender[position]}",Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val arrayAdapterstat = ArrayAdapter(this@FormInput, android.R.layout.simple_spinner_dropdown_item, statues)
        spinStatus.adapter = arrayAdapterstat
        spinStatus.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                StatusInput=statues[position]
                Toast.makeText(this@FormInput," Status ${statues[position]}",Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        btnSend.setOnClickListener({
            val userdata = UserModel(0,editName.text.toString(), genderInput,
                editUmur.text.toString(),StatusInput)


            GlobalScope.launch {
                UserDatabase.getInstance(this@FormInput).userDao().insertUser(userdata)
                jmlUsr = getJumlahUser()
                txtCountUser.setText("${jmlUsr.toString()} User")
                val intent = Intent()
                intent.putExtra("data",userdata)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        })
    }

    fun getJumlahUser():Int{

        return UserDatabase.getInstance(this@FormInput).userDao().getCount()

    }




}