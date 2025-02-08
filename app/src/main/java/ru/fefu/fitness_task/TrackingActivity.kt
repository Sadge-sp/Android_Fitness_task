package ru.fefu.fitness_task

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.fitness_task.databinding.ActivityTrackingBinding

class TrackingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrackingBinding
    private var isRunning = false
    private var startTime = 0L
    private val handler = Handler(Looper.getMainLooper())
    private val timerRunnable = object : Runnable {
        override fun run() {
            val millis = System.currentTimeMillis() - startTime
            val seconds = (millis / 1000) % 60
            val minutes = (millis / (1000 * 60)) % 60
            val hours = (millis / (1000 * 60 * 60)) % 24
            binding.timerTextView.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Настройка RecyclerView с опциями
        val options = listOf("Пешком", "Велосипед", "Машина")
        val adapter = OptionsAdapter(options)
        binding.optionsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.optionsRecyclerView.adapter = adapter

        // Обработка нажатия на кнопку "Начать"
        binding.startButton.setOnClickListener {
            startTimer()
        }

        // Обработка нажатия на кнопку "Стоп"
        binding.stopButton.setOnClickListener {
            pauseTimer()
        }

        // Обработка нажатия на кнопку "Закончить"
        binding.finishButton.setOnClickListener {
            finishTimer()
        }
    }

    private fun startTimer() {
        if (!isRunning) {
            startTime = System.currentTimeMillis()
            handler.postDelayed(timerRunnable, 0)
            isRunning = true

            // Показываем элементы
            binding.timerTextView.visibility = View.VISIBLE
            binding.stopButton.visibility = View.VISIBLE
            binding.finishButton.visibility = View.VISIBLE
            binding.startButton.visibility = View.GONE

            // Обновляем текст кнопки "Стоп"
            binding.stopButton.text = "Стоп"
            binding.stopButton.setOnClickListener { pauseTimer() }
        }
    }

    private fun pauseTimer() {
        if (isRunning) {
            handler.removeCallbacks(timerRunnable)
            isRunning = false
            binding.stopButton.text = "Продолжить"
            binding.stopButton.setOnClickListener { startTimer() }
        }
    }

    private fun finishTimer() {
        handler.removeCallbacks(timerRunnable)
        isRunning = false
        binding.timerTextView.visibility = View.GONE
        binding.stopButton.visibility = View.GONE
        binding.finishButton.visibility = View.GONE
        binding.startButton.visibility = View.VISIBLE
    }
}