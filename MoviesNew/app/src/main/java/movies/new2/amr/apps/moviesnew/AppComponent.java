package movies.new2.amr.apps.moviesnew;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import javax.inject.Singleton;
import dagger.Component;
import retrofit.RestAdapter;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class}
)
public interface AppComponent {

    void inject(ActivityMain activity);
    void inject(SearchFragment fragment);
    void inject(DetailsFrag fragment);
    void inject(MovieApplication movieApplication);
    public MovieApplication movieApplication();
    public RxBus rxBus();
    public OkHttpClient okHttpClient();
    public Gson gson();
    public RestAdapter restAdapter();
    public MovieService movieService();
}
