package ouyj.hyena.com.loginmvp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ouyj.hyena.com.loginmvp.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class InterestFragment extends BaseFragment {

    public InterestFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_interest, container, false);
    }

}
