package com.igA.demo.constant;

import com.alibaba.fastjson.JSONObject;

public class Constant {


    public final static String idSql = "select a.id  from hospital.hs_patient a ";


    public final static String patientSql = "   select a.id as zhyl110000001,\n" +
            "b.id  as zhyl110000002, --产品配置\n" +
            "a.caseno as zhyl110000003,\n" +
            "a.patientname as zhyl110000004,\n" +
            "case when a.sex=0 then 1 when a.sex=1 then 2 end as zhyl110000005,\n" +
            "(EXTRACT(EPOCH FROM a.birthday::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as zhyl110000006,\n" +
            "d.dictname as zhyl110000007, --需要转换\n" +
            "(EXTRACT(EPOCH FROM a.agreetime::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as  zhyl110000008,\n" +
            "(EXTRACT(EPOCH FROM a.knowtime::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as  zhyl110000009,\n" +
            "case when a.certype='3' then 1\n" +
            "when a.certype='126' then 2\n" +
            "when a.certype='127' then 3\n" +
            "when a.certype='4' then 4\n" +
            "when a.certype='5' then 5\n" +
            "when a.certype='6' then 6 end as zhyl110000010, \n" +
            "a.cernum as zhyl110000011,\n" +
            "\n" +
            "f.\"name\"||g.\"name\"||h.\"name\" as zhyl110100001,--需要转换\n" +
            "a.address as zhyl110100002,\n" +
            "a.code as zhyl110100003,\n" +
            "\n" +
            " a.contact_data \n" +
            " \n" +
            " from hospital.hs_patient a \n" +
            "left join hospital.hs_hospital b on a.hospitalid=b.id\n" +
            "left join hospital.hs_dcit c on cast(a.certype as integer) =c.id\n" +
            "left join hospital.hs_dcit d on cast(a.marriaged as integer) =d.id\n" +
            "\n" +
            "left join hospital.hs_region f on cast(a.province as integer) =f.id\n" +
            "left join hospital.hs_region g on cast(a.city as integer) =g.id\n" +
            "left join hospital.hs_region h on cast(a.area as integer) =h.id\n" +
            "\n" +
            "where a.id='?'";


    public final static String patientInfoSql ="select \n" +
            "(EXTRACT(EPOCH FROM b.intime::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as  zhyl200000001,\n" +
            "(EXTRACT(EPOCH FROM b.intime::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as  zhyl210000001,\n" +
            "case when b.examinetype='17' then 1 when b.examinetype='16' then 2 when b.examinetype='131' then 3 end as zhyl210000002,\n" +
            "(EXTRACT(EPOCH FROM b.intime::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as  zhyl210000003,\n" +
            "(EXTRACT(EPOCH FROM b.intime::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as  zhyl210000004,\n" +
            "(EXTRACT(EPOCH FROM b.outtime::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as  zhyl210000005\n" +
            "\n" +
            "\n" +
            "from hospital.hs_patient a \n" +
            "inner join hospital.hs_patientinfo b on a.id=b.patientid \n" +
            "left join hospital.hs_hospital c on a.hospitalid=c.id \n" +
            "where  b.reporttype='首诊报告' and b.historyflag=0  and  a.id='?'"
            ;


    public final static String withDrawSql ="select \n" +
            "a.id , c.hospitalcode  as center, a.agreetime as time_enroll, a.outtime as WITHDRAW_TIME,\n" +
            "case when a .reason='113' then 1   \n" +
            "when a .reason='114' then 2 \n" +
            "when a .reason='115' then 3\n" +
            "when a .reason='116' then 4  end as  WITHDRAW_REASON,\n" +
            "a.outremark as WITHDRAW_OTH\n" +
            "from hospital.hs_patient a \n" +
            "left join hospital.hs_hospital c on a.hospitalid=c.id where   a.id='?' ";


    public final static String baseLineHisSql ="  select \n" +
            " case when d.xn='1'then 1 when d.xn='2'then 0 when d.xn='3'then 9 end as zhyl210100001,\n" +
            " (EXTRACT(EPOCH FROM d.xn_time::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as zhyl210100002,\n" +
            " case when d.ryxn='1'then 1 when d.ryxn='2'then 0 when d.ryxn='3'then 9 end as zhyl210100003,\n" +
            " case when d.ryxn_attr='1'then 1 when d.ryxn_attr='2'then 2 when d.ryxn_attr='3'then 9 end as zhyl210100004,\n" +
            " case when d.dbn='1'then 1 when d.dbn='2'then 0 when d.dbn='3'then 9 end as zhyl210100005,\n" +
            "  (EXTRACT(EPOCH FROM d.dbn_time ::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as  zhyl210100006,\n" +
            "  case when d.dbn_attr='1'then 1 when d.dbn_attr='2'then 2 when d.dbn_attr='3'then 9 end as zhyl210100007,\n" +
            "  case when d.sgnyc='1'then 1 when d.sgnyc='2'then 0 when d.sgnyc='3'then 9 end as zhyl210100008,\n" +
            "  (EXTRACT(EPOCH FROM d.sgnyc_time ::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as  zhyl210100009,\n" +
            "  case when d.sgnyc_attr='1'then 1 when d.sgnyc_attr='2'then 2 when d.sgnyc_attr='3'then 9 end as zhyl210100010,\n" +
            "  case when d.gxy='1'then 1 when d.gxy='2'then 0 when d.gxy='3'then 9 end as zhyl210100011,\n" +
            "    (EXTRACT(EPOCH FROM d.gxy_time ::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as  zhyl210100012,\n" +
            "    null as zhyl210100013,\n" +
            "  case when d.pz='1'then 1 when d.pz='2'then 0 when d.pz='3'then 9 end as   zhyl210100014,\n" +
            "  d.gjttxm as zhyl210100015,\n" +
            "case when d.gjtt='1'then 1 when d.gjtt='2'then 0 when d.gjtt='3'then 9 end as zhyl210100016,\n" +
            "d.gjttxm as zhyl210100017,\n" +
            "case when d.fz='1'then 1 when d.fz='2'then 0 when d.fz='3'then 9 end as zhyl210100018,\n" +
            "case when d.yys='1'then 1 when d.yys='2'then 0 when d.yys='3'then 9 end as zhyl210100019,\n" +
            " d.yys_data, --用药\n" +
            "case when d.shjyy='1'then 1 when d.shjyy='2'then 0 when d.shjyy='3'then 9 end as zhyl210100020,\n" +
            "case when d.acei='1'then 1 when d.acei='2'then 0 when d.acei='3'then 9 end as zhyl210100021,\n" +
            " case when d.arb='1'then 1 when d.arb='2'then 0 when d.arb='3'then 9 end as zhyl210100022,\n" +
            "case when d.ccb='1'then 1 when d.ccb='2'then 0 when d.ccb='3'then 9 end as zhyl210100023,\n" +
            "case when d.qtjyyw='1'then 1 when d.qtjyyw='2'then 0 when d.qtjyyw='3'then 9 end as zhyl210100024,\n" +
            "d.qtjyjwmt as zhyl210100025,\n" +
            "case when d.bttzcs='1'then 1 when d.bttzcs='2'then 0 when d.bttzcs='3'then 9 end as zhyl210100026,\n" +
            "(EXTRACT(EPOCH FROM d.bttzcs_time ::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as  zhyl210100027,\n" +
            "d.\"explain\" as zhyl210100028,\n" +
            "case when d.bxtzcj='1'then 1 when d.bxtzcj='2'then 0 when d.bxtzcj='3'then 9 end as zhyl210100029,\n" +
            "\n" +
            "d.szjbjzs_data--肾脏病家族史\n" +
            "\n" +
            "\n" +
            "from hospital.hs_patient a \n" +
            "inner join hospital.hs_patientinfo b on a.id=b.patientid \n" +
            "left join hospital.hs_hospital c on a.hospitalid=c.id  \n" +
            "left join hospital.hs_caseone d on b.id=d.id\n" +
            "\n" +
            " where  b.reporttype='首诊报告' and b.historyflag=0 and a.id='?'\n";



    public final static String treatmentHistorySql ="select a.id as id ,c.hospitalcode  as center,  a.agreetime as time_enroll,\n" +
            "b.intime as visit_time,\n" +
            "d.yys_data\n" +
            "\n" +
            "\n" +
            "from hospital.hs_patient a \n" +
            "inner join hospital.hs_patientinfo b on a.id=b.patientid \n" +
            "left join hospital.hs_hospital c on a.hospitalid=c.id  \n" +
            "left join hospital.hs_caseone d on b.id=d.id\n" +
            "\n" +
            " where  b.reporttype='首诊报告'  and b.historyflag=0 and   a.id='?' ";


    public final static String familySql ="select a.id as id ,c.hospitalcode  as center,\n" +
            "d.szjbjzs_data\n" +
            "\n" +
            "from hospital.hs_patient a \n" +
            "inner join hospital.hs_patientinfo b on a.id=b.patientid \n" +
            "left join hospital.hs_hospital c on a.hospitalid=c.id  \n" +
            "left join hospital.hs_caseone d on b.id=d.id\n" +
            "\n" +
            " where  b.reporttype='首诊报告'  and b.historyflag=0 and   a.id='?' ";



    public final static String basicPESql =" select \n" +
            "  (EXTRACT(EPOCH FROM d.check_time::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as zhyl210200001,\n" +
            "  d.shengao as zhyl210200002,\n" +
            "  d.tizhong as zhyl210200003,\n" +
            "  d.shousuoya as  zhyl210200006,\n" +
            "  d.shuzhangya as zhyl210200007,\n" +
            "  case when d.fuzhong='1' then 1  when d.fuzhong='2' then 0 \n" +
            "  when d.fuzhong='3' then 9 end as zhyl210200008,\n" +
            "  d.fuzhong_qt as zhyl210200009,\n" +
            "   case when d.fuzhongadd='1' then 1  when d.fuzhongadd='2' then 0 \n" +
            "  when d.fuzhongadd='3' then 9 end as zhyl210200010,\n" +
            "    d.fuzhongadd_qt as zhyl210200011,\n" +
            "      d.remark as zhyl210200012\n" +
            " \n" +
            " \n" +
            "  from hospital.hs_patient a \n" +
            "inner join hospital.hs_patientinfo b on a.id=b.patientid \n" +
            "left join hospital.hs_hospital c on a.hospitalid=c.id  \n" +
            "left join hospital.hs_casetwo d on b.id=d.id\n" +
            "\n" +
            " where  b.reporttype='首诊报告'  and b.historyflag=0 and   a.id='?'  ";

    public final static String basicLabSql =" select  (EXTRACT(EPOCH FROM d.ncg_check_time::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as zhyl210301001,\n" +
            " case when d.ncg_pro='1' then 0 when d.ncg_pro='2' then 0.5  when d.ncg_pro='3' then 1 when d.ncg_pro='4' then 2 when d.ncg_pro='5' then 3 \n" +
            "when d.ncg_pro='6' then 4 end as zhyl210301002,\n" +
            "case when d.ncg_bld='1' then 0 when d.ncg_bld='2' then 1 end as zhyl210301003,\n" +
            "d.ncg_count as zhyl210301004,\n" +
            "d.ncg_hpf as  zhyl210301005,\n" +
            "d.ndbdl_data,\n" +
            "d.ndbjg_data,\n" +
            "d.szzq_data,\n" +
            "d.xcg_data,\n" +
            "(EXTRACT(EPOCH FROM d.shjc_time ::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as zhyl210305001,\n" +
            "d.shjc_alt as zhyl210305002,\n" +
            "d.shjc_ast as zhyl210305003,\n" +
            "d.shjc_ua as zhyl210305004,\n" +
            "d.shjc_ca as zhyl210305005,\n" +
            "d.shjc_p as zhyl210305006,\n" +
            "d.shjc_k as zhyl210305007,\n" +
            "d.shjc_co2 as zhyl210305008,\n" +
            "d.shjc_tg as zhyl210305009,\n" +
            "d.shjc_data,\n" +
            "d.ccr_data,\n" +
            "(EXTRACT(EPOCH FROM d.grsc_time ::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as zhyl210307001,\n" +
            "case when d.grsc_hbsag='1' then 0 when d.grsc_hbsag='2' then 1 end as zhyl210307002,\n" +
            "case when d.grsc_anti_hbs='1' then 0 when d.grsc_anti_hbs='2' then 1 end as zhyl210307003,\n" +
            "case when d.grsc_hbeag='1' then 0 when d.grsc_hbeag='2' then 1 end as zhyl210307004,\n" +
            "case when d.grsc_anti_hbe='1' then 0 when d.grsc_anti_hbe='2' then 1 end as zhyl210307005,\n" +
            "case when d.grsc_anti_hbc='1' then 0 when d.grsc_anti_hbc='2' then 1 end as zhyl210307006,\n" +
            "case when d.grsc_anti_hcv='1' then 0 when d.grsc_anti_hcv='2' then 1 end as zhyl210307007,\n" +
            "case when d.grsc_anti_hiv='1' then 0 when d.grsc_anti_hiv='2' then 1 end as zhyl210307008,\n" +
            "case when d.grsc_tp_ab='1' then 0 when d.grsc_tp_ab='2' then 1 end as zhyl210307009,\n" +
            "(EXTRACT(EPOCH FROM d.myqdb_time ::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as zhyl210308001,\n" +
            "d.myqdb_igg as zhyl210308002,\n" +
            "d.myqdb_iga as zhyl210308003,\n" +
            "d.myqdb_igm as zhyl210308004,\n" +
            "d.myqdb_c3 as zhyl210308005,\n" +
            "d.myqdb_c4 as zhyl210308006,\n" +
            "(EXTRACT(EPOCH FROM d.gmjc_time ::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as zhyl210309001,\n" +
            "d.gmjc_ige as zhyl210309002,\n" +
            "d.gmjc_ecp as zhyl210309003,\n" +
            "(EXTRACT(EPOCH FROM d.zxktjc_time ::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as zhyl210310001,\n" +
            "case when d.zxktjc_ana='1' then 0 when d.zxktjc_ana='2' then 1 end as zhyl210310002,\n" +
            "case when d.zxktjc_dsdna='1' then 0 when d.zxktjc_dsdna='2' then 1 end as zhyl210310003,\n" +
            "case when d.zxktjc_anca='1' then 0 when d.zxktjc_anca='2' then 1 end as zhyl210310004,\n" +
            " d.zxktjc_ancaxm  as zhyl210310005,\n" +
            " d.ngjc_data, \n" +
            " d.ngdl_data,\n" +
            " \n" +
            " (EXTRACT(EPOCH FROM d.ipth_time ::timestamp with time zone AT TIME ZONE 'UTC') * 1000)::VARCHAR as zhyl210312001,\n" +
            "d.ipth_val as zhyl210312002,\n" +
            "d.iga_tjh as zhyl210313001\n" +
            "\n" +
            "\n" +
            "   from hospital.hs_patient a \n" +
            "inner join hospital.hs_patientinfo b on a.id=b.patientid \n" +
            "left join hospital.hs_hospital c on a.hospitalid=c.id  \n" +
            "left join hospital.hs_casethree d on b.id=d.id\n" +
            "\n" +
            " where  b.reporttype='首诊报告'  and b.historyflag=0 and   a.id='?' \n" +
            "  ";



    public final static String bingliSql =" \n" +
            " \n" +
            " \n" +
            " select d.def_data,\n" +
            " d.check_blh as zhyl210401004,\n" +
            " case when d.myyg='1' then 1 when d.myyg='2' then 0 end as zhyl210402001,\n" +
            " d.myyg_xqs as zhyl210402002,\n" +
            " case \n" +
            " when d.iga_rsqd='1' then 1\n" +
            "when d.iga_rsqd='2' then 2\n" +
            "when d.iga_rsqd='3' then 3\n" +
            "when d.iga_rsqd='4' then 4\n" +
            " when d.iga_rsqd='5' then 5\n" +
            "when d.iga_rsqd='6' then 6\n" +
            "     when d.iga_rsqd='7' then 7\n" +
            "      when d.iga_rsqd='7' then 8\n" +
            "       when d.iga_rsqd='8' then 9\n" +
            "        when d.iga_rsqd='9' then 10\n" +
            "         when d.iga_rsqd='10' then 11\n" +
            "          when d.iga_rsqd='11' then 12\n" +
            "           when d.iga_rsqd='12' then 13\n" +
            "            when d.iga_rsqd='13' then 14\n" +
            "             when d.iga_rsqd='14' then 15\n" +
            "              when d.iga_rsqd='15' then 16\n" +
            "               when d.iga_rsqd='16' then 17\n" +
            "                when d.iga_rsqd='17' then 18\n" +
            "                 when d.iga_rsqd='18' then 19\n" +
            "                  when d.iga_rsqd='19' then 20\n" +
            "                   when d.iga_rsqd='20' then 21 end as zhyl210402003,\n" +
            " \n" +
            "   d.iga_cjbw as        zhyl210402004 ,        \n" +
            " d.iga_cjxs as  zhyl210402005,\n" +
            " case when d.igg_rsqd='1' then 1\n" +
            "when d.igg_rsqd='2' then 2\n" +
            "when d.igg_rsqd='3' then 3\n" +
            "when d.igg_rsqd='4' then 4\n" +
            " when d.igg_rsqd='5' then 5\n" +
            "when d.igg_rsqd='6' then 6\n" +
            "     when d.igg_rsqd='7' then 7\n" +
            "      when d.igg_rsqd='7' then 8\n" +
            "       when d.igg_rsqd='8' then 9\n" +
            "        when d.igg_rsqd='9' then 10\n" +
            "         when d.igg_rsqd='10' then 11\n" +
            "          when d.igg_rsqd='11' then 12\n" +
            "           when d.igg_rsqd='12' then 13\n" +
            "            when d.igg_rsqd='13' then 14\n" +
            "             when d.igg_rsqd='14' then 15\n" +
            "              when d.igg_rsqd='15' then 16\n" +
            "               when d.igg_rsqd='16' then 17\n" +
            "                when d.igg_rsqd='17' then 18\n" +
            "                 when d.igg_rsqd='18' then 19\n" +
            "                  when d.igg_rsqd='19' then 20\n" +
            "                   when d.igg_rsqd='20' then 21 end as zhyl210402006,\n" +
            "       d.igg_cjbw as    zhyl210402007,\n" +
            "     d.igg_cjxs as zhyl210402008,    \n" +
            "     \n" +
            "      case when d.igg_rsqd='1' then 1\n" +
            "when d.igm_rsqd='2' then 2\n" +
            "when d.igm_rsqd='3' then 3\n" +
            "when d.igm_rsqd='4' then 4\n" +
            " when d.igm_rsqd='5' then 5\n" +
            "when d.igm_rsqd='6' then 6\n" +
            "     when d.igm_rsqd='7' then 7\n" +
            "      when d.igm_rsqd='7' then 8\n" +
            "       when d.igm_rsqd='8' then 9\n" +
            "        when d.igm_rsqd='9' then 10\n" +
            "         when d.igm_rsqd='10' then 11\n" +
            "          when d.igm_rsqd='11' then 12\n" +
            "           when d.igm_rsqd='12' then 13\n" +
            "            when d.igm_rsqd='13' then 14\n" +
            "             when d.igm_rsqd='14' then 15\n" +
            "              when d.igm_rsqd='15' then 16\n" +
            "               when d.igm_rsqd='16' then 17\n" +
            "                when d.igm_rsqd='17' then 18\n" +
            "                 when d.igm_rsqd='18' then 19\n" +
            "                  when d.igm_rsqd='19' then 20\n" +
            "                   when d.igm_rsqd='20' then 21 end as zhyl210402009,\n" +
            "  \n" +
            "       d.igm_cjbw as    zhyl210402010,\n" +
            "       d.igm_cjxs as zhyl210402011,\n" +
            "       \n" +
            "     case when d.igg_rsqd='1' then 1\n" +
            "when d.c3_rsqd='2' then 2\n" +
            "when d.c3_rsqd='3' then 3\n" +
            "when d.c3_rsqd='4' then 4\n" +
            " when d.c3_rsqd='5' then 5\n" +
            "when d.c3_rsqd='6' then 6\n" +
            "     when d.c3_rsqd='7' then 7\n" +
            "      when d.c3_rsqd='7' then 8\n" +
            "       when d.c3_rsqd='8' then 9\n" +
            "        when d.c3_rsqd='9' then 10\n" +
            "         when d.c3_rsqd='10' then 11\n" +
            "          when d.c3_rsqd='11' then 12\n" +
            "           when d.c3_rsqd='12' then 13\n" +
            "            when d.c3_rsqd='13' then 14\n" +
            "             when d.c3_rsqd='14' then 15\n" +
            "              when d.c3_rsqd='15' then 16\n" +
            "               when d.c3_rsqd='16' then 17\n" +
            "                when d.c3_rsqd='17' then 18\n" +
            "                 when d.c3_rsqd='18' then 19\n" +
            "                  when d.c3_rsqd='19' then 20\n" +
            "                   when d.c3_rsqd='20' then 21 end as zhyl210402012,\n" +
            "           d.c3_cjbw as    zhyl210402013,\n" +
            "         d.c3_cjxs as zhyl210402014, \n" +
            "        case when d.igg_rsqd='1' then 1\n" +
            "when d.c1q_rsqd='2' then 2\n" +
            "when d.c1q_rsqd='3' then 3\n" +
            "when d.c1q_rsqd='4' then 4\n" +
            " when d.c1q_rsqd='5' then 5\n" +
            "when d.c1q_rsqd='6' then 6\n" +
            "     when d.c1q_rsqd='7' then 7\n" +
            "      when d.c1q_rsqd='7' then 8\n" +
            "       when d.c1q_rsqd='8' then 9\n" +
            "        when d.c1q_rsqd='9' then 10\n" +
            "         when d.c1q_rsqd='10' then 11\n" +
            "          when d.c1q_rsqd='11' then 12\n" +
            "           when d.c1q_rsqd='12' then 13\n" +
            "            when d.c1q_rsqd='13' then 14\n" +
            "             when d.c1q_rsqd='14' then 15\n" +
            "              when d.c1q_rsqd='15' then 16\n" +
            "               when d.c1q_rsqd='16' then 17\n" +
            "                when d.c1q_rsqd='17' then 18\n" +
            "                 when d.c1q_rsqd='18' then 19\n" +
            "                  when d.c1q_rsqd='19' then 20\n" +
            "                   when d.c1q_rsqd='20' then 21 end as     zhyl210402015,\n" +
            "                   \n" +
            "     d.c1q_cjbw as zhyl210402016,\n" +
            "d.c1q_cjxs as zhyl210402017,  \n" +
            "\n" +
            "      case when d.igg_rsqd='1' then 1\n" +
            "when d.c4_rsqd='2' then 2\n" +
            "when d.c4_rsqd='3' then 3\n" +
            "when d.c4_rsqd='4' then 4\n" +
            " when d.c4_rsqd='5' then 5\n" +
            "when d.c4_rsqd='6' then 6\n" +
            "     when d.c4_rsqd='7' then 7\n" +
            "      when d.c4_rsqd='7' then 8\n" +
            "       when d.c4_rsqd='8' then 9\n" +
            "        when d.c4_rsqd='9' then 10\n" +
            "         when d.c4_rsqd='10' then 11\n" +
            "          when d.c4_rsqd='11' then 12\n" +
            "           when d.c4_rsqd='12' then 13\n" +
            "            when d.c4_rsqd='13' then 14\n" +
            "             when d.c4_rsqd='14' then 15\n" +
            "              when d.c4_rsqd='15' then 16\n" +
            "               when d.c4_rsqd='16' then 17\n" +
            "                when d.c4_rsqd='17' then 18\n" +
            "                 when d.c4_rsqd='18' then 19\n" +
            "                  when d.c4_rsqd='19' then 20\n" +
            "                   when d.c4_rsqd='20' then 21 end as zhyl210402018,\n" +
            "         d.c4_cjbw as   zhyl210402019   ,\n" +
            "  d.c4_cjxs as zhyl210402020,\n" +
            "        case when d.igg_rsqd='1' then 1\n" +
            "when d.fra_rsqd='2' then 2\n" +
            "when d.fra_rsqd='3' then 3\n" +
            "when d.fra_rsqd='4' then 4\n" +
            " when d.fra_rsqd='5' then 5\n" +
            "when d.fra_rsqd='6' then 6\n" +
            "     when d.fra_rsqd='7' then 7\n" +
            "      when d.fra_rsqd='7' then 8\n" +
            "       when d.fra_rsqd='8' then 9\n" +
            "        when d.fra_rsqd='9' then 10\n" +
            "         when d.fra_rsqd='10' then 11\n" +
            "          when d.fra_rsqd='11' then 12\n" +
            "           when d.fra_rsqd='12' then 13\n" +
            "            when d.fra_rsqd='13' then 14\n" +
            "             when d.fra_rsqd='14' then 15\n" +
            "              when d.fra_rsqd='15' then 16\n" +
            "               when d.fra_rsqd='16' then 17\n" +
            "                when d.fra_rsqd='17' then 18\n" +
            "                 when d.fra_rsqd='18' then 19\n" +
            "                  when d.fra_rsqd='19' then 20\n" +
            "                   when d.fra_rsqd='20' then 21 end as zhyl210402021,\n" +
            "       d.fra_cjbw as zhyl210402022,\n" +
            "d.fra_cjxs as zhyl210402023,  \n" +
            "\n" +
            "case when d.gj='1' then 1  when d.gj='2' then 0 end as zhyl210403001,\n" +
            "d.xxqzs_totalnum as  zhyl210403002,\n" +
            "case when d.ymcbzs='1' then 1  when d.ymcbzs='2' then 0 end as zhyl210403003,\n" +
            "case when d.ymcbzs_drgree='3' then 1 when d.ymcbzs_drgree='4' then 2 \n" +
            "when d.ymcbzs_drgree='7' then 3 when d.ymcbzs_drgree='5' then 4 \n" +
            "when d.ymcbzs_drgree='6' then 5 end as  zhyl210403004,\n" +
            "\n" +
            "d.ymcbzs_zxjs as zhyl210403005,\n" +
            "d.ymcbzs_zxympf as zhyl210403006,\n" +
            "d.ymcbzs_kdzs as zhyl210403007,\n" +
            "d.ymcbzs_kdympf as zhyl210403008,\n" +
            "d.ymcbzs_zdzs as zhyl210403009,\n" +
            "d.ymcbzs_zdympf as zhyl210403010,\n" +
            "d.ymcbzs_zhdzs as zhyl210403011,\n" +
            "d.ymcbzs_zhdzspf as zhyl210403012,\n" +
            "d.ymcbzs_pfxxqzs as zhyl210403013,\n" +
            "d.ymcbzs_pfxxqpf as zhyl210403014,\n" +
            "d.ymcbzs_ympdf as zhyl210403015,\n" +
            "d.ymcbzs_bqds as zhyl210403016,\n" +
            "REPLACE(\n" +
            "        REPLACE(\n" +
            "            REPLACE(\n" +
            "                REPLACE(\n" +
            "                    REPLACE(d.ymcbzs_bqdyy, '125', '5'),\n" +
            "                '124', '4'),\n" +
            "            '123', '3'),\n" +
            "        '121', '1'),\n" +
            "    '122', '2') as zhyl210403058,\n" +
            "    \n" +
            " case when d.ymcbzs_bqdyy is not null and d.ymcbzs_bqdyy like '%121%' then (\n" +
            "case when d.ymcbzs_bqdyynum is not null then SPLIT_PART(d.ymcbzs_bqdyynum, ',', 1) end \n" +
            ") end as zhyl210403017,\n" +
            "case when d.ymcbzs_bqdyy is not null and d.ymcbzs_bqdyy like '%122%' then (\n" +
            "case when d.ymcbzs_bqdyynum is not null then SPLIT_PART(d.ymcbzs_bqdyynum, ',', 2) end \n" +
            ") end as zhyl210403018,\n" +
            "case when d.ymcbzs_bqdyy is not null and d.ymcbzs_bqdyy like '%123%' then (\n" +
            "case when d.ymcbzs_bqdyynum is not null then SPLIT_PART(d.ymcbzs_bqdyynum, ',', 3) end \n" +
            ") end as zhyl210403019,\n" +
            "case when d.ymcbzs_bqdyy is not null and d.ymcbzs_bqdyy like '%124%' then (\n" +
            "case when d.ymcbzs_bqdyynum is not null then SPLIT_PART(d.ymcbzs_bqdyynum, ',', 4) end \n" +
            ") end as zhyl210403020,\n" +
            "case when d.ymcbzs_bqdyy is not null and d.ymcbzs_bqdyy like '%125%' then (\n" +
            "case when d.ymcbzs_bqdyynum is not null then SPLIT_PART(d.ymcbzs_bqdyynum, ',', 5) end \n" +
            ") end as zhyl210403021,\n" +
            "\n" +
            "d.xxqzs_zh as zhyl210403022,\n" +
            "d.xxqzs_zdlh as zhyl210403023,\n" +
            "d.xxqzs_zl as zhyl210403024,\n" +
            "d.xxqzs_tx as zhyl210403025,\n" +
            "d.mxsgzs_zd as zhyl210403026,\n" +
            "d.mxsgzs_qx as zhyl210403027,\n" +
            "d.mxsgzs_xch as zhyl210403028,\n" +
            "d.mxsgzs_hs as zhyl210403029,\n" +
            "\n" +
            "d.mxsgzs_dyws as zhyl210403030,\n" +
            "d.mxsgzs_outnum as zhyl210403031,\n" +
            "d.mxsgzs_xys as zhyl210403032,\n" +
            "d.mxsgzs_szyw as zhyl210403033,\n" +
            "d.mxsgzs_ylzws as zhyl210403034,\n" +
            "d.mxsgpb_dyws as zhyl210403035,\n" +
            "d.mxsgzs_cwsnum as zhyl210403036,\n" +
            "d.mxsgpb_xys as zhyl210403037,\n" +
            "d.mxsgpb_szyw as zhyl210403038,\n" +
            "d.mxsgpb_ylzws as zhyl210403039,\n" +
            "d.mxsgpbcwh_dyws as zhyl210403040,\n" +
            "d.mxsgzs_cwsnum as zhyl210403041,\n" +
            "d.mxsgpbcwh_xys as zhyl210403042,\n" +
            "d.mxsgpbcwh_szyw as zhyl210403043,\n" +
            "d.mxsgpbcwh_ylzws as zhyl210403044,\n" +
            "case when d.c_bcbl ='1' then 1 when d.c_bcbl ='2' then 0 end as zhyl210403045,\n" +
            "d.c_ezs as zhyl210403046,\n" +
            "d.c_jzcwh as zhyl210403047,\n" +
            "d.c_jzyz as zhyl210403048,\n" +
            "case when d.c_wc='1' then 0   when d.c_wc='2' then 1 end as zhyl210403049,\n" +
            "d.c_xyzdm as zhyl210403050,\n" +
            "d.c_ddm as zhyl210403051,\n" +
            "d.c_dmblyb as zhyl210403052,\n" +
            "d.gj_remark as zhyl210403053,\n" +
            "case when d.gjm_type='0'then 1 when d.gjm_type='1'then 2 end  as zhyl210403054,\n" +
            "case when d.gje_type='0'then 1 when d.gje_type='1'then 2 end  as zhyl210403055,\n" +
            "case when d.gjs_type='0'then 1 when d.gjs_type='1'then 2 end  as zhyl210403056,\n" +
            "case when d.gjt_type='0'then 1 when d.gjt_type='1'then 2 end  as zhyl210403057,\n" +
            "\n" +
            "case when d.dj='1' then 1  when d.dj='2' then 0  end as zhyl210404001,\n" +
            "case when d.dj_gbm='1' then 1  when d.dj_gbm='2' then 0  end as zhyl210404002,\n" +
            "case when d.dj_dzzmwcj='1' then 1  when d.dj_dzzmwcj='2' then 0  end as zhyl210404003,\n" +
            "d.dj_zd as zhyl210404004,\n" +
            "d.dj_remark as zhyl210404005\n" +
            "    \n" +
            "    \n" +
            "    \n" +
            " from hospital.hs_patient a \n" +
            "inner join hospital.hs_patientinfo b on a.id=b.patientid \n" +
            "left join hospital.hs_hospital c on a.hospitalid=c.id  \n" +
            "left join hospital.hs_casefour d on b.id=d.id\n" +
            "\n" +
            " where  b.reporttype='首诊报告'  and b.historyflag=0 and   a.id='?' ";



    public final static String jiYinSql ="  select a.id as id ,c.hospitalcode  as center,\n" +
            " a.agreetime as time_enroll, b.intime as visit_time,\n" +
            " case  when b.reporttype='首诊报告' then 'V0'\n" +
            "when b.waketime='30' then 'V1'\n" +
            "when b.waketime='90' then 'V3' when b.waketime='180' then 'V6' when b.waketime='360' then 'V12'\n" +
            "when b.waketime='540' then 'V18' when b.waketime='720' then 'V24' when b.waketime='10000' then 'VN'end as visit_point,\n" +
            "case when d.status='1' then 1 when d.status='2' then 0 end as GENE_ALY,\n" +
            "d.datas\n" +
            " \n" +
            " from hospital.hs_patient a \n" +
            "inner join hospital.hs_patientinfo b on a.id=b.patientid \n" +
            "left join hospital.hs_hospital c on a.hospitalid=c.id  \n" +
            "left join hospital.hs_casefive d on b.id=d.id\n" +
            "\n" +
            " where  b.reporttype='首诊报告'  and b.historyflag=0 and   a.id='?' ";



}
