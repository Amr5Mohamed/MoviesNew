package movies.new2.amr.apps.moviesnew;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@Module
public class NetworkModule {
    public static String API_KEY="1f6e223f90fb57a943c57b6b1cccfd57";//cc1c2ce2a16823ab8ea4bb70470d6
    public static String HTTP_REQUEST_DISCOVER="http://api.themoviedb.org";
    public static final String PRODUCTION_API_URL = "http://image.tmdb.org/t/p/w185//";
    public static String BASE_URL="http://image.tmdb.org/t/p/";
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }
    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(HTTP_REQUEST_DISCOVER);
    }
    @Provides
    @Singleton
    RequestInterceptor provideRequestInterceptor(){
        return  new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };
    }
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }
    @Provides
    @Singleton
    RestAdapter provideRestAdapter(Endpoint endpoint, OkHttpClient client, Gson gson,RequestInterceptor requestInterceptor) {
        return new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setEndpoint(endpoint)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();
    }
    @Provides
    @Singleton
    MovieService provideMovieService(RestAdapter restAdapter) {
        return restAdapter.create(MovieService.class);
    }
}
