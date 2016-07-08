package com.example.gvhorizontalscrollview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	LinearLayout lly;
	AGridViewAdapter adapter;
	GridView gridView;
	ArrayList<String> list = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lly = (LinearLayout) findViewById(R.id.lly);
		gridView = (GridView) findViewById(R.id.gv);
		for (int i = 0; i < 8; i++) {
			list.add("测试+dd " + i);
		}

		setGridView();
	}

	/** 设置GirdView参数 */
	private void setGridView() {
		int size = list.size();
		int length = 100;
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		int gridviewWidth = (int) (size * (length + 4) * density);
		int itemWidth = (int) (length * density);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				gridviewWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
		gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
		gridView.setColumnWidth(itemWidth); // 设置列表项宽
		gridView.setHorizontalSpacing(5); // 设置列表项水平间距
		gridView.setStretchMode(GridView.NO_STRETCH);
		gridView.setNumColumns(size); // 设置列数量=列表集合数

		adapter = new AGridViewAdapter(this, list);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,
						adapter.getItem(position) + "  选中 ", 0).show();
			}
		});
	}

	class AGridViewAdapter extends BaseAdapter {
		Context context;
		ArrayList<String> list;
		LayoutInflater mInaflater;

		AGridViewAdapter(Context context, ArrayList<String> list) {
			this.context = context;
			this.list = list;
			this.mInaflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInaflater.inflate(R.layout.item, null);
				holder.tv = (TextView) convertView.findViewById(R.id.txt);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv.setText(list.get(position));
			return convertView;
		}

		class ViewHolder {
			TextView tv;
		}

	}
}
