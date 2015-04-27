package io.migrant.pir.fragment.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

import io.migrant.pir.R;

/**
 * Base Fragment that provides common functionality.
 * <p/>
 * Created by Brad Siegel on 1/27/15.
 */
public abstract class RMBaseFragment extends Fragment {

    protected void createErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getActivity().getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create().show();
    }

    protected void createDialogAndStartActivity(String message, final Intent intent, final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getActivity().getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(intent);
                        ActionBarActivity activity = (ActionBarActivity) context;
                        activity.finish();

                    }
                });
        builder.create().show();
    }

    public abstract boolean isAllowedBackPress();


}
