package xyz.cambria.autokeepingaccountbeta.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import xyz.cambria.autokeepingaccountbeta.R;
import xyz.cambria.autokeepingaccountbeta.entity.KeepingAccountRec;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KARecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k_a_rec);
        KeepingAccountRec item = (KeepingAccountRec) getIntent().getSerializableExtra("KARItem");
        int index = getIntent().getIntExtra("index" , -1);

        ((TextView)findViewById(R.id.textView_io_activity_k_a_rec)).setText(item.getOutcomeAccount().equals("您")?"支出":"收入");
        ((TextView)findViewById(R.id.textView_category_activity_k_a_rec)).setText(item.getCategory().toString());
        ((TextView)findViewById(R.id.textView_amount_activity_k_a_rec)).setText(item.getAmount().toString());
        ((TextView)findViewById(R.id.textView_time_activity_k_a_rec)).setText(new SimpleDateFormat("yyyy-MM-ss HH:mm:ss").format(new Date(item.getTime())));
        ((TextView)findViewById(R.id.textView_balance_activity_k_a_rec)).setText(item.getBalance().toString());

    }
}