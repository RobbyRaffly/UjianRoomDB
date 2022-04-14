package com.adl.ujianroomdb

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adl.adlroomdb.database.UserDatabase
import com.adl.adlroomdb.database.model.UserModel

import kotlinx.android.synthetic.main.activity_form_input.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.android.synthetic.main.activity_main.*


class FormInput : AppCompatActivity (), LocationListener {
    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: EditText
    private val locationPermissionCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_input)
        val gender:Array<String> = arrayOf("Pria", "Wanita")
        val statues:Array<String> = arrayOf("Single", "Married")
        lateinit var genderInput:String
        lateinit var StatusInput:String

        getLocation()

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
//                jmlUsr = getJumlahUser()
//                txtCountUser.setText("${jmlUsr.toString()} User")
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

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }
    override fun onLocationChanged(location: Location) {
        tvGpsLocation = findViewById(R.id.editLocation)
        tvGpsLocation.setText(location.latitude.toString() +","+location.longitude.toString())
       // tvGpsLocation.text = "Latitude: " + location.latitude.toString() + " , Longitude: " + location.longitude
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }





}