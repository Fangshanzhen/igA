package com.igA.demo.constant;

import com.alibaba.fastjson.JSONObject;

public class Constant {


    public final static String idSql = "select a.id  from hospital.hs_patient a ";


    public final static String patientSql = " select a.id as id , b.hospitalcode  as center , a.caseno as a,b.hospitalname as b, a.patientname as name, " +
            "case when a.sex=0 then 1 when a.sex=1 then 2 end as sex, a.birthday as birthdate," +
            "a.marriaged as race, a.agreetime as time_enroll ,a.knowtime as time_consent,c.sort as ident_type," +
            "a.cernum as ident_id, a.province,a.city, a.area as district,a.address as street,a.code as zip,a.contact_data " +
            "from hospital.hs_patient a " +
            "left join hospital.hs_hospital b on a.hospitalid=b.id  " +
            "left join hospital.hs_dcit c on cast(a.certype AS INTEGER) =c.id where a.id='?' ";


    public final static String patientInfoSql ="select a.id as id ,c.hospitalcode  as center,  a.agreetime as time_enroll," +
            " a.patientname as name, b.age,a.numberinfo,b.caseno ,case when b.examinetype='17' then 1 " +
            "when b.examinetype='16' then 2 when b.examinetype='131' then 3 end as visit_type, b.caseno as visit_id," +
            "b.intime as visit_time, b.outtime as discharge_time, case  when reporttype='首诊报告' then 'V0'" +
            "when b.waketime='30' then 'V1'" +
            "when b.waketime='90' then 'V3' when b.waketime='180' then 'V6' when b.waketime='360' then 'V12'" +
            "when b.waketime='540' then 'V18' when b.waketime='720' then 'V24' when b.waketime='10000' then 'VN'end as visit_point," +
            "b.id as record_id, case when b.submitflag=0 then 2 when b.submitflag=1 then 1 end as \"lock\"," +
            "b.id as patientid,b.parentid  " +
            "from hospital.hs_patient a " +
            "inner join hospital.hs_patientinfo b on a.id=b.patientid " +
            "left join hospital.hs_hospital c on a.hospitalid=c.id " +
            "where b.historyflag=0  and  a.id='?'"
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


    public final static String baseLineHisSql ="select a.id as id ,c.hospitalcode  as center,  a.agreetime as time_enroll,\n" +
            "b.intime as visit_time, case when b.examinetype='17' then 1 \n" +
            "when b.examinetype='16' then 2 when b.examinetype='131' then 3 end as visit_type, b.caseno as visit_id,\n" +
            " b.outtime as discharge_time, case  when b.reporttype='首诊报告' then 'V0'\n" +
            "when b.waketime='30' then 'V1'\n" +
            "when b.waketime='90' then 'V3' when b.waketime='180' then 'V6' when b.waketime='360' then 'V12'\n" +
            "when b.waketime='540' then 'V18' when b.waketime='720' then 'V24' when b.waketime='10000' then 'VN'end as visit_point,\n" +
            "a.id||'报告' as a, 'ziliaowanchengdu' as b,  a.patientname as name,\n" +
            "case when a.sex=0 then 1 when a.sex=1 then 2 end as sex, b.intime as menzhen_time, b.caseno,\n" +
            "case when d.xn='1'then 1 when d.xn='2'then 0 when d.xn='3'then 9 end as hema,\n" +
            "d.xn_time as hema_time,\n" +
            "case when d.ryxn='1'then 1 when d.ryxn='2'then 0 when d.ryxn='3'then 9 end as machema,\n" +
            "case when d.ryxn_attr='1'then 1 when d.ryxn_attr='2'then 2 when d.ryxn_attr='3'then 9 end as MACHEMA_TYPE,\n" +
            "case when d.dbn='1'then 1 when d.dbn='2'then 0 when d.dbn='3'then 9 end as UPRO,\n" +
            "d.dbn_time as UPRO_TIME,\n" +
            "case when d.dbn_attr='1'then 1 when d.dbn_attr='2'then 2 when d.dbn_attr='3'then 9 end as UPRO_TYPE,\n" +
            "case when d.sgnyc='1'then 1 when d.sgnyc='2'then 0 when d.sgnyc='3'then 9 end as RF,\n" +
            "d.sgnyc_time as RF_TIME,\n" +
            "case when d.sgnyc_attr='1'then 1 when d.sgnyc_attr='2'then 2 when d.sgnyc_attr='3'then 9 end as RF_TYPE,\n" +
            "\n" +
            "case when d.gxy='1'then 1 when d.gxy='2'then 0 when d.gxy='3'then 9 end as HTN,\n" +
            "d.gxy_time as HTN_TIME,\n" +
            "\n" +
            "case when d.pz='1'then 1 when d.pz='2'then 0 when d.pz='3'then 9 end as PURPURA,\n" +
            "d.gmxztxm as PURPURA_OTH,\n" +
            "\n" +
            "case when d.gjtt='1'then 1 when d.gjtt='2'then 0 when d.gjtt='3'then 9 end as JPAIN,\n" +
            "d.gjttxm as JPAIN_OTH,\n" +
            "case when d.fz='1'then 1 when d.fz='2'then 0 when d.fz='3'then 9 end as EDEMA,\n" +
            "case when d.yys='1'then 1 when d.yys='2'then 0 when d.yys='3'then 9 end as MED_HIS,\n" +
            "case when d.shjyy='1'then 1 when d.shjyy='2'then 0 when d.shjyy='3'then 9 end as MED_HTN,\n" +
            "case when d.acei='1'then 1 when d.acei='2'then 0 when d.acei='3'then 9 end as ACEI,\n" +
            "case when d.arb='1'then 1 when d.arb='2'then 0 when d.arb='3'then 9 end as ARB,\n" +
            "case when d.ccb='1'then 1 when d.ccb='2'then 0 when d.ccb='3'then 9 end as CCB,\n" +
            "case when d.bxtzcj='1'then 1 when d.bxtzcj='2'then 0 when d.bxtzcj='3'then 9 end as BBLOCK,\n" +
            "case when d.qtjyyw='1'then 1 when d.qtjyyw='2'then 0 when d.qtjyyw='3'then 9 end as OTH_HTN,\n" +
            "d.qtjyjwmt as OTH_HTN_TEXT,\n" +
            "case when d.bttzcs='1'then 1 when d.bttzcs='2'then 0 when d.bttzcs='3'then 9 end as TONSILECT,\n" +
            "d.bttzcs_time as TONSILECT_TIME,\n" +
            "d.\"explain\" as HISTORY_OTH\n" +
            "\n" +
            "from hospital.hs_patient a \n" +
            "inner join hospital.hs_patientinfo b on a.id=b.patientid \n" +
            "left join hospital.hs_hospital c on a.hospitalid=c.id  \n" +
            "left join hospital.hs_caseone d on b.id=d.id\n" +
            "\n" +
            " where  b.reporttype='首诊报告'  and b.historyflag=0   and   a.id='?' ";



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



    public final static String basicPESql =" select a.id as id ,c.hospitalcode  as center,\n" +
            " a.agreetime as time_enroll, b.intime as visit_time,\n" +
            " case  when b.reporttype='首诊报告' then 'V0'\n" +
            "when b.waketime='30' then 'V1'\n" +
            "when b.waketime='90' then 'V3' when b.waketime='180' then 'V6' when b.waketime='360' then 'V12'\n" +
            "when b.waketime='540' then 'V18' when b.waketime='720' then 'V24' when b.waketime='10000' then 'VN'end as visit_point,\n" +
            "d.check_time as PE_TIME, d.shengao as HEIGHT_CM,d.tizhong as WEIGHT_KG,\n" +
            "d.shousuoya as SBP, d.shuzhangya as DBP, case when d.fuzhong='1' then 1  when d.fuzhong='2' then 0 \n" +
            "  when d.fuzhong='3' then 9 end as  PE_PURPURA, d.fuzhong_qt as PE_PUR_OTH,\n" +
            "  case when d.fuzhongadd='1' then 1  when d.fuzhongadd='2' then 0 \n" +
            "  when d.fuzhongadd='3' then 9 end as PE_EDEMA,\n" +
            "  d.fuzhongadd_qt as PE_EDEMA_OTH,\n" +
            "  d.remark as PE_OTH\n" +
            " \n" +
            " \n" +
            " from hospital.hs_patient a \n" +
            "inner join hospital.hs_patientinfo b on a.id=b.patientid \n" +
            "left join hospital.hs_hospital c on a.hospitalid=c.id  \n" +
            "left join hospital.hs_casetwo d on b.id=d.id\n" +
            "\n" +
            " where  b.reporttype='首诊报告'  and b.historyflag=0 and   a.id='?' ";

    public final static String basicLabSql ="select a.id as id ,c.hospitalcode  as center,\n" +
            " a.agreetime as time_enroll, b.intime as visit_time,\n" +
            " case  when b.reporttype='首诊报告' then 'V0'\n" +
            "when b.waketime='30' then 'V1'\n" +
            "when b.waketime='90' then 'V3' when b.waketime='180' then 'V6' when b.waketime='360' then 'V12'\n" +
            "when b.waketime='540' then 'V18' when b.waketime='720' then 'V24' when b.waketime='10000' then 'VN'end as visit_point,\n" +
            "d.ncg_check_time as UALY_TIME,\n" +
            "case when d.ncg_pro='1' then 0 when d.ncg_pro='2' then 0.5  when d.ncg_pro='3' then 1 when d.ncg_pro='4' then 2 when d.ncg_pro='5' then 3 \n" +
            "when d.ncg_pro='6' then 4 end as UPRO_VAL,\n" +
            "case when d.ncg_bld='1' then 0 when d.ncg_bld='2' then 1 end as  BLD_CODE,\n" +
            "d.ncg_count as URBC_VAL,\n" +
            "d.ncg_hpf as URBC_M_VAL,\n" +
            "d.shjc_time as BCHEM_TIME,\n" +
            "d.shjc_alt as ALT_VAL,\n" +
            "d.shjc_ast as AST_VAL,\n" +
            "d.shjc_ua as UA_VAL,\n" +
            "d.shjc_ca as CA_VAL,\n" +
            "d.shjc_p as P_VAL,\n" +
            "d.shjc_k as K_VAL,\n" +
            "d.shjc_co2 as CO2_VAL,\n" +
            "d.shjc_tg as TG_VAL,\n" +
            "d.shjc_data,\n" +
            "d.ccr_data,\n" +
            "d.grsc_time as INFECT_TIME,\n" +
            "case when d.grsc_hbsag='1' then 0 when d.grsc_hbsag='2' then 1 end as HBS_AG_CODE,\n" +
            "case when d.grsc_anti_hbs='1' then 0 when d.grsc_anti_hbs='2' then 1 end as ANTI_HBS_CODE,\n" +
            "case when d.grsc_hbeag='1' then 0 when d.grsc_hbeag='2' then 1 end as HBE_AG_CODE,\n" +
            "case when d.grsc_anti_hbe='1' then 0 when d.grsc_anti_hbe='2' then 1 end as ANTI_HBE_CODE,\n" +
            "case when d.grsc_anti_hbc='1' then 0 when d.grsc_anti_hbc='2' then 1 end as ANTI_HBC_CODE,\n" +
            "case when d.grsc_anti_hcv='1' then 0 when d.grsc_anti_hcv='2' then 1 end as ANTI_HCV_CODE,\n" +
            "case when d.grsc_anti_hiv='1' then 0 when d.grsc_anti_hiv='2' then 1 end as ANTI_HIV_CODE,\n" +
            "case when d.grsc_tp_ab='1' then 0 when d.grsc_tp_ab='2' then 1 end as TP_AB_CODE,\n" +
            "d.myqdb_time as IMMUN_TIME,\n" +
            "d.myqdb_igg as IGG_VAL,\n" +
            "d.myqdb_iga as IGA_VAL,\n" +
            "d.myqdb_igm as IGM_VAL,\n" +
            "d.myqdb_c3 as C3_VAL,\n" +
            "d.myqdb_c4 as C4_VAL,\n" +
            "d.gmjc_time as ALLERG_TIME,\n" +
            "d.gmjc_ige as IGE_VAL,\n" +
            "d.gmjc_ecp as ECP_VAL,\n" +
            "d.zxktjc_time as AUTOAB_TIME,\n" +
            "case when d.zxktjc_ana='1' then 0 when d.zxktjc_ana='2' then 1 end as ANA_CODE,\n" +
            "case when d.zxktjc_dsdna='1' then 0 when d.zxktjc_dsdna='2' then 1 end as DSDNA_CODE,\n" +
            "case when d.zxktjc_anca='1' then 0 when d.zxktjc_anca='2' then 1 end as ANCA_CODE,\n" +
            "\n" +
            " case when d.zxktjc_ancaxm like '%1%' then  1 else 0 end as AANCA_CODE,\n" +
            " case when d.zxktjc_ancaxm like '%2%' then  1 else 0 end as CANCA_CODE,\n" +
            " case when d.zxktjc_ancaxm like '%3%' then  1 else 0 end as PANCA_CODE,\n" +
            " case when d.zxktjc_ancaxm like '%4%' then  1 else 0 end as PR3_CODE,\n" +
            " case when d.zxktjc_ancaxm like '%5%' then  1 else 0 end as MPO_CODE,\n" +
            " \n" +
            "d.ngjc_data,\n" +
            "d.ngdl_data,\n" +
            "d.ipth_time as IPTH_TIME,\n" +
            "d.ipth_val as IPTH_VAL,\n" +
            "d.iga_tjh as GD_IGA1,\n" +
            "d.ndbdl_data,\n" +
            "d.ndbjg_data,\n" +
            "d.szzq_data,\n" +
            "d.xcg_data\n" +
            "\n" +
            " \n" +
            "  from hospital.hs_patient a \n" +
            "inner join hospital.hs_patientinfo b on a.id=b.patientid \n" +
            "left join hospital.hs_hospital c on a.hospitalid=c.id  \n" +
            "left join hospital.hs_casethree d on b.id=d.id\n" +
            "\n" +
            " where  b.reporttype='首诊报告'  and b.historyflag=0 and   a.id='?' ";








}
