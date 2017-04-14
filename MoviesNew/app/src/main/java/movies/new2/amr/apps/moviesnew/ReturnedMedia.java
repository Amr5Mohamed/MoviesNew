package movies.new2.amr.apps.moviesnew;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ReturnedMedia {
        @Expose private int id;
        @SerializedName("results")
        @Expose private List<MovieMedia> resultsMedia = new ArrayList<>();
        public int getId() {
                return id;
        }
        public void setId(int id) {
                this.id = id;
        }
        public List<MovieMedia> getResultsMedia() {
                return resultsMedia;
        }
        public void setResultsMedia(List<MovieMedia> resultsMedia) {
                this.resultsMedia = resultsMedia;}
}
