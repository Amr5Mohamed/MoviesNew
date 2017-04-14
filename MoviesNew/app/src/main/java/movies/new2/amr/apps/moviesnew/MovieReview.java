package movies.new2.amr.apps.moviesnew;

import com.google.gson.annotations.Expose;

public class MovieReview {
    @Expose private String id;
    @Expose private String author;
    @Expose private String content;
    @Expose private String url;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
