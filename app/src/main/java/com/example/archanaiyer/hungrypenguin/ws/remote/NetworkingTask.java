package com.example.archanaiyer.hungrypenguin.ws.remote;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Knock on 4/20/16.
 */
public class NetworkingTask extends AsyncTask<Object, Void, Void> {


    private String TAG = "NetworkingTask";
    private String mUrl;
    private boolean mShowLoader;
    private NetworkConstants.METHOD_TYPE mHttpMethod;
    private NetworkConstants.REQUEST_TYPE mRequestType;
    private Context mContext;
    private String mLoadingMessage;
    private ProgressDialog mProgressDialog;
    private String response;

    public NetworkingTask(String url, boolean showLoader,
                          NetworkConstants.METHOD_TYPE httpMethod, NetworkConstants.REQUEST_TYPE requestType,
                          Context context, String loadingMessage){
        this.mUrl = url;
        this.mShowLoader = showLoader;
        this.mHttpMethod = httpMethod;
        this.mRequestType = requestType;
        this.mContext = context;
        this.mLoadingMessage = loadingMessage;

        if(this.mShowLoader) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage(this.mLoadingMessage);
            mProgressDialog.setCancelable(false);
        }


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        try {
            if(this.mShowLoader) {
                this.mProgressDialog.show();
            }
        }
        catch(Exception e) {
            Log.i(TAG, "Pre execute exception");
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Object... params) {

        JSONObject reqObject = null;
        if(params != null && params.length > 0)
            reqObject = (JSONObject)params[0];


        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        try {
            if (this.mProgressDialog.isShowing() && this.mShowLoader)
                this.mProgressDialog.dismiss();
        } catch (Exception e) {
            Log.i(TAG, "Post execute exception");
            e.printStackTrace();
        }
    }

}
