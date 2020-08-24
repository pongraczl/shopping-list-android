package com.example.android.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.shoppinglist.model.SLItem;

public class SLItemDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_SL_ITEM_REPLY = "com.example.android.shoppinglist.extra.SL_ITEM_REPLY";

    //private SLItem currentSLItem;
    private long id;

    private EditText txtTitle;
    private EditText txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slitem_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SLItem currentSLItem = getIntent().getParcelableExtra(MainActivity.EXTRA_SL_ITEM);

        id = currentSLItem.getId();

        txtTitle = findViewById(R.id.sl_item_details_title);
        txtTitle.setText(currentSLItem.getTitle());
        txtTitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    finishAndReplyWithSLItem();
                }
                return true;
            }
        });

        txtDescription = findViewById(R.id.sl_item_details_description);
        txtDescription.setText(currentSLItem.getDescription());
    }

    private SLItem readSLItem() {
        SLItem slItem = new SLItem();
        slItem.setId(id);

        String title = txtTitle.getText().toString().trim();
        if (title.isEmpty()) title = getString(R.string.SLItem_title_emptyTitleText);
        slItem.setTitle(title);

        slItem.setDescription(txtDescription.getText().toString());
        return slItem;
    }

    private void finishAndReplyWithSLItem () {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_SL_ITEM_REPLY, readSLItem());
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelable(MainActivity.EXTRA_SL_ITEM, readSLItem());
    }

    @Override
    public boolean onSupportNavigateUp() {
        setResult(RESULT_CANCELED);
        finish();
        return true;
    }
}
