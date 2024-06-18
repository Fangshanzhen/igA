package com.igA.demo.test;


import com.igA.demo.data.PediatricKidneyData2;

public class a {
    /**
     * start 1开始 0其他不传数据
     * type:
     * 1		Alport综合征
     * 2		蛋白尿性肾脏疾病
     * 3		肾小管疾病
     * 4		肾脏囊性疾病
     * 5		先天性肾脏尿路畸形
     */


    public static void main(String[] args) throws Exception {


        PediatricKidneyData2.transformData(null, "admin","72d0645981154de34f35e03d06c626cc",
                null,"0","2");
    }


}


