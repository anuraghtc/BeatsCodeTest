package sample.com.beatscodetest.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import sample.com.beatscodetest.Config;
import sample.com.beatscodetest.R;

/**
 * Get search result for user query.
 */
public class AlbumSearchAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    // list of items served by this adapter
    private ArrayList<AlbumSearchResultItem> mItems = new ArrayList<AlbumSearchResultItem>();
    private SearchResultInfo mSearchResultInfo = new SearchResultInfo(0,0);

    private ImageLoader mImageLoader;

    private static String TAG = AlbumSearchAdapter.class.getSimpleName();

    public AlbumSearchAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        mImageLoader = NetworkRequestHandler.getInstance(mContext).getImageLoader();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return position >= 0 && position < mItems.size() ? mItems.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // check if the view already exists
        // if so, no need to inflate and findViewById again!
        if (convertView == null) {

            // Inflate the custom row layout from your XML.
            convertView = mInflater.inflate(R.layout.album_search_list_item, null);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.thumbnailImageView = (NetworkImageView) convertView.findViewById(R.id.img_thumbnail);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.text_title);
            holder.authorTextView = (TextView) convertView.findViewById(R.id.text_artist);

            // hang onto this holder for future use
            convertView.setTag(holder);
        } else {

            // skip all the expensive inflation/findViewById
            // and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }

        AlbumSearchResultItem item = (AlbumSearchResultItem) getItem(position);
        if (item.getId().isEmpty() == false) {
            String albumID = item.getId();
            String imageURL = String.format(Locale.US, Config.IMAGE_LOOKUP_URI, albumID, Config.ALBUM_IMAGE_SIZE.thumb);
            Log.d(TAG, "Album image URL: " + imageURL);
            holder.thumbnailImageView.setImageUrl(imageURL, mImageLoader);
        }
        holder.titleTextView.setText(item.getDisplay());
        holder.authorTextView.setText(item.getDetail());

        return convertView;
    }

    /*
    * Update current search result with new data
    * @result new search data
    */
    public void updateItems(JSONObject result) {
        if (result != null) {
            JSONArray data = result.optJSONArray("data");
            JSONObject info = result.optJSONObject("info");

            if(info != null){
                mSearchResultInfo.setOffset(info.optInt("offset"));
                mSearchResultInfo.setTotal(info.optInt("total"));
            }

            if(data != null && data.length()>0){
                int count = data.length();
                for (int i =0; i< count; i++) {
                    if(data.opt(i)!= null){
                        JSONObject jsonDataObj = (JSONObject)data.opt(i);
                        AlbumSearchResultItem newItem = new AlbumSearchResultItem(jsonDataObj.optString("id"));
                        newItem.setDisplay(jsonDataObj.optString("display"));
                        newItem.setDetail(jsonDataObj.optString("detail"));
                        mItems.add(newItem);
                        Log.d(TAG, "Adding new item: " + newItem.getId() );
                    }

                }
            }

        }
        notifyDataSetChanged();
    }

    /*
    * This method will reset the adapter with search result.
    * It must be called when a new search is performed.
    * */

    public void resetAdapter(){
        mItems.clear();
        mSearchResultInfo.reset();
        notifyDataSetChanged();
    }

    // this is used so you only ever have to do
    // inflation and finding by ID once ever per View
    private static class ViewHolder {
        public NetworkImageView thumbnailImageView;
        public TextView titleTextView;
        public TextView authorTextView;
    }
}
