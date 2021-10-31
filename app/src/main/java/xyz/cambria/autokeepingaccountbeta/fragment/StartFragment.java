package xyz.cambria.autokeepingaccountbeta.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import xyz.cambria.autokeepingaccountbeta.adapter.KeepingAccountTestAdapter;
import xyz.cambria.autokeepingaccountbeta.adapter.SMSAdapter;
import xyz.cambria.autokeepingaccountbeta.databinding.FragmentStartBinding;
import xyz.cambria.autokeepingaccountbeta.entity.Bank;
import xyz.cambria.autokeepingaccountbeta.entity.KeepingAccountRec;
import xyz.cambria.autokeepingaccountbeta.entity.SMSEntity;
import xyz.cambria.autokeepingaccountbeta.mapper.SMSMapper;
import xyz.cambria.autokeepingaccountbeta.util.KeepingAccountUtil;

import java.util.ArrayList;
import java.util.List;

public class StartFragment extends Fragment {

    private FragmentStartBinding binding;
    private SMSMapper smsMapper;
    private static Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = this.getActivity();
        smsMapper = new SMSMapper(SMSMapper.SMS_URI_INBOX , activity);
        Log.v(StartFragment.class.getName() , "!!!" + activity.toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStartBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = binding.listviewStartMain;
        Log.v(StartFragment.class.getName() , "onViewCreated");
//        listView.setAdapter(new SMSAdapter(smsMapper.selectByAddress(Bank.ABC.tel) , this.getActivity()));
        List<SMSEntity> smsEntities = smsMapper.selectByAddress(Bank.ABC.tel);
        ArrayList<KeepingAccountRec> keepingAccountRecs = new ArrayList<>();
        for (SMSEntity smsEntity : smsEntities) {
            keepingAccountRecs.add(KeepingAccountRec.parseKARec(smsEntity , Bank.ABC));
        }
        listView.setAdapter(new KeepingAccountTestAdapter(keepingAccountRecs , this.getActivity()));

    }


}
