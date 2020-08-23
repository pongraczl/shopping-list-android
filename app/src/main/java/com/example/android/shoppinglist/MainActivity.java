package com.example.android.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.shoppinglist.model.SLItem;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    public static final String EXTRA_SL_ITEM = "com.example.android.shoppinglist.extra.SL_ITEM";
    public static final int REQUEST_OPEN_SL_ITEM_DETAILS = 1;

    private final List<SLItem> slItems = new LinkedList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        sampleDataInit();   //TODO: remove later

        recyclerView = findViewById(R.id.main_list_view);
        adapter = new SLAdapter(this, slItems, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openSLItemDetails(SLItem selectedItem) {
        Intent intent = new Intent(this, SLItemDetailsActivity.class);
        intent.putExtra(EXTRA_SL_ITEM, selectedItem);
        startActivityForResult(intent, REQUEST_OPEN_SL_ITEM_DETAILS);
    }

    @Override
    public void onItemClicked(SLItem selectedItem) {
        openSLItemDetails(selectedItem);
    }

    private void sampleDataInit() {
        slItems.add(
                new SLItem(
                        "kenyér",
                        "Ne legyen nagyon megégve, illetve nézd meg, hogy elég puha-e"
                )
        );
        slItems.add(
                new SLItem(
                        "margarin",
                        "Rama vagy Flora"
                )
        );
        slItems.add(
                new SLItem(
                        "sör",
                        ""
                )
        );

        for (int csoki = 1; csoki < 14; csoki++) {
            slItems.add(
                    new SLItem(
                            "csoki" + csoki,
                            "többet is"
                    )

            );
        }
    }
}
