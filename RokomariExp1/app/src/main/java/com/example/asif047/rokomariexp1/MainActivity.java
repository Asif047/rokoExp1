package com.example.asif047.rokomariexp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private TextView matchTV;

    Gson gson;

    private Cricket crickets;
    private static final String serverurl= "http://cricapi.com/api/matchCalendar//?unique_id=918033&&apikey=6krnL0YcJrVul5eNZrNee4ogpVF2";

    private String TAG="Home Fragment:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        matchTV= (TextView) findViewById(R.id.match_textview);
        //crickets=new ArrayList<>();

        try
        {
            doGetRequest(serverurl);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }




    void doGetRequest(String url) throws IOException
    {
        OkHttpClient client=new OkHttpClient();

        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                String res=response.body().string();


                Log.d(TAG,response.toString());
                Log.d(TAG,res);






               gson = new Gson();

                crickets=gson.fromJson(res,Cricket.class);

                List<Cricket.Datum>cric=crickets.getData();



                Log.d(TAG,cric.get(0).getName().toString());







            }
        });
    }








}
