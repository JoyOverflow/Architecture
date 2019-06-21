package ouyj.hyena.com.loginmvp.contract;


import ouyj.hyena.com.loginmvp.base.BasePresenter;
import ouyj.hyena.com.loginmvp.base.BaseView;

/**
 * 定义View接口和Presenter接口需为对方提供的方法
 */
public interface LoginContract {
    /**
     * View层的按钮调用通过它来实现登陆和重置功能（视图层需持有Presenter实例）
     */
    interface Presenter extends BasePresenter {
        void login();
        void reset();
    }
    /**
     * Presenter层通过它来获取用户输入的邮箱和密码等
     */
    interface View extends BaseView<Presenter> {
        String getUserEmail();
        String getPassword();

        ///校验邮箱和密码
        boolean isEmailValid(String email);
        boolean isPasswordValid(String password);
        //输出邮箱和密码的报错信息
        boolean setEmailError(String error);
        boolean setPasswordError(String error);

        void showLoginProgress(boolean show);
        void resetEditView();
        void toMainAct();
        void showFailedError();
    }


}
