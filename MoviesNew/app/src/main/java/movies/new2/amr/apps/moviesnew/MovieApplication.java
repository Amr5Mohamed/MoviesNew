package movies.new2.amr.apps.moviesnew;

import android.app.Application;

public class MovieApplication extends Application{
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }
    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
        appComponent.inject(this);
    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
