package com.jhancarlos.appmovies.models;

/**
 * Created by Jhancarlos on 10/05/2015.
 */
public class Movie {
    private int id;
    private String name;
    private String director;
    private int year;
    private String country;
    private int idPhoto;

    public Movie() {
    }

    public Movie(int id, String name, String director, int year, String country, int idPhoto) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.year = year;
        this.country = country;
        this.idPhoto = idPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", country='" + country + '\'' +
                ", idPhoto=" + idPhoto +
                '}';
    }
}
