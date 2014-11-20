package sample.com.beatscodetest.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Handle network calls for search and album images.
 */
public class NetworkRequestHandler {

    private static NetworkRequestHandler mInstance;//Singleton instance for handling network calls
    private RequestQueue mRequestQueue;//volley request queue for handling network request
    private ImageLoader mImageLoader; //Volley network image loader for loading album thumnail
    private static Context mCtx;//Application context


    private static final int NETWORK_CACHE_SIZE = 1024 * 1024;

    private NetworkRequestHandler(Context context) {

        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(100);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized NetworkRequestHandler getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NetworkRequestHandler(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            //Use application context
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }


}
