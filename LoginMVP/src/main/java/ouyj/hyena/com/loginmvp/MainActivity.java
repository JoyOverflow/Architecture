package ouyj.hyena.com.loginmvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ouyj.hyena.com.loginmvp.data.TagStatic;
import ouyj.hyena.com.loginmvp.presenter.MainPresenter;
import ouyj.hyena.com.loginmvp.view.TabItem;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_item_main_0)
    TabItem tabItemMain0;
    @BindView(R.id.tab_item_main_1)
    TabItem tabItemMain1;
    @BindView(R.id.tab_item_main_2)
    TabItem tabItemMain2;
    @BindView(R.id.tab_item_main_3)
    TabItem tabItemMain3;
    @BindView(R.id.tab_item_main_4)
    TabItem tabItemMain4;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton floatingBar;

    private FragmentManager manager;
    private TodayFragment todayFragment;
    private InterestFragment interestFragment;
    private SafetyFragment safetyFragment;
    private SportFragment sportFragment;
    private OtherFragment otherFragment;
    private MainPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);

        //使用ToolBar来替代ActionBar
        setSupportActionBar(toolbar);

        manager = getSupportFragmentManager();
        presenter = new MainPresenter(getApplicationContext());
        tabItemMain0.performClick();
    }


    private void clearChecked() {
        tabItemMain0.setChecked(false);
        tabItemMain1.setChecked(false);
        tabItemMain2.setChecked(false);
        tabItemMain3.setChecked(false);
        tabItemMain4.setChecked(false);
    }
    @OnClick({
            R.id.tab_item_main_0, R.id.tab_item_main_1, R.id.tab_item_main_2,
            R.id.tab_item_main_3, R.id.tab_item_main_4, R.id.fab
    })
    public void onClick(View view) {
        clearChecked();
        switch (view.getId()) {
            case R.id.tab_item_main_0:
                tabItemMain0.setChecked(true);
                showFragment(TagStatic.TAG_FRAGMENT_TODAY);
                break;
            case R.id.tab_item_main_1:
                tabItemMain1.setChecked(true);
                showFragment(TagStatic.TAG_FRAGMENT_INTEREST);
                break;
            case R.id.tab_item_main_2:
                tabItemMain2.setChecked(true);
                showFragment(TagStatic.TAG_FRAGMENT_SAFETY);
                break;
            case R.id.tab_item_main_3:
                tabItemMain3.setChecked(true);
                showFragment(TagStatic.TAG_FRAGMENT_SPORT);
                break;
            case R.id.tab_item_main_4:
                tabItemMain4.setChecked(true);
                showFragment(TagStatic.TAG_FRAGMENT_OTHER);
                break;
            case R.id.fab:
                Intent intent = new Intent(
                        MainActivity.this,
                        PagerActivity.class
                );
                startActivity(intent);
                break;
        }
    }
    public void showFragment(int tag) {
        if (manager != null) {
            FragmentTransaction transaction = manager.beginTransaction();
            hideFragments();
            switch (tag) {
                case TagStatic.TAG_FRAGMENT_TODAY:
                    todayFragment = (TodayFragment)
                            manager.findFragmentByTag(TagStatic.TAG_FRAGMENT_TODAY + "");
                    if (todayFragment == null) {
                        todayFragment = new TodayFragment();
                        transaction.add(R.id.fragment_content, todayFragment, tag + "");
                    }
                    else
                        transaction.show(todayFragment);

                    presenter.setView(todayFragment);
                    break;
                case TagStatic.TAG_FRAGMENT_INTEREST:
                    interestFragment = (InterestFragment) manager.findFragmentByTag(TagStatic.TAG_FRAGMENT_INTEREST + "");
                    if (interestFragment == null) {
                        interestFragment = new InterestFragment();
                        transaction.add(R.id.fragment_content, interestFragment, tag + "");
                    }
                    else
                        transaction.show(interestFragment);

                    presenter.setView(interestFragment);
                    break;
                case TagStatic.TAG_FRAGMENT_SAFETY:
                    safetyFragment = (SafetyFragment) manager.findFragmentByTag(TagStatic.TAG_FRAGMENT_SAFETY + "");
                    if (safetyFragment == null) {
                        safetyFragment = new SafetyFragment();
                        transaction.add(R.id.fragment_content, safetyFragment, tag + "");
                    } else {
                        transaction.show(safetyFragment);
                    }
                    presenter.setView(safetyFragment);
                    break;
                case TagStatic.TAG_FRAGMENT_SPORT:
                    sportFragment = (SportFragment) manager.findFragmentByTag(TagStatic.TAG_FRAGMENT_SPORT + "");
                    if (sportFragment == null) {
                        sportFragment = new SportFragment();
                        transaction.add(R.id.fragment_content, sportFragment, tag + "");
                    } else {
                        transaction.show(sportFragment);
                    }
                    presenter.setView(sportFragment);
                    break;
                case TagStatic.TAG_FRAGMENT_OTHER:
                    otherFragment = (OtherFragment) manager.findFragmentByTag(TagStatic.TAG_FRAGMENT_OTHER + "");
                    if (otherFragment == null) {
                        otherFragment = new OtherFragment();
                        transaction.add(R.id.fragment_content, otherFragment, tag + "");
                    } else {
                        transaction.show(otherFragment);
                    }
                    presenter.setView(otherFragment);
                    break;
            }
            transaction.commitAllowingStateLoss();
        }
    }
    private void hideFragments() {
        FragmentTransaction transaction = manager.beginTransaction();
        todayFragment = (TodayFragment) manager.findFragmentByTag(TagStatic.TAG_FRAGMENT_TODAY + "");
        if (todayFragment != null) {
            transaction.hide(todayFragment);
        }
        interestFragment = (InterestFragment) manager.findFragmentByTag(TagStatic.TAG_FRAGMENT_INTEREST + "");
        if (interestFragment != null) {
            transaction.hide(interestFragment);
        }
        safetyFragment = (SafetyFragment) manager.findFragmentByTag(TagStatic.TAG_FRAGMENT_SAFETY + "");
        if (safetyFragment != null) {
            transaction.hide(safetyFragment);
        }
        sportFragment = (SportFragment) manager.findFragmentByTag(TagStatic.TAG_FRAGMENT_SPORT + "");
        if (sportFragment != null) {
            transaction.hide(sportFragment);
        }
        otherFragment = (OtherFragment) manager.findFragmentByTag(TagStatic.TAG_FRAGMENT_OTHER + "");
        if (otherFragment != null) {
            transaction.hide(otherFragment);
        }
        transaction.commitAllowingStateLoss();
    }
}
