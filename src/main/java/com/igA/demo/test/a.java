package com.igA.demo.test;


import com.igA.demo.data.PediatricKidneyData2;

public class a {
    /**
     * start 1开始 0其他不传数据
     * type:
     * 1		Alport综合征   1979
     * 2		蛋白尿性肾脏疾病 637
     * 3		肾小管疾病 310
     * 4		肾脏囊性疾病  165
     * 5		先天性肾脏尿路畸形  68
     * 0        其它数据筛查表   428
     */


    public static void main(String[] args) throws Exception {


//        PediatricKidneyData2.transformData("http://10.0.108.41/api-gate", "admin","72d0645981154de34f35e03d06c626cc",
//                null,"1","5");


        PediatricKidneyData2.transformData("http://pikic.bjmu.edu.cn/api-gate", "admin","eb0444d18bfa4a6d0fb758201280801f",
                null,"1","0");
    }


}


