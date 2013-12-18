package com.alipay.simplehbase.myrecord.test;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;

import org.junit.Before;
import org.junit.BeforeClass;

import com.alipay.simplehbase.client.SimpleHbaseAdminClient;
import com.alipay.simplehbase.client.SimpleHbaseClient;

import com.alipay.simplehbase.config.Config;

import com.alipay.simplehbase.literal.LiteralValue;
import com.alipay.simplehbase.myrecord.Gender;
import com.alipay.simplehbase.myrecord.MyFatRecord;
import com.alipay.simplehbase.myrecord.MyRecord;

import com.alipay.simplehbase.myrecord.MyRecordRowKey;

/**
 * @author xinzhi
 */
public class MyRecordTestBase {

    protected static SimpleHbaseClient      simpleHbaseClient;
    protected static SimpleHbaseAdminClient simpleHbaseAdminClient;

    static {
        simpleHbaseClient = Config.getSimpleHbaseClient();
        simpleHbaseAdminClient = Config.getSimpleHbaseAdminClient();
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        Config.beforeClass();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        Config.afterClass();
    }

    @Before
    public void before() {
        deleteRecords();
    }

    @After
    public void after() {
        deleteRecords();
    }

    private void deleteRecords() {
        MyRecordRowKey start = new MyRecordRowKey(0);
        MyRecordRowKey end = new MyRecordRowKey(Integer.MAX_VALUE);
        simpleHbaseClient.deleteObjectList(start, end);
    }

    protected static MyRecord mockMyRecord(int id) {
        MyRecord myRecord = new MyRecord();
        myRecord.setId(id);
        myRecord.setName("allen_" + id);
        myRecord.setDate(new Date());
        myRecord.setVersion(0L);
        myRecord.setGender(Gender.MALE);
        return myRecord;
    }

    protected static MyRecord[] mockMyRecords(int size) {
        MyRecord[] myRecords = new MyRecord[size];
        for (int i = 0; i < myRecords.length; i++) {
            myRecords[i] = mockMyRecord(i);
        }
        return myRecords;
    }

    protected static void addMockRecords(int size) {
        MyRecord[] myRecords = mockMyRecords(size);
        for (int i = 0; i < myRecords.length; i++) {
            MyRecordRowKey myRecordRowKey = new MyRecordRowKey(i);
            simpleHbaseClient.putObject(myRecordRowKey, myRecords[i]);
        }
    }

    protected void putSlim(String str) {
        MyRecord myRecord = parseMyRecord(str);
        MyRecordRowKey myRecordRowKey = new MyRecordRowKey(myRecord.getId());
        simpleHbaseClient.putObject(myRecordRowKey, myRecord);
    }

    protected MyRecord parseMyRecord(String str) {

        MyRecord record = new MyRecord();

        String[] parts = str.split("[=,]");

        for (int i = 0; i < parts.length; i += 2) {

            if (parts[i].equals("id")) {
                record.setId((Integer) (LiteralValue.convertToObject(int.class,
                        parts[i + 1])));
                continue;
            }

            if (parts[i].equals("name")) {
                record.setName(parts[i + 1]);
                continue;
            }

            if (parts[i].equals("date")) {
                record.setDate((Date) (LiteralValue.convertToObject(Date.class,
                        parts[i + 1])));
                continue;
            }

            if (parts[i].equals("gender")) {
                record.setGender((Gender) (LiteralValue.convertToObject(
                        Gender.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("age")) {
                record.setAge((Long) (LiteralValue.convertToObject(long.class,
                        parts[i + 1])));
                continue;
            }

            if (parts[i].equals("version")) {
                record.setVersion((Long) (LiteralValue.convertToObject(
                        long.class, parts[i + 1])));
                continue;
            }

        }

        return record;
    }

    protected void putFatRecord(String str) {
        MyFatRecord myRecord = parseFatRecord(str);
        MyRecordRowKey myRecordRowKey = new MyRecordRowKey(myRecord.getId());
        simpleHbaseClient.putObject(myRecordRowKey, myRecord);
    }

    protected MyFatRecord parseFatRecord(String str) {

        MyFatRecord record = new MyFatRecord();

        String[] parts = str.split("[=,]");

        for (int i = 0; i < parts.length; i += 2) {

            if (parts[i].equals("id")) {
                record.setId((Integer) (LiteralValue.convertToObject(int.class,
                        parts[i + 1])));
                continue;
            }

            if (parts[i].equals("name")) {
                record.setName(parts[i + 1]);
                continue;
            }

            if (parts[i].equals("date")) {
                record.setDate((Date) (LiteralValue.convertToObject(Date.class,
                        parts[i + 1])));
                continue;
            }

            if (parts[i].equals("gender")) {
                record.setGender((Gender) (LiteralValue.convertToObject(
                        Gender.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("age")) {
                record.setAge((Long) (LiteralValue.convertToObject(long.class,
                        parts[i + 1])));
                continue;
            }

            if (parts[i].equals("version")) {
                record.setVersion((Long) (LiteralValue.convertToObject(
                        long.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("fatid")) {
                record.setFatid((Integer) (LiteralValue.convertToObject(
                        int.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("fatname")) {
                record.setFatname(parts[i + 1]);
                continue;
            }

            if (parts[i].equals("fatdate")) {
                record.setFatdate((Date) (LiteralValue.convertToObject(
                        Date.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("fatgender")) {
                record.setFatgender((Gender) (LiteralValue.convertToObject(
                        Gender.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("fatage")) {
                record.setFatage((Long) (LiteralValue.convertToObject(
                        long.class, parts[i + 1])));
                continue;
            }

            if (parts[i].equals("fatversion")) {
                record.setFatversion((Long) (LiteralValue.convertToObject(
                        long.class, parts[i + 1])));
                continue;
            }

        }

        return record;
    }

}
