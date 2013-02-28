
package com.study.tools.items;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.study.tools.R;

public class NfcReaderActivity extends Activity {
    NfcAdapter mNfcAdapter;
    TextView mNfcTagInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc_reader);
        mNfcTagInfo = (TextView)findViewById(R.id.nfc_tag_info);

        try {
            mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
            if (mNfcAdapter == null){
                throw new NullPointerException();
            }
        } catch (UnsupportedOperationException ex){
            Toast.makeText(this, R.string.no_support_nfc, Toast.LENGTH_LONG).show();
            finish();
            return;
        } catch (NullPointerException ex) {
            Toast.makeText(this, R.string.no_support_nfc, Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }

    @Override
    protected void onResume() {
        if (!mNfcAdapter.isEnabled()){
            Toast.makeText(this, R.string.nfc_disable, Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_NFCSHARING_SETTINGS));
        }
        // If get the ACTION_TECH_DISCOVERED action
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent().getAction())) {
            //handle intent
            processIntent(getIntent());
        }
        super.onResume();
    }

    private void processIntent(Intent intent) {
        // Get the TAG
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        for (String tech : tagFromIntent.getTechList()){
            Log.d("debug","lisy=============tech:"+tech);
        }
        boolean auth = false;
        // Read the TAG
        MifareClassic mfc = MifareClassic.get(tagFromIntent);
        try {
            String metaInfo = "";
            //Enable I/O operations to the tag from this TagTechnology object.
            mfc.connect();
            int type = mfc.getType();// Get the type of TAG
            int sectorCount = mfc.getSectorCount();// Get the counts of sector
            String typeS = "";
            switch (type) {
                case MifareClassic.TYPE_CLASSIC:
                    typeS = "TYPE_CLASSIC";
                    break;
                case MifareClassic.TYPE_PLUS:
                    typeS = "TYPE_PLUS";
                    break;
                case MifareClassic.TYPE_PRO:
                    typeS = "TYPE_PRO";
                    break;
                case MifareClassic.TYPE_UNKNOWN:
                    typeS = "TYPE_UNKNOWN";
                    break;
            }
            metaInfo += "Card Type: " + typeS + "\nSector counts: " + sectorCount
                    + "\nBlock counts: " + mfc.getBlockCount() + "\nMemory size: " + mfc.getSize()
                    + "B\n";
            for (int j = 0; j < sectorCount; j++) {
                // Authenticate a sector with key A
                auth = mfc.authenticateSectorWithKeyA(j, MifareClassic.KEY_DEFAULT);
                if (!auth) {
                    auth = mfc.authenticateSectorWithKeyA(j, MifareClassic.KEY_MIFARE_APPLICATION_DIRECTORY);
                }
                if (!auth) {
                    auth = mfc.authenticateSectorWithKeyA(j, MifareClassic.KEY_NFC_FORUM);
                }
                if (!auth) {
                    auth = mfc.authenticateSectorWithKeyB(j, MifareClassic.KEY_DEFAULT);
                }
                if (!auth) {
                    auth = mfc.authenticateSectorWithKeyB(j, MifareClassic.KEY_MIFARE_APPLICATION_DIRECTORY);
                }
                if (!auth) {
                    auth = mfc.authenticateSectorWithKeyB(j, MifareClassic.KEY_NFC_FORUM);
                }
                int bCount;
                int bIndex;
                if (auth) {
                    metaInfo += "Sector " + j + ": Auth Success\n";
                    // Read the block in sector
                    bCount = mfc.getBlockCountInSector(j);
                    bIndex = mfc.sectorToBlock(j);
                    for (int i = 0; i < bCount; i++) {
                        byte[] data = mfc.readBlock(bIndex);
                        metaInfo += "Block " + bIndex + " £º " + bytesToHexString(data) + "\n";
                        bIndex++;
                    }
                } else {
                    metaInfo += "Sector " + j + ":Auth failed\n";
                }
            }
            mNfcTagInfo.setText(metaInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("0x");
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
            System.out.println(buffer);
            stringBuilder.append(buffer);
        }
        return stringBuilder.toString();
    }

}
