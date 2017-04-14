package movies.new2.amr.apps.moviesnew;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmReturnedMovie extends RealmObject {
    @PrimaryKey
    private int id;
    private RealmMovieInfo realmMovieInfo;
    private RealmList<RealmMovieMedia> realmMediaList;
    private RealmList<RealmMovieReview> realmReviewList;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public RealmList<RealmMovieMedia> getRealmMediaList() {
        return realmMediaList;
    }
    public void setRealmMediaList(RealmList<RealmMovieMedia> realmMediaList) {
        this.realmMediaList = realmMediaList;}
    public RealmList<RealmMovieReview> getRealmReviewList() {
        return realmReviewList;
    }
    public void setRealmReviewList(RealmList<RealmMovieReview> realmReviewList) {
        this.realmReviewList = realmReviewList;}
    public RealmMovieInfo getRealmMovieInfo() {
        return realmMovieInfo;
    }
    public void setRealmMovieInfo(RealmMovieInfo realmMovieInfos) {
        this.realmMovieInfo = realmMovieInfos;}
}
