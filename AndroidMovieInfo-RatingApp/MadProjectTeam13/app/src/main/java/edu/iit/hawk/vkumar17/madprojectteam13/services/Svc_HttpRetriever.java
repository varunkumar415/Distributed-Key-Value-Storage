package edu.iit.hawk.vkumar17.madprojectteam13.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;

import edu.iit.hawk.vkumar17.madprojectteam13.io.IO_FlushedInputStream;
import edu.iit.hawk.vkumar17.madprojectteam13.utils.Utils;


public class Svc_HttpRetriever {

    private DefaultHttpClient httpClient = new DefaultHttpClient();

    public String retrieve(String url){
        HttpGet getRequest = new HttpGet(url);
        try{
            HttpResponse getResponse = httpClient.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if(statusCode != HttpStatus.SC_OK)
                Log.w(getClass().getSimpleName(),"Error " + statusCode + " for Url " + url );

            HttpEntity responseEntity = getResponse.getEntity();

            if(responseEntity != null){
                return EntityUtils.toString(responseEntity);
            }
        }
        catch (IOException e){
            getRequest.abort();
            e.printStackTrace();
            Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }
        return null;
    }

    public InputStream retrieveStream(String url)
    {
        HttpGet getRequest = new HttpGet(url);
        try{
            HttpResponse getResponse = httpClient.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if(statusCode != HttpStatus.SC_OK)
                Log.w(getClass().getSimpleName(),"Error " + statusCode + " for Url " + url );

            HttpEntity responseEntity = getResponse.getEntity();

            if(responseEntity != null){
                return responseEntity.getContent();
            }
        }
        catch (IOException e){
            getRequest.abort();
            Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
        }
        return null;
    }

    public Bitmap retrieveBitmap(String url) throws Exception{
        InputStream inputStream = null;
        try {
            inputStream = this.retrieveStream(url);
            final Bitmap bitmap = BitmapFactory.decodeStream(new IO_FlushedInputStream(inputStream));
            return bitmap;
        }
        finally {
            Utils.closeStreamQuietly(inputStream);
        }
    }

}
