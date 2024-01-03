package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate:TextView?=null
    private var tvTimeInMinutes:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvTimeInMinutes=findViewById(R.id.tvTimeInMinutes)
        val btnDatePicker:Button=findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener{
           clickDatePicker()
        }

    }
    private fun clickDatePicker(){
        val myCalender=Calendar.getInstance()
        val year=myCalender.get(Calendar.YEAR)
        val month=myCalender.get(Calendar.YEAR)
        val day=myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd= DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,selectedYear,selectedMonth,dayOfMonth->
            Toast.makeText(this,"Year is $selectedYear, Month is ${selectedMonth+1}, Day is $dayOfMonth ", Toast.LENGTH_LONG).show()
            val selectedDate="$dayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.setText(selectedDate)
            val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)
            theDate.let {
                val selectedDateInMinutes=theDate.time /60000
                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate.let {
                    val currentDateInMinutes=currentDate.time / 60000
                    val differenceInMinutes=currentDateInMinutes-selectedDateInMinutes
                    tvTimeInMinutes?.text=differenceInMinutes.toString()
                }

            }

        },year,month,day)
        dpd.show()
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000

    }
}