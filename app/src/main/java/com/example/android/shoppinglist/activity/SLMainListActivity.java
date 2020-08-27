package com.example.android.shoppinglist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.shoppinglist.R;
import com.example.android.shoppinglist.model.SLItem;
import com.example.android.shoppinglist.service.ISLItemService;
import com.example.android.shoppinglist.service.SLItemInMemForTestService;
import com.example.android.shoppinglist.service.SLItemOfflineService;

import java.util.LinkedList;
import java.util.List;

public class SLMainListActivity extends AppCompatActivity {

    public static final String EXTRA_SL_ITEM = "com.example.android.shoppinglist.extra.SL_ITEM";
    public static final int REQUEST_OPEN_SL_ITEM_DETAILS = 1;
    public static final int REQUEST_OPEN_SL_ITEM_DETAILS_NEW_ITEM = 2;

    private List<SLItem> slItems = new LinkedList<>();

    //private ISLItemService slItemService = new SLItemInMemForTestService().initSampleData();
    private ISLItemService slItemService;
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

        slItemService = new SLItemOfflineService(getApplicationContext());
        slItems = slItemService.getAll();

        recyclerView = findViewById(R.id.main_list_view);
        adapter = new SLAdapter(this, slItemService.getAll(),
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
                        slItemService.update(responseSLItem);
                        adapter.notifyItemChanged(slItems.lastIndexOf(responseSLItem));
                    }
                    break;

                case REQUEST_OPEN_SL_ITEM_DETAILS_NEW_ITEM:
                    slItemService.insert(responseSLItem);
                    adapter.notifyItemInserted(slItems.lastIndexOf(responseSLItem));
                    //recyclerView.smoothScrollToPosition(slItems.size() - 1);
                    break;

                default:

            }

        }
    }

    private void performDelete(SLItem slItem) {
        int index = slItems.lastIndexOf(slItem);
        slItemService.remove(slItem);
        adapter.notifyItemRemoved(index);
    }

}
