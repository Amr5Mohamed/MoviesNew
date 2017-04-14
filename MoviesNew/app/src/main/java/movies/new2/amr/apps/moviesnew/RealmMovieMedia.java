package movies.new2.amr.apps.moviesnew;

import io.realm.RealmObject;

public class RealmMovieMedia extends RealmObject{
    private String id;
    private String name;
    private String key;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
}
