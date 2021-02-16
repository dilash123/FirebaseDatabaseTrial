package com.example.firebasedatabasetrial;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class CallReceiver extends PhonecallReceiver {
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onIncomingCallReceived(Context ctx, String number, Date start)
    {
        //Toast.makeText(ctx,"heloo",Toast.LENGTH_SHORT).show();
        Intent service = new Intent(ctx, MyService.class);
        service.putExtra("number",number);
        ctx.startService(service);
        //checkNumberInDatabase(ctx,number);
        //
       // Toast.makeText(ctx,"Call Received "+number, Toast.LENGTH_SHORT).show();
        //checkNumberInDatabase(ctx,number);
    }

    @Override
    protected void onIncomingCallAnswered(Context ctx, String number, Date start)
    {
        //
      // Toast.makeText(ctx,"onIncomingCallAnswered "+number, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end)
    {

        //
      //  Toast.makeText(ctx,"onIncomingCallEnded "+number, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start)
    {
        //
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end)
    {
        //
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start)
    {
        //
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

                final Intent intent = new Intent(ctx, MyCustomDialog.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("phone_no",name);

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ctx.startActivity(intent);
                    }
                },2000);


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
