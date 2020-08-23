package com.example.android.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.shoppinglist.model.SLItem;

public class SLItemDetailsActivity extends AppCompatActivity {

    private SLItem currentSLItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slitem_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentSLItem = getIntent().getParcelableExtra(MainActivity.EXTRA_SL_ITEM);

        EditText txtTitle = findViewById(R.id.sl_item_details_title);
        txtTitle.setText(currentSLItem.getTitle());

        EditText txtDescription = findViewById(R.id.sl_item_details_description);
        txtDescription.setText(currentSLItem.getDescription());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
