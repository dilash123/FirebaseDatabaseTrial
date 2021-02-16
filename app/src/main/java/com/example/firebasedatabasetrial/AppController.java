package com.example.firebasedatabasetrial;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDelegate;


import com.google.firebase.FirebaseApp;

import java.util.ArrayList;


public class AppController extends Application  {

    public static final String TAG = AppController.class.getSimpleName();



    private Thread.UncaughtExceptionHandler defaultHandler;

    private static AppController mInstance;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    //used to store app screen
    // public ArrayList<DcpVisitorCO> dcpVisitorCOList=new ArrayList<>();

    CallReceiver callReceiver;


    @Override
    public void onCreate()
    {
        super.onCreate();
        //setupActivityListener();

        //callReceiver = new CallReceiver();

        mInstance = this;
        //Glimpse.init(mInstance);


       /* AppUtilities.writeToPref(IConstants.AppStartTime,"");
        AppUtilities.writeToPref(IConstants.AppEndTime,"");*/
       // registerNetworkBroadcastForNougat();
        //callServiceForSeconds(getInstance());
        //scheduleAlarm();
        // this.registerActivityLifecycleCallbacks(activityLifecycleListener);





        //getUserAnalyticsCO

        // registerActivityLifecycleCallbacks(new LifecycleListener());


        //userAnalyticsCOList.add(listener.getUserAnalyticsCO());

        // getUserAnalyticsCOList();
    }



    public static synchronized AppController getInstance() {
        return mInstance;
    }

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(callReceiver, new IntentFilter("com.eduncle.android.SOME_ACTION"));
        }
    }

    public void unregisterReceiver() {
        unregisterReceiver(callReceiver);
    }
    public void logScreenLaunch(String screenName) {
        String eventName = "{name} viewed " + screenName;
        Intent intent = new Intent("com.eduncle.android.SOME_ACTION");
        intent.putExtra("ClassName",eventName);
        sendBroadcast(intent);

        /*arrayList.add(eventName);
        SharedPreferences sharedPreferences = AppController.getInstance().getSharedPreferences(ISysConfig.APP_SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        String json = gson.toJson(arrayList);

        editor.putString("activity", json);
        editor.commit();

        //Gson gson = new Gson();
        String json1 = sharedPreferences.getString("activity", "");
        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> arrayList = gson.fromJson(json1, type);
        Log.e("sdadasd",""+arrayList);*/
    }

    public interface LifecycleListener {
        /**
         * Called right before the application is stopped.
         */
        // UserAnalyticsCO userAnalyticsCo=new UserAnalyticsCO();

        void onApplicationStopped();

        /**
         * Called right after the application has been started.
         */
        void onApplicationStarted();

        /**
         * Called when the application is paused (but still awake).
         */
        void onApplicationPaused();

        /**
         * Called right after the application has been resumed (come to the foreground).
         */
        void onApplicationResumed();


    }



   /* public class MyApplicationActivityLifecycleListener implements ActivityLifecycleCallbacks {
        UserAnalyticsCO userAnalyticsCO =new UserAnalyticsCO();
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if(!activity.getClass().getSimpleName().equalsIgnoreCase("SplashScreen"))
            userAnalyticsCO.startTime=AppUtilities.getCurrentDateTime();
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            userAnalyticsCO.startTime=AppUtilities.getCurrentDateTime();
        }

        @Override
        public void onActivityPaused(Activity activity) {
            userAnalyticsCO.endTime=AppUtilities.getCurrentDateTime();
        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }


    }

    public void addArrayList()
    {}*/





}