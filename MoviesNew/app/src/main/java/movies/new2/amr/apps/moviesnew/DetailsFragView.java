package movies.new2.amr.apps.moviesnew;

import com.hannesdorfmann.mosby.mvp.MvpView;
import java.util.List;

public interface DetailsFragView extends MvpView {
    public void setData(RealmMovieInfo movieInfo);
    public void setRealmData(RealmReturnedMovie realmReturnedMovie);
    public void setDataMedia(List<MovieMedia> resultsMedia);
    public void setDataReview(List<MovieReview> resultsReview);
    public void showLoading();
    public void showError(Throwable e);
    public void showSearchList();
    public RealmMovieInfo getRealmMovieInfo();
}
