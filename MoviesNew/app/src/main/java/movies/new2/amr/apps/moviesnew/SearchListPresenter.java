package movies.new2.amr.apps.moviesnew;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

public class SearchListPresenter extends MvpBasePresenter<SearchListView> {
    String TAG = getClass().getName().toString();
    MovieService movieService;
    CompositeSubscription compositeSubscription;
    List<MovieInfo> list;
    List<RealmReturnedMovie> realmList;
    String previousQuery;
    boolean isRealmAdapterActive = false;
    public SearchListPresenter(MovieService movieService) {
        this.movieService = movieService;
    }
    public void setMovies(Realm realm){
        if (list!=null && !isRealmAdapterActive) {
            getView().setData(list);
            getView().showSearchList();
        }else if (!isRealmAdapterActive){
            searchForMovies(previousQuery);}
        if (realmList !=null && isRealmAdapterActive){
            getView().setRealmData(realmList);
            getView().showSearchList();
        }else if (isRealmAdapterActive){
            searchForRealmMovies(realm);
        }
    }
    public void searchForRealmMovies(Realm realm) {
        ArrayList<RealmReturnedMovie> realmReturnedMovies = new ArrayList<>();
        RealmResults<RealmReturnedMovie> query = realm.where(RealmReturnedMovie.class).findAll();
        for (RealmReturnedMovie p : query){
            realmReturnedMovies.add(p);}
        realmList = realmReturnedMovies;
        getView().setRealmData(realmList);
        getView().showSearchList();
        isRealmAdapterActive = true;
    }
    public void searchForMovies(String query) {
        previousQuery = query;
        if (isViewAttached()) {
            getView().showLoading();}
        movieService.movieSearch(previousQuery, NetworkModule.API_KEY)
                .delay(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnedMovies>() {
                    @Override
                    public void onCompleted() {
                        if (isViewAttached()) {
                            getView().showSearchList();
                            isRealmAdapterActive = false;}
                    }
                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().showError(e);}
                    }
                    @Override
                    public void onNext(ReturnedMovies movieSearchResponse) {
                        list = movieSearchResponse.getMovieInfos();
                        if (isViewAttached()) {
                            getView().setData(list);}}
                });
    }
}
