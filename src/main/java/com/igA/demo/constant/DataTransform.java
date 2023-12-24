package com.igA.demo.constant;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
            jsonObj.put(key, "1");
        }
        if (String.valueOf(o).equals("2")) {
            jsonObj.put(key, "0");
        }
        if (String.valueOf(o).equals("3")) {
            jsonObj.put(key, "9");
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
        if (o != null && !String.valueOf(o).equals("") ) {
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
        if (o != null && !String.valueOf(o).equals("")&& String.valueOf(o).contains("-")) {
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

}
