package com.igA.demo.utils;

import com.igA.demo.constant.Constant;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.json.JSONException;

import static com.igA.demo.constant.Constant.*;
import static com.igA.demo.constant.DataTransform.*;


@Slf4j
public class igAData {

    public static void transformData1() throws Exception {

        Connection connection = null; //默认postgresql
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection("postgresql", "10.0.108.51", "5432", "postgres", "hospital", "postgres", "zhyl0123");
        } catch (SQLException e) {
            log.error("database connection error: " + e, "");
        }
        log.info("数据库连接成功");

        if (connection != null) {
            List<String> idList = new ArrayList<>();

            idList = commonExecute(connection, statement, resultSet);

            idList = Collections.singletonList("195");


            if (idList != null && idList.size() > 0) {
                for (String s : idList) {

                    JSONObject jsonObject = new JSONObject();
                    Map<String, Object> map = new HashMap<>();

                    String newPatientSql = patientSql.replace("?", s);
                    map = commonExecute1(connection, newPatientSql, statement, resultSet);
                    JSONArray newContactList = new JSONArray();
                    if (map != null && map.size() > 0) {
                        for (String key : map.keySet()) {

                            if (map.keySet().contains("contact_data") && key.equals("contact_data")) {   //解析contact_data中的数据
                                if (map.get(key) != null) {
                                    JSONArray contactList = JSONArray.parseArray(String.valueOf(map.get(key)));
                                    for (Object o : contactList) {
                                        o = String.valueOf(o).replace("explain", "cont_oth").replace("linkname", "cont_name")
                                                .replace("numberinfo", "cont_phone").replace("relation", "cont_relat");
                                        newContactList.add(o);
                                    }
                                }
                            }
                        }
                        map.remove("contact_data");

                        if (newContactList.size() > 0) {
                            map.put("zhyl0002", newContactList);
                        } else {
                            map.put("zhyl0002", null);
                        }

                    }
                    jsonObject.put("zhyl001", map);   //基本信息zhyl001
                    //-----------------------------------------------------------

                    String newPatientInfoSql = patientInfoSql.replace("?", s);
                    List<Map<String, Object>> mapList = commonExecute2(connection, newPatientInfoSql, statement, resultSet);

                    jsonObject.put("zhyl002", mapList);   //首诊或随访信息zhyl002
                    //-----------------------------------------------------------

                    String newWithDrawSql = withDrawSql.replace("?", s);
                    Map<String, Object> withDrawMap = commonExecute1(connection, newWithDrawSql, statement, resultSet);

                    jsonObject.put("zhyl003", withDrawMap);   //退出研究zhyl003
                    //-----------------------------------------------------------
                    String newBaseLineHisSql = baseLineHisSql.replace("?", s);
                    Map<String, Object> baseLineHisMap = commonExecute1(connection, newBaseLineHisSql, statement, resultSet);
                    jsonObject.put("zhyl004", baseLineHisMap);   //首诊病史zhyl004
                    //-----------------------------------------------------------

                    String newTreatmentHistorySql = treatmentHistorySql.replace("?", s);
                    Map<String, Object> treatmentHistoryMap = commonExecute1(connection, newTreatmentHistorySql, statement, resultSet);
                    JSONArray newYysDataList = new JSONArray();
                    if (treatmentHistoryMap != null && treatmentHistoryMap.size() > 0) {
                        for (String key : treatmentHistoryMap.keySet()) {

                            if (treatmentHistoryMap.keySet().contains("yys_data") && key.equals("yys_data")) {   //解析yys_data中的数据
                                if (treatmentHistoryMap.get(key) != null) {
                                    JSONArray yysList = JSONArray.parseArray(String.valueOf(treatmentHistoryMap.get(key)));
                                    for (Object o : yysList) {
                                        o = String.valueOf(o).replace("name", "MED_NAME").replace("start_time", "MED_START_HT")
                                                .replace("end_time", "MED_END_HT").replace("remark", "MED_OTH");
                                        newYysDataList.add(o);
                                    }
                                }
                            }
                        }
                        JSONArray transYysDataList = new JSONArray();
                        if (newYysDataList.size() > 0) {
                            for (int i = 0; i < newYysDataList.size(); i++) {
                                JSONObject jsonObj = JSONObject.parseObject(String.valueOf(newYysDataList.get(i)));
                                if (jsonObj.keySet().contains("MED_NAME") && jsonObj.get("MED_NAME") != null) {
                                    Object o = jsonObj.get("MED_NAME");
                                    transYysDataList.add(transformDrug(jsonObj, o,""));
                                }
                            }
                        }


                        treatmentHistoryMap.remove("yys_data");

                        if (transYysDataList.size() > 0) {
                            treatmentHistoryMap.put("zhyl00005", transYysDataList);
                        } else {
                            treatmentHistoryMap.put("zhyl00005", null);
                        }
                    }
                    jsonObject.put("zhyl005", treatmentHistoryMap); //治疗史
                    //-----------------------------------------------------------

                    String newFamilySql = familySql.replace("?", s);
                    Map<String, Object> familyMap = commonExecute1(connection, newFamilySql, statement, resultSet);
                    JSONArray newFamilyList = new JSONArray();

                    if (familyMap != null && familyMap.size() > 0) {
                        for (String key : familyMap.keySet()) {

                            if (familyMap.keySet().contains("szjbjzs_data") && key.equals("szjbjzs_data")) {   //解析szjbjzs_data中的数据
                                if (familyMap.get(key) != null) {
                                    JSONArray familyList = JSONArray.parseArray(String.valueOf(familyMap.get(key)));
                                    for (Object o : familyList) {
                                        o = String.valueOf(o).replace("name", "FAM_RELAT").replace("sbzj", "FAM_DIS")
                                                .replace("age", "FAM_AGE").replace("select", "FAM_PATH")
                                                .replace("report", "FAM_PATH_TEXT").replace("exlplain", "FAM_OTH")
                                        ;
                                        newFamilyList.add(o);
                                    }
                                }
                            }
                        }
                        JSONArray transFamilyDataList = new JSONArray();

                        for (int i = 0; i < newFamilyList.size(); i++) {
                            JSONObject jsonObj = JSONObject.parseObject(String.valueOf(newFamilyList.get(i)));
                            if (jsonObj.keySet().contains("FAM_RELAT") && jsonObj.get("FAM_RELAT") != null) {
                                Object o = jsonObj.get("FAM_RELAT");
                                jsonObj = transformRelation(jsonObj, o);
                            }
                            if (jsonObj.keySet().contains("FAM_DIS") && jsonObj.get("FAM_DIS") != null) {
                                Object o = jsonObj.get("FAM_DIS");
                                jsonObj = transformDis(jsonObj, o);
                            }
                            if (jsonObj.keySet().contains("FAM_PATH") && jsonObj.get("FAM_PATH") != null) {
                                Object o = jsonObj.get("FAM_PATH");
                                jsonObj = transformIsOrNot(jsonObj, o,"");
                            }
                            transFamilyDataList.add(jsonObj);
                        }

                        familyMap.remove("szjbjzs_data");

                        if (transFamilyDataList.size() > 0) {
                            familyMap.put("zhyl00006", transFamilyDataList);
                        } else {
                            familyMap.put("zhyl00006", null);
                        }

                    }
                    jsonObject.put("zhyl006", familyMap); //家族史

                    //-----------------------------------------------------------

                    String newBasicPESql = basicPESql.replace("?", s);
                    Map<String, Object> basicPESqlMap = commonExecute1(connection, newBasicPESql, statement, resultSet);
                    jsonObject.put("zhyl007", basicPESqlMap);   //体格检查zhyl007

                    //-----------------------------------------------------------
                    String newBasicLabSql = basicLabSql.replace("?", s);

                    Map<String, Object> basicLabMap = commonExecute1(connection, newBasicLabSql, statement, resultSet);

                    JSONArray shjcLab = new JSONArray();
                    JSONArray ccrLab = new JSONArray();
                    JSONObject ngjcLab = new JSONObject();
                    JSONObject ngdlLab = new JSONObject();
                    JSONArray ndbdlLab = new JSONArray();
                    JSONArray ndbjgLab = new JSONArray();
                    JSONArray szzqLab = new JSONArray();
                    JSONArray xcgLab = new JSONArray();

                    if (basicLabMap != null && basicLabMap.size() > 0) {
                        for (String key : basicLabMap.keySet()) {

                            if (basicLabMap.keySet().contains("shjc_data") && key.equals("shjc_data")) {   //解析shjc_data中的数据
                                if (basicLabMap.get(key) != null) {
                                    JSONArray basicLabList = JSONArray.parseArray(String.valueOf(basicLabMap.get(key)));
                                    for (Object o : basicLabList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("time")) {
                                            newJsonObj.put("RENAL_TIME", jsonObj.get("time"));
                                        }
                                        if (jsonObj.keySet().contains("alb")) {
                                            newJsonObj.put("ALB_VAL", jsonObj.get("alb"));
                                        }
                                        if (jsonObj.keySet().contains("crea")) {
                                            newJsonObj.put("CREA_VAL", jsonObj.get("crea"));
                                        }
                                        if (jsonObj.keySet().contains("urea")) {
                                            newJsonObj.put("UREA_VAL", jsonObj.get("urea"));
                                        }
                                        if (jsonObj.keySet().contains("c")) {
                                            newJsonObj.put("CYSC_VAL", jsonObj.get("c"));
                                        }
                                        if (jsonObj.keySet().contains("tcho")) {
                                            newJsonObj.put("TCHO_VAL", jsonObj.get("tcho"));
                                        }
                                        shjcLab.add(newJsonObj);
                                    }
                                }
                            }

                            if (basicLabMap.keySet().contains("ccr_data") && key.equals("ccr_data")) {   //解析ccr_data中的数据
                                if (basicLabMap.get(key) != null) {
                                    JSONArray basicLabList = JSONArray.parseArray(String.valueOf(basicLabMap.get(key)));
                                    for (Object o : basicLabList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("time")) {
                                            newJsonObj.put("CCR_TIME", jsonObj.get("time"));
                                        }
                                        if (jsonObj.keySet().contains("val")) {
                                            newJsonObj.put("CCR_VAL", jsonObj.get("val"));
                                        }
                                        if (jsonObj.keySet().contains("score")) {
                                            newJsonObj.put("CCR_VAL_BSA", jsonObj.get("score"));
                                        }

                                        ccrLab.add(newJsonObj);
                                    }
                                }
                            }

                            if (basicLabMap.keySet().contains("ngjc_data") && key.equals("ngjc_data")) {   //解析ngjc_data中的数据
                                if (basicLabMap.get(key) != null) {
                                    JSONArray basicLabList = JSONArray.parseArray(String.valueOf(basicLabMap.get(key)));
                                    for (Object o : basicLabList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("time")) {
                                            newJsonObj.put("UAC_TIME", jsonObj.get("time"));
                                        }
                                        if (jsonObj.keySet().contains("ucr")) {
                                            newJsonObj.put("UAC_VAL", jsonObj.get("ucr"));
                                        }

                                        ngjcLab = newJsonObj;
                                    }
                                }
                            }

                            if (basicLabMap.keySet().contains("ngdl_data") && key.equals("ngdl_data")) {   //解析ngdl_data中的数据
                                if (basicLabMap.get(key) != null) {
                                    JSONArray basicLabList = JSONArray.parseArray(String.valueOf(basicLabMap.get(key)));
                                    for (Object o : basicLabList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("time")) {
                                            newJsonObj.put("24HUCA_TIME", jsonObj.get("time"));
                                        }
                                        if (jsonObj.keySet().contains("val")) {
                                            newJsonObj.put("24HUCA_VAL", jsonObj.get("val"));
                                        }

                                        ngdlLab = newJsonObj;
                                    }
                                }
                            }

                            if (basicLabMap.keySet().contains("ndbdl_data") && key.equals("ndbdl_data")) {   //解析ndbdl_data中的数据
                                if (basicLabMap.get(key) != null) {
                                    JSONArray basicLabList = JSONArray.parseArray(String.valueOf(basicLabMap.get(key)));
                                    for (Object o : basicLabList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("time")) {
                                            newJsonObj.put("24HUPRO_TIME", jsonObj.get("time"));
                                        }
                                        if (jsonObj.keySet().contains("val")) {
                                            newJsonObj.put("24HUPRO_VAL", jsonObj.get("val"));
                                        }
                                        if (jsonObj.keySet().contains("blood")) {
                                            if (String.valueOf(jsonObj.get("blood")).equals("1")) {
                                                newJsonObj.put("24HPRO_MACHEMA", 1);
                                            }
                                            if (String.valueOf(jsonObj.get("blood")).equals("2")) {
                                                newJsonObj.put("24HPRO_MACHEMA", 0);
                                            }
                                            if (String.valueOf(jsonObj.get("blood")).equals("3")) {
                                                newJsonObj.put("24HPRO_MACHEMA", 9);
                                            }
                                        }
                                        if (jsonObj.keySet().contains("tizhong")) {
                                            newJsonObj.put("24HPRO_VAL_WT", jsonObj.get("tizhong"));
                                        }

                                        ndbdlLab.add(newJsonObj);
                                    }
                                }
                            }

                            if (basicLabMap.keySet().contains("ndbjg_data") && key.equals("ndbjg_data")) {   //解析ndbjg_data中的数据
                                if (basicLabMap.get(key) != null) {
                                    JSONArray basicLabList = JSONArray.parseArray(String.valueOf(basicLabMap.get(key)));
                                    for (Object o : basicLabList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("time")) {
                                            newJsonObj.put("UPC_TIME", jsonObj.get("time"));
                                        }
                                        if (jsonObj.keySet().contains("val")) {
                                            newJsonObj.put("UPC_VAL", jsonObj.get("val"));
                                        }
                                        if (jsonObj.keySet().contains("blood")) {
                                            if (String.valueOf(jsonObj.get("blood")).equals("1")) {
                                                newJsonObj.put("UPC_MACHEMA", 1);
                                            }
                                            if (String.valueOf(jsonObj.get("blood")).equals("2")) {
                                                newJsonObj.put("UPC_MACHEMA", 0);
                                            }
                                            if (String.valueOf(jsonObj.get("blood")).equals("3")) {
                                                newJsonObj.put("UPC_MACHEMA", 9);
                                            }
                                        }

                                        ndbjgLab.add(newJsonObj);
                                    }
                                }
                            }

                            if (basicLabMap.keySet().contains("szzq_data") && key.equals("szzq_data")) {   //解析szzq_data中的数据
                                if (basicLabMap.get(key) != null) {
                                    JSONArray basicLabList = JSONArray.parseArray(String.valueOf(basicLabMap.get(key)));
                                    for (Object o : basicLabList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("time")) {
                                            newJsonObj.put("MA_TIME", jsonObj.get("time"));
                                        }
                                        if (jsonObj.keySet().contains("ma")) {
                                            newJsonObj.put("UMA_VAL", jsonObj.get("ma"));
                                        }
                                        if (jsonObj.keySet().contains("tru")) {
                                            newJsonObj.put("UTRU_VAL", jsonObj.get("tru"));
                                        }
                                        if (jsonObj.keySet().contains("nag")) {
                                            newJsonObj.put("UNAG_VAL", jsonObj.get("nag"));
                                        }
                                        if (jsonObj.keySet().contains("aim")) {
                                            newJsonObj.put("UA1M_VAL", jsonObj.get("aim"));
                                        }
                                        if (jsonObj.keySet().contains("ucea")) {
                                            newJsonObj.put("UCREA_VAL", jsonObj.get("ucea"));
                                        }
                                        if (jsonObj.keySet().contains("acr")) {
                                            newJsonObj.put("UACR_VAL", jsonObj.get("avr"));
                                        }
                                        szzqLab.add(newJsonObj);
                                    }
                                }
                            }


                            if (basicLabMap.keySet().contains("xcg_data") && key.equals("xcg_data")) {   //解析xcg_data中的数据
                                if (basicLabMap.get(key) != null) {
                                    JSONArray basicLabList = JSONArray.parseArray(String.valueOf(basicLabMap.get(key)));
                                    for (Object o : basicLabList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("time")) {
                                            newJsonObj.put("CBC_TIME", jsonObj.get("time"));
                                        }
                                        if (jsonObj.keySet().contains("wbc")) {
                                            newJsonObj.put("CBC_WBC_VAL", jsonObj.get("wbc"));
                                        }
                                        if (jsonObj.keySet().contains("hgb")) {
                                            newJsonObj.put("CBC_HGB_VAL", jsonObj.get("hgb"));
                                        }
                                        if (jsonObj.keySet().contains("hct")) {
                                            newJsonObj.put("CBC_HCT_VAL", jsonObj.get("hct"));
                                        }
                                        if (jsonObj.keySet().contains("plt")) {
                                            newJsonObj.put("CBC_PLT_VAL", jsonObj.get("plt"));
                                        }

                                        xcgLab.add(newJsonObj);
                                    }
                                }
                            }

                        }  //key

                        basicLabMap.remove("shjc_data");
                        basicLabMap.remove("ccr_data");
                        basicLabMap.remove("ngjc_data");
                        basicLabMap.remove("ngdl_data");
                        basicLabMap.remove("ndbdl_data");
                        basicLabMap.remove("ndbjg_data");
                        basicLabMap.remove("szzq_data");
                        basicLabMap.remove("xcg_data");

                        if (shjcLab.size() > 0) {
                            basicLabMap.put("zhyl00008", shjcLab);
                        } else {
                            basicLabMap.put("zhyl00008", null);
                        }
                        if (ccrLab.size() > 0) {
                            basicLabMap.put("zhyl000081", ccrLab);
                        } else {
                            basicLabMap.put("zhyl000081", null);
                        }
                        basicLabMap.put("zhyl000082", ngjcLab);
                        basicLabMap.put("zhyl000083", ngdlLab);
                        if (ndbdlLab.size() > 0) {
                            basicLabMap.put("zhyl000084", ndbdlLab);
                        } else {
                            basicLabMap.put("zhyl000084", null);
                        }
                        if (ndbjgLab.size() > 0) {
                            basicLabMap.put("zhyl000085", ndbjgLab);
                        } else {
                            basicLabMap.put("zhyl000085", null);
                        }
                        if (szzqLab.size() > 0) {
                            basicLabMap.put("zhyl000086", szzqLab);
                        } else {
                            basicLabMap.put("zhyl000086", null);
                        }
                        if (xcgLab.size() > 0) {
                            basicLabMap.put("zhyl000087", xcgLab);
                        } else {
                            basicLabMap.put("zhyl000087", null);
                        }


                    }


                    jsonObject.put("zhyl008", basicLabMap); //实验室检查
//--------------------------------------------------
                    String newBingLiSql = bingliSql.replace("?", s);
                    Map<String, Object> bingliMap = commonExecute1(connection, newBingLiSql, statement, resultSet);
                    JSONObject bingLiJsonObject = new JSONObject();

                    if (bingliMap != null && bingliMap.size() > 0) {

                        for (String key : bingliMap.keySet()) {

                            if (bingliMap.keySet().contains("def_data") && key.equals("def_data")) {   //解析def_data中的数据
                                if (bingliMap.get(key) != null) {
                                    JSONArray bingliList = JSONArray.parseArray(String.valueOf(bingliMap.get(key)));
                                    for (Object o : bingliList) {
                                        o = String.valueOf(o).replace("time", "BIOPSY_TIME").replace("select", "BIOPSY_SITE")
                                                .replace("name", "BIOPSY_HOSP");
                                        bingLiJsonObject = JSONObject.parseObject((String) o);
                                    }
                                }
                                if(bingLiJsonObject!=null){
                                    if (bingLiJsonObject.keySet().contains("BIOPSY_SITE") && bingLiJsonObject.get("BIOPSY_SITE") != null) {
                                        Object o = bingLiJsonObject.get("BIOPSY_SITE");
                                        if (String.valueOf(o).equals("1")) {
                                            bingLiJsonObject.put("BIOPSY_SITE", "2");
                                        }
                                        if (String.valueOf(o).equals("2")) {
                                            bingLiJsonObject.put("BIOPSY_SITE", "1");
                                        }
                                    }
                                }

                            }
                        }

                        bingliMap.remove("def_data");
                        bingliMap.put("zhyl00009", bingLiJsonObject);
                    }

                    jsonObject.put("zhyl009", bingliMap); //病理检查
                    //--------------------------------------------------

                    String newJiYinSql=jiYinBiaoBenSql.replace("?", s);

                    Map<String, Object> jiyinMap = commonExecute1(connection, newJiYinSql, statement, resultSet);
                    JSONArray jiYinArray=new JSONArray();

                    if (jiyinMap != null && jiyinMap.size() > 0) {

                        for (String key : jiyinMap.keySet()) {

                            if (jiyinMap.keySet().contains("datas") && key.equals("datas")) {   //解析datas中的数据
                                if (jiyinMap.get(key) != null) {
                                    JSONArray jiYinList = JSONArray.parseArray(String.valueOf(jiyinMap.get(key)));
                                    for (Object o : jiYinList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("type")) {
                                            newJsonObj.put("GENE_NUCLEO", jsonObj.get("type"));
                                        }
                                        if (jsonObj.keySet().contains("no")) {
                                            newJsonObj.put("GENE_ID", jsonObj.get("no"));
                                        }
                                        if (jsonObj.keySet().contains("source")) {
                                            newJsonObj.put("GENE_BIOSPE", jsonObj.get("source"));
                                        }
                                        if (jsonObj.keySet().contains("check_time")) {
                                            newJsonObj.put("GENE_TIME", jsonObj.get("check_time"));
                                        }
                                        if (jsonObj.keySet().contains("result")) {
                                            newJsonObj.put("GENE_TEXT", jsonObj.get("result"));
                                        }

                                        jiYinArray.add(newJsonObj);
                                    }
                                }


                            }
                        }

                        jiyinMap.remove("datas");
                        jiyinMap.put("zhyl00010", jiYinArray);
                    }

                    jsonObject.put("zhyl010", jiyinMap); //基因

//---------------------------------------------------------------





                    System.out.println(jsonObject);


                }

            }


        }


    }


    private static List<Map<String, Object>> commonExecute2(Connection connection, String sql, Statement statementTable, ResultSet resultSetTable) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();

        try {
            statementTable = executeSql(sql, connection);
            resultSetTable = statementTable.executeQuery(sql);

            if (resultSetTable != null) {
                list = ResultSetUtils.listResultSetToJson(resultSetTable);
            }
        } finally {
            close(statementTable, resultSetTable);
        }
        return list;
    }

    private static Map<String, Object> commonExecute1(Connection connection, String sql, Statement statementTable, ResultSet resultSetTable) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            statementTable = executeSql(sql, connection);
            resultSetTable = statementTable.executeQuery(sql);

            if (resultSetTable != null) {
                map = ResultSetUtils.allResultSetToJson(resultSetTable);
            }
        } finally {
            close(statementTable, resultSetTable);
        }
        return map;
    }

    private static List<String> commonExecute(Connection connection, Statement statementTable, ResultSet resultSetTable) throws Exception {
        List<String> list = new ArrayList<>();
        try {
            statementTable = executeSql(idSql, connection);
            resultSetTable = statementTable.executeQuery(idSql);

            if (resultSetTable != null) {
                list = ResultSetUtils.allResultSet(resultSetTable);
            }
        } finally {
            close(statementTable, resultSetTable);
        }
        return list;
    }


    private static Statement executeSql(String sql, Connection connection) throws Exception {
        Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
                ResultSet.CLOSE_CURSORS_AT_COMMIT);
        statement.setQueryTimeout(6000);
        statement.setFetchSize(100000);
        statement.setEscapeProcessing(false);
        return statement;
    }

    private static void close(Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }

        if (statement != null) {
            statement.close();
        }
    }

}
