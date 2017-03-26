package coolcats.se.homecontrol;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by nim on 2017-03-26.
 */

public class LedFragment extends Fragment {

    private Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    private View root;
    private MainActivity mainActivity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root == null){
            root = inflater.inflate(R.layout.led_layout, container, false);
        }
        root.findViewById(R.id.on).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOn();
            }
        });

        root.findViewById(R.id.off).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOff();
            }
        });

        return root;
    }

    private void sendOff(){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String url = "http://" + mainActivity.getIp()+":"+mainActivity.getPort()+"/executetest";
                Log.i("Rasmus","url : " + url);
                try {
                    post(url,false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String aVoid) {
                ((TextView)root.findViewById(R.id.response)).setText(aVoid);
            }
        }.execute();
    }




    private void sendOn(){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String url = "http://" + mainActivity.getIp()+":"+mainActivity.getPort()+"/executetest";
                Log.i("Rasmus","url : " + url);
                try {
                    post(url,true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String aVoid) {
                ((TextView)root.findViewById(R.id.response)).setText(aVoid);
            }
        }.execute();
    }




    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    String post(String url, boolean on) throws IOException {
        SenderObject object = new SenderObject();
        object.setAction("led");
        List<String> list = new ArrayList<>();
        String string = on?"on":"off";
        list.add(string);


        RequestBody body = RequestBody.create(JSON, gson.toJson(object));
        Request request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Log.i("Rasmus","response:" + response.body().string());
        return response.body().string();
    }
}
