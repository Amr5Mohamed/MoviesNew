package movies.new2.amr.apps.moviesnew;

import android.content.SharedPreferences;
import android.util.Log;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.realm.Realm;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class DetailsFragPresenter extends MvpBasePresenter<DetailsFragView> {
    private final String TAG = getClass().getName().toString();
    private final String APPLICATION_TAG = "movies.new2.amr.apps.moviesnew";
    private final String MOVIE_ID = "MOVIE_ID";
    MovieService movieService;
    int movieID = 0;
    RealmMovieInfo realmMovieInfo;
    List<MovieMedia> returnedMediaList;
    List<MovieReview> returnedReviewList;
    RealmReturnedMovie realmReturnedMovie;
    String previousMediaQuery;
    String previousReviewQuery;
    int LOADSTATE = 0;
    public DetailsFragPresenter(MovieService movieService) {
        this.movieService = movieService;
    }
    public void initFrag(Realm realm, SharedPreferences sharedPreferences, int providedMovieID){
        if (movieID == 0){
            this.movieID = providedMovieID;
        }
        if (sharedPreferences.getInt(String.valueOf(movieID),0) > 0){
            Log.i("LOOKUP", String.valueOf(movieID) + " exists in SharedPreferences");
            RealmReturnedMovie query = realm.where(RealmReturnedMovie.class)
                    .equalTo("id",movieID)
                    .findFirst();
            RealmReturnedMovie realmReturnedMovie = new RealmReturnedMovie();
            realmReturnedMovie.setRealmMovieInfo(query.getRealmMovieInfo());
            realmReturnedMovie.setRealmMediaList(query.getRealmMediaList());
            realmReturnedMovie.setRealmReviewList(query.getRealmReviewList());
            if (isViewAttached()) {
                setRealmDetails(realmReturnedMovie); // change this to lookup from realm
            }
        }else {
            if (isViewAttached()){
                if (getView().getRealmMovieInfo() != null) {
                    realmMovieInfo = getView().getRealmMovieInfo();
                    Log.i("LOOKUP",String.valueOf(movieID)+" DOES NOT exist in SharedPreferences");
                    setDetails(realmMovieInfo);
                }else if(realmMovieInfo!=null){
                    setDetails(realmMovieInfo);
                }} }
    }
    public void setDetails(RealmMovieInfo currentMovieInfo) {
        if (isViewAttached()) {
            if (this.realmMovieInfo == null) {
                this.realmMovieInfo = currentMovieInfo;
            }
            getView().setData(realmMovieInfo);
            getView().showSearchList();//If view IS attached then show the searchList
            movieID = realmMovieInfo.getId();
            if (returnedMediaList !=null) {
                getView().setDataMedia(returnedMediaList);
            }else{
                requestMovieMedia();
            }
            if (returnedReviewList !=null) {
                getView().setDataReview(returnedReviewList);
            }else{
                requestMovieReviews();
            }}
    }
    public void setRealmDetails(RealmReturnedMovie currentRealmMovie){
        if (this.realmReturnedMovie == null){
            this.realmReturnedMovie = currentRealmMovie;
        }
        getView().setRealmData(this.realmReturnedMovie);
        getView().showSearchList();
    }
        public void requestMovieMedia() {
            String query =Integer.toString(realmMovieInfo.getId());
            previousMediaQuery = query;
            if (isViewAttached()) {
                getView().showLoading();
            }
            movieService.movieMediaRequest(query, NetworkModule.API_KEY)
                    .delay(3, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ReturnedMedia>() {
                        @Override
                        public void onCompleted() {
                            if (isViewAttached()) {
                                getView().showSearchList();
                                LOADSTATE++;}
                        }
                        @Override
                        public void onError(Throwable e) {
                            if (isViewAttached()) {
                                getView().showError(e);}
                        }
                        @Override
                        public void onNext(ReturnedMedia movieMediaRequestResponse) {
                            returnedMediaList = movieMediaRequestResponse.getResultsMedia();
                            if (isViewAttached()) {
                                getView().setDataMedia(returnedMediaList);}}
                    });
        }
    public void requestMovieReviews() {
        String queryReview =Integer.toString(realmMovieInfo.getId());
        previousReviewQuery = queryReview;
        if (isViewAttached()) {
            getView().showLoading();
        }
        movieService.movieReviewRequest(queryReview, NetworkModule.API_KEY)
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnedReviews>() {
                    @Override
                    public void onCompleted() {
                        if (isViewAttached()) {
                            getView().showSearchList();
                            LOADSTATE++;
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().showError(e);
                        }
                    }
                    @Override
                    public void onNext(ReturnedReviews movieReviewRequestResponse) {
                        returnedReviewList = movieReviewRequestResponse.getResultsReview();
                        if (isViewAttached()) {
                            getView().setDataReview(returnedReviewList);}
                    }
                });
    }
    public void addMovieToRealm(Realm realm, SharedPreferences sharedPreferences) {
        if (LOADSTATE > 1 && movieID > 0) {
                sharedPreferences.edit().putInt(String.valueOf(movieID), movieID).commit();
                realm.beginTransaction();
                RealmReturnedMovie returnedMovie = realm.createObject(RealmReturnedMovie.class);
                returnedMovie.setId(movieID);
                RealmMovieInfo movieInfo = realm.copyToRealm(realmMovieInfo);
                returnedMovie.setRealmMovieInfo(movieInfo);
                if (returnedMediaList!=null){
                    for (MovieMedia returnedMedia : returnedMediaList){
                        RealmMovieMedia movieMedia = new RealmMovieMedia();
                        movieMedia.setId(returnedMedia.getId());
                        movieMedia.setName(returnedMedia.getName());
                        movieMedia.setKey(returnedMedia.getKey());
                        RealmMovieMedia realmMovieMedia = realm.copyToRealm(movieMedia);
                        returnedMovie.getRealmMediaList().add(realmMovieMedia);
                    }
                }
                if (returnedReviewList!=null){
                    for (MovieReview returnedReview : returnedReviewList){
                        RealmMovieReview movieReview = new RealmMovieReview();
                        movieReview.setId(returnedReview.getId());
                        movieReview.setAuthor(returnedReview.getAuthor());
                        movieReview.setUrl(returnedReview.getUrl());
                        movieReview.setContent(returnedReview.getContent());
                        RealmMovieReview realmMovieReview = realm.copyToRealm(movieReview);
                        returnedMovie.getRealmReviewList().add(realmMovieReview);
                    }
                }
                realm.commitTransaction();
        }else{ }}
}
