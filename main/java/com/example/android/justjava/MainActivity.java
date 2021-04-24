package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int numberOfCoffees=2;
    public void increment(View view) {
        if(numberOfCoffees>=100){
            Toast.makeText(this,"You cannot have more than 100 coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees=numberOfCoffees+1;
        display(numberOfCoffees);
    }

    public void decrement(View view) {
        if(numberOfCoffees<=1){
            Toast.makeText(this,"You cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees=numberOfCoffees-1;
        display(numberOfCoffees);
    }

    public int countPrice(int numberOfCoffees,boolean hasCheckedW,boolean hasCheckedC){
        int Amount;
        if (hasCheckedW == true && hasCheckedC==false){
            Amount=((numberOfCoffees*5)+(numberOfCoffees*1));
        }
        else if(hasCheckedC == true && hasCheckedW==false){
            Amount=((numberOfCoffees*5)+(numberOfCoffees*2));
        }
        else if(hasCheckedC == true && hasCheckedW==true){
            Amount=((numberOfCoffees*5)+(numberOfCoffees*3));
        }
        else{
            Amount=(numberOfCoffees*5);
        }
        return Amount;
    }

    public void submitOrder(View view){
        CheckBox creamBox = (CheckBox) findViewById(R.id.cream_box);
        boolean hasCheckedW = creamBox.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_box);
        boolean hasCheckedC = chocolate.isChecked();
        EditText et = (EditText) findViewById(R.id.name_view);
        String edit1 = et.getText().toString();
        int price = countPrice(numberOfCoffees,hasCheckedW,hasCheckedC);
        String Message = "Name : "+ edit1 + "\nQuantity : " + numberOfCoffees + "\nAdd Whiped Cream? : " + hasCheckedW + "\nAdd Chocolate? : " + hasCheckedC + "\nTotal : $" + price + "\nThank You!";
        Intent intent =  new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee order for" + edit1);
        intent.putExtra(Intent.EXTRA_TEXT,Message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}