package ca.georgiancollege.assignment2v2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private var movies: List<MovieModel>, private var context: Context) :
    RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var movieView: View = LayoutInflater.from(parent.context).inflate(R.layout.movie_row,parent, false)

        var myViewHolder: MyViewHolder = MyViewHolder(movieView)

        return myViewHolder
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var movie: MovieModel = movies.get(position)
        holder.title.setText(movie.getTitle())
        holder.year.setText(movie.getYear())
        Glide.with(context).load(movie.getPoster()).into(holder.imageView)
        holder.rating.setText(movie.getRating())

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MovieList::class.java)
            context.startActivity(intent)
        }
    }

}