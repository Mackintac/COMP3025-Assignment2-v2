package ca.georgiancollege.assignment2v2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.assignment2v2.databinding.ActivityMovieListBinding
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class MovieList : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding
    private lateinit var movieList: ArrayList<MovieModel>
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore
    private lateinit var collectionReference: CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieListBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        db = FirebaseFirestore.getInstance()
        collectionReference = db.collection("movies")

        movieList = ArrayList<MovieModel>()

        myAdapter = MyAdapter(movieList, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = myAdapter

        fetchMoviesCollection()
    }


    private fun fetchMoviesCollection(){
        collectionReference.get().addOnSuccessListener { documents ->
            for (document in documents){
                val movie = document.toObject(MovieModel::class.java)
                movieList.add(movie)
            }
            myAdapter.notifyDataSetChanged()
        }
    }
}