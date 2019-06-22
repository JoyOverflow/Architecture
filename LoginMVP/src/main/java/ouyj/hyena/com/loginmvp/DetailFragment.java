package ouyj.hyena.com.loginmvp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ouyj.hyena.com.loginmvp.contract.DetailContract;

/**
 */
public class DetailFragment extends Fragment implements DetailContract.View{

    public static final String STORY_ID = "story_id";
    public static final String STORY_TITLE = "story_title";

    @BindView(R.id.story_detail_webView)
    WebView storyDetailWebView;
    @BindView(R.id.login_tool_bar)
    Toolbar loginToolBar;

    private int mStoryId;
    private String mTitle;
    private OnFragmentInteractionListener mListener;
    private DetailContract.Presenter mPresenter;


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    public DetailFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mStoryId = getArguments().getInt(STORY_ID);
            mTitle = getArguments().getString(STORY_TITLE);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getNewsDetail(mStoryId);
        initToolBar();
    }
    private void initToolBar() {
        loginToolBar.setTitle(mTitle);
        ((AppCompatActivity) getActivity()).setSupportActionBar(loginToolBar);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }






    public static DetailFragment newInstance(@NonNull int id, @Nullable String title) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(STORY_ID, id);
        args.putString(STORY_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void loadWebView(String s) {
        storyDetailWebView.loadDataWithBaseURL("file:///android_asset/", s, "text/html", "UTF-8", null);
    }
    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
