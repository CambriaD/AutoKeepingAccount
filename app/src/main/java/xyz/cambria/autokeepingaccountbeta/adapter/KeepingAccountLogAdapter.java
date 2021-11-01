package xyz.cambria.autokeepingaccountbeta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import xyz.cambria.autokeepingaccountbeta.R;
import xyz.cambria.autokeepingaccountbeta.entity.KeepingAccountRec;

import java.util.List;

public class KeepingAccountLogAdapter extends BaseAdapter {
    private List<KeepingAccountRec> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public KeepingAccountLogAdapter(List<KeepingAccountRec> list, Context context) {
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
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_ka_test, null, false);
        }

        ((TextView) convertView.findViewById(R.id.textView_item_ka_test))
                .setText(list.get(position).toString());
        return convertView;
    }
}
