package io.migrant.pir.activity.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.migrant.pir.R;
import io.migrant.pir.application.PIRDatabaseHelper;
import io.migrant.pir.callback.ChangeFragmentCallback;
import io.migrant.pir.controller.ApplicationController;
import io.migrant.pir.controller.LogoutController;
import io.migrant.pir.fragment.home.ConfirmEmailFragment;
import io.migrant.pir.fragment.home.CreateUserFragment;
import io.migrant.pir.fragment.parent.ParentRegistrationFragment;

public class RegistrationActivity extends RMBaseActivity implements ChangeFragmentCallback {

    private String mFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        if (savedInstanceState == null) {
            CreateUserFragment fragment = CreateUserFragment.newInstance();
            setupFragment(fragment, CreateUserFragment.TAG);
        }
        setupToolbar();


    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.new_user_registration));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setupFragment(Fragment fragment, String tag) {
        mFragmentTag = tag;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (tag) {
            case CreateUserFragment.TAG:
                ft.replace(R.id.content, fragment).addToBackStack(tag).commit();
                break;
            case ParentRegistrationFragment.TAG:
                ft.replace(R.id.content, fragment).addToBackStack(tag).commit();
                break;
            case ConfirmEmailFragment.TAG:
                ft.replace(R.id.content, fragment).addToBackStack(tag);
                break;
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_parent, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {
//        if(mFragmentTag.equals(ConfirmEmailFragment.TAG)){
//            Intent intent = new Intent(this,LoginActivity.class);
//            startActivity(intent);
//        }

        Log.i(ApplicationController.TAG, "Tag: " + mFragmentTag.toString());
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onChangeFragment(Fragment fragment, String tag) {
        setupFragment(fragment, tag);
    }
}
