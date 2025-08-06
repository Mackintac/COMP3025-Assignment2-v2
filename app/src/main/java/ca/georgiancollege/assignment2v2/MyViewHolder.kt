package ca.georgiancollege.assignment2v2

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    lateinit var imageView: ImageView
    lateinit var title: TextView
    lateinit var year: TextView
    lateinit var rating: TextView

    init {
        imageView = itemView.findViewById(R.id.posterImageView)
        title = itemView.findViewById(R.id.titleTextView)
        year = itemView.findViewById(R.id.yearTextView)
        rating = itemView.findViewById(R.id.ratingTextView)
    }
}