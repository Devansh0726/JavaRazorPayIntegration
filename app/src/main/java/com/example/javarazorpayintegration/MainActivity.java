package com.example.javarazorpayintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.javarazorpayintegration.databinding.ActivityMainBinding;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amountInString = binding.etAmount.getText().toString();
                int amount = Math.round(Float.parseFloat(amountInString)*100);

                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_qMDY60TxJOnaQe");

                JSONObject object = new JSONObject();

                try {
                    object.put("name", "developer");
                    object.put("currency","INR");
                    object.put("amount",amount);
                    checkout.open(MainActivity.this,object);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(MainActivity.this, "Payment completed Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
       Toast.makeText(MainActivity.this, "Error please try again", Toast.LENGTH_SHORT).show();
    }
}