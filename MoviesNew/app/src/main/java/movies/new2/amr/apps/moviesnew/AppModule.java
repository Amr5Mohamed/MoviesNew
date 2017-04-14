package movies.new2.amr.apps.moviesnew;

import android.content.SharedPreferences;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class AppModule {
    private final String APPLICATION_TAG = "movies.new2.amr.apps.moviesnew";
    private MovieApplication movieApplication;
    private RxBus _rxBus = null;
    private SharedPreferences sharedPreferences;
    private Realm realm;
    public AppModule(MovieApplication movieApplication){
        this.movieApplication = movieApplication;
    }
    @Provides @Singleton
    public MovieApplication provideMovieApplication(){
        return this.movieApplication;
    }
    @Provides @Singleton
    public RxBus provideRxBus() {
        if (_rxBus == null) {_rxBus = new RxBus();}
        return _rxBus;
    }
    @Provides @Singleton
    public SharedPreferences provideSharedPreferences (MovieApplication movieApplication){
        if (sharedPreferences == null){
           sharedPreferences = movieApplication.getSharedPreferences(APPLICATION_TAG, movieApplication.MODE_PRIVATE);
        }
        return sharedPreferences;
    }
    @Provides @Singleton
    public Realm provideRealm (MovieApplication movieApplication){
        if (realm == null){
            realm = Realm.getInstance(movieApplication);}
        return realm;
    }
}
