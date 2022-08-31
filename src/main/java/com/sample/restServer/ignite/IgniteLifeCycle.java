package com.sample.restServer.ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.internal.processors.cache.ClientCacheChangeDiscoveryMessage;

import java.util.List;

public class IgniteLifeCycle {
    //접속정보
    String igniteServerIP = "192.168.20.64:10800";
    ClientConfiguration cfg = new ClientConfiguration()
            .setAddresses(igniteServerIP);

    IgniteClient igniteClient = Ignition.startClient(cfg);

    public IgniteLifeCycle(String igniteServerIP, ClientConfiguration cfg, IgniteClient igniteClient) {
        this.igniteServerIP = igniteServerIP;
        this.cfg = cfg;
        this.igniteClient = igniteClient;
    }


    //스키마 설정
    public ClientCache IgniteConnect(){
        ClientCache<Object, Object> cache = igniteClient.getOrCreateCache("YSH");
        return cache;
    }

    public void IgniteClose(){
        try{
            igniteClient.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getSelectAll(ClientCache cache, String table){
        List<?> resultList;

        //select 쿼리
        SqlFieldsQuery qry = new SqlFieldsQuery("SELECT * FROM EMP");
        resultList = cache.query(qry).getAll();
        System.out.println("emp 테이블 조회 결과 : " + resultList);
    }
    public void insertEmp(ClientCache cache, String table, String user_id, String user_name, int age){
        String values = "\'"+ user_id + "\'," + "\'"+ user_id + "\'," +  Integer.toString(age) ;
        String dmlQry = "INSERT INTO EMP(USER_ID, USER_NAME, AGE) VALUES("+ values +")";
        SqlFieldsQuery qry = new SqlFieldsQuery(dmlQry);
        cache.query(qry).getAll();
    }

    public void updateEmp(ClientCache cache, String table, String user_id, String user_name, int age){
        String dmlQry = "UPDATE EMP SET USER_NAME = "+"\'"+ user_name +"\'";
        dmlQry += "AND AGE = " + Integer.toString(age) ;
        dmlQry += "WHERE USER_ID =" + "\'"+ user_id +"\'";
        SqlFieldsQuery qry = new SqlFieldsQuery(dmlQry);
        cache.query(qry).getAll();
    }

    public void createTable(ClientCache cache, String table){
        String ddlQry = "CREATE TABLE EMP( "
                      + "USER_ID VARCHAR, "
                      + "USER_NAME VARCHAR, "
                      + "AGE INTEGER,  "
                      + "CONSTRAINT USER_ID PRIMARY KEY (USER_ID)); ";
        SqlFieldsQuery qry = new SqlFieldsQuery(ddlQry);
        qry.setSchema("YSH");
        cache.query(qry).getAll();

    }
}
