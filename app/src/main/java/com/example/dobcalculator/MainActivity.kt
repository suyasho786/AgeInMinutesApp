package com.example.dobcalculator

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*

class MainActivity : AppCompatActivity() {

    private var SelectedDate :TextView?=null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker :Button=findViewById(R.id.DatePickerButton)
        SelectedDate=findViewById<TextView>(R.id.MyDisplayDate)
        btnDatePicker.setOnClickListener {
            clickdatepicker()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun clickdatepicker(){
        val Mycalendar=Calendar.getInstance()
        val year=Mycalendar.get(Calendar.YEAR)
        val month=Mycalendar.get(Calendar.MONTH)
        val day=Mycalendar.get(Calendar.DAY_OF_MONTH)

       val dpd=DatePickerDialog(this,
           DatePickerDialog.OnDateSetListener{view,year,month,dayofMonth->
               Toast.makeText(this,"Year Was $year,Month was ${month + 1},day of  month was $dayofMonth",Toast.LENGTH_LONG).show()
               var flag=0
               if(month+1<10)flag=1
               var dateSelected:String?=null
               if(flag==1 )dateSelected="$dayofMonth/0${month+1}/$year"
               else dateSelected="$dayofMonth/${month+1}/$year"
               SelectedDate?.setText(dateSelected)
               val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
               val theDate=sdf.parse(dateSelected)
               val selectedDateinMiniutes=theDate.time/(60000)
               val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))//time since 1970
               val currentDateInMiniutes=currentDate.time/(60000)
               val YourMiniutesSurvived =currentDateInMiniutes-selectedDateinMiniutes
               val MiniutesSurvived:TextView=findViewById(R.id.MinutesSurvived)
               MiniutesSurvived?.text=YourMiniutesSurvived.toString()
           },
           year,
           month,
           day
       )
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()

    }
}