package movies.new2.amr.apps.moviesnew;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieMedia {
    @Expose private String id;
    @SerializedName("iso_639_1")
    @Expose private String iso6391;
    @Expose private String key;
    @Expose private String name;
    @Expose private String site;
    @Expose private int size;
    @Expose private String type;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIso6391() {
        return iso6391;
    }
    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {this.size = size;}
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
