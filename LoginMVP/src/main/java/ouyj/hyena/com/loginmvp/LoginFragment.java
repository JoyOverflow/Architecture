package ouyj.hyena.com.loginmvp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ouyj.hyena.com.loginmvp.contract.LoginContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginContract.View{

    @BindView(R.id.progressBar)
    ProgressBar loginProgress;
    @BindView(R.id.txtEmail)
    AutoCompleteTextView txtEmail;
    @BindView(R.id.txtPassword)
    EditText txtPassword;
    @BindView(R.id.loginForm)
    ScrollView loginForm;

    //@BindView(R.id.btnLogin)
    //Button signButton;

    //视图层需持有Presenter实例
    private LoginContract.Presenter presenter;
    /**
     * 返回片段实例
     * @param id
     * @return
     */
    public static LoginFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString("fragmentId", id);
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * 构造方法
     */
    public LoginFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }
    @OnClick({R.id.btnLogin, R.id.btnReset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                presenter.login();
                break;
            case R.id.btnReset:
                presenter.reset();
                break;
        }
    }






    @Override
    public String getUserEmail() {
        return txtEmail.getText().toString();
    }
    @Override
    public String getPassword() {
        return txtPassword.getText().toString();
    }
    @Override
    public boolean isEmailValid(String txtEmail) {
        return txtEmail.contains("@");
    }
    @Override
    public boolean isPasswordValid(String txtPassword) {
        return txtPassword.length() > 4;
    }
    @Override
    public boolean setEmailError(String error) {
        txtEmail.setError(error);
        if (!TextUtils.isEmpty(error)) {
            txtEmail.requestFocus();
        }
        return true;
    }
    @Override
    public boolean setPasswordError(String error) {
        txtPassword.setError(error);
        if (!TextUtils.isEmpty(error)) {
            txtPassword.requestFocus();
        }
        return true;
    }
    @Override
    public void showLoginProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginForm.setVisibility(show ? View.VISIBLE : View.GONE);
            loginProgress.animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                            loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                        }
                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    @Override
    public void resetEditView() {
        txtEmail.setText("");
        txtPassword.setText("");
    }
    @Override
    public void toMainAct() {
        Toast.makeText(getContext(), "登录成功！！！", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void showFailedError() {
        Toast.makeText(getContext(), "登录失败！！！", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}







