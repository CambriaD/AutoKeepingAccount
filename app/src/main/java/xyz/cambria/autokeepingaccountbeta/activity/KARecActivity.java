package xyz.cambria.autokeepingaccountbeta.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import xyz.cambria.autokeepingaccountbeta.R;
import xyz.cambria.autokeepingaccountbeta.entity.KeepingAccountRec;

import java.io.Serializable;

public class KARecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k_a_rec);
        KeepingAccountRec item = (KeepingAccountRec) getIntent().getSerializableExtra("KARItem");
        int index = getIntent().getIntExtra("index" , -1);

        TextView io = findViewById(R.id.textView_io_item_ka_test);
        bind

    }
}