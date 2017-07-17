package com.example.adriealle.smartparks;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class fire {
    String k;
    private static final String TAG = "PREBOOKING METHOD";
    private static final String TA = "CANCELLING METHOD";
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    void users(String name,String phone,String plate,String model,String e,String p)
    {
        DatabaseReference myRef = database.getReference("USERS");
        myRef.push().setValue(plate);
        DatabaseReference m = database.getReference("USERS/"+plate+"/NAME");
        m.setValue(name);
        DatabaseReference my = database.getReference("USERS/"+plate+"/PHONE");
        my.setValue(phone);
        DatabaseReference myr = database.getReference("USERS/"+plate+"/MODEL");
        myr.setValue(model);
        DatabaseReference myre = database.getReference("USERS/"+plate+"/EMAIL");
        myre.setValue(e);
        DatabaseReference myref = database.getReference("USERS/"+plate+"/PASSWORD");
        myref.setValue(p);

    }
    void location(String location,String slot)
    {

        DatabaseReference m = database.getReference("LOCATION/"+location);
        m.push().setValue(slot);
        DatabaseReference y = database.getReference("LOCATION/"+location+"/"+slot+"/ALLOTED");
        y.setValue("NULL");
        DatabaseReference my = database.getReference("LOCATION/"+location+"/"+slot+"/TIME");
        my.setValue("NULL");
        DatabaseReference myr = database.getReference("LOCATION/"+location+"/"+slot+"/DATE");
        myr.setValue("NULL");
        DatabaseReference l = database.getReference("LOCATION/"+location+"/"+slot+"/LIMIT");
        l.setValue("NULL");

    }

    void pay(String plate,String loc,String slot,String amt,String mode)
    {
        DatabaseReference myRef = database.getReference("PAYMENT");
        myRef.push().setValue(plate);
        DatabaseReference m = database.getReference("PAYMENT/"+plate+"/LOCATION");
        m.setValue(loc);
        DatabaseReference my = database.getReference("PAYMENT/"+plate+"/SLOT");
        my.setValue(slot);
        DatabaseReference myr = database.getReference("PAYMENT/"+plate+"/AMOUNT");
        myr.setValue(amt);
        DatabaseReference myre = database.getReference("PAYMENT/"+plate+"/MODE");
        myre.setValue(mode);

    }


    void book(String plate,String loc,String date ,String time)
    {DatabaseReference myRef = database.getReference("LOCATION/"+loc);
        Query query = myRef.orderByChild("ALLOTED").equalTo("null").limitToFirst(1);


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Log.v(TAG,""+ childDataSnapshot.getKey());
                    Log.v(TAG,""+ childDataSnapshot.child("DATE").getValue());
                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    void can(final String all, String loc) {
        DatabaseReference myRef = database.getReference("LOCATION/" + loc);
        Query query = myRef.orderByChild("ALLOTED").equalTo("null").limitToFirst(1);


        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Log.v(TAG, "" + childDataSnapshot.getKey());
                    Log.v(TAG, "" + childDataSnapshot.child("DATE").getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
