package com.example.adriealle.smartparks;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Start extends AppCompatActivity {
     String pae,eme,K,tin;
    user d=new user(this);
    fire r=new fire();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        MediaPlayer mediaplayer = new MediaPlayer();
        mediaplayer = MediaPlayer.create(this, R.raw.deb_clai);
        mediaplayer.start();

        final EditText pel=(EditText)findViewById(R.id.editText14);
        final   EditText eel=(EditText)findViewById(R.id.editText12);

        Button lo=(Button)findViewById(R.id.button7);
        Button sign=(Button)findViewById(R.id.button8);
      lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pae=pel.getText().toString();
                eme=eel.getText().toString();

                if(pae.equals("0000")&&(eme.equals("admin")||eme.equals("adriealle")))
                { Intent k=new Intent(Start.this,owneract.class);
                  Toast.makeText(getApplicationContext(), "Welcome sir", Toast.LENGTH_LONG).show();
                    startActivity(k);
                }
                else{
                    final Intent i = new Intent(Start.this, book.class);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("USERS/");
                    Query query = myRef.orderByChild("EMAIL").equalTo(eme).limitToFirst(1);
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                                K = "" + childDataSnapshot.child("PASSWORD").getValue();
                                tin = "" + childDataSnapshot.getKey();
                                if (K.equals(pae)) {
                                    i.putExtra("pl", tin);
                                    startActivity(i);
                                } else
                                    Toast.makeText(getApplicationContext(), "Please ENTER CORRECT PASSWORD", Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), "Please REGISTER FIRST", Toast.LENGTH_LONG).show();
                        }
                    });
                }}


      });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(Start.this,signup.class);
                startActivity(j);
            }
        });
    }}
