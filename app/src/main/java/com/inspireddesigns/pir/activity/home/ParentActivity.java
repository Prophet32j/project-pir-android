package com.inspireddesigns.pir.activity.home;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.inspireddesigns.pir.R;

public class ParentActivity extends ActionBarActivity {

    private int parentId;
    private String mToken;
    private String mEmail;
    private static final String URL_PREFIX = "http://pir-node.herokuapp.com/parents/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
    }

//    private void setupFragment() {
//        CreateUserFragment fragment = CreateUserFragment.newInstance();
//        getFragmentManager().beginTransaction().replace(R.id.content, fragment).disallowAddToBackStack().commitAllowingStateLoss();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parent, menu);
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
}
