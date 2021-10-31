package xyz.cambria.autokeepingaccountbeta.entity;

import android.util.Log;
import lombok.Data;

import java.math.BigDecimal;

import static android.content.ContentValues.TAG;

@Data
public class KeepingAccountRec {

    /**
     * id
     */
    private Long id;

    /**
     * 收入人
     */
    private String incomeAccount;

    /**
     * 支出人
     */
    private String outcomeAccount;

    /**
     * 银行卡号
     */
    private String account;

    /**
     * 交易时间
     */
    private Long time;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 账户交易后余额
     */
    private BigDecimal balance;

    /**
     * 账单类别
     */
    private Category category;


    public KeepingAccountRec(SMSEntity smsEntity , Bank bank) {
        id = Long.valueOf(smsEntity.get_id());
        time = smsEntity.getDate();
        category = Category.LAUNCH;
        String sms = smsEntity.getBody();

        switch (bank) {

            case ABC:
                Log.d(TAG, "KeepingAccountRec: " + sms);

                if (sms.contains("年费交易")) {

                    outcomeAccount = "您";
                    incomeAccount = bank.nameCN+"年费";
                    account = sms.substring(sms.indexOf("尾号")+2 , sms.indexOf("尾号")+6);
                    amount = new BigDecimal(sms.substring(sms.indexOf("交易人民币")+6 , sms.length()-1));

                } else if (sms.charAt(8) == '您') {

                    outcomeAccount = "您";
                    sms = sms.substring(sms.indexOf("尾号") + 2);
                    account = sms.substring(0, 4);
                    sms = sms.substring(sms.indexOf("完成") + 2);
                    incomeAccount = sms.substring(0, sms.indexOf("交易人民币"));
                    sms = sms.substring(sms.indexOf("交易人民币") + 6);
                    amount = new BigDecimal(sms.substring(0, sms.indexOf("，")));
                    sms = sms.substring(sms.indexOf("余额") + 2);
                    balance = new BigDecimal(sms.substring(0, sms.length() - 1));

                } else {

                    outcomeAccount = sms.substring(8, sms.indexOf("于"));
                    incomeAccount = "您";
                    sms = sms.substring(sms.indexOf("向您尾号") + 4);
                    account = sms.substring(0, 4);
                    sms = sms.substring(sms.indexOf("交易人民币") + 5);
                    amount = new BigDecimal(sms.substring(0, sms.indexOf("，")));
                    sms = sms.substring(sms.indexOf("余额") + 2);
                    balance = new BigDecimal(sms.substring(0, sms.length() - 1));

                }
                break;
            default:
        }
    }

    /**
     *
     * @param smsEntity
     * @return
     */
    public static KeepingAccountRec parseKARec(SMSEntity smsEntity , Bank bank) {
        return new KeepingAccountRec(smsEntity , bank);
    }

}
