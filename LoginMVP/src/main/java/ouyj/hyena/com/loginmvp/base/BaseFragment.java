package ouyj.hyena.com.loginmvp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ouyj.hyena.com.loginmvp.DetailActivity;
import ouyj.hyena.com.loginmvp.DetailFragment;
import ouyj.hyena.com.loginmvp.R;
import ouyj.hyena.com.loginmvp.contract.MainContract;
import ouyj.hyena.com.loginmvp.data.StoriesEntity;
import ouyj.hyena.com.loginmvp.data.ZhiHuNewsAdapter;

/**
 *
 */
public class BaseFragment extends Fragment implements MainContract.View {

    @BindView(R.id.lstNews)
    ListView listView;

    protected MainContract.Presenter mPresenter;
    protected ActionBar mActionBar;
    private ZhiHuNewsAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main_0, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    /**
     * 已创建完片段视图
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mAdapter = new ZhiHuNewsAdapter(getContext());
        listView.setAdapter(mAdapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isAdded()) {
            mActionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            setTitle();
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
    @Override
    public void refresh(final List<StoriesEntity> list) {
        mAdapter.setNewsList(list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailFragment.STORY_ID, list.get(position).getId());
                intent.putExtra(DetailFragment.STORY_TITLE, list.get(position).getTitle());
                startActivity(intent);
            }
        });
    }
    @Override
    public void setTitle() {
        if (mActionBar != null) {
            mActionBar.setTitle(R.string.app_name);
        }
    }
}
