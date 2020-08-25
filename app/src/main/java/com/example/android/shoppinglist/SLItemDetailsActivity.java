package com.example.android.shoppinglist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.shoppinglist.model.SLItem;

public class SLItemDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_SL_ITEM_REPLY = "com.example.android.shoppinglist.extra.SL_ITEM_REPLY";
    public static final String EXTRA_SL_ITEM_DELETE = "com.example.android.shoppinglist.extra.SL_ITEM_DELETE";

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

        if (currentSLItem == null) {
            currentSLItem = new SLItem(-1, "New item","");
            Button btnDelete = findViewById(R.id.sl_item_details_btn_delete);
            btnDelete.setVisibility(View.INVISIBLE);
        }

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

    public void finishAndReplyWithSLItem(View view) {
        finishAndReplyWithSLItem();
    }

    public void deleteSLItem(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Are you sure you want to delete this item?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishWithDeleteSLItem();
                    }})
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    private void finishAndReplyWithSLItem () {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_SL_ITEM_REPLY, readSLItem());
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private void finishWithDeleteSLItem() {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_SL_ITEM_REPLY, readSLItem());
        replyIntent.putExtra(EXTRA_SL_ITEM_DELETE, true);
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
