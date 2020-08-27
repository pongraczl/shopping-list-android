package com.example.android.shoppinglist.service;

import com.example.android.shoppinglist.model.SLItem;

import java.util.LinkedList;
import java.util.List;

public class SLItemInMemForTestService implements ISLItemService {

    private static final List<SLItem> slItems = new LinkedList<>();

    public SLItemInMemForTestService initSampleData() {
        if (slItems.isEmpty()) sampleDataInit();
        return this;
    }

    @Override
    public List<SLItem> getAll() {
        return slItems;
    }

    @Override
    public SLItem findById(long id) {
        SLItem currentSLItem;
        for (int index = 0; index < slItems.size(); index++) {
            currentSLItem = slItems.get(index);
            if (currentSLItem.getId() == id) {
                return currentSLItem;
            }
        }
        return null;
    }

    @Override
    public void insert(SLItem slItem) {
        slItems.add(slItem);
    }

    @Override
    public void update(SLItem slItem) {
        slItems.set( slItems.indexOf(slItem), slItem );
    }

    @Override
    public void remove(SLItem slItem) {
        slItems.remove(slItem);
    }

    public void removeAll() {
        slItems.clear();
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
