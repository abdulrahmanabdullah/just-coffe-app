package com.example.abdulrahman.just_coffe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView customerName , getFlavor,totalPrice,totalCups , counterCups;
    EditText etName ;

    RelativeLayout showInfo ;
    CheckBox boxOne ,boxTwo ;
    // final int price for each cups .
     int price = 5 ;
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

    /**
     *
     * @param cups pass {@link #orderCounter}
     */
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
        // set Customer name .
        customerName.setText(getCustomerName());
        // set flavor
        StringBuilder str = new StringBuilder();
        if (boxOne.isChecked())
            str.append("Wipped cream, ");
        if (boxTwo.isChecked())
            str.append("And Chocolate ");
        getFlavor.setText(str.toString());
        // set quantity cups .
       totalCups.setText(String.valueOf(getOrderCounter()));
       // set total Price .
        int priceWithFlavor = incrementPrice(hasWippedCreams(),isHasChocolate());
        totalPrice.setText("$"+String.valueOf(priceWithFlavor));
        String result = "name : " + getCustomerName() +"\n" + "Flavor : " + str+"\n" + " total price : $" + priceWithFlavor;
        String subject = " order summary ";
        openMailApp(subject,result);
    }

    /**
     * @return string  form edit text .
     */
    public String getCustomerName(){
    return  etName.getText().toString();
    }

    /**
     * @return if customer check wiped return true otherwise return false .
     */
    public boolean hasWippedCreams(){
        return boxOne.isChecked();
    }

    /**
     * @return if customers check chocolate return true otherwise return false .
     */
    public boolean isHasChocolate(){
        return boxTwo.isChecked();
    }
    /**
     * @param check if customer choice wiped cream  flavor, add 10 $ price .
     * @param  checkChocolate  if customers want chocolate .
     * @return total price .
     */
    public int incrementPrice(boolean check,boolean checkChocolate){
        int t = price * orderCounter ;
        if (check && !checkChocolate ) {
            return t + (orderCounter * 10); // 2 cups = 10 + 20 = 30 .
        }
        else if (check && checkChocolate) {
            return t + (orderCounter * 10) + (orderCounter * 5); // 2 cups = 2* 5 , price = 10 + 20 for cream + 10 for chocolate. total = 40 .
        }
        if( !check && checkChocolate){
            return t + 5 ;}
        else {
            return t;
        }

    }

    /**
     * @return how many cups the customer well take it .
     */
    private int getOrderCounter(){
        return orderCounter;
    }

    /**
     * @param subject title of mail.
     * @param message  content order of coffe .
     */
    public void openMailApp(String subject,String message ){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
       intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
       intent.putExtra(Intent.EXTRA_TEXT,message);
       if (intent.resolveActivity(getPackageManager()) !=null){
           startActivity(intent);
       }
    }
}
