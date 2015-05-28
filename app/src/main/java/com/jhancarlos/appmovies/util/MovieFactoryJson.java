package com.jhancarlos.appmovies.util;

import android.content.Context;
import android.widget.Toast;

import com.jhancarlos.appmovies.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by DAM on 18/5/15.
 */
public class MovieFactoryJson {

    private String json = "";
    public static ArrayList<Movie> data = new ArrayList<>();
    private BufferedReader bufferedReader;
    private StringBuilder builder;
    private static String NAME_JSON = "movies.json";
    private static String ID_MOVIE = "id";
    private static String ID_NAME = "name";
    private static String ID_DIRECTOR = "director";
    private static String ID_YEAR = "year";
    private static String ID_COUNTRY = "country";

    public ArrayList<Movie> getMovie (Context context){


        try {

            builder = new StringBuilder();
            bufferedReader =  new BufferedReader(new InputStreamReader(context.getAssets().open(NAME_JSON)));

            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }

            bufferedReader.close();
            json = builder.toString();

            JSONArray jsonArray = new JSONArray(json);

            for (int index = 0 ; index < jsonArray.length();index++){
                Movie movie = new Movie();
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                movie.setId(jsonObject.getInt(ID_MOVIE));
                movie.setName(jsonObject.getString(ID_NAME));
                movie.setDirector(jsonObject.getString(ID_DIRECTOR));
                movie.setYear(jsonObject.getInt(ID_YEAR));
                movie.setCountry(jsonObject.getString(ID_COUNTRY));
                data.add(movie);

            }

        }catch (IOException ex){
            ex.printStackTrace();
            Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
        } catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
        }


            return data;
    }

}
