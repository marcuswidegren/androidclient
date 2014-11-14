package com.visma.cash.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.visma.cash.restmodel.Account;

import java.util.ArrayList;
import java.util.List;

public final class TransactionListView extends ListView {

    public TransactionListView(Context context) {
        super(context);
    }

    public TransactionListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TransactionListView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
    }

    public void update(Account account) {
        this.setAdapter(new TransactionAdapter(new ArrayList<>(account.getTransactions()), this.getContext()));
    }

}
