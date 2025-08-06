package ca.georgiancollege.assignment2v2

import android.net.Uri

class MovieModel() {
    private lateinit var title: String
    private lateinit var rating: String
    private lateinit var poster: String
    private lateinit var year: String



    constructor(title:String, poster: String,
                rating: String, year: String) : this() {
        this.title = title
        this.rating = rating
        this.poster = poster
        this.year = year
    }

    fun getTitle(): String{
        return title
    }

    fun setTitle(title:String){
        this.title = title
    }

    fun getRating(): String
    {
        return rating
    }

    fun getPoster(): String{
        return poster
    }
    fun getYear():String {
        return year
    }
    fun setYear(year:String){
        this.year = year
    }
}