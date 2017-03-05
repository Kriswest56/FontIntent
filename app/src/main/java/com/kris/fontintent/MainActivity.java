package com.kris.fontintent;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final int INTENT_CODE = 400;

    //Initialize fields for intent
    private int mAlpha = 255;
    private int mRed = 0;
    private int mGreen = 0;
    private int mBlue = 0;
    private float mTextSize = 20;
    private String mTextStyle = "Normal";
    private String mTextTypeface = "Default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * Send font chooser intent
     *
     * @param view
     */
    @OnClick(R.id.fontButton)
    public void sendFontIntent(View view){

        Log.i("Message: ", "Button Clicked!");

        Intent intent = new Intent("com.kris.CHOOSE_FONT");
        intent.putExtra("intentCode", INTENT_CODE);
        startActivityForResult(intent, INTENT_CODE);

    }

    /**
     * Retrieve result of fontchooser intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == INTENT_CODE){
            Log.d("Message: ", "Returned Intent!");

            mAlpha = data.getIntExtra("alpha", 255);
            mRed = data.getIntExtra("red", 0);
            mGreen = data.getIntExtra("green", 0);
            mBlue = data.getIntExtra("blue", 0);
            mTextSize = data.getFloatExtra("textSize", 20);
            mTextStyle = data.getStringExtra("textStyle");
            mTextTypeface = data.getStringExtra("textTypeface");

            applyFont();

        }else{
            Log.d("Message: ", "Did not return Intent!");
        }

    }

    /**
     * Method to call each style change method
     */
    private void applyFont(){

        EditText editText = (EditText) findViewById(R.id.mainText);

        if(editText != null){

            changeTextSize(mTextSize, editText);
            changeTextColor(mAlpha, mRed, mGreen, mBlue, editText);
            changeStyle(mTextStyle, editText);
            changeTypeface(mTextTypeface, editText);

        }


    }

    private void changeTextSize(Float sizeNum, View view){

        EditText editText = (EditText) view;

        if(sizeNum != null && editText != null){
            if(sizeNum > 100){
                sizeNum = (float)99;
            }
            editText.setTextSize(sizeNum);
        }

    }

    private void changeTextColor(int a, int r, int g, int b, View view){

        EditText editText = (EditText) view;
        editText.setTextColor(Color.argb(a, r, g, b));

    }

    private void changeStyle(String s, View view){

        EditText editText = (EditText) view;

        switch (s){

            case "Normal":
                editText.setTypeface(editText.getTypeface(), Typeface.NORMAL);
                break;

            case "Bold":
                editText.setTypeface(editText.getTypeface(), Typeface.BOLD);
                break;

            case "Italic":
                editText.setTypeface(editText.getTypeface(), Typeface.ITALIC);
                break;

            case "Bold Italic":
                editText.setTypeface(editText.getTypeface(), Typeface.BOLD_ITALIC);
                break;

        }

    }

    private void changeTypeface(String s, View view){

        EditText editText = (EditText) view;

        switch (s){

            case "Default":
                editText.setTypeface(Typeface.DEFAULT, editText.getTypeface().getStyle());
                break;

            case "Default Bold":
                editText.setTypeface(Typeface.DEFAULT_BOLD, editText.getTypeface().getStyle());
                break;

            case "Monospace":
                editText.setTypeface(Typeface.MONOSPACE, editText.getTypeface().getStyle());
                break;

            case "Sans Serif":
                editText.setTypeface(Typeface.SANS_SERIF, editText.getTypeface().getStyle());
                break;

            case "Serif":
                editText.setTypeface(Typeface.SERIF, editText.getTypeface().getStyle());
                break;

        }

    }

}
