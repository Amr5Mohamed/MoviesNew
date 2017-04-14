package movies.new2.amr.apps.moviesnew;

import android.os.Bundle;
import com.hannesdorfmann.mosby.mvp.viewstate.RestoreableViewState;

public class DetailsFragViewState implements RestoreableViewState<DetailsFragView> {

    private final String KEY_STATE = getClass().getName().toString() + "currentState";
    private final int STATE_SHOWING_SEARCH_LIST = 0;
    private final int STATE_SHOWING_LOADING = 1;
    private int currentState = 0;
    @Override
    public void saveInstanceState(Bundle out) {
        out.putInt(KEY_STATE, currentState);
    }
    @Override
    public RestoreableViewState<DetailsFragView> restoreInstanceState(Bundle in) {
        currentState = in.getInt(KEY_STATE);
        return restoreInstanceState(in);
    }
    @Override
    public void apply(DetailsFragView detailsFragView, boolean b) {
        if (currentState == STATE_SHOWING_SEARCH_LIST) {
            detailsFragView.showSearchList();}
        else {detailsFragView.showLoading();}
    }
    public void setStateShowSearchList() {
        currentState = STATE_SHOWING_SEARCH_LIST;
    }
    public void setStateShowLoading() {
        currentState = STATE_SHOWING_LOADING;
    }
}

