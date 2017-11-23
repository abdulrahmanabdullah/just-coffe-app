package com.example.abdulrahman.just_coffe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView customerName , getFlavor,totalPrice,totalCups , counterCups;
    EditText etName ;
    RelativeLayout showInfo ;
    CheckBox boxOne ,boxTwo ;
    boolean hasWippedCream ;
    // final int price for each cups .
    final int price = 5 ;
    int quantityCups ;
    int orderCounter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customerName = findViewById(R.id.txv_customer_name);
        etName = findViewById(R.id.et_customer_name);
        getFlavor = findViewById(R.id.txv_get_flavor);
        boxOne = findViewById(R.id.cBox_1);
        boxTwo = findViewById(R.id.cBox_2);
        totalCups = findViewById(R.id.txv_total_cups);
        totalPrice = findViewById(R.id.txv_total_price);
        showInfo = findViewById(R.id.show_info_order);
        counterCups = findViewById(R.id.txv_order_counter);

    }

    private void showQuantity(int cups){
       counterCups.setText(String.valueOf(cups));
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
        setRelativeVisble();
        // set Customer name .
        customerName.setText(getCustomerName());
        // set flavor
        getFlavor.setText(String.valueOf(checkStatusBox()));
        // set quantity cups .
       totalCups.setText(String.valueOf(getOrderCounter()));
       // set total Price .
        totalPrice.setText(String.valueOf(getPrice()));
    }

    public String getCustomerName(){
    return  etName.getText().toString();
    }
    private void setRelativeVisble(){
        etName.setVisibility(View.INVISIBLE);
        showInfo.setVisibility(View.VISIBLE);
    }

    public boolean checkStatusBox(){
        return boxOne.isChecked();
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
