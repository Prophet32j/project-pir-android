package io.migrant.pir.activity.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import io.migrant.pir.R;
import io.migrant.pir.application.PIRDatabaseHelper;

/**
 * Base Activity that provides common functionality including logout.
 *
 * Created by Brad Siegel on 4/26/15.
 */
public class RMBaseActivity extends ActionBarActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                //TODO send user to settings screen
                break;
            case R.id.action_logout:
                logout(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Logs the user out of the application by deleting the user's token in the SQLiteDB. Upon logout,
     * user is sent back to the LoginActivity. Flags are added to the intent the prevent the user from returning
     * to the previous screen after logging out.
     *
     * @param context the activity that the user is logging out from.
     */
    public void logout(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(getString(R.string.logout_confirmation));
        builder.setCancelable(true);
        builder.setPositiveButton(getString(R.string.logout_dialog_positive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean tokenDeleted = PIRDatabaseHelper.getInstance(context).deleteToken();
                if (tokenDeleted) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    if (Build.VERSION.SDK_INT >= 11) {
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    } else {
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    }
                    startActivity(intent);
                }
            }
        });
        builder.setNegativeButton(getString(R.string.logout_dialog_negative), null);
        builder.show();
    }
}
