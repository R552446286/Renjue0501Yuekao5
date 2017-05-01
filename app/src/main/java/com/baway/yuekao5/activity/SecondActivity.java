package com.baway.yuekao5.activity;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baway.yuekao5.R;
import com.baway.yuekao5.adapter.MyAdapter;
import com.baway.yuekao5.bean.HouseBean;
import com.baway.yuekao5.utils.MyOkHttp;
import com.baway.yuekao5.view.RecycleViewDivider;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private SpringView spring;
    private RecyclerView recycler;
    private String qtime="20160411091603";
    private MyAdapter adapter;
    private List<HouseBean.Result.Rows> rows;
    private int i=3;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        getServerData();
    }

    private void getServerData() {
        String url="http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/?city_id=14&lat=40.04652&lng=116.306033&api_key=androidkey&sig=9317e9634b5fbc16078ab07abb6661c5&macid=45cd2478331b184ff0e15f29aaa89e3e&app=a-ajk&_pid=11738&o=PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys&from=mobile&m=Android-PE-TL10&cv=9.5.1&cid=14&i=864601026706713&v=4.4.2&pm=b61&uuid=1848c59c-185d-48d9-b0e9-782016041109&_chat_id=0&qtime="+qtime;
        MyAsyncTask task=new MyAsyncTask();
        task.execute(url);
    }

    private void initView() {
        spring = (SpringView) findViewById(R.id.spring);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        spring.setType(SpringView.Type.FOLLOW);
        spring.setHeader(new DefaultHeader(this));
        spring.setFooter(new DefaultFooter(this));
        spring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                qtime="20160411091603";
                rows.clear();
                getServerData();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        spring.onFinishFreshAndLoad();
                    }
                },2000);
            }

            @Override
            public void onLoadmore() {
                qtime="2016041109160"+(++i);
                getServerData();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        spring.onFinishFreshAndLoad();
                    }
                },2000);
            }
        });
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider));
    }
    class MyAsyncTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return MyOkHttp.get(params[0]);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String json=s.toString();
            Gson gson=new Gson();
            HouseBean houseBean = gson.fromJson(json, HouseBean.class);
            if (rows==null){
                rows = houseBean.result.rows;
            }else{
                rows.addAll(houseBean.result.rows);
            }
            initAdapter();
        }
    }

    private void initAdapter() {
        if (adapter==null){
            adapter = new MyAdapter(SecondActivity.this, rows);
            recycler.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }

}
