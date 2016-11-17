package criminalintent.android.bignerdranch.com.criminalintent;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment; //使用拓展库的Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by zkhk on 2016/11/17.
 */

public class DatePickerFragment extends DialogFragment {


//    接口(fluent interface) = 链式编程
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View picker = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        return new AlertDialog.Builder(getActivity())
                .setView(picker)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();

    }
//    dialogSetPositiveButtonText('')这是用来显示确定按钮的
//    dialogSetNeutralButtonText('')这个是用来显示中立按钮的，
//    dialogSetNegativeButtonText('')这个是用来显示否定按钮的，
}
