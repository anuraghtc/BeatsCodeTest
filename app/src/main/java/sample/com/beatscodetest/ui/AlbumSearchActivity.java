package sample.com.beatscodetest.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import sample.com.beatscodetest.R;

/*
* Activity to query and show album search results.
* */
public class AlbumSearchActivity extends FragmentActivity {

    private static final String TAG = AlbumSearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_search);
        if (savedInstanceState == null) {
            FragmentManager manager = getSupportFragmentManager();
            Fragment fragment = new PreviousSearchViewFragment();
            manager.beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }

        final ImageButton searchButton = (ImageButton)findViewById(R.id.search_button);
        final EditText searchText = (EditText)findViewById(R.id.search_text);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = searchText.getText().toString();
                if(keyword != null && keyword.trim().isEmpty()==false){
                    startSearch(keyword.trim());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    public void startSearch(String keyword) {
        ShowSearchResultFragment newFragment = new ShowSearchResultFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle searchParams = new Bundle();
        searchParams.putString(ShowSearchResultFragment.SEARCH_KEYORD, keyword);
        newFragment.setArguments(searchParams);
        //Replace previous fragment with new fragment
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack("search_result");
        transaction.commit();

    }


}
