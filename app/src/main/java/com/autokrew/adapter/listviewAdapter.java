package com.autokrew.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.autokrew.R;
import com.autokrew.models.Model;

import java.util.ArrayList;

/**
 * 
 * @author anfer
 * 
 */
public class listviewAdapter extends BaseAdapter {
	public ArrayList<Model> productList;
	Activity activity;

	public listviewAdapter(Activity activity, ArrayList<Model> productList) {
		super();
		this.activity = activity;
		this.productList = productList;
	}

	@Override
	public int getCount() {
		return productList.size();
	}

	@Override
	public Object getItem(int position) {
		return productList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		TextView mLeaveType;
		TextView mEligible;
		TextView mAvailed;
		TextView mBalance;
		//CheckBox mCheckbox;
		ImageView mImgApply;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		LayoutInflater inflater = activity.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listview_row, null);
			holder = new ViewHolder();
			holder.mLeaveType = (TextView) convertView.findViewById(R.id.mLeaveType);
			holder.mEligible = (TextView) convertView.findViewById(R.id.mEligible);
			holder.mAvailed = (TextView) convertView
					.findViewById(R.id.mAvailed);
			holder.mBalance = (TextView) convertView.findViewById(R.id.mBalance);
			holder.mImgApply = (ImageView) convertView.findViewById(R.id.mImgApply);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Model item = productList.get(position);
		holder.mLeaveType.setText(item.getLeaveType().toString());
		holder.mEligible.setText(item.getEligible().toString());
		holder.mAvailed.setText(item.getAvailed().toString());
		holder.mBalance.setText(item.getBalance().toString());

		if(item.getIsApplyLeave().length()>0){
			holder.mImgApply.setVisibility(View.VISIBLE);
		}
		else {
			holder.mImgApply.setVisibility(View.INVISIBLE);
		}

		return convertView;
	}

}