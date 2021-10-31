package xyz.cambria.autokeepingaccountbeta.entity;

import lombok.Data;

@Data
public class SMSEntity {

    private Integer _id;

    private Integer thread_id;

    private String address;

    private String person;

    private Long date;

    private String protocol;

    private boolean read;

    private Integer status;

    private Integer type;

    private String body;

    private String service_center;
}