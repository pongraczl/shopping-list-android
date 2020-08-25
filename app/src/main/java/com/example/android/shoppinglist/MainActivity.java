package com.example.android.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.shoppinglist.model.SLItem;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_SL_ITEM = "com.example.android.shoppinglist.extra.SL_ITEM";
    public static final int REQUEST_OPEN_SL_ITEM_DETAILS = 1;
    public static final int REQUEST_OPEN_SL_ITEM_DETAILS_NEW_ITEM = 2;

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
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(view.getContext(), SLItemDetailsActivity.class);
                startActivityForResult(intent, REQUEST_OPEN_SL_ITEM_DETAILS_NEW_ITEM);
            }
        });

        sampleDataInit();   //TODO: remove later

        recyclerView = findViewById(R.id.main_list_view);
        adapter = new SLAdapter(this, slItems,
                new OnItemClickListener() {
                    @Override
                    public void onItemClicked(SLItem selectedItem) {
                        openSLItemDetails(selectedItem);
                    }
                },
                new OnItemClickListener() {
                    @Override
                    public void onItemClicked(SLItem selectedItem) {
                        performDelete(selectedItem);
                    }
                });
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {

            SLItem responseSLItem = data.getParcelableExtra(SLItemDetailsActivity.EXTRA_SL_ITEM_REPLY);
            if (responseSLItem == null) return;

            switch(requestCode) {
                case REQUEST_OPEN_SL_ITEM_DETAILS:
                    if (data.getBooleanExtra(SLItemDetailsActivity.EXTRA_SL_ITEM_DELETE, false)) {
                        performDelete(responseSLItem);
                    } else {
                        performUpdate(responseSLItem);
                    }
                    break;
                case REQUEST_OPEN_SL_ITEM_DETAILS_NEW_ITEM:
                    performInsert(responseSLItem);
                    break;
                default:
            }

        }
    }


    private void performUpdate(SLItem updatedSLItem) {
        //TODO: persist, synch
        for (int index = 0; index < slItems.size(); index++) {  //TODO: remove it later
            if (slItems.get(index).getId() == updatedSLItem.getId()) {
                slItems.set(index, updatedSLItem);
                adapter.notifyItemChanged(index);
                break;
            }
        }
    }

    private void performDelete(SLItem deletedSLItem) {
        //TODO: persist, synch
        for (int index = 0; index < slItems.size(); index++) {  //TODO: remove it later
            if (slItems.get(index).getId() == deletedSLItem.getId()) {
                slItems.remove(index);
                adapter.notifyItemRemoved(index);
                break;
            }
        }
    }

    private void performInsert(SLItem newItem) {
        //TODO: persist, synch
        slItems.add(newItem);
        adapter.notifyItemInserted(slItems.lastIndexOf(newItem));
        recyclerView.smoothScrollToPosition(slItems.size() - 1);
    }


    private void sampleDataInit() {
        slItems.add(
                new SLItem(1,
                        "kenyér",
                        "Ne legyen nagyon megégve, illetve nézd meg, hogy elég puha-e"
                )
        );
        slItems.add(
                new SLItem(2,
                        "margarin",
                        "Rama vagy Flora"
                )
        );
        slItems.add(
                new SLItem(3,
                        "sör",
                        ""
                )
        );

        for (int csoki = 1; csoki < 14; csoki++) {
            slItems.add(
                    new SLItem( 100 + csoki,
                            "csoki" + csoki,
                            "többet is"
                    )

            );
        }
    }
}
