package com.example.aml.volleyexamole;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by aml on 30/04/18.
 */
public class AppSingleton {

    private static AppSingleton appSingletonInstance;
    private RequestQueue requestQueue ;
    private ImageLoader imageLoader ;
    private static Context mcontext ;



    private AppSingleton(Context context) {
        mcontext = context ;
        requestQueue = getRequestQueue();
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

            private final LruCache <String , Bitmap > cache = new LruCache<String, Bitmap>(20) ;
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
cache.put(url , bitmap);
            }
        }) ;
    }
public RequestQueue getRequestQueue () {
    if (requestQueue == null) {
        // getApplicationContext() is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        requestQueue = Volley.newRequestQueue(mcontext.getApplicationContext());
    }
        return requestQueue ;
}
    public static  synchronized AppSingleton getInstance(Context context) {

        if (appSingletonInstance == null) {
            appSingletonInstance = new AppSingleton(context);
        }
        return appSingletonInstance;
    }

    public <T> void addToRequestQueue (Request <T> request , String tag){
        request.setTag(tag);
        getRequestQueue().add(request);

    }
    public ImageLoader getImageLoader (){
        return imageLoader ;
    }

    public  void  cancelPendingRequests (Object tag) {

        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
