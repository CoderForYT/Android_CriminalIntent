package criminalintent.android.bignerdranch.com.criminalintent;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by zkhk on 2016/11/14.
 */

public class CrimeFragment extends Fragment {


    private Crime mCrime;
    private EditText mTitleField; // 文本输入框
    private Button mDateButton; // 时间日期
    private CheckBox mSolvedCheckBox; // 时间是否处理

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    // 暴露接口, 为了与activity解耦, 变成与Acvity没有关系的fragment;
    public static CrimeFragment newInstance(UUID crimeId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment crimeFragment = new CrimeFragment();
        crimeFragment.setArguments(args);
        return crimeFragment;
    }

    @Override
    // onCreateView(...)方法的实现代码，从fragment_crime.xml布局中实例化并返回视图
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        // 取对象用 :getSerializableExtra;
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    //    fragment 的 视 图 是 直 接 通 过 调 用 LayoutInflater. inflate(...)方法并传入布局的资源ID生成的。第二个参数是视图的父视图，我们通常需要父 视图来正确配置组件。第三个参数告知布局生成器是否将生成的视图添加给父视图。
    // 等同与Activity 的 onCreate
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("onCreateView", "onCreateView");

        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        mDateButton = (Button)view.findViewById(R.id.crime_date);
        updateDate();

        // 显示指定的格式 :
//        final Date date = new Date();
//        SimpleDateFormat sdformat = new SimpleDateFormat("EE dd, yyyy");//24小时制
//        String LgTime = sdformat.format(date);
//        mDateButton.setText(LgTime);


//        mDateButton.setEnabled(false); //   用按钮可以确保它不响应用户的单击事件。 用后，按钮的外观样式也会发生改变(变为 灰色)，表明它已处于 用状态。
        mDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();

                DialogFragment dialogFragment = DatePickerFragment.newInstance(mCrime.getDate());
//                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                // 两种展示对话框的的方式
                dialogFragment.show(fm, DIALOG_DATE);
//                dialogFragment.show(fm.beginTransaction(), DIALOG_DATE);
            }
        });


        mSolvedCheckBox = (CheckBox)view.findViewById(R.id.crime_Solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isSolved) {
                mCrime.setSolved(isSolved);
            }
        });

        mTitleField = (EditText)view.findViewById(R.id.crime_title);
        mTitleField.setText( mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override // 更新字符串
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        return view;
    }

    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
    }
}
3