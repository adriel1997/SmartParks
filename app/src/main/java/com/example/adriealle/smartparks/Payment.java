package com.example.adriealle.smartparks;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.paytm.pgsdk.PaytmMerchant;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This is the sample app which will make use of the PG SDK. This activity will
 * show the usage of Paytm PG SDK API's.
 **/

public class Payment extends Activity {
String f,g,h,m;
    TextView qq,w,e,r,t,y,u,i,o,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initOrderId();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        g=getIntent().getExtras().getString("patt");
        f=getIntent().getExtras().getString("lc");
        h=getIntent().getExtras().getString("at");
        m=getIntent().getExtras().getString("pn");
        Button sat=(Button)findViewById(R.id.start_transaction);
        qq   =(TextView) findViewById(R.id.order_id);
        w =(TextView) findViewById(R.id.merchant_id);
        e   =(TextView) findViewById(R.id.customer_id);
        r  =(TextView) findViewById(R.id.channel_id);
        t  =(TextView) findViewById(R.id.industry_type_id);
        y   =(TextView) findViewById(R.id.website);
        u =(TextView) findViewById(R.id.transaction_amount);
        i =(TextView) findViewById(R.id.theme);
        o  =(TextView) findViewById(R.id.cust_email_id);
        p =(TextView) findViewById(R.id.cust_mobile_no);
        qq.setText(""+g);
        w.setText(""+f);
        e.setText(""+g);
        u.setText(""+h);
        p.setText(""+m);
    }

    //This is to refresh the order id: Only for the Sample App's purpose.
    @Override
    protected void onStart(){
        super.onStart();
        initOrderId();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


    private void initOrderId() {
        Random r = new Random(System.currentTimeMillis());
        String orderId = "ORDER" + (1 + r.nextInt(2)) * 10000
                + r.nextInt(10000);
        TextView orderIdTextView = (TextView) findViewById(R.id.order_id);
        orderIdTextView.setText(orderId);
    }
    public void onStartTransaction(View view) {
        PaytmPGService Service = PaytmPGService.getStagingService();
        Map<String, String> paramMap = new HashMap<String, String>();

        paramMap.put("ORDER_ID", qq.getText().toString());
        paramMap.put("MID", w.getText().toString());
        paramMap.put("CUST_ID", e.getText().toString());
        paramMap.put("CHANNEL_ID", r.getText().toString());
        paramMap.put("INDUSTRY_TYPE_ID", t.getText().toString());
        paramMap.put("WEBSITE", y.getText().toString());
        paramMap.put("TXN_AMOUNT", u.getText().toString());
        paramMap.put("THEME", i.getText().toString());
        paramMap.put("EMAIL", o.getText().toString());
        paramMap.put("MOBILE_NO", p.getText().toString());
        PaytmOrder Order = new PaytmOrder(paramMap);

        PaytmMerchant Merchant = new PaytmMerchant(
                "https://pguat.paytm.com/paytmchecksum/paytmCheckSumGenerator.jsp",
                "https://pguat.paytm.com/paytmchecksum/paytmCheckSumVerify.jsp");

        Service.initialize(Order, Merchant, null);

        Service.startPaymentTransaction(this, true, true,
                new PaytmPaymentTransactionCallback() {
                    @Override
                    public void someUIErrorOccurred(String inErrorMessage) {
                        // Some UI Error Occurred in Payment Gateway Activity.
                        // // This may be due to initialization of views in
                        // Payment Gateway Activity or may be due to //
                        // initialization of webview. // Error Message details
                        // the error occurred.
                    }

                    @Override
                    public void onTransactionSuccess(Bundle inResponse) {
                        // After successful transaction this method gets called.
                        // // Response bundle contains the merchant response
                        // parameters.
                        Log.d("LOG", "Payment Transaction is successful " + inResponse);
                        Toast.makeText(getApplicationContext(), "Payment Transaction is successful ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onTransactionFailure(String inErrorMessage,
                                                     Bundle inResponse) {
                        // This method gets called if transaction failed. //
                        // Here in this case transaction is completed, but with
                        // a failure. // Error Message describes the reason for
                        // failure. // Response bundle contains the merchant
                        // response parameters.
                        Log.d("LOG", "Payment Transaction Failed " + inErrorMessage);
                        Toast.makeText(getBaseContext(), "Payment Transaction Failed ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void networkNotAvailable() { // If network is not
                        // available, then this
                        // method gets called.
                    }

                    @Override
                    public void clientAuthenticationFailed(String inErrorMessage) {
                        // This method gets called if client authentication
                        // failed. // Failure may be due to following reasons //
                        // 1. Server error or downtime. // 2. Server unable to
                        // generate checksum or checksum response is not in
                        // proper format. // 3. Server failed to authenticate
                        // that client. That is value of payt_STATUS is 2. //
                        // Error Message describes the reason for failure.
                    }

                    @Override
                    public void onErrorLoadingWebPage(int iniErrorCode,
                                                      String inErrorMessage, String inFailingUrl) {

                    }

                    // had to be added: NOTE
                    @Override
                    public void onBackPressedCancelTransaction() {
                        // TODO Auto-generated method stub
                    }

                });
    }
}
