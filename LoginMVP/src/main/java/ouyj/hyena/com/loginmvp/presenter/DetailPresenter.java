package ouyj.hyena.com.loginmvp.presenter;

import android.util.Log;

import ouyj.hyena.com.loginmvp.contract.DetailContract;
import ouyj.hyena.com.loginmvp.data.StoryDetailsEntity;
import ouyj.hyena.com.loginmvp.data.service.ZhiHuService;
import ouyj.hyena.com.loginmvp.data.util.HtmlUtils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class DetailPresenter implements DetailContract.Presenter {

    private String baseUrl = "http://news-at.zhihu.com";
    private DetailContract.View mDetailView;
    protected ZhiHuService service;

    public DetailPresenter(DetailContract.View view) {
        this.mDetailView = view;
        mDetailView.setPresenter(this);
        service = getService();
    }
    @Override
    public void start() {
    }
    public ZhiHuService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(ZhiHuService.class);
        return service;
    }

    @Override
    public StoryDetailsEntity getNewsDetail(int id) {
        service.getNewsDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<StoryDetailsEntity, String>() {
                    @Override
                    public String call(StoryDetailsEntity storyDetailsEntity) {
                        return  HtmlUtils.structHtml(storyDetailsEntity);
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("error",e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        mDetailView.loadWebView(s);
                    }
                });
        return null;
    }
}
