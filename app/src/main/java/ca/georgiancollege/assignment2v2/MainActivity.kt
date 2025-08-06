package ca.georgiancollege.assignment2v2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.assignment2v2.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var collectionReference: CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        db = FirebaseFirestore.getInstance()
        collectionReference = db.collection("movies")

        binding.setDataButton.setOnClickListener{

            val inputTitle = binding.titleEditText.text.toString()

            val inputDirector= binding.directorEditText.text.toString()

            val inputYear= binding.yearEditText.text.toString()

            saveDataToNewDocument(inputTitle, inputDirector, inputYear)
        }

        binding.getDataButton.setOnClickListener {
            getAllDocumentsInCollection()
        }
    }

    fun saveDataToNewDocument(title: String, director: String, year:String){

        val movieData = MovieModel(title, director, year)


        collectionReference.add(movieData).addOnSuccessListener { documentReference ->

            if(documentReference != null)
            {
                Log.d("add", "Entry added successfully!")
            } else{
                Log.d("add", "Error adding movie entry!")
            }


        }


    }

    fun getAllDocumentsInCollection(){
        collectionReference.get().addOnSuccessListener { documents ->
        var data: String = ""
        for (document in documents){
            val movie: MovieModel = document.toObject( MovieModel::class.java)

            data += "Title: " + movie.getTitle() + " Director: " + movie.getDirector() + " Year: " + movie.getYear() + "\n"
            }

            val intentObj = Intent(applicationContext, MovieList::class.java)
            intentObj.putExtra("movie data", data)

            startActivity(intentObj)
        }

    }
}