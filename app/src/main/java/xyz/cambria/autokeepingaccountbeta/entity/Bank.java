package xyz.cambria.autokeepingaccountbeta.entity;

/**
 * 标记了各大银行信息
 */
public enum Bank {

    ABC("中国农业银行" , "AGRICULTURAL BANK OF CHINA" , "95599");

    public final String nameCN;
    public final String nameEN;
    public final String tel;

    Bank(String nameCN, String nameEN, String tel) {
        this.nameCN = nameCN;
        this.nameEN = nameEN;
        this.tel = tel;
    }
}
