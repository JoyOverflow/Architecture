package ouyj.hyena.com.loginmvp.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ouyj.hyena.com.loginmvp.R;

public class ZhiHuNewsAdapter extends BaseAdapter {

    private List<StoriesEntity> newsList;
    private LayoutInflater mInflater;
    private Context context;


    public ZhiHuNewsAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setNewsList(List<StoriesEntity> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }




    @Override
    public int getCount() {
        return (newsList == null || newsList.isEmpty()) ? 0 : newsList.size();
    }
    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.news_item, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }
        holder = (Holder) convertView.getTag();
        StoriesEntity news = newsList.get(position);
        TextView tvnews = holder.tvnews;
        ImageView ivnews = holder.ivnews;
        tvnews.setText(news.getTitle());

        //记得判空，不然后会空指针异常
        if (news.getImages() == null) {
            ivnews.setVisibility(View.GONE);
        } else {
            //图像视图可见
            ivnews.setVisibility(View.VISIBLE);

            //使用图片加载框架来加载URL图片
            String url=news.getImages().get(0);
            Glide.with(context).load(url).into(ivnews);
        }
        return convertView;
    }

    static class Holder {
        @BindView(R.id.tvnews)
        TextView tvnews;
        @BindView(R.id.ivnews)
        ImageView ivnews;
        Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
