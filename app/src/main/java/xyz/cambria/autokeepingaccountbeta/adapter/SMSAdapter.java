package xyz.cambria.autokeepingaccountbeta.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import xyz.cambria.autokeepingaccountbeta.entity.SMSEntity;
import xyz.cambria.autokeepingaccountbeta.R;

import java.util.List;

public class SMSAdapter extends BaseAdapter {

    private List<SMSEntity> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public SMSAdapter(List<SMSEntity> list, Context context) {
        this.list = list;
        this.context = context;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        return list.get(position).get_id();
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_sms, null, false);
        }

        ((TextView) convertView.findViewById(R.id.textView_item_sms))
                .setText(list.get(position).getBody());
        return convertView;
    }
}