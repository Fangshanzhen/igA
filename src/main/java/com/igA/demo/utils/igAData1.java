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
public class igAData1 {

    public static void transformData() throws Exception {

        Connection connection = null; //默认postgresql
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection("postgresql", "10.0.108.51", "5432", "postgres", "hospital", "postgres", "zhyl0123");
        } catch (SQLException e) {
            log.error("database connection error: " + e, "");
        }
//        log.info("数据库连接成功");

        if (connection != null) {
            List<String> idList = new ArrayList<>();

            idList = commonExecute(connection, statement, resultSet);

            idList = Collections.singletonList("49");


            if (idList != null && idList.size() > 0) {
                for (String s : idList) {

                    JSONObject jsonObject = new JSONObject();
                    Map<String, Object> map = new HashMap<>();

                    String newPatientSql = patientSql.replace("?", s);
                    map = commonExecute1(connection, newPatientSql, statement, resultSet);
                    JSONObject addressJson = new JSONObject();
                    JSONArray newContactList = new JSONArray();
                    if (map != null && map.size() > 0) {
                        for (String key : map.keySet()) {

                            if (map.keySet().contains("contact_data") && key.equals("contact_data")) {   //解析contact_data中的数据
                                if (map.get(key) != null) {
                                    JSONArray contactList = JSONArray.parseArray(String.valueOf(map.get(key)));
                                    for (Object o : contactList) {
                                        o = String.valueOf(o).replace("explain", "zhyl110200004").replace("linkname", "zhyl110200001")
                                                .replace("numberinfo", "zhyl110200003").replace("relation", "zhyl110200002");
                                        newContactList.add(o);
                                    }
                                }
                            }

                            if (map.keySet().contains("zhyl110100001") && key.equals("zhyl110100001")) {
                                addressJson.put("zhyl110100001", map.get(key));
                            }
                            if (map.keySet().contains("zhyl110100002") && key.equals("zhyl110100002")) {
                                addressJson.put("zhyl110100002", map.get(key));
                            }
                            if (map.keySet().contains("zhyl110100003") && key.equals("zhyl110100003")) {
                                addressJson.put("zhyl110100003", map.get(key));
                            }

                        }

                        JSONArray newContactList1 = new JSONArray();
                        if (newContactList.size() > 0) {
                            for (int i = 0; i < newContactList.size(); i++) {
                                JSONObject jsonObj = JSONObject.parseObject(String.valueOf(newContactList.get(i)));
                                if (jsonObj.keySet().contains("zhyl110200002") && jsonObj.get("zhyl110200002") != null) {
                                    Object o = jsonObj.get("zhyl110200002");
                                    newContactList1.add(transformHuanErGuanXi(jsonObj, o, "zhyl110200002"));
                                }
                            }
                        }

                        map.remove("contact_data");
                        map.remove("zhyl110100001");
                        map.remove("zhyl110100002");
                        map.remove("zhyl110100003");

                        if (newContactList.size() > 0) {
                            map.put("zhyl110200000", newContactList1);
                        } else {
                            map.put("zhyl110200000", null);
                        }
                        map.put("zhyl110100000", addressJson);

                    }
                    JSONObject zhyl110000000 = new JSONObject();

                    zhyl110000000.put("zhyl110000000", map);
                    jsonObject.put("zhyl100000000", zhyl110000000);   //基本信息zhyl100000000


                    //-----------------------------------------------------------


                    JSONObject zhyl200000000Json = new JSONObject();

                    String newPatientInfoSql = patientInfoSql.replace("?", s);
                    Map<String, Object> map1 = commonExecute1(connection, newPatientInfoSql, statement, resultSet);
                    JSONObject zhyl210000000Json = new JSONObject();
                    if (map1 != null && map1.size() > 0) {
                        for (String key : map1.keySet()) {
                            if (map1.keySet().contains("zhyl210000001") && key.equals("zhyl210000001")) {
                                zhyl210000000Json.put("zhyl210000001", map1.get(key));
                            }
                            if (map1.keySet().contains("zhyl210000002") && key.equals("zhyl210000002")) {
                                zhyl210000000Json.put("zhyl210000002", map1.get(key));
                            }
                            if (map1.keySet().contains("zhyl210000003") && key.equals("zhyl210000003")) {
                                zhyl210000000Json.put("zhyl210000003", map1.get(key));
                            }
                            if (map1.keySet().contains("zhyl210000004") && key.equals("zhyl210000004")) {
                                zhyl210000000Json.put("zhyl210000004", map1.get(key));
                            }
                            if (map1.keySet().contains("zhyl210000005") && key.equals("zhyl210000005")) {
                                zhyl210000000Json.put("zhyl210000005", map1.get(key));
                            }
                            if (map1.keySet().contains("zhyl200000001") && key.equals("zhyl200000001")) {
                                zhyl200000000Json.put("zhyl200000001", map1.get(key)); //zhyl200000001 单独的时间
                            }

                        }

                    }
                    zhyl200000000Json.put("zhyl210000000", zhyl210000000Json);
                    //-----------------------------------------------------------zhyl210000000

                    String newBaseLineHisSql = baseLineHisSql.replace("?", s);
                    Map<String, Object> baseLineHisMap = commonExecute1(connection, newBaseLineHisSql, statement, resultSet);

                    JSONArray newYysDataJSONArray = new JSONArray();
                    JSONArray newFamilyJSONArray = new JSONArray();

                    if (baseLineHisMap != null && baseLineHisMap.size() > 0) {
                        for (String key : baseLineHisMap.keySet()) {

                            if (baseLineHisMap.keySet().contains("yys_data") && key.equals("yys_data")) {   //解析yys_data中的数据 用药数据 是个list
                                if (baseLineHisMap.get(key) != null) {
                                    JSONArray yysList = JSONArray.parseArray(String.valueOf(baseLineHisMap.get(key)));
                                    for (Object o : yysList) {
                                        o = String.valueOf(o).replace("name", "zhyl210101001").replace("start_time", "zhyl210101002")
                                                .replace("end_time", "zhyl210101003").replace("remark", "zhyl210101004");
                                        newYysDataJSONArray.add(o);
                                    }
                                }
                            }

                            if (baseLineHisMap.keySet().contains("szjbjzs_data") && key.equals("szjbjzs_data")) {   //解析szjbjzs_data中的数据
                                if (baseLineHisMap.get(key) != null) {
                                    JSONArray familyList = JSONArray.parseArray(String.valueOf(baseLineHisMap.get(key)));
                                    for (Object o : familyList) {
                                        o = String.valueOf(o).replace("name", "zhyl210102001").replace("sbzj", "zhyl210102002")
                                                .replace("age", "zhyl210102003").replace("select", "zhyl210102004")
                                                .replace("report", "zhyl210102005").replace("exlplain", "zhyl210102006")
                                        ;
                                        newFamilyJSONArray.add(o);
                                    }
                                }
                            }

                        }  //key

                        JSONArray transYysDataJSONArray = new JSONArray();   //用药史
                        if (newYysDataJSONArray.size() > 0) {
                            for (int i = 0; i < newYysDataJSONArray.size(); i++) {
                                JSONObject jsonObj = JSONObject.parseObject(String.valueOf(newYysDataJSONArray.get(i)));
                                if (jsonObj.keySet().contains("zhyl210101001") && jsonObj.get("zhyl210101001") != null) {
                                    Object o = jsonObj.get("zhyl210101001");
                                    jsonObj = transformDrug(jsonObj, o, "zhyl210101001");
                                }
                                if (jsonObj.keySet().contains("zhyl210101002") && jsonObj.get("zhyl210101002") != null) {
                                    Object o = jsonObj.get("zhyl210101002");
                                    jsonObj = transformTime(jsonObj, o, "zhyl210101002");
                                }
                                if (jsonObj.keySet().contains("zhyl210101003") && jsonObj.get("zhyl210101003") != null) {
                                    Object o = jsonObj.get("zhyl210101003");
                                    jsonObj = transformTime(jsonObj, o, "zhyl210101003");
                                }
                                transYysDataJSONArray.add(jsonObj);
                            }
                        }

                        baseLineHisMap.remove("yys_data");

                        if (transYysDataJSONArray.size() > 0) {
                            baseLineHisMap.put("zhyl210101000", transYysDataJSONArray);
                        } else {
                            baseLineHisMap.put("zhyl210101000", null);
                        }


                        JSONArray transFamilyDataJSONArray = new JSONArray();    //家族史

                        for (int i = 0; i < newFamilyJSONArray.size(); i++) {
                            JSONObject jsonObj = JSONObject.parseObject(String.valueOf(newFamilyJSONArray.get(i)));
                            if (jsonObj.keySet().contains("zhyl210102001") && jsonObj.get("zhyl210102001") != null) {
                                Object o = jsonObj.get("zhyl210102001");
                                jsonObj = transformHuanErGuanXi(jsonObj, o, "zhyl210102001");
                            }
//                            if (jsonObj.keySet().contains("zhyl210102002") && jsonObj.get("zhyl210102002") != null) {
//                                Object o = jsonObj.get("zhyl210102002");
//                                jsonObj = transformDis(jsonObj, o);
//                            }
                            if (jsonObj.keySet().contains("zhyl210102004") && jsonObj.get("zhyl210102004") != null) {
                                Object o = jsonObj.get("zhyl210102004");
                                jsonObj = transformIsOrNot(jsonObj, o, "zhyl210102004");
                            }
                            transFamilyDataJSONArray.add(jsonObj);
                        }

                        baseLineHisMap.remove("szjbjzs_data");

                        if (transFamilyDataJSONArray.size() > 0) {
                            baseLineHisMap.put("zhyl210102000", transFamilyDataJSONArray);
                        } else {
                            baseLineHisMap.put("zhyl210102000", null);
                        }


                    }

                    zhyl200000000Json.put("zhyl210100000", baseLineHisMap);
                    //-----------------------------------------------------------zhyl210100000 病史

                    String newBasicPESql = basicPESql.replace("?", s);
                    Map<String, Object> basicPESqlMap = commonExecute1(connection, newBasicPESql, statement, resultSet);
                    zhyl200000000Json.put("zhyl210200000", basicPESqlMap);
                    //-----------------------------------------------------------zhyl210200000 体格检查
                    String newBasicLabSql = basicLabSql.replace("?", s);

                    Map<String, Object> basicLabMap = commonExecute1(connection, newBasicLabSql, statement, resultSet);
                    JSONObject zhyl210301000Json = new JSONObject(); //尿常规
                    JSONObject zhyl210302000Json = new JSONObject(); //尿蛋白定量检查
                    JSONArray zhyl210302100Json = new JSONArray(); //24小时尿蛋白定量
                    JSONArray zhyl210302200Json = new JSONArray(); //尿蛋白/肌酐
                    JSONArray zhyl210303000Json = new JSONArray(); //肾脏早期损伤指标 
                    JSONArray zhyl210304000Json = new JSONArray(); //血常规 
                    JSONObject zhyl210305000Json = new JSONObject(); //生化检查
                    JSONArray zhyl210305100Json = new JSONArray(); //其他生化检查
                    JSONArray zhyl210306000Json = new JSONArray(); //24小时肌酐清除率(Ccr)
                    JSONObject zhyl210307000Json = new JSONObject(); //感染筛查
                    JSONObject zhyl210308000Json = new JSONObject(); //免疫球蛋白和补体检查
                    JSONObject zhyl210309000Json = new JSONObject(); //过敏相关检查
                    JSONObject zhyl210310000Json = new JSONObject(); //自身抗体检查
                    JSONObject zhyl210311000Json = new JSONObject(); //尿钙检查
                    JSONObject zhyl210312000Json = new JSONObject(); //全段甲状旁腺素检查
                    JSONObject zhyl210313000Json = new JSONObject(); //IgA1分子异常糖基化

                    if (basicLabMap != null && basicLabMap.size() > 0) {
                        for (String key : basicLabMap.keySet()) {
                            if (basicLabMap.keySet().contains("zhyl210301001") && key.equals("zhyl210301001")) {
                                zhyl210301000Json.put("zhyl210301001", basicLabMap.get(key));
                            }
                            if (basicLabMap.keySet().contains("zhyl210301002") && key.equals("zhyl210301002")) {
                                zhyl210301000Json.put("zhyl210301002", basicLabMap.get(key));
                            }
                            if (basicLabMap.keySet().contains("zhyl210301003") && key.equals("zhyl210301003")) {
                                zhyl210301000Json.put("zhyl210301003", basicLabMap.get(key));
                            }
                            if (basicLabMap.keySet().contains("zhyl210301004") && key.equals("zhyl210301004")) {
                                zhyl210301000Json.put("zhyl210301004", basicLabMap.get(key));
                            }
                            if (basicLabMap.keySet().contains("zhyl210301005") && key.equals("zhyl210301005")) {
                                zhyl210301000Json.put("zhyl210301005", basicLabMap.get(key));
                            }

                            if (basicLabMap.keySet().contains("ndbdl_data") && key.equals("ndbdl_data")) {   //解析ndbdl_data中的数据
                                if (basicLabMap.get(key) != null) {
                                    JSONArray basicLabList = JSONArray.parseArray(String.valueOf(basicLabMap.get(key)));
                                    for (Object o : basicLabList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("time")) {
                                            newJsonObj.put("zhyl210302001", transformTime1(jsonObj.get("time")));
                                        }
                                        if (jsonObj.keySet().contains("val")) {
                                            newJsonObj.put("zhyl210302002", jsonObj.get("val"));
                                        }
                                        if (jsonObj.keySet().contains("blood")) {
                                            if (String.valueOf(jsonObj.get("blood")).equals("1")) {
                                                newJsonObj.put("zhyl210302003", 1);
                                            }
                                            if (String.valueOf(jsonObj.get("blood")).equals("2")) {
                                                newJsonObj.put("zhyl210302003", 0);
                                            }
                                            if (String.valueOf(jsonObj.get("blood")).equals("3")) {
                                                newJsonObj.put("zhyl210302003", 9);
                                            }
                                        }
                                        if (jsonObj.keySet().contains("tizhong")) {
                                            newJsonObj.put("zhyl210302004", jsonObj.get("tizhong"));
                                        }
                                        zhyl210302100Json.add(newJsonObj);
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
                                            newJsonObj.put("zhyl210302005", transformTime1(jsonObj.get("time")));
                                        }
                                        if (jsonObj.keySet().contains("val")) {
                                            newJsonObj.put("zhyl210302006", jsonObj.get("val"));
                                        }
                                        if (jsonObj.keySet().contains("blood")) {
                                            if (String.valueOf(jsonObj.get("blood")).equals("1")) {
                                                newJsonObj.put("zhyl210302007", 1);
                                            }
                                            if (String.valueOf(jsonObj.get("blood")).equals("2")) {
                                                newJsonObj.put("zhyl210302007", 0);
                                            }
                                            if (String.valueOf(jsonObj.get("blood")).equals("3")) {
                                                newJsonObj.put("zhyl210302007", 9);
                                            }
                                        }
                                        zhyl210302200Json.add(newJsonObj);
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
                                            newJsonObj.put("zhyl210303001", transformTime1(jsonObj.get("time")));
                                        }
                                        if (jsonObj.keySet().contains("ma")) {
                                            newJsonObj.put("zhyl210303002", jsonObj.get("ma"));
                                        }
                                        if (jsonObj.keySet().contains("tru")) {
                                            newJsonObj.put("zhyl210303003", jsonObj.get("tru"));
                                        }
                                        if (jsonObj.keySet().contains("nag")) {
                                            newJsonObj.put("zhyl210303004", jsonObj.get("nag"));
                                        }
                                        if (jsonObj.keySet().contains("aim")) {
                                            newJsonObj.put("zhyl210303005", jsonObj.get("aim"));
                                        }
                                        if (jsonObj.keySet().contains("ucea")) {
                                            newJsonObj.put("zhyl210303006", jsonObj.get("ucea"));
                                        }
                                        if (jsonObj.keySet().contains("acr")) {
                                            newJsonObj.put("zhyl210303007", jsonObj.get("acr"));
                                        }
                                        zhyl210303000Json.add(newJsonObj);
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
                                            newJsonObj.put("zhyl210304001", transformTime1(jsonObj.get("time")));
                                        }
                                        if (jsonObj.keySet().contains("wbc")) {
                                            newJsonObj.put("zhyl210304002", jsonObj.get("wbc"));
                                        }
                                        if (jsonObj.keySet().contains("hgb")) {
                                            newJsonObj.put("zhyl210304003", jsonObj.get("hgb"));
                                        }
                                        if (jsonObj.keySet().contains("hct")) {
                                            newJsonObj.put("zhyl210304004", jsonObj.get("hct"));
                                        }
                                        if (jsonObj.keySet().contains("plt")) {
                                            newJsonObj.put("zhyl210304005", jsonObj.get("plt"));
                                        }

                                        zhyl210304000Json.add(newJsonObj);
                                    }
                                }
                            }

                            transJson(basicLabMap, key, "zhyl210305001", zhyl210305000Json, null);
                            transJson(basicLabMap, key, "zhyl210305002", zhyl210305000Json, null);
                            transJson(basicLabMap, key, "zhyl210305003", zhyl210305000Json, null);
                            transJson(basicLabMap, key, "zhyl210305004", zhyl210305000Json, null);
                            transJson(basicLabMap, key, "zhyl210305005", zhyl210305000Json, null);
                            transJson(basicLabMap, key, "zhyl210305006", zhyl210305000Json, null);
                            transJson(basicLabMap, key, "zhyl210305007", zhyl210305000Json, null);
                            transJson(basicLabMap, key, "zhyl210305008", zhyl210305000Json, null);
                            transJson(basicLabMap, key, "zhyl210305009", zhyl210305000Json, null);

                            if (basicLabMap.keySet().contains("shjc_data") && key.equals("shjc_data")) {   //解析shjc_data中的数据
                                if (basicLabMap.get(key) != null) {
                                    JSONArray basicLabList = JSONArray.parseArray(String.valueOf(basicLabMap.get(key)));
                                    for (Object o : basicLabList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("time")) {
                                            newJsonObj.put("zhyl210305010", transformTime1(jsonObj.get("time")));
                                        }
                                        if (jsonObj.keySet().contains("alb")) {
                                            newJsonObj.put("zhyl210305011", jsonObj.get("alb"));
                                        }
                                        if (jsonObj.keySet().contains("crea")) {
                                            newJsonObj.put("zhyl210305012", jsonObj.get("crea"));
                                        }
                                        if (jsonObj.keySet().contains("urea")) {
                                            newJsonObj.put("zhyl210305013", jsonObj.get("urea"));
                                        }
                                        if (jsonObj.keySet().contains("c")) {
                                            newJsonObj.put("zhyl210305014", jsonObj.get("c"));
                                        }
                                        if (jsonObj.keySet().contains("tcho")) {
                                            newJsonObj.put("zhyl210305015", jsonObj.get("tcho"));
                                        }
                                        zhyl210305100Json.add(newJsonObj);
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
                                            newJsonObj.put("zhyl210306001", transformTime1(jsonObj.get("time")));
                                        }
                                        if (jsonObj.keySet().contains("val")) {
                                            newJsonObj.put("zhyl210306002", jsonObj.get("val"));
                                        }
                                        if (jsonObj.keySet().contains("score")) {
                                            newJsonObj.put("zhyl210306003", jsonObj.get("score"));
                                        }

                                        zhyl210306000Json.add(newJsonObj);
                                    }
                                }
                            }

                            transJson(basicLabMap, key, "zhyl210307001", zhyl210307000Json, null);
                            transJson(basicLabMap, key, "zhyl210307002", zhyl210307000Json, null);
                            transJson(basicLabMap, key, "zhyl210307003", zhyl210307000Json, null);
                            transJson(basicLabMap, key, "zhyl210307004", zhyl210307000Json, null);
                            transJson(basicLabMap, key, "zhyl210307005", zhyl210307000Json, null);
                            transJson(basicLabMap, key, "zhyl210307006", zhyl210307000Json, null);
                            transJson(basicLabMap, key, "zhyl210307007", zhyl210307000Json, null);
                            transJson(basicLabMap, key, "zhyl210307008", zhyl210307000Json, null);
                            transJson(basicLabMap, key, "zhyl210307009", zhyl210307000Json, null);


                            transJson(basicLabMap, key, "zhyl210308001", zhyl210308000Json, null);
                            transJson(basicLabMap, key, "zhyl210308002", zhyl210308000Json, null);
                            transJson(basicLabMap, key, "zhyl210308003", zhyl210308000Json, null);
                            transJson(basicLabMap, key, "zhyl210308004", zhyl210308000Json, null);
                            transJson(basicLabMap, key, "zhyl210308005", zhyl210308000Json, null);
                            transJson(basicLabMap, key, "zhyl210308006", zhyl210308000Json, null);

                            transJson(basicLabMap, key, "zhyl210309001", zhyl210309000Json, null);
                            transJson(basicLabMap, key, "zhyl210309002", zhyl210309000Json, null);
                            transJson(basicLabMap, key, "zhyl210309003", zhyl210309000Json, null);


                            transJson(basicLabMap, key, "zhyl210310001", zhyl210310000Json, null);
                            transJson(basicLabMap, key, "zhyl210310002", zhyl210310000Json, null);
                            transJson(basicLabMap, key, "zhyl210310003", zhyl210310000Json, null);
                            transJson(basicLabMap, key, "zhyl210310004", zhyl210310000Json, null);
                            transJson(basicLabMap, key, "zhyl210310005", zhyl210310000Json, "list");


                            if (basicLabMap.keySet().contains("ngjc_data") && key.equals("ngjc_data")) {   //解析ngjc_data中的数据
                                if (basicLabMap.get(key) != null) {
                                    JSONArray basicLabList = JSONArray.parseArray(String.valueOf(basicLabMap.get(key)));
                                    for (Object o : basicLabList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));

                                        if (jsonObj.keySet().contains("time")) {
                                            zhyl210311000Json.put("zhyl210311001", transformTime1(jsonObj.get("time")));
                                        }
                                        if (jsonObj.keySet().contains("ucr")) {
                                            zhyl210311000Json.put("zhyl210311002", jsonObj.get("ucr"));
                                        }

                                    }
                                }
                            }

                            if (basicLabMap.keySet().contains("ngdl_data") && key.equals("ngdl_data")) {   //解析ngdl_data中的数据
                                if (basicLabMap.get(key) != null) {
                                    JSONArray basicLabList = JSONArray.parseArray(String.valueOf(basicLabMap.get(key)));
                                    for (Object o : basicLabList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        if (jsonObj.keySet().contains("time")) {
                                            zhyl210311000Json.put("zhyl210311003", transformTime1(jsonObj.get("time")));
                                        }
                                        if (jsonObj.keySet().contains("val")) {
                                            zhyl210311000Json.put("zhyl210311004", jsonObj.get("val"));
                                        }

                                    }
                                }
                            }

                            transJson(basicLabMap, key, "zhyl210312001", zhyl210312000Json, null);
                            transJson(basicLabMap, key, "zhyl210312002", zhyl210312000Json, null);
                            transJson(basicLabMap, key, "zhyl210313001", zhyl210313000Json, null);


                        } //key

                        basicLabMap.remove("zhyl210301001");
                        basicLabMap.remove("zhyl210301002");
                        basicLabMap.remove("zhyl210301003");
                        basicLabMap.remove("zhyl210301004");
                        basicLabMap.remove("zhyl210301005");
                        basicLabMap.put("zhyl210301000", zhyl210301000Json);

                        basicLabMap.remove("ndbdl_data");
                        zhyl210302000Json.put("zhyl210302100", zhyl210302100Json);
                        basicLabMap.remove("ndbjg_data");
                        zhyl210302000Json.put("zhyl210302200", zhyl210302200Json);
                        basicLabMap.put("zhyl210302000", zhyl210302000Json);

                        basicLabMap.remove("szzq_data");
                        basicLabMap.put("zhyl210303000", zhyl210303000Json);

                        basicLabMap.remove("xcg_data");
                        basicLabMap.put("zhyl210304000", zhyl210304000Json);

                        basicLabMap.remove("zhyl210305001");
                        basicLabMap.remove("zhyl210305002");
                        basicLabMap.remove("zhyl210305003");
                        basicLabMap.remove("zhyl210305004");
                        basicLabMap.remove("zhyl210305005");
                        basicLabMap.remove("zhyl210305006");
                        basicLabMap.remove("zhyl210305007");
                        basicLabMap.remove("zhyl210305008");
                        basicLabMap.remove("zhyl210305009");
                        basicLabMap.remove("shjc_data");
                        zhyl210305000Json.put("zhyl210305100", zhyl210305100Json);
                        basicLabMap.put("zhyl210305000", zhyl210305000Json);

                        basicLabMap.remove("ccr_data");
                        basicLabMap.put("zhyl210306000", zhyl210306000Json);

                        basicLabMap.remove("zhyl210307001");
                        basicLabMap.remove("zhyl210307002");
                        basicLabMap.remove("zhyl210307003");
                        basicLabMap.remove("zhyl210307004");
                        basicLabMap.remove("zhyl210307005");
                        basicLabMap.remove("zhyl210307006");
                        basicLabMap.remove("zhyl210307007");
                        basicLabMap.remove("zhyl210307008");
                        basicLabMap.remove("zhyl210307009");
                        basicLabMap.put("zhyl210307000", zhyl210307000Json);

                        basicLabMap.remove("zhyl210308001");
                        basicLabMap.remove("zhyl210308002");
                        basicLabMap.remove("zhyl210308003");
                        basicLabMap.remove("zhyl210308004");
                        basicLabMap.remove("zhyl210308005");
                        basicLabMap.remove("zhyl210308006");
                        basicLabMap.put("zhyl210308000", zhyl210308000Json);

                        basicLabMap.remove("zhyl210309001");
                        basicLabMap.remove("zhyl210309002");
                        basicLabMap.remove("zhyl210309003");
                        basicLabMap.put("zhyl210309000", zhyl210309000Json);

                        basicLabMap.remove("zhyl210310001");
                        basicLabMap.remove("zhyl210310002");
                        basicLabMap.remove("zhyl210310003");
                        basicLabMap.remove("zhyl210310004");
                        basicLabMap.remove("zhyl210310005");
                        basicLabMap.put("zhyl210310000", zhyl210310000Json);

                        basicLabMap.remove("ngdl_data");
                        basicLabMap.remove("ngjc_data");
                        basicLabMap.put("zhyl210311000", zhyl210311000Json);

                        basicLabMap.remove("zhyl210312001");
                        basicLabMap.remove("zhyl210312002");
                        basicLabMap.put("zhyl210312000", zhyl210312000Json);

                        basicLabMap.remove("zhyl210313001");
                        basicLabMap.put("zhyl210313000", zhyl210313000Json);
                    }

                    zhyl200000000Json.put("zhyl210300000", basicLabMap);
//--------------------------------------------------------------------------------------实验室检验

                    String newBingLiSql = bingliSql.replace("?", s);
                    Map<String, Object> bingliMap = commonExecute1(connection, newBingLiSql, statement, resultSet);
                    JSONObject zhyl210401000Json = new JSONObject(); //病理检查

                    JSONObject zhyl210402000Json = new JSONObject(); //免疫荧光
                    JSONObject zhyl210402100Json = new JSONObject(); //IgA
                    JSONObject zhyl210402200Json = new JSONObject(); //IgG
                    JSONObject zhyl210402300Json = new JSONObject(); //IgM
                    JSONObject zhyl210402400Json = new JSONObject(); //C3
                    JSONObject zhyl210402500Json = new JSONObject(); //C1q
                    JSONObject zhyl210402600Json = new JSONObject(); //C4
                    JSONObject zhyl210402700Json = new JSONObject(); //fra

                    JSONObject zhyl210403000Json = new JSONObject(); //光镜
                    JSONObject zhyl210403100Json = new JSONObject(); //肾小球
                    JSONObject zhyl210403200Json = new JSONObject(); //毛内细胞增生病变
                    JSONObject zhyl210403300Json = new JSONObject(); //毛细血管外病变-细胞性新月体
                    JSONObject zhyl210403400Json = new JSONObject(); //毛细血管外病变-细胞纤维性新月体
                    JSONObject zhyl210403500Json = new JSONObject(); //毛细血管外病变-纤维性新月体


                    JSONObject zhyl210404000Json = new JSONObject(); //电镜检查

                    if (bingliMap != null && bingliMap.size() > 0) {

                        for (String key : bingliMap.keySet()) {

                            if (bingliMap.keySet().contains("def_data") && key.equals("def_data")) {   //解析def_data中的数据
                                if (bingliMap.get(key) != null) {
                                    JSONArray bingliList = JSONArray.parseArray(String.valueOf(bingliMap.get(key)));
                                    for (Object o : bingliList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        if (jsonObj.keySet().contains("time")) {
                                            zhyl210401000Json.put("zhyl210401001", transformTime1(jsonObj.get("time")));
                                        }
                                        if (jsonObj.keySet().contains("select")) {
                                            if (String.valueOf(jsonObj.get("select")).equals("1")) {
                                                zhyl210401000Json.put("zhyl210401002", 2);
                                            }
                                            if (String.valueOf(jsonObj.get("select")).equals("2")) {
                                                zhyl210401000Json.put("zhyl210401002", 1);
                                            }
                                        }
                                        if (jsonObj.keySet().contains("name")) {
                                            zhyl210401000Json.put("zhyl210401003", jsonObj.get("name"));
                                        }
                                    }
                                }
                            }
                            transJson(bingliMap, key, "zhyl210401004", zhyl210401000Json, null);

                            transJson(bingliMap, key, "zhyl210402001", zhyl210402000Json, null);
                            transJson(bingliMap, key, "zhyl210402002", zhyl210402000Json, null);

                            transJson(bingliMap, key, "zhyl210402003", zhyl210402100Json, null);
                            transJson(bingliMap, key, "zhyl210402004", zhyl210402100Json, "list");
                            transJson(bingliMap, key, "zhyl210402005", zhyl210402100Json, "list");
                            zhyl210402000Json.put("zhyl210402100", zhyl210402100Json);//iga属于免疫荧光

                            transJson(bingliMap, key, "zhyl210402006", zhyl210402200Json, null);
                            transJson(bingliMap, key, "zhyl210402007", zhyl210402200Json, "list");
                            transJson(bingliMap, key, "zhyl210402008", zhyl210402200Json, "list");
                            zhyl210402000Json.put("zhyl210402200", zhyl210402200Json);//igg属于免疫荧光

                            transJson(bingliMap, key, "zhyl210402009", zhyl210402300Json, null);
                            transJson(bingliMap, key, "zhyl210402010", zhyl210402300Json, "list");
                            transJson(bingliMap, key, "zhyl210402011", zhyl210402300Json, "list");
                            zhyl210402000Json.put("zhyl210402300", zhyl210402300Json);//igm属于免疫荧光

                            transJson(bingliMap, key, "zhyl210402012", zhyl210402400Json, null);
                            transJson(bingliMap, key, "zhyl210402013", zhyl210402400Json, "list");
                            transJson(bingliMap, key, "zhyl210402014", zhyl210402400Json, "list");
                            zhyl210402000Json.put("zhyl210402400", zhyl210402400Json);//c3属于免疫荧光

                            transJson(bingliMap, key, "zhyl210402015", zhyl210402500Json, null);
                            transJson(bingliMap, key, "zhyl210402016", zhyl210402500Json, "list");
                            transJson(bingliMap, key, "zhyl210402017", zhyl210402500Json, "list");
                            zhyl210402000Json.put("zhyl210402500", zhyl210402500Json);//c1q属于免疫荧光

                            transJson(bingliMap, key, "zhyl210402018", zhyl210402600Json, null);
                            transJson(bingliMap, key, "zhyl210402019", zhyl210402600Json, "list");
                            transJson(bingliMap, key, "zhyl210402020", zhyl210402600Json, "list");
                            zhyl210402000Json.put("zhyl210402600", zhyl210402600Json);//c4属于免疫荧光

                            transJson(bingliMap, key, "zhyl210402021", zhyl210402700Json, null);
                            transJson(bingliMap, key, "zhyl210402022", zhyl210402700Json, "list");
                            transJson(bingliMap, key, "zhyl210402023", zhyl210402700Json, "list");
                            zhyl210402000Json.put("zhyl210402700", zhyl210402700Json);//c4属于免疫荧光

                            transJson(bingliMap, key, "zhyl210403001", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403002", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403003", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403004", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403005", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403006", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403007", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403008", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403009", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403010", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403011", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403012", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403013", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403014", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403015", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403016", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403058", zhyl210403000Json, "list");
                            transJson(bingliMap, key, "zhyl210403017", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403018", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403019", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403020", zhyl210403000Json, null);
                            transJson(bingliMap, key, "zhyl210403021", zhyl210403000Json, null);

                            transJson(bingliMap, key, "zhyl210403022", zhyl210403100Json, null);
                            transJson(bingliMap, key, "zhyl210403023", zhyl210403100Json, null);
                            transJson(bingliMap, key, "zhyl210403024", zhyl210403100Json, null);
                            transJson(bingliMap, key, "zhyl210403025", zhyl210403100Json, null);
                            zhyl210403000Json.put("zhyl210403100", zhyl210403100Json); //肾小球属于光镜

                            transJson(bingliMap, key, "zhyl210403026", zhyl210403200Json, null);
                            transJson(bingliMap, key, "zhyl210403027", zhyl210403200Json, null);
                            transJson(bingliMap, key, "zhyl210403028", zhyl210403200Json, null);
                            transJson(bingliMap, key, "zhyl210403029", zhyl210403200Json, null);
                            zhyl210403000Json.put("zhyl210403200", zhyl210403200Json); //毛内细胞增生病变属于光镜


                            transJson(bingliMap, key, "zhyl210403030", zhyl210403300Json, null);
                            transJson(bingliMap, key, "zhyl210403031", zhyl210403300Json, null);
                            transJson(bingliMap, key, "zhyl210403032", zhyl210403300Json, null);
                            transJson(bingliMap, key, "zhyl210403033", zhyl210403300Json, null);
                            transJson(bingliMap, key, "zhyl210403034", zhyl210403300Json, null);
                            zhyl210403000Json.put("zhyl210403300", zhyl210403300Json); //毛细血管外病变-细胞性新月体变属于光镜

                            transJson(bingliMap, key, "zhyl210403035", zhyl210403400Json, null);
                            transJson(bingliMap, key, "zhyl210403036", zhyl210403400Json, null);
                            transJson(bingliMap, key, "zhyl210403037", zhyl210403400Json, null);
                            transJson(bingliMap, key, "zhyl210403038", zhyl210403400Json, null);
                            transJson(bingliMap, key, "zhyl210403039", zhyl210403400Json, null);
                            zhyl210403000Json.put("zhyl210403400", zhyl210403400Json); //毛细血管外病变-细胞纤维性新月体

                            transJson(bingliMap, key, "zhyl210403040", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403041", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403042", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403043", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403044", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403045", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403046", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403047", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403048", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403049", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403050", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403051", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403052", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403053", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403054", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403055", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403056", zhyl210403500Json, null);
                            transJson(bingliMap, key, "zhyl210403057", zhyl210403500Json, null);
                            zhyl210403000Json.put("zhyl210403500", zhyl210403500Json); //毛细血管外病变-纤维性新月体


                            transJson(bingliMap, key, "zhyl210404001", zhyl210404000Json, null);
                            transJson(bingliMap, key, "zhyl210404002", zhyl210404000Json, null);
                            transJson(bingliMap, key, "zhyl210404003", zhyl210404000Json, null);
                            transJson(bingliMap, key, "zhyl210404004", zhyl210404000Json, null);
                            transJson(bingliMap, key, "zhyl210404005", zhyl210404000Json, null);
                        }


                        bingliMap.clear();//清空bingliMap

                        bingliMap.put("zhyl210401000", zhyl210401000Json);
                        bingliMap.put("zhyl210402000", zhyl210402000Json);
                        bingliMap.put("zhyl210403000", zhyl210403000Json);
                        bingliMap.put("zhyl210404000", zhyl210404000Json);
                    }


                    zhyl200000000Json.put("zhyl210400000", bingliMap); //病理检查
                    //------------------------------------------------------------------------------------------


                    String newJiYinSql = jiYinBiaoBenSql.replace("?", s);

                    Map<String, Object> jiYinMap = commonExecute1(connection, newJiYinSql, statement, resultSet);
                    JSONArray zhyl210501000Json = new JSONArray(); //基因检测
                    JSONObject zhyl210502000Json = new JSONObject(); //标本库
                    JSONArray zhyl210502010Json = new JSONArray(); //皮肤
                    JSONArray zhyl210502020Json = new JSONArray(); //肾脏
                    JSONArray zhyl210502030Json = new JSONArray(); //DNA
                    JSONArray zhyl210502040Json = new JSONArray(); //血清
                    JSONArray zhyl210502050Json = new JSONArray(); //尿液

                    if (jiYinMap != null && jiYinMap.size() > 0) {
                        for (String key : jiYinMap.keySet()) {
                            if (jiYinMap.keySet().contains("datas") && key.equals("datas")) {   //解析datas中的数据
                                if (jiYinMap.get(key) != null) {
                                    JSONArray jiYinList = JSONArray.parseArray(String.valueOf(jiYinMap.get(key)));
                                    for (Object o : jiYinList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("type")) {
                                            newJsonObj.put("zhyl210501001", jsonObj.get("zhyl210501001"));
                                        }
                                        if (jsonObj.keySet().contains("no")) {
                                            newJsonObj.put("zhyl210501002", jsonObj.get("no"));
                                        }
                                        if (jsonObj.keySet().contains("source")) {
                                            newJsonObj.put("zhyl210501003", jsonObj.get("source"));
                                        }
                                        if (jsonObj.keySet().contains("check_time")) {
                                            newJsonObj.put("zhyl210501004", transformTime1(jsonObj.get("check_time")));
                                        }
                                        if (jsonObj.keySet().contains("result")) {
                                            newJsonObj.put("zhyl210501005", jsonObj.get("result"));
                                        }
                                        zhyl210501000Json.add(newJsonObj);
                                    }
                                }
                            }

                            if (jiYinMap.keySet().contains("bf_data") && key.equals("bf_data")) {   //解析bf_data中的数据
                                if (jiYinMap.get(key) != null) {
                                    JSONArray jiYinList = JSONArray.parseArray(String.valueOf(jiYinMap.get(key)));
                                    for (Object o : jiYinList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("bfnum")) {
                                            newJsonObj.put("zhyl210502011", jsonObj.get("bfnum"));
                                        }
                                        if (jsonObj.keySet().contains("bflqcj")) {
                                            newJsonObj.put("zhyl210502012", transformTime1(jsonObj.get("bflqcj")));
                                        }
                                        if (jsonObj.keySet().contains("bftj")) {
                                            newJsonObj.put("zhyl210502013", jsonObj.get("bftj"));
                                        }
                                        if (jsonObj.keySet().contains("bfqfwq")) {
                                            newJsonObj.put("zhyl210502014", jsonObj.get("bfqfwq"));
                                        }
                                        if (jsonObj.keySet().contains("bfsycj")) {
                                            newJsonObj.put("zhyl210502015", transformTime1(jsonObj.get("bfsycj")));
                                        }
                                        if (jsonObj.keySet().contains("bfsyl")) {
                                            newJsonObj.put("zhyl210502016", jsonObj.get("bfsyl"));
                                        }
                                        zhyl210502010Json.add(newJsonObj);
                                    }
                                }
                            }
                            zhyl210502000Json.put("zhyl210502010", zhyl210502010Json); //皮肤属于标本库


                            if (jiYinMap.keySet().contains("xz_data") && key.equals("xz_data")) {   //解析xz_data中的数据
                                if (jiYinMap.get(key) != null) {
                                    JSONArray jiYinList = JSONArray.parseArray(String.valueOf(jiYinMap.get(key)));
                                    for (Object o : jiYinList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("xznum")) {
                                            newJsonObj.put("zhyl210502021", jsonObj.get("xznum"));
                                        }
                                        if (jsonObj.keySet().contains("xzlqcj")) {
                                            newJsonObj.put("zhyl210502022", transformTime1(jsonObj.get("xzlqcj")));
                                        }
                                        if (jsonObj.keySet().contains("xztj")) {
                                            newJsonObj.put("zhyl210502023", jsonObj.get("xztj"));
                                        }
                                        if (jsonObj.keySet().contains("xzqfwq")) {
                                            newJsonObj.put("zhyl210502024", jsonObj.get("xzqfwq"));
                                        }
                                        if (jsonObj.keySet().contains("xzsycj")) {
                                            newJsonObj.put("zhyl210502025", transformTime1(jsonObj.get("xzsycj")));
                                        }
                                        if (jsonObj.keySet().contains("xzsyl")) {
                                            newJsonObj.put("zhyl210502026", jsonObj.get("xzsyl"));
                                        }
                                        zhyl210502020Json.add(newJsonObj);
                                    }
                                }
                            }
                            zhyl210502000Json.put("zhyl210502020", zhyl210502020Json); //肾脏属于标本库


                            if (jiYinMap.keySet().contains("dna_data") && key.equals("dna_data")) {   //解析dna_data中的数据
                                if (jiYinMap.get(key) != null) {
                                    JSONArray jiYinList = JSONArray.parseArray(String.valueOf(jiYinMap.get(key)));
                                    for (Object o : jiYinList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("dnanum")) {
                                            newJsonObj.put("zhyl210502031", jsonObj.get("dnanum"));
                                        }
                                        if (jsonObj.keySet().contains("dnalqcj")) {
                                            newJsonObj.put("zhyl210502032", transformTime1(jsonObj.get("dnalqcj")));
                                        }
                                        if (jsonObj.keySet().contains("dnatj")) {
                                            newJsonObj.put("zhyl210502033", jsonObj.get("dnatj"));
                                        }
                                        if (jsonObj.keySet().contains("dnaod")) {
                                            newJsonObj.put("zhyl210502034", jsonObj.get("dnaod"));
                                        }
                                        if (jsonObj.keySet().contains("dnald")) {
                                            newJsonObj.put("zhyl210502035", jsonObj.get("dnald"));
                                        }
                                        if (jsonObj.keySet().contains("dnaqfwq")) {
                                            newJsonObj.put("zhyl210502036", jsonObj.get("dnaqfwq"));
                                        }
                                        if (jsonObj.keySet().contains("dnasycj")) {
                                            newJsonObj.put("zhyl210502037", transformTime1(jsonObj.get("dnasycj")));
                                        }
                                        if (jsonObj.keySet().contains("dnasyl")) {
                                            newJsonObj.put("zhyl210502038", jsonObj.get("dnasyl"));
                                        }
                                        zhyl210502030Json.add(newJsonObj);
                                    }
                                }
                            }
                            zhyl210502000Json.put("zhyl210502030", zhyl210502030Json); //dna属于标本库


                            if (jiYinMap.keySet().contains("xq_data") && key.equals("xq_data")) {   //解析xq_data中的数据
                                if (jiYinMap.get(key) != null) {
                                    JSONArray jiYinList = JSONArray.parseArray(String.valueOf(jiYinMap.get(key)));
                                    for (Object o : jiYinList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("xqnum")) {
                                            newJsonObj.put("zhyl210502041", jsonObj.get("xqnum"));
                                        }
                                        if (jsonObj.keySet().contains("xqlqcj")) {
                                            newJsonObj.put("zhyl210502042", transformTime1(jsonObj.get("xqlqcj")));
                                        }
                                        if (jsonObj.keySet().contains("xqtj")) {
                                            newJsonObj.put("zhyl210502043", jsonObj.get("xqtj"));
                                        }
                                        if (jsonObj.keySet().contains("xqqfwq")) {
                                            newJsonObj.put("zhyl210502044", jsonObj.get("xqqfwq"));
                                        }
                                        if (jsonObj.keySet().contains("xqsycj")) {
                                            newJsonObj.put("zhyl210502045", transformTime1(jsonObj.get("xqsycj")));
                                        }
                                        if (jsonObj.keySet().contains("xqsyl")) {
                                            newJsonObj.put("zhyl210502046", jsonObj.get("xqsyl"));
                                        }

                                        zhyl210502040Json.add(newJsonObj);
                                    }
                                }
                            }
                            zhyl210502000Json.put("zhyl210502040", zhyl210502040Json); //血清属于标本库

                            if (jiYinMap.keySet().contains("yy_data") && key.equals("yy_data")) {   //解析yy_data中的数据
                                if (jiYinMap.get(key) != null) {
                                    JSONArray jiYinList = JSONArray.parseArray(String.valueOf(jiYinMap.get(key)));
                                    for (Object o : jiYinList) {
                                        JSONObject jsonObj = JSONObject.parseObject(String.valueOf(o));
                                        JSONObject newJsonObj = new JSONObject();
                                        if (jsonObj.keySet().contains("yynum")) {
                                            newJsonObj.put("zhyl210502051", jsonObj.get("yynum"));
                                        }
                                        if (jsonObj.keySet().contains("yylqcj")) {
                                            newJsonObj.put("zhyl210502052", transformTime1(jsonObj.get("yylqcj")));
                                        }
                                        if (jsonObj.keySet().contains("yytj")) {
                                            newJsonObj.put("zhyl210502053", jsonObj.get("yytj"));
                                        }
                                        if (jsonObj.keySet().contains("yyqfwq")) {
                                            newJsonObj.put("zhyl210502054", jsonObj.get("yyqfwq"));
                                        }
                                        if (jsonObj.keySet().contains("yysycj")) {
                                            newJsonObj.put("zhyl210502055", transformTime1(jsonObj.get("yysycj")));
                                        }
                                        if (jsonObj.keySet().contains("yysyl")) {
                                            newJsonObj.put("zhyl210502056", jsonObj.get("yysyl"));
                                        }
                                        zhyl210502050Json.add(newJsonObj);
                                    }
                                }
                            }
                            zhyl210502000Json.put("zhyl210502050", zhyl210502050Json); //尿液属于标本库

                        }

                        jiYinMap.remove("datas");
                        jiYinMap.remove("bf_data");
                        jiYinMap.remove("xz_data");
                        jiYinMap.remove("dna_data");
                        jiYinMap.remove("xq_data");
                        jiYinMap.remove("yy_data");
                        jiYinMap.put("zhyl210501000", zhyl210501000Json);
                        jiYinMap.put("zhyl210502000", zhyl210502000Json);
                    }

                    zhyl200000000Json.put("zhyl210500000", jiYinMap); //基因标本库
//---------------------------------------------------------------------------------------

                    String newchaoShenSql = chaoShenSql.replace("?", s);
                    Map<String, Object> chaoShenMap = commonExecute1(connection, newchaoShenSql, statement, resultSet);
                    JSONObject zhyl210602000Json = new JSONObject();//左肾
                    JSONObject zhyl210603000Json = new JSONObject();//右肾
                    JSONObject zhyl210604000Json = new JSONObject();//超声所见

                    if (chaoShenMap != null && chaoShenMap.size() > 0) {
                        for (String key : chaoShenMap.keySet()) {
                            transJson(chaoShenMap, key, "zhyl210602001", zhyl210602000Json, null);
                            transJson(chaoShenMap, key, "zhyl210602002", zhyl210602000Json, null);
                            transJson(chaoShenMap, key, "zhyl210602003", zhyl210602000Json, null);
                            transJson(chaoShenMap, key, "zhyl210602004", zhyl210602000Json, null);
                            transJson(chaoShenMap, key, "zhyl210602005", zhyl210602000Json, null);
                            transJson(chaoShenMap, key, "zhyl210602006", zhyl210602000Json, null);

                            transJson(chaoShenMap, key, "zhyl210603001", zhyl210603000Json, null);
                            transJson(chaoShenMap, key, "zhyl210603002", zhyl210603000Json, null);
                            transJson(chaoShenMap, key, "zhyl210603003", zhyl210603000Json, null);
                            transJson(chaoShenMap, key, "zhyl210603004", zhyl210603000Json, null);
                            transJson(chaoShenMap, key, "zhyl210603005", zhyl210603000Json, null);
                            transJson(chaoShenMap, key, "zhyl210603006", zhyl210603000Json, null);

                            transJson(chaoShenMap, key, "zhyl210604001", zhyl210604000Json, null);

                        }
                        chaoShenMap.remove("zhyl210602001");
                        chaoShenMap.remove("zhyl210602002");
                        chaoShenMap.remove("zhyl210602003");
                        chaoShenMap.remove("zhyl210602004");
                        chaoShenMap.remove("zhyl210602005");
                        chaoShenMap.remove("zhyl210602006");
                        chaoShenMap.put("zhyl210602000", zhyl210602000Json);

                        chaoShenMap.remove("zhyl210603001");
                        chaoShenMap.remove("zhyl210603002");
                        chaoShenMap.remove("zhyl210603003");
                        chaoShenMap.remove("zhyl210603004");
                        chaoShenMap.remove("zhyl210603005");
                        chaoShenMap.remove("zhyl210603006");
                        chaoShenMap.put("zhyl210603000", zhyl210603000Json);

                        chaoShenMap.remove("zhyl210604001");
                        chaoShenMap.put("zhyl210604000", zhyl210604000Json);

                    }
                    zhyl200000000Json.put("zhyl210600000", chaoShenMap);//超声
                    //----------------------------------------------------------------------
                    String newyaoWuSql = yaoWuSql.replace("?", s);
                    List<Map<String, Object>> newyaoWuSqlList = commonExecute2(connection, newyaoWuSql, statement, resultSet);//药物有多条
                    JSONObject zhyl210700000Json = new JSONObject();//药物治疗 包括药物和冲击
                    JSONArray zhyl210701000Json = new JSONArray(); //药物应该是个list

                    if (newyaoWuSqlList != null && newyaoWuSqlList.size() > 0) {
                        for (Map<String, Object> yaoWuMap : newyaoWuSqlList) {
                            if (yaoWuMap != null && yaoWuMap.size() > 0) {
                                JSONObject zhyl210701100Json = new JSONObject();//药品名称
                                zhyl210701100Json.put("zhyl210701100", yaoWuMap);
                                zhyl210701000Json.add(zhyl210701100Json);
                            }
                        }

                    }
                    zhyl210700000Json.put("zhyl210701000", zhyl210701000Json); //----药物
                    //----------------------------------------------

                    JSONArray zhyl210702000Json = new JSONArray(); //冲击应该是个list
                    JSONArray zhyl210702003Json1 = new JSONArray(); //冲击治疗疗程
                    JSONArray zhyl210702003Json2 = new JSONArray(); //冲击治疗疗程

                    String newchongJi1Sql = chongJi1Sql.replace("?", s);
                    String newchongJi2Sql = chongJi2Sql.replace("?", s);
                    List<Map<String, Object>> newchongJi1List = commonExecute2(connection, newchongJi1Sql, statement, resultSet);//甲泼尼龙冲击
                    List<Map<String, Object>> newchongJi2List = commonExecute2(connection, newchongJi2Sql, statement, resultSet);//环磷酰胺冲击

                    Map<String, Object> newChongji1Map = new HashMap<>();
                    if (newchongJi1List != null && newchongJi1List.size() > 0) {
                        for (Map<String, Object> chongji1Map : newchongJi1List) {
                            JSONObject newJsonObj = new JSONObject();
                            for (String key : chongji1Map.keySet()) {
                                if (chongji1Map.keySet().contains("zhyl210702001") && key.equals("zhyl210702001")) {
                                    newChongji1Map.put("zhyl210702001", chongji1Map.get(key));
                                }
                                if (chongji1Map.keySet().contains("zhyl210702002") && key.equals("zhyl210702002")) {
                                    newChongji1Map.put("zhyl210702002", chongji1Map.get(key)); //固定
                                }
                                if (chongji1Map.keySet().contains("zhyl210702004") && key.equals("zhyl210702004")) {
                                    newJsonObj.put("zhyl210702004", chongji1Map.get(key));
                                }
                                if (chongji1Map.keySet().contains("zhyl210702005") && key.equals("zhyl210702005")) {
                                    newJsonObj.put("zhyl210702005", chongji1Map.get(key));
                                }
                                if (chongji1Map.keySet().contains("zhyl210702006") && key.equals("zhyl210702006")) {
                                    newJsonObj.put("zhyl210702006", chongji1Map.get(key));
                                }
                                if (chongji1Map.keySet().contains("zhyl210702007") && key.equals("zhyl210702007")) {
                                    newJsonObj.put("zhyl210702007", chongji1Map.get(key));
                                }
                                if (chongji1Map.keySet().contains("zhyl210702008") && key.equals("zhyl210702008")) {
                                    newJsonObj.put("zhyl210702008", chongji1Map.get(key));
                                }
                            }
                            if (newJsonObj.keySet().size() > 0) {
                                zhyl210702003Json1.add(newJsonObj);
                            }
                        }

                        newChongji1Map.put("zhyl210702003", zhyl210702003Json1);//集合
                        zhyl210702000Json.add(newChongji1Map);
                    }


                    Map<String, Object> newChongji2Map = new HashMap<>();

                    if (newchongJi2List != null && newchongJi2List.size() > 0) {
                        for (Map<String, Object> chongji2Map : newchongJi2List) {
                            JSONObject newJsonObj1 = new JSONObject();
                            for (String key : chongji2Map.keySet()) {
                                if (chongji2Map.keySet().contains("zhyl210702001") && key.equals("zhyl210702001")) {
                                    newChongji2Map.put("zhyl210702001", chongji2Map.get(key));
                                }
                                if (chongji2Map.keySet().contains("zhyl210702002") && key.equals("zhyl210702002")) {
                                    newChongji2Map.put("zhyl210702002", chongji2Map.get(key)); //固定
                                }

                                if (chongji2Map.keySet().contains("zhyl210702004") && key.equals("zhyl210702004")) {
                                    newJsonObj1.put("zhyl210702004", chongji2Map.get(key));
                                }
                                if (chongji2Map.keySet().contains("zhyl210702005") && key.equals("zhyl210702005")) {
                                    newJsonObj1.put("zhyl210702005", chongji2Map.get(key));
                                }
                                if (chongji2Map.keySet().contains("zhyl210702006") && key.equals("zhyl210702006")) {
                                    newJsonObj1.put("zhyl210702006", chongji2Map.get(key));
                                }
                                if (chongji2Map.keySet().contains("zhyl210702007") && key.equals("zhyl210702007")) {
                                    newJsonObj1.put("zhyl210702007", chongji2Map.get(key));
                                }
                                if (chongji2Map.keySet().contains("zhyl210702008") && key.equals("zhyl210702008")) {
                                    newJsonObj1.put("zhyl210702008", chongji2Map.get(key));
                                }
                            }
                            if (newJsonObj1.keySet().size() > 0) {
                                zhyl210702003Json2.add(newJsonObj1);
                            }

                        }
                        newChongji2Map.put("zhyl210702003", zhyl210702003Json2);//集合
                        zhyl210702000Json.add(newChongji2Map);
                    }


                    zhyl210700000Json.put("zhyl210702000", zhyl210702000Json);//----冲击

                    zhyl200000000Json.put("zhyl210700000", zhyl210700000Json);//药物和冲击


                    jsonObject.put("zhyl200000000", zhyl200000000Json);
                    //-----------------------------------------------------------zhyl200000000


                    System.out.println(jsonObject);


                }

            }


        }


    }


    private static void transJson(Map<String, Object> basicLabMap, String key, String x, JSONObject json, String list) {
        if (basicLabMap.keySet().contains(x) && key.equals(x) && list == null) {
            json.put(x, basicLabMap.get(key));
        }
        if (basicLabMap.keySet().contains(x) && key.equals(x) && list != null) {
            if (list.equals("list")) {
                JSONArray jsonArray = new JSONArray();
                String s = String.valueOf(basicLabMap.get(key));
                if (s != null && s.contains(",")) {
                    jsonArray.addAll(Arrays.asList(s.split(",")));
                }
                if (s != null && !s.contains(",") && !s.equals("null")) {
                    jsonArray.add(Integer.valueOf(s));
                }
                json.put(key, jsonArray);
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
