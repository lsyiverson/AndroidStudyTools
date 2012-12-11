
package com.study.tools.items;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.study.tools.R;

public class SpeechRecognizeActivity extends Activity {
    private Button mBtnSpeak;
    private AutoCompleteTextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_recognize);
        setupViews();
    }

    private void setupViews(){
        mBtnSpeak = (Button)findViewById(R.id.btn_speak);
        mBtnSpeak.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                startSpeechRecognize();
            }
        });
        mTextView = (AutoCompleteTextView)findViewById(R.id.edittext_input);
    }

    private void startSpeechRecognize(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "test");
        startActivityForResult(intent, 0x1111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0x1111 && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, matches);
            mTextView.setAdapter(adapter);
            mTextView.showDropDown();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
