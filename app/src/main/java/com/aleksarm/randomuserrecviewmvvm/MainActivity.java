package com.aleksarm.randomuserrecviewmvvm;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.aleksarm.randomuserrecviewmvvm.adapter.CustomAdapter;
import com.aleksarm.randomuserrecviewmvvm.viewmodel.UsersViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  UsersViewModel usersViewModel;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        int con = checkInternetConnection();
        if (con == 1) {
            intiProgressDialog();
            progressDoalog.show();

            usersViewModel.getLiveData().observe(this, new Observer<ArrayList<UsersViewModel>>() {
                @Override
                public void onChanged(@Nullable ArrayList<UsersViewModel> usersViewModels) {
                    customAdapter = new CustomAdapter(MainActivity.this, usersViewModels);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(customAdapter);
                    progressDoalog.dismiss();
                }
            });
        }

    }

    private void intiProgressDialog() {
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("loading data....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private int checkInternetConnection(){
        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr != null ? conMgr.getActiveNetworkInfo() : null;
        if (netInfo == null){
            new AlertDialog.Builder(MainActivity.this, android.app.AlertDialog.THEME_HOLO_LIGHT)
                    .setTitle(getResources().getString(R.string.con_warr))
                    .setIcon(R.drawable.ic_exclamation)
                    .setMessage(getResources().getString(R.string.connection_error))
                    .setPositiveButton("Cancel", null)
                    .setNegativeButton(getApplicationContext().getResources().getString(R.string.open_network_settings), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            // TODO Auto-generated method stub
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                            startActivity(intent);
                            //get gps
                        }
                    }).show();
            return  -1;
        }
        return 1;
    }
}
