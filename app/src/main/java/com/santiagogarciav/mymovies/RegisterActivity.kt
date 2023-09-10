package com.santiagogarciav.mymovies

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.santiagogarciav.mymovies.databinding.ActivityRegisterBinding
import java.util.Calendar
import java.util.Locale

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private var cal = Calendar.getInstance()
    private var dateOfBirth:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = registerBinding.root
        setContentView(R.layout.activity_register)

        registerBinding.registerButton.setOnClickListener {
            val name = registerBinding.nameEditText.text.toString()
            val email = registerBinding.emailEditText.text.toString()
            val password = registerBinding.passwordEditText.text.toString()
            val rePassword = registerBinding.rePasswordEditText.text.toString()
            val cities = registerBinding.citySpinner.selectedItem.toString()
            var genre = "Femenino"
            if (registerBinding.maleRatioButton.isChecked){
                genre = "Masculino"
            }
            if (name == "" || email == "" || password == "" || rePassword == "" || genre == "" || cities == "" || dateOfBirth == "") {
                Toast.makeText(this, "Falta ingresar algún elemento", Toast.LENGTH_LONG).show()
            }else {
                if (password == rePassword) {
                    val info = name + "\n" + email + "\n" + password + "\n" + genre + "\n" + cities + "\n" + dateOfBirth
                    registerBinding.infoTextView.text = info
                } else{
                    Toast.makeText(this,"Las contraseñas no son iguales", Toast.LENGTH_LONG).show()
                }
            }
        }
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM/dd/yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                dateOfBirth = sdf.format(cal.time).toString()
                registerBinding.datePickerButton.setText(dateOfBirth)
            }

        registerBinding.datePickerButton.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}