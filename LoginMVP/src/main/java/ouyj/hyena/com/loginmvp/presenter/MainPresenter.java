package ouyj.hyena.com.loginmvp.presenter;

import android.content.Context;
import java.util.ArrayList;
import ouyj.hyena.com.loginmvp.contract.MainContract;
import ouyj.hyena.com.loginmvp.data.RootEntity;
import ouyj.hyena.com.loginmvp.data.StoriesEntity;
import ouyj.hyena.com.loginmvp.data.service.ZhiHuService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainPresenter implements MainContract.Presenter{

    private String baseUrl = "http://news-at.zhihu.com";
    private MainContract.View mMainView;
    private Context mContext;

    protected ZhiHuService service;
    public MainPresenter(Context context) {
        this.mContext = context;
    }



    public void setView(MainContract.View view) {
        this.mMainView = view;
        mMainView.setPresenter(this);
        mMainView.setTitle();
        service = getService();
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




    public RootEntity loadData(Observable<RootEntity> observable) {
        final RootEntity rootEntity = new RootEntity();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<RootEntity, ArrayList<StoriesEntity>>() {
                    @Override
                    public ArrayList<StoriesEntity> call(RootEntity rootEntity) {
                        return rootEntity.getStories();
                    }
                })
                .subscribe(new Subscriber<ArrayList<StoriesEntity>>() {
                    @Override
                    public void onCompleted() { }
                    @Override
                    public void onError(Throwable e) { }
                    @Override
                    public void onNext(ArrayList<StoriesEntity> storiesEntities) {
                        rootEntity.setStories(storiesEntities);
                        mMainView.refresh(storiesEntities);
                    }
                });
        return rootEntity;
    }

    /**
     * 通过WebApI获取数据
     * @return
     */
    @Override
    public RootEntity getLatestNews() {
        return loadData(service.getLatestNews());
    }
    @Override
    public RootEntity getSafety() {
        return loadData(service.getSafety());
    }
    @Override
    public RootEntity getInterest() {
        return loadData(service.getInterest());
    }
    @Override
    public RootEntity getSport() {
        return loadData(service.getSport());
    }
    @Override
    public void start() {
    }
}
