package com.example.abdulrahman.just_coffe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // declare Views ..
    TextView cName, showFlaver ,totalPrice ,numberCups,totalQuantity ;
    // final int price for each cups .
    final int price = 5 ;
    int quantityCups ;
    int orderCounter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void showQuantity(int cups){
        numberCups = findViewById(R.id.txv_order_counter);
        numberCups.setText(String.valueOf(cups));
    }

    public void incrementOrder(View view) {
        orderCounter++;
        showQuantity(orderCounter);
    }
    public void decrementOrder(View view) {
        orderCounter--;
        if(orderCounter <=0 || orderCounter == 0)
            orderCounter =0 ;
        showQuantity(orderCounter);
    }

    public void submitOrder(View view) {
        cName =findViewById(R.id.txv_customer_name);
        cName.setText("Abdulrahman ");
        showFlaver = findViewById(R.id.txv_get_flaver);
        showFlaver.setText("True");
        totalPrice = findViewById(R.id.txv_total_price);
        totalPrice.setText(String.valueOf(getPrice()));
    }

    // get method price and quantity cups .
    private int getOrderCounter(){
        return orderCounter;
    }
    // get price .
    private int getPrice(){
        return getOrderCounter() * price ;
    }
}
