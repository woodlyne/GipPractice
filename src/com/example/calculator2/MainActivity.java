package com.example.calculator2;

//import com.xperttech.helloworld.MainActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toast;

public class MainActivity extends Activity {

	Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;//number digit
	Button btnPlus,btnMinus,btnTimes,btnClear,btnEqual;//number digit
	double numberOne=0;
	double numberTwo=0;
	double numberResult=0;
	TextView textview;
	boolean OperationIsDone=false;
	boolean userIsInMiddleOfTypingANumber = false;
	String operationEntered = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MyNumberListener myNumberListner = new MyNumberListener();
		MyOperationListener myOperationListner = new MyOperationListener();
		cleanListener clean = new cleanListener();

		setContentView(R.layout.activity_main);
		textview= (TextView)findViewById(R.id.txtResult);
		btn1=(Button)findViewById(R.id.btnOne);
		btn2=(Button)findViewById(R.id.btnTwo);
		btn3=(Button)findViewById(R.id.btnThree);
		btn4=(Button)findViewById(R.id.btnFour);
		btn5=(Button)findViewById(R.id.btnFive);
		btn6=(Button)findViewById(R.id.btnSix);
		btn7=(Button)findViewById(R.id.btnSeven);
		btn8=(Button)findViewById(R.id.btnEight);
		btn9=(Button)findViewById(R.id.btnNine);
		btn0=(Button)findViewById(R.id.btnZero);
		btnPlus=(Button)findViewById(R.id.btnPlus);
		btnMinus=(Button)findViewById(R.id.btnMinus);
		btnTimes=(Button)findViewById(R.id.btnMultiply);
		btnClear=(Button)findViewById(R.id.btnClear);
		btnEqual=(Button)findViewById(R.id.btnEqual);


		btn1.setOnClickListener(myNumberListner);
		btn2.setOnClickListener(myNumberListner);
		btn3.setOnClickListener(myNumberListner);
		btn4.setOnClickListener(myNumberListner);
		btn5.setOnClickListener(myNumberListner);
		btn6.setOnClickListener(myNumberListner);
		btn7.setOnClickListener(myNumberListner);
		btn8.setOnClickListener(myNumberListner);
		btn9.setOnClickListener(myNumberListner);
		btn0.setOnClickListener(myNumberListner);
		btnPlus.setOnClickListener(myOperationListner);
		btnMinus.setOnClickListener(myOperationListner);
		btnTimes.setOnClickListener(myOperationListner);
		btnClear.setOnClickListener(clean);
		btnEqual.setOnClickListener(myOperationListner);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	void clearResult()
	{
		textview.setText("");
		OperationIsDone=false;		
	}
	
	class cleanListener implements OnClickListener
	{
		@Override
		public void onClick(View v) {

			clearResult();
			 
		}
	}
	class MyNumberListener implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {
			if(OperationIsDone)
			{
				clearResult();
			}
			Button whichButton = (Button) arg0;
			String strNumberEntered = whichButton.getText().toString();
			int numberAsInteger = Integer.parseInt(strNumberEntered);
			if(!userIsInMiddleOfTypingANumber)
			{
				numberOne = numberAsInteger;
				userIsInMiddleOfTypingANumber = true;	
				textview.setText(strNumberEntered);
			}
			else
			{
				String oldNumber;
				String newNumber;
				oldNumber = textview.getText().toString() ;
				newNumber = oldNumber + strNumberEntered;
				textview.setText(newNumber);

			}
		}

	}
	class MyOperationListener implements OnClickListener
	{
		@Override
		public void onClick(View arg0) {	
			Button whichButton = (Button) arg0;
			operationEntered = whichButton.getText().toString();
			if(userIsInMiddleOfTypingANumber)
			{
				if(operationEntered.equals("="))
				{
					performMath();
					OperationIsDone=true;
					return;
				}

				String existingText = textview.getText().toString() ;
				String newText = existingText + operationEntered;
				textview.setText(newText);
			} 

		}
	}

	void performMath()
	{
		String textEnteredSoFar = textview.getText().toString();
		int length = textEnteredSoFar.length();
		String value1 = new String();
		String value2 = new String();
		char operand = ' ';
		boolean value1IsDone = false;

		for(int i=0; i<length; i++)
		{
			char currentCharacter = textEnteredSoFar.charAt(i);
			if(currentCharacter == '+')
			{
				operand = currentCharacter;
				value1IsDone = true;
			}
			else if(currentCharacter == '-')
			{
				value1IsDone = true;
				operand = currentCharacter;
				//doSubstraction();
			}
			else if(currentCharacter == '*')
			{
				value1IsDone = true;
				operand = currentCharacter;
				//doMultiplication();
			}
			else 

				if(value1IsDone)
				{
					value2 += String.valueOf(currentCharacter);
				}
				else
				{
					value1 += String.valueOf(currentCharacter);
				}

		}

		if(operand == '+')
		{
			doAddition(value1, value2);
		}

		if(operand == '-')
		{
			doSubstration(value1, value2);
		}
		if(operand == '*')
		{
			doMultiplication(value1, value2);
		}
	}

	void doAddition(String value1, String value2)
	{
		int number1 = Integer.parseInt(value1);
		int number2 = Integer.parseInt(value2);

		int sum = number1 + number2;
		textview.setText(String.valueOf(sum));
	}


	void doSubstration(String value1, String value2)
	{
		int number1 = Integer.parseInt(value1);
		int number2 = Integer.parseInt(value2);

		int substraction = number1 - number2;
		textview.setText(String.valueOf (substraction));
	}


	void doMultiplication(String value1, String value2)
	{
		int number1 = Integer.parseInt(value1);
		int number2 = Integer.parseInt(value2);

		int multiplication = number1 * number2;
		textview.setText(String.valueOf(multiplication));
	}
 

}
