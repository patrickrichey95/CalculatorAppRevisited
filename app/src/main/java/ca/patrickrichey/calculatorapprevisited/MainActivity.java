package ca.patrickrichey.calculatorapprevisited;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    //instantiate variables for calculations
    float firstNumber = 0;
    float secondNumber = 0;
    float result = 0;
    String operation = "";
    boolean isTypingNumber = false;
    int decimalCount = 0;
    int operatorCount = 0;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = (TextView)findViewById(R.id.textView);
    }

    //capture event when a number button is clicked
    public void numberClicked(View view) {
        Button number = (Button)view;
        //while use is still entering numbers, concat to existing number
        if (isTypingNumber) {
            resultText.setText(resultText.getText().toString() + number.getText().toString());
        } else {
            resultText.setText(number.getText().toString());
            decimalCount = 0;
        }
        isTypingNumber = true;
    }

    //add decimal to current number
    public void decimalClicked(View view) {
        Button decimal = (Button)view;
        //if user is typing a number and hasn't yet entered a decimal point
        if ((isTypingNumber) && (decimalCount == 0)){
            resultText.setText(resultText.getText() + decimal.getText().toString());
            decimalCount = 1; //to limit allowed decimals per number
        }
    }

    //assign the operator on click (working on multiple operator clicks)
    public void operationClicked(View view) {
        Button operationType = (Button)view;
        if (operatorCount == 0) {
            firstNumber = Float.parseFloat(resultText.getText().toString());
                    operation = operationType.getText().toString();
                    isTypingNumber = false;
        }
        operatorCount++;
    }

    //perform calculation
    public void evaluate(View view) {
        secondNumber = Float.parseFloat(resultText.getText().toString());
                isTypingNumber = false;
        //perform any chosen operation
        if (operation.equals("+")) {
            result = firstNumber + secondNumber;
        } else if (operation.equals("-")){
            result = firstNumber - secondNumber;
        } else if (operation.equals("×")){
            result = firstNumber * secondNumber;
        } else if (operation.equals("÷")){
            result = firstNumber / secondNumber;
        }
        //display the result to the resultText
        resultText.setText(String.valueOf(result));
        operatorCount = 0;
    }

    //clear the resultText and reset numbers
    public void clearView(View view) {
        firstNumber = 0;
        secondNumber = 0;
        result = 0;
        resultText.setText("0");
        isTypingNumber = false;
    }
}
