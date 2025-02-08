package ru.fefu.fitness_task
import android.content.Intent
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.fefu.fitness_task.databinding.ActivityMainBinding
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.fefu.fitness_task.ui.theme.Fitness_taskTheme

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }


        val spannableString = SpannableString("Уже есть аккаунт?")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: android.text.TextPaint) {
                super.updateDrawState(ds)
                ds.color = getColor(R.color.purple_500)
                ds.isUnderlineText = true
            }
        }
        spannableString.setSpan(clickableSpan, 0, spannableString.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.loginTextView.text = spannableString
        binding.loginTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Fitness_taskTheme {
        Greeting("Android")
    }
}