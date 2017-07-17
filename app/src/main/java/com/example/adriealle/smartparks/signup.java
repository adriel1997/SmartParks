package com.example.adriealle.smartparks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
user q =new user(this);
    fire k=new fire();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final EditText fname =(EditText)findViewById(R.id.editText);
        final EditText plate=(EditText)findViewById(R.id.editText2);
        final EditText Pno = (EditText)findViewById(R.id.editText3);
        final EditText em=(EditText)findViewById(R.id.editText13);
        final EditText pass =(EditText)findViewById(R.id.editText15);
        final EditText m=(EditText)findViewById(R.id.editText5);
        Button next = (Button)findViewById(R.id.button3);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vl=new Intent(signup.this,Start.class);
                if((fname.getText().toString()).equals(null)||(Pno.getText().toString()).equals(null)||(plate.getText().toString()).equals(null)||(m.getText().toString()).equals(null)||(em.getText().toString()).equals(null)||(pass.getText().toString()).equals(null))
                {if((fname.getText().toString()).equals(null))
                {
                    Toast.makeText(getApplicationContext(), "Entire First name", Toast.LENGTH_LONG).show();
                }
                    if((plate.getText().toString()).equals(null)||(plate.getText().toString()).length()!=9)
                    {
                        Toast.makeText(getApplicationContext(), "Entire proper complete Plate Number", Toast.LENGTH_LONG).show();
                    }
                    if((Pno.getText().toString()).equals(null)||(plate.getText().toString()).length()!=10)
                    {
                        Toast.makeText(getApplicationContext(), "Entire 10 digit number", Toast.LENGTH_LONG).show();
                    }
                    if((em.getText().toString()).equals(null))
                    {
                        Toast.makeText(getApplicationContext(), "Don't leave blank", Toast.LENGTH_LONG).show();
                    }
                    if((pass.getText().toString()).equals(null)||(plate.getText().toString()).length()!=5)
                    {
                        Toast.makeText(getApplicationContext(), "Enter at-least 5digit long", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    k.users(fname.getText().toString(),Pno.getText().toString(),plate.getText().toString(),m.getText().toString(),em.getText().toString(),pass.getText().toString());
                    startActivity(vl);
                }

                     }
        });
    }
}


