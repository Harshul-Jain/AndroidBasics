package com.example.todoapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_task.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


const val DB_NAME = "todo.db"

class TaskActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var myCalendar: Calendar
    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener

    private val labels =
        arrayListOf("Personal", "Business", "Insurance", "Shopping", "Banking", "Work")

    var finalDate = 0L
    var finalTime = 0L
    val db by lazy {
        AppDatabase.getDatabase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        dateEdt.setOnClickListener(this)
        timeEdt.setOnClickListener(this)
        saveBtn.setOnClickListener(this)
        setUpSpinner()
    }

    private fun setUpSpinner() {
        labels.sort()
        val adapter =
            ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                labels
            )
        spinnerCategory.adapter = adapter

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.dateEdt -> {
                setListener()
            }
            R.id.timeEdt -> {
                setTimeListener()
            }
            R.id.saveBtn -> {
                saveTodo()
            }
        }
    }

    private fun saveTodo() {
        val category = spinnerCategory.selectedItem.toString()
        val title = titleInpLay.editText?.text.toString()
        val description = taskInpLay.editText?.text.toString()

        GlobalScope.launch(Dispatchers.Main) {
            val id = withContext(Dispatchers.IO) {
                return@withContext db.todoDao().insertTask(
                    TodoModel(
                        title,
                        description,
                        category,
                        finalDate,
                        finalTime
                    )
                )
            }
            finish()
        }
    }

    private fun setTimeListener() {
        myCalendar = Calendar.getInstance()
        timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            myCalendar.set(Calendar.HOUR, hourOfDay)
            myCalendar.set(Calendar.MINUTE, minute)
            updateTime()
        }
        val timePickerDialog = TimePickerDialog(
            this,
            timeSetListener,
            myCalendar.get(Calendar.HOUR),
            myCalendar.get(Calendar.MINUTE),
            false
        )
        timePickerDialog.show()
    }

    private fun updateTime() {
        val myFormat = " h:mm a"
        val sdf = SimpleDateFormat(myFormat)
        finalTime = myCalendar.time.time
        timeEdt.setText(sdf.format(myCalendar.time))
    }

    private fun setListener() {
        myCalendar = Calendar.getInstance()
        dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDate()
        }
        val datePickerDialog = DatePickerDialog(
            this, dateSetListener,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun updateDate() {
        val myFormat = "EEE,d MMM yyyy"
        val sdf = SimpleDateFormat(myFormat)
        finalDate = myCalendar.time.time
        dateEdt.setText(sdf.format(myCalendar.time))
        timeInpLay.visibility = View.VISIBLE
    }

}