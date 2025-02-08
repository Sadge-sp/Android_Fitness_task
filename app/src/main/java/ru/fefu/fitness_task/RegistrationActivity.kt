package ru.fefu.fitness_task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.fitness_task.databinding.ActivityRegistrationBinding
import android.widget.Spinner
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.coroutines.runBlocking


class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val choices = resources.getStringArray(R.array.gender_options)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, choices)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        binding.continueButton.setOnClickListener {
            val intent = Intent(this, MainAppActivity::class.java)
            startActivity(intent)
        }
    }
}