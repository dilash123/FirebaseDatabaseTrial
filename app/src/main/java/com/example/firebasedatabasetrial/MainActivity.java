package com.example.firebasedatabasetrial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Students> studentsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter uadapter;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeApp();



        if (android.os.Build.VERSION.SDK_INT > 22)
        {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {

            }
            else {
                requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG,Manifest.permission.READ_PHONE_STATE},1);
            }
        }



        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });

        Query query = myRef.orderByKey();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentsList.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    Students students = userSnapshot.getValue(Students.class);
                    studentsList.add(students);
                }
                uadapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        /*writeNewUser("S1","abcd","+918209401721");
        writeNewUser("S2","bcde","+919461189610");
        writeNewUser("S3","cdef","+919414189610");*/
    }


    public void writeNewUser(String userId, String name, String mobile) {
        Students students = new Students(name,mobile);
        myRef.child(userId).setValue(students);
    }

    void filter(String text){
        List<Students> temp = new ArrayList();
        for(Students students: studentsList){
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if(students.name.contains(text) || students.contact.contains(text)){
                temp.add(students);
            }
        }
        //update recyclerview
        uadapter.updateList(temp);
    }

    void initializeApp()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("students");
        recyclerView = (RecyclerView) findViewById(R.id.recycle_users);
        etSearch = findViewById(R.id.et_search);
        uadapter = new UserAdapter(studentsList,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(uadapter);

    }
}