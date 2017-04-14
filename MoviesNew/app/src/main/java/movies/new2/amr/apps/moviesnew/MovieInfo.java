package movies.new2.amr.apps.moviesnew;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class MovieInfo {
    @Expose private boolean adult;
    @SerializedName("backdrop_path")
    @Expose private String backdropPath;
    @SerializedName("genre_ids")
    @Expose private List<Integer> genreIds = new ArrayList<Integer>();
    @Expose private int id;
    @SerializedName("original_language")
    @Expose private String originalLanguage;
    @SerializedName("original_title")
    @Expose private String originalTitle;
    @Expose private String overview;
    @SerializedName("release_date")
    @Expose private String releaseDate;
    @SerializedName("poster_path")
    @Expose private String posterPath;
    @Expose private double popularity;
    @Expose private String title;
    @Expose private boolean video;
    @SerializedName("vote_average")
    @Expose private double voteAverage;
    @SerializedName("vote_count")
    @Expose private int voteCount;
    public boolean isAdult() {
        return adult;
    }
    public void setAdult(boolean adult) {
        this.adult = adult;
    }
    public String getBackdropPath() {
        return backdropPath;
    }
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
    public List<Integer> getGenreIds() {
        return genreIds;
    }
    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getOriginalLanguage() {
        return originalLanguage;
    }
    public void setOriginalLanguage(String originalLanguage) {this.originalLanguage = originalLanguage;}
    public String getOriginalTitle() {
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public double getPopularity() {
        return popularity;
    }
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isVideo() {
        return video;
    }
    public void setVideo(boolean video) {
        this.video = video;
    }
    public double getVoteAverage() {
        return voteAverage;
    }
    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
    public int getVoteCount() {
        return voteCount;
    }
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}