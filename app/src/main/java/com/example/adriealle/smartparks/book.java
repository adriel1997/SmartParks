package com.example.adriealle.smartparks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class book extends AppCompatActivity {
    String plate,a,slot,q,w,e,r;
    String l,pol="null";
   fire vb=new fire();
    Calendar c = Calendar.getInstance();
    String sDate = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
    int shour=c.get(Calendar.HOUR_OF_DAY);
    int smin =c.get(Calendar.MINUTE);
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        final TextView Plateno = (TextView)findViewById(R.id.editText9);
        final TextView date = (TextView)findViewById(R.id.editText10);
        final  TextView hour = (TextView)findViewById(R.id.editText11);
        final  TextView min = (TextView)findViewById(R.id.min);
        plate=getIntent().getExtras().getString("pl");

        if(shour<10)
        {
            time="0"+shour+":"+smin;
        }
        if(smin<10)
        {
            time=shour+":"+smin+"0";
        }
else
            time=shour+":"+smin;

            date.setText(""+sDate);
            hour.setText(""+shour);
            min.setText(""+smin);

        Plateno.setText(""+plate);
        final Spinner s=(Spinner)findViewById(R.id.spinner2);
        final Spinner limit=(Spinner)findViewById(R.id.spin);
        Button sub = (Button)findViewById(R.id.button6);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("LOCATION/"+s.getSelectedItem().toString());
                Query query = myRef.orderByChild("ALLOTED").equalTo("NULL").limitToFirst(1);

query.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
            slot =childDataSnapshot.getKey();
            q=""+ myRef.child(slot).child("ALLOTED").setValue(Plateno.getText().toString());
            w=""+ myRef.child(slot).child("DATE").setValue(sDate);
            e= ""+myRef.child(slot).child("TIME").setValue(time);
            r=""+ myRef.child(slot).child("LIMIT").setValue(limit.getSelectedItem().toString());
            break;
        }
        if(slot!=null) {
            Toast.makeText(getApplicationContext(), "SPACE ALLOTED" + slot, Toast.LENGTH_LONG).show();
            Intent p = new Intent(book.this, Confirmpage.class);
            p.putExtra("plat", plate);
            p.putExtra("all", slot);
            p.putExtra("date", sDate);
            p.putExtra("time", time);
            p.putExtra("lim", limit.getSelectedItem().toString());
            p.putExtra("loc", s.getSelectedItem().toString());
            startActivity(p);
        }
        else
            Toast.makeText(getApplicationContext(), " NO SPACE ALLOTED", Toast.LENGTH_LONG).show();
}

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});
            }
        });
    }
}
