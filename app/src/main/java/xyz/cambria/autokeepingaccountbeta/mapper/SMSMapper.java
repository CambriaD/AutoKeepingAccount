package xyz.cambria.autokeepingaccountbeta.mapper;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import xyz.cambria.autokeepingaccountbeta.entity.SMSEntity;

import java.util.ArrayList;
import java.util.List;

public class SMSMapper {

    /**
     * 所有的短信
     */
    public static final String SMS_URI_ALL = "content://sms/";
    /**
     * 收件箱短信
     */
    public static final String SMS_URI_INBOX = "content://sms/inbox";
    /**
     * 发件箱短信
     */
    public static final String SMS_URI_SEND = "content://sms/sent";
    /**
     * 草稿箱短信
     */
    public static final String SMS_URI_DRAFT = "content://sms/draft";

    private Uri smsDBUri;
    private Activity activity;

    public SMSMapper(Uri smsDBUri) {
        this.smsDBUri = smsDBUri;
    }

    public SMSMapper(String smsDBUri, Activity activity) {
        this.smsDBUri = Uri.parse(smsDBUri);
        this.activity = activity;
    }

    public List<SMSEntity> selectAll() {
        String[] projection = {"_id" , "thread_id" , "address" , "person" , "date" , "protocol" , "read" , "status" , "type" , "body" , "service_center"};
        Cursor cursor = activity.getContentResolver().query(smsDBUri, projection, null, null, "date desc");

        int id = cursor.getColumnIndex(projection[0]);
        int thread_id = cursor.getColumnIndex(projection[1]);
        int address = cursor.getColumnIndex(projection[2]);
        int person = cursor.getColumnIndex(projection[3]);
        int date = cursor.getColumnIndex(projection[4]);
        int protocol = cursor.getColumnIndex(projection[5]);
        int read = cursor.getColumnIndex(projection[6]);
        int status = cursor.getColumnIndex(projection[7]);
        int type = cursor.getColumnIndex(projection[8]);
        int body = cursor.getColumnIndex(projection[9]);
        int service_center = cursor.getColumnIndex(projection[10]);

        List<SMSEntity> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            SMSEntity smsEntity = new SMSEntity();
            smsEntity.set_id(cursor.getInt(id));
            smsEntity.setThread_id(cursor.getInt(thread_id));
            smsEntity.setAddress(cursor.getString(address));
            smsEntity.setPerson(cursor.getString(person));
            smsEntity.setDate(cursor.getLong(date));
            smsEntity.setProtocol(cursor.getString(protocol));
            smsEntity.setRead(cursor.getInt(read) == 1);
            smsEntity.setStatus(cursor.getInt(status));
            smsEntity.setType(cursor.getInt(type));
            smsEntity.setBody(cursor.getString(body));
            smsEntity.setService_center(cursor.getString(service_center));

            list.add(smsEntity);
        }
        cursor.close();

        return list;
    }

    public List<SMSEntity> selectByAddress(String addr) {
        String[] projection = {"_id" , "thread_id" , "address" , "person" , "date" , "protocol" , "read" , "status" , "type" , "body" , "service_center"};
        Cursor cursor = activity.getContentResolver().query(smsDBUri, projection, "address=?", new String[]{addr}, "date desc");

        int id = cursor.getColumnIndex(projection[0]);
        int thread_id = cursor.getColumnIndex(projection[1]);
        int address = cursor.getColumnIndex(projection[2]);
        int person = cursor.getColumnIndex(projection[3]);
        int date = cursor.getColumnIndex(projection[4]);
        int protocol = cursor.getColumnIndex(projection[5]);
        int read = cursor.getColumnIndex(projection[6]);
        int status = cursor.getColumnIndex(projection[7]);
        int type = cursor.getColumnIndex(projection[8]);
        int body = cursor.getColumnIndex(projection[9]);
        int service_center = cursor.getColumnIndex(projection[10]);

        List<SMSEntity> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            SMSEntity smsEntity = new SMSEntity();
            smsEntity.set_id(cursor.getInt(id));
            smsEntity.setThread_id(cursor.getInt(thread_id));
            smsEntity.setAddress(cursor.getString(address));
            smsEntity.setPerson(cursor.getString(person));
            smsEntity.setDate(cursor.getLong(date));
            smsEntity.setProtocol(cursor.getString(protocol));
            smsEntity.setRead(cursor.getInt(read) == 1);
            smsEntity.setStatus(cursor.getInt(status));
            smsEntity.setType(cursor.getInt(type));
            smsEntity.setBody(cursor.getString(body));
            smsEntity.setService_center(cursor.getString(service_center));

            list.add(smsEntity);
        }
        cursor.close();

        return list;
    }

}
