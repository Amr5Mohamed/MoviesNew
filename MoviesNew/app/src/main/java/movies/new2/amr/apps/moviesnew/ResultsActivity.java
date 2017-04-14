package movies.new2.amr.apps.moviesnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class ResultsActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        handleIntent(getIntent());}
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }
   private void handleIntent(Intent intent) {}
}
