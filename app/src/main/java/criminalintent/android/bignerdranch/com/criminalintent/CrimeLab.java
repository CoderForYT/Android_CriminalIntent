package criminalintent.android.bignerdranch.com.criminalintent;

import android.content.Context;
import android.text.GetChars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by zkhk on 2016/11/15.
 */

// 单例, Crime数据池

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes; // 一个可变数组

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
        // 创建100条数据
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // Every other one
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for(Crime crime : mCrimes) {
            if (crime.getID().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
