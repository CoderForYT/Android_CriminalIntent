package criminalintent.android.bignerdranch.com.criminalintent;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zkhk on 2016/11/14.
 */

public class CrimeFragment extends Fragment {


    private Crime mCrime;
    private EditText mTitleField; // 文本输入框
    private Button mDateButton; // 时间日期
    private CheckBox mSolvedCheckBox; // 时间是否处理

    @Override
    // onCreateView(...)方法的实现代码，从fragment_crime.xml布局中实例化并返回视图
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        mCrime = new Crime();
    }


    //    fragment 的 视 图 是 直 接 通 过 调 用 LayoutInflater. inflate(...)方法并传入布局的资源ID生成的。第二个参数是视图的父视图，我们通常需要父 视图来正确配置组件。第三个参数告知布局生成器是否将生成的视图添加给父视图。
    // 等同与Activity 的 onCreate
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("onCreateView", "onCreateView");

        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        mDateButton = (Button)view.findViewById(R.id.crime_date);
//        mDateButton.setText(mCrime.getDate().toString());

        Date date = new Date();

        SimpleDateFormat sdformat = new SimpleDateFormat("EE dd, yyyy");//24小时制
        String LgTime = sdformat.format(date);
        mDateButton.setText(LgTime);



        mDateButton.setEnabled(false); //   用按钮可以确保它不响应用户的单击事件。 用后，按钮的外观样式也会发生改变(变为 灰色)，表明它已处于 用状态。

        mSolvedCheckBox = (CheckBox)view.findViewById(R.id.crime_Solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isSolved) {
                mCrime.setSolved(isSolved);
            }
        });

        mTitleField = (EditText)view.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }
}
