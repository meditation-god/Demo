package com.itheima.demorecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView mRv;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRv = (RecyclerView) findViewById(R.id.recv);
//        //线性布局管理器
//        mRv.setLayoutManager(new LinearLayoutManager(this));
////        //表格布局管理器
        mRv.setLayoutManager(new GridLayoutManager(this, 4));
        //瀑布流布局管理器
        mRv.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        mData = new ArrayList<String>();
        for (int i = 0; i < 500; i++) {
            mData.add("OKWEI - "+i);
        }
        mRv.setAdapter(new Myadpter());
        float density = getResources().getDisplayMetrics().density;
        System.out.println("**********"+density);


    }

    private class Myadpter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
            return new MyViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_view,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            TextView tv=((MyViewHolder)viewHolder).tv;
            LinearLayout.LayoutParams lp= (LinearLayout.LayoutParams) tv.getLayoutParams();
            lp.height=position%2==0?100:50;
            tv.setLayoutParams(lp);
            tv.setBackgroundColor(position%2==0?0xff00ff00:0xff00ffff);
            tv.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData==null?0:mData.size();
        }
    }


    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, getPosition()+" ", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
