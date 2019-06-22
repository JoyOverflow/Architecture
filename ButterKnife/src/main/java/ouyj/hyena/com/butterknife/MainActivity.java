package ouyj.hyena.com.butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.lstNews)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView.setAdapter(null);
    }
}
