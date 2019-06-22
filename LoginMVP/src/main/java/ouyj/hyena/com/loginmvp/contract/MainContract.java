package ouyj.hyena.com.loginmvp.contract;

import java.util.List;

import ouyj.hyena.com.loginmvp.base.BasePresenter;
import ouyj.hyena.com.loginmvp.base.BaseView;
import ouyj.hyena.com.loginmvp.data.RootEntity;
import ouyj.hyena.com.loginmvp.data.StoriesEntity;

public interface MainContract {
    interface Presenter extends BasePresenter {
        RootEntity getLatestNews();
        RootEntity getSafety();
        RootEntity getInterest();
        RootEntity getSport();
    }
    interface View extends BaseView<Presenter> {
        void setTitle();
        void refresh(List<StoriesEntity> list);
    }
}
