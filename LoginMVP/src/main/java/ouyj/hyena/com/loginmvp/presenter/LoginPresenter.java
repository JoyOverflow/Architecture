package ouyj.hyena.com.loginmvp.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import ouyj.hyena.com.loginmvp.R;
import ouyj.hyena.com.loginmvp.contract.LoginContract;
import ouyj.hyena.com.loginmvp.data.User;
import ouyj.hyena.com.loginmvp.data.UserRepository;

public class LoginPresenter implements LoginContract.Presenter{

    //邮箱和密码
    private static final String[] credentials = {
            "foo@example.com:hello",
            "bar@example.com:world"
    };

    //当前应用上下文
    private final Context context;
    //必须持有Model层的对象
    private final UserRepository userRepository;
    //必须持有View层对象（视图层接口）
    private final LoginContract.View ILogin;

    private LoginTask loginTask = null;


    /**
     * 构造方法
     * @param context
     * @param userRepository
     * @param loginView
     */
    public LoginPresenter(Context context, UserRepository userRepository, LoginContract.View loginView) {
        //当前应用上下文
        this.context = context;
        //保存Model层对象
        this.userRepository = userRepository;
        //设置视图的Presenter成员变量
        this.ILogin = loginView;
        ILogin.setPresenter(this);
    }


    /**
     * 需为视图实现的接口方法
     */
    @Override
    public void login() {
        tryLogin();
    }
    private void tryLogin() {
        if (loginTask != null) {
            return;
        }
        //清除错误提示（调用视图类实现的方法）
        ILogin.setEmailError(null);
        ILogin.setPasswordError(null);

        String email = ILogin.getUserEmail();
        String password = ILogin.getPassword();
        boolean cancel = false;

        //校验输入的邮箱和密码
        if (TextUtils.isEmpty(email))
            cancel = ILogin.setEmailError(context.getString(R.string.error_field_required));
        else if (!ILogin.isEmailValid(email))
            cancel = ILogin.setEmailError(context.getString(R.string.error_invalid_email));
        else if (TextUtils.isEmpty(password))
            cancel = ILogin.setPasswordError(context.getString(R.string.error_field_required));
        else if (!ILogin.isPasswordValid(password))
            cancel = ILogin.setPasswordError(context.getString(R.string.error_invalid_password));
        
        //开始登录
        if (!cancel) {
            //登录进度
            ILogin.showLoginProgress(true);
            //执行AsyncTask
            loginTask = new LoginTask(email,password);
            loginTask.execute((Void) null);
        }
    }
    /**
     * 需为视图实现的接口方法
     */
    @Override
    public void reset() {
        ILogin.resetEditView();
    }
    /**
     * Presenter基类需实现方法
     */
    @Override
    public void start() { }





    /**
     * 内部类
     */
    public class LoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mEmail;
        private final String mPassword;
        LoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            //休眠（模拟网络访问）
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
            for (String credential : credentials) {
                //分割字串
                String[] array = credential.split(":");
                if (array[0].equals(mEmail)) {
                    //是否为邮箱对应的密码
                    return array[1].equals(mPassword);
                }
            }
            //登录不成功（可定向到注册页面）
            return false;
        }
        @Override
        protected void onPostExecute(final Boolean success) {
            loginTask = null;
            ILogin.showLoginProgress(false);
            if (success) {
                //登录成功
                ILogin.toMainAct();
                //保存此登录日志
                if (userRepository != null) {
                    userRepository.saveUserInfo(
                            new User(ILogin.getUserEmail(), ILogin.getPassword())
                    );
                }
            }
            else {
                ILogin.showFailedError();
            }
        }
        @Override
        protected void onCancelled() {
            loginTask = null;
            ILogin.showLoginProgress(false);
        }
    }


}
