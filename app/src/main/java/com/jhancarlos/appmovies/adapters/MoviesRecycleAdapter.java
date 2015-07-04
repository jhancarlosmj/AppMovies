package com.jhancarlos.appmovies.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jhancarlos.appmovies.R;
import com.jhancarlos.appmovies.models.Movie;
import com.squareup.picasso.Picasso;

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
        Movie movie;
        public Toolbar tbCard;


        public ViewHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (itemRecycleClickListener != null) {
                        movie = movies.get(getLayoutPosition());
                        itemRecycleClickListener.itemRecycleClicked(getLayoutPosition(), movie.getName());
                    }


                }
            });

            image = (ImageView) itemView.findViewById(R.id.image);
           /// name = (TextView) itemView.findViewById(R.id.name);
             tbCard = (Toolbar) itemView.findViewById(R.id.tbCard);
            director = (TextView) itemView.findViewById(R.id.director);

        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        final Movie movie = movies.get(position);
        //viewHolder.name.setText(movie.getName());
        viewHolder.director.setText(movie.getDirector());


        viewHolder.tbCard.setTitle(movie.getName());

        viewHolder.tbCard.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_delete:
                        movies.remove(movie);
                        notifyDataSetChanged();
                        break;
                }
                return true;
            }
        });

        viewHolder.tbCard.inflateMenu(R.menu.menu_card);

        switch (movie.getId()) {
            case 1:
                movie.setIdPhoto(R.drawable.pulp_fiction);
                Picasso.with(viewHolder.image.getContext()).load(R.drawable.pulp_fiction).into(viewHolder.image);
                break;

            case 2:
                movie.setIdPhoto(R.drawable.el_padrino);
                Picasso.with(viewHolder.image.getContext()).load(R.drawable.el_padrino).into(viewHolder.image);
                break;

            case 3:
                movie.setIdPhoto(R.drawable.la_vida_es_bella);
                Picasso.with(viewHolder.image.getContext()).load(R.drawable.la_vida_es_bella).into(viewHolder.image);
                break;

            case 4:
                movie.setIdPhoto(R.drawable.el_club_de_la_lucha);
                Picasso.with(viewHolder.image.getContext()).load(R.drawable.el_club_de_la_lucha).into(viewHolder.image);
                break;

            case 5:
                movie.setIdPhoto(R.drawable.cadena_perpetua);
                Picasso.with(viewHolder.image.getContext()).load(R.drawable.cadena_perpetua).into(viewHolder.image);
                break;

            case 6:
                movie.setIdPhoto(R.drawable.la_lista_de_schindler);
                Picasso.with(viewHolder.image.getContext()).load(R.drawable.la_lista_de_schindler).into(viewHolder.image);
                break;

            case 7:
                movie.setIdPhoto(R.drawable.la_naranja_mec_nica);
                Picasso.with(viewHolder.image.getContext()).load(R.drawable.la_naranja_mec_nica).into(viewHolder.image);
                break;
            default:
                Picasso.with(viewHolder.image.getContext()).load(R.drawable.caratula_default).into(viewHolder.image);
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
