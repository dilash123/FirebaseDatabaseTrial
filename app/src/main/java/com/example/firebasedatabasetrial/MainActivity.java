package com.example.firebasedatabasetrial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        Intent service = new Intent(getApplicationContext(), MyService.class);
        startService(service);

        if (android.os.Build.VERSION.SDK_INT > 22)
        {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                //AppUtilities.downloadFile(Uri.parse(cmnDownloadStuffsCO.url), cmnDownloadStuffsCO.attachmentTitle, getActivity());
                //do nothing
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();

            }
            else {
                requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG},1);
            }
        }

        myRef.orderByChild("username").equalTo("raj12").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

              //  String usernames=dataSnapshot.child("username").getValue().toString();

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


     /*   ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    userList.add(user);



                }
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();

                // ..
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.addValueEventListener(postListener);*/
        writeNewUser("2","anil","rahul@gmail.com","+919784742589");
    }


    public void writeNewUser(String userId, String name, String email, String mobile) {
        User user = new User(name, email,mobile);
        myRef.child(userId).setValue(user);


       /* myRef.child("users").child(userId).child("username").setValue(name);
        myRef.child("users").child(userId).child("email").setValue(email);*/
    }
}