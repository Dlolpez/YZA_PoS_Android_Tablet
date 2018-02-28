package com.pos.yza.yzapos.managetransactions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.pos.yza.yzapos.Injection;
import com.pos.yza.yzapos.R;
import com.pos.yza.yzapos.data.source.TransactionsRepository;
import com.pos.yza.yzapos.newtransaction.NewTransaction;
import com.pos.yza.yzapos.newtransaction.cart.CartFragment;
import com.pos.yza.yzapos.newtransaction.cart.CartPresenter;
import com.pos.yza.yzapos.newtransaction.categoryselection.CategorySelectionPresenter;
import com.pos.yza.yzapos.newtransaction.customerdetails.CustomerDetailsPresenter;
import com.pos.yza.yzapos.newtransaction.payment.PaymentPresenter;
import com.pos.yza.yzapos.newtransaction.productselection.ProductSelectionPresenter;
import com.pos.yza.yzapos.util.ActivityUtils;

public class ManageTransactionsActivity extends AppCompatActivity {

    private ManageTransactionsPresenter mManageTransactionsPresenter;

    private final String MGMTTRANSACTIONS = "MANAGE_TRANSACTIONS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_transactions);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle(R.string.view_transaction);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ManageTransactionsFragment manageTransactionsFragment =
                (ManageTransactionsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (manageTransactionsFragment == null) {
            // Create the fragment
            manageTransactionsFragment = ManageTransactionsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), manageTransactionsFragment, R.id.contentFrame,
                    MGMTTRANSACTIONS,false);
        }

        // Create the presenter
        mManageTransactionsPresenter =
                new ManageTransactionsPresenter(Injection.provideTransactionsRepository(this)
                        ,manageTransactionsFragment);

    }

    @Override
    public boolean onSupportNavigateUp () {
        onBackPressed();
        return true;
    }
}