package ca.georgiancollege.assignment2v2

class MovieModel() {
    private lateinit var title: String
    private lateinit var director: String
    private lateinit var year: String



    constructor(title:String, director:String, year: String) : this() {
        this.title = title
        this.director = director
        this.year = year
    }

    fun getTitle(): String{
        return title
    }

    fun setTitle(title:String){
        this.title = title
    }


    fun getDirector(): String{
        return director
    }

    fun setDirector(director:String){
        this.director = director
    }

    fun getYear():String {
        return year
    }
    fun setYear(year:String){
        this.year = year
    }
}