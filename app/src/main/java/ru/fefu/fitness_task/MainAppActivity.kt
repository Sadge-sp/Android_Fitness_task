package ru.fefu.fitness_task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.fefu.fitness_task.databinding.ActivityMainAppBinding

class MainAppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, ActivityTabFragment(), "ActivityTabFragment")
                .commit()
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.activity_tab -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, ActivityTabFragment(), "ActivityTabFragment")
                        .commit()
                    true
                }
                R.id.profile_tab -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, ProfileFragment(), "ProfileFragment")
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}