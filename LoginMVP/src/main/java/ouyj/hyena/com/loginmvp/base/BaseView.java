package ouyj.hyena.com.loginmvp.base;

/**
 *视图要实现的接口基类
 *View层通过它来为类内Presenter成员字段赋值
 */
public interface BaseView<T> {
    /**
     * 设置视图类的Presenter成员变量
     * @param presenter
     */
    void setPresenter(T presenter);
}
