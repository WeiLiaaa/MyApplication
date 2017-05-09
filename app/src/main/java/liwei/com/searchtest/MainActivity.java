package liwei.com.searchtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import liwei.com.searchtest.recyclerview.HistoryRecyclerViewAdapter;
import liwei.com.searchtest.recyclerview.HotRecyclerViewAdapter;
import liwei.com.searchtest.sqlite.Bean;
import liwei.com.searchtest.sqlite.ControllerDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //退出
    @BindView(R.id.exit)
    ImageView exit;
    @BindView(R.id.tv)
    TextView tv;
    //清除历史
    @BindView(R.id.eraser)
    TextView eraser;
    //历史记录
    @BindView(R.id.history_recy)
    RecyclerView historyRecy;
    //热门搜索
    @BindView(R.id.hot_recy)
    RecyclerView hotRecy;
    //搜索框
    @BindView(R.id.searchView)
    SearchView searchView;
    //listview
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private Unbinder bind;
    //数据库
    private ControllerDao sqlite;
    //输入框内容
    private String string;
    //listview数据
    private String[] mStrs = {"aaa", "bbb", "ccc", "airsaidaaaaaaaaaaa", "快快快快快扩"};
    private HistoryRecyclerViewAdapter adapter1;
    private ArrayList<Bean> query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定butterknife
        bind = ButterKnife.bind(this);
        //初始化数据库
        initSQLite();
        //初始化操作
        initController();

    }

    /**
     * 初始化数据库
     */
    private void initSQLite() {
        //实例化数据库
        sqlite = new ControllerDao(MainActivity.this);
    }

    /**
     * 操作逻辑
     */
    private void initController() {

        //退出
        exit.setOnClickListener(this);
        //退出
        tv.setOnClickListener(this);
        //清除历史
        eraser.setOnClickListener(this);
        //历史记录展示
        setRecyclerViewData1();
        //热门搜索的数据库展示
        setRecyclerViewData();
        //设置listview的adapter
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrs));
        //搜索框监听事件
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                lv.setVisibility(View.VISIBLE);
                ll.setVisibility(View.GONE);
                if (!TextUtils.isEmpty(newText)) {
                    lv.setFilterText(newText);

                } else {
                    lv.clearTextFilter();
                }
                return false;
            }
        });
        setListviewTest();
    }

    /**
     * listview的逻辑操作
     */
    private void setListviewTest() {
        //listview 的点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lv.setVisibility(View.GONE);
                Intent in = new Intent(MainActivity.this, TwoActivity.class);
                startActivity(in);
                ll.setVisibility(View.VISIBLE);
                //添加到数据库中
                sqlite.insert(mStrs[position]);
            }
        });
    }

    /**
     * 历史记录
     */
    private void setRecyclerViewData1() {
        //从数据库中提取数据
        query = sqlite.query();
        adapter1 = new HistoryRecyclerViewAdapter(MainActivity.this);
        //HotRecyclerViewAdapter adapter=new HotRecyclerViewAdapter(this);
        /*ArrayList<String> li=new ArrayList<>();
        for (int i=0; i<10 ; i++){
            li.add(i+"  .数据");
        }*/
        adapter1.AddData(query);
        adapter1.notifyDataSetChanged();
        //设置recyclerview的模式
        historyRecy.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        //设置adapter
        historyRecy.setAdapter(adapter1);
    }

    private void setRecyclerViewData() {
        HotRecyclerViewAdapter adapter = new HotRecyclerViewAdapter(this);
        ArrayList<String> li = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            li.add(i + " . 数据");
        }
        adapter.AddData(li);
        adapter.notifyDataSetChanged();
        //设置recyclerview的模式
        hotRecy.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        //设置adapter
        hotRecy.setAdapter(adapter);

    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                finish();
                Toast.makeText(MainActivity.this, "退出", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv:
                finish();
                Toast.makeText(MainActivity.this, "退出", Toast.LENGTH_SHORT).show();
                break;
            case R.id.eraser:
                int size = query.size();
                for (int i = 0; i <size ; i++) {
                    String name1 = query.get(i).getName();
                    sqlite.delete(name1);
                }

                adapter1.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "清除历史", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    /**
     * 取消绑定butterknife
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消绑定
        bind.unbind();
    }

}
