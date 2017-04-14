package movies.new2.amr.apps.moviesnew;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import javax.inject.Inject;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static rx.android.app.AppObservable.bindActivity;

public class ActivityMain extends AppCompatActivity {
    @Inject MovieApplication movieApplication;
    @Inject RxBus rxBus;
    private CompositeSubscription subscriptions;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private SearchFragment retainedFragment;
    private RealmMovieInfo movieInfo;
    private static final String VIEWSTATE0 = "0";
    private static final String VIEWSTATE1 = "1";
    private static final String VIEWSTATE2 = "2";
    private int movieID;
    FrameLayout fragmentContainer;
    FrameLayout fragmentContainer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        fragmentContainer = (FrameLayout) findViewById(R.id.fragmentContainer);
        fragmentContainer1 = (FrameLayout) findViewById(R.id.fragmentContainer1);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);}
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        ab.setDisplayHomeAsUpEnabled(true);
       fragmentContainer = (FrameLayout) findViewById(R.id.fragmentContainer);
       fragmentContainer1 = (FrameLayout) findViewById(R.id.fragmentContainer1);
        if (savedInstanceState != null) {
            if(!(getResources().getBoolean(R.bool.dual_pane))) {
                fragmentContainer1.setVisibility(View.VISIBLE);}
            if(getResources().getBoolean(R.bool.dual_pane)) {
                Log.d("FRAG", "Looking up previous frag");
                if (getSupportFragmentManager().findFragmentByTag(VIEWSTATE2) != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, getSupportFragmentManager().findFragmentByTag(VIEWSTATE1))
                            .replace(R.id.fragmentContainer1, getSupportFragmentManager().findFragmentByTag(VIEWSTATE2))
                            .commit();
                } else {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentContainer, getSupportFragmentManager().findFragmentByTag(VIEWSTATE1))
                                .replace(R.id.fragmentContainer1, new DetailsFrag(), VIEWSTATE2)
                                .commit();}
            }else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, getSupportFragmentManager().findFragmentByTag(VIEWSTATE1))
                        .commit();
            }
        } else if (savedInstanceState == null) {
            Log.d("FRAG", "Creating new Frag");
            if(getResources().getBoolean(R.bool.dual_pane)){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new SearchFragment(), VIEWSTATE1)
                        .replace(R.id.fragmentContainer1, new DetailsFrag(),VIEWSTATE2)
                        .addToBackStack(VIEWSTATE2)
                        .commit();
            }else{
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new SearchFragment(),VIEWSTATE1)
                        .commit();
            }}}
    @Override
    public void onStart() {
        super.onStart();
       getComponent().inject(this);
        subscriptions = new CompositeSubscription();
        subscriptions
                .add(bindActivity(this, rxBus.toObserverable())//
                        .subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object event) {
                                if (event instanceof RealmMovieInfo) {
                                    movieInfo = (RealmMovieInfo) event;
                                    movieID = movieInfo.getId();
                                    initDetailFrag();
                                }else if (event instanceof RealmReturnedMovie){
                                    movieID = ((RealmReturnedMovie) event).getId();
                                    initDetailFrag();
                                }}
                        }));
    }
    private void initDetailFrag() {
        if (getResources().getBoolean(R.bool.dual_pane)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer1, new DetailsFrag(), VIEWSTATE2)
                    .addToBackStack(VIEWSTATE2)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer1, new DetailsFrag(), VIEWSTATE2)
                    .addToBackStack(VIEWSTATE2)
                    .commit();
            fragmentContainer.setVisibility(View.GONE);
            fragmentContainer1.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        subscriptions.unsubscribe();
    }
    public AppComponent getComponent(){
        MovieApplication movieApplication = (MovieApplication) getApplication();
         return movieApplication.getAppComponent();
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
            if (!(getResources().getBoolean(R.bool.dual_pane))) {
                fragmentContainer.setVisibility(View.VISIBLE);}
        } else {
            super.onBackPressed();}
    }
    public void openDrawr(){
        mDrawer.openDrawer(GravityCompat.START);
    }
    public RealmMovieInfo getCurrentResult(){
        return movieInfo;
    }
    public int getMovieID(){
        return  movieID;}
}
