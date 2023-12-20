package com.igA.demo.constant;

import com.alibaba.fastjson.JSONObject;

public class DataTransform {



    public static JSONObject transformDrug(JSONObject jsonObj, Object o) {
        if (String.valueOf(o).equals("6")) {
            jsonObj.put("MED_NAME", "11");
        }
        if (String.valueOf(o).equals("7")) {
            jsonObj.put("MED_NAME", "12");
        }
        if (String.valueOf(o).equals("8")) {
            jsonObj.put("MED_NAME", "13");
        }
        if (String.valueOf(o).equals("9")) {
            jsonObj.put("MED_NAME", "14");
        }
        if (String.valueOf(o).equals("10")) {
            jsonObj.put("MED_NAME", "15");
        }
        if (String.valueOf(o).equals("34")) {
            jsonObj.put("MED_NAME", "16");
        }
        if (String.valueOf(o).equals("11")) {
            jsonObj.put("MED_NAME", "21");
        }
        if (String.valueOf(o).equals("29")) {
            jsonObj.put("MED_NAME", "22");
        }
        if (String.valueOf(o).equals("12")) {
            jsonObj.put("MED_NAME", "23");
        }
        if (String.valueOf(o).equals("13")) {
            jsonObj.put("MED_NAME", "24");
        }

        if (String.valueOf(o).equals("14")) {
            jsonObj.put("MED_NAME", "25");
        }
        if (String.valueOf(o).equals("15")) {
            jsonObj.put("MED_NAME", "26");
        }
        if (String.valueOf(o).equals("16")) {
            jsonObj.put("MED_NAME", "27");
        }
        if (String.valueOf(o).equals("32")) {
            jsonObj.put("MED_NAME", "28");
        }
        if (String.valueOf(o).equals("33")) {
            jsonObj.put("MED_NAME", "29");
        }
        if (String.valueOf(o).equals("18")) {
            jsonObj.put("MED_NAME", "41");
        }
        if (String.valueOf(o).equals("19")) {
            jsonObj.put("MED_NAME", "42");
        }
        if (String.valueOf(o).equals("20")) {
            jsonObj.put("MED_NAME", "43");
        }
        if (String.valueOf(o).equals("21")) {
            jsonObj.put("MED_NAME", "44");
        }
        if (String.valueOf(o).equals("22")) {
            jsonObj.put("MED_NAME", "45");
        }
        if (String.valueOf(o).equals("23")) {
            jsonObj.put("MED_NAME", "46");
        }

        if (String.valueOf(o).equals("25")) {
            jsonObj.put("MED_NAME", "51");
        }

        if (String.valueOf(o).equals("26")) {
            jsonObj.put("MED_NAME", "52");
        }
        if (String.valueOf(o).equals("27")) {
            jsonObj.put("MED_NAME", "53");
        }

        if (String.valueOf(o).equals("28")) {
            jsonObj.put("MED_NAME", "54");
        }
        if (String.valueOf(o).equals("31")) {
            jsonObj.put("MED_NAME", "99");
        }
        if (String.valueOf(o).equals("17")) {
            jsonObj.put("MED_NAME", "61");
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
            jsonObj.put("FAM_DIS", "血尿");
        }
        if (String.valueOf(o).equals("2")) {
            jsonObj.put("FAM_DIS", "蛋白尿");
        }
        if (String.valueOf(o).equals("3")) {
            jsonObj.put("FAM_DIS", "急性肾小球肾炎");
        }
        if (String.valueOf(o).equals("4")) {
            jsonObj.put("FAM_DIS", "急进性肾小球肾炎");
        }
        if (String.valueOf(o).equals("5")) {
            jsonObj.put("FAM_DIS", "迁延性肾小球肾炎");
        }
        if (String.valueOf(o).equals("6")) {
            jsonObj.put("FAM_DIS", "慢性肾小球肾炎");
        }
        if (String.valueOf(o).equals("7")) {
            jsonObj.put("FAM_DIS", "IgA肾病");
        }
        if (String.valueOf(o).equals("8")) {
            jsonObj.put("FAM_DIS", "原发性肾病综合征");
        }
        if (String.valueOf(o).equals("10")) {
            jsonObj.put("FAM_DIS", "肾小管间质疾病");
        }
        if (String.valueOf(o).equals("11")) {
            jsonObj.put("FAM_DIS", "泌尿系感染");
        }
        if (String.valueOf(o).equals("12")) {
            jsonObj.put("FAM_DIS", "其它");
        }

        if (String.valueOf(o).equals("9")) {
            jsonObj.put("FAM_DIS", "继发性肾脏疾病");
        }



        return jsonObj;
    }



    public static JSONObject transformIsOrNot(JSONObject jsonObj, Object o) {
        if (String.valueOf(o).equals("1")) {
            jsonObj.put("FAM_PATH", "1");
        }
        if (String.valueOf(o).equals("2")) {
            jsonObj.put("FAM_PATH", "0");
        }
        if (String.valueOf(o).equals("3")) {
            jsonObj.put("FAM_PATH", "9");
        }




        return jsonObj;
    }




}
