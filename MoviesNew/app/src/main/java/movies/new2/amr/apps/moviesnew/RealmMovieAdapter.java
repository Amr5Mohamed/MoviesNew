package movies.new2.amr.apps.moviesnew;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

public class RealmMovieAdapter extends RecyclerView.Adapter<RealmMovieAdapter.ViewHolder> {
    Glide glide;
    private List<RealmReturnedMovie> movies;
    Context context;
    public RealmMovieAdapter( ) {}
    public List<RealmReturnedMovie> getRealmMovies() {
        return movies ;
    }
    public void setRealmMovies(List<RealmReturnedMovie> movies) {
        this.movies = movies;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image) ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);}
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_view_images, parent, false);
        context= rowView.getContext();
        return new ViewHolder(rowView);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RealmReturnedMovie movie = movies.get(position);
        String imageUrl = "http://image.tmdb.org/t/p/w185/" + movie.getRealmMovieInfo().getPosterPath();
        RealmMovieAdapter.this.glide.with(context)
                .load(imageUrl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.placeholderdrawable)
                .fitCenter()
                .into(holder.image);
    }
    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }
}
