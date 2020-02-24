package com.example.sorteioandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
	
	Random r = new Random();
	Button mButtonSorteio;
	EditText mNumber1;
	EditText mNumber2;
	TextView mDraw;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mButtonSorteio = (Button) findViewById(R.id.sortear);
		mNumber1 = (EditText) findViewById(R.id.num1);
		mNumber2 = (EditText) findViewById(R.id.num2);
		mDraw = (TextView) findViewById(R.id.result);
		
		mButtonSorteio.setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View view) {
						generateRandom();
					}
				});
	}
	
	private void generateRandom(){
		boolean is_valid = false;
		String number1 = mNumber1.getText().toString();
		String number2 = mNumber2.getText().toString();
		
		is_valid = validateEmptyStrings(number1, number2);
		if(!is_valid) { return; }
		
		int num1 = Integer.parseInt(number1);
		int num2 = Integer.parseInt(number2);
		int range = num2 - num1;
		Log.v("EditText", "Number 1: " + num1);
		Log.v("EditText", "Number 2: " + num2);
		Log.v("EditText", "Range: " + range);
		
		is_valid = validatePositiveValues(range);
		if(!is_valid) { return; }
		
		int random = r.nextInt(range + 1) + num1;
		Log.v("EditText", "Random: " + random);
		mDraw.setText("Result: " + random);
	}
	
	private boolean validateEmptyStrings(String number1, String number2) {
		if (number1.isEmpty() || number2.isEmpty()) {
			String message = "One of the fields is empty. Please insert a number.";
			Log.w("EditText", message);
			mDraw.setText("Warning: " + message);
			return false;
		}
		return true;
	}
	
	private boolean validatePositiveValues(int range){
		if (range <= 0) {
			String message = "Invalid Random as Number 2 is equal or less than Number 1." +
					" Please insert a valid range.";
			Log.w("EditText", message);
			mDraw.setText("Warning: " + message);
			return false;
		}
		return true;
	}
}
