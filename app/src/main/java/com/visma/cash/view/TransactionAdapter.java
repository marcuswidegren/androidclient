package com.visma.cash.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.visma.cash.restmodel.Transaction;

import java.util.List;


final class TransactionAdapter extends BaseAdapter{

    private final List<Transaction> transactions;
    private final Context context;

    TransactionAdapter(List<Transaction> transactions, Context context) {
        this.transactions = transactions;
        this.context = context;
    }

    @Override
    public int getCount() {
        return transactions.size();
    }

    @Override
    public Object getItem(int i) {
        return transactions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return transactions.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView timeView = new TextView(context);
        timeView.setText(transactions.get(i).getTimestamp().toString("EEE' 'MMM-dd' 'hh:mm:ss"));
        timeView.setTextColor(Color.BLACK);

        TextView seperatorView = new TextView(context);
        seperatorView.setText(transactions.get(i).getCategory());
        seperatorView.setTextColor(Color.BLACK);

        TextView amountView = new TextView(context);
        amountView.setText(transactions.get(i).getAmount().toString());
        amountView.setTextColor(Color.BLACK);

        layout.addView(timeView);
        layout.addView(seperatorView);
        layout.addView(amountView);
        return layout;
    }
}
