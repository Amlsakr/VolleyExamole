package com.example.aml.volleyexamole;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private static final String IMAGE_REQUEST_URL = "https://androidtutorialpoint.com/api/lg_nexus_5x" ;
    private static final String STRING_REQUEST_URL = "http://androidtutorialpoint.com/api/volleyString" ;
    private static final String JSON_OBJECT_REQUEST_URL = "http://androidtutorialpoint.com/api/volleyJsonObject" ;
    private static final String JSON_ARRAY_REQUEST_URL = "http://androidtutorialpoint.com/api/volleyJsonArray" ;

    ProgressDialog progressDialog ;
    private static final String TAG = MainActivity.class.getName();
    private Button stringRequestButton;
    private Button JsonObjectRequestButton;
    private Button JsonArrayRequestButton;
    private Button ImageRequestButton;
    private View showDialogView;
    private TextView outputTextView;
    private ImageView outputImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);

        stringRequestButton = (Button)findViewById(R.id.button_get_string);
        JsonObjectRequestButton = (Button)findViewById(R.id.button_get_Json_object);
        JsonArrayRequestButton = (Button)findViewById(R.id.button_get_Json_array);
        ImageRequestButton = (Button)findViewById(R.id.button_get_image);

        stringRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyStringRequst(STRING_REQUEST_URL);
            }
        });

        JsonObjectRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyJsonRequest(JSON_OBJECT_REQUEST_URL);

            }
        });

        JsonArrayRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyJsonArrayRequest(JSON_ARRAY_REQUEST_URL);

            }
        });

        ImageRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                volleyImageLoader(IMAGE_REQUEST_URL);

            }
        });
    }

    public void  volleyStringRequst (String url) {




        String REQUEST_TAG  = "com.androidtutorialpoint.volleyJsonObjectRequest" ;
        progressDialog.setMessage(" LOADING...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG , response.toString());
                        LayoutInflater li = LayoutInflater.from(MainActivity.this);
                        showDialogView = li.inflate(R.layout.show_dialogue , null);
                        outputTextView =(TextView) showDialogView.findViewById(R.id.text_view_dialog);
                        AlertDialog.Builder aBuilder = new AlertDialog.Builder(MainActivity.this);
                        aBuilder.setView(showDialogView) ;
                        aBuilder.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setCancelable(false)
                                .create();
                        outputTextView.setText(response.toString());
                        aBuilder.show();
                        progressDialog.hide();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG , "Error :" + error.getMessage());
              //  outputTextView.setText(error.getMessage());
                progressDialog.hide();
            }
        }) ;

        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest , REQUEST_TAG);
    }

public void volleyJsonRequest (String url) {

    String REQUEST_TAG  = "com.androidtutorialpoint.volleyJsonObjectRequest" ;
    progressDialog.setMessage("LOADING ...");
    progressDialog.show();
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d(TAG, response.toString());
            LayoutInflater li = LayoutInflater.from(MainActivity.this );
            showDialogView = li.inflate(R.layout.show_dialogue , null);
            outputTextView = (TextView) showDialogView.findViewById(R.id.text_view_dialog);
            AlertDialog.Builder aBuilder = new AlertDialog.Builder(MainActivity.this);
           aBuilder.setView(showDialogView) ;
            aBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setCancelable(false)
                    .create() ;

            outputTextView.setText(response.toString());
            aBuilder.show();
            progressDialog.hide();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            VolleyLog.d(TAG , "ERROR :" +error.getMessage());
            progressDialog.hide();
        }
    }) ;
    AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest , REQUEST_TAG);

}

    public void  volleyJsonArrayRequest (String url) {

        String  REQUEST_TAG = "com.androidtutorialpoint.volleyJsonArrayRequest";
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                showDialogView = li.inflate(R.layout.show_dialogue, null);
                outputTextView = (TextView)showDialogView.findViewById(R.id.text_view_dialog);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setView(showDialogView);
                alertDialogBuilder
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        })
                        .setCancelable(false)
                        .create();
                outputTextView.setText(response.toString());
                alertDialogBuilder.show();
                progressDialog.hide();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d(TAG , "ERROR :" +error.getMessage());
                progressDialog.hide();
            }
        }) ;
AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest , REQUEST_TAG);
    }

    public void volleyImageLoader  (String url  ) {

        ImageLoader imageLoader = AppSingleton.getInstance(getApplicationContext()) .getImageLoader() ;
        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

                if (response.getBitmap() != null) {

                    LayoutInflater li = LayoutInflater.from(MainActivity.this);
                    showDialogView = li.inflate(R.layout.show_dialogue, null);
                    outputImageView = (ImageView)showDialogView.findViewById(R.id.image_view_dialog);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setView(showDialogView);
                    alertDialogBuilder
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .setCancelable(false)
                            .create();
                    outputImageView.setImageBitmap(response.getBitmap());
                    alertDialogBuilder.show();
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Load Error: " + error.getMessage());
            }
        }) ;
    }


    public void VolleyCashRequest (String url) {
       Cache cache = AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache() ;
        Cache.Entry entry = cache.get(url);
        if (entry != null) {

            try {

                String data = new String(entry.data , "UTF-8") ;

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else  {

        }

    }

    public void VolleyinvalidateCache (String url) {

        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().invalidate(url ,true);
    }

public void VolleyDeleteCach (String url) {
    AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().remove(url);
}

    public void volleyClearCache (String url) {
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
    }
}
