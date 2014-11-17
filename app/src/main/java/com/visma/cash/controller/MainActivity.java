package com.visma.cash.controller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.Arrays;

import com.example.widegmar.myapplication.R;
import com.visma.cash.controller.rest.Controller;
import com.visma.cash.model.AccountModel;
import com.visma.cash.model.Model;
import com.visma.cash.model.Observer;
import com.visma.cash.restmodel.Account;
import com.visma.cash.restmodel.Transaction;
import com.visma.cash.view.TransactionListView;

public final class MainActivity extends Activity implements Observer<AccountModel> {

    private TransactionListView listView;
    private AccountModel model;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (TransactionListView)findViewById(R.id.transaction_list);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                controller.deleteTransaction((Transaction)adapterView.getItemAtPosition(i));
                return true;
            }
        });
        model = Model.newModel();
        controller = new Controller(model, getApplicationContext());
        model.addObserver(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void sendTransaction(View view) {
        EditText textField = (EditText)findViewById(R.id.edit_message);
        EditText categoryTextField = (EditText) findViewById(R.id.edit_category);
        if(textField.getText().toString().isEmpty()) {
            return;
        }
        BigDecimal amount = new BigDecimal(textField.getText().toString());
        controller.addTransaction(Transaction.newTransactionInEuros(amount, categoryTextField.getText().toString()));
        textField.getText().clear();
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

    @Override
    public void update(AccountModel observable) {
        Log.i(this.getClass().getName(), "Model changed, updating view...");
        final Account currentAccount = model.getAccount();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.update(currentAccount);
                getSharedPreferences("account_id", 0).edit().putLong("account_id", currentAccount.getId()).commit();
                TextView view = (TextView)findViewById(R.id.account_status);
                view.setText("Current balance: " + currentAccount.getAmount().toString());
                view.setTextSize(TypedValue.COMPLEX_UNIT_IN, 0.1f);
                TextView avView = (TextView) findViewById(R.id.server_status);
                avView.setText("" + model.isWebServerAvailable());
            }
        });
    }
}
