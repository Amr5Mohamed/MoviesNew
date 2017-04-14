package movies.new2.amr.apps.moviesnew;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

public interface SearchListView extends MvpView {
    public void setData(List<MovieInfo> list);
    public void showLoading();
    public void showError(Throwable e);
    public void showSearchList();
    public void setRealmData(List<RealmReturnedMovie> realmList);
}
