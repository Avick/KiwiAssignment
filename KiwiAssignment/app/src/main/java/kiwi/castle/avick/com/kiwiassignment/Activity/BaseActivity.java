package kiwi.castle.avick.com.kiwiassignment.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import kiwi.castle.avick.com.kiwiassignment.Fragment.SearchFragment;
import kiwi.castle.avick.com.kiwiassignment.R;

/**
 * Created by avick on 10/24/16.
 */

public class BaseActivity extends Activity {

    Toolbar mActionbarToolbar;
    TextView txtHeaderView;
    TextView txtSubHeader;
    ImageView imgSearch;
    ImageView imgMap;
    LinearLayout searchLayout;
    EditText searchText;
    private ProgressDialog mProgressDialog;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        getActionBarToolBar();
    }


    @Override
    public void onBackPressed() {

        Fragment frag = getFragmentManager().findFragmentByTag(SearchFragment.class.getSimpleName());
        if(frag != null && frag.isVisible()) {
            if (doubleBackToExitPressedOnce) {
                finish();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }
    }

    public Toolbar getActionBarToolBar() {
        if (mActionbarToolbar == null) {
            mActionbarToolbar = (Toolbar) findViewById(R.id.tool_bar);
            if (mActionbarToolbar != null) {
                setActionBar(mActionbarToolbar);
            }

            txtHeaderView = (TextView) mActionbarToolbar.findViewById(R.id.txt_header);
            imgSearch = (ImageView) mActionbarToolbar.findViewById(R.id.tool_bar_search);
            searchLayout = (LinearLayout) mActionbarToolbar.findViewById(R.id.tool_bar_layout);
            searchText = (EditText) mActionbarToolbar.findViewById(R.id.search_edt);
            imgMap = (ImageView) mActionbarToolbar.findViewById(R.id.img_map);
            txtSubHeader = (TextView) mActionbarToolbar.findViewById(R.id.txt_subheader);
            //searchText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
            //SearchView searchView = (SearchView)mActionbarToolbar.findViewById(R.id.action_search);

        }

        return mActionbarToolbar;
    }

    public void setHeaderText(String header) {
        txtHeaderView.setText(header);
    }
    public void setSubHeaderText(String subheader) {
        txtSubHeader.setText(subheader);
    }

    public ImageView getImgSearch() {
        return imgSearch;
    }

    public LinearLayout getSearchLayout() {
        return searchLayout;
    }

    public TextView getTxtHeaderView() {
        return txtHeaderView;
    }

    public EditText getSearchText() {
        return searchText;
    }

    public ImageView getImgMap() {
        return imgMap;
    }

    public TextView getTxtSubHeader() {
        return txtSubHeader;
    }

    public void showProgressDialog(String message, boolean isCancelable) {
        if (mProgressDialog == null)
            mProgressDialog = new ProgressDialog(BaseActivity.this);
        if (!mProgressDialog.isShowing()) {
            if (message == null)
                message = "Loading......." ;
            mProgressDialog.setMessage(message);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(isCancelable);
            mProgressDialog.show();
        }
    }

    public void showProgressDialog(String message) {
        showProgressDialog(message, true);
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showInstructionAlert(final String title, String message, Context context, DialogInterface.OnClickListener listener, DialogInterface.OnCancelListener cancelListener, boolean isCancellable, String positiveButtonTitle, String negButtonTittle) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle(title);
        //alertDialog.setCancelable(false);
//        if (!AppUtils.isStringDataValid(message))
//            message = "OOps!!! Something went wrong. Please contact Ziffi support";

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        if (listener == null) {
            //Setting generic button listener
            listener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        // On pressing Settings button
        alertDialog.setPositiveButton(positiveButtonTitle, listener);
        alertDialog.setNegativeButton(negButtonTittle, listener);
        if (cancelListener != null) {
            alertDialog.setOnCancelListener(cancelListener);
        }

        alertDialog.setCancelable(isCancellable);

        // Showing Alert Message
        alertDialog.show();
    }

    public void showInstructionAlert(final String title, String message, Context context, DialogInterface.OnClickListener listener, DialogInterface.OnCancelListener cancelListener) {
        showInstructionAlert(title, message, context, listener, cancelListener, true, getString(android.R.string.ok),getString(android.R.string.no));
    }
}
