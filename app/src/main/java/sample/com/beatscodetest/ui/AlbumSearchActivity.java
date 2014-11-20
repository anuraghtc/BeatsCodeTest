package sample.com.beatscodetest.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import sample.com.beatscodetest.R;

/*
* Activity to query and show album search results.
* */
public class AlbumSearchActivity extends ActionBarActivity {

    private static final String TAG = AlbumSearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_search);
        if (savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            Fragment container = manager.findFragmentById(R.layout.fragment_previous_serch_keyword);
            manager.beginTransaction()
                    .add(R.id.container, container)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_album_search, menu);
/*
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search:
                startSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startSearch() {
        ShowSearchResultFragment newFragment = new ShowSearchResultFragment();
        newFragment.invokeAlbumSearchRequest("Justin");
//        Bundle args = new Bundle();
//        args.putString(ShowSearchResultFragment.SEARCH_KEYORD, "Justin");
//        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //Replace previous fragment with new fragment
        transaction.replace(R.id.container, newFragment);
//        transaction.addToBackStack(null);
        transaction.commit();


    }


}
