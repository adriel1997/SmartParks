package com.example.adriealle.smartparks;


        import android.app.Activity;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import com.paypal.android.sdk.payments.PayPalConfiguration;
        import com.paypal.android.sdk.payments.PayPalPayment;
        import com.paypal.android.sdk.payments.PayPalService;
        import com.paypal.android.sdk.payments.PaymentActivity;
        import com.paypal.android.sdk.payments.PaymentConfirmation;

        import org.json.JSONException;

        import java.math.BigDecimal;
        import java.util.Calendar;


public class paymentt extends AppCompatActivity {
    String g,f,h,m,sd;
    fire kj =new fire();
    /*PayPalConfiguration m_configuration;
    / the id is the link to the paypal account, we have to create an app and get its id
    String m_paypalClientId = "ASbSfj8PG-PPJb01yeJVJB_HKAcBMdZx2rqZ5emVwvpKCNongd8Mu9kgpnn_erQonCcdAseqxtzjUuGp";
    Intent m_service;
    int m_paypalRequestCode = 999; //*/
    private static PayPalConfiguration config = new PayPalConfiguration()

            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)

            .clientId("ASbSfj8PG-PPJb01yeJVJB_HKAcBMdZx2rqZ5emVwvpKCNongd8Mu9kgpnn_erQonCcdAseqxtzjUuGp");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentt);
        Button y= (Button)findViewById(R.id.one);
        Button p=(Button)findViewById(R.id.button11);
        Button pdd=(Button)findViewById(R.id.button12);
        Intent intent = new Intent(this, PayPalService.class);
        g=getIntent().getExtras().getString("platt");
        f=getIntent().getExtras().getString("loc");
        h=getIntent().getExtras().getString("amt");
        m=getIntent().getExtras().getString("pon");
        sd=getIntent().getExtras().getString("slo");
        Calendar c=Calendar.getInstance();
        final String sDate = "" + c.get(Calendar.DAY_OF_MONTH) + c.get(Calendar.MONTH) + c.get(Calendar.YEAR);
        TextView op=(TextView)findViewById(R.id.textView10);
        op.setText(""+h);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        startService(intent);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent j = new Intent(paymentt.this, Payment.class);
                j.putExtra("patt", g+sDate);//userid
                j.putExtra("at", h);//amt
                j.putExtra("lc", f);
                j.putExtra("pn", m);
                kj.pay(g+sDate,f,sd,h,"PayTM");
                startActivity(j);
            }
        });
        pdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent j = new Intent(paymentt.this, Payment.class);
                j.putExtra("patt", g);//userid
                j.putExtra("at", h);//amt
                j.putExtra("lc", f);
                j.putExtra("pn", m);
                kj.pay(g+sDate,f,sd,h,"Cash");
                startActivity(j);

            }
        });

        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayPalPayment payment = new PayPalPayment(new BigDecimal(h), "USD", "sample item",
                        PayPalPayment.PAYMENT_INTENT_SALE);
                kj.pay(g+sDate,f,sd,h,"PayPAL");
                Intent intent = new Intent(paymentt .this, PaymentActivity.class);

                // send the same configuration for restart resiliency
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

                startActivityForResult(intent, 0);
            }
        });
    }
    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }



    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("paymentExample", confirm.toJSONObject().toString(4));

                    // TODO: send 'confirm' to your server for verification.
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.

                } catch (JSONException e) {
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("paymentExample", "The user canceled.");
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }
    }
}
