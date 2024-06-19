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

                    if (map != null && map.size() > 0) {
                        for (String key : map.keySet()) {
                            if (map.keySet().contains("zhyl1110023") && key.equals("zhyl1110023")) {
                                zhyl1100022Json.put("zhyl1110023", map.get(key));
                            }
                            if (map.keySet().contains("zhyl1110024") && key.equals("zhyl1110024")) {
                                zhyl1100022Json.put("zhyl1110024", map.get(key));
                            }
                            if (map.keySet().contains("zhyl1110026") && key.equals("zhyl1110026")) {
                                zhyl1100022Json.put("zhyl1110026", map.get(key));
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

                        }
                        map.remove("zhyl1110023");
                        map.remove("zhyl1110024");
                        map.remove("zhyl1110026");
                        map.remove("zhyl1110028");
                        map.remove("zhyl1110029");
                        map.remove("zhyl1110030");


                        map.put("zhyl1100022", zhyl1100022Json);
                        map.put("zhyl1100027", zhyl1100027Json);


                        JSONObject zhyl1000000 = new JSONObject();

                        zhyl1000000.put("zhyl1100000", map);
                        jsonObject.put("zhyl1000000", zhyl1000000);   //一般资料zhyl1000000
                    }


//----------------------------------------------现病史-------------------------------------------------
                    String xianbingshi = xianbingshi2.replace("?", s); //现病史
                    Map<String, Object> xianbingshiMap = commonExecute1(connection, xianbingshi, statement, resultSet);
                    if (xianbingshiMap != null) {
                        if (xianbingshiMap.keySet().contains("zhyl2100030")) {
                            if (xianbingshiMap.get("zhyl2100030") != null && xianbingshiMap.get("zhyl2100030").toString().contains(",")) {
                                String commaSeparatedString = (String) xianbingshiMap.get("zhyl2100030");
                                xianbingshiMap.put("zhyl2100030", Arrays.asList(commaSeparatedString.split(",")));
                            }
                            if (xianbingshiMap.get("zhyl2100030") != null && !xianbingshiMap.get("zhyl2100030").toString().contains(",")) {
                                String commaSeparatedString = (String) xianbingshiMap.get("zhyl2100030");
                                xianbingshiMap.put("zhyl2100030", Arrays.asList(commaSeparatedString));
                            }
                            if (xianbingshiMap.get("zhyl2100031") != null && xianbingshiMap.get("zhyl2100031").toString().contains(",")) {
                                String commaSeparatedString = (String) xianbingshiMap.get("zhyl2100031");
                                xianbingshiMap.put("zhyl2100031", Arrays.asList(commaSeparatedString.split(",")));
                            }
                            if (xianbingshiMap.get("zhyl2100031") != null && !xianbingshiMap.get("zhyl2100031").toString().contains(",")) {
                                String commaSeparatedString = (String) xianbingshiMap.get("zhyl2100031");
                                xianbingshiMap.put("zhyl2100031", Arrays.asList(commaSeparatedString));
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
                                for (String key : yaoWuMap.keySet()) {
                                    transJson(yaoWuMap, key, "zhyl5100022", zhyl5000022Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100023", zhyl5000022Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100028", zhyl5000027Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100029", zhyl5000027Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100031", zhyl5000030Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100032", zhyl5000030Json, null);

                                }
                                yaoWuMap.remove("zhyl5100022");
                                yaoWuMap.remove("zhyl5100023");
                                yaoWuMap.remove("zhyl5100028");
                                yaoWuMap.remove("zhyl5100029");
                                yaoWuMap.remove("zhyl5100031");
                                yaoWuMap.remove("zhyl5100032");

                                yaoWuMap.put("zhyl5000022", zhyl5000022Json);
                                yaoWuMap.put("zhyl5000027", zhyl5000027Json);
                                yaoWuMap.put("zhyl5000030", zhyl5000030Json);
                                zhyl5000000Json.add(yaoWuMap);
                            }
                        }

                    }
                    jsonObject.put("zhyl5000000", zhyl5000000Json);
//-------------------------------------------辅助检查--------------------------------------------
                    JSONObject zhyl6000000JSONObject = new JSONObject(); //
                    JSONObject zhyl60000000JSONObject = new JSONObject();

                    JSONArray zhyl6000001Json = new JSONArray(); //尿常规
                    String niaochangguiSql = niaochanggui2.replace("?", s);
                    List<Map<String, Object>> niaochangguiList = commonExecute2(connection, niaochangguiSql, statement, resultSet);//有多条
                    if (niaochangguiList != null) {
                        zhyl6000001Json.addAll(niaochangguiList);
                        zhyl60000000JSONObject.put("zhyl6000001", zhyl6000001Json);
                    }

                    JSONArray zhyl6000002Json = new JSONArray(); //尿蛋白肌酐比
                    String niaodanbaijiganSql = niaodanbaijigan2.replace("?", s);
                    List<Map<String, Object>> niaodanbaijiganSqlList = commonExecute2(connection, niaodanbaijiganSql, statement, resultSet);
                    if (niaodanbaijiganSqlList != null) {
                        zhyl6000002Json.addAll(niaodanbaijiganSqlList);
                        zhyl60000000JSONObject.put("zhyl6000002", zhyl6000002Json);
                    }

                    JSONArray zhyl6000003Json = new JSONArray(); //24h尿蛋白定量
                    String niaodanbaidingliang24h2Sql = niaodanbaidingliang24h2.replace("?", s);
                    List<Map<String, Object>> niaodanbaidingliang24h2SqlList = commonExecute2(connection, niaodanbaidingliang24h2Sql, statement, resultSet);
                    if (niaodanbaidingliang24h2SqlList != null) {
                        zhyl6000003Json.addAll(niaodanbaidingliang24h2SqlList);
                        zhyl60000000JSONObject.put("zhyl6000003", zhyl6000003Json);
                    }


                    JSONArray zhyl6000004Json = new JSONArray(); //24h肌酐清除率
                    String jiganqingchu24h2Sql = jiganqingchu24h2.replace("?", s);
                    List<Map<String, Object>> jiganqingchu24h2SqlList = commonExecute2(connection, jiganqingchu24h2Sql, statement, resultSet);
                    if (jiganqingchu24h2SqlList != null) {
                        for (Map<String, Object> map1 : jiganqingchu24h2SqlList) {
                            if (map1 != null && map1.size() > 0) {
                                JSONObject zhyl6000004JsonObject = new JSONObject();

                                List<String> nameList = new ArrayList<>();
                                List<String> valueList = new ArrayList<>();
                                for (String key : map1.keySet()) {
                                    if (key.toUpperCase().equals("HETERM") && map1.get(key).toString().contains(",")) {
                                        nameList = Arrays.asList(map1.get(key).toString().split(","));
                                    }
                                    if (key.toUpperCase().equals("VALUE") && map1.get(key).toString().contains(",")) {
                                        valueList = Arrays.asList(map1.get(key).toString().split(","));
                                    }
                                    if (key.toUpperCase().equals("HEHOSP")) {
                                        zhyl6000004JsonObject.put("zhyl6100017", map1.get(key));
                                    }
                                    if (key.equals("zhyl6100016")) {
                                        zhyl6000004JsonObject.put("zhyl6100016", map1.get(key));
                                    }
                                    if (key.equals("zhyl6100104")) {
                                        zhyl6000004JsonObject.put("zhyl6100104", map1.get(key));
                                    }
                                }
                                if (nameList.size() > 0) {
                                    for (int i = 0; i < nameList.size(); i++) {
                                        if (nameList.get(i).contains("24hCCr") && !nameList.get(i).contains("校正24hCCr")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl6100018", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl6100018", null);
                                            }
                                        }
                                        if (nameList.get(i).contains("身高")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl6100019", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl6100019", null);
                                            }
                                        }
                                        if (nameList.get(i).contains("体重")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl6100020", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl6100020", null);
                                            }
                                        }
                                        if (nameList.get(i).contains("体表面积")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl6100021", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl6100021", null);
                                            }
                                        }
                                        if (nameList.get(i).contains("校正24hCCr")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl6100022", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl6100022", null);
                                            }
                                        }

                                    }

                                }
                                zhyl6000004Json.add(zhyl6000004JsonObject);
                            }
                        }
                        zhyl60000000JSONObject.put("zhyl6000004", zhyl6000004Json);
                    }

                    JSONArray zhyl6000005Json = new JSONArray(); //肾早损
                    String shenzaosuai2Sql = shenzaosuai2.replace("?", s);
                    List<Map<String, Object>> shenzaosuai2SqlList = commonExecute2(connection, shenzaosuai2Sql, statement, resultSet);
                    if (shenzaosuai2SqlList != null) {
                        zhyl6000005Json.addAll(shenzaosuai2SqlList);
                        zhyl60000000JSONObject.put("zhyl6000005", zhyl6000005Json);
                    }

                    JSONArray zhyl6000006Json = new JSONArray(); //尿蛋白电泳
                    String niaodanbaidianyong2Sql = niaodanbaidianyong2.replace("?", s);
                    List<Map<String, Object>> niaodanbaidianyong2SqlList = commonExecute2(connection, niaodanbaidianyong2Sql, statement, resultSet);
                    if (niaodanbaidianyong2SqlList != null) {
                        zhyl6000006Json.addAll(niaodanbaidianyong2SqlList);
                        zhyl60000000JSONObject.put("zhyl6000006", zhyl6000006Json);
                    }


                    JSONArray zhyl6000007Json = new JSONArray(); //血生化
                    commonjiancha(s, zhyl6000007Json, xueshenghua2, zhyl60000000JSONObject, "zhyl6000007", connection, statement, resultSet);

                    JSONArray zhyl6000008Json = new JSONArray(); //免疫球蛋白
                    commonjiancha(s, zhyl6000008Json, mianyiqiudanbai2, zhyl60000000JSONObject, "zhyl6000008", connection, statement, resultSet);

                    JSONArray zhyl6000009Json = new JSONArray(); //血补体
                    commonjiancha(s, zhyl6000009Json, xuebuti2, zhyl60000000JSONObject, "zhyl6000009", connection, statement, resultSet);

                    JSONArray zhyl6000010Json = new JSONArray(); //感染筛查
                    commonjiancha(s, zhyl6000010Json, ganranshuaicha2, zhyl60000000JSONObject, "zhyl6000010", connection, statement, resultSet);

                    JSONArray zhyl6000011Json = new JSONArray(); //TORCH
                    commonjiancha(s, zhyl6000011Json, TORCH2, zhyl60000000JSONObject, "zhyl6000011", connection, statement, resultSet);

                    JSONArray zhyl6000013Json = new JSONArray(); //超声心动
                    commonjiancha(s, zhyl6000013Json, chaoshenxindong2, zhyl60000000JSONObject, "zhyl6000013", connection, statement, resultSet);

                    JSONArray zhyl6000014Json = new JSONArray(); //腹部B超
                    commonjiancha(s, zhyl6000014Json, fububichao2, zhyl60000000JSONObject, "zhyl6000014", connection, statement, resultSet);

                    //JSONObject zhyl6000015JSONObject = new JSONObject(); //肾活检
                    JSONArray zhyl6100055JSONArray = new JSONArray(); //免疫荧光-光镜
                    JSONArray zhyl6100063JSONArray = new JSONArray(); //电镜

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
//                    zhyl6000000JSONObject.put("zhyl6000015", zhyl6000015JSONObject);

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

                    JSONArray zhyl6000030Json = new JSONArray(); //24小时尿钙体重比
                    commonjiancha(s, zhyl6000030Json, niaogaitizhong2, zhyl60000000JSONObject, "zhyl6000030", connection, statement, resultSet);


                    JSONArray zhyl6000021Json = new JSONArray(); //血常规
                    commonjiancha(s, zhyl6000021Json, xuechanggui2, zhyl60000000JSONObject, "zhyl6000021", connection, statement, resultSet);

                    JSONArray zhyl6000022Json = new JSONArray(); //尿钙/肌酐比
                    commonjiancha(s, zhyl6000022Json, niaogaijigan2, zhyl60000000JSONObject, "zhyl6000022", connection, statement, resultSet);

                    JSONArray zhyl6000023Json = new JSONArray(); //24小时尿电解质
                    String niaodianjiezhi24h42Sql = niaodianjiezhi24h2.replace("?", s);
                    List<Map<String, Object>> niaodianjiezhi24h42SqlList = commonExecute2(connection, niaodianjiezhi24h42Sql, statement, resultSet);
                    if (niaodianjiezhi24h42SqlList != null) {
                        for (Map<String, Object> map1 : niaodianjiezhi24h42SqlList) {
                            if (map1 != null && map1.size() > 0) {
                                JSONObject zhyl6000023Object = new JSONObject();

                                List<String> nameList = new ArrayList<>();
                                List<String> valueList = new ArrayList<>();
                                for (String key : map1.keySet()) {
                                    if (key.toUpperCase().equals("HETERM") && map1.get(key).toString().contains(",")) {
                                        nameList = Arrays.asList(map1.get(key).toString().split(","));
                                    }
                                    if (key.toUpperCase().equals("VALUE") && map1.get(key).toString().contains(",")) {
                                        valueList = Arrays.asList(map1.get(key).toString().split(","));
                                    }
                                    if (key.toUpperCase().equals("HEHOSP")) {
                                        zhyl6000023Object.put("zhyl60000232", map1.get(key));
                                    }
                                    if (key.equals("zhyl60000231")) {
                                        zhyl6000023Object.put("zhyl60000231", map1.get(key));
                                    }
                                }
                                if (nameList.size() > 0) {
                                    for (int i = 0; i < nameList.size(); i++) {
                                        if (nameList.get(i).contains("24h尿钙") && !nameList.get(i).contains("24h尿钙体重比")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000023Object.put("zhyl60000233", valueList.get(i));
                                            } else {
                                                zhyl6000023Object.put("zhyl60000233", null);
                                            }
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
                                                zhyl6000023Object.put("zhyl60000235", valueList.get(i));
                                            } else {
                                                zhyl6000023Object.put("zhyl60000235", null);
                                            }
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

//----------------
                    //  zhyl6000000Json.add(zhyl60000000JSONObject);
                    zhyl6000000JSONObject.put("zhyl60000000", zhyl60000000JSONObject);
                    jsonObject.put("zhyl6000000", zhyl6000000JSONObject);

//-----------------------------------结局----------------------------------------------------
                    commonjiancha1(s, jieju2, jsonObject, "zhyl9000000", connection, statement, resultSet, Arrays.asList("zhyl9200002", "zhyl9200014"));


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


//---------------------------------------随访可能有多个，现在全部只取一个------------------------------------------------
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
                            if (suifangtimeList != null && suifangtimeList.size() > 0) {
                                zhyl80000001Json.put("zhyl8000001", suifangtimeList.get(0));
                            }

                            //--------体格检查 每次随访可能有多个---------
                            //  JSONArray zhyl8000002Json = new JSONArray();//由列表改为单个
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
                                        zhyl80000001Json.put("zhyl8000002", yaoWuMap);
                                    }
                                }

                            }


                            //-------血生化 每次随访可能有多个---------
                            JSONArray zhyl8000003Json = new JSONArray();
                            commonjiancha1(suifang, suifangxueshenghua2, zhyl80000001Json, "zhyl8000003", connection, statement, resultSet, null);

                            //-------尿常规 每次随访可能有多个---------
                            JSONArray zhyl8000004Json = new JSONArray();
                            commonjiancha1(suifang, suifangniaochanggui2, zhyl80000001Json, "zhyl8000004", connection, statement, resultSet, null);

                            //-------尿蛋白/肌酐 每次随访可能有多个---------
                            JSONArray zhyl8000005Json = new JSONArray();
                            commonjiancha1(suifang, suifangniaodanbaijigan2, zhyl80000001Json, "zhyl8000005", connection, statement, resultSet, null);

                            //-------24小时尿蛋白定量 每次随访可能有多个---------
                            JSONArray zhyl8000006Json = new JSONArray();
                            commonjiancha1(suifang, suifang24niaodanbai2, zhyl80000001Json, "zhyl8000006", connection, statement, resultSet, null);

                            //-------24h肌酐清除率 每次随访可能有多个---------
                            //  JSONArray zhyl8000007Json = new JSONArray(); //24h肌酐清除率
                            String suifang24jiganqingchu2Sql = suifang24jiganqingchu2.replace("?", suifang);
                            List<Map<String, Object>> suifang24jiganqingchu2SqlList = commonExecute2(connection, suifang24jiganqingchu2Sql, statement, resultSet);
                            if (suifang24jiganqingchu2SqlList != null) {
                                for (Map<String, Object> map1 : suifang24jiganqingchu2SqlList) {
                                    if (map1 != null && map1.size() > 0) {
                                        JSONObject zhyl8000007JsonObject = new JSONObject();
                                        List<String> nameList = new ArrayList<>();
                                        List<String> valueList = new ArrayList<>();
                                        for (String key : map1.keySet()) {
                                            if (key.toUpperCase().equals("HETERM") && map1.get(key).toString().contains(",")) {
                                                nameList = Arrays.asList(map1.get(key).toString().split(","));
                                            }
                                            if (key.toUpperCase().equals("VALUE") && map1.get(key).toString().contains(",")) {
                                                valueList = Arrays.asList(map1.get(key).toString().split(","));
                                            }
                                            if (key.equals("zhyl8100018")) {
                                                zhyl8000007JsonObject.put("zhyl8100018", map1.get(key));
                                            }
                                        }
                                        if (nameList.size() > 0) {
                                            for (int i = 0; i < nameList.size(); i++) {
                                                if (nameList.get(i).contains("24hCCr") && !nameList.get(i).contains("校正24hCCr")) {
                                                    if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                        zhyl8000007JsonObject.put("zhyl8100019", valueList.get(i));
                                                    } else {
                                                        zhyl8000007JsonObject.put("zhyl8100019", null);
                                                    }
                                                }
                                                if (nameList.get(i).contains("身高")) {
                                                    if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                        zhyl8000007JsonObject.put("zhyl8100020", valueList.get(i));
                                                    } else {
                                                        zhyl8000007JsonObject.put("zhyl8100020", null);
                                                    }
                                                }
                                                if (nameList.get(i).contains("体重")) {
                                                    if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                        zhyl8000007JsonObject.put("zhyl8100021", valueList.get(i));
                                                    } else {
                                                        zhyl8000007JsonObject.put("zhyl8100021", null);
                                                    }
                                                }
                                                if (nameList.get(i).contains("体表面积")) {
                                                    if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                        zhyl8000007JsonObject.put("zhyl8100022", valueList.get(i));
                                                    } else {
                                                        zhyl8000007JsonObject.put("zhyl8100022", null);
                                                    }
                                                }
                                                if (nameList.get(i).contains("校正24hCCr")) {
                                                    if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                        zhyl8000007JsonObject.put("zhyl8100023", valueList.get(i));
                                                    } else {
                                                        zhyl8000007JsonObject.put("zhyl8100023", null);
                                                    }
                                                }
                                            }
                                        }
                                        // zhyl8000007Json.add(zhyl8000007JsonObject);
                                        zhyl80000001Json.put("zhyl8000007", zhyl8000007JsonObject);
                                    }
                                }
                            }


                            //-------肾脏早起损伤指标每次随访可能有多个---------
                            JSONArray zhyl8000008Json = new JSONArray();
                            commonjiancha1(suifang, suifangshenzaoshuai2, zhyl80000001Json, "zhyl8000008", connection, statement, resultSet, null);

                            //-------泌尿系超声 每次随访可能有多个---------
                            JSONArray zhyl8000009Json = new JSONArray();
                            if (type.equals("2")) {//蛋白尿
                                commonjiancha1(suifang, suifangchaoshen2, zhyl80000001Json, "zhyl8000009", connection, statement, resultSet, null);
                            }
                            if (type.equals("4")) {//肾囊肿
                                commonjiancha1(suifang, suifangchaoshen4, zhyl80000001Json, "zhyl8000009", connection, statement, resultSet, null);
                            }
                            //-------肝、胆影像学检查每次随访可能有多个---------
                            JSONArray zhyl8000010Json = new JSONArray();
                            commonjiancha1(suifang, gandanyingxiangxue4, zhyl80000001Json, "zhyl8000010", connection, statement, resultSet, null);


                            //-------用药 每次随访可能有多个---------
                            JSONObject zhyl80001001Json = new JSONObject();
                            JSONArray zhyl80001002JsonArray = new JSONArray(); //
                            String suifangyongyao2Sql = suifangyongyao2.replace("?", suifang);
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


                        jsonObject.put("zhyl8000000", zhyl8000000Json);
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
        } catch (SQLException e) {
            log.error("Error closing resources: " + e, "");
        }

//        LocalDateTime now1 = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String timeString = now1.format(formatter);
//        System.out.println("-----运行结束时间为：------" + timeString);

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
                        if (map.get(list) != null && map.get(list).toString().contains(",")) {
                            map.put(list, Arrays.asList(map.get(list).toString().split(",")));
                        }
                    }

                }
                jsonObject.put(biaoti, map);
            } else {
                jsonObject.put(biaoti, xueshenghua2SqlList.get(0));
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

                        if (key.toUpperCase().equals("FHTERM") && muqinMap.get(key).toString().contains(",")) {
                            nameList = Arrays.asList(muqinMap.get(key).toString().split(","));
                        }
                        if (key.toUpperCase().equals("FHTERM") && !muqinMap.get(key).toString().contains(",")) {
                            nameList = Collections.singletonList(muqinMap.get(key).toString());
                        }
                        if (key.toUpperCase().equals("FHOCCUR") && muqinMap.get(key).toString().contains(",")) {
                            isNotList = Arrays.asList(muqinMap.get(key).toString().split(","));
                        }
                        if (key.toUpperCase().equals("FHOCCUR") && !muqinMap.get(key).toString().contains(",")) {
                            isNotList = Collections.singletonList(muqinMap.get(key).toString());
                        }
                        if (key.toUpperCase().equals("AGE") && muqinMap.get(key).toString().contains(",")) {
                            ageList = Arrays.asList(muqinMap.get(key).toString().split(","));
                        }
                        if (key.toUpperCase().equals("AGE") && !muqinMap.get(key).toString().contains(",")) {
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
