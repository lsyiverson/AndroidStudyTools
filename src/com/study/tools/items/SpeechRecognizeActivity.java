
package com.study.tools.items;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.study.tools.R;

public class SpeechRecognizeActivity extends Activity {
    private ImageButton mBtnFind;

    private ImageView mIvSpeak;

    private AutoCompleteTextView mTextView;

    private final static int START_SPEECH_RECOGNIZE = 0x1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech_recognize);
        setupViews();
    }

    private void setupViews() {
        mBtnFind = (ImageButton)findViewById(R.id.btn_find);
        mBtnFind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!TextUtils.isEmpty(mTextView.getText())) {
                    Toast.makeText(SpeechRecognizeActivity.this, mTextView.getText(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        mIvSpeak = (ImageView)findViewById(R.id.iv_speak);
        mIvSpeak.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mTextView.setText("");
                startSpeechRecognize();
            }
        });
        mTextView = (AutoCompleteTextView)findViewById(R.id.edittext_input);
    }

    private void startSpeechRecognize() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "test");
        try {
            startActivityForResult(intent, START_SPEECH_RECOGNIZE);
        } catch (ActivityNotFoundException ex) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.no_voice_search_title)
                    .setMessage(R.string.no_voice_search_msg)
                    .setNegativeButton(R.string.no_voice_search_cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                    .setPositiveButton(R.string.no_voice_search_download,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent installIntent = new Intent(Intent.ACTION_VIEW);
                                    installIntent.setData(Uri
                                            .parse("market://details?id=com.google.android.voicesearch"));
                                    try {
                                        startActivity(installIntent);
                                    } catch (ActivityNotFoundException ex) {
                                        dialog.dismiss();
                                        new AlertDialog.Builder(SpeechRecognizeActivity.this)
                                                .setTitle(R.string.no_market_title)
                                                .setMessage(R.string.no_market_msg)
                                                .setNegativeButton(R.string.no_market_cancel,
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(
                                                                    DialogInterface dialog,
                                                                    int which) {
                                                            }
                                                        })
                                                .setPositiveButton(R.string.no_market_download,
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(
                                                                    DialogInterface dialog,
                                                                    int which) {
                                                                Intent intent = new Intent(
                                                                        Intent.ACTION_VIEW);
                                                                intent.setData(Uri
                                                                        .parse("http://m.wandoujia.com/apps/com.google.android.voicesearch"));
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
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            try {
                new recognizeResultTask().execute(matches);
            } catch (RejectedExecutionException ex) {
                ex.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private ArrayList<String> removeDuplicateWithOrder(ArrayList<String> list) {
        HashSet<String> hashSet = new HashSet<String>();
        List<String> newlist = new ArrayList<String>();

        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
            String element = iterator.next();
            if (hashSet.add(element)) {
                newlist.add(element);
            }
        }

        list.clear();
        list.addAll(newlist);
        return list;
    }

    private class recognizeResultTask extends AsyncTask<ArrayList<String>, Void, ArrayList<String>> {

        private String formatRecognizeData(String source) {
            String temp = source.trim().toUpperCase().replace(" ", "");
            if (temp.length() > 4) {
                temp = temp.substring(0, 4);
            }
            if (temp.endsWith("·")) {
                temp = temp.replace("·", "");
            }
            String regEx = "^[0-9]{1,3}[A-Z]?$";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(temp);
            if (m.matches()) {
                return temp;
            }
            return null;
        }

        @Override
        protected ArrayList<String> doInBackground(ArrayList<String>... params) {
            ArrayList<String> result = new ArrayList<String>();
            for (String recognize : params[0]) {
                String busLineNo = formatRecognizeData(recognize);
                Log.d("debug", "lisy=======bus = " + busLineNo);
                if (busLineNo != null) {
                    result.add(busLineNo);
                }
            }
            return removeDuplicateWithOrder(result);
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            for (String temp : result) {
                Log.d("debug", "lisy============str = " + temp);
            }
            mTextView.setAdapter(null);
            if (result.size() > 1) {
                mTextView.setText(result.get(0));
                result.remove(0);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        SpeechRecognizeActivity.this, android.R.layout.simple_dropdown_item_1line,
                        result);
                mTextView.setAdapter(adapter);
                mTextView.showDropDown();
            } else if (result.size() == 1) {
                mTextView.setText(result.get(0));
            } else {
                Toast.makeText(SpeechRecognizeActivity.this, "No matches bus number",
                        Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(result);
        }

    }

}
