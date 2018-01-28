package com.example.adriealle.smartparks;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class owneract extends AppCompatActivity {
String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owneract);
        final EditText plat=(EditText)findViewById(R.id.editText4);
        final TextView name=(TextView)findViewById(R.id.t);
        final TextView model=(TextView)findViewById(R.id.tt);
        final TextView phone=(TextView)findViewById(R.id.ttt);
        final TextView email=(TextView)findViewById(R.id.tttt);
        Button find=(Button)findViewById(R.id.button2);
       final  Button con=(Button)findViewById(R.id.button10);
        con.setEnabled(true);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
             


			 //contact me on github @adriel1997




			 }
        });
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent call = new Intent(Intent.ACTION_CALL);
                Toast.makeText(view.getContext(), "okay", Toast.LENGTH_SHORT).show();

                        call.setData(Uri.parse("tel:"+s));

                        if (ActivityCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(view.getContext(), "Please Grant Permission", Toast.LENGTH_SHORT).show();
                            requestPermission();
                        } else {
                            startActivity(call);
                       }





            }
        });}
            private void requestPermission(){
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE},1);
            }
            }







