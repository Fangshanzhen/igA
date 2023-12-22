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

            idList = Collections.singletonList("195");


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
                                            if(String.valueOf( jsonObj.get("select")).equals("1")){
                                                zhyl210401000Json.put("zhyl210401002", 2);
                                            }
                                            if(String.valueOf( jsonObj.get("select")).equals("2")){
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


                        }

                        bingliMap.remove("def_data");
                        bingliMap.remove("zhyl210401004");

                        bingliMap.remove("zhyl210402001");
                        bingliMap.remove("zhyl210402002");

                        bingliMap.put("zhyl210401000", zhyl210401000Json);
                    }





                    zhyl200000000Json.put("zhyl210400000", bingliMap); //病理检查




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
            if ( list.equals("list")) {
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
