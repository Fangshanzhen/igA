package com.igA.demo.constant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.igA.demo.utils.FileTransformUtils;
import com.igA.demo.utils.FileTransformUtils;
import com.igA.demo.utils.HttpClientUtils;
import com.igA.demo.utils.ResultSetUtils1;
import lombok.extern.slf4j.Slf4j;
import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogChannelFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class DataTransform {


    public static JSONObject transformDrug(JSONObject jsonObj, Object o, String key) {
        if (String.valueOf(o).equals("6")) {
            jsonObj.put(key, "11");
        }
        if (String.valueOf(o).equals("7")) {
            jsonObj.put(key, "12");
        }
        if (String.valueOf(o).equals("8")) {
            jsonObj.put(key, "13");
        }
        if (String.valueOf(o).equals("9")) {
            jsonObj.put(key, "14");
        }
        if (String.valueOf(o).equals("10")) {
            jsonObj.put(key, "15");
        }
        if (String.valueOf(o).equals("34")) {
            jsonObj.put(key, "16");
        }
        if (String.valueOf(o).equals("11")) {
            jsonObj.put(key, "21");
        }
        if (String.valueOf(o).equals("29")) {
            jsonObj.put(key, "22");
        }
        if (String.valueOf(o).equals("12")) {
            jsonObj.put(key, "23");
        }
        if (String.valueOf(o).equals("13")) {
            jsonObj.put(key, "24");
        }

        if (String.valueOf(o).equals("14")) {
            jsonObj.put(key, "25");
        }
        if (String.valueOf(o).equals("15")) {
            jsonObj.put(key, "26");
        }
        if (String.valueOf(o).equals("16")) {
            jsonObj.put(key, "27");
        }
        if (String.valueOf(o).equals("32")) {
            jsonObj.put(key, "28");
        }
        if (String.valueOf(o).equals("33")) {
            jsonObj.put(key, "29");
        }
        if (String.valueOf(o).equals("18")) {
            jsonObj.put(key, "41");
        }
        if (String.valueOf(o).equals("19")) {
            jsonObj.put(key, "42");
        }
        if (String.valueOf(o).equals("20")) {
            jsonObj.put(key, "43");
        }
        if (String.valueOf(o).equals("21")) {
            jsonObj.put(key, "44");
        }
        if (String.valueOf(o).equals("22")) {
            jsonObj.put(key, "45");
        }
        if (String.valueOf(o).equals("23")) {
            jsonObj.put(key, "46");
        }

        if (String.valueOf(o).equals("25")) {
            jsonObj.put(key, "51");
        }

        if (String.valueOf(o).equals("26")) {
            jsonObj.put(key, "52");
        }
        if (String.valueOf(o).equals("27")) {
            jsonObj.put(key, "53");
        }

        if (String.valueOf(o).equals("28")) {
            jsonObj.put(key, "54");
        }
        if (String.valueOf(o).equals("31")) {
            jsonObj.put(key, "99");
        }
        if (String.valueOf(o).equals("17")) {
            jsonObj.put(key, "61");
        }


        return jsonObj;
    }


    public static JSONObject transformRelation(JSONObject jsonObj, Object o) {
        if (String.valueOf(o).equals("1")) {
            jsonObj.put("FAM_RELAT", "父亲");
        }
        if (String.valueOf(o).equals("2")) {
            jsonObj.put("FAM_RELAT", "母亲");
        }
        if (String.valueOf(o).equals("3")) {
            jsonObj.put("FAM_RELAT", "爷爷");
        }
        if (String.valueOf(o).equals("4")) {
            jsonObj.put("FAM_RELAT", "奶奶");
        }
        if (String.valueOf(o).equals("5")) {
            jsonObj.put("FAM_RELAT", "姥爷");
        }
        if (String.valueOf(o).equals("6")) {
            jsonObj.put("FAM_RELAT", "姥姥");
        }
        if (String.valueOf(o).equals("7")) {
            jsonObj.put("FAM_RELAT", "兄弟");
        }
        if (String.valueOf(o).equals("8")) {
            jsonObj.put("FAM_RELAT", "姐妹");
        }
        if (String.valueOf(o).equals("10")) {
            jsonObj.put("FAM_RELAT", "叔伯");
        }
        if (String.valueOf(o).equals("11")) {
            jsonObj.put("FAM_RELAT", "姑姑");
        }
        if (String.valueOf(o).equals("12")) {
            jsonObj.put("FAM_RELAT", "舅舅");
        }
        if (String.valueOf(o).equals("13")) {
            jsonObj.put("FAM_RELAT", "姨妈");
        }
        if (String.valueOf(o).equals("13")) {
            jsonObj.put("FAM_RELAT", "堂(表)兄弟姐妹");
        }
        if (String.valueOf(o).equals("9")) {
            jsonObj.put("FAM_RELAT", "其它");
        }


        return jsonObj;
    }

    public static JSONObject transformDis(JSONObject jsonObj, Object o) {
        if (String.valueOf(o).equals("1")) {
            jsonObj.put("FAM_DIS", 1); //"血尿"
        }
        if (String.valueOf(o).equals("2")) {
            jsonObj.put("FAM_DIS", 2); //"蛋白尿"
        }
        if (String.valueOf(o).equals("3")) {
            jsonObj.put("FAM_DIS", 3);//"急性肾小球肾炎"
        }
        if (String.valueOf(o).equals("4")) {
            jsonObj.put("FAM_DIS", 4);//"急进性肾小球肾炎"
        }
        if (String.valueOf(o).equals("5")) {
            jsonObj.put("FAM_DIS", 5);//"迁延性肾小球肾炎"
        }
        if (String.valueOf(o).equals("6")) {
            jsonObj.put("FAM_DIS", 6);//"慢性肾小球肾炎"
        }
        if (String.valueOf(o).equals("7")) {
            jsonObj.put("FAM_DIS", 7);//"IgA肾病"
        }
        if (String.valueOf(o).equals("8")) {
            jsonObj.put("FAM_DIS", 8);//"原发性肾病综合征"
        }
        if (String.valueOf(o).equals("10")) {
            jsonObj.put("FAM_DIS", 10);//"肾小管间质疾病"
        }
        if (String.valueOf(o).equals("11")) {
            jsonObj.put("FAM_DIS", 11);//"泌尿系感染"
        }
        if (String.valueOf(o).equals("12")) {
            jsonObj.put("FAM_DIS", 12); //"其它"
        }

        if (String.valueOf(o).equals("9")) {
            jsonObj.put("FAM_DIS", 9);//"继发性肾脏疾病"
        }


        return jsonObj;
    }


    public static JSONObject transformIsOrNot(JSONObject jsonObj, Object o, String key) {
        if (String.valueOf(o).equals("1")) {
            jsonObj.put(key, 1);
        }
        if (String.valueOf(o).equals("2")) {
            jsonObj.put(key, 0);
        }
        if (String.valueOf(o).equals("3")) {
            jsonObj.put(key, 9);
        }

        return jsonObj;
    }


    public static JSONObject transformHuanErGuanXi(JSONObject jsonObj, Object o, String key) {
        if (String.valueOf(o).equals("1")) {
            jsonObj.put(key, 1);
        }
        if (String.valueOf(o).equals("2")) {
            jsonObj.put(key, 2);
        }
        if (String.valueOf(o).equals("3")) {
            jsonObj.put(key, 3);
        }
        if (String.valueOf(o).equals("4")) {
            jsonObj.put(key, 4);
        }
        if (String.valueOf(o).equals("5")) {
            jsonObj.put(key, 5);
        }
        if (String.valueOf(o).equals("6")) {
            jsonObj.put(key, 6);
        }
        if (String.valueOf(o).equals("7")) {
            jsonObj.put(key, 7);
        }
        if (String.valueOf(o).equals("8")) {
            jsonObj.put(key, 8);
        }
        if (String.valueOf(o).equals("10")) {
            jsonObj.put(key, 9);
        }
        if (String.valueOf(o).equals("11")) {
            jsonObj.put(key, 10);
        }
        if (String.valueOf(o).equals("12")) {
            jsonObj.put(key, 11);
        }
        if (String.valueOf(o).equals("13")) {
            jsonObj.put(key, 12);
        }
        if (String.valueOf(o).equals("13")) {
            jsonObj.put(key, 12);
        }
        if (String.valueOf(o).equals("9")) {
            jsonObj.put(key, 13);
        }


        return jsonObj;
    }


    public static JSONObject transformTime(JSONObject jsonObj, Object o, String key) throws ParseException {
        // 定义字符串对应的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 将日期字符串转换为Date对象
        if (o != null && !String.valueOf(o).equals("")) {
            Date date = null;
            try {
                date = sdf.parse(String.valueOf(o));
            } catch (ParseException e) {
                return jsonObj;
            }
            // 获取13位时间戳（毫秒级）
            long timestamp = date.getTime();
            jsonObj.put(key, String.valueOf(timestamp));
        }


        return jsonObj;

    }

    public static String transformTime1(Object o) throws ParseException {
        // 定义字符串对应的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
        String s = null;

        // 将日期字符串转换为Date对象
        if (o != null && !String.valueOf(o).equals("") && String.valueOf(o).contains("-")) {
            Date date = sdf.parse(String.valueOf(o));
            // 获取13位时间戳（毫秒级）
            long timestamp = date.getTime();
            if (timestamp > 0) {
                s = String.valueOf(timestamp);
            }
        }

        // 将日期字符串转换为Date对象
        if (o != null && String.valueOf(o).contains(".") && !String.valueOf(o).equals("")) {
            Date date1 = sdf1.parse(String.valueOf(o));
            // 获取13位时间戳（毫秒级）
            long timestamp1 = date1.getTime();
            if (timestamp1 > 0) {
                s = String.valueOf(timestamp1);
            }

        }
        return s;

    }


    public static void transform(String baseUrl, String jsonStr, String id,Connection connection,String admin,String password) throws Exception {
        LogChannelFactory logChannelFactory = new org.pentaho.di.core.logging.LogChannelFactory();
        LogChannel kettleLog = logChannelFactory.create("IgA传输数据");


        String dataUrl = baseUrl + "/iga-export/imexport/importData";
//        dataUrl = "http://10.0.108.41/api-gate/iga-export/imexport/importData";


        //   ----------------每隔30分钟获取一次token，避免多次调用---------------------------
        String accessToken = null;
        Statement tokenTime = null;
        ResultSet resultSetToken = null;
        String tokenSql = "SELECT token  FROM  "  + "public.token_time  ";
        List<String> tokenList = null;
        tokenTime = executeSql(tokenSql, connection);
        resultSetToken = tokenTime.executeQuery(tokenSql);
        try {
            if (resultSetToken != null) {
                tokenList = ResultSetUtils1.allResultSet(resultSetToken);
            }
            if (tokenList == null || (tokenList.size() == 0) || (tokenList.size() > 0 && tokenList.get(0) == null)
                    || (tokenList.size() > 0 && tokenList.get(0).equals("")) || (tokenList.size() > 0 && tokenList.get(0).equals("null"))) {
                accessToken = getToken(baseUrl,admin,password);
                Date date = new Date();
                long a = date.getTime() + 30 * 60 * 1000;  //30分钟
                String sql = "UPDATE "  + "public.token_time " + " SET token= " + "'" + accessToken + "'" + "  ,  token_time= " + a;
                tokenTime = executeSql(sql, connection);
                tokenTime.execute(sql);
            } else if (tokenList.size() > 0 && tokenList.get(0) != null && !tokenList.get(0).equals("null")) { //有token
                tokenSql = "SELECT token_time  FROM  " +  "public.token_time  ";
                List<String> timeList = new ArrayList<>();
                tokenTime = executeSql(tokenSql, connection);
                resultSetToken = tokenTime.executeQuery(tokenSql);
                if (resultSetToken != null) {
                    timeList = ResultSetUtils1.allResultSet(resultSetToken);
                }
                if (timeList != null && timeList.size() > 0 && timeList.get(0) != null) { //判断时间是否有效
                    Date date = new Date();
                    long a = date.getTime();
                    if (Long.valueOf(timeList.get(0)) > a) {
                        accessToken = tokenList.get(0);
                    } else {
                        accessToken = getToken(baseUrl,admin,password);
                        Date date1 = new Date();
                        long a1 = date1.getTime() + 30 * 60 * 1000;
                        String sql1 = "UPDATE " + "public.token_time  " + " SET token = " + "'" + accessToken + "'" + "  ,  token_time= " + a1;

                        tokenTime = executeSql(sql1, connection);
                        tokenTime.execute(sql1);
                    }
                }
            }
        } finally {
            close(tokenTime, resultSetToken);
        }


        if (accessToken != null) {
            MultipartFile multipartFile = FileTransformUtils.transform(jsonStr);
            //                    kettleLog.logBasic(jsonStr);
            kettleResponse dataResponse = HttpClientUtils.uploadFile(dataUrl, multipartFile, accessToken);
            if (dataResponse.getCode() == 200) {
                log.info("病人id: " + id + "  传输数据成功!");
//                kettleLog.logBasic("病人id: " + id + "  transform data success");
            }
            if (dataResponse.getCode() != 200) {
                throw new Exception(dataResponse.getData());
            }
        }

    }


    private static Statement executeSql(String sql, Connection connection) throws Exception {
        Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT);
        statement.setQueryTimeout(6000);
        statement.setFetchSize(100000);
        statement.setEscapeProcessing(false);
        return statement;
    }

    private static String getToken(String baseUrl,String admin,String password) throws Exception {

        String tokenUrl = baseUrl + "/auth/user/login";
//        tokenUrl = "http://10.0.108.41/api-gate/auth/user/login";

        kettleResponse tokenResponse = null;  //
        Map<String, Object> tokenMap = new HashMap<String, Object>();
        tokenMap.put("uid", admin);
        tokenMap.put("password", password);
        tokenMap.put("loginType", 1);
        String accessToken = null;

        try {
            tokenResponse = HttpClientUtils.doPost(tokenUrl, null, JSON.toJSONString(tokenMap, SerializerFeature.WriteMapNullValue), "1");
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (tokenResponse != null && tokenResponse.getCode() == 200) {
            JSONObject jsonObject = JSON.parseObject(tokenResponse.getData());
            JSONObject jsonObject1 = (JSONObject) jsonObject.get("data");
            accessToken = String.valueOf(jsonObject1.get("accessToken"));
        }
        if (tokenResponse != null && tokenResponse.getCode() != 200) {
            throw new Exception(tokenResponse.getData());
        }
        return accessToken;

    }
    private static void close(Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }

        if (statement != null) {
            statement.close();
        }
    }


    public static JSONObject transformQiangdu(JSONObject jsonObj, Object o, String key) {
        if (String.valueOf(o).equals("-")) {
            jsonObj.put(key, 0);
        }
        if (String.valueOf(o).equals("+-")) {
            jsonObj.put(key, 1);
        }
        if (String.valueOf(o).equals("+")) {
            jsonObj.put(key, 2);
        }
        if (String.valueOf(o).equals("++")) {
            jsonObj.put(key, 3);
        }
        if (String.valueOf(o).equals("+++")) {
            jsonObj.put(key, 4);
        }
        if (String.valueOf(o).equals("++++")) {
            jsonObj.put(key, 5);
        }
        if (String.valueOf(o).equals("-~+-")) {
            jsonObj.put(key, 6);
        }
        if (String.valueOf(o).equals("-~+")) {
            jsonObj.put(key, 7);
        }
        if (String.valueOf(o).equals("-~++")) {
            jsonObj.put(key, 8);
        }
        if (String.valueOf(o).equals("-~+++")) {
            jsonObj.put(key, 9);
        }
        if (String.valueOf(o).equals("-~++++")) {
            jsonObj.put(key, 10);
        }
        if (String.valueOf(o).equals("+-~+")) {
            jsonObj.put(key, 11);
        }if (String.valueOf(o).equals("+-~++")) {
            jsonObj.put(key, 12);
        }
        if (String.valueOf(o).equals("+-~+++")) {
            jsonObj.put(key, 13);
        }
        if (String.valueOf(o).equals("+-~++++")) {
            jsonObj.put(key, 14);
        }
        if (String.valueOf(o).equals("+~++")) {
            jsonObj.put(key, 15);
        }
        if (String.valueOf(o).equals("+~+++")) {
            jsonObj.put(key, 16);
        }
        if (String.valueOf(o).equals("+~++++")) {
            jsonObj.put(key, 17);
        }
        if (String.valueOf(o).equals("++~+++")) {
            jsonObj.put(key, 18);
        }
        if (String.valueOf(o).equals("++~++++")) {
            jsonObj.put(key, 19);
        }
        if (String.valueOf(o).equals("+++~++++")) {
            jsonObj.put(key, 20);
        }


        return jsonObj;
    }

}
