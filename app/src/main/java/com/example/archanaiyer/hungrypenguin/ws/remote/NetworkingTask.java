package com.example.archanaiyer.hungrypenguin.ws.remote;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.net.URI;

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

//    private String establishConnection(JSONObject namevaluepairs) {
//        String response_str = "";
//        try {
//            HttpParams httpParameters = new BasicHttpParams();
//            int timeoutConnection = 10000;
//            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
//            int timeoutSocket = 60000;
//            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//
//            HttpClient client = new DefaultHttpClient(httpParameters);
//            HttpResponse response = null;
//            switch (mHttpMethod) {
//                case POST:
//                    HttpPost mPost = new HttpPost(mUrl);
//                    mPost.setEntity(new StringEntity(namevaluepairs.toString(), Xml.Encoding.UTF_8.toString()));
//                    mPost.setHeader("Content-type", "application/json");
//                    response = client.execute(mPost);
//                    break;
//
//                case GET:
//                    HttpGet mGet = new HttpGet(mUrl);
//                    URI uri = new URI(mUrl);
//                    mGet.setURI(uri);
//                    response = client.execute(mGet);
//                    break;
//
//                case DELETE:
//                    HttpDelete mDelete = new HttpDelete(mUrl);
//                    URI uri1 = new URI(mUrl);
//                    mDelete.setURI(uri1);
//                    response = client.execute(mDelete);
//                    break;
//
//                case PUT:
//                    HttpPut mPut = new HttpPut(mUrl);
//                    mPut.setEntity(new StringEntity(namevaluepairs.toString(), Encoding.UTF_8.toString()));
//                    mPut.setHeader("Content-type", "application/json");
//                    response = client.execute(mPut);
//                    break;
//
//                default:
//                    break;
//            }
//
//            int responseCode = response.getStatusLine().getStatusCode();
////             System.out.println("Response Code: " + responseCode);
//            switch(responseCode)
//            {
//
//                case HttpURLConnection.HTTP_NO_CONTENT:
//                    response_str = "No Content";
//                    break;
//
//                default:
//                    InputStream is = response.getEntity().getContent();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//                    StringBuilder str = new StringBuilder();
//                    String line = null;
//                    while ((line = reader.readLine()) != null) {
//                        str.append(line + "\n");
//                    }
//                    is.close();
//
//                    response_str = str.toString();
//                    break;
//
//            }
//
//        } catch (ClientProtocolException e) {
//            response_str = "Network Error" + e.getMessage();
//            LogUtils.LOGE(TAG, response_str);
//            // e.printStackTrace();
//        } catch (SocketTimeoutException e) {
//            response_str = "Network Error" + e.getMessage();
//            LogUtils.LOGE(TAG, response_str);
//            // e.printStackTrace();
//        } catch (ConnectTimeoutException e) {
//            response_str = "Network Error" + e.getMessage();
//            LogUtils.LOGE(TAG, response_str);
//            // e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//            response_str = "Network Error" + e.getMessage();
//            LogUtils.LOGE(TAG, response_str);
//            // e.printStackTrace();
//        } catch (Exception e) {
//            LogUtils.LOGE(TAG, response_str);
//            // e.printStackTrace();
//        }
//
//        // System.out.println("Response:- " + response_str);
//        return response_str;
//    }

}
