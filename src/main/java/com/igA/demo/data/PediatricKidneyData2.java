package com.igA.demo.data;

import com.igA.demo.constant.kettleResponse;
import com.igA.demo.utils.*;
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

import static com.igA.demo.constant.DataTransform.*;
import static com.igA.demo.constant.PediatricKidneyDatabaseConstant2.*;
import static com.igA.demo.data.igAData.*;
import static com.igA.demo.data.igAData.transformData;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogChannelFactory;
import org.springframework.web.multipart.MultipartFile;

//遗传病-早发蛋白尿数据

@Slf4j
public class PediatricKidneyData2 {

    public static void transformData(String baseUrl, String admin, String password, String id, String start, String type) throws Exception {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
//            connection = JDBCUtils.getConnection("sqlserver", "43.143.220.216", "1433", "PediatricKidneyDatabase", "PediatricKidneyDatabase", "sa", "6774566sa!");
            connection = JDBCUtils.getConnection("sqlserver", "127.0.0.1", "1433", "master", "master", "sa", "123456");
        } catch (SQLException e) {
            log.error("database connection error: " + e, "");
        }

        if (connection != null) {
            // 设置连接的持久性
            connection.setHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);
            List<String> idList = new ArrayList<>();
            if (id != null) {
                idList = Collections.singletonList(id);
            } else {
                idList = commonExecute(connection, statement, resultSet, KidneyIdSql2.replace("#", type));
            }

            if (idList != null && idList.size() > 0) {
                for (String s : idList) {
                    //--------------------------------------一般资料------------------------------------

                    JSONObject jsonObject = new JSONObject();   //----总的大json,每个患者一个json
                    jsonObject.put("id", s);

                    Map<String, Object> map = new HashMap<>();
                    String yibanziliao = yibanziliao2.replace("?", s);  //一般资料
                    map = commonExecute1(connection, yibanziliao, statement, resultSet);
                    JSONObject zhyl1100022Json = new JSONObject();
                    JSONObject zhyl1100027Json = new JSONObject();
                    JSONObject zhyl1100037Json = new JSONObject();

                    if (map != null && map.size() > 0) {
                        for (String key : map.keySet()) {
                            if (map.keySet().contains("zhyl1110023") && key.equals("zhyl1110023")) {
                                zhyl1100022Json.put("zhyl1110023", map.get(key));
                            }
                            if (map.keySet().contains("zhyl1110024") && key.equals("zhyl1110024")) {
                                zhyl1100022Json.put("zhyl1110024", map.get(key));
                            }
                            if (map.keySet().contains("zhyl1100026") && key.equals("zhyl1100026")) {
                                zhyl1100022Json.put("zhyl1100026", map.get(key));
                            }
                            if (map.keySet().contains("zhyl1110028") && key.equals("zhyl1110028")) {
                                zhyl1100027Json.put("zhyl1110028", map.get(key));
                            }
                            if (map.keySet().contains("zhyl1110029") && key.equals("zhyl1110029")) {
                                zhyl1100027Json.put("zhyl1110029", map.get(key));
                            }
                            if (map.keySet().contains("zhyl1110030") && key.equals("zhyl1110030")) {
                                zhyl1100027Json.put("zhyl1110030", map.get(key));
                            }

                            if (map.keySet().contains("zhyl11000371") && key.equals("zhyl11000371")) {
                                zhyl1100037Json.put("zhyl11000371", map.get(key));
                            }
                            if (map.keySet().contains("zhyl11000372") && key.equals("zhyl11000372")) {
                                zhyl1100037Json.put("zhyl11000372", map.get(key));
                            }
                            if (map.keySet().contains("zhyl11000373") && key.equals("zhyl11000373")) {
                                zhyl1100037Json.put("zhyl11000373", map.get(key));
                            }
                            if (map.keySet().contains("zhyl11000374") && key.equals("zhyl11000374")) {
                                zhyl1100037Json.put("zhyl11000374", map.get(key));
                            }

                        }
                        map.remove("zhyl1110023");
                        map.remove("zhyl1110024");
                        map.remove("zhyl1110026");
                        map.remove("zhyl1110028");
                        map.remove("zhyl1110029");
                        map.remove("zhyl1110030");
                        map.remove("zhyl11000371");
                        map.remove("zhyl11000372");
                        map.remove("zhyl11000373");
                        map.remove("zhyl11000374");


                        map.put("zhyl1100022", zhyl1100022Json);
                        map.put("zhyl1100027", zhyl1100027Json);
                        map.put("zhyl1100037", zhyl1100037Json);


                        JSONObject zhyl1000000 = new JSONObject();

                        zhyl1000000.put("zhyl1100000", map);
                        jsonObject.put("zhyl1000000", zhyl1000000);   //一般资料zhyl1000000
                    }


//----------------------------------------------现病史-------------------------------------------------
                    String xianbingshi = xianbingshi2.replace("?", s); //现病史
                    Map<String, Object> xianbingshiMap = commonExecute1(connection, xianbingshi, statement, resultSet);
                    if (xianbingshiMap != null) {
                        if (xianbingshiMap.keySet().contains("zhyl2100030")) {
                            if (xianbingshiMap.get("zhyl2100030") != null) {
                                if (xianbingshiMap.get("zhyl2100030").toString().contains(",")) {
                                    String commaSeparatedString = (String) xianbingshiMap.get("zhyl2100030");
                                    xianbingshiMap.put("zhyl2100030", Arrays.asList(commaSeparatedString.split(",")));
                                } else {
                                    String commaSeparatedString = (String) xianbingshiMap.get("zhyl2100030");
                                    xianbingshiMap.put("zhyl2100030", Arrays.asList(commaSeparatedString));
                                }
                            }
                        }

                        if (xianbingshiMap.keySet().contains("zhyl2100031")) {
                            if (xianbingshiMap.get("zhyl2100031") != null) {
                                if (xianbingshiMap.get("zhyl2100031").toString().contains(",")) {
                                    String commaSeparatedString = (String) xianbingshiMap.get("zhyl2100031");
                                    xianbingshiMap.put("zhyl2100031", Arrays.asList(commaSeparatedString.split(",")));
                                } else {
                                    String commaSeparatedString = (String) xianbingshiMap.get("zhyl2100031");
                                    xianbingshiMap.put("zhyl2100031", Arrays.asList(commaSeparatedString));
                                }
                            }
                        }

                        jsonObject.put("zhyl2000000", xianbingshiMap);
                    }


//----------------------------------------------个人史---------------------------------

                    String gerrenshi = gerenshi2.replace("?", s); //个人史

                    Map<String, Object> gerrenshiMap = commonExecute1(connection, gerrenshi, statement, resultSet);

                    JSONObject zhyl3100000Json = new JSONObject();

                    if (gerrenshiMap != null && gerrenshiMap.size() > 0) {
                        for (String key : gerrenshiMap.keySet()) {
                            transJson(gerrenshiMap, key, "zhyl3100002", zhyl3100000Json, null);
                            transJson(gerrenshiMap, key, "zhyl3100003", zhyl3100000Json, null);

                        }
                        gerrenshiMap.remove("zhyl3100002");
                        gerrenshiMap.remove("zhyl3100003");

                        gerrenshiMap.put("zhyl3100000", zhyl3100000Json);

                        jsonObject.put("zhyl3000000", gerrenshiMap);

                    }


//----------------------------------------------家族史---------------------------------
                    JSONObject zhyl4000000Json = new JSONObject();//家族史

                    JSONArray zhyl4100000JsonArray = new JSONArray(); //
                    jiazushi(s, jiazhushi2, connection, statement, resultSet, zhyl4100000JsonArray);//家族史
                    zhyl4000000Json.put("zhyl4100000", zhyl4100000JsonArray);

                    jsonObject.put("zhyl4000000", zhyl4000000Json);


//-------------------------------------------体格检查--------------------------------------------
                    JSONArray zhyl5000000Json = new JSONArray(); //
                    String tigejianchaSql = tigejiancha2.replace("?", s);
                    List<Map<String, Object>> tigeList = commonExecute2(connection, tigejianchaSql, statement, resultSet);//有多条
                    if (tigeList != null && tigeList.size() > 0) {
                        for (Map<String, Object> yaoWuMap : tigeList) {
                            if (yaoWuMap != null && yaoWuMap.size() > 0) {
                                JSONObject zhyl5000022Json = new JSONObject();
                                JSONObject zhyl5000027Json = new JSONObject();
                                JSONObject zhyl5000030Json = new JSONObject();
                                JSONObject zhyl5000050Json = new JSONObject();
                                for (String key : yaoWuMap.keySet()) {
                                    transJson(yaoWuMap, key, "zhyl5100022", zhyl5000022Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100023", zhyl5000022Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100028", zhyl5000027Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100029", zhyl5000027Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100031", zhyl5000030Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100032", zhyl5000030Json, null);
                                    transJson(yaoWuMap, key, "zhyl5000051", zhyl5000050Json, null);
                                    transJson(yaoWuMap, key, "zhyl5000052", zhyl5000050Json, null);
                                    transJson(yaoWuMap, key, "zhyl5000053", zhyl5000050Json, null);
                                    transJson(yaoWuMap, key, "zhyl5000054", zhyl5000050Json, null);

                                }
                                yaoWuMap.remove("zhyl5100022");
                                yaoWuMap.remove("zhyl5100023");
                                yaoWuMap.remove("zhyl5100028");
                                yaoWuMap.remove("zhyl5100029");
                                yaoWuMap.remove("zhyl5100031");
                                yaoWuMap.remove("zhyl5100032");
                                yaoWuMap.remove("zhyl5100051");
                                yaoWuMap.remove("zhyl5100052");
                                yaoWuMap.remove("zhyl5100053");
                                yaoWuMap.remove("zhyl5100054");

                                yaoWuMap.put("zhyl5000022", zhyl5000022Json);
                                yaoWuMap.put("zhyl5000027", zhyl5000027Json);
                                yaoWuMap.put("zhyl5000030", zhyl5000030Json);
                                yaoWuMap.put("zhyl5000050", zhyl5000050Json);
                                zhyl5000000Json.add(yaoWuMap);
                            }
                        }

                    }
                    jsonObject.put("zhyl5000000", zhyl5000000Json);
//-------------------------------------------辅助检查--------------------------------------------
                    JSONObject zhyl6000000JSONObject = new JSONObject(); //
                    JSONObject zhyl60000000JSONObject = new JSONObject();

                    JSONArray zhyl6000001JSONArray = new JSONArray(); //尿常规
                    String niaochangguiSql = niaochanggui2.replace("?", s);
                    List<Map<String, Object>> niaochangguiList = commonExecute2(connection, niaochangguiSql, statement, resultSet);//有多条
                    if (niaochangguiList != null && niaochangguiList.size() > 0) {
                        for (Map<String, Object> niaochanggui : niaochangguiList) {
                            if (niaochanggui != null && niaochanggui.size() > 0) {
                                JSONObject zhyl6000001SONObject = new JSONObject();
                                JSONObject zhyl600000105JSONObject = new JSONObject();
                                JSONObject zhyl600000106JSONObject = new JSONObject();
                                JSONObject zhyl600000107JSONObject = new JSONObject();
                                JSONObject zhyl600000108JSONObject = new JSONObject();
                                JSONObject zhyl600000109JSONObject = new JSONObject();
                                JSONObject zhyl600000110JSONObject = new JSONObject();
                                JSONObject zhyl600000111JSONObject = new JSONObject();
                                JSONObject zhyl600000112JSONObject = new JSONObject();
                                JSONObject zhyl600000113JSONObject = new JSONObject();

                                for (String key : niaochanggui.keySet()) {
                                    transJson(niaochanggui, key, "zhyl600000101", zhyl6000001SONObject, null);
                                    transJson(niaochanggui, key, "zhyl600000102", zhyl6000001SONObject, null);
                                    transJson(niaochanggui, key, "zhyl600000103", zhyl6000001SONObject, null);
                                    transJson(niaochanggui, key, "zhyl600000104", zhyl6000001SONObject, null);

                                    transJson(niaochanggui, key, "zhyl6000001051", zhyl600000105JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001052", zhyl600000105JSONObject, null);

                                    transJson(niaochanggui, key, "zhyl6000001061", zhyl600000106JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001062", zhyl600000106JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001063", zhyl600000106JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001064", zhyl600000106JSONObject, null);

                                    transJson(niaochanggui, key, "zhyl6000001071", zhyl600000107JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001072", zhyl600000107JSONObject, null);

                                    transJson(niaochanggui, key, "zhyl6000001081", zhyl600000108JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001082", zhyl600000108JSONObject, null);

                                    transJson(niaochanggui, key, "zhyl6000001091", zhyl600000109JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001092", zhyl600000109JSONObject, null);

                                    transJson(niaochanggui, key, "zhyl6000001101", zhyl600000110JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001102", zhyl600000110JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001103", zhyl600000110JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001104", zhyl600000110JSONObject, null);

                                    transJson(niaochanggui, key, "zhyl6000001111", zhyl600000111JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001112", zhyl600000111JSONObject, null);

                                    transJson(niaochanggui, key, "zhyl6000001121", zhyl600000112JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001122", zhyl600000112JSONObject, null);

                                    transJson(niaochanggui, key, "zhyl6000001131", zhyl600000113JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001132", zhyl600000113JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001133", zhyl600000113JSONObject, null);
                                    transJson(niaochanggui, key, "zhyl6000001134", zhyl600000113JSONObject, null);
                                }
                                zhyl6000001SONObject.put("zhyl600000105", zhyl600000105JSONObject);
                                zhyl6000001SONObject.put("zhyl600000106", zhyl600000106JSONObject);
                                zhyl6000001SONObject.put("zhyl600000107", zhyl600000107JSONObject);
                                zhyl6000001SONObject.put("zhyl600000108", zhyl600000108JSONObject);
                                zhyl6000001SONObject.put("zhyl600000109", zhyl600000109JSONObject);
                                zhyl6000001SONObject.put("zhyl600000110", zhyl600000110JSONObject);
                                zhyl6000001SONObject.put("zhyl600000111", zhyl600000111JSONObject);
                                zhyl6000001SONObject.put("zhyl600000112", zhyl600000112JSONObject);
                                zhyl6000001SONObject.put("zhyl600000113", zhyl600000113JSONObject);

                                zhyl6000001JSONArray.add(zhyl6000001SONObject);
                            }

                        }
                        zhyl60000000JSONObject.put("zhyl6000001", zhyl6000001JSONArray);
                    }


                    JSONArray zhyl6000030Json = new JSONArray(); //24小时尿钙体重比
                    String niaogaitizhong2Sql = niaogaitizhong2.replace("?", s);
                    List<Map<String, Object>> niaogaitizhong2SqlList = commonExecute2(connection, niaogaitizhong2Sql, statement, resultSet);//有多条
                    if (niaogaitizhong2SqlList != null && niaogaitizhong2SqlList.size() > 0) {
                        for (Map<String, Object> niaogaitizhong : niaogaitizhong2SqlList) {
                            if (niaogaitizhong != null && niaogaitizhong.size() > 0) {
                                JSONObject zhyl6000030SONObject = new JSONObject();
                                JSONObject zhyl600003004JSONObject = new JSONObject();
                                JSONObject zhyl600003006JSONObject = new JSONObject();

                                for (String key : niaogaitizhong.keySet()) {
                                    transJson(niaogaitizhong, key, "zhyl600003001", zhyl6000030SONObject, null);
                                    transJson(niaogaitizhong, key, "zhyl600003002", zhyl6000030SONObject, null);
                                    transJson(niaogaitizhong, key, "zhyl600003003", zhyl6000030SONObject, null);
                                    transJson(niaogaitizhong, key, "zhyl600003005", zhyl6000030SONObject, null);

                                    transJson(niaogaitizhong, key, "zhyl6000030041", zhyl600003004JSONObject, null);
                                    transJson(niaogaitizhong, key, "zhyl6000030042", zhyl600003004JSONObject, null);

                                    transJson(niaogaitizhong, key, "zhyl6000030061", zhyl600003006JSONObject, null);
                                    transJson(niaogaitizhong, key, "zhyl6000030062", zhyl600003006JSONObject, null);
                                }
                                zhyl6000030SONObject.put("zhyl600003004", zhyl600003004JSONObject);
                                zhyl6000030SONObject.put("zhyl600003006", zhyl600003006JSONObject);

                                zhyl6000030Json.add(zhyl6000030SONObject);
                            }

                        }
                        zhyl60000000JSONObject.put("zhyl6000030", zhyl6000030Json);
                    }


                    JSONArray zhyl6000002JSONArray = new JSONArray(); //尿蛋白肌酐比
                    String niaodanbaijiganSql = niaodanbaijigan2.replace("?", s);
                    List<Map<String, Object>> niaodanbaijiganSqlList = commonExecute2(connection, niaodanbaijiganSql, statement, resultSet);
                    if (niaodanbaijiganSqlList != null && niaodanbaijiganSqlList.size() > 0) {
                        for (Map<String, Object> niaodanbai : niaodanbaijiganSqlList) {
                            if (niaodanbai != null && niaodanbai.size() > 0) {
                                JSONObject zhyl6000002SONObject = new JSONObject();
                                JSONObject zhyl600000206JSONObject = new JSONObject();

                                for (String key : niaodanbai.keySet()) {
                                    transJson(niaodanbai, key, "zhyl600000201", zhyl6000002SONObject, null);
                                    transJson(niaodanbai, key, "zhyl600000202", zhyl6000002SONObject, null);
                                    transJson(niaodanbai, key, "zhyl600000203", zhyl6000002SONObject, null);
                                    transJson(niaodanbai, key, "zhyl600000204", zhyl6000002SONObject, null);
                                    transJson(niaodanbai, key, "zhyl600000205", zhyl6000002SONObject, null);

                                    transJson(niaodanbai, key, "zhyl6000002061", zhyl600000206JSONObject, null);
                                    transJson(niaodanbai, key, "zhyl6000002062", zhyl600000206JSONObject, null);
                                }
                                zhyl6000002SONObject.put("zhyl600000206", zhyl600000206JSONObject);
                                zhyl6000002JSONArray.add(zhyl6000002SONObject);
                            }

                        }
                        zhyl60000000JSONObject.put("zhyl6000002", zhyl6000002JSONArray);
                    }


                    JSONArray zhyl6000003JSONArray = new JSONArray(); //24h尿蛋白定量
                    String niaodanbaidingliang24h2Sql = niaodanbaidingliang24h2.replace("?", s);
                    List<Map<String, Object>> niaodanbaidingliang24h2SqlList = commonExecute2(connection, niaodanbaidingliang24h2Sql, statement, resultSet);
                    if (niaodanbaidingliang24h2SqlList != null && niaodanbaidingliang24h2SqlList.size() > 0) {
                        for (Map<String, Object> niaodanbaidingliang : niaodanbaidingliang24h2SqlList) {
                            if (niaodanbaidingliang != null && niaodanbaidingliang.size() > 0) {
                                JSONObject zhyl6000003SONObject = new JSONObject();
                                JSONObject zhyl600000304JSONObject = new JSONObject();
                                JSONObject zhyl600000306JSONObject = new JSONObject();

                                for (String key : niaodanbaidingliang.keySet()) {
                                    transJson(niaodanbaidingliang, key, "zhyl600000301", zhyl6000003SONObject, null);
                                    transJson(niaodanbaidingliang, key, "zhyl600000302", zhyl6000003SONObject, null);
                                    transJson(niaodanbaidingliang, key, "zhyl600000303", zhyl6000003SONObject, null);
                                    transJson(niaodanbaidingliang, key, "zhyl600000305", zhyl6000003SONObject, null);

                                    transJson(niaodanbaidingliang, key, "zhyl6000003041", zhyl600000304JSONObject, null);
                                    transJson(niaodanbaidingliang, key, "zhyl6000003042", zhyl600000304JSONObject, null);

                                    transJson(niaodanbaidingliang, key, "zhyl6000003061", zhyl600000306JSONObject, null);
                                    transJson(niaodanbaidingliang, key, "zhyl6000003062", zhyl600000306JSONObject, null);

                                }
                                zhyl6000003SONObject.put("zhyl600000304", zhyl600000304JSONObject);
                                zhyl6000003SONObject.put("zhyl600000306", zhyl600000306JSONObject);
                                zhyl6000003JSONArray.add(zhyl6000003SONObject);
                            }

                        }
                        zhyl60000000JSONObject.put("zhyl6000003", zhyl6000003JSONArray);
                    }


                    JSONArray zhyl6000004Json = new JSONArray(); //24h肌酐清除率
                    String jiganqingchu24h2Sql = jiganqingchu24h2.replace("?", s);
                    List<Map<String, Object>> jiganqingchu24h2SqlList = commonExecute2(connection, jiganqingchu24h2Sql, statement, resultSet);
                    if (jiganqingchu24h2SqlList != null) {
                        for (Map<String, Object> map1 : jiganqingchu24h2SqlList) {
                            if (map1 != null && map1.size() > 0) {
                                JSONObject zhyl6000004JsonObject = new JSONObject();
                                JSONObject zhyl600000404JsonObject = new JSONObject();
                                List<String> nameList = new ArrayList<>();
                                List<String> valueList = new ArrayList<>();
                                List<String> refList = new ArrayList<>();
                                for (String key : map1.keySet()) {
                                    if (key.toUpperCase().equals("HETERM") && map1.get(key).toString().contains(",")) {
                                        nameList = Arrays.asList(map1.get(key).toString().split(","));
                                    }
                                    if (key.toUpperCase().equals("VALUE") && map1.get(key).toString().contains(",")) {
                                        valueList = Arrays.asList(map1.get(key).toString().split(","));
                                    }
                                    if (key.toUpperCase().equals("HESREF") && map1.get(key).toString().contains(",")) {
                                        refList = Arrays.asList(map1.get(key).toString().split(","));
                                    }
                                    if (key.toUpperCase().equals("HEHOSP")) {   //检查医院
                                        zhyl6000004JsonObject.put("zhyl600000403", map1.get(key));
                                    }
                                    if (key.equals("zhyl600000401")) {//是否为随访数据
                                        zhyl6000004JsonObject.put("zhyl600000401", map1.get(key));
                                    }
                                    if (key.equals("zhyl600000402")) {//检查日期
                                        zhyl6000004JsonObject.put("zhyl600000402", map1.get(key));
                                    }
                                }
                                if (nameList.size() > 0) {
                                    for (int i = 0; i < nameList.size(); i++) {
                                        if (nameList.get(i).contains("24hCCr") && !nameList.get(i).contains("校正24hCCr")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl600000404JsonObject.put("zhyl6000004041", valueList.get(i));
                                            } else {
                                                zhyl600000404JsonObject.put("zhyl6000004041", null);
                                            }
                                            if (!refList.get(i).contains("#") && refList.get(i) != null) {
                                                zhyl600000404JsonObject.put("zhyl6000004042", refList.get(i));
                                            } else {
                                                zhyl600000404JsonObject.put("zhyl6000004042", null);
                                            }
                                            zhyl6000004JsonObject.put("zhyl600000404", zhyl600000404JsonObject);
                                        }
                                        if (nameList.get(i).contains("身高")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl600000405", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl600000405", null);
                                            }
                                        }
                                        if (nameList.get(i).contains("体重")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl600000406", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl600000406", null);
                                            }
                                        }
                                        if (nameList.get(i).contains("体表面积")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl600000407", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl600000407", null);
                                            }
                                        }
                                        if (nameList.get(i).contains("校正24hCCr")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl600000408", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl600000408", null);
                                            }
                                        }

                                    }

                                }
                                zhyl6000004Json.add(zhyl6000004JsonObject);
                            }
                        }
                        zhyl60000000JSONObject.put("zhyl6000004", zhyl6000004Json);
                    }

                    JSONArray zhyl6000005JSONArray = new JSONArray(); //肾早损
                    String shenzaosuai2Sql = shenzaosuai2.replace("?", s);
                    List<Map<String, Object>> shenzaosuai2SqlList = commonExecute2(connection, shenzaosuai2Sql, statement, resultSet);
                    if (shenzaosuai2SqlList != null && shenzaosuai2SqlList.size() > 0) {
                        for (Map<String, Object> shenzaosuai : shenzaosuai2SqlList) {
                            if (shenzaosuai != null && shenzaosuai.size() > 0) {
                                JSONObject zhyl6000005SONObject = new JSONObject();
                                JSONObject zhyl600000504JSONObject = new JSONObject();
                                JSONObject zhyl600000505JSONObject = new JSONObject();
                                JSONObject zhyl600000506JSONObject = new JSONObject();
                                JSONObject zhyl600000507JSONObject = new JSONObject();
                                JSONObject zhyl600000508JSONObject = new JSONObject();

                                for (String key : shenzaosuai.keySet()) {
                                    transJson(shenzaosuai, key, "zhyl600000501", zhyl6000005SONObject, null);
                                    transJson(shenzaosuai, key, "zhyl600000502", zhyl6000005SONObject, null);
                                    transJson(shenzaosuai, key, "zhyl600000503", zhyl6000005SONObject, null);
                                    transJson(shenzaosuai, key, "zhyl600000509", zhyl6000005SONObject, null);

                                    transJson(shenzaosuai, key, "zhyl6000005041", zhyl600000504JSONObject, null);
                                    transJson(shenzaosuai, key, "zhyl6000005042", zhyl600000504JSONObject, null);
                                    transJson(shenzaosuai, key, "zhyl6000005043", zhyl600000504JSONObject, null);

                                    transJson(shenzaosuai, key, "zhyl6000005051", zhyl600000505JSONObject, null);
                                    transJson(shenzaosuai, key, "zhyl6000005052", zhyl600000505JSONObject, null);
                                    transJson(shenzaosuai, key, "zhyl6000005053", zhyl600000505JSONObject, null);

                                    transJson(shenzaosuai, key, "zhyl6000005061", zhyl600000506JSONObject, null);
                                    transJson(shenzaosuai, key, "zhyl6000005062", zhyl600000506JSONObject, null);
                                    transJson(shenzaosuai, key, "zhyl6000005063", zhyl600000506JSONObject, null);

                                    transJson(shenzaosuai, key, "zhyl6000005071", zhyl600000507JSONObject, null);
                                    transJson(shenzaosuai, key, "zhyl6000005072", zhyl600000507JSONObject, null);
                                    transJson(shenzaosuai, key, "zhyl6000005073", zhyl600000507JSONObject, null);

                                    transJson(shenzaosuai, key, "zhyl6000005081", zhyl600000508JSONObject, null);
                                    transJson(shenzaosuai, key, "zhyl6000005082", zhyl600000508JSONObject, null);
                                    transJson(shenzaosuai, key, "zhyl6000005083", zhyl600000508JSONObject, null);

                                }
                                zhyl6000005SONObject.put("zhyl600000504", zhyl600000504JSONObject);
                                zhyl6000005SONObject.put("zhyl600000505", zhyl600000505JSONObject);
                                zhyl6000005SONObject.put("zhyl600000506", zhyl600000506JSONObject);
                                zhyl6000005SONObject.put("zhyl600000507", zhyl600000507JSONObject);
                                zhyl6000005SONObject.put("zhyl600000508", zhyl600000508JSONObject);

                                zhyl6000005JSONArray.add(zhyl6000005SONObject);
                            }

                        }
                        zhyl60000000JSONObject.put("zhyl6000005", zhyl6000005JSONArray);
                    }

                    JSONArray zhyl6000006JSONArray = new JSONArray(); //尿蛋白电泳
                    String niaodanbaidianyong2Sql = niaodanbaidianyong2.replace("?", s);
                    List<Map<String, Object>> niaodanbaidianyong2SqlList = commonExecute2(connection, niaodanbaidianyong2Sql, statement, resultSet);
                    if (niaodanbaidianyong2SqlList != null && niaodanbaidianyong2SqlList.size() > 0) {
                        for (Map<String, Object> niaodanbaidianyong : niaodanbaidianyong2SqlList) {
                            if (niaodanbaidianyong != null && niaodanbaidianyong.size() > 0) {
                                JSONObject zhyl6000006SONObject = new JSONObject();
                                JSONObject zhyl600000604JSONObject = new JSONObject();
                                JSONObject zhyl600000605JSONObject = new JSONObject();
                                JSONObject zhyl600000606JSONObject = new JSONObject();

                                for (String key : niaodanbaidianyong.keySet()) {
                                    transJson(niaodanbaidianyong, key, "zhyl600000601", zhyl6000006SONObject, null);
                                    transJson(niaodanbaidianyong, key, "zhyl600000602", zhyl6000006SONObject, null);
                                    transJson(niaodanbaidianyong, key, "zhyl600000603", zhyl6000006SONObject, null);

                                    transJson(niaodanbaidianyong, key, "zhyl6000006041", zhyl600000604JSONObject, null);
                                    transJson(niaodanbaidianyong, key, "zhyl6000006042", zhyl600000604JSONObject, null);

                                    transJson(niaodanbaidianyong, key, "zhyl6000006051", zhyl600000605JSONObject, null);
                                    transJson(niaodanbaidianyong, key, "zhyl6000006052", zhyl600000605JSONObject, null);

                                    transJson(niaodanbaidianyong, key, "zhyl6000006061", zhyl600000606JSONObject, null);
                                    transJson(niaodanbaidianyong, key, "zhyl6000006062", zhyl600000606JSONObject, null);

                                }
                                zhyl6000006SONObject.put("zhyl600000604", zhyl600000604JSONObject);
                                zhyl6000006SONObject.put("zhyl600000605", zhyl600000605JSONObject);
                                zhyl6000006SONObject.put("zhyl600000606", zhyl600000606JSONObject);

                                zhyl6000006JSONArray.add(zhyl6000006SONObject);
                            }

                        }
                        zhyl60000000JSONObject.put("zhyl6000006", zhyl6000006JSONArray);
                    }


                    JSONArray zhyl6000007JSONArray = new JSONArray(); //血生化
                    //  commonjiancha(s, zhyl6000007JSONArray, xueshenghua2, zhyl60000000JSONObject, "zhyl6000007", connection, statement, resultSet);
                    String xueshenghua2Sql = xueshenghua2.replace("?", s);
                    List<Map<String, Object>> xueshenghua2SqlSqlList = commonExecute2(connection, xueshenghua2Sql, statement, resultSet);
                    if (xueshenghua2SqlSqlList != null && xueshenghua2SqlSqlList.size() > 0) {
                        for (Map<String, Object> xueshenghua : xueshenghua2SqlSqlList) {
                            if (xueshenghua != null && xueshenghua.size() > 0) {
                                JSONObject zhyl6000007SONObject = new JSONObject();
                                JSONObject zhyl600000705JSONObject = new JSONObject();
                                JSONObject zhyl600000706JSONObject = new JSONObject();
                                JSONObject zhyl600000707JSONObject = new JSONObject();
                                JSONObject zhyl600000708JSONObject = new JSONObject();
                                JSONObject zhyl600000709JSONObject = new JSONObject();
                                JSONObject zhyl600000710JSONObject = new JSONObject();
                                JSONObject zhyl600000711JSONObject = new JSONObject();
                                JSONObject zhyl600000712JSONObject = new JSONObject();
                                JSONObject zhyl600000713JSONObject = new JSONObject();
                                JSONObject zhyl600000714JSONObject = new JSONObject();
                                JSONObject zhyl600000715JSONObject = new JSONObject();
                                JSONObject zhyl600000716JSONObject = new JSONObject();
                                JSONObject zhyl600000717JSONObject = new JSONObject();
                                JSONObject zhyl600000718JSONObject = new JSONObject();
                                JSONObject zhyl600000719JSONObject = new JSONObject();
                                JSONObject zhyl600000720JSONObject = new JSONObject();
                                JSONObject zhyl600000721JSONObject = new JSONObject();
                                JSONObject zhyl600000722JSONObject = new JSONObject();

                                for (String key : xueshenghua.keySet()) {
                                    transJson(xueshenghua, key, "zhyl600000701", zhyl6000007SONObject, null);
                                    transJson(xueshenghua, key, "zhyl600000702", zhyl6000007SONObject, null);
                                    transJson(xueshenghua, key, "zhyl600000703", zhyl6000007SONObject, null);
                                    transJson(xueshenghua, key, "zhyl600000704", zhyl6000007SONObject, null);
                                    transJson(xueshenghua, key, "zhyl600000723", zhyl6000007SONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007051", zhyl600000705JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007052", zhyl600000705JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007061", zhyl600000706JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007062", zhyl600000706JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007071", zhyl600000707JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007072", zhyl600000707JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007081", zhyl600000708JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007082", zhyl600000708JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007091", zhyl600000709JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007092", zhyl600000709JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007101", zhyl600000710JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007102", zhyl600000710JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007111", zhyl600000711JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007112", zhyl600000711JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007121", zhyl600000712JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007122", zhyl600000712JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007131", zhyl600000713JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007132", zhyl600000713JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007141", zhyl600000714JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007142", zhyl600000714JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007151", zhyl600000715JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007152", zhyl600000715JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007161", zhyl600000716JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007162", zhyl600000716JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007171", zhyl600000717JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007172", zhyl600000717JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007181", zhyl600000718JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007182", zhyl600000718JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007191", zhyl600000719JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007192", zhyl600000719JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007201", zhyl600000720JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007202", zhyl600000720JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007211", zhyl600000721JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007212", zhyl600000721JSONObject, null);

                                    transJson(xueshenghua, key, "zhyl6000007221", zhyl600000722JSONObject, null);
                                    transJson(xueshenghua, key, "zhyl6000007222", zhyl600000722JSONObject, null);

                                }
                                zhyl6000007SONObject.put("zhyl600000705", zhyl600000705JSONObject);
                                zhyl6000007SONObject.put("zhyl600000706", zhyl600000706JSONObject);
                                zhyl6000007SONObject.put("zhyl600000707", zhyl600000707JSONObject);
                                zhyl6000007SONObject.put("zhyl600000708", zhyl600000708JSONObject);
                                zhyl6000007SONObject.put("zhyl600000709", zhyl600000709JSONObject);
                                zhyl6000007SONObject.put("zhyl600000710", zhyl600000710JSONObject);
                                zhyl6000007SONObject.put("zhyl600000711", zhyl600000711JSONObject);
                                zhyl6000007SONObject.put("zhyl600000712", zhyl600000712JSONObject);
                                zhyl6000007SONObject.put("zhyl600000713", zhyl600000713JSONObject);
                                zhyl6000007SONObject.put("zhyl600000714", zhyl600000714JSONObject);
                                zhyl6000007SONObject.put("zhyl600000715", zhyl600000715JSONObject);
                                zhyl6000007SONObject.put("zhyl600000716", zhyl600000716JSONObject);
                                zhyl6000007SONObject.put("zhyl600000717", zhyl600000717JSONObject);
                                zhyl6000007SONObject.put("zhyl600000718", zhyl600000718JSONObject);
                                zhyl6000007SONObject.put("zhyl600000719", zhyl600000719JSONObject);
                                zhyl6000007SONObject.put("zhyl600000720", zhyl600000720JSONObject);
                                zhyl6000007SONObject.put("zhyl600000721", zhyl600000721JSONObject);
                                zhyl6000007SONObject.put("zhyl600000722", zhyl600000722JSONObject);

                                zhyl6000007JSONArray.add(zhyl6000007SONObject);
                            }

                        }
                        zhyl60000000JSONObject.put("zhyl6000007", zhyl6000007JSONArray);
                    }

                    JSONArray zhyl6000008JSONArray = new JSONArray(); //免疫球蛋白
                    // commonjiancha(s, zhyl6000008JSONArray, mianyiqiudanbai2, zhyl60000000JSONObject, "zhyl6000008", connection, statement, resultSet);
                    String mianyiqiudanbai2Sql = mianyiqiudanbai2.replace("?", s);
                    List<Map<String, Object>> mianyiqiudanbai2SqlList = commonExecute2(connection, mianyiqiudanbai2Sql, statement, resultSet);
                    if (mianyiqiudanbai2SqlList != null && mianyiqiudanbai2SqlList.size() > 0) {
                        for (Map<String, Object> mianyiqiudanbai : mianyiqiudanbai2SqlList) {
                            if (mianyiqiudanbai != null && mianyiqiudanbai.size() > 0) {
                                JSONObject zhyl6000008SONObject = new JSONObject();
                                JSONObject zhyl600000804JSONObject = new JSONObject();
                                JSONObject zhyl600000805JSONObject = new JSONObject();
                                JSONObject zhyl600000806JSONObject = new JSONObject();

                                for (String key : mianyiqiudanbai.keySet()) {
                                    transJson(mianyiqiudanbai, key, "zhyl600000801", zhyl6000008SONObject, null);
                                    transJson(mianyiqiudanbai, key, "zhyl600000802", zhyl6000008SONObject, null);
                                    transJson(mianyiqiudanbai, key, "zhyl600000803", zhyl6000008SONObject, null);

                                    transJson(mianyiqiudanbai, key, "zhyl6000008041", zhyl600000804JSONObject, null);
                                    transJson(mianyiqiudanbai, key, "zhyl6000008042", zhyl600000804JSONObject, null);

                                    transJson(mianyiqiudanbai, key, "zhyl6000008051", zhyl600000805JSONObject, null);
                                    transJson(mianyiqiudanbai, key, "zhyl6000008052", zhyl600000805JSONObject, null);

                                    transJson(mianyiqiudanbai, key, "zhyl6000008061", zhyl600000806JSONObject, null);
                                    transJson(mianyiqiudanbai, key, "zhyl6000008062", zhyl600000806JSONObject, null);
                                }
                                zhyl6000008SONObject.put("zhyl600000804", zhyl600000804JSONObject);
                                zhyl6000008SONObject.put("zhyl600000805", zhyl600000805JSONObject);
                                zhyl6000008SONObject.put("zhyl600000806", zhyl600000806JSONObject);

                                zhyl6000008JSONArray.add(zhyl6000008SONObject);
                            }

                        }
                        zhyl60000000JSONObject.put("zhyl6000008", zhyl6000008JSONArray);
                    }


                    JSONArray zhyl6000009JSONArray = new JSONArray(); //血补体
                    //commonjiancha(s, zhyl6000009Json, xuebuti2, zhyl60000000JSONObject, "zhyl6000009", connection, statement, resultSet);
                    String xuebuti2Sql = xuebuti2.replace("?", s);
                    List<Map<String, Object>> xuebuti2SqllList = commonExecute2(connection, xuebuti2Sql, statement, resultSet);
                    if (xuebuti2SqllList != null && xuebuti2SqllList.size() > 0) {
                        for (Map<String, Object> xuebuti : xuebuti2SqllList) {
                            if (xuebuti != null && xuebuti.size() > 0) {
                                JSONObject zhyl6000009SONObject = new JSONObject();
                                JSONObject zhyl600000904JSONObject = new JSONObject();
                                JSONObject zhyl600000905JSONObject = new JSONObject();

                                for (String key : xuebuti.keySet()) {
                                    transJson(xuebuti, key, "zhyl600000901", zhyl6000009SONObject, null);
                                    transJson(xuebuti, key, "zhyl600000902", zhyl6000009SONObject, null);
                                    transJson(xuebuti, key, "zhyl600000903", zhyl6000009SONObject, null);

                                    transJson(xuebuti, key, "zhyl6000009041", zhyl600000904JSONObject, null);
                                    transJson(xuebuti, key, "zhyl6000009042", zhyl600000904JSONObject, null);

                                    transJson(xuebuti, key, "zhyl6000009051", zhyl600000905JSONObject, null);
                                    transJson(xuebuti, key, "zhyl6000009052", zhyl600000905JSONObject, null);

                                }
                                zhyl6000009SONObject.put("zhyl600000904", zhyl600000904JSONObject);
                                zhyl6000009SONObject.put("zhyl600000905", zhyl600000905JSONObject);

                                zhyl6000009JSONArray.add(zhyl6000009SONObject);
                            }

                        }
                        zhyl60000000JSONObject.put("zhyl6000009", zhyl6000009JSONArray);
                    }


                    JSONArray zhyl6000010Json = new JSONArray(); //感染筛查
                    commonjiancha(s, zhyl6000010Json, ganranshuaicha2, zhyl60000000JSONObject, "zhyl6000010", connection, statement, resultSet);

                    JSONArray zhyl6000011Json = new JSONArray(); //TORCH
                    commonjiancha(s, zhyl6000011Json, TORCH2, zhyl60000000JSONObject, "zhyl6000011", connection, statement, resultSet);

                    JSONArray zhyl6000013Json = new JSONArray(); //超声心动
                    commonjiancha(s, zhyl6000013Json, chaoshenxindong2, zhyl60000000JSONObject, "zhyl6000013", connection, statement, resultSet);

                    JSONArray zhyl6000014JSONArray = new JSONArray(); //腹部B超
                    //   commonjiancha(s, zhyl6000014Json, fububichao2, zhyl60000000JSONObject, "zhyl6000014", connection, statement, resultSet);
                    String fububichao2Sql = fububichao2.replace("?", s);
                    List<Map<String, Object>> fububichao2SqllList = commonExecute2(connection, fububichao2Sql, statement, resultSet);
                    if (fububichao2SqllList != null && fububichao2SqllList.size() > 0) {
                        for (Map<String, Object> fububichao : fububichao2SqllList) {
                            if (fububichao != null && fububichao.size() > 0) {
                                JSONObject zhyl6000014SONObject = new JSONObject();
                                JSONObject zhyl600001404JSONObject = new JSONObject();
                                JSONObject zhyl600001405JSONObject = new JSONObject();

                                for (String key : fububichao.keySet()) {
                                    transJson(fububichao, key, "zhyl600001401", zhyl6000014SONObject, null);
                                    transJson(fububichao, key, "zhyl600001402", zhyl6000014SONObject, null);
                                    transJson(fububichao, key, "zhyl600001403", zhyl6000014SONObject, null);
                                    transJson(fububichao, key, "zhyl600001406", zhyl6000014SONObject, null);

                                    transJson(fububichao, key, "zhyl6000014041", zhyl600001404JSONObject, null);
                                    transJson(fububichao, key, "zhyl6000014042", zhyl600001404JSONObject, null);
                                    transJson(fububichao, key, "zhyl6000014043", zhyl600001404JSONObject, null);

                                    transJson(fububichao, key, "zhyl6000014051", zhyl600001405JSONObject, null);
                                    transJson(fububichao, key, "zhyl6000014052", zhyl600001405JSONObject, null);
                                    transJson(fububichao, key, "zhyl6000014053", zhyl600001405JSONObject, null);
                                    transJson(fububichao, key, "zhyl6000014054", zhyl600001405JSONObject, null);
                                    transJson(fububichao, key, "zhyl6000014055", zhyl600001405JSONObject, null);
                                    transJson(fububichao, key, "zhyl6000014056", zhyl600001405JSONObject, null);
                                    transJson(fububichao, key, "zhyl6000014057", zhyl600001405JSONObject, null);
                                    transJson(fububichao, key, "zhyl6000014058", zhyl600001405JSONObject, null);
                                    transJson(fububichao, key, "zhyl6000014059", zhyl600001405JSONObject, null);

                                }
                                zhyl6000014SONObject.put("zhyl600001404", zhyl600001404JSONObject);
                                zhyl6000014SONObject.put("zhyl600001405", zhyl600001405JSONObject);

                                zhyl6000014JSONArray.add(zhyl6000014SONObject);
                            }

                        }
                        zhyl60000000JSONObject.put("zhyl6000014", zhyl6000014JSONArray);
                    }


                    JSONArray zhyl6100055JSONArray = new JSONArray(); //免疫荧光-光镜
                    String guangjing2Sql = guangjing2.replace("?", s);
                    List<Map<String, Object>> guangjing2SqlList = commonExecute2(connection, guangjing2Sql, statement, resultSet);
                    if (guangjing2SqlList != null && guangjing2SqlList.size() > 0) {
                        for (Map<String, Object> guangjingMap : guangjing2SqlList) {
                            if (guangjingMap != null && guangjingMap.size() > 0) {
                                JSONObject zhyl6100055Json = new JSONObject();
                                JSONObject zhyl6100061Json = new JSONObject();
                                JSONObject zhyl6100062Json = new JSONObject();
                                for (String key : guangjingMap.keySet()) {
                                    transJson(guangjingMap, key, "zhyl6100056", zhyl6100055Json, null);
                                    transJson(guangjingMap, key, "zhyl6100057", zhyl6100055Json, null);
                                    transJson(guangjingMap, key, "zhyl6100058", zhyl6100055Json, null);
                                    transJson(guangjingMap, key, "zhyl6100059", zhyl6100055Json, null);
                                    transJson(guangjingMap, key, "zhyl6100060", zhyl6100055Json, null);

                                    transJson(guangjingMap, key, "zhyl6200000", zhyl6100061Json, null);
                                    if (key.equals("zhyl6200001")) {
                                        transformQiangdu(zhyl6100061Json, guangjingMap.get(key), "zhyl6200001");
                                    }
                                    transJson(guangjingMap, key, "zhyl6200002", zhyl6100061Json, "list");
                                    transJson(guangjingMap, key, "zhyl6200003", zhyl6100061Json, "list");
                                    if (key.equals("zhyl6200004")) {
                                        transformQiangdu(zhyl6100061Json, guangjingMap.get(key), "zhyl6200004");

                                    }
                                    transJson(guangjingMap, key, "zhyl6200005", zhyl6100061Json, "list");
                                    transJson(guangjingMap, key, "zhyl6200006", zhyl6100061Json, "list");
                                    if (key.equals("zhyl6200007")) {
                                        transformQiangdu(zhyl6100061Json, guangjingMap.get(key), "zhyl6200007");

                                    }
                                    transJson(guangjingMap, key, "zhyl6200008", zhyl6100061Json, "list");
                                    transJson(guangjingMap, key, "zhyl6200009", zhyl6100061Json, "list");
                                    if (key.equals("zhyl6200010")) {
                                        transformQiangdu(zhyl6100061Json, guangjingMap.get(key), "zhyl6200010");

                                    }
                                    transJson(guangjingMap, key, "zhyl6200011", zhyl6100061Json, "list");
                                    transJson(guangjingMap, key, "zhyl6200012", zhyl6100061Json, "list");
                                    if (key.equals("zhyl6200013")) {
                                        transformQiangdu(zhyl6100061Json, guangjingMap.get(key), "zhyl6200013");

                                    }
                                    transJson(guangjingMap, key, "zhyl6200014", zhyl6100061Json, "list");
                                    transJson(guangjingMap, key, "zhyl6200015", zhyl6100061Json, "list");
                                    if (key.equals("zhyl6200016")) {
                                        transformQiangdu(zhyl6100061Json, guangjingMap.get(key), "zhyl6200016");

                                    }
                                    transJson(guangjingMap, key, "zhyl6200017", zhyl6100061Json, "list");
                                    transJson(guangjingMap, key, "zhyl6200018", zhyl6100061Json, "list");
                                    transJson(guangjingMap, key, "zhyl61000611", zhyl6100061Json, null);

                                    transJson(guangjingMap, key, "zhyl6200019", zhyl6100062Json, null);
                                    transJson(guangjingMap, key, "zhyl6200020", zhyl6100062Json, null);
                                    transJson(guangjingMap, key, "zhyl6200021", zhyl6100062Json, null);
                                    transJson(guangjingMap, key, "zhyl6200022", zhyl6100062Json, null);

                                }
                                zhyl6100055Json.put("zhyl6100061", zhyl6100061Json);
                                zhyl6100055Json.put("zhyl6100062", zhyl6100062Json);
                                zhyl6100055JSONArray.add(zhyl6100055Json);
                            }
                            zhyl60000000JSONObject.put("zhyl6100055", zhyl6100055JSONArray);

                        }
                    }

                    JSONArray zhyl6100063JSONArray = new JSONArray(); //电镜
                    String dianjing2Sql = dianjing2.replace("?", s);
                    List<Map<String, Object>> dianjing2SqlSqlList = commonExecute2(connection, dianjing2Sql, statement, resultSet);
                    if (dianjing2SqlSqlList != null && dianjing2SqlSqlList.size() > 0) {
                        for (Map<String, Object> dianjingMap : dianjing2SqlSqlList) {
                            if (dianjingMap != null && dianjingMap.size() > 0) {
                                JSONObject zhyl6100063Json = new JSONObject();
                                JSONObject zhyl6100069Json = new JSONObject();

                                for (String key : dianjingMap.keySet()) {
                                    transJson(dianjingMap, key, "zhyl6100064", zhyl6100063Json, null);
                                    transJson(dianjingMap, key, "zhyl6100065", zhyl6100063Json, null);
                                    transJson(dianjingMap, key, "zhyl6100066", zhyl6100063Json, null);
                                    transJson(dianjingMap, key, "zhyl6100067", zhyl6100063Json, null);
                                    transJson(dianjingMap, key, "zhyl6100068", zhyl6100063Json, null);

                                    transJson(dianjingMap, key, "zhyl6200023", zhyl6100069Json, null);
                                    transJson(dianjingMap, key, "zhyl6200024", zhyl6100069Json, null);
                                    transJson(dianjingMap, key, "zhyl6200025", zhyl6100069Json, null);
                                    transJson(dianjingMap, key, "zhyl6200026", zhyl6100069Json, null);
                                    transJson(dianjingMap, key, "zhyl6200027", zhyl6100069Json, null);
                                    transJson(dianjingMap, key, "zhyl6200028", zhyl6100069Json, null);

                                }
                                zhyl6100063Json.put("zhyl6100069", zhyl6100069Json);
                                zhyl6100063JSONArray.add(zhyl6100063Json);
                            }
                            zhyl60000000JSONObject.put("zhyl6100063", zhyl6100063JSONArray);
                        }
                    }

                    JSONArray zhyl6000016Json = new JSONArray(); //纯音测听
                    commonjiancha(s, zhyl6000016Json, chunyinceting2, zhyl60000000JSONObject, "zhyl6000016", connection, statement, resultSet);

                    JSONArray zhyl6000017Json = new JSONArray(); //眼裂隙灯检查
                    commonjiancha(s, zhyl6000017Json, yanliedeng2, zhyl60000000JSONObject, "zhyl6000017", connection, statement, resultSet);

                    JSONArray zhyl6000018Json = new JSONArray(); //其他检查
                    commonjiancha(s, zhyl6000018Json, qitajiancha2, zhyl60000000JSONObject, "zhyl6000018", connection, statement, resultSet);

                    JSONArray zhyl6000019Json = new JSONArray(); //基因检测
                    String jiyinjiance2Sql = jiyinjiance2.replace("?", s);
                    List<Map<String, Object>> jiyinjiance2List = commonExecute2(connection, jiyinjiance2Sql, statement, resultSet);
                    if (jiyinjiance2List != null && jiyinjiance2List.size() > 0) {
                        for (Map<String, Object> jiyinMap : jiyinjiance2List) {
                            if (jiyinMap != null && jiyinMap.size() > 0) {
                                JSONObject zhyl6000019JSONObject = new JSONObject();
                                JSONObject zhyl6100097JSONObject = new JSONObject();

                                for (String key : jiyinMap.keySet()) {
                                    transJson(jiyinMap, key, "zhyl6100086", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100087", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100088", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100089", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100090", zhyl6000019JSONObject, "list");
                                    transJson(jiyinMap, key, "zhyl6100091", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100092", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100093", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100094", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100095", zhyl6000019JSONObject, "list");
                                    transJson(jiyinMap, key, "zhyl6100096", zhyl6000019JSONObject, null);

                                    transJson(jiyinMap, key, "zhyl6200029", zhyl6100097JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6200030", zhyl6100097JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6200031", zhyl6100097JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6200032", zhyl6100097JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6200033", zhyl6100097JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6200034", zhyl6100097JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6200035", zhyl6100097JSONObject, null);

                                }
                                zhyl6000019JSONObject.put("zhyl6100097", zhyl6100097JSONObject);
                                zhyl6000019Json.add(zhyl6000019JSONObject);
                            }

                        }
                        zhyl60000000JSONObject.put("zhyl6000019", zhyl6000019Json);
                    }


                    JSONArray zhyl6000020Json = new JSONArray(); //最终诊断
                    commonjiancha(s, zhyl6000020Json, zuizhongzhenduan2, zhyl60000000JSONObject, "zhyl6000020", connection, statement, resultSet);


                    JSONArray zhyl6000021JSONArray = new JSONArray(); //血常规
                    //   commonjiancha(s, zhyl6000021Json, xuechanggui2, zhyl60000000JSONObject, "zhyl6000021", connection, statement, resultSet);
                    String xuechanggui2Sql = xuechanggui2.replace("?", s);
                    List<Map<String, Object>> xuechanggui2SqlList = commonExecute2(connection, xuechanggui2Sql, statement, resultSet);
                    if (xuechanggui2SqlList != null && xuechanggui2SqlList.size() > 0) {
                        for (Map<String, Object> xuechanggui : xuechanggui2SqlList) {
                            if (xuechanggui != null && xuechanggui.size() > 0) {
                                JSONObject zhyl6000021JSONObject = new JSONObject();
                                JSONObject zhyl60000213JSONObject = new JSONObject();

                                for (String key : xuechanggui.keySet()) {
                                    transJson(xuechanggui, key, "zhyl60000211", zhyl6000021JSONObject, null);
                                    transJson(xuechanggui, key, "zhyl60000212", zhyl6000021JSONObject, null);
                                    transJson(xuechanggui, key, "zhyl600002131", zhyl60000213JSONObject, null);
                                    transJson(xuechanggui, key, "zhyl600002132", zhyl60000213JSONObject, null);
                                }
                                zhyl6000021JSONObject.put("zhyl60000213", zhyl60000213JSONObject);
                                zhyl6000021JSONArray.add(zhyl6000021JSONObject);
                            }
                        }
                        zhyl60000000JSONObject.put("zhyl6000021", zhyl6000021JSONArray);
                    }

                    JSONArray zhyl6000022JSONArray = new JSONArray(); //尿钙/肌酐比
                    // commonjiancha(s, zhyl6000022Json, niaogaijigan2, zhyl60000000JSONObject, "zhyl6000022", connection, statement, resultSet);
                    String niaogaijigan2Sql = niaogaijigan2.replace("?", s);
                    List<Map<String, Object>> niaogaijigan2SqlList = commonExecute2(connection, niaogaijigan2Sql, statement, resultSet);
                    if (niaogaijigan2SqlList != null && niaogaijigan2SqlList.size() > 0) {
                        for (Map<String, Object> niaogaijigan : niaogaijigan2SqlList) {
                            if (niaogaijigan != null && niaogaijigan.size() > 0) {
                                JSONObject zhyl6000022JSONObject = new JSONObject();
                                JSONObject zhyl60000223JSONObject = new JSONObject();

                                for (String key : niaogaijigan.keySet()) {
                                    transJson(niaogaijigan, key, "zhyl60000221", zhyl6000022JSONObject, null);
                                    transJson(niaogaijigan, key, "zhyl60000222", zhyl6000022JSONObject, null);
                                    transJson(niaogaijigan, key, "zhyl600002231", zhyl60000223JSONObject, null);
                                    transJson(niaogaijigan, key, "zhyl600002232", zhyl60000223JSONObject, null);
                                }
                                zhyl6000022JSONObject.put("zhyl60000223", zhyl60000223JSONObject);
                                zhyl6000022JSONArray.add(zhyl6000022JSONObject);
                            }
                        }
                        zhyl60000000JSONObject.put("zhyl6000022", zhyl6000022JSONArray);
                    }

                    JSONArray zhyl6000023Json = new JSONArray(); //24小时尿电解质
                    String niaodianjiezhi24h42Sql = niaodianjiezhi24h2.replace("?", s);
                    List<Map<String, Object>> niaodianjiezhi24h42SqlList = commonExecute2(connection, niaodianjiezhi24h42Sql, statement, resultSet);
                    if (niaodianjiezhi24h42SqlList != null) {
                        for (Map<String, Object> map1 : niaodianjiezhi24h42SqlList) {
                            if (map1 != null && map1.size() > 0) {
                                JSONObject zhyl6000023Object = new JSONObject();
                                JSONObject zhyl60000233Object = new JSONObject();
                                JSONObject zhyl60000235Object = new JSONObject();

                                List<String> nameList = new ArrayList<>();
                                List<String> valueList = new ArrayList<>();
                                List<String> refList = new ArrayList<>();
                                for (String key : map1.keySet()) {
                                    if (key.toUpperCase().equals("HETERM") && map1.get(key).toString().contains(",")) {
                                        nameList = Arrays.asList(map1.get(key).toString().split(","));
                                    }
                                    if (key.toUpperCase().equals("VALUE") && map1.get(key).toString().contains(",")) {
                                        valueList = Arrays.asList(map1.get(key).toString().split(","));
                                    }
                                    if (key.toUpperCase().equals("HESREF") && map1.get(key).toString().contains(",")) {
                                        refList = Arrays.asList(map1.get(key).toString().split(","));
                                    }
                                    if (key.toUpperCase().equals("HEHOSP")) {//检查医院
                                        zhyl6000023Object.put("zhyl60000232", map1.get(key));
                                    }
                                    if (key.equals("zhyl60000231")) {//检查日期
                                        zhyl6000023Object.put("zhyl60000231", map1.get(key));
                                    }
                                }
                                if (nameList.size() > 0) {
                                    for (int i = 0; i < nameList.size(); i++) {
                                        if (nameList.get(i).contains("24h尿钙") && !nameList.get(i).contains("24h尿钙体重比")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl60000233Object.put("zhyl600002331", valueList.get(i));
                                            } else {
                                                zhyl60000233Object.put("zhyl600002331", null);
                                            }
                                            if (!refList.get(i).contains("#") && refList.get(i) != null) {
                                                zhyl60000233Object.put("zhyl600002332", refList.get(i));
                                            } else {
                                                zhyl60000233Object.put("zhyl600002332", null);
                                            }
                                            zhyl6000023Object.put("zhyl60000233", zhyl60000233Object);

                                        }
                                        if (nameList.get(i).contains("体重")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000023Object.put("zhyl60000234", valueList.get(i));
                                            } else {
                                                zhyl6000023Object.put("zhyl60000234", null);
                                            }
                                        }

                                        if (nameList.get(i).contains("24h尿钙体重比")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl60000235Object.put("zhyl600002351", valueList.get(i));
                                            } else {
                                                zhyl60000235Object.put("zhyl600002351", null);
                                            }
                                            if (!refList.get(i).contains("#") && refList.get(i) != null) {
                                                zhyl60000235Object.put("zhyl600002352", refList.get(i));
                                            } else {
                                                zhyl60000235Object.put("zhyl600002352", null);
                                            }
                                            zhyl6000023Object.put("zhyl60000235", zhyl60000235Object);
                                        }
                                    }
                                }
                                zhyl6000023Json.add(zhyl6000023Object);
                            }
                        }
                        zhyl60000000JSONObject.put("zhyl6000023", zhyl6000023Json);
                    }


                    JSONArray zhyl6000024Json = new JSONArray(); //肾脏影像学检查
                    commonjiancha(s, zhyl6000024Json, shenzangyingxiang2, zhyl60000000JSONObject, "zhyl6000024", connection, statement, resultSet);

                    JSONArray zhyl6000025Json = new JSONArray(); //肾动态检查
                    commonjiancha(s, zhyl6000025Json, shendongtai2, zhyl60000000JSONObject, "zhyl6000025", connection, statement, resultSet);

                    JSONArray zhyl6000026Json = new JSONArray(); //肝、胆影像学检查
                    commonjiancha(s, zhyl6000026Json, gandanyingxiang2, zhyl60000000JSONObject, "zhyl6000026", connection, statement, resultSet);

                    JSONArray zhyl6000027Json = new JSONArray(); //肝活检
                    commonjiancha(s, zhyl6000027Json, ganhuojian2, zhyl60000000JSONObject, "zhyl6000027", connection, statement, resultSet);

                    JSONArray zhyl6000028Json = new JSONArray(); //眼科检查
                    commonjiancha(s, zhyl6000028Json, yankejiancha2, zhyl60000000JSONObject, "zhyl6000028", connection, statement, resultSet);

                    JSONArray zhyl6000029JSONArray = new JSONArray(); //肾活检-肾组织IV胶原染色
                    String shenhuojianranse = shenhuojianranse2.replace("?", s);
                    List<Map<String, Object>> shenhuojianranse2List = commonExecute2(connection, shenhuojianranse, statement, resultSet);
                    if (shenhuojianranse2List != null && shenhuojianranse2List.size() > 0) {
                        for (Map<String, Object> shenhuojianransemap : shenhuojianranse2List) {
                            if (shenhuojianransemap != null && shenhuojianransemap.size() > 0) {
                                JSONObject zhyl6000029JSONObject = new JSONObject();
                                JSONObject zhyl60000297Json = new JSONObject();
                                for (String key : shenhuojianransemap.keySet()) {
                                    transJson(shenhuojianransemap, key, "zhyl60000291", zhyl6000029JSONObject, null);
                                    transJson(shenhuojianransemap, key, "zhyl60000292", zhyl6000029JSONObject, null);
                                    transJson(shenhuojianransemap, key, "zhyl60000293", zhyl6000029JSONObject, null);
                                    transJson(shenhuojianransemap, key, "zhyl60000294", zhyl6000029JSONObject, null);
                                    transJson(shenhuojianransemap, key, "zhyl60000295", zhyl6000029JSONObject, null);
                                    transJson(shenhuojianransemap, key, "zhyl60000296", zhyl6000029JSONObject, null);


                                    transJson(shenhuojianransemap, key, "zhyl600002971", zhyl60000297Json, null);
                                    transJson(shenhuojianransemap, key, "zhyl600002972", zhyl60000297Json, null);
                                    transJson(shenhuojianransemap, key, "zhyl600002974", zhyl60000297Json, null);
                                    transJson(shenhuojianransemap, key, "zhyl600002976", zhyl60000297Json, null);
                                    transJson(shenhuojianransemap, key, "zhyl600002978", zhyl60000297Json, null);
                                    transJson(shenhuojianransemap, key, "zhyl600002980", zhyl60000297Json, null);
                                    transJson(shenhuojianransemap, key, "zhyl600002982", zhyl60000297Json, null);


                                    if (key.equals("zhyl600002973")) {
                                        transformQiangdu(zhyl60000297Json, shenhuojianransemap.get(key), "zhyl600002973");
                                    }
                                    if (key.equals("zhyl600002975")) {
                                        transformQiangdu(zhyl60000297Json, shenhuojianransemap.get(key), "zhyl600002975");
                                    }
                                    if (key.equals("zhyl600002977")) {
                                        transformQiangdu(zhyl60000297Json, shenhuojianransemap.get(key), "zhyl600002977");
                                    }
                                    if (key.equals("zhyl600002979")) {
                                        transformQiangdu(zhyl60000297Json, shenhuojianransemap.get(key), "zhyl600002979");
                                    }
                                    if (key.equals("zhyl600002981")) {
                                        transformQiangdu(zhyl60000297Json, shenhuojianransemap.get(key), "zhyl600002981");
                                    }
                                    if (key.equals("zhyl600002983")) {
                                        transformQiangdu(zhyl60000297Json, shenhuojianransemap.get(key), "zhyl600002983");
                                    }

                                }
                                zhyl6000029JSONObject.put("zhyl60000297", zhyl60000297Json);
                                zhyl6000029JSONArray.add(zhyl6000029JSONObject);
                            }
                            zhyl60000000JSONObject.put("zhyl6000029", zhyl6000029JSONArray);

                        }
                    }

                    JSONArray zhyl6000032JSONArray = new JSONArray(); //皮肤活检
                    String pifihuojian = pifihuojian2.replace("?", s);
                    List<Map<String, Object>> pifihuojianList = commonExecute2(connection, pifihuojian, statement, resultSet);
                    if (pifihuojianList != null && pifihuojianList.size() > 0) {
                        for (Map<String, Object> pifuhuojianmap : pifihuojianList) {
                            if (pifuhuojianmap != null && pifuhuojianmap.size() > 0) {
                                JSONObject zhyl6000032JSONObject = new JSONObject();
                                for (String key : pifuhuojianmap.keySet()) {
                                    transJson(pifuhuojianmap, key, "zhyl60000321", zhyl6000032JSONObject, null);
                                    transJson(pifuhuojianmap, key, "zhyl60000322", zhyl6000032JSONObject, null);
                                    transJson(pifuhuojianmap, key, "zhyl60000323", zhyl6000032JSONObject, null);
                                    transJson(pifuhuojianmap, key, "zhyl60000324", zhyl6000032JSONObject, null);
                                    transJson(pifuhuojianmap, key, "zhyl60000325", zhyl6000032JSONObject, null);
                                    transJson(pifuhuojianmap, key, "zhyl60000326", zhyl6000032JSONObject, null);
                                    transJson(pifuhuojianmap, key, "zhyl60000327", zhyl6000032JSONObject, null);
                                    transJson(pifuhuojianmap, key, "zhyl60000329", zhyl6000032JSONObject, null);

                                    if (key.equals("zhyl60000328")) {
                                        transformQiangdu(zhyl6000032JSONObject, pifuhuojianmap.get(key), "zhyl60000328");
                                    }
                                    if (key.equals("zhyl60000330")) {
                                        transformQiangdu(zhyl6000032JSONObject, pifuhuojianmap.get(key), "zhyl60000330");
                                    }
                                }
                                zhyl6000032JSONArray.add(zhyl6000032JSONObject);
                            }
                            zhyl60000000JSONObject.put("zhyl6000032", zhyl6000032JSONArray);

                        }
                    }


                    JSONArray zhyl6000033JSONArray = new JSONArray(); //酸中毒时尿PH
                    String suanzhongdu2sql = suanzhongdu2.replace("?", s);
                    List<Map<String, Object>> suanzhongdu2List = commonExecute2(connection, suanzhongdu2sql, statement, resultSet);
                    if (suanzhongdu2List != null && suanzhongdu2List.size() > 0) {
                        for (Map<String, Object> suanzhongdu : suanzhongdu2List) {
                            if (suanzhongdu != null && suanzhongdu.size() > 0) {
                                JSONObject zhyl6000033JSONObject = new JSONObject();
                                JSONObject zhyl60000334JSONObject = new JSONObject();
                                for (String key : suanzhongdu.keySet()) {
                                    transJson(suanzhongdu, key, "zhyl60000331", zhyl6000033JSONObject, null);
                                    transJson(suanzhongdu, key, "zhyl60000332", zhyl6000033JSONObject, null);
                                    transJson(suanzhongdu, key, "zhyl60000333", zhyl6000033JSONObject, null);
                                    transJson(suanzhongdu, key, "zhyl600003341", zhyl60000334JSONObject, null);
                                    transJson(suanzhongdu, key, "zhyl600003342", zhyl60000334JSONObject, null);
                                }
                                zhyl6000033JSONObject.put("zhyl60000334", zhyl60000334JSONObject);
                                zhyl6000033JSONArray.add(zhyl6000033JSONObject);
                            }
                            zhyl60000000JSONObject.put("zhyl6000033", zhyl6000033JSONArray);

                        }
                    }

                    JSONArray zhyl6000034JSONArray = new JSONArray(); //尿氨基酸分析
                    commonjiancha(s, zhyl6000034JSONArray, niaoanjisuan2, zhyl60000000JSONObject, "zhyl6000034", connection, statement, resultSet);


                    JSONArray zhyl6000035JSONArray = new JSONArray(); //尿酸化试验
                    String niaosuanhuashiyan2sql = niaosuanhuashiyan2.replace("?", s);
                    List<Map<String, Object>> niaosuanhuashiyan2List = commonExecute2(connection, niaosuanhuashiyan2sql, statement, resultSet);
                    if (niaosuanhuashiyan2List != null && niaosuanhuashiyan2List.size() > 0) {
                        for (Map<String, Object> niaosuanhuashiyan : niaosuanhuashiyan2List) {
                            if (niaosuanhuashiyan != null && niaosuanhuashiyan.size() > 0) {
                                JSONObject zhyl6000035JSONObject = new JSONObject();
                                JSONObject zhyl60000353JSONObject = new JSONObject();
                                JSONObject zhyl60000354JSONObject = new JSONObject();
                                JSONObject zhyl60000355JSONObject = new JSONObject();
                                JSONObject zhyl60000356JSONObject = new JSONObject();
                                for (String key : niaosuanhuashiyan.keySet()) {
                                    transJson(niaosuanhuashiyan, key, "zhyl60000351", zhyl6000035JSONObject, null);
                                    transJson(niaosuanhuashiyan, key, "zhyl60000352", zhyl6000035JSONObject, null);
                                    transJson(niaosuanhuashiyan, key, "zhyl600003531", zhyl60000353JSONObject, null);
                                    transJson(niaosuanhuashiyan, key, "zhyl600003532", zhyl60000353JSONObject, null);
                                    transJson(niaosuanhuashiyan, key, "zhyl600003541", zhyl60000354JSONObject, null);
                                    transJson(niaosuanhuashiyan, key, "zhyl600003542", zhyl60000354JSONObject, null);
                                    transJson(niaosuanhuashiyan, key, "zhyl600003551", zhyl60000355JSONObject, null);
                                    transJson(niaosuanhuashiyan, key, "zhyl600003552", zhyl60000355JSONObject, null);
                                    transJson(niaosuanhuashiyan, key, "zhyl600003561", zhyl60000356JSONObject, null);
                                    transJson(niaosuanhuashiyan, key, "zhyl600003562", zhyl60000356JSONObject, null);
                                }
                                zhyl6000035JSONObject.put("zhyl60000353", zhyl60000353JSONObject);
                                zhyl6000035JSONObject.put("zhyl60000354", zhyl60000354JSONObject);
                                zhyl6000035JSONObject.put("zhyl60000355", zhyl60000355JSONObject);
                                zhyl6000035JSONObject.put("zhyl60000356", zhyl60000356JSONObject);
                                zhyl6000035JSONArray.add(zhyl6000035JSONObject);
                            }
                            zhyl60000000JSONObject.put("zhyl6000035", zhyl6000035JSONArray);

                        }
                    }

                    JSONArray zhyl6000036JSONArray = new JSONArray(); //血氨基酸测定
                    commonjiancha(s, zhyl6000036JSONArray, xueanjisuan2, zhyl60000000JSONObject, "zhyl6000036", connection, statement, resultSet);

                    JSONArray zhyl6000037JSONArray = new JSONArray(); //尿有机酸测定
                    commonjiancha(s, zhyl6000037JSONArray, niaoyoujisuan2, zhyl60000000JSONObject, "zhyl6000037", connection, statement, resultSet);

                    JSONArray zhyl6000038JSONArray = new JSONArray(); //肾钙化/肾结石
                    commonjiancha(s, zhyl6000038JSONArray, shengaihua2, zhyl60000000JSONObject, "zhyl6000038", connection, statement, resultSet);

                    JSONArray zhyl6000039JSONArray = new JSONArray(); //骨骼X线片
                    String gugex2Sql = gugex2.replace("?", s);
                    List<Map<String, Object>> gugex2SqlList = commonExecute2(connection, gugex2Sql, statement, resultSet);
                    if (gugex2SqlList != null && gugex2SqlList.size() > 0) {
                        for (Map<String, Object> gugex2 : gugex2SqlList) {
                            if (gugex2 != null && gugex2.size() > 0) {
                                JSONObject zhyl6000039JSONObject = new JSONObject();
                                for (String key : gugex2.keySet()) {
                                    transJson(gugex2, key, "zhyl60000391", zhyl6000039JSONObject, null);
                                    transJson(gugex2, key, "zhyl60000392", zhyl6000039JSONObject, null);
                                    transJson(gugex2, key, "zhyl60000393", zhyl6000039JSONObject, null);
                                    transJson(gugex2, key, "zhyl60000394", zhyl6000039JSONObject, "list");
                                    transJson(gugex2, key, "zhyl60000395", zhyl6000039JSONObject, null);
                                    transJson(gugex2, key, "zhyl60000396", zhyl6000039JSONObject, null);
                                }
                                zhyl6000039JSONArray.add(zhyl6000039JSONObject);
                            }
                        }
                        zhyl60000000JSONObject.put("zhyl6000039", zhyl6000039JSONArray);
                    }

                    JSONArray zhyl6000040JSONArray = new JSONArray(); //血甲状旁腺素
                    String xuejiazhuangpang2Sql = xuejiazhuangpang2.replace("?", s);
                    List<Map<String, Object>> xuejiazhuangpang2lList = commonExecute2(connection, xuejiazhuangpang2Sql, statement, resultSet);
                    if (xuejiazhuangpang2lList != null && xuejiazhuangpang2lList.size() > 0) {
                        for (Map<String, Object> xuejiazhuangpang : xuejiazhuangpang2lList) {
                            if (xuejiazhuangpang != null && xuejiazhuangpang.size() > 0) {
                                JSONObject zhyl6000040JSONObject = new JSONObject();
                                JSONObject zhyl60000403JSONObject = new JSONObject();
                                for (String key : xuejiazhuangpang.keySet()) {
                                    transJson(xuejiazhuangpang, key, "zhyl60000401", zhyl6000040JSONObject, null);
                                    transJson(xuejiazhuangpang, key, "zhyl60000402", zhyl6000040JSONObject, null);
                                    transJson(xuejiazhuangpang, key, "zhyl600004031", zhyl60000403JSONObject, null);
                                    transJson(xuejiazhuangpang, key, "zhyl600004032", zhyl60000403JSONObject, null);
                                }
                                zhyl6000040JSONObject.put("zhyl60000403", zhyl60000403JSONObject);
                                zhyl6000039JSONArray.add(zhyl6000040JSONObject);
                            }
                        }
                        zhyl60000000JSONObject.put("zhyl6000040", zhyl6000040JSONArray);
                    }

                    JSONArray zhyl6000041JSONArray = new JSONArray(); //泌尿系统影像学检查
                    commonjiancha(s, zhyl6000041JSONArray, jingmaishenyuzaoying2, zhyl60000000JSONObject, "zhyl6000041", connection, statement, resultSet);

                    JSONArray zhyl6000042JSONArray = new JSONArray(); //静脉肾盂造影
                    commonjiancha(s, zhyl6000042JSONArray, miniaoxitongyingxiang2, zhyl60000000JSONObject, "zhyl6000042", connection, statement, resultSet);

                    JSONArray zhyl6000043JSONArray = new JSONArray(); //排泄性膀胱尿路造影
                    commonjiancha(s, zhyl6000043JSONArray, paixiexingpangguang2, zhyl60000000JSONObject, "zhyl6000043", connection, statement, resultSet);

//----------------

                    zhyl6000000JSONObject.put("zhyl60000000", zhyl60000000JSONObject);
                    jsonObject.put("zhyl6000000", zhyl6000000JSONObject);

//-----------------------------------------用药----------------------------------------------
                    JSONArray zhyl7000000Json = new JSONArray(); //用药

                    String yongyao2Sql = yongyao2.replace("?", s);
                    List<Map<String, Object>> yongyao2SqlList = commonExecute2(connection, yongyao2Sql, statement, resultSet);
                    if (yongyao2SqlList != null && yongyao2SqlList.size() > 0) {
                        for (Map<String, Object> yongyao2Map : yongyao2SqlList) {
                            if (yongyao2Map != null && yongyao2Map.size() > 0) {
                                JSONObject zhyl7100000JSONObject = new JSONObject();
                                for (String key : yongyao2Map.keySet()) {
                                    transJson(yongyao2Map, key, "zhyl7000002", zhyl7100000JSONObject, null);
                                    transJson(yongyao2Map, key, "zhyl7000003", zhyl7100000JSONObject, null);
                                    transJson(yongyao2Map, key, "zhyl7000004", zhyl7100000JSONObject, null);
                                }
                                yongyao2Map.remove("zhyl7000002");
                                yongyao2Map.remove("zhyl7000003");
                                yongyao2Map.remove("zhyl7000004");

                                yongyao2Map.put("zhyl7100000", zhyl7100000JSONObject);
                                zhyl7000000Json.add(yongyao2Map);
                            }
                        }
                        jsonObject.put("zhyl7000000", zhyl7000000Json);
                    }


//---------------------------------------随访有多个数据的，现在全部只取一个------------------------------------------------
                    JSONObject zhyl8000000Json = new JSONObject();
                    JSONArray zhyl80000000JSONArray = new JSONArray(); //总随访

                    String suifangIdSql = suifang2.replace("?", s);
                    List<String> suifangIdList = commonExecute(connection, statement, resultSet, suifangIdSql);//每个dmid对应多个随访id
                    if (suifangIdList != null) {

                        for (String suifang : suifangIdList) {  //每个随访id

                            JSONObject zhyl80000001Json = new JSONObject();//对于每次随访
                            //--------随访时间----------
                            String suifangtimeSql = suifangtime.replace("?", suifang);
                            List<String> suifangtimeList = commonExecute(connection, statement, resultSet, suifangtimeSql);//
                            String suifangtimeSql1 = suifangtime1.replace("?", suifang);
                            List<String> suifangtimeList1 = commonExecute(connection, statement, resultSet, suifangtimeSql1);//
                            String suifangtime = null;
                            if (suifangtimeList1 != null && suifangtimeList1.size() > 0) {
                                suifangtime = suifangtimeList1.get(0);
                            }
                            if (suifangtimeList != null && suifangtimeList.size() > 0) {
                                zhyl80000001Json.put("zhyl8000009", suifangtimeList.get(0));
                            }

                            //--------体格检查 每次随访可能有多个,由列表改为单个---------
                            String suifangtigejiancha2Sql = suifangtigejiancha2.replace("?", suifang);
                            List<Map<String, Object>> suifangtigejiancha2SqlList = commonExecute2(connection, suifangtigejiancha2Sql, statement, resultSet);//有多条
                            if (suifangtigejiancha2SqlList != null && suifangtigejiancha2SqlList.size() > 0) {
                                for (Map<String, Object> yaoWuMap : suifangtigejiancha2SqlList) {
                                    if (yaoWuMap != null && yaoWuMap.size() > 0) {
                                        JSONObject zhyl8100003Json = new JSONObject();
                                        for (String key : yaoWuMap.keySet()) {
                                            transJson(yaoWuMap, key, "zhyl8200001", zhyl8100003Json, null);
                                            transJson(yaoWuMap, key, "zhyl8200002", zhyl8100003Json, null);
                                        }
                                        yaoWuMap.remove("zhyl8200001");
                                        yaoWuMap.remove("zhyl8200002");
                                        yaoWuMap.put("zhyl8100003", zhyl8100003Json);
                                        zhyl80000001Json.put("zhyl8000008", yaoWuMap);
                                    }
                                }

                            }

                            //-------尿常规 每次随访可能有多个---------
                            String suifangniaochangguiSql = suifangniaochanggui2.replace("?", suifang);
                            List<Map<String, Object>> suifangniaochangguiSqlList = commonExecute2(connection, suifangniaochangguiSql, statement, resultSet);
                            if (suifangniaochangguiSqlList != null && suifangniaochangguiSqlList.size() > 0) {
                                Map<String, Object> suifangniaochanggui = suifangniaochangguiSqlList.get(0);
                                if (suifangniaochanggui != null && suifangniaochanggui.size() > 0) {
                                    JSONObject zhyl8000001SONObject = new JSONObject();
                                    JSONObject zhyl800000105JSONObject = new JSONObject();
                                    JSONObject zhyl800000106JSONObject = new JSONObject();
                                    JSONObject zhyl800000108JSONObject = new JSONObject();
                                    JSONObject zhyl800000109JSONObject = new JSONObject();
                                    JSONObject zhyl800000111JSONObject = new JSONObject();

                                    for (String key : suifangniaochanggui.keySet()) {
                                        transJson(suifangniaochanggui, key, "zhyl800000102", zhyl8000001SONObject, null);
                                        transJson(suifangniaochanggui, key, "zhyl800000104", zhyl8000001SONObject, null);

                                        transJson(suifangniaochanggui, key, "zhyl8000001051", zhyl800000105JSONObject, null);
                                        transJson(suifangniaochanggui, key, "zhyl8000001052", zhyl800000105JSONObject, null);

                                        transJson(suifangniaochanggui, key, "zhyl8000001061", zhyl800000106JSONObject, null);
                                        transJson(suifangniaochanggui, key, "zhyl8000001062", zhyl800000106JSONObject, null);
                                        transJson(suifangniaochanggui, key, "zhyl8000001063", zhyl800000106JSONObject, null);
                                        transJson(suifangniaochanggui, key, "zhyl8000001064", zhyl800000106JSONObject, null);

                                        transJson(suifangniaochanggui, key, "zhyl8000001081", zhyl800000108JSONObject, null);
                                        transJson(suifangniaochanggui, key, "zhyl8000001082", zhyl800000108JSONObject, null);

                                        transJson(suifangniaochanggui, key, "zhyl8000001091", zhyl800000109JSONObject, null);
                                        transJson(suifangniaochanggui, key, "zhyl8000001092", zhyl800000109JSONObject, null);

                                        transJson(suifangniaochanggui, key, "zhyl8000001111", zhyl800000111JSONObject, null);
                                        transJson(suifangniaochanggui, key, "zhyl8000001112", zhyl800000111JSONObject, null);

                                    }
                                    zhyl8000001SONObject.put("zhyl800000105", zhyl800000105JSONObject);
                                    zhyl8000001SONObject.put("zhyl800000106", zhyl800000106JSONObject);
                                    zhyl8000001SONObject.put("zhyl600000108", zhyl800000108JSONObject);
                                    zhyl8000001SONObject.put("zhyl600000109", zhyl800000109JSONObject);
                                    zhyl8000001SONObject.put("zhyl600000111", zhyl800000111JSONObject);


                                    zhyl80000001Json.put("zhyl8000001", zhyl8000001SONObject);
                                }

                            }


                            //-------尿蛋白/肌酐 每次随访可能有多个---------
                            String suifangniaodanbaijigan2Sql = suifangniaodanbaijigan2.replace("?", suifang);
                            List<Map<String, Object>> suifangniaodanbaijigan2SqlList = commonExecute2(connection, suifangniaodanbaijigan2Sql, statement, resultSet);
                            if (suifangniaodanbaijigan2SqlList != null && suifangniaodanbaijigan2SqlList.size() > 0) {
                                Map<String, Object> suifangniaodanbaijigan = suifangniaodanbaijigan2SqlList.get(0);
                                if (suifangniaodanbaijigan != null && suifangniaodanbaijigan.size() > 0) {
                                    JSONObject zhyl8000002JSONObject = new JSONObject();
                                    JSONObject zhyl800000206JSONObject = new JSONObject();

                                    for (String key : suifangniaodanbaijigan.keySet()) {
                                        transJson(suifangniaodanbaijigan, key, "zhyl800000202", zhyl8000002JSONObject, null);
                                        transJson(suifangniaodanbaijigan, key, "zhyl800000204", zhyl8000002JSONObject, null);
                                        transJson(suifangniaodanbaijigan, key, "zhyl800000205", zhyl8000002JSONObject, null);

                                        transJson(suifangniaodanbaijigan, key, "zhyl8000002061", zhyl800000206JSONObject, null);
                                        transJson(suifangniaodanbaijigan, key, "zhyl8000002062", zhyl800000206JSONObject, null);
                                    }
                                    zhyl8000002JSONObject.put("zhyl800000206", zhyl800000206JSONObject);
                                    zhyl80000001Json.put("zhyl8000002", zhyl8000002JSONObject);
                                }
                            }

                            //-------24小时尿蛋白定量 每次随访可能有多个---------

                            String suifang24niaodanbai22Sql = suifang24niaodanbai2.replace("?", suifang);
                            List<Map<String, Object>> suifang24niaodanbai22SqlList = commonExecute2(connection, suifang24niaodanbai22Sql, statement, resultSet);
                            if (suifang24niaodanbai22SqlList != null && suifang24niaodanbai22SqlList.size() > 0) {
                                Map<String, Object> suifang24niaodanbai = suifang24niaodanbai22SqlList.get(0);
                                if (suifang24niaodanbai != null && suifang24niaodanbai.size() > 0) {
                                    JSONObject zhyl8000003JSONObject = new JSONObject();
                                    JSONObject zhyl800000304JSONObject = new JSONObject();
                                    JSONObject zhyl800000306JSONObject = new JSONObject();

                                    for (String key : suifang24niaodanbai.keySet()) {
                                        transJson(suifang24niaodanbai, key, "zhyl800000302", zhyl8000003JSONObject, null);
                                        transJson(suifang24niaodanbai, key, "zhyl800000305", zhyl8000003JSONObject, null);
                                        transJson(suifang24niaodanbai, key, "zhyl8000003041", zhyl800000304JSONObject, null);
                                        transJson(suifang24niaodanbai, key, "zhyl8000003042", zhyl800000304JSONObject, null);
                                        transJson(suifang24niaodanbai, key, "zhyl8000003061", zhyl800000306JSONObject, null);
                                        transJson(suifang24niaodanbai, key, "zhyl8000003062", zhyl800000306JSONObject, null);

                                    }
                                    zhyl8000003JSONObject.put("zhyl800000304", zhyl800000304JSONObject);
                                    zhyl8000003JSONObject.put("zhyl800000306", zhyl800000306JSONObject);
                                    zhyl80000001Json.put("zhyl8000003", zhyl8000003JSONObject);
                                }
                            }


                            //-------24h肌酐清除率 每次随访可能有多个---------
                            String suifang24jiganqingchu2Sql = suifang24jiganqingchu2.replace("?", suifang);
                            List<Map<String, Object>> suifang24jiganqingchu2SqlList = commonExecute2(connection, suifang24jiganqingchu2Sql, statement, resultSet);
                            if (suifang24jiganqingchu2SqlList != null && suifang24jiganqingchu2SqlList.size() > 0) {
                                Map<String, Object> map1 = suifang24jiganqingchu2SqlList.get(0);
                                if (map1 != null && map1.size() > 0) {
                                    JSONObject zhyl8000004JsonObject = new JSONObject();
                                    JSONObject zhyl800000404JsonObject = new JSONObject();
                                    List<String> nameList = new ArrayList<>();
                                    List<String> valueList = new ArrayList<>();
                                    List<String> refList = new ArrayList<>();
                                    for (String key : map1.keySet()) {
                                        if (key.toUpperCase().equals("HETERM") && map1.get(key).toString().contains(",")) {
                                            nameList = Arrays.asList(map1.get(key).toString().split(","));
                                        }
                                        if (key.toUpperCase().equals("VALUE") && map1.get(key).toString().contains(",")) {
                                            valueList = Arrays.asList(map1.get(key).toString().split(","));
                                        }
                                        if (key.toUpperCase().equals("HESREF") && map1.get(key).toString().contains(",")) {
                                            refList = Arrays.asList(map1.get(key).toString().split(","));
                                        }
                                        if (key.toUpperCase().equals("HEHOSP")) {//检查医院
                                            zhyl8000004JsonObject.put("zhyl800000403", map1.get(key));
                                        }
                                        if (key.equals("zhyl800000402")) {//检查日期
                                            zhyl8000004JsonObject.put("zhyl800000402", map1.get(key));
                                        }
                                    }
                                    if (nameList.size() > 0) {
                                        for (int i = 0; i < nameList.size(); i++) {
                                            if (nameList.get(i).contains("24hCCr") && !nameList.get(i).contains("校正24hCCr")) {
                                                if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                    zhyl800000404JsonObject.put("zhyl8000004041", valueList.get(i));
                                                } else {
                                                    zhyl800000404JsonObject.put("zhyl8000004041", null);
                                                }
                                                if (!refList.get(i).contains("#") && refList.get(i) != null) {
                                                    zhyl800000404JsonObject.put("zhyl8000004042", refList.get(i));
                                                } else {
                                                    zhyl800000404JsonObject.put("zhyl8000004042", null);
                                                }
                                                zhyl8000004JsonObject.put("zhyl800000404", zhyl800000404JsonObject);
                                            }
                                            if (nameList.get(i).contains("身高")) {
                                                if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                    zhyl8000004JsonObject.put("zhyl800000405", valueList.get(i));
                                                } else {
                                                    zhyl8000004JsonObject.put("zhyl800000405", null);
                                                }
                                            }
                                            if (nameList.get(i).contains("体重")) {
                                                if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                    zhyl8000004JsonObject.put("zhyl800000406", valueList.get(i));
                                                } else {
                                                    zhyl8000004JsonObject.put("zhyl800000406", null);
                                                }
                                            }
                                            if (nameList.get(i).contains("体表面积")) {
                                                if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                    zhyl8000004JsonObject.put("zhyl800000407", valueList.get(i));
                                                } else {
                                                    zhyl8000004JsonObject.put("zhyl800000407", null);
                                                }
                                            }
                                            if (nameList.get(i).contains("校正24hCCr")) {
                                                if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                    zhyl8000004JsonObject.put("zhyl800000408", valueList.get(i));
                                                } else {
                                                    zhyl8000004JsonObject.put("zhyl800000408", null);
                                                }
                                            }

                                        }
                                    }
                                    zhyl80000001Json.put("zhyl8000004", zhyl8000004JsonObject);
                                }
                            }


                            //-------肾脏早起损伤指标每次随访可能有多个---------
                            String suifangshenzaoshuai2Sql = suifangshenzaoshuai2.replace("?", suifang);
                            List<Map<String, Object>> suifangshenzaoshuai2SqlList = commonExecute2(connection, suifangshenzaoshuai2Sql, statement, resultSet);
                            if (suifangshenzaoshuai2SqlList != null && suifangshenzaoshuai2SqlList.size() > 0) {
                                Map<String, Object> shenzaosuai = suifangshenzaoshuai2SqlList.get(0);
                                if (shenzaosuai != null && shenzaosuai.size() > 0) {
                                    JSONObject zhyl8000005JSONObject = new JSONObject();
                                    JSONObject zhyl800000504JSONObject = new JSONObject();
                                    JSONObject zhyl800000505JSONObject = new JSONObject();
                                    JSONObject zhyl800000506JSONObject = new JSONObject();
                                    JSONObject zhyl800000507JSONObject = new JSONObject();
                                    JSONObject zhyl800000508JSONObject = new JSONObject();

                                    for (String key : shenzaosuai.keySet()) {

                                        transJson(shenzaosuai, key, "zhyl800000502", zhyl8000005JSONObject, null);
                                        transJson(shenzaosuai, key, "zhyl800000503", zhyl8000005JSONObject, null);
                                        //   transJson(shenzaosuai, key, "zhyl800000509", zhyl8000005JSONObject, null);

                                        transJson(shenzaosuai, key, "zhyl8000005041", zhyl800000504JSONObject, null);
                                        transJson(shenzaosuai, key, "zhyl8000005042", zhyl800000504JSONObject, null);
                                        transJson(shenzaosuai, key, "zhyl8000005043", zhyl800000504JSONObject, null);

                                        transJson(shenzaosuai, key, "zhyl8000005051", zhyl800000505JSONObject, null);
                                        transJson(shenzaosuai, key, "zhyl8000005052", zhyl800000505JSONObject, null);
                                        transJson(shenzaosuai, key, "zhyl8000005053", zhyl800000505JSONObject, null);

                                        transJson(shenzaosuai, key, "zhyl8000005061", zhyl800000506JSONObject, null);
                                        transJson(shenzaosuai, key, "zhyl8000005062", zhyl800000506JSONObject, null);
                                        transJson(shenzaosuai, key, "zhyl8000005063", zhyl800000506JSONObject, null);

                                        transJson(shenzaosuai, key, "zhyl8000005071", zhyl800000507JSONObject, null);
                                        transJson(shenzaosuai, key, "zhyl8000005072", zhyl800000507JSONObject, null);
                                        transJson(shenzaosuai, key, "zhyl8000005073", zhyl800000507JSONObject, null);

                                        transJson(shenzaosuai, key, "zhyl8000005081", zhyl800000508JSONObject, null);
                                        transJson(shenzaosuai, key, "zhyl8000005082", zhyl800000508JSONObject, null);
                                        transJson(shenzaosuai, key, "zhyl8000005083", zhyl800000508JSONObject, null);

                                    }
                                    zhyl8000005JSONObject.put("zhyl800000504", zhyl800000504JSONObject);
                                    zhyl8000005JSONObject.put("zhyl800000505", zhyl800000505JSONObject);
                                    zhyl8000005JSONObject.put("zhyl800000506", zhyl800000506JSONObject);
                                    zhyl8000005JSONObject.put("zhyl800000507", zhyl800000507JSONObject);
                                    zhyl8000005JSONObject.put("zhyl800000508", zhyl800000508JSONObject);

                                    zhyl80000001Json.put("zhyl8000005", zhyl8000005JSONObject);
                                }
                            }

                            //-------尿蛋白电泳 每次随访可能有多个---------
                            String suifangniaodanbaidianyong2Sql = suifangniaodanbaidianyong2.replace("?", suifang);
                            List<Map<String, Object>> suifangniaodanbaidianyong2SqlList = commonExecute2(connection, suifangniaodanbaidianyong2Sql, statement, resultSet);
                            if (suifangniaodanbaidianyong2SqlList != null && suifangniaodanbaidianyong2SqlList.size() > 0) {
                                Map<String, Object> niaodanbaidianyong = suifangniaodanbaidianyong2SqlList.get(0);
                                if (niaodanbaidianyong != null && niaodanbaidianyong.size() > 0) {
                                    JSONObject zhyl8000006JSONObject = new JSONObject();
                                    JSONObject zhyl800000604JSONObject = new JSONObject();
                                    JSONObject zhyl800000605JSONObject = new JSONObject();
                                    JSONObject zhyl800000606JSONObject = new JSONObject();

                                    for (String key : niaodanbaidianyong.keySet()) {
                                        transJson(niaodanbaidianyong, key, "zhyl800000602", zhyl8000006JSONObject, null);

                                        transJson(niaodanbaidianyong, key, "zhyl8000006041", zhyl800000604JSONObject, null);
                                        transJson(niaodanbaidianyong, key, "zhyl8000006042", zhyl800000604JSONObject, null);

                                        transJson(niaodanbaidianyong, key, "zhyl8000006051", zhyl800000605JSONObject, null);
                                        transJson(niaodanbaidianyong, key, "zhyl8000006052", zhyl800000605JSONObject, null);

                                        transJson(niaodanbaidianyong, key, "zhyl8000006061", zhyl800000606JSONObject, null);
                                        transJson(niaodanbaidianyong, key, "zhyl8000006062", zhyl800000606JSONObject, null);

                                    }
                                    zhyl8000006JSONObject.put("zhyl800000604", zhyl800000604JSONObject);
                                    zhyl8000006JSONObject.put("zhyl800000605", zhyl800000605JSONObject);
                                    zhyl8000006JSONObject.put("zhyl800000606", zhyl800000606JSONObject);

                                    zhyl80000001Json.put("zhyl8000006", zhyl8000006JSONObject);
                                }

                            }

                            //-------血生化 每次随访可能有多个---------
                            String suifangxueshenghua2Sql = suifangxueshenghua2.replace("?", suifang);
                            List<Map<String, Object>> suifangxueshenghua2SqlList = commonExecute2(connection, suifangxueshenghua2Sql, statement, resultSet);
                            if (suifangxueshenghua2SqlList != null && suifangxueshenghua2SqlList.size() > 0) {
                                Map<String, Object> xueshenghua = suifangxueshenghua2SqlList.get(0);
                                if (xueshenghua != null && xueshenghua.size() > 0) {
                                    JSONObject zhyl8000007SONObject = new JSONObject();
                                    JSONObject zhyl800000705JSONObject = new JSONObject();
                                    JSONObject zhyl800000706JSONObject = new JSONObject();
                                    JSONObject zhyl800000707JSONObject = new JSONObject();
                                    JSONObject zhyl800000708JSONObject = new JSONObject();
                                    JSONObject zhyl800000709JSONObject = new JSONObject();
                                    JSONObject zhyl800000710JSONObject = new JSONObject();
                                    JSONObject zhyl800000711JSONObject = new JSONObject();
                                    JSONObject zhyl800000712JSONObject = new JSONObject();
                                    JSONObject zhyl800000713JSONObject = new JSONObject();
                                    JSONObject zhyl800000714JSONObject = new JSONObject();
                                    JSONObject zhyl800000715JSONObject = new JSONObject();
                                    JSONObject zhyl800000716JSONObject = new JSONObject();
                                    JSONObject zhyl800000717JSONObject = new JSONObject();
                                    JSONObject zhyl800000718JSONObject = new JSONObject();
                                    JSONObject zhyl800000719JSONObject = new JSONObject();
                                    JSONObject zhyl800000720JSONObject = new JSONObject();
                                    JSONObject zhyl800000721JSONObject = new JSONObject();
                                    JSONObject zhyl800000722JSONObject = new JSONObject();

                                    for (String key : xueshenghua.keySet()) {

                                        transJson(xueshenghua, key, "zhyl800000702", zhyl8000007SONObject, null);
                                        transJson(xueshenghua, key, "zhyl800000703", zhyl8000007SONObject, null);
                                        transJson(xueshenghua, key, "zhyl800000704", zhyl8000007SONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007051", zhyl800000705JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007052", zhyl800000705JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007061", zhyl800000706JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007062", zhyl800000706JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007071", zhyl800000707JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007072", zhyl800000707JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007081", zhyl800000708JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007082", zhyl800000708JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007091", zhyl800000709JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007092", zhyl800000709JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007101", zhyl800000710JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007102", zhyl800000710JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007111", zhyl800000711JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007112", zhyl800000711JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007121", zhyl800000712JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007122", zhyl800000712JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007131", zhyl800000713JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007132", zhyl800000713JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007141", zhyl800000714JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007142", zhyl800000714JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007151", zhyl800000715JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007152", zhyl800000715JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007161", zhyl800000716JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007162", zhyl800000716JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007171", zhyl800000717JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007172", zhyl800000717JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007181", zhyl800000718JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007182", zhyl800000718JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007191", zhyl800000719JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007192", zhyl800000719JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007201", zhyl800000720JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007202", zhyl800000720JSONObject, null);

                                        transJson(xueshenghua, key, "zhyl8000007211", zhyl800000721JSONObject, null);
                                        transJson(xueshenghua, key, "zhyl8000007212", zhyl800000721JSONObject, null);


                                    }
                                    zhyl8000007SONObject.put("zhyl800000705", zhyl800000705JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000706", zhyl800000706JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000707", zhyl800000707JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000708", zhyl800000708JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000709", zhyl800000709JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000710", zhyl800000710JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000711", zhyl800000711JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000712", zhyl800000712JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000713", zhyl800000713JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000714", zhyl800000714JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000715", zhyl800000715JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000717", zhyl800000717JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000719", zhyl800000719JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000720", zhyl800000720JSONObject);
                                    zhyl8000007SONObject.put("zhyl800000721", zhyl800000721JSONObject);


                                    zhyl80000001Json.put("zhyl8000007", zhyl8000007SONObject);
                                }

                            }
                            //-------纯音测听 每次随访可能有多个---------
                            commonjiancha1(suifang, suifangchunyinceting2, zhyl80000001Json, "zhyl8000016", connection, statement, resultSet, null);

                            //-------眼裂隙灯检查 每次随访可能有多个---------
                            commonjiancha1(suifang, suifangyanleixideng2, zhyl80000001Json, "zhyl8000017", connection, statement, resultSet, null);

                            //-------血常规 每次随访可能有多个---------
                            String suifangxuechanggui2Sql = suifangxuechanggui2.replace("?", suifang);
                            List<Map<String, Object>> suifangxuechanggui2SqlList = commonExecute2(connection, suifangxuechanggui2Sql, statement, resultSet);
                            if (suifangxuechanggui2SqlList != null && suifangxuechanggui2SqlList.size() > 0) {
                                Map<String, Object> xuechanggui = suifangxuechanggui2SqlList.get(0);
                                if (xuechanggui != null && xuechanggui.size() > 0) {
                                    JSONObject zhyl8000021JSONObject = new JSONObject();
                                    JSONObject zhyl80000213JSONObject = new JSONObject();

                                    for (String key : xuechanggui.keySet()) {
                                        transJson(xuechanggui, key, "zhyl80000211", zhyl8000021JSONObject, null);
                                        transJson(xuechanggui, key, "zhyl800002131", zhyl80000213JSONObject, null);
                                        transJson(xuechanggui, key, "zhyl800002132", zhyl80000213JSONObject, null);
                                    }
                                    zhyl8000021JSONObject.put("zhyl80000213", zhyl80000213JSONObject);
                                    zhyl80000001Json.put("zhyl8000021", zhyl8000021JSONObject);
                                }

                            }
                            //-------24小时尿电解质 每次随访可能有多个---------
                            String suifang24niaodianjiezhiSql = suifang24niaodianjiezhi.replace("?", suifang);
                            List<Map<String, Object>> suifang24niaodianjiezhiSqlList = commonExecute2(connection, suifang24niaodianjiezhiSql, statement, resultSet);
                            if (suifang24niaodianjiezhiSqlList != null && suifang24niaodianjiezhiSqlList.size() > 0) {
                                Map<String, Object> map1 = suifang24niaodianjiezhiSqlList.get(0);
                                if (map1 != null && map1.size() > 0) {
                                    JSONObject zhyl8000023Object = new JSONObject();
                                    JSONObject zhyl80000233Object = new JSONObject();
                                    JSONObject zhyl80000235Object = new JSONObject();

                                    List<String> nameList = new ArrayList<>();
                                    List<String> valueList = new ArrayList<>();
                                    List<String> refList = new ArrayList<>();
                                    for (String key : map1.keySet()) {
                                        if (key.toUpperCase().equals("HETERM") && map1.get(key).toString().contains(",")) {
                                            nameList = Arrays.asList(map1.get(key).toString().split(","));
                                        }
                                        if (key.toUpperCase().equals("VALUE") && map1.get(key).toString().contains(",")) {
                                            valueList = Arrays.asList(map1.get(key).toString().split(","));
                                        }
                                        if (key.toUpperCase().equals("HESREF") && map1.get(key).toString().contains(",")) {
                                            refList = Arrays.asList(map1.get(key).toString().split(","));
                                        }
                                        if (key.equals("zhyl80000231")) {//检查日期
                                            zhyl8000023Object.put("zhyl80000231", map1.get(key));
                                        }
                                    }
                                    if (nameList.size() > 0) {
                                        for (int i = 0; i < nameList.size(); i++) {
                                            if (nameList.get(i).contains("24h尿钙") && !nameList.get(i).contains("24h尿钙体重比")) {
                                                if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                    zhyl80000233Object.put("zhyl800002331", valueList.get(i));
                                                } else {
                                                    zhyl80000233Object.put("zhyl800002331", null);
                                                }
                                                if (!refList.get(i).contains("#") && refList.get(i) != null) {
                                                    zhyl80000233Object.put("zhyl800002332", refList.get(i));
                                                } else {
                                                    zhyl80000233Object.put("zhyl800002332", null);
                                                }
                                                zhyl8000023Object.put("zhyl80000233", zhyl80000233Object);

                                            }
                                            if (nameList.get(i).contains("体重")) {
                                                if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                    zhyl8000023Object.put("zhyl80000234", valueList.get(i));
                                                } else {
                                                    zhyl8000023Object.put("zhyl80000234", null);
                                                }
                                            }

                                            if (nameList.get(i).contains("24h尿钙体重比")) {
                                                if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                    zhyl80000235Object.put("zhyl800002351", valueList.get(i));
                                                } else {
                                                    zhyl80000235Object.put("zhyl800002351", null);
                                                }
                                                if (!refList.get(i).contains("#") && refList.get(i) != null) {
                                                    zhyl80000235Object.put("zhyl800002352", refList.get(i));
                                                } else {
                                                    zhyl80000235Object.put("zhyl800002352", null);
                                                }
                                                zhyl8000023Object.put("zhyl80000235", zhyl80000235Object);
                                            }
                                        }
                                    }
                                    zhyl80000001Json.put("zhyl8000023", zhyl8000023Object);
                                }

                            }
                            //-------肾脏影像学检查 每次随访可能有多个---------
                            commonjiancha1(suifang, suifangshenzangyixuejiancha, zhyl80000001Json, "zhyl8000024", connection, statement, resultSet, null);

                            //-------肝、胆影像学检查 每次随访可能有多个---------
                            commonjiancha1(suifang, suifanggandanyingxiangxue, zhyl80000001Json, "zhyl8000026", connection, statement, resultSet, null);

                            //-------酸中毒时尿PH 每次随访可能有多个---------
                            String suifangsuanzhongdusql = suifangsuanzhongdu.replace("?", suifang);
                            List<Map<String, Object>> suifangsuanzhongdusqlList = commonExecute2(connection, suifangsuanzhongdusql, statement, resultSet);
                            if (suifangsuanzhongdusqlList != null && suifangsuanzhongdusqlList.size() > 0) {
                                Map<String, Object> suanzhongdu = suifangsuanzhongdusqlList.get(0);
                                if (suanzhongdu != null && suanzhongdu.size() > 0) {
                                    JSONObject zhyl8000033JSONObject = new JSONObject();
                                    JSONObject zhyl80000334JSONObject = new JSONObject();
                                    for (String key : suanzhongdu.keySet()) {
                                        transJson(suanzhongdu, key, "zhyl80000332", zhyl8000033JSONObject, null);
                                        transJson(suanzhongdu, key, "zhyl800003341", zhyl80000334JSONObject, null);
                                        transJson(suanzhongdu, key, "zhyl800003342", zhyl80000334JSONObject, null);
                                    }
                                    zhyl8000033JSONObject.put("zhyl80000334", zhyl80000334JSONObject);
                                    zhyl80000001Json.put("zhyl8000033", zhyl8000033JSONObject);
                                }
                            }

                            //-------肾钙化/肾结石 每次随访可能有多个---------
                            commonjiancha1(suifang, suifangshengaihua, zhyl80000001Json, "zhyl8000038", connection, statement, resultSet, null);
                            //------骨骼X线片 每次随访可能有多个---------
                            String suifanggugexSql = suifanggugex.replace("?", suifang);
                            List<Map<String, Object>> suifanggugexSqlList = commonExecute2(connection, suifanggugexSql, statement, resultSet);
                            if (suifanggugexSqlList != null && suifanggugexSqlList.size() > 0) {
                                Map<String, Object> gugex2 = suifanggugexSqlList.get(0);
                                if (gugex2 != null && gugex2.size() > 0) {
                                    JSONObject zhyl8000039JSONObject = new JSONObject();
                                    for (String key : gugex2.keySet()) {
                                        transJson(gugex2, key, "zhyl80000392", zhyl8000039JSONObject, null);
                                        transJson(gugex2, key, "zhyl80000394", zhyl8000039JSONObject, "list");
                                        transJson(gugex2, key, "zhyl80000395", zhyl8000039JSONObject, null);
                                    }
                                    zhyl80000001Json.put("zhyl8000039", zhyl8000039JSONObject);
                                }
                            }
                            //------血甲状旁腺素 每次随访可能有多个---------
                            String suifangxuejiapangSql = suifangxuejiapang.replace("?", suifang);
                            List<Map<String, Object>> suifangxuejiapangSqllList = commonExecute2(connection, suifangxuejiapangSql, statement, resultSet);
                            if (suifangxuejiapangSqllList != null && suifangxuejiapangSqllList.size() > 0) {
                                Map<String, Object> xuejiazhuangpang = suifangxuejiapangSqllList.get(0);
                                if (xuejiazhuangpang != null && xuejiazhuangpang.size() > 0) {
                                    JSONObject zhyl8000040JSONObject = new JSONObject();
                                    JSONObject zhyl80000403JSONObject = new JSONObject();
                                    for (String key : xuejiazhuangpang.keySet()) {
                                        transJson(xuejiazhuangpang, key, "zhyl80000402", zhyl8000040JSONObject, null);
                                        transJson(xuejiazhuangpang, key, "zhyl800004031", zhyl80000403JSONObject, null);
                                        transJson(xuejiazhuangpang, key, "zhyl800004032", zhyl80000403JSONObject, null);
                                    }
                                    zhyl8000040JSONObject.put("zhyl80000403", zhyl80000403JSONObject);
                                    zhyl80000001Json.put("zhyl8000040", zhyl8000040JSONObject);
                                }

                            }
                            //-------泌尿系统影像学检查 每次随访可能有多个---------
                            commonjiancha1(suifang, suifangminiaoxiyingxiangjiancha, zhyl80000001Json, "zhyl8000041", connection, statement, resultSet, null);

                            //-------泌尿系超声 每次随访可能有多个---------
                            if (type.equals("2")) {//蛋白尿
                                commonjiancha1(suifang, suifangchaoshen2, zhyl80000001Json, "zhyl8000028", connection, statement, resultSet, null);
                            }
                            if (type.equals("1")) {//Alport综合征
                                commonjiancha1(suifang, suifangchaoshen4, zhyl80000001Json, "zhyl8000028", connection, statement, resultSet, null);
                            }


                            //-------用药 每次随访可能有多个---------
                            if (suifangtime != null) {
                                JSONObject zhyl80001001Json = new JSONObject();
                                JSONArray zhyl80001002JsonArray = new JSONArray(); //
                                String suifangyongyao2Sql = suifangyongyao2.replace("?", suifangtime).replace("#", s);
                                List<Map<String, Object>> suifangyongyao2SqlList = commonExecute2(connection, suifangyongyao2Sql, statement, resultSet);
                                if (suifangyongyao2SqlList != null && suifangyongyao2SqlList.size() > 0) {
                                    for (Map<String, Object> yongyao2Map : suifangyongyao2SqlList) {
                                        if (yongyao2Map != null && yongyao2Map.size() > 0) {
                                            JSONObject zhyl80000100JSONObject = new JSONObject();
                                            for (String key : yongyao2Map.keySet()) {
                                                transJson(yongyao2Map, key, "zhyl8100039", zhyl80000100JSONObject, null);
                                                transJson(yongyao2Map, key, "zhyl8100040", zhyl80000100JSONObject, null);
                                                transJson(yongyao2Map, key, "zhyl8100038", zhyl80000100JSONObject, null);
                                            }
                                            yongyao2Map.remove("zhyl8100038");
                                            yongyao2Map.remove("zhyl8100039");
                                            yongyao2Map.remove("zhyl8100040");

                                            yongyao2Map.put("zhyl80000100", zhyl80000100JSONObject);
                                            zhyl80001002JsonArray.add(yongyao2Map);
                                        }
                                    }
                                    zhyl80001001Json.put("zhyl80001002", zhyl80001002JsonArray);
                                    zhyl80000001Json.put("zhyl80001001", zhyl80001001Json);
                                }

                                JSONObject zhyl80000001 = new JSONObject();
                                zhyl80000001.put("zhyl80000001", zhyl80000001Json);
                                zhyl80000000JSONArray.add(zhyl80000001);
                                zhyl8000000Json.put("zhyl80000000", zhyl80000000JSONArray);
                            }
                        }


                        jsonObject.put("zhyl8000000", zhyl8000000Json);
                    }

//-----------------------------------结局----------------------------------------------------
                    commonjiancha1(s, jieju2, jsonObject, "zhyl9000000", connection, statement, resultSet, Arrays.asList("zhyl9200002", "zhyl9200014"));


//---------------------------------------累及其他系统------------------------------------------------
                    if (type.equals("5")) {//先天性肾脏尿路畸形

                        JSONObject zhyl10000000Json = new JSONObject();
                        JSONObject zhyl11000000Json = new JSONObject();
                        //肌肉骨骼
                        JSONArray zhyl10000001JSONArray = new JSONArray();
                        commonjiancha(s, zhyl10000001JSONArray, jirouguge, zhyl11000000Json, "zhyl10000001", connection, statement, resultSet);
                        //消化系统
                        JSONArray zhyl10000002JSONArray = new JSONArray();
                        commonjiancha(s, zhyl10000002JSONArray, xiaohuaxitong, zhyl11000000Json, "zhyl10000002", connection, statement, resultSet);
                        //先天性心脏病
                        JSONArray zhyl10000003JSONArray = new JSONArray();
                        commonjiancha(s, zhyl10000003JSONArray, xiantianxingxinzangbing, zhyl11000000Json, "zhyl10000003", connection, statement, resultSet);
                        //中枢神经系统
                        JSONArray zhyl10000004JSONArray = new JSONArray();
                        commonjiancha(s, zhyl10000004JSONArray, zhongshushenjing, zhyl11000000Json, "zhyl10000004", connection, statement, resultSet);
                        //耳、面、颈部
                        JSONArray zhyl10000005JSONArray = new JSONArray();
                        commonjiancha(s, zhyl10000005JSONArray, ermianjing, zhyl11000000Json, "zhyl10000005", connection, statement, resultSet);
                        //肺部 无数据
                        JSONArray zhyl10000006JSONArray = new JSONArray();
                        //唇和/或腭  无数据
                        JSONArray zhyl10000007JSONArray = new JSONArray();
                        //腹壁 无数据
                        JSONArray zhyl10000008JSONArray = new JSONArray();
                        //尿道下裂
                        JSONArray zhyl10000009JSONArray = new JSONArray();
                        commonjiancha(s, zhyl10000009JSONArray, niaodaoxielie, zhyl11000000Json, "zhyl10000009", connection, statement, resultSet);
                        //膈疝、综合征 无数据
                        //染色体检测
                        JSONArray zhyl10000010JSONArray = new JSONArray();
                        commonjiancha(s, zhyl10000010JSONArray, ranseti, zhyl11000000Json, "zhyl10000010", connection, statement, resultSet);


                        zhyl10000000Json.put("zhyl11000000", zhyl11000000Json);
                        jsonObject.put("zhyl10000000", zhyl10000000Json);
                    }

//---------------------------------------------------------------------------------------
                    ObjectMapper mapper = new ObjectMapper();    //为了让json中的字段有序
                    mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
                    String jsonStr = mapper.writeValueAsString(jsonObject);


                    System.out.println(jsonStr);


//---------------------------------------------------------------------------------------
                    if (start.equals("1")) {
                        transform("http://10.0.108.41/api-gate/cgkd-export/imexport/importData", jsonStr, s, connection, admin, password, type);
                    }


                }
            }


        }
        // 关闭资源
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (
                SQLException e) {
            log.error("Error closing resources: " + e, "");
        }


    }


    public static void commonjiancha(String s, JSONArray jsonArray, String sql, JSONObject jsonObject, String biaoti, Connection connection, Statement statement, ResultSet resultSet) throws Exception {
        String xueshenghua2Sql = sql.replace("?", s);
        List<Map<String, Object>> xueshenghua2SqlList = commonExecute2(connection, xueshenghua2Sql, statement, resultSet);
        if (xueshenghua2SqlList != null) {
            jsonArray.addAll(xueshenghua2SqlList);
            jsonObject.put(biaoti, jsonArray);
        }
    }

    public static void commonjiancha1(String s, String sql, JSONObject jsonObject, String biaoti, Connection connection, Statement statement, ResultSet resultSet, List<String> transList) throws Exception {
        String xueshenghua2Sql = sql.replace("?", s);
        List<Map<String, Object>> xueshenghua2SqlList = commonExecute2(connection, xueshenghua2Sql, statement, resultSet);
        if (xueshenghua2SqlList != null) {
            Map<String, Object> map = xueshenghua2SqlList.get(0);
            if (transList != null) {
                for (String list : transList) {
                    if (map.keySet().contains(list)) {
                        if (map.get(list) != null) {
                            if (map.get(list).toString().contains(",")) {
                                map.put(list, Arrays.asList(map.get(list).toString().split(",")));
                            } else {
                                map.put(list, Arrays.asList(map.get(list)));
                            }
                        }

                    }

                }
                jsonObject.put(biaoti, map);
            } else {
                jsonObject.put(biaoti, map);
            }
        }
    }

    public static void jiazushi(String s, String sql, Connection connection, Statement statement, ResultSet resultSet, JSONArray jsonArray) throws Exception {
        String guanxi = sql.replace("?", s);
        List<Map<String, Object>> muqinMapList = commonExecute2(connection, guanxi, statement, resultSet);
        if (muqinMapList != null && muqinMapList.size() > 0) {
            for (Map<String, Object> muqinMap : muqinMapList) {
                JSONObject zhyl4100000Json = new JSONObject();
                if (muqinMap != null && muqinMap.size() > 0) {
                    List<String> nameList = new ArrayList<>();
                    List<String> isNotList = new ArrayList<>();
                    List<String> ageList = new ArrayList<>();
                    for (String key : muqinMap.keySet()) {

                        if (key.toUpperCase().equals("FHTERM") && muqinMap.get(key) != null && muqinMap.get(key).toString().contains(",")) {
                            nameList = Arrays.asList(muqinMap.get(key).toString().split(","));
                        }
                        if (key.toUpperCase().equals("FHTERM") && muqinMap.get(key) != null && !muqinMap.get(key).toString().contains(",")) {
                            nameList = Collections.singletonList(muqinMap.get(key).toString());
                        }
                        if (key.toUpperCase().equals("FHOCCUR") && muqinMap.get(key) != null && muqinMap.get(key).toString().contains(",")) {
                            isNotList = Arrays.asList(muqinMap.get(key).toString().split(","));
                        }
                        if (key.toUpperCase().equals("FHOCCUR") && muqinMap.get(key) != null && !muqinMap.get(key).toString().contains(",")) {
                            isNotList = Collections.singletonList(muqinMap.get(key).toString());
                        }
                        if (key.toUpperCase().equals("AGE") && muqinMap.get(key) != null && muqinMap.get(key).toString().contains(",")) {
                            ageList = Arrays.asList(muqinMap.get(key).toString().split(","));
                        }
                        if (key.toUpperCase().equals("AGE") && muqinMap.get(key) != null && !muqinMap.get(key).toString().contains(",")) {
                            ageList = Collections.singletonList(muqinMap.get(key).toString());
                        }
                        if (key.toUpperCase().equals("FHORRES")) {
                            zhyl4100000Json.put("zhyl4100015", muqinMap.get(key));//其他
                        }
                        if (key.toUpperCase().equals("FHOTHER")) {
                            zhyl4100000Json.put("zhyl4100018", muqinMap.get(key));//具体表现
                        }
                        if (key.toUpperCase().equals("FHSTATUS")) {//生存状态
                            zhyl4100000Json.put("zhyl4100002", castDataShengCun((String) muqinMap.get(key)));
                        }
                        if (key.toUpperCase().equals("FHITEM")) { //有无家族史
                            zhyl4100000Json.put("zhyl4000001", castDataYinYang((String) muqinMap.get(key)));
                        }
                        if (key.toUpperCase().equals("FHSUBREL")) { //与患者关系
                            zhyl4100000Json.put("zhyl4100001", muqinMap.get(key));
                        }
                    }
                    if (nameList.size() > 0) {
                        for (int i = 0; i < nameList.size(); i++) {
                            String name = nameList.get(i);
                            String isNot = isNotList.get(i);
                            String age = ageList.get(i);
                            if (name.contains("蛋白尿")) {
                                zhyl4100000Json.put("zhyl4100003", castData(isNot));
                                if (!age.contains("#") && age != null) {
                                    zhyl4100000Json.put("zhyl4100004", age);
                                } else {
                                    zhyl4100000Json.put("zhyl4100004", null);
                                }
                            }
                            if (name.contains("肾功能异常")) {
                                zhyl4100000Json.put("zhyl4100005", castData(isNot));
                                if (!age.contains("#") && age != null) {
                                    zhyl4100000Json.put("zhyl4100006", age);
                                } else {
                                    zhyl4100000Json.put("zhyl4100006", null);
                                }
                            }
                            if (name.contains("听力异常")) {
                                zhyl4100000Json.put("zhyl4100007", castData(isNot));
                                if (!age.contains("#") && age != null) {
                                    zhyl4100000Json.put("zhyl4100008", age);
                                } else {
                                    zhyl4100000Json.put("zhyl4100008", null);
                                }
                            }
                            if (name.contains("眼部症状")) {
                                zhyl4100000Json.put("zhyl4100009", castData(isNot));
                                if (!age.contains("#") && age != null) {
                                    zhyl4100000Json.put("zhyl4100010", age);
                                } else {
                                    zhyl4100000Json.put("zhyl4100010", null);
                                }
                            }
                            if (name.contains("血尿")) {
                                zhyl4100000Json.put("zhyl4100011", castData(isNot));
                                if (!age.contains("#") && age != null) {
                                    zhyl4100000Json.put("zhyl4100012", age);
                                } else {
                                    zhyl4100000Json.put("zhyl4100012", null);
                                }
                            }
                            if (name.contains("肾囊肿")) {
                                zhyl4100000Json.put("zhyl4100013", castData(isNot));
                                if (!age.contains("#") && age != null) {
                                    zhyl4100000Json.put("zhyl4100014", age);
                                } else {
                                    zhyl4100000Json.put("zhyl4100014", null);
                                }
                            }

                            if (name.contains("肾脏尿路畸形")) {
                                zhyl4100000Json.put("zhyl4100016", castData(isNot));
                                if (!age.contains("#") && age != null) {
                                    zhyl4100000Json.put("zhyl4100017", age);
                                } else {
                                    zhyl4100000Json.put("zhyl4100017", null);
                                }
                            }

                        }

                    }

                    jsonArray.add(zhyl4100000Json);

                }
            }
        }

    }

    public static Object castData(String s) {
        if (s.contains("是")) {
            return "1";
        }
        if (s.contains("否")) {
            return "0";
        }
        if (s.contains("不详")) {
            return "2";
        }
        return null;
    }

    public static Object castDataYinYang(String s) {
        if (s != null) {
            if (s.contains("阳性")) {
                return "2";
            }
            if (s.contains("阴性")) {
                return "1";
            }
            if (s.contains("不确定")) {
                return "3";
            }
        }
        return null;
    }

    public static Object castDataShengCun(String s) {
        if (s != null) {
            if (s.contains("生存")) {
                return "1";
            }
            if (s.contains("死亡")) {
                return "2";
            }
        }
        return null;
    }


    public static void transform(String baseUrl, String jsonStr, String id, Connection connection, String admin, String password, String type) throws Exception {

//        String dataUrl = baseUrl + "/iga-export/imexport/importData";
//        dataUrl = "http://10.0.108.41/api-gate/iga-export/imexport/importData";

        String tokenurl = "http://10.0.108.41/api-gate";


        //   ----------------每隔30分钟获取一次token，避免多次调用---------------------------
        String accessToken = null;
        Statement tokenTime = null;
        ResultSet resultSetToken = null;
        String tokenSql = "SELECT token  FROM  " + "dbo.token_time  ";
        List<String> tokenList = null;
        tokenTime = executeSql(tokenSql, connection);
        resultSetToken = tokenTime.executeQuery(tokenSql);
        try {
            if (resultSetToken != null) {
                tokenList = ResultSetUtils1.allResultSet(resultSetToken);
            }
            if (tokenList == null || (tokenList.size() == 0) || (tokenList.size() > 0 && tokenList.get(0) == null)
                    || (tokenList.size() > 0 && tokenList.get(0).equals("")) || (tokenList.size() > 0 && tokenList.get(0).equals("null"))) {
                accessToken = getToken(tokenurl, admin, password, "cgkd");
                Date date = new Date();
                long a = date.getTime() + 30 * 60 * 1000;  //30分钟
                String sql = "UPDATE " + "dbo.token_time " + " SET token= " + "'" + accessToken + "'" + "  ,  token_time= " + a;
                tokenTime = executeSql(sql, connection);
                tokenTime.execute(sql);
            } else if (tokenList.size() > 0 && tokenList.get(0) != null && !tokenList.get(0).equals("null")) { //有token
                tokenSql = "SELECT token_time  FROM  " + "dbo.token_time  ";
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
                        accessToken = getToken(tokenurl, admin, password, "cgkd");
                        Date date1 = new Date();
                        long a1 = date1.getTime() + 30 * 60 * 1000;
                        String sql1 = "UPDATE " + "dbo.token_time  " + " SET token = " + "'" + accessToken + "'" + "  ,  token_time= " + a1;

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
            kettleResponse dataResponse = HttpClientUtils.uploadFile1(baseUrl, multipartFile, accessToken, type);
            if (dataResponse.getCode() == 200) {
                log.info("病人id: " + id + "  传输数据成功!");
            }
            if (dataResponse.getCode() != 200) {
                throw new Exception(dataResponse.getData());
            }
        }

    }

}
