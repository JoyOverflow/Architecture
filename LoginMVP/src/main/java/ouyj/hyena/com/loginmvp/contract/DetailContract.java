package ouyj.hyena.com.loginmvp.contract;


import ouyj.hyena.com.loginmvp.base.BasePresenter;
import ouyj.hyena.com.loginmvp.base.BaseView;
import ouyj.hyena.com.loginmvp.data.StoryDetailsEntity;

/**
 *
 */
public interface DetailContract {

    interface Presenter extends BasePresenter {
        StoryDetailsEntity getNewsDetail(int id);
    }
    interface View extends BaseView<Presenter> {
        void loadWebView(String s);
    }
}
