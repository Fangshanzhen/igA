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
import static com.igA.demo.constant.PediatricKidneyDatabaseConstant4.*;
import static com.igA.demo.data.PediatricKidneyData2.commonjiancha;
import static com.igA.demo.data.PediatricKidneyData2.jiazushi;
import static com.igA.demo.data.igAData.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogChannelFactory;

//遗传病-早发蛋白尿数据

@Slf4j
public class PediatricKidneyData4 {

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
                idList = commonExecute(connection, statement, resultSet, KidneyIdSql4);
            }

            if (idList != null && idList.size() > 0) {
                for (String s : idList) {
                    //--------------------------------------一般资料------------------------------------

                    JSONObject jsonObject = new JSONObject();   //----总的大json,每个患者一个json

                    Map<String, Object> map = new HashMap<>();
                    String yibanziliao = yibanziliao4.replace("?", s);  //一般资料
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
                    String xianbingshi = xianbingshi4.replace("?", s); //现病史
                    Map<String, Object> xianbingshiMap = commonExecute1(connection, xianbingshi, statement, resultSet);
                    jsonObject.put("zhyl2000000", xianbingshiMap);

//----------------------------------------------个人史---------------------------------
                    String gerrenshi = gerenshi4.replace("?", s); //现病史
                    Map<String, Object> gerrenshiMap = commonExecute1(connection, gerrenshi, statement, resultSet);
                    jsonObject.put("zhyl3000000", gerrenshiMap);
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
                            "zhyl4200008", "zhyl4200009", "zhyl4100006", null, "zhyl4200060", "zhyl4200066", "zhyl4200067");//母亲家族史
                    jiazushi(zhyl4000000Json, s, zhyl4000002Json, jiazhushi2, "父亲", connection, statement, resultSet, "zhyl4000002", "zhyl4200010", "zhyl4200011",
                            "zhyl4200012", "zhyl4200013", "zhyl4200014", "zhyl4200015", "zhyl4200016", "zhyl4200017",
                            "zhyl4200018", "zhyl4200019", "zhyl4100012", null, "zhyl4200061", "zhyl4200068", "zhyl4200069");//父亲家族史

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
                                "zhyl4200028", "zhyl4200029", "zhyl4100018", zhyl4000003JsonArray, "zhyl4200062", "zhyl4200070", "zhyl4200071");//兄弟姐妹
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
                                "zhyl4200038", "zhyl4200039", "zhyl4100038", zhyl4000004JsonArray, "zhyl4200063", "zhyl4200072", "zhyl4200073");//父母辈亲属
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
                                "zhyl4200048", "zhyl4200049", "zhyl4100029", zhyl4000005JsonArray, "zhyl4200064", "zhyl4200074", "zhyl4200075");//（外）祖父母
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
                                "zhyl4200058", "zhyl4200059", "zhyl4100040", zhyl4000006JsonArray, "zhyl4200065", "zhyl4200076", "zhyl4200077");//其它
                    }

                    jsonObject.put("zhyl4000000", zhyl4000000Json);

//-------------------------------------------体格检查--------------------------------------------
                    JSONArray zhyl5000000Json = new JSONArray(); //
                    String tigejianchaSql = tigejiancha4.replace("?", s);
                    List<Map<String, Object>> tigeList = commonExecute2(connection, tigejianchaSql, statement, resultSet);//有多条
                    if (tigeList != null && tigeList.size() > 0) {
                        for (Map<String, Object> yaoWuMap : tigeList) {
                            if (yaoWuMap != null && yaoWuMap.size() > 0) {
                                JSONObject zhyl5000022Json = new JSONObject();
                                JSONObject zhyl5000024Json = new JSONObject();
                                JSONObject zhyl5000025Json = new JSONObject();
                                for (String key : yaoWuMap.keySet()) {
                                    transJson(yaoWuMap, key, "zhyl5100022", zhyl5000022Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100023", zhyl5000022Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100024", zhyl5000024Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100025", zhyl5000024Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100026", zhyl5000025Json, null);
                                    transJson(yaoWuMap, key, "zhyl5100027", zhyl5000025Json, null);

                                }
                                yaoWuMap.remove("zhyl5100022");
                                yaoWuMap.remove("zhyl5100023");
                                yaoWuMap.remove("zhyl5100024");
                                yaoWuMap.remove("zhyl5100025");
                                yaoWuMap.remove("zhyl5100026");
                                yaoWuMap.remove("zhyl5100027");

                                yaoWuMap.put("zhyl5000022", zhyl5000022Json);
                                yaoWuMap.put("zhyl5000024", zhyl5000024Json);
                                yaoWuMap.put("zhyl5000025", zhyl5000025Json);
                                zhyl5000000Json.add(yaoWuMap);
                            }
                        }

                    }
                    jsonObject.put("zhyl5000000", zhyl5000000Json);

//-------------------------------------------辅助检查--------------------------------------------
                    JSONArray zhyl6000000Json = new JSONArray(); //
                    JSONObject zhyl6000000JSONObject = new JSONObject();

                    JSONArray zhyl6000001Json = new JSONArray(); //尿常规
                    String niaochangguiSql = niaochanggui4.replace("?", s);
                    List<Map<String, Object>> niaochangguiList = commonExecute2(connection, niaochangguiSql, statement, resultSet);//有多条
                    if (niaochangguiList != null) {
                        zhyl6000001Json.addAll(niaochangguiList);
                        zhyl6000000JSONObject.put("zhyl6000001", zhyl6000001Json);
                    }
                    JSONArray zhyl6000002Json = new JSONArray(); //尿蛋白肌酐比
                    String niaodanbaijiganSql = niaodanbaijigan4.replace("?", s);
                    List<Map<String, Object>> niaodanbaijiganSqlList = commonExecute2(connection, niaodanbaijiganSql, statement, resultSet);
                    if (niaodanbaijiganSqlList != null) {
                        zhyl6000002Json.addAll(niaodanbaijiganSqlList);
                        zhyl6000000JSONObject.put("zhyl6000002", zhyl6000002Json);
                    }
                    JSONArray zhyl6000003Json = new JSONArray(); //24h尿蛋白定量
                    String niaodanbaidingliang24h2Sql = niaodanbaidingliang24h4.replace("?", s);
                    List<Map<String, Object>> niaodanbaidingliang24h2SqlList = commonExecute2(connection, niaodanbaidingliang24h2Sql, statement, resultSet);
                    if (niaodanbaidingliang24h2SqlList != null) {
                        zhyl6000003Json.addAll(niaodanbaidingliang24h2SqlList);
                        zhyl6000000JSONObject.put("zhyl6000003", zhyl6000003Json);
                    }


                    JSONArray zhyl6000004Json = new JSONArray(); //24h肌酐清除率
                    String jiganqingchu24h2Sql = jiganqingchu24h4.replace("?", s);
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
                                        zhyl6000004JsonObject.put("zhyl6100023", map1.get(key));
                                    }
                                    if (key.equals("zhyl6100022")) {
                                        zhyl6000004JsonObject.put("zhyl6100022", map1.get(key));
                                    }
                                    if (key.equals("zhyl6100021")) {
                                        zhyl6000004JsonObject.put("zhyl6100021", map1.get(key));
                                    }
                                }
                                if (nameList.size() > 0) {
                                    for (int i = 0; i < nameList.size(); i++) {
                                        if (nameList.get(i).contains("24hCCr") && !nameList.get(i).contains("校正24hCCr")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl6100024", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl6100024", null);
                                            }
                                        }
                                        if (nameList.get(i).contains("身高")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl6100025", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl6100025", null);
                                            }
                                        }
                                        if (nameList.get(i).contains("体重")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl6100026", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl6100026", null);
                                            }
                                        }
                                        if (nameList.get(i).contains("体表面积")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl6100027", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl6100027", null);
                                            }
                                        }
                                        if (nameList.get(i).contains("校正24hCCr")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000004JsonObject.put("zhyl6100028", valueList.get(i));
                                            } else {
                                                zhyl6000004JsonObject.put("zhyl6100028", null);
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
                    String shenzaosuai2Sql = shenzaosuai4.replace("?", s);
                    List<Map<String, Object>> shenzaosuai2SqlList = commonExecute2(connection, shenzaosuai2Sql, statement, resultSet);
                    if (shenzaosuai2SqlList != null) {
                        zhyl6000005Json.addAll(shenzaosuai2SqlList);
                        zhyl6000000JSONObject.put("zhyl6000005", zhyl6000005Json);
                    }

                    JSONArray zhyl6000006Json = new JSONArray(); //尿蛋白电泳
                    String niaodanbaidianyong2Sql = niaodanbaidianyong4.replace("?", s);
                    List<Map<String, Object>> niaodanbaidianyong2SqlList = commonExecute2(connection, niaodanbaidianyong2Sql, statement, resultSet);
                    if (niaodanbaidianyong2SqlList != null) {
                        zhyl6000006Json.addAll(niaodanbaidianyong2SqlList);
                        zhyl6000000JSONObject.put("zhyl6000006", zhyl6000006Json);
                    }

                    JSONArray zhyl6000007Json = new JSONArray(); //血生化
                    commonjiancha(s, zhyl6000007Json, xueshenghua4, zhyl6000000JSONObject, "zhyl6000007", connection, statement, resultSet);

                    JSONArray zhyl6000008Json = new JSONArray(); //血常规
                    commonjiancha(s, zhyl6000008Json, xuechanggui4, zhyl6000000JSONObject, "zhyl6000008", connection, statement, resultSet);

                    JSONArray zhyl6000009Json = new JSONArray(); //尿钙/肌酐比
                    commonjiancha(s, zhyl6000009Json, niaogaijigan4, zhyl6000000JSONObject, "zhyl6000009", connection, statement, resultSet);


                    JSONArray zhyl6000010Json = new JSONArray(); //24h肌酐清除率
                    String niaodianjiezhi24h42Sql = niaodianjiezhi24h4.replace("?", s);
                    List<Map<String, Object>> niaodianjiezhi24h42SqlList = commonExecute2(connection, niaodianjiezhi24h42Sql, statement, resultSet);
                    if (niaodianjiezhi24h42SqlList != null) {
                        for (Map<String, Object> map1 : niaodianjiezhi24h42SqlList) {
                            if (map1 != null && map1.size() > 0) {
                                JSONObject zhyl6000010Object = new JSONObject();

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
                                        zhyl6000010Object.put("zhyl6100069", map1.get(key));
                                    }
                                    if (key.equals("zhyl6100068")) {
                                        zhyl6000010Object.put("zhyl6100068", map1.get(key));
                                    }
                                }
                                if (nameList.size() > 0) {
                                    for (int i = 0; i < nameList.size(); i++) {
                                        if (nameList.get(i).contains("24h尿钙") && !nameList.get(i).contains("24h尿钙体重比")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000010Object.put("zhyl6100070", valueList.get(i));
                                            } else {
                                                zhyl6000010Object.put("zhyl6100070", null);
                                            }
                                        }
                                        if (nameList.get(i).contains("体重")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000010Object.put("zhyl6100071", valueList.get(i));
                                            } else {
                                                zhyl6000010Object.put("zhyl6100071", null);
                                            }
                                        }

                                        if (nameList.get(i).contains("24h尿钙体重比")) {
                                            if (!valueList.get(i).contains("#") && valueList.get(i) != null) {
                                                zhyl6000010Object.put("zhyl6100072", valueList.get(i));
                                            } else {
                                                zhyl6000010Object.put("zhyl6100072", null);
                                            }
                                        }
                                    }
                                }
                                zhyl6000010Json.add(zhyl6000010Object);
                            }
                        }
                        zhyl6000000JSONObject.put("zhyl6000010", zhyl6000010Json);
                    }


                    JSONArray zhyl6000011Json = new JSONArray(); //肾脏影像学检查
                    commonjiancha(s, zhyl6000011Json, shenzangyingxiang4, zhyl6000000JSONObject, "zhyl6000011", connection, statement, resultSet);

                    JSONArray zhyl6000013Json = new JSONArray(); //肾动态检查
                    commonjiancha(s, zhyl6000013Json, shendongtai4, zhyl6000000JSONObject, "zhyl6000013", connection, statement, resultSet);

                    JSONArray zhyl6000014Json = new JSONArray(); //肝、胆影像学检查
                    commonjiancha(s, zhyl6000014Json, gandanyingxiang4, zhyl6000000JSONObject, "zhyl6000014", connection, statement, resultSet);


                    JSONObject zhyl6000015JSONObject = new JSONObject(); //肾活检
                    JSONArray zhyl6100101JSONArray = new JSONArray(); //免疫荧光-光镜
                    JSONArray zhyl6100123JSONArray = new JSONArray(); //电镜

                    String guangjing2Sql = guangjing4.replace("?", s);
                    List<Map<String, Object>> guangjing2SqlList = commonExecute2(connection, guangjing2Sql, statement, resultSet);
                    if (guangjing2SqlList != null && guangjing2SqlList.size() > 0) {
                        for (Map<String, Object> guangjingMap : guangjing2SqlList) {
                            if (guangjingMap != null && guangjingMap.size() > 0) {
                                JSONObject zhyl6100101Json = new JSONObject();
                                JSONObject zhyl6100121Json = new JSONObject();
                                JSONObject zhyl6100122Json = new JSONObject();
                                for (String key : guangjingMap.keySet()) {
                                    transJson(guangjingMap, key, "zhyl6100116", zhyl6100101Json, null);
                                    transJson(guangjingMap, key, "zhyl6100117", zhyl6100101Json, null);
                                    transJson(guangjingMap, key, "zhyl6100118", zhyl6100101Json, null);
                                    transJson(guangjingMap, key, "zhyl6100119", zhyl6100101Json, null);
                                    transJson(guangjingMap, key, "zhyl6100120", zhyl6100101Json, null);

                                    transJson(guangjingMap, key, "zhyl6200000", zhyl6100121Json, null);
                                    if (key.equals("zhyl6200001")) {
                                        transformQiangdu(zhyl6100121Json, guangjingMap.get(key), "zhyl6200001");
                                    }
                                    transJson(guangjingMap, key, "zhyl6200002", zhyl6100121Json, null);
                                    transJson(guangjingMap, key, "zhyl6200003", zhyl6100121Json, null);
                                    if (key.equals("zhyl6200004")) {
                                        transformQiangdu(zhyl6100121Json, guangjingMap.get(key), "zhyl6200004");
                                    }
                                    transJson(guangjingMap, key, "zhyl6200005", zhyl6100121Json, null);
                                    transJson(guangjingMap, key, "zhyl6200006", zhyl6100121Json, null);
                                    if (key.equals("zhyl6200007")) {
                                        transformQiangdu(zhyl6100121Json, guangjingMap.get(key), "zhyl6200007");
                                    }
                                    transJson(guangjingMap, key, "zhyl6200008", zhyl6100121Json, null);
                                    transJson(guangjingMap, key, "zhyl6200009", zhyl6100121Json, null);
                                    if (key.equals("zhyl6200010")) {
                                        transformQiangdu(zhyl6100121Json, guangjingMap.get(key), "zhyl6200010");
                                    }
                                    transJson(guangjingMap, key, "zhyl6200011", zhyl6100121Json, null);
                                    transJson(guangjingMap, key, "zhyl6200012", zhyl6100121Json, null);
                                    if (key.equals("zhyl6200013")) {
                                        transformQiangdu(zhyl6100121Json, guangjingMap.get(key), "zhyl6200013");
                                    }
                                    transJson(guangjingMap, key, "zhyl6200014", zhyl6100121Json, null);
                                    transJson(guangjingMap, key, "zhyl6200015", zhyl6100121Json, null);
                                    if (key.equals("zhyl6200016")) {
                                        transformQiangdu(zhyl6100121Json, guangjingMap.get(key), "zhyl6200016");
                                    }
                                    transJson(guangjingMap, key, "zhyl6200017", zhyl6100121Json, null);
                                    transJson(guangjingMap, key, "zhyl6200018", zhyl6100121Json, null);

                                    transJson(guangjingMap, key, "zhyl6200019", zhyl6100122Json, null);
                                    transJson(guangjingMap, key, "zhyl6200020", zhyl6100122Json, null);
                                    transJson(guangjingMap, key, "zhyl6200021", zhyl6100122Json, null);
                                    transJson(guangjingMap, key, "zhyl6200022", zhyl6100122Json, null);

                                }
                                zhyl6100101Json.put("zhyl6100121", zhyl6100121Json);
                                zhyl6100101Json.put("zhyl6100122", zhyl6100122Json);
                                zhyl6100101JSONArray.add(zhyl6100101Json);
                            }
                            zhyl6000015JSONObject.put("zhyl6100101", zhyl6100101JSONArray);

                        }
                    }

                    String dianjing2Sql = dianjing4.replace("?", s);
                    List<Map<String, Object>> dianjing2SqlSqlList = commonExecute2(connection, dianjing2Sql, statement, resultSet);
                    if (dianjing2SqlSqlList != null && dianjing2SqlSqlList.size() > 0) {
                        for (Map<String, Object> dianjingMap : dianjing2SqlSqlList) {
                            if (dianjingMap != null && dianjingMap.size() > 0) {
                                JSONObject zhyl6100123Json = new JSONObject();
                                JSONObject zhyl6100129Json = new JSONObject();

                                for (String key : dianjingMap.keySet()) {
                                    transJson(dianjingMap, key, "zhyl6100124", zhyl6100123Json, null);
                                    transJson(dianjingMap, key, "zhyl6100125", zhyl6100123Json, null);
                                    transJson(dianjingMap, key, "zhyl6100126", zhyl6100123Json, null);
                                    transJson(dianjingMap, key, "zhyl6100127", zhyl6100123Json, null);
                                    transJson(dianjingMap, key, "zhyl6100128", zhyl6100123Json, null);

                                    transJson(dianjingMap, key, "zhyl6200023", zhyl6100129Json, null);
                                    transJson(dianjingMap, key, "zhyl6200024", zhyl6100129Json, null);
                                    transJson(dianjingMap, key, "zhyl6200025", zhyl6100129Json, null);
                                    transJson(dianjingMap, key, "zhyl6200026", zhyl6100129Json, null);
                                    transJson(dianjingMap, key, "zhyl6200027", zhyl6100129Json, null);
                                    transJson(dianjingMap, key, "zhyl6200028", zhyl6100129Json, null);

                                }
                                zhyl6100123Json.put("zhyl6100129", zhyl6100129Json);
                                zhyl6100123JSONArray.add(zhyl6100123Json);
                            }
                            zhyl6000015JSONObject.put("zhyl6100123", zhyl6100123JSONArray);
                        }
                    }
                    zhyl6000000JSONObject.put("zhyl6000015", zhyl6000015JSONObject);

                    JSONArray zhyl6000016Json = new JSONArray(); //肝活检
                    commonjiancha(s, zhyl6000016Json, ganhuojian4, zhyl6000000JSONObject, "zhyl6000016", connection, statement, resultSet);

                    JSONArray zhyl6000017Json = new JSONArray(); //眼科检查
                    commonjiancha(s, zhyl6000017Json, ganhuojian4, zhyl6000000JSONObject, "zhyl6000017", connection, statement, resultSet);

                    JSONArray zhyl6000018Json = new JSONArray(); //其他检查
                    commonjiancha(s, zhyl6000018Json, qitajiancha4, zhyl6000000JSONObject, "zhyl6000018", connection, statement, resultSet);


                    JSONArray zhyl6000019Json = new JSONArray(); //基因检测
                    String jiyinjiance2Sql = jiyinjiance4.replace("?", s);
                    List<Map<String, Object>> jiyinjiance2List = commonExecute2(connection, jiyinjiance2Sql, statement, resultSet);
                    if (jiyinjiance2List != null && jiyinjiance2List.size() > 0) {
                        for (Map<String, Object> jiyinMap : jiyinjiance2List) {
                            if (jiyinMap != null && jiyinMap.size() > 0) {
                                JSONObject zhyl6000019JSONObject = new JSONObject();
                                JSONObject zhyl6100156JSONObject = new JSONObject();

                                for (String key : jiyinMap.keySet()) {
                                    transJson(jiyinMap, key, "zhyl6100145", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100146", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100147", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100148", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100149", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100150", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100151", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100152", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100153", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100154", zhyl6000019JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6100155", zhyl6000019JSONObject, null);

                                    transJson(jiyinMap, key, "zhyl6200036", zhyl6100156JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6200037", zhyl6100156JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6200038", zhyl6100156JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6200039", zhyl6100156JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6200040", zhyl6100156JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6200041", zhyl6100156JSONObject, null);
                                    transJson(jiyinMap, key, "zhyl6200042", zhyl6100156JSONObject, null);
                                }
                                zhyl6000019JSONObject.put("zhyl6100156", zhyl6100156JSONObject);
                                zhyl6000019Json.add(zhyl6000019JSONObject);
                            }
                        }
                        zhyl6000000JSONObject.put("zhyl6000019", zhyl6000019Json);
                    }

                    JSONArray zhyl6000020Json = new JSONArray(); //最终诊断
                    commonjiancha(s, zhyl6000020Json, zuizhongzhenduan4, zhyl6000000JSONObject, "zhyl6000020", connection, statement, resultSet);

                    zhyl6000000Json.add(zhyl6000000JSONObject);
                    jsonObject.put("zhyl6000000", zhyl6000000Json);

//-----------------------------------------用药----------------------------------------------
                    JSONArray zhyl7000000Json = new JSONArray(); //用药, 是一模一样的
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
                            commonjiancha(suifang, zhyl8000003Json, suifangxueshenghua4, zhyl8000000Json, "zhyl8000003", connection, statement, resultSet);

                            //-------尿蛋白/肌酐 每次随访可能有多个---------
                            JSONArray zhyl8000005Json = new JSONArray();
                            commonjiancha(suifang, zhyl8000005Json, suifangniaodanbaijigan4, zhyl8000000Json, "zhyl8000005", connection, statement, resultSet);

                            //-------24小时尿蛋白定量 每次随访可能有多个---------
                            JSONArray zhyl8000006Json = new JSONArray();
                            commonjiancha(suifang, zhyl8000006Json, suifang24niaodanbai4, zhyl8000000Json, "zhyl8000006", connection, statement, resultSet);

                            //-------24h肌酐清除率 每次随访可能有多个---------
                            JSONArray zhyl8000007Json = new JSONArray(); //24h肌酐清除率
                            String suifang24jiganqingchu2Sql = suifang24jiganqingchu4.replace("?", suifang);
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
                            commonjiancha(suifang, zhyl8000008Json, suifangshenzaoshuai4, zhyl8000000Json, "zhyl8000008", connection, statement, resultSet);
                            //-------肾脏早起损伤指标每次随访可能有多个---------
                            JSONArray zhyl8000009Json = new JSONArray();
                            commonjiancha(suifang, zhyl8000009Json, suifangshenzaoyingxiangxue4, zhyl8000000Json, "zhyl8000009", connection, statement, resultSet);

                            //-------肝、胆影像学检查每次随访可能有多个---------
                            JSONArray zhyl8000014Json = new JSONArray();
                            commonjiancha(suifang, zhyl8000014Json, gandanyingxiangxue4, zhyl8000000Json, "zhyl8000014", connection, statement, resultSet);







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


    }



}

