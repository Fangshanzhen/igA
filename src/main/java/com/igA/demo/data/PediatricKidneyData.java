package com.igA.demo.data;

import com.igA.demo.utils.JDBCUtils;
import com.igA.demo.utils.ResultSetUtils;
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
import static com.igA.demo.constant.PediatricKidneyDatabaseConstant.*;

import static com.igA.demo.data.igAData.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogChannelFactory;

@Slf4j
public class PediatricKidneyData {

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

        log.info("数据库连接成功");
        if (connection != null) {
            // 设置连接的持久性
            connection.setHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);
            List<String> idList = new ArrayList<>();
            idList = commonExecute(connection, statement, resultSet, KidneyIdSql2);
            if (id != null) {
                idList = Collections.singletonList(id);
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
//----------------------------------------------现病史-------------------------------------------------
                    String xianbingshi = xianbingshi2.replace("?", s); //现病史

                    Map<String, Object> xianbingshiMap = commonExecute1(connection, xianbingshi, statement, resultSet);
                    jsonObject.put("zhyl2000000", xianbingshiMap);

//                    JSONObject zhyl2000001Json = new JSONObject(); //水肿
//                    JSONObject zhyl2000002Json = new JSONObject(); //少尿
//                    JSONObject zhyl2000004Json = new JSONObject(); //贫血
//                    JSONObject zhyl2000005Json = new JSONObject(); //发育落后
//                    JSONObject zhyl2000006Json = new JSONObject(); //躯体畸形
//                    JSONObject zhyl2000007Json = new JSONObject(); //小头畸形
//                    JSONObject zhyl2000008Json = new JSONObject(); //眼部异常
//                    JSONObject zhyl2000009Json = new JSONObject(); //小瞳孔
//                    JSONObject zhyl2000010Json = new JSONObject(); //晶状体形状异常
//                    JSONObject zhyl2000011Json = new JSONObject(); //白内障
//                    JSONObject zhyl2000012Json = new JSONObject(); //外生殖器异常
//                    JSONObject zhyl2000013Json = new JSONObject(); //骨骼异常
//                    JSONObject zhyl2000014Json = new JSONObject(); //神经系统症状
//                    JSONObject zhyl2000015Json = new JSONObject(); //其他
//
//
//
//                    if (xianbingshiMap != null && xianbingshiMap.size() > 0) {
//                        for (String key : xianbingshiMap.keySet()) {
//                            transJson(xianbingshiMap, key, "zhyl2100000", zhyl2000001Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100001", zhyl2000001Json, null);
//
//                            transJson(xianbingshiMap, key, "zhyl2100002", zhyl2000002Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100003", zhyl2000002Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100046", zhyl2000002Json, null);
//
//                            transJson(xianbingshiMap, key, "zhyl2100004", zhyl2000004Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100005", zhyl2000004Json, null);
//
//                            transJson(xianbingshiMap, key, "zhyl2100006", zhyl2000005Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100007", zhyl2000005Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100026", zhyl2000005Json, null);
//
//                            transJson(xianbingshiMap, key, "zhyl2100008", zhyl2000006Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100009", zhyl2000006Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100027", zhyl2000006Json, null);
//
//                            transJson(xianbingshiMap, key, "zhyl2100010", zhyl2000007Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100011", zhyl2000007Json, null);
//
//
//                            transJson(xianbingshiMap, key, "zhyl2100012", zhyl2000008Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100013", zhyl2000008Json, null);
//
//                            transJson(xianbingshiMap, key, "zhyl2100014", zhyl2000009Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100015", zhyl2000009Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100047", zhyl2000009Json, null);
//
//                            transJson(xianbingshiMap, key, "zhyl2100016", zhyl2000010Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100017", zhyl2000010Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100028", zhyl2000010Json, null);
//
//                            transJson(xianbingshiMap, key, "zhyl2100018", zhyl2000011Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100019", zhyl2000011Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100048", zhyl2000011Json, null);
//
//                            transJson(xianbingshiMap, key, "zhyl2100020", zhyl2000012Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100021", zhyl2000012Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100029", zhyl2000012Json, null);
//
//                            transJson(xianbingshiMap, key, "zhyl2100031", zhyl2000013Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100022", zhyl2000013Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100023", zhyl2000013Json, null);
//
//                            transJson(xianbingshiMap, key, "zhyl2100030", zhyl2000014Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100049", zhyl2000014Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100050", zhyl2000014Json, null);
//
//
//                            transJson(xianbingshiMap, key, "zhyl2100034", zhyl2000015Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100024", zhyl2000015Json, null);
//                            transJson(xianbingshiMap, key, "zhyl2100025", zhyl2000015Json, null);
//
//                        }
//                        xianbingshiMap.remove("zhyl2100000");
//                        xianbingshiMap.remove("zhyl2100001");
//                        xianbingshiMap.remove("zhyl2100002");
//                        xianbingshiMap.remove("zhyl2100003");
//                        xianbingshiMap.remove("zhyl2100046");
//                        xianbingshiMap.remove("zhyl2100004");
//                        xianbingshiMap.remove("zhyl2100005");
//                        xianbingshiMap.remove("zhyl2100006");
//                        xianbingshiMap.remove("zhyl2100007");
//                        xianbingshiMap.remove("zhyl2100026");
//                        xianbingshiMap.remove("zhyl2100008");
//                        xianbingshiMap.remove("zhyl2100009");
//                        xianbingshiMap.remove("zhyl2100027");
//                        xianbingshiMap.remove("zhyl2100010");
//                        xianbingshiMap.remove("zhyl2100011");
//                        xianbingshiMap.remove("zhyl2100012");
//                        xianbingshiMap.remove("zhyl2100013");
//                        xianbingshiMap.remove("zhyl2100014");
//                        xianbingshiMap.remove("zhyl2100015");
//                        xianbingshiMap.remove("zhyl2100047");
//                        xianbingshiMap.remove("zhyl2100016");
//                        xianbingshiMap.remove("zhyl2100017");
//                        xianbingshiMap.remove("zhyl2100028");
//                        xianbingshiMap.remove("zhyl2100018");
//                        xianbingshiMap.remove("zhyl2100019");
//                        xianbingshiMap.remove("zhyl2100048");
//                        xianbingshiMap.remove("zhyl2100020");
//                        xianbingshiMap.remove("zhyl2100021");
//                        xianbingshiMap.remove("zhyl2100029");
//                        xianbingshiMap.remove("zhyl2100031");
//                        xianbingshiMap.remove("zhyl2100022");
//                        xianbingshiMap.remove("zhyl2100023");
//                        xianbingshiMap.remove("zhyl2100030");
//                        xianbingshiMap.remove("zhyl2100049");
//                        xianbingshiMap.remove("zhyl2100050");
//                        xianbingshiMap.remove("zhyl2100034");
//                        xianbingshiMap.remove("zhyl2100024");
//                        xianbingshiMap.remove("zhyl2100025");
//
//
//
//                        xianbingshiMap.put("zhyl2000001", zhyl2000001Json);
//                        xianbingshiMap.put("zhyl2000002", zhyl2000002Json);
//                        xianbingshiMap.put("zhyl2000004", zhyl2000004Json);
//                        xianbingshiMap.put("zhyl2000005", zhyl2000005Json);
//                        xianbingshiMap.put("zhyl2000006", zhyl2000006Json);
//                        xianbingshiMap.put("zhyl2000007", zhyl2000007Json);
//                        xianbingshiMap.put("zhyl2000008", zhyl2000008Json);
//                        xianbingshiMap.put("zhyl2000009", zhyl2000009Json);
//                        xianbingshiMap.put("zhyl2000010", zhyl2000010Json);
//                        xianbingshiMap.put("zhyl2000011", zhyl2000011Json);
//                        xianbingshiMap.put("zhyl2000012", zhyl2000012Json);
//                        xianbingshiMap.put("zhyl2000013", zhyl2000013Json);
//                        xianbingshiMap.put("zhyl2000014", zhyl2000014Json);
//                        xianbingshiMap.put("zhyl2000015", zhyl2000015Json);
//
//                        jsonObject.put("zhyl2000000",xianbingshiMap);
//
//                    }
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
                            "zhyl4200008", "zhyl4200009", "zhyl4100006", null);//母亲家族史
                    jiazushi(zhyl4000000Json, s, zhyl4000002Json, jiazhushi2, "父亲", connection, statement, resultSet, "zhyl4000002", "zhyl4200010", "zhyl4200011",
                            "zhyl4200012", "zhyl4200013", "zhyl4200014", "zhyl4200015", "zhyl4200016", "zhyl4200017",
                            "zhyl4200018", "zhyl4200019", "zhyl4100012", null);//父亲家族史

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
                                "zhyl4200028", "zhyl4200029", "zhyl4100018", zhyl4000003JsonArray);//兄弟姐妹
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
                                "zhyl4200038", "zhyl4200039", "zhyl4100038", zhyl4000004JsonArray);//父母辈亲属
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
                                "zhyl4200048", "zhyl4200049", "zhyl4100029", zhyl4000005JsonArray);//（外）祖父母
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
                                "zhyl4200058", "zhyl4200059", "zhyl4100040", zhyl4000006JsonArray);//其它
                    }


                    jsonObject.put("zhyl4000000", zhyl4000000Json);

//---------------------------------------------------------------------------------------


                    ObjectMapper mapper = new ObjectMapper();    //为了让json中的字段有序
                    mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
                    String jsonStr = mapper.writeValueAsString(jsonObject);
                    System.out.println(jsonStr);


                }
            }


        }

    }

    private static void jiazushi(JSONObject zhyl4000000Json, String s, JSONObject kaitouJson, String sql, String jiazuguanxi, Connection connection, Statement statement, ResultSet resultSet,
                                 String kaitou, String danbainiao, String danbainiaonianling, String shengongneng, String shengongnengnianling,
                                 String tingli, String tinglinianling, String yanbu, String yanbulianling, String xueniao,
                                 String xueniaonianling, String qita, JSONArray jsonArray) throws Exception {
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
            }
            if (nameList.size() > 0) {
                for (int i = 0; i < nameList.size(); i++) {
                    if (nameList.get(i).contains("蛋白尿")) {
                        kaitouJson.put(danbainiao, castData(isNotList.get(i)));
                        if (!ageList.get(i).contains("#") && ageList.get(i) != null) {
                            kaitouJson.put(danbainiaonianling, ageList.get(i));
                        } else {
                            kaitouJson.put(danbainiaonianling, null);
                        }
                    }
                    if (nameList.get(i).contains("肾功能异常")) {
                        kaitouJson.put(shengongneng, castData(isNotList.get(i)));
                        if (!ageList.get(i).contains("#") && ageList.get(i) != null) {
                            kaitouJson.put(shengongnengnianling, ageList.get(i));
                        } else {
                            kaitouJson.put(shengongnengnianling, null);
                        }
                    }
                    if (nameList.get(i).contains("听力异常")) {
                        kaitouJson.put(tingli, castData(isNotList.get(i)));
                        if (!ageList.get(i).contains("#") && ageList.get(i) != null) {
                            kaitouJson.put(tinglinianling, ageList.get(i));
                        } else {
                            kaitouJson.put(tinglinianling, null);
                        }
                    }
                    if (nameList.get(i).contains("眼部症状")) {
                        kaitouJson.put(yanbu, castData(isNotList.get(i)));
                        if (!ageList.get(i).contains("#") && ageList.get(i) != null) {
                            kaitouJson.put(yanbulianling, ageList.get(i));
                        } else {
                            kaitouJson.put(yanbulianling, null);
                        }
                    }
                    if (nameList.get(i).contains("血尿")) {
                        kaitouJson.put(xueniao, castData(isNotList.get(i)));
                        if (!ageList.get(i).contains("#") && ageList.get(i) != null) {
                            kaitouJson.put(xueniaonianling, ageList.get(i));
                        } else {
                            kaitouJson.put(xueniaonianling, null);
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

    private static Object castData(String s) {
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
}
