package com.example.aman.youtubeplayer;

import android.content.SharedPreferences;
import android.content.Context;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.pm.ActivityInfo;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import android.widget.Toast;
import android.widget.VideoView;
import android.net.Uri;
import android.media.MediaPlayer;
import android.widget.ImageButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;

import static android.provider.Contacts.SettingsColumns.KEY;
import static android.provider.MediaStore.Audio.Playlists.Members.PLAYLIST_ID;


public class YoutubePlayer extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener{
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    //SharedPreferences sharedpreferences;


    long timestamp = System.currentTimeMillis();
    private YouTubePlayer player;
    public static final String DEVELOPER_KEY = "AIzaSyCUQaUOpdlDY8Q92PSzNYStksS1_V3aigI";
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private static String PlayList_ID = "";
    YouTubePlayerFragment myYouTubePlayerFragment;
    //Recieving URL from MainActivity and assigning to PlayList ID
    //    It is made static so that this method loads first
    public static void geturl(String url) {
        PlayList_ID = url;
    }
    public int c=0;
    private static final String APPLICATION_NAME = "YoutubePlayer";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    public void endi(){
        /*long elapsedTime = System.currentTimeMillis() - timestamp;
        if(sharedpreferences.contains("time")){
            sharedpreferences.edit().remove("time");
        }

        sharedpreferences.edit().putLong("time", elapsedTime);
        sharedpreferences.edit().commit();
        long time1=sharedpreferences.getLong("time",0);
        Toast.makeText(this, time1+"", Toast.LENGTH_LONG).show();
        */
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        endi();
    }
    private void showMessage_v3() {

        final Dialog dialog =new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.custom2);
        dialog.setTitle("Title...");
        dialog.show();
        dialog.setCancelable(false);

        final ImageButton imageViewl=(ImageButton) dialog.findViewById(R.id.imageViewl);
        final ImageButton imageViewr=(ImageButton) dialog.findViewById(R.id.imageViewr);

        final VideoView simpleVideoView = (VideoView) dialog.findViewById(R.id.simpleVideoView);
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.v3));
        simpleVideoView.start();

        simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                imageViewl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        player.play();
                    }
                });

                imageViewr.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        endi();
                    }

                });

            }
        });
    }


    private void showMessage_v2() {

        final Dialog dialog =new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.custom3);
        dialog.setTitle("Title...");
        dialog.show();
        dialog.setCancelable(false);

        final ImageButton imageViewl=(ImageButton) dialog.findViewById(R.id.imageViewl);
        final VideoView simpleVideoView = (VideoView) dialog.findViewById(R.id.simpleVideoView);
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.v2));
        simpleVideoView.start();

        simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                imageViewl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        player.play();
                    }
                });

            }
        });
    }


    private void showMessage_v1(String vId) {
        MediaPlayer mp;

        final Dialog dialog =new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.custom3);
        dialog.setTitle("Title...");
        dialog.show();
        dialog.setCancelable(false);

        final ImageButton imageViewl=(ImageButton) dialog.findViewById(R.id.imageViewl);
        //int rawId = getResources().getIdentifier(vId, "raw", getPackageName());

        final VideoView simpleVideoView = (VideoView) dialog.findViewById(R.id.simpleVideoView);
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.v1));
        simpleVideoView.start();

        simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                imageViewl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        player.play();
                    }
                });

            }
        });
    }

    private void showMessage2() {

        final Dialog dialog =new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.custom);
        dialog.setCancelable(false);
        dialog.setTitle("Title...");
        dialog.show();
        VideoView simpleVideoView = (VideoView) dialog.findViewById(R.id.simpleVideoView);
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videoplayback));
        simpleVideoView.start();
        simpleVideoView.postDelayed(new Runnable() {

            @Override
            public void run() {
                endi();
            }
        }, 22500);
        simpleVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                endi();

            }
        });
    }
    private void showMessage1() {

        final Dialog dialog =new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.custom2);
        dialog.setTitle("Title...");
        dialog.show();
        dialog.setCancelable(false);

        final ImageButton imageViewl=(ImageButton) dialog.findViewById(R.id.imageViewl);
        final ImageButton imageViewr=(ImageButton) dialog.findViewById(R.id.imageViewr);
        final VideoView simpleVideoView = (VideoView) dialog.findViewById(R.id.simpleVideoView);
        simpleVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videoplayback));
        simpleVideoView.start();
        simpleVideoView.postDelayed(new Runnable() {

            @Override
            public void run() {
                simpleVideoView.pause();
                imageViewl.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        player.play();
                    }
                });

                imageViewr.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        endi();
                    }

                });
            }
        }, 25000);
    }



        private final class MyPlaylistEventListener implements YouTubePlayer.PlaylistEventListener {

        @Override
        public void onNext() {
        }

        @Override
        public void onPlaylistEnded() {

            showMessage2();
        }

        @Override
        public void onPrevious() {
        }

    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        @Override
        public void onLoading() {
        }

        @Override
        public void onLoaded(String s) {
        }

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onVideoStarted() {
            player.setFullscreen(true);
            if(c==0)
            {
                player.pause();
                showMessage_v1("vi");
            }
        }

        @Override
        public void onVideoEnded() {
            player.pause();
            c+=1;
            switch (c){
                case 1:
                    showMessage_v2();
                    break;
                case 2:
                    showMessage_v3();
                    break;
            }
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
        }
    }
    private MyPlaylistEventListener playlistEventListener;
    private MyPlayerStateChangeListener playerStateChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<String> Playlist_items=new ArrayList<String>();
      //  sharedpreferences = getSharedPreferences(MY_PREFS_NAME,
        //        Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // Make to run your application only in portrait mode
        setContentView(R.layout.youtube_player);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;

        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            // Where you get exception write that code inside this.
        }
        myYouTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
                .findFragmentById(R.id.youtubeplayerfragment);
        myYouTubePlayerFragment.initialize(DEVELOPER_KEY, this);
        playlistEventListener = new MyPlaylistEventListener();
        playerStateChangeListener= new MyPlayerStateChangeListener();

    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            Toast.makeText(this, "Youtube Player Initializing can't be done",
                    Toast.LENGTH_LONG).show();
        }
    }/*
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public HttpResponse http(String url, String body) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(body);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse result = httpClient.execute(request);

            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            try {
                JSONParser parser = new JSONParser();
                Object resultObject = parser.parse(json);

                if (resultObject instanceof JSONArray) {
                    JSONArray array=(JSONArray)resultObject;
                    for (Object object : array) {
                        JSONObject obj =(JSONObject)object;
                        System.out.println(obj.get("example"));
                        System.out.println(obj.get("fr"));
                    }

                }else if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;
                    System.out.println(obj.get("example"));
                    System.out.println(obj.get("fr"));
                }

            } catch (Exception e) {
            }

        } catch (IOException ex) {
        }
        return null;
    }*/

    public static JSONObject getJSONObjectFromURL() throws IOException, JSONException, ParseException {
        HttpURLConnection urlConnection = null;
        String s="https://www.googleapis.com/youtube/v3/playlistItems?part=contentDetails&maxResults=50&playlistId="+PlayList_ID+"&key=AIzaSyC2_YRcTE9916fsmA0_KRnef43GbLzz8m0";
        URL url = new URL(s);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        //System.out.println("JSON: " + jsonString);

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonString);
      return json;
    }


    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        /*List<String> Playlist_items_new=new ArrayList<String>();;
        YouTube youtubeService = null;
        try {
            youtubeService = getService();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        YouTube.PlaylistItems.List request = null;
        try {
            request = youtubeService.playlistItems()
                    .list("snippet");
            PlaylistItemListResponse response = request.setPlaylistId(PlayList_ID)
                    .execute();
            JSONArray ja = (JSONArray) response.get("items");

            // iterating phoneNumbers
            Iterator<Map.Entry> itr1 ;
            Iterator itr2 = ja.iterator();

            while (itr2.hasNext())
            {
                itr1 = ((Map) itr2.next()).entrySet().iterator();
                while (itr1.hasNext()) {
                    Map.Entry pair = itr1.next();
                    if(pair.getKey()=="id")
                        Playlist_items_new.add((String)pair.getKey());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

*/
       /* List<String> Playlist_items_new=new ArrayList<String>();;

        try {
            JSONObject response = getJSONObjectFromURL();
            JSONArray ja = (JSONArray) response.get("items");
            for(int i=0; i<ja.size(); i++) {
                JSONObject dataObj1 = (JSONObject)ja.get(i);
                JSONObject dataObj =(JSONObject)dataObj1.get("contentDetails");
                String id = (String)dataObj.get("videoId");
                //System.out.println(id);
                Playlist_items_new.add(id);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        player.setPlaylistEventListener(playlistEventListener);
        this.player = player;
        player.setPlayerStateChangeListener(playerStateChangeListener);
        if(!wasRestored)
        {
            //player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
            player.setFullscreen(true);

            player.setFullscreenControlFlags(0);
            //if(Playlist_items_new.isEmpty())
            player.loadPlaylist(PlayList_ID);
            //else{
               // System.out.println(Playlist_items_new);
              ///  player.loadVideos(Playlist_items_new);


        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }


    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtubeplayerfragment);
    }



}
