package ouyj.hyena.com.loginmvp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import ouyj.hyena.com.loginmvp.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SafetyFragment extends BaseFragment {

    public SafetyFragment() {
        // Required empty public constructor
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_safety, container, false);
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getSafety();
    }
    @Override
    public void setTitle() {
        if (mActionBar != null) {
            mActionBar.setTitle("网络安全");
        }
    }



}
