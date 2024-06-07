package com.igA.demo.data;

import com.igA.demo.utils.JDBCUtils;
import com.igA.demo.utils.ResultSetUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.json.JSONException;

import static com.igA.demo.constant.DataTransform.*;
import static com.igA.demo.constant.PediatricKidneyDatabaseConstant2.*;
import static com.igA.demo.data.igAData.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogChannelFactory;

//遗传病-早发蛋白尿数据

@Slf4j
public class PediatricKidneyData2 {

    public static void transformData(String baseUrl, String admin, String password, String id) throws Exception {

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
                idList = commonExecute(connection, statement, resultSet, KidneyIdSql2);
            }

            if (idList != null && idList.size() > 0) {
                for (String s : idList) {
                    //--------------------------------------一般资料------------------------------------

                    JSONObject jsonObject = new JSONObject();   //----总的大json,每个患者一个json

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
                        map.remove("zhyl1110028");
                        map.remove("zhyl1110029");
                        map.remove("zhyl1110030");


                        map.put("zhyl1100022", zhyl1100022Json);
                        map.put("zhyl1100027", zhyl1100027Json);


                        JSONObject zhyl1000000 = new JSONObject();

                        zhyl1000000.put("zhyl1100000", map);
                        jsonObject.put("zhyl100000000", zhyl1000000);   //一般资料zhyl1000000
                    }


//----------------------------------------------现病史-------------------------------------------------
                    String xianbingshi = xianbingshi2.replace("?", s); //现病史
                    Map<String, Object> xianbingshiMap = commonExecute1(connection, xianbingshi, statement, resultSet);
                    jsonObject.put("zhyl2000000", xianbingshiMap);

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

                    JSONObject zhyl4000001Json = new JSONObject();//母亲
                    JSONObject zhyl4000002Json = new JSONObject();//父亲
                    JSONArray zhyl4000003JsonArray = new JSONArray(); //兄弟姐妹
                    JSONArray zhyl4000004JsonArray = new JSONArray(); //父母辈亲属
                    JSONArray zhyl4000005JsonArray = new JSONArray(); //（外）祖父母
                    JSONArray zhyl4000006JsonArray = new JSONArray(); //其他

                    jiazushi(zhyl4000000Json, s, zhyl4000001Json, jiazhushi2, "母亲", connection, statement, resultSet, "zhyl4000001", "zhyl4200000", "zhyl4200001",
                            "zhyl4200002", "zhyl4200003", "zhyl4200004", "zhyl4200005", "zhyl4200006", "zhyl4200007",
                            "zhyl4200008", "zhyl4200009", "zhyl4100006", null, "zhyl4200060","zhyl4200066","zhyl4200067");//母亲家族史
                    jiazushi(zhyl4000000Json, s, zhyl4000002Json, jiazhushi2, "父亲", connection, statement, resultSet, "zhyl4000002", "zhyl4200010", "zhyl4200011",
                            "zhyl4200012", "zhyl4200013", "zhyl4200014", "zhyl4200015", "zhyl4200016", "zhyl4200017",
                            "zhyl4200018", "zhyl4200019", "zhyl4100012", null, "zhyl4200061","zhyl4200068","zhyl4200069");//父亲家族史

                                List<String> xiongdi = Arrays.asList("哥哥", "弟弟", "姐姐", "妹妹");
                        for (String x : xiongdi) {
                            JSONObject zhyl4000003Json = new JSONObject();//兄弟姐妹
                            if (x.equals("哥哥")) {
                                zhyl4000003Json.put("zhyl4100030", "1");
                            }
                            if (x.equals("弟弟")) {
                                zhyl4000003Json.put("zhyl4100030", "2");
                            }
                            if (x.equals("姐姐")) {
                                zhyl4000003Json.put("zhyl4100030", "3");
                            }
                            if (x.equals("妹妹")) {
                                zhyl4000003Json.put("zhyl4100030", "4");
                            }
                            jiazushi(zhyl4000000Json, s, zhyl4000003Json, jiazhushi2, x, connection, statement, resultSet, "zhyl4000003", "zhyl4200020", "zhyl4200021",
                                    "zhyl4200022", "zhyl4200023", "zhyl4200024", "zhyl4200025", "zhyl4200026", "zhyl4200027",
                                    "zhyl4200028", "zhyl4200029", "zhyl4100018", zhyl4000003JsonArray, "zhyl4200062","zhyl4200070","zhyl4200071");//兄弟姐妹
                    }

                    List<String> zhangbei = Arrays.asList("母亲的弟弟", "母亲的哥哥", "母亲的姐姐", "母亲的妹妹", "父亲的姐姐", "父亲的妹妹", "父亲的弟弟", "父亲的哥哥");
                    for (String x : zhangbei) {
                        JSONObject zhyl4000004Json = new JSONObject();//父母辈亲属
                        if (x.equals("母亲的弟弟") || x.equals("母亲的哥哥")) {
                            zhyl4000004Json.put("zhyl4100031", "1");
                        }
                        if (x.equals("母亲的姐姐") || x.equals("母亲的妹妹")) {
                            zhyl4000004Json.put("zhyl4100031", "2");
                        }
                        if (x.equals("父亲的姐姐") || x.equals("父亲的妹妹")) {
                            zhyl4000004Json.put("zhyl4100031", "3");
                        }
                        if (x.equals("父亲的弟弟")) {
                            zhyl4000004Json.put("zhyl4100031", "4");
                        }
                        if (x.equals("父亲的哥哥")) {
                            zhyl4000004Json.put("zhyl4100031", "5");
                        }
                        jiazushi(zhyl4000000Json, s, zhyl4000004Json, jiazhushi2, x, connection, statement, resultSet, "zhyl4000004", "zhyl4200030", "zhyl4200031",
                                "zhyl4200032", "zhyl4200033", "zhyl4200034", "zhyl4200035", "zhyl4200036", "zhyl4200037",
                                "zhyl4200038", "zhyl4200039", "zhyl4100038", zhyl4000004JsonArray, "zhyl4200063","zhyl4200072","zhyl4200073");//父母辈亲属
                    }

                    List<String> zufu = Arrays.asList("祖父", "外祖父", "祖母", "外祖母");
                    for (String x : zufu) {
                        JSONObject zhyl4000005Json = new JSONObject();//（外）祖父母
                        if (x.equals("祖父")) {
                            zhyl4000005Json.put("zhyl4100032", "3");
                        }
                        if (x.equals("外祖父")) {
                            zhyl4000005Json.put("zhyl4100032", "1");
                        }
                        if (x.equals("祖母")) {
                            zhyl4000005Json.put("zhyl4100032", "4");
                        }
                        if (x.equals("外祖母")) {
                            zhyl4000005Json.put("zhyl4100032", "2");
                        }

                        jiazushi(zhyl4000000Json, s, zhyl4000005Json, jiazhushi2, x, connection, statement, resultSet, "zhyl4000005", "zhyl4200040", "zhyl4200041",
                                "zhyl4200042", "zhyl4200043", "zhyl4200044", "zhyl4200045", "zhyl4200046", "zhyl4200047",
                                "zhyl4200048", "zhyl4200049", "zhyl4100029", zhyl4000005JsonArray, "zhyl4200064","zhyl4200074","zhyl4200075");//（外）祖父母
                    }

                    List<String> other = Arrays.asList(
                            "父亲的姐姐的儿子",
                            "母亲的姐姐的儿子",
                            "母亲的弟弟的女儿",
                            "母亲的妹妹的儿子",
                            "母亲的姐姐的女儿",
                            "外祖母的弟弟",
                            "外祖母的妹妹",
                            "母亲的妹妹的女儿",
                            "父亲的妹妹",
                            "父亲的姐姐的女儿",
                            "外祖母的母亲",
                            "母亲的弟弟的儿子",
                            "外祖母的姐姐",
                            "外祖父的哥哥",
                            "患者儿子",
                            "父亲的哥哥的女儿",
                            "祖母的弟弟",
                            "外祖母的父亲",
                            "外祖母的哥哥",
                            "母亲的哥哥的儿子",
                            "祖母的哥哥",
                            "祖母的妹妹",
                            "祖母的姐姐",
                            "父亲的哥哥的儿子",
                            "祖母的父亲",
                            "祖父母",
                            "母亲的兄弟姐妹",
                            "患糖尿病亲属",
                            "兄弟姐妹",
                            "其他有肾脏尿路畸形亲属",
                            "外祖父母",
                            "父亲的兄弟姐妹",
                            "外祖父的父亲",
                            "姐姐的儿子",
                            "患者女儿",
                            "外祖父的弟弟",
                            "母亲的哥哥的女儿",
                            "外祖父的姐姐",
                            "外祖父的母亲",
                            "父亲的妹妹的儿子",
                            "外祖父的妹妹",
                            "祖母的母亲",
                            "祖父的哥哥",
                            "祖父的姐姐",
                            "祖父的父亲",
                            "祖父的弟弟",
                            "祖父的妹妹",
                            "母亲的姐姐的配偶",
                            "父亲的妹妹的女儿",
                            "父亲的哥哥的配偶",
                            "父亲的弟弟的女儿",
                            "父亲的弟弟的配偶",
                            "父亲的弟弟的儿子",
                            "祖母的哥哥的配偶",
                            "外祖父的哥哥的配偶",
                            "哥哥的儿子",
                            "祖父的哥哥的配偶",
                            "父亲的姐姐的配偶"); //其它
                    for (String x : other) {
                        JSONObject zhyl4000006Json = new JSONObject();
                        zhyl4000006Json.put("zhyl4100039", x);
                        zhyl4000006Json.put("zhyl4100033", "1");
                        jiazushi(zhyl4000000Json, s, zhyl4000006Json, jiazhushi2, x, connection, statement, resultSet, "zhyl4000006", "zhyl4200050", "zhyl4200051",
                                "zhyl4200052", "zhyl4200053", "zhyl4200054", "zhyl4200055", "zhyl4200056", "zhyl4200057",
                                "zhyl4200058", "zhyl4200059", "zhyl4100040", zhyl4000006JsonArray, "zhyl4200065","zhyl4200076","zhyl4200077");//其它
                    }


                    jsonObject.put("zhyl4000000", zhyl4000000Json);


//-------------------------------------------体格检查--------------------------------------------
                    JSONArray zhyl5000000Json = new JSONArray(); //
                    String tigejianchaSql = tigejiancha2.replace("?", s);
                    List<Map<String, Object>> tigeList = commonExecute2(connection, tigejianchaSql, statement, resultSet);//有多条
                    if (tigeList != null && tigeList.size() > 0) {
                        for (Map<String, Object> yaoWuMap : tigeList) {
                            if (yaoWuMap != null && yaoWuMap.size() > 0) {
                                JSONObject zhyl5000022Json = new JSONObject();
                                for (String key : yaoWuMap.keySet()) {
                                    transJson(yaoWuMap, key, "zhyl5100022", zhyl5000022Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100023", zhyl5000022Json, null);

                                }
                                yaoWuMap.remove("zhyl5100022");
                                yaoWuMap.remove("zhyl5100023");

                                yaoWuMap.put("zhyl5000022", zhyl5000022Json);
                                zhyl5000000Json.add(yaoWuMap);
                            }
                        }

                    }
                    jsonObject.put("zhyl5000000", zhyl5000000Json);
//-------------------------------------------辅助检查--------------------------------------------
                    JSONArray zhyl6000000Json = new JSONArray(); //
                    JSONObject zhyl6000000JSONObject = new JSONObject();

                    JSONArray zhyl6000001Json = new JSONArray(); //尿常规
                    String niaochangguiSql = niaochanggui2.replace("?", s);
                    List<Map<String, Object>> niaochangguiList = commonExecute2(connection, niaochangguiSql, statement, resultSet);//有多条
                    if (niaochangguiList != null) {
                        zhyl6000001Json.addAll(niaochangguiList);
                        zhyl6000000JSONObject.put("zhyl6000001", zhyl6000001Json);
                    }

                    JSONArray zhyl6000002Json = new JSONArray(); //尿蛋白肌酐比
                    String niaodanbaijiganSql = niaodanbaijigan2.replace("?", s);
                    List<Map<String, Object>> niaodanbaijiganSqlList = commonExecute2(connection, niaodanbaijiganSql, statement, resultSet);
                    if (niaodanbaijiganSqlList != null) {
                        zhyl6000002Json.addAll(niaodanbaijiganSqlList);
                        zhyl6000000JSONObject.put("zhyl6000002", zhyl6000002Json);
                    }

                    JSONArray zhyl6000003Json = new JSONArray(); //24h尿蛋白定量
                    String niaodanbaidingliang24h2Sql = niaodanbaidingliang24h2.replace("?", s);
                    List<Map<String, Object>> niaodanbaidingliang24h2SqlList = commonExecute2(connection, niaodanbaidingliang24h2Sql, statement, resultSet);
                    if (niaodanbaidingliang24h2SqlList != null) {
                        zhyl6000003Json.addAll(niaodanbaidingliang24h2SqlList);
                        zhyl6000000JSONObject.put("zhyl6000003", zhyl6000003Json);
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
                        zhyl6000000JSONObject.put("zhyl6000004", zhyl6000004Json);
                    }

                    JSONArray zhyl6000005Json = new JSONArray(); //肾早损
                    String shenzaosuai2Sql = shenzaosuai2.replace("?", s);
                    List<Map<String, Object>> shenzaosuai2SqlList = commonExecute2(connection, shenzaosuai2Sql, statement, resultSet);
                    if (shenzaosuai2SqlList != null) {
                        zhyl6000005Json.addAll(shenzaosuai2SqlList);
                        zhyl6000000JSONObject.put("zhyl6000005", zhyl6000005Json);
                    }

                    JSONArray zhyl6000006Json = new JSONArray(); //尿蛋白电泳
                    String niaodanbaidianyong2Sql = niaodanbaidianyong2.replace("?", s);
                    List<Map<String, Object>> niaodanbaidianyong2SqlList = commonExecute2(connection, niaodanbaidianyong2Sql, statement, resultSet);
                    if (niaodanbaidianyong2SqlList != null) {
                        zhyl6000006Json.addAll(niaodanbaidianyong2SqlList);
                        zhyl6000000JSONObject.put("zhyl6000006", zhyl6000006Json);
                    }


                    JSONArray zhyl6000007Json = new JSONArray(); //血生化
                    commonjiancha(s, zhyl6000007Json, xueshenghua2, zhyl6000000JSONObject, "zhyl6000007", connection, statement, resultSet);

                    JSONArray zhyl6000008Json = new JSONArray(); //免疫球蛋白
                    commonjiancha(s, zhyl6000008Json, mianyiqiudanbai2, zhyl6000000JSONObject, "zhyl6000008", connection, statement, resultSet);

                    JSONArray zhyl6000009Json = new JSONArray(); //血补体
                    commonjiancha(s, zhyl6000009Json, xuebuti2, zhyl6000000JSONObject, "zhyl6000009", connection, statement, resultSet);

                    JSONArray zhyl6000010Json = new JSONArray(); //感染筛查
                    commonjiancha(s, zhyl6000010Json, ganranshuaicha2, zhyl6000000JSONObject, "zhyl6000010", connection, statement, resultSet);

                    JSONArray zhyl6000011Json = new JSONArray(); //TORCH
                    commonjiancha(s, zhyl6000011Json, TORCH2, zhyl6000000JSONObject, "zhyl6000011", connection, statement, resultSet);

                    JSONArray zhyl6000013Json = new JSONArray(); //超声心动
                    commonjiancha(s, zhyl6000013Json, chaoshenxindong2, zhyl6000000JSONObject, "zhyl6000013", connection, statement, resultSet);

                    JSONArray zhyl6000014Json = new JSONArray(); //腹部B超
                    commonjiancha(s, zhyl6000014Json, fububichao2, zhyl6000000JSONObject, "zhyl6000014", connection, statement, resultSet);

                    JSONObject zhyl6000015JSONObject = new JSONObject(); //肾活检
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
                                    transJson(guangjingMap, key, "zhyl6200002", zhyl6100061Json, null);
                                    transJson(guangjingMap, key, "zhyl6200003", zhyl6100061Json, null);
                                    if (key.equals("zhyl6200004")) {
                                        transformQiangdu(zhyl6100061Json, guangjingMap.get(key), "zhyl6200004");

                                    }
                                    transJson(guangjingMap, key, "zhyl6200005", zhyl6100061Json, null);
                                    transJson(guangjingMap, key, "zhyl6200006", zhyl6100061Json, null);
                                    if (key.equals("zhyl6200007")) {
                                        transformQiangdu(zhyl6100061Json, guangjingMap.get(key), "zhyl6200007");

                                    }
                                    transJson(guangjingMap, key, "zhyl6200008", zhyl6100061Json, null);
                                    transJson(guangjingMap, key, "zhyl6200009", zhyl6100061Json, null);
                                    if (key.equals("zhyl6200010")) {
                                        transformQiangdu(zhyl6100061Json, guangjingMap.get(key), "zhyl6200010");

                                    }
                                    transJson(guangjingMap, key, "zhyl6200011", zhyl6100061Json, null);
                                    transJson(guangjingMap, key, "zhyl6200012", zhyl6100061Json, null);
                                    if (key.equals("zhyl6200013")) {
                                        transformQiangdu(zhyl6100061Json, guangjingMap.get(key), "zhyl6200013");

                                    }
                                    transJson(guangjingMap, key, "zhyl6200014", zhyl6100061Json, null);
                                    transJson(guangjingMap, key, "zhyl6200015", zhyl6100061Json, null);
                                    if (key.equals("zhyl6200016")) {
                                        transformQiangdu(zhyl6100061Json, guangjingMap.get(key), "zhyl6200016");

                                    }
                                    transJson(guangjingMap, key, "zhyl6200017", zhyl6100061Json, null);
                                    transJson(guangjingMap, key, "zhyl6200018", zhyl6100061Json, null);

                                    transJson(guangjingMap, key, "zhyl6200019", zhyl6100062Json, null);
                                    transJson(guangjingMap, key, "zhyl6200020", zhyl6100062Json, null);
                                    transJson(guangjingMap, key, "zhyl6200021", zhyl6100062Json, null);
                                    transJson(guangjingMap, key, "zhyl6200022", zhyl6100062Json, null);

                                }
                                zhyl6100055Json.put("zhyl6100061", zhyl6100061Json);
                                zhyl6100055Json.put("zhyl6100062", zhyl6100062Json);
                                zhyl6100055JSONArray.add(zhyl6100055Json);
                            }
                            zhyl6000015JSONObject.put("zhyl6100055", zhyl6100055JSONArray);

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
                            zhyl6000015JSONObject.put("zhyl6100063", zhyl6100063JSONArray);
                        }
                    }
                    zhyl6000000JSONObject.put("zhyl6000015", zhyl6000015JSONObject);

                    JSONArray zhyl6000016Json = new JSONArray(); //纯音测听
                    commonjiancha(s, zhyl6000016Json, chunyinceting2, zhyl6000000JSONObject, "zhyl6000016", connection, statement, resultSet);

                    JSONArray zhyl6000017Json = new JSONArray(); //眼裂隙灯检查
                    commonjiancha(s, zhyl6000017Json, yanliedeng2, zhyl6000000JSONObject, "zhyl6000017", connection, statement, resultSet);

                    JSONArray zhyl6000018Json = new JSONArray(); //其他检查
                    commonjiancha(s, zhyl6000018Json, qitajiancha2, zhyl6000000JSONObject, "zhyl6000018", connection, statement, resultSet);

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
                                    transJson(jiyinMap, key, "zhyl6100090", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100091", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100092", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100093", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100094", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100095", zhyl6000019JSONObject, null);
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
                        zhyl6000000JSONObject.put("zhyl6000019", zhyl6000019Json);
                    }


                    JSONArray zhyl6000020Json = new JSONArray(); //最终诊断
                    commonjiancha(s, zhyl6000020Json, zuizhongzhenduan2, zhyl6000000JSONObject, "zhyl6000020", connection, statement, resultSet);

//----------------
                    zhyl6000000Json.add(zhyl6000000JSONObject);
                    jsonObject.put("zhyl6000000", zhyl6000000Json);

//-----------------------------------结局----------------------------------------------------
                    JSONArray zhyl9000000JSONArray = new JSONArray();
                    commonjiancha(s, zhyl9000000JSONArray, jieju2, jsonObject, "zhyl9000000", connection, statement, resultSet);


//-----------------------------------------用药----------------------------------------------
                    JSONArray zhyl7000000Json = new JSONArray(); //用药
                    commonjiancha(s, zhyl7000000Json, yongyao2, jsonObject, "zhyl7000000", connection, statement, resultSet);
//---------------------------------------随访------------------------------------------------
                    JSONArray zhyl8000000JSONArray = new JSONArray(); //总随访

                    String suifangIdSql = suifang2.replace("?", s);
                    List<String> suifangIdList = commonExecute(connection, statement, resultSet, suifangIdSql);//每个dmid对应多个随访id
                    if (suifangIdList != null) {

                        for (String suifang : suifangIdList) {  //每个随访id

                            JSONObject zhyl8000000Json = new JSONObject();//对于每次随访
                            //--------随访时间----------
                            String suifangtimeSql = suifangtime.replace("?", suifang);
                            List<String> suifangtimeList = commonExecute(connection, statement, resultSet, suifangtimeSql);//
                            if (suifangtimeList != null && suifangtimeList.size() > 0) {
                                zhyl8000000Json.put("zhyl8000001", suifangtimeList.get(0));
                            }

                            //--------体格检查 每次随访可能有多个---------
                            JSONArray zhyl8000002Json = new JSONArray();
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
                                        zhyl8000002Json.add(yaoWuMap);
                                    }
                                }

                            }
                            zhyl8000000Json.put("zhyl8000002", zhyl8000002Json);

                            //-------血生化 每次随访可能有多个---------
                            JSONArray zhyl8000003Json = new JSONArray();
                            commonjiancha(suifang, zhyl8000003Json, suifangxueshenghua2, zhyl8000000Json, "zhyl8000003", connection, statement, resultSet);

                            //-------尿常规 每次随访可能有多个---------
                            JSONArray zhyl8000004Json = new JSONArray();
                            commonjiancha(suifang, zhyl8000004Json, suifangniaochanggui2, zhyl8000000Json, "zhyl8000004", connection, statement, resultSet);

                            //-------尿蛋白/肌酐 每次随访可能有多个---------
                            JSONArray zhyl8000005Json = new JSONArray();
                            commonjiancha(suifang, zhyl8000005Json, suifangniaodanbaijigan2, zhyl8000000Json, "zhyl8000005", connection, statement, resultSet);

                            //-------24小时尿蛋白定量 每次随访可能有多个---------
                            JSONArray zhyl8000006Json = new JSONArray();
                            commonjiancha(suifang, zhyl8000006Json, suifang24niaodanbai2, zhyl8000000Json, "zhyl8000006", connection, statement, resultSet);

                            //-------24h肌酐清除率 每次随访可能有多个---------
                            JSONArray zhyl8000007Json = new JSONArray(); //24h肌酐清除率
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
                                        zhyl8000007Json.add(zhyl8000007JsonObject);
                                    }
                                }
                            }
                            zhyl8000000Json.put("zhyl8000007", zhyl8000007Json);

                            //-------肾脏早起损伤指标每次随访可能有多个---------
                            JSONArray zhyl8000008Json = new JSONArray();
                            commonjiancha(suifang, zhyl8000008Json, suifangshenzaoshuai2, zhyl8000000Json, "zhyl8000008", connection, statement, resultSet);

                            //-------泌尿系超声 每次随访可能有多个---------
                            JSONArray zhyl8000009Json = new JSONArray();
                            commonjiancha(suifang, zhyl8000009Json, suifangchaoshen2, zhyl8000000Json, "zhyl8000009", connection, statement, resultSet);

                            //-------用药 每次随访可能有多个---------
                            JSONArray zhyl8000010Json = new JSONArray(); //随访的药就是用药里面的，所以用s，不用随访
                            commonjiancha(s, zhyl8000010Json, suifangyongyao2, zhyl8000000Json, "zhyl8000010", connection, statement, resultSet);


                            zhyl8000000JSONArray.add(zhyl8000000Json);
                        }


                        jsonObject.put("zhyl8000000", zhyl8000000JSONArray);
                    }


//---------------------------------------------------------------------------------------
                    ObjectMapper mapper = new ObjectMapper();    //为了让json中的字段有序
                    mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
                    String jsonStr = mapper.writeValueAsString(jsonObject);
                    System.out.println(jsonStr);


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

    public static void jiazushi(JSONObject zhyl4000000Json, String s, JSONObject kaitouJson, String sql, String jiazuguanxi, Connection connection, Statement statement, ResultSet resultSet,
                                 String kaitou, String danbainiao, String danbainiaonianling, String shengongneng, String shengongnengnianling,
                                 String tingli, String tinglinianling, String yanbu, String yanbulianling, String xueniao,
                                 String xueniaonianling, String qita, JSONArray jsonArray, String shengcunzhuangtai,String shennangzhong,String shennangzhonglianling) throws Exception {
        String muqin = sql.replace("?", s).replace("#", jiazuguanxi);//母亲家族史
        Map<String, Object> muqinMap = commonExecute1(connection, muqin, statement, resultSet);

        if (muqinMap != null && muqinMap.size() > 0) {
            List<String> nameList = new ArrayList<>();
            List<String> isNotList = new ArrayList<>();
            List<String> ageList = new ArrayList<>();
            for (String key : muqinMap.keySet()) {
                if (key.toUpperCase().equals("FHTERM") && muqinMap.get(key).toString().contains(",")) {
                    nameList = Arrays.asList(muqinMap.get(key).toString().split(","));
                }
                if (key.toUpperCase().equals("FHOCCUR") && muqinMap.get(key).toString().contains(",")) {
                    isNotList = Arrays.asList(muqinMap.get(key).toString().split(","));
                }
                if (key.toUpperCase().equals("AGE") && muqinMap.get(key).toString().contains(",")) {
                    ageList = Arrays.asList(muqinMap.get(key).toString().split(","));
                }
                if (key.toUpperCase().equals("FHORRES")) {
                    kaitouJson.put(qita, muqinMap.get(key));
                }
                if (key.toUpperCase().equals("FHSTATUS")) {
                    kaitouJson.put(shengcunzhuangtai, castDataShengCun((String) muqinMap.get(key)));
                }
                if (key.toUpperCase().equals("FHITEM")) { //有无家族史
                    zhyl4000000Json.put("zhyl4000007", castDataYinYang((String) muqinMap.get(key)));
                }
            }
            if (nameList.size() > 0) {
                for (int i = 0; i < nameList.size(); i++) {
                    String name = nameList.get(i);
                    String isNot = isNotList.get(i);
                    String age = ageList.get(i);
                    if (name.contains("蛋白尿")) {
                        kaitouJson.put(danbainiao, castData(isNot));
                        if (!age.contains("#") && age != null) {
                            kaitouJson.put(danbainiaonianling, age);
                        } else {
                            kaitouJson.put(danbainiaonianling, null);
                        }
                    }
                    if (name.contains("肾功能异常")) {
                        kaitouJson.put(shengongneng, castData(isNot));
                        if (!age.contains("#") && age != null) {
                            kaitouJson.put(shengongnengnianling, age);
                        } else {
                            kaitouJson.put(shengongnengnianling, null);
                        }
                    }
                    if (name.contains("听力异常")) {
                        kaitouJson.put(tingli, castData(isNot));
                        if (!age.contains("#") && age != null) {
                            kaitouJson.put(tinglinianling, age);
                        } else {
                            kaitouJson.put(tinglinianling, null);
                        }
                    }
                    if (name.contains("眼部症状")) {
                        kaitouJson.put(yanbu, castData(isNot));
                        if (!age.contains("#") && age != null) {
                            kaitouJson.put(yanbulianling, age);
                        } else {
                            kaitouJson.put(yanbulianling, null);
                        }
                    }
                    if (name.contains("血尿")) {
                        kaitouJson.put(xueniao, castData(isNot));
                        if (!age.contains("#") && age != null) {
                            kaitouJson.put(xueniaonianling, age);
                        } else {
                            kaitouJson.put(xueniaonianling, null);
                        }
                    }
                    if (name.contains("肾囊肿")) {
                        kaitouJson.put(shennangzhong, castData(isNot));
                        if (!age.contains("#") && age != null) {
                            kaitouJson.put(shennangzhonglianling, age);
                        } else {
                            kaitouJson.put(shennangzhonglianling, null);
                        }
                    }

                }

            }

            if (jsonArray != null) {
                jsonArray.add(kaitouJson);
                zhyl4000000Json.put(kaitou, jsonArray);
            } else {
                zhyl4000000Json.put(kaitou, kaitouJson);
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
}
