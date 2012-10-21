
package com.study.tools.items;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.study.tools.R;

public class SetErrorActivity extends Activity {
    private TextView mDisplay;

    private Button btn_submit;

    private EditText input_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_error);
        setupView();
    }

    private void setupView() {
        mDisplay = (TextView)findViewById(R.id.display_text);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        input_text = (EditText)findViewById(R.id.edittext_input);
        btn_submit.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                if (!TextUtils.isEmpty(input_text.getText())) {
                    mDisplay.setText(input_text.getText());
                } else {
                    input_text.setError(getResources().getString(R.string.empty_input_tips));
                }
            }
        });
    }
}
