package xyz.cambria.autokeepingaccountbeta.util;

import android.util.Log;
import xyz.cambria.autokeepingaccountbeta.entity.Category;
import xyz.cambria.autokeepingaccountbeta.entity.KeepingAccountRec;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static android.support.constraint.Constraints.TAG;

public class KeepingAccountUtil {

    /**
     * 农行短信不会提醒单笔金额小于￥10的交易，需要手动校准
     * @param list 短信转成的记账记录
     */
    public static void offsetRecord(List<KeepingAccountRec> list) throws Exception {

        if (list.size() < 2) {
            throw new Exception("list长度必须不小于2，包含当前账单的上一条账单记录");
        }

        BigDecimal ans = new BigDecimal("0.00");
        KeepingAccountRec now;
        KeepingAccountRec pre;
        for (int i = 0; i < list.size()-1; i++) {
            now = list.get(i);
            pre = list.get(i + 1);

            Log.d(TAG, "offsetRecord: "+now);
            Log.d(TAG, "offsetRecord: "+pre);

            if (pre.getIncomeAccount().equals("中国农业银行年费")) {
                pre.setBalance(list.get(i+2).getBalance().subtract(new BigDecimal("10.00")));
                continue;
            }

            if (now.getOutcomeAccount().equals("您")) {
                //最后一笔账单为支出
                ans = now.getBalance().add(now.getAmount()).subtract(pre.getBalance());
                Log.d(TAG, "offsetRecord: offset=" + ans);
                if (ans.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                } else if (ans.compareTo(BigDecimal.ZERO) == 1){
                    //有未记录的收入
                    list.add(i+1 , new KeepingAccountRec(
                            now.getId()+1000000L, "您" , "无记录" , now.getAccount() , now.getTime()-1000L , ans , pre.getBalance().add(ans) , Category.NONE
                    ));
                } else {
                    //有未记录支出
                    list.add(i+1 , new KeepingAccountRec(
                            now.getId()+1000000L, "无记录" , "您" , now.getAccount() , now.getTime()-1000L , ans.abs() , pre.getBalance().add(ans) , Category.NONE
                    ));
                }
                i++;
            } else {
                //最后一笔账单为收入
                ans = now.getBalance().subtract(now.getAmount()).subtract(pre.getBalance());
                if (ans.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                } else if (ans.compareTo(BigDecimal.ZERO) == 1){
                    //有未记录的收入
                    list.add(i+1 , new KeepingAccountRec(
                            now.getId()+1000000L, "您" , "无记录" , now.getAccount() , now.getTime()-1000L , ans , pre.getBalance().add(ans) , Category.NONE
                    ));
                } else {
                    //有未记录支出
                    list.add(i+1 , new KeepingAccountRec(
                            now.getId()+1000000L, "无记录" , "您" , now.getAccount() , now.getTime()-1000L , ans.abs() , pre.getBalance().add(ans) , Category.NONE
                    ));
                }
                i++;
            }

        }
    }

}
