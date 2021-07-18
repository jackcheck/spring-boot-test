package com.fabrick.testsv4.utils;

public class Const {

    public static final boolean isMock = true;

    //WEB-CLIENT CONSTS
    public static final String BASE_URL = "https://sandbox.platfr.io";
    public static final String TRANS_LIST_URI = "/api/gbs/banking/v4.0/accounts";
    public static final String ACC_BAL_URI = "/api/gbs/banking/v4.0/accounts";
    public static final String MON_TRANS_URI = "/api/gbs/banking/v4.0/accounts/";
    public static final String MON_TRANS_URI_PART2 = "/payments/money-transfers";
    public static final String API_KEY = "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP";
    public static final String AUTH_SCHEMA = "S2S";
    public static final int CONNECT_TIMEOUT_MILLIS = 10000;
    public static final int READ_TIMEOUT = 10;
    public static final int WRITE_TIMEOUT = 10;

    //CONTROLLER CONST
    public static final String TRANS_LIST_API = "api/v1/fabrick/transactionList";
    public static final String ACC_BAL_API = "api/v1/fabrick/accountBalance";
    public static final String MON_TRANS_API = "api/v1/fabrick/moneytransfer";
    public static final String MON_TRANSLIST_API = "api/v1/fabrick/moneytransferList";

}
