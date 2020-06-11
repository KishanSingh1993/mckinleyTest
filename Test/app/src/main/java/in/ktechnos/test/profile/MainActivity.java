package in.ktechnos.test.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import in.ktechnos.test.R;
import in.ktechnos.test.TestApplication;

public class MainActivity extends AppCompatActivity {
    private CircleImageView profileImage;
    private TextView userName,userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
        getUserData();
    }

    private void init(){

         userName = findViewById(R.id.name);
         userEmail = findViewById(R.id.email);
         profileImage = findViewById(R.id.profile_image);
    }

    private void getUserData(){

        getDATA(Request.Method.GET, "https://reqres.in/api/details","getUserData");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getDATA(int requestMehod, final String url,final String requestType) {

        StringRequest stringRequest = new StringRequest(requestMehod, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Resetdata","*****"+requestType+"******************"+"*********"+url+"********************************************************");
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject dataObj = new JSONObject(jsonObject.getString("ad"));
                    String data = dataObj.getString("data");
                    JSONArray jsonArray = new JSONArray(dataObj.getString("data"));
                    int size = jsonArray.length();
                    String company = dataObj.getString("company");
                    userName.setText(company);
                    //Picasso.get().load(imgUrl).into(profileImage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG","##################Error##########Error#####s#######  "+requestType+"  ############# "+"  "+url+" ############################### ## ######");
                Log.d("TAG","Error->"+error.toString());

            }
        }
        ) {


            @Override
            public Request<?> setRetryPolicy(RetryPolicy retryPolicy) {
                retryPolicy = new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*30,(DefaultRetryPolicy.DEFAULT_MAX_RETRIES+4),
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                return super.setRetryPolicy (retryPolicy);
            }



        };

        stringRequest.setShouldCache(false);
        TestApplication.getInstance().addToRequestQueue(stringRequest);
    }
}