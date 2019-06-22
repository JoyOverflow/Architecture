package ouyj.hyena.com.loginmvp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import ouyj.hyena.com.loginmvp.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class InterestFragment extends BaseFragment {

    public InterestFragment() {
        // Required empty public constructor
    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_interest, container, false);
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getInterest();
    }
    @Override
    public void setTitle() {
        if (mActionBar != null) {
            mActionBar.setTitle("不许无聊");
        }
    }
}
