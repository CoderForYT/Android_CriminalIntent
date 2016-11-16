package criminalintent.android.bignerdranch.com.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public class CrimeActivity extends SingleFragmentActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_crime);
//        setContentView(R.layout.activity_fragment);
//
//        // 获取 FragmentManager 管理者
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//        if (fragment == null){ // 创建fragment 并添加到FragmentManager 的事务中
//            fragment = new CrimeFragment();
////            FragmentManager.beginTransaction()方法创建并返回FragmentTransaction实例。
//            fm.beginTransaction().add(R.id.fragment_container, fragment).commit(); // beginTransaction 开始事务
//        }
//
//    }

    // 实现协议
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }



}
