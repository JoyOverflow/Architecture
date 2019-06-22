package ouyj.hyena.com.loginmvp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ouyj.hyena.com.loginmvp.contract.DetailContract;
import ouyj.hyena.com.loginmvp.presenter.DetailPresenter;

public class DetailActivity extends AppCompatActivity implements
        DetailFragment.OnFragmentInteractionListener {

    private DetailContract.Presenter mPresenter;
    private DetailFragment mDetailFragment;
    private FragmentManager mFragmentManager;

    private int mStoryId;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getExtra();

        mFragmentManager = getSupportFragmentManager();
        mDetailFragment = (DetailFragment) mFragmentManager.findFragmentById(R.id.webView_content);
        if (null == mDetailFragment) {
            mDetailFragment = DetailFragment.newInstance(mStoryId, mTitle);
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.webView_content, mDetailFragment);
        transaction.commitAllowingStateLoss();

        mPresenter = new DetailPresenter(mDetailFragment);
    }
    private void getExtra() {
        if (getIntent().hasExtra(DetailFragment.STORY_ID)) {
            mStoryId = getIntent().getIntExtra(DetailFragment.STORY_ID, 0);
        }
        if (getIntent().hasExtra(DetailFragment.STORY_TITLE)) {
            mTitle = getIntent().getStringExtra(DetailFragment.STORY_TITLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
