package xyz.cambria.autokeepingaccountbeta.entity;

import android.util.Log;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;

@Data
public class KeepingAccountRec implements Serializable {

//    private static final Long TIME_OFFSET = 12*60*60*1000L;

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
        category = Category.NONE;
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

    public KeepingAccountRec(Long id, String incomeAccount, String outcomeAccount, String account, Long time, BigDecimal amount, BigDecimal balance, Category category) {
        this.id = id;
        this.incomeAccount = incomeAccount;
        this.outcomeAccount = outcomeAccount;
        this.account = account;
        this.time = time;
        this.amount = amount;
        this.balance = balance;
        this.category = category;
    }

    /**
     *
     * @param smsEntity
     * @return
     */
    public static KeepingAccountRec parseKARec(SMSEntity smsEntity , Bank bank) {
        return new KeepingAccountRec(smsEntity , bank);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time))).append("\n");
        if (outcomeAccount.equals("您"))
            sb.append("支出\n").append(incomeAccount).append("\n");
        else
            sb.append("收入\n").append(outcomeAccount).append("\n");

        sb.append("交易金额：").append(amount).append("\n");
        sb.append("余额：").append(balance);
        return sb.toString();
    }
}
