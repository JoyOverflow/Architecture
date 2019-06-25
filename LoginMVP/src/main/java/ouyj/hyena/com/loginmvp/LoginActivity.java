package ouyj.hyena.com.loginmvp;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ouyj.hyena.com.loginmvp.data.UserRepository;
import ouyj.hyena.com.loginmvp.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //在活动类布局中加载指定片段类（登录框在片段布局内）
        LoginFragment login = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.content);
        if (login == null) {
            login = LoginFragment.newInstance("LOGIN_FRAGMENT");
        }
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.add(R.id.content, login);
        trans.commit();

        //创建presenter对象（View层通过它来操作Model层）
        new LoginPresenter(
                getApplicationContext(),
                UserRepository.getInstance(),
                login
        );
    }
}
