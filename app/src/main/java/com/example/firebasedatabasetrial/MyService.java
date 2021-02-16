package com.example.firebasedatabasetrial;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyService extends Service
{
    private static CallReceiver callReceiver;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    public IBinder onBind(Intent intent)
    {
        //Toast.makeText(AppController.getInstance().getApplicationContext(),intent.getStringExtra("number"),Toast.LENGTH_SHORT).show();
        return null;
    }

    @Override
    public void onCreate()
    {
        //registerScreenOffReceiver();
        //checkNumberInDatabase(AppController.getInstance().getApplicationContext(),intent.getStringExtra("number"));
      // Toast.makeText(AppController.getInstance().getApplicationContext(),"yo",Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null)
        {
            checkNumberInDatabase(AppController.getInstance().getApplicationContext(),intent.getStringExtra("number"));
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
       /* unregisterReceiver(callReceiver);
        callReceiver = null;*/
    }

    private void registerScreenOffReceiver()
    {
        callReceiver = new CallReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
              //  checkNumberInDatabase(context,intent.getStringExtra("number"));
                super.onReceive(context, intent);
                //Log.d(TAG, "ACTION_SCREEN_OFF");
                // do something, e.g. send Intent to main app
            }
        };
       /* IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(callReceiver, filter);*/
    }

    public void checkNumberInDatabase(final Context ctx, final String number)
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("students");
        myRef.orderByChild("contact").equalTo(number).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String name= dataSnapshot.child("name").getValue().toString();
                Toast.makeText(ctx,"Call By "+name, Toast.LENGTH_SHORT).show();

                /*startService(new Intent(ctx, FloatingViewService.class));*/
                Intent service = new Intent(ctx, FloatingViewService.class);
                service.putExtra("number",number);
                service.putExtra("name",name);
                ctx.startService(service);


             /*   final Intent intent = new Intent(ctx, MyCustomDialog.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("phone_no",number);
                intent.putExtra("name",name);

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ctx.startActivity(intent);
                    }
                },2000);*/


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
