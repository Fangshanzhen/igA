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
import static com.igA.demo.constant.PediatricKidneyDatabaseConstant2.jiazhushi2;
import static com.igA.demo.constant.PediatricKidneyDatabaseConstant4.*;
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

