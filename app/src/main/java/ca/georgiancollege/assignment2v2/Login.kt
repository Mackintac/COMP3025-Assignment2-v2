package ca.georgiancollege.assignment2v2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.assignment2v2.databinding.ActivityLoginBinding
import ca.georgiancollege.assignment2v2.databinding.ActivityRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = Firebase.auth

        binding.registerNowTextView.setOnClickListener {
                val intentObj = Intent(applicationContext, Register::class.java)
                startActivity(intentObj)
        }

        binding.loginButton.setOnClickListener{
            val email = binding.emailLoginEditText.text.toString()
            val password = binding.passwordLoginEditText.text.toString()
            signIn(email, password)
        }
    }

    fun signIn(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->
            if (task.isSuccessful){
                val intentObj = Intent(applicationContext, MainActivity::class.java)
                startActivity(intentObj)
            }
        }
    }
}