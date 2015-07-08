package com.appmovies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appmovies.R;
import com.appmovies.models.Movie;

import java.util.List;

public class MoviesRecycleAdapter extends RecyclerView.Adapter<MoviesRecycleAdapter.ViewHolder> {

	private List<Movie> movies;
	private int itemLayout;
	ItemRecycleClickListener itemRecycleClickListener;

	public MoviesRecycleAdapter(List<Movie> data, int itemLayout) {
		movies = data;
		this.itemLayout = itemLayout;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public ImageView image;
		public TextView name;
		public TextView director;
		Movie movie;


		public ViewHolder(View itemView) {
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
			name = (TextView) itemView.findViewById(R.id.name);
			director = (TextView) itemView.findViewById(R.id.director);

		}

	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, int position) {

		final Movie movie = movies.get(position);
		viewHolder.name.setText(movie.getName());
		viewHolder.director.setText(movie.getDirector());

		viewHolder.itemView.setTag(movie);
	}


	@Override
	public int getItemCount() {
		return movies.size();
	}

	public interface ItemRecycleClickListener {
		public void itemRecycleClicked(int position, String name);
	}

	public void setItemRecycleClickListener(ItemRecycleClickListener itemRecycleClickListener) {
		this.itemRecycleClickListener = itemRecycleClickListener;
	}


}
