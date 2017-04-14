package movies.new2.amr.apps.moviesnew;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ReturnedReviews {
    @Expose private int id;
    @Expose private int page;
    @SerializedName("results")
    @Expose private List<MovieReview> resultsReview = new ArrayList<>();
    @SerializedName("total_pages")
    @Expose private int totalPages;
    @SerializedName("total_results")
    @Expose private int totalResults;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public List<MovieReview> getResultsReview() {
        return resultsReview;
    }
    public void setResultsReview(List<MovieReview> results) {
        this.resultsReview = results;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
