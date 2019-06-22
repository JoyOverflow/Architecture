package ouyj.hyena.com.loginmvp.data.service;
import ouyj.hyena.com.loginmvp.data.RootEntity;
import ouyj.hyena.com.loginmvp.data.StoryDetailsEntity;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
/**
 */
public interface ZhiHuService {

    //今日头条
    @GET("/api/4/news/latest")
    Observable<RootEntity> getLatestNews();

    //互联网安全
    @GET("/api/4/theme/10")
    Observable<RootEntity> getSafety();

    //不准无聊
    @GET("/api/4/theme/11")
    Observable<RootEntity> getInterest();

    //体育日报
    @GET("/api/4/theme/8")
    Observable<RootEntity> getSport();

    //查看详细信息
    @GET("/api/4/news/{id}")
    Observable<StoryDetailsEntity> getNewsDetails(@Path("id") int id);
}
