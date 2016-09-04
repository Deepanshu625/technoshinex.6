package com.example.viking.tsx6.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viking.tsx6.MainActivity;
import com.example.viking.tsx6.R;
import com.example.viking.tsx6.for_login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by viking on 17/8/16.
 */
public class login extends Fragment {

    String Username,Password;
    private EditText user,pass;
    Context context;
    Dialog dialog;

    public String newInstance(String username,String password,Context context,Dialog dialog) {
        Username=username;
        Password=password;
        this.context = context;
        this.dialog = dialog;

        new on_start().execute();
        System.out.println("Username:" + Username);
        return Username;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
    private class on_start extends AsyncTask<Void, Void, String>
    {
       // private final ProgressDialog dialog = new ProgressDialog(getActivity());
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
       //     dialog.setMessage("Retrieving Question.......");
       //     dialog.show();
        }
        @Override
        protected String doInBackground(Void... params)
        {
            for_login for_login = new for_login("","");
            String line, response = "";
            String POST_PARAMS = "Username=" +Username ;
            POST_PARAMS += "&Password=" +Password;
            if(Config.SESS_ID!=null) {
                POST_PARAMS += "&sess_id=" + Config.SESS_ID;
            }
            Log.e("POST PARAMS",POST_PARAMS);

            if(haveNetworkConnection())
            {
                try {

                    Log.e("aasfasf","asfasf");

                    URL url = new URL("http://192.168.0.102/login/login.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
                        //conn.setRequestProperty("Connection", "close");
                    }
                    //conn.setRequestProperty("Accept-Encoding", "");
                    conn.setReadTimeout(15000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);

                    // For POST only - BEGIN
                    conn.setDoOutput(true);
                    OutputStream os = conn.getOutputStream();
                    os.write(POST_PARAMS.getBytes());
                    os.flush();
                    os.close();
                    // For POST only - END
                    //conn.connect();

                    int responseCode = conn.getResponseCode();

                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        Log.e("aasfasf","bbbbbbbbbbbbbb");
// read the response
                        System.out.println("Response Code: " + conn.getResponseCode());
                        //InputStream in = new BufferedInputStream(conn.getInputStream());

                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        while ((line = br.readLine()) != null) {
                            response += line;
                        }
                    }
                    else
                    {
                        response = "server";
                    }

                    Log.e("aasfasf","aaaaaaaaaaaa");
                    //System.out.println(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                response = "no internet";
            }

            return response;
        }
        void showToast(String text) {
            Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Log.e("RESPONSE", response);
//         //   dialog.dismiss();
            if(response.equals("no internet"))
            {
                Toast.makeText(context, "No internet Connection", Toast.LENGTH_SHORT).show();
            }
            else if (response.equals("server"))
            {
                Toast.makeText(context, "Can't connect to server", Toast.LENGTH_SHORT).show();
            }
            else
            {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    boolean success=jsonObject.getBoolean("success");
                    String message=jsonObject.getString("message");
                    if(success) {
                        Config.SESS_ID=jsonObject.getString("sess_id");
                        showToast(message);
                        setUsername(jsonObject.getString("user"));
                        dialog.dismiss();
                    }
                    else {
                        showToast(message);
                        setUsername(null);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }




//            if(response.equals("ok"))
//            {
//                setUsername(Username);
//            }
//            if(response.equals("no"))
//            {
//                setUsername(null);
//            }

        }
    }
    public void setUsername(String username)
    {
        Username = username;

        //MainActivity mainActivity = new MainActivity();
        //mainActivity.getUsername(Username);
        MainActivity.getUsername(Username);

        System.out.println("Username:"+Username);
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
