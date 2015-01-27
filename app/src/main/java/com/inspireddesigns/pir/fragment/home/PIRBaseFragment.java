package com.inspireddesigns.pir.fragment.home;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;

import com.inspireddesigns.pir.R;

/**
 * Created by Brad Siegel on 1/27/15.
 */
public class PIRBaseFragment extends Fragment {

    protected AlertDialog createErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getActivity().getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Closes dialog and remains on current page
                    }
                });
        return builder.create();
    }
}
