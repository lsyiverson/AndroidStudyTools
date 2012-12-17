
package com.study.tools.items;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
    private final static int START_SPEECH_RECOGNIZE = 0x1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_recognize);
        setupViews();
    }

    private void setupViews(){
        mBtnSpeak = (Button)findViewById(R.id.btn_speak);
        mBtnSpeak.setOnClickListener(new OnClickListener() {
            @Override
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
        try {
            startActivityForResult(intent, START_SPEECH_RECOGNIZE);
        } catch (ActivityNotFoundException ex){
            new AlertDialog.Builder(this).setTitle(R.string.no_voice_search_title)
            .setMessage(R.string.no_voice_search_msg)
            .setNegativeButton(R.string.no_voice_search_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            })
            .setPositiveButton(R.string.no_voice_search_download, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent installIntent = new Intent(Intent.ACTION_VIEW);
                    installIntent.setData(Uri.parse("market://details?id=com.google.android.voicesearch"));
                    try {
                        startActivity(installIntent);
                    } catch (ActivityNotFoundException ex) {
                        dialog.dismiss();
                        new AlertDialog.Builder(SpeechRecognizeActivity.this)
                        .setTitle(R.string.no_market_title)
                        .setMessage(R.string.no_market_msg)
                        .setNegativeButton(R.string.no_market_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton(R.string.no_market_download, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("http://m.wandoujia.com/apps/com.google.android.voicesearch"));
                                startActivity(intent);
                            }
                        }).show();
                    }
                }
            }).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == START_SPEECH_RECOGNIZE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, matches);
            mTextView.setAdapter(adapter);
            mTextView.showDropDown();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
