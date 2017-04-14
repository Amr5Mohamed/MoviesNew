package movies.new2.amr.apps.moviesnew;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface MovieService {

        @GET("/3/discover/movie") Observable<ReturnedMovies> movieSearch(
                @Query("sort_by") String query,
                @Query("api_key") String apiKey);
        @GET("/3/movie/{id}/videos") Observable<ReturnedMedia> movieMediaRequest(
                @Path("id") String query,
                @Query("api_key") String apiKey);
        @GET("/3/movie/{id}/reviews") Observable<ReturnedReviews> movieReviewRequest(
                @Path("id") String query,
                @Query("api_key") String apiKey);

}
