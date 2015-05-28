package com.jhancarlos.appmovies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jhancarlos.appmovies.R;
import com.jhancarlos.appmovies.models.Movie;

import java.util.ArrayList;

/**
 * Created by DAM on 18/5/15.
 */
public class MoviesRecycleAdapter extends RecyclerView.Adapter<MoviesRecycleAdapter.ViewHolder> {

    private ArrayList<Movie> movies;
    private int itemLayout;
    ItemRecycleClickListener itemRecycleClickListener;

    public MoviesRecycleAdapter(ArrayList<Movie> data, int itemLayout){
        movies =  data;
        this.itemLayout = itemLayout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView name;
        public TextView director;


        public ViewHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (itemRecycleClickListener != null) {
                        Movie movie = movies.get(getLayoutPosition());
                        itemRecycleClickListener.itemRecycleClicked(getLayoutPosition(), movie.getName());
                    }


                }
            });

            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            director = (TextView) itemView.findViewById(R.id.director);

        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Movie movie = movies.get(position);
        viewHolder.name.setText(movie.getName());
        viewHolder.director.setText(movie.getDirector());

        switch (movie.getId()) {
            case 1:

                viewHolder.image.setImageResource(R.drawable.pulp_fiction);
                break;

            case 2:

                viewHolder.image.setImageResource(R.drawable.el_padrino);
                break;

            case 3:
                viewHolder.image.setImageResource(R.drawable.la_vida_es_bella);
                break;

            case 4:
                viewHolder.image.setImageResource(R.drawable.el_club_de_la_lucha);
                break;

            case 5:
                viewHolder.image.setImageResource(R.drawable.cadena_perpetua);
                break;

            case 6:
                viewHolder.image.setImageResource(R.drawable.la_lista_de_schindler);
                break;

            case 7:
                viewHolder.image.setImageResource(R.drawable.la_naranja_mec_nica);
                break;
            default:
                viewHolder.image.setImageResource(R.drawable.caratula_default);
        }
        viewHolder.itemView.setTag(movie);
    }


    @Override
    public int getItemCount(){
        return movies.size();
    }

    public interface ItemRecycleClickListener{
        public void itemRecycleClicked(int position,String name);
    }

    public void setItemRecycleClickListener(ItemRecycleClickListener itemRecycleClickListener){
        this.itemRecycleClickListener = itemRecycleClickListener;
    }

}