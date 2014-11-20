package sample.com.beatscodetest.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Locale;

import sample.com.beatscodetest.Config;
import sample.com.beatscodetest.R;
import sample.com.beatscodetest.model.AlbumSearchAdapter;
import sample.com.beatscodetest.model.NetworkRequestHandler;


/**
 * A placeholder fragment containing a simple view.
 */
public class ShowSearchResultFragment extends android.support.v4.app.ListFragment{
    public static final String SEARCH_KEYORD = "search_keyword";
    private static final String TAG = ShowSearchResultFragment.class.getSimpleName();

    AlbumSearchAdapter mSearchResultdapter;

    public ShowSearchResultFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_album_search, container, false);

        //Setup adapter for list view
        mSearchResultdapter = new AlbumSearchAdapter(getActivity(), inflater);
        ListView searchResultListView = (ListView) rootView.findViewById(R.id.search_result_view);
        searchResultListView.setAdapter(mSearchResultdapter);

        return rootView;

    }

    public void invokeAlbumSearchRequest(String keyword) {

        if (keyword == null || keyword.isEmpty()) return;

        //Make album search
        String serchURL = String.format(Locale.US, Config.ALBUM_SEARCH_URI, keyword, 0);

        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.GET, serchURL, null, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        mSearchResultdapter.updateItems((JSONObject) response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error loading search result data");
                    }
                });

        NetworkRequestHandler.getInstance(this.getActivity()).addToRequestQueue(jsonObjRequest);
    }
}