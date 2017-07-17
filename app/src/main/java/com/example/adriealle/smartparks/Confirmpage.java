package com.example.adriealle.smartparks;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Confirmpage extends AppCompatActivity {
    String l, as, s, d;
    String slot;
    String value;
    Intent i = new Intent(this, Start.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmpage);
        final TextView Plateno = (TextView) findViewById(R.id.textView7);
        final TextView loca = (TextView) findViewById(R.id.textView11);
        final TextView all = (TextView) findViewById(R.id.textView6);
        final TextView lim = (TextView) findViewById(R.id.tex);
        final TextView cost = (TextView) findViewById(R.id.text);
        l = getIntent().getExtras().getString("plat");
        as = getIntent().getExtras().getString("loc");
        s = getIntent().getExtras().getString("all");
        d = getIntent().getExtras().getString("lim");
        lim.setText("Booked for:" + d);
        Plateno.setText("Plate number:" + l);
        loca.setText("Location:" + as);
        all.setText("Slot alloted:" + s);
        if (d.equals("1hr")) {
            cost.setText("50");
        } else if (d.equals("2hr")) {
            cost.setText("90");
        } else if (d.equals("3hr")) {
            cost.setText("130");
        }
        Button contin = (Button) findViewById(R.id.button4);
        Button can = (Button) findViewById(R.id.button5);
        Button con = (Button) findViewById(R.id.button9);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("OWNER/" + as+"/PHONE");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });







        contin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.NotificationCompat.Builder m = new NotificationCompat.Builder(Confirmpage.this)
                        .setSmallIcon(R.drawable.logok)
                        .setContentTitle("You've been Alloted No." + s)
                        .setContentText("Don't swipe");
                int a = 001;

                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                Confirmpage.this,
                                0,
                                i,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                m.setContentIntent(resultPendingIntent);
                NotificationManager n = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                n.notify(a, m.build());

                final Intent j = new Intent(Confirmpage.this, paymentt.class);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("USERS/" + l + "/PHONE");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        j.putExtra("platt", l);//userid
                        j.putExtra("amt", cost.getText().toString());//amt
                        j.putExtra("loc", as);
                        j.putExtra("pon", value);
                        j.putExtra("slo",s);
                        startActivity(j);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });


            }
        });
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent k = new Intent(Confirmpage.this, Start.class);
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("LOCATION/" + as);
                Query query = myRef.orderByChild("ALLOTED").equalTo(l).limitToFirst(1);


                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                            slot = childDataSnapshot.getKey();
                            myRef.child(slot).child("ALLOTED").setValue("NULL");
                            myRef.child(slot).child("DATE").setValue("NULL");
                            myRef.child(slot).child("TIME").setValue("NULL");
                            myRef.child(slot).child("LIMIT").setValue("NULL");

                            break;
                        }
                        Toast.makeText(getApplicationContext(), "PLEASE CHANGE YOUR MIND " + slot, Toast.LENGTH_LONG).show();
                        startActivity(k);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:"+value));
                if (ActivityCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(view.getContext(), "Please Grant Permission", Toast.LENGTH_SHORT).show();
                    requestPermission();
                } else {
                    startActivity(call);
                }

            }

        });

    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE},1);
    }
}


