package com.igA.demo.constant;

//早发蛋白尿

public class PediatricKidneyDatabaseConstant2 {

    //id
    public final static String KidneyIdSql2 = "select a.id  from dbo.DM a  where a.Del_Mark=0 and a.DataTypeId='#' ";
    //一般资料
    public final static String yibanziliao2 = "select a.id , i.a as zhyl11000001, SUBNAM as zhyl1100001,SUBJMRID as zhyl1100002,case when DMSUBRE='门诊' then '1' when DMSUBRE='住院' then '2' end as zhyl1100003,\n" +
            "case when SEX='男' then '1' when SEX='女' then '2' end as zhyl1100004, d.b1 as zhyl1100005,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BRIHDTC) as bigint )*1000 AS zhyl1100006, AGEY as zhyl1100023,\n" +
            "c.a  as zhyl1110023, SUBADRRE as zhyl1110024,SUBZIPCD as zhyl1100026,SUBHTEL as zhyl1110028,SUBMTEL as zhyl1110029,\n" +
            "SUBMTEL2 as zhyl1110030, case when a.PACOMAT ='否' then 0  when a.PACOMAT ='是' then 1 end as  zhyl1200031, null as zhyl1200030,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', DMVISDTC) as bigint )*1000 AS  zhyl1100031,\n" +
            "REGHOID as zhyl1100032,INVNAM as zhyl1100033,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', DMDTC) as bigint )*1000 AS  zhyl1100034,\n" +
            "a.VERNAM as zhyl1100007,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', a.VERDTC) as bigint )*1000 AS  zhyl1100008,\n" +
            "case when a.DMSIIC ='否' then 0  when a.DMSIIC ='是' then 1 end as zhyl1100009,\n" +
            "case when e.MHOCCUR='是' then '1' when e.MHOCCUR='否' then '0' when e.MHOCCUR='不详' then '2' end as zhyl1100035,\n" +
            "case when e1.MHOCCUR='是' then '1' when e1.MHOCCUR='否' then '0' when e1.MHOCCUR='不详' then '2' end as zhyl1100036,\n" +
            "case when e2.MHOCCUR='是' then '1' when e2.MHOCCUR='否' then '0' when e2.MHOCCUR='不详' then '2' end as zhyl11000371,\n" +
            "f.SBP as zhyl11000372,\n" +
            "f.DBP as zhyl11000373, \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', f.VSDTC) as bigint )*1000 as zhyl11000374,\n" +
            "case when e3.MHOCCUR='是' then '1' when e3.MHOCCUR='否' then '0' when e3.MHOCCUR='不详' then '2' end as zhyl1100042,\n" +
            "case when e4.MHOCCUR='是' then '1' when e4.MHOCCUR='否' then '0' when e4.MHOCCUR='不详' then '2' end as zhyl1100043,\n" +
            "case when e5.MHOCCUR='是' then '1' when e5.MHOCCUR='否' then '0' when e5.MHOCCUR='不详' then '2' end as zhyl1100044,\n" +
            "case when e6.MHOCCUR='是' then '1' when e6.MHOCCUR='否' then '0' when e6.MHOCCUR='不详' then '2' end as zhyl1100045,\n" +
            "case when e7.MHOCCUR='是' then '1' when e7.MHOCCUR='否' then '0' when e7.MHOCCUR='不详' then '2' end as zhyl1100046,\n" +
            "coalesce(e.MHOTHER,e1.MHOTHER,e2.MHOTHER,e3.MHOTHER,e4.MHOTHER,e5.MHOTHER,e6.MHOTHER) as zhyl1100047\n" +
            "from dbo.DM a \n" +
            "left join dbo.DICTABLEINFO b on a.SUBPROVINCE=b.[Path] \n" +
            "left join dbo.area c on b.CODE=c.c  \n" +
            "left join dbo.nation d on a.ETHNIC=d.a1\n" +
            "left join (select * from dbo.mh   where [SOURCE] ='筛查表'  and MHTERM='肾脏病病史3个月' ) e on  a.ID =e.DMID \n" +
            "left join (select * from dbo.mh  where [SOURCE] ='筛查表'  and MHTERM='蛋白尿') e1 on  a.ID =e1.DMID \n" +
            "left join (select * from dbo.mh   where [SOURCE] ='筛查表'  and MHTERM='高血压' ) e2 on  a.ID =e2.DMID \n" +
            "left join (select * from dbo.vs   where [SOURCE] ='筛查表' and DELMARK=0)f  on  a.ID =f.DMID \n" +
            "left join (select * from dbo.mh   where [SOURCE] ='筛查表'  and MHTERM='少尿/无尿' ) e3 on  a.ID =e3.DMID \n" +
            "left join (select * from dbo.mh   where [SOURCE] ='筛查表'  and MHTERM='多尿' ) e4 on  a.ID =e4.DMID \n" +
            "left join (select * from dbo.mh   where [SOURCE] ='筛查表'  and MHTERM='夜尿增多' ) e5 on  a.ID =e5.DMID \n" +
            "left join (select * from dbo.mh   where [SOURCE] ='筛查表'  and MHTERM='血肌酐增高' ) e6 on  a.ID =e6.DMID \n" +
            "left join (select * from dbo.mh   where [SOURCE] ='筛查表'  and MHTERM='生长发育落后' ) e7 on  a.ID =e7.DMID \n" +
            "left join hospital i on a.SITEID=i.h\n" +
            "where a.id='?'";

    //现病史
    public final static String xianbingshi2 = "select * from dbo.mh2 where id='?'";
    //个人史
    public final static String gerenshi2 = "select * from dbo.gerenshi  where id='?'";
    //  家族史
    public final static String jiazhushi2 = "select * from dbo.fh1 where    DMID='?' ";
////体格检查
//    public final static String tigejiancha2 ="select a.ID,\n" +
//        "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.VSDTC) as bigint )*1000 AS  zhyl5000001,\n" +
//        "b.HEIGHT as zhyl5000007,WEIGHT as zhyl5000020,b.BSA as zhyl5000021,\n" +
//        "b.SBP as zhyl5100022,b.DBP as zhyl5100023,case when  b.[SOURCE]='随访'  then '1' else '0' end as zhyl5000024,\n" +
//        "SOURCE\n" +
//        "from dbo.DM a left join dbo.VS b on a.ID =b.DMID \n" +
//        "\n" +
//        "where b.DELMARK=0 and  b.DMID='?'  ";

    //体格检查
    public final static String tigejiancha2 = "select * from dbo.tige  where dmid='?'";
    //尿常规
    public final static String niaochanggui2 = "SELECT \n" +
            "case when  b.[SOURCE]='随访'  then '1' else '0' end as zhyl600000101,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.URDTC ) as bigint )*1000 AS zhyl600000102,\n" +
            "b.URHOSP as zhyl600000103,\n" +
            "case when b.UPL='-' then '0' when b.UPL='+-' then '1' when b.UPL='+' then '2' when b.UPL='++' then '3'  \n" +
            "when b.UPL='+++' then '4'when b.UPL='++++' then '5' end as zhyl600000104,\n" +
            "b.UPN as zhyl6000001051,\n" +
            "b.UPNREF as zhyl6000001052,\n" +
            "case when b.RBCAN='是' then '1' when b.RBCAN='否' then '0'  end as zhyl6000001061,\n" +
            "b.RBC as zhyl6000001062,\n" +
            "b.RBC1 as zhyl6000001063,\n" +
            "b.RBCDSC as zhyl6000001064,\n" +
            "case when b.RBCFORM='肾小球性' then '0' when b.RBCFORM='非肾小球性' then '1'  end as zhyl6000001071,\n" +
            "b.RBCFOSC as zhyl6000001072,\n" +
            "b.SG as zhyl6000001081,\n" +
            "b.SGREF as zhyl6000001082,\n" +
            "b.GLU as zhyl6000001091,\n" +
            "b.GLUREF as zhyl6000001092,\n" +
            "b.RBC as zhyl6000001101,\n" +
            "b.RBC1 as zhyl6000001102,\n" +
            "b.RBCREF as zhyl6000001103,\n" +
            "b.RBCDSC as zhyl6000001104,\n" +
            "b.PH as zhyl6000001111,\n" +
            "b.PHREF as zhyl6000001112,\n" +
            "b.UPH as zhyl6000001121,\n" +
            "b.UPHREF as zhyl6000001122,\n" +
            "b.WBC AS zhyl6000001131,\n" +
            "b.WBC1 as zhyl6000001132,\n" +
            "b.WBCREF as zhyl6000001133,\n" +
            "b.WBCDSC as zhyl6000001134\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_UR b on a.ID =b.DMID \n" +
            "where  b.DELMARK=0   and  b.DMID='?'  ";

    //24小时尿钙体重比
    public final static String niaogaitizhong2 = " select \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl600003001,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', UCDTC  ) as bigint )*1000 as  zhyl600003002,\n" +
            "UCHOSP as zhyl600003003,\n" +
            "UCDL as zhyl6000030041,\n" +
            "UCDLREF as zhyl6000030042,\n" +
            "UCWEIGHT as zhyl600003005,\n" +
            "UCUWP as zhyl6000030061,\n" +
            "UCUWPREF as zhyl6000030062\n" +
            "\n" +
            "from dbo.E_UC where DELMARK=0 and  DMID='?'";

    //尿蛋白肌酐比
    public final static String niaodanbaijigan2 = " SELECT \n" +
            "case when  b.[SOURCE]='随访'  then '1' else '0' end as zhyl600000201,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.PCDTC ) as bigint )*1000 AS zhyl600000202,\n" +
            "b.PCHOSP as zhyl600000203,\n" +
            "b.PCNDB as zhyl600000204,\n" +
            "b.PCNJG as zhyl600000205,\n" +
            "b.PCORRES as zhyl6000002061,\n" +
            "b.PCSREF as zhyl6000002062\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_PC b on a.ID =b.DMID \n" +
            "where  b.DELMARK=0  and b.PCTYPE='尿蛋白/肌酐比'  and   b.DMID='?' ";

    //24h尿蛋白定量
    public final static String niaodanbaidingliang24h2 = " SELECT \n" +
            "case when  b.[SOURCE]='随访'  then '1' else '0' end as zhyl600000301,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.UPDTC ) as bigint )*1000 AS zhyl600000302,\n" +
            "b.UPHOSP as zhyl600000303,\n" +
            "b.UPDL as zhyl6000003041,\n" +
            "UPDLREF as zhyl6000003042,\n" +
            "UPWEIGHT as zhyl600000305,\n" +
            "\n" +
            "b.UPUWP as zhyl6000003061,\n" +
            "b.UPUWPREF as zhyl6000003062\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_UP b on a.ID =b.DMID  \n" +
            "where  b.DELMARK=0  and  b.DMID='?' ";

    //24h肌酐清除率
    public final static String jiganqingchu24h2 = "select *,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', HEDTC ) as bigint )*1000 AS zhyl600000402,\n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl600000401\n" +
            "from dbo.ehe1 \n" +
            "where   HETYPE='24小时肌酐清除率'   and  DELMARK=0 and  DMID='?'  ";
    //肾早损
    public final static String shenzaosuai2 = " select  \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl600000501,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', IRDTC ) as bigint )*1000 AS zhyl600000502,\n" +
            "IRHOSP as zhyl600000503,\n" +
            "MALB as zhyl6000005041,\n" +
            "MALBREF as zhyl6000005042,\n" +
            "case when MALBORRE='正常' then '1'   when MALBORRE='异常' then '0'   when MALBORRE='不详' then '2'  end  as  zhyl6000005043,\n" +
            "TRU as zhyl6000005051,\n" +
            "TRUREF as zhyl6000005052,\n" +
            "case when TRUORRE='正常' then '1'   when TRUORRE='异常' then '0'   when TRUORRE='不详' then '2'  end  as zhyl6000005053,\n" +
            "NAG as zhyl6000005061,\n" +
            "NAGREF as zhyl6000005062,\n" +
            "case when NAGORRE='正常' then '1'   when NAGORRE='异常' then '0'   when NAGORRE='不详' then '2'  end  as zhyl6000005063,\n" +
            "A1MG as zhyl6000005071,\n" +
            "A1MGREF as zhyl6000005072,\n" +
            "case when A1MGORRE='正常' then '1'   when A1MGORRE='异常' then '0'   when A1MGORRE='不详' then '2'  end  as zhyl6000005073,\n" +
            "MALBJG as zhyl6000005081,\n" +
            "MALBJGREF as zhyl6000005082,\n" +
            "case when MALBJGORRE='正常' then '1'   when MALBJGORRE='异常' then '0'   when MALBJGORRE='不详' then '2'  end  as zhyl6000005083,\n" +
            "IROTHER as zhyl600000509\n" +
            "\n" +
            "\n" +
            "from dbo.E_IR  where  DELMARK=0  and DMID='?' ";

    //尿蛋白电泳
    public final static String niaodanbaidianyong2 = " \n" +
            "select   \n" +
            "case when   [SOURCE]='随访'  then '1' else '0' end as  zhyl600000601,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', UEDTC ) as bigint )*1000 AS zhyl600000602,\n" +
            "UEHOSP as zhyl600000603,\n" +
            "SPPORG  as zhyl6000006041,\n" +
            "SPPREF as zhyl6000006042,\n" +
            "ALBPORG as zhyl6000006051,\n" +
            "ALBPREF as zhyl6000006052,\n" +
            "BPPORG as zhyl6000006061,\n" +
            "BPPREF as zhyl6000006062\n" +
            "\n" +
            "from dbo.E_UE  where  DELMARK=0  and DMID='?'  "; //and [SOURCE] is null

    //血生化
    public final static String xueshenghua2 = " select   \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl600000701,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BBDTC ) as bigint )*1000 AS zhyl600000702,\n" +
            "BBHOSP as  zhyl600000703,\n" +
            "ALB as zhyl600000704,\n" +
            "SCR as zhyl6000007051,\n" +
            "SCRREF as zhyl6000007052,\n" +
            "BUN as zhyl6000007061,\n" +
            "BUNREF as zhyl6000007062,\n" +
            "TCHO as zhyl6000007071,\n" +
            "TCHOREF as zhyl6000007072,\n" +
            "ALT as zhyl6000007081,\n" +
            "ALTREF as zhyl6000007082,\n" +
            "AST as zhyl6000007091,\n" +
            "ASTREF as zhyl6000007092,\n" +
            "K as zhyl6000007101,\n" +
            "KREF as zhyl6000007102,\n" +
            "P as zhyl6000007111,\n" +
            "PREF as zhyl6000007112,\n" +
            "CA as zhyl6000007121,\n" +
            "CAREF as zhyl6000007122,\n" +
            "CL as zhyl6000007131,\n" +
            "CLREF as zhyl6000007132,\n" +
            "MG as zhyl6000007141,\n" +
            "MGREF as zhyl6000007142,\n" +
            "NA as zhyl6000007151,\n" +
            "NAREF as zhyl6000007152,\n" +
            "AG as zhyl6000007161,\n" +
            "AGREF as zhyl6000007162,\n" +
            "ALP as zhyl6000007171,\n" +
            "ALPREF as zhyl6000007172,\n" +
            "LD as zhyl6000007181,\n" +
            "LDREF as zhyl6000007182,\n" +
            "TBA as zhyl6000007191,\n" +
            "TBAREF as zhyl6000007192,\n" +
            "GGT as zhyl6000007201,\n" +
            "GGTREF as zhyl6000007202,\n" +
            "HCO3 as zhyl6000007211,\n" +
            "HCO3REF as zhyl6000007212,\n" +
            "LAT as zhyl6000007221,\n" +
            "LATREF as zhyl6000007222,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', VERDTC ) as bigint )*1000 AS  zhyl600000723\n" +
            "\n" +
            "\n" +
            "from dbo.E_BB  where  DELMARK=0    and DMID='?' ";//and [SOURCE] is null

    //免疫球蛋白
    public final static String mianyiqiudanbai2 = " select   \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl600000801,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', IGDTC ) as bigint )*1000 AS zhyl600000802,\n" +
            "IGHOSP as zhyl600000803,\n" +
            "IGG as zhyl6000008041,\n" +
            "IGGREF as zhyl6000008042,\n" +
            "IGA as zhyl6000008051,\n" +
            "IGAREF as zhyl6000008052,\n" +
            "IGM as zhyl6000008061,\n" +
            "IGMREF as zhyl6000008062\n" +
            "from dbo.E_IG  where  DELMARK=0   and DMID='?' ";  // and [SOURCE] is null

    //血补体
    public final static String xuebuti2 = "  select   \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl600000901,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BCDTC ) as bigint )*1000 AS zhyl600000902,\n" +
            "BCHOSP as zhyl600000903,\n" +
            "C3 as zhyl6000009041,\n" +
            "C3REF as zhyl6000009042,\n" +
            "C4 as zhyl6000009051,\n" +
            "C4REF as zhyl6000009052\n" +
            "\n" +
            "from dbo.E_BC  where  DELMARK=0  and DMID='?'  ";  // and [SOURCE] is null
    //感染筛查
    public final static String ganranshuaicha2 = "  select   \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as  zhyl600001001,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', ISDTC ) as bigint )*1000 AS zhyl600001002,\n" +
            "ISHOSP as zhyl600001003,\n" +
            "case when ISORRE='阴性' then '0'  when ISORRE='阳性' then '1' end   as zhyl600001004,\n" +
            "ISDESC as zhyl600001005\n" +
            "from dbo.E_IS  where  DELMARK=0   and DMID='?'  ";// and [SOURCE] is null

    //TORCH
    public final static String TORCH2 = "  select   \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl600001101,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', TODTC ) as bigint )*1000 AS zhyl600001102,\n" +
            "TOHOSP as zhyl600001103,\n" +
            "case when TOORRE='阴性' then '0'  when TOORRE='阳性' then '1' end   as zhyl600001104,\n" +
            "TODESC as zhyl600001105\n" +
            "\n" +
            "from dbo.E_TO  where  DELMARK=0 and DMID='?'   ";
    //超声心动
    public final static String chaoshenxindong2 = " select   \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl600001301,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', EGDTC ) as bigint )*1000 AS zhyl600001302,\n" +
            "EGHOSP as zhyl600001303,\n" +
            "case when EGORRE='异常' then '0'  when EGORRE='正常' then '1' end   as zhyl600001304,\n" +
            "case when CAST(EGDESC AS varchar(max))='室缺' then '0' when CAST(EGDESC AS varchar(max))='房缺' then '1' when CAST(EGDESC AS varchar(max))='左心室肥大' then '2' when CAST(EGDESC AS varchar(max))='肺动脉瓣狭窄' then '3' when CAST(EGDESC AS varchar(max))='分散性主动脉瓣下狭窄' then '4' \n" +
            "when CAST(EGDESC AS varchar(max))='三尖瓣下移' then '5' when CAST(EGDESC AS varchar(max))='其他' then '6'   end  as zhyl600001305,\n" +
            "null as zhyl600001306\n" +
            "from dbo.E_EG  where  DELMARK=0   and DMID='?' ";
    //腹部B超
    public final static String fububichao2 = " select * from (\n" +
            "select a.DMID,\n" +
            "'0' as zhyl600001401,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', a.BUDTC ) as bigint )*1000 AS zhyl600001402,\n" +
            "a.BUHOSP as zhyl600001403,\n" +
            "case when a.BUORRE='正常' then '1'  when a.BUORRE='异常' then '0'  end as zhyl6000014041,\n" +
            "case when a.BUSIZE='正常' then '1' when a.BUSIZE='增大' then '0' when a.BUSIZE='缩小' then '2' end as zhyl6000014042,\n" +
            "a.BUDESC as zhyl6000014043,\n" +
            "case when b.BUORRE='正常' then '1'  when b.BUORRE='异常' then '0'  end as zhyl6000014051,\n" +
            "b.LSL as zhyl6000014052,\n" +
            "b.LSW as zhyl6000014053,\n" +
            "b.LSH as zhyl6000014054,\n" +
            "b.RSL as zhyl6000014055,\n" +
            "b.RSW as zhyl6000014056,\n" +
            "b.RSH as zhyl6000014057,\n" +
            "case when b.BUECHO='正常' then 1 when b.BUECHO='增强' then 0  end  as zhyl6000014058,\n" +
            "b.BUDESC as zhyl6000014059,\n" +
            "null as zhyl600001406\n" +
            "\n" +
            "\n" +
            "\n" +
            "from  (select * from  (\n" +
            "select  * ,ROW_NUMBER() OVER (PARTITION BY DMID,BUDTC,BUCAT ORDER BY BUDTC desc) AS num\n" +
            "from  dbo.E_BU where BUCAT='肝脏'  and DELMARK=0 and [SOURCE] is null \n" +
            ") s where num=1) a \n" +
            "left join \n" +
            "(select * from  (\n" +
            "select  * ,ROW_NUMBER() OVER (PARTITION BY DMID,BUDTC,BUCAT ORDER BY BUDTC desc) AS num\n" +
            "from  dbo.E_BU where BUCAT='肾脏'  and DELMARK=0 and [SOURCE] is null \n" +
            ") s where num=1) b on a.DMID=b.DMID and a.BUDTC=b.BUDTC\n" +
            "\n" +
            "\n" +
            "UNION \n" +
            "\n" +
            "select a.DMID,\n" +
            "'1' as zhyl600001401,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', a.BUDTC ) as bigint )*1000 AS zhyl600001402,\n" +
            "a.BUHOSP as zhyl600001403,\n" +
            "case when a.BUORRE='正常' then '1'  when a.BUORRE='异常' then '0'  end as zhyl6000014041,\n" +
            "case when a.BUSIZE='正常' then '1' when a.BUSIZE='增大' then '0' when a.BUSIZE='缩小' then '2' end as zhyl6000014042,\n" +
            "a.BUDESC as zhyl6000014043,\n" +
            "case when b.BUORRE='正常' then '1'  when b.BUORRE='异常' then '0'  end as zhyl6000014051,\n" +
            "b.LSL as zhyl6000014052,\n" +
            "b.LSW as zhyl6000014053,\n" +
            "b.LSH as zhyl6000014054,\n" +
            "b.RSL as zhyl6000014055,\n" +
            "b.RSW as zhyl6000014056,\n" +
            "b.RSH as zhyl6000014057,\n" +
            "case when b.BUECHO='正常' then 1 when b.BUECHO='增强' then 0  end  as zhyl6000014058,\n" +
            "b.BUDESC as zhyl6000014059,\n" +
            "null as zhyl600001406\n" +
            "\n" +
            "\n" +
            "\n" +
            "from  (select * from  (\n" +
            "select  * ,ROW_NUMBER() OVER (PARTITION BY DMID,BUDTC,BUCAT ORDER BY BUDTC desc) AS num\n" +
            "from  dbo.E_BU where BUCAT='肝脏'  and DELMARK=0 and [SOURCE] ='随访'\n" +
            ") s where num=1) a \n" +
            "left join \n" +
            "(select * from  (\n" +
            "select  * ,ROW_NUMBER() OVER (PARTITION BY DMID,BUDTC,BUCAT ORDER BY BUDTC desc) AS num\n" +
            "from  dbo.E_BU where BUCAT='肾脏'  and DELMARK=0 and [SOURCE] ='随访' \n" +
            ") s where num=1) b on a.DMID=b.DMID and a.BUDTC=b.BUDTC\n" +
            "\n" +
            ")v \n  where DMID='?' ";
    //免疫荧光-光镜
    public final static String guangjing2 = "  select \n" +
            "\n" +
            "case when JianChaDuiXiang='患儿' then  '1'  when JianChaDuiXiang='患儿亲属' then  '2' end as zhyl6100056,\n" +
            "YuHuanErGuanXi as zhyl6100057,\n" +
            "case when ShenHuoJian='有' then '1' when ShenHuoJian='无' then '0' end as zhyl6100058,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', JianChaRiQi ) as bigint )*1000 AS zhyl6100059,\n" +
            "JianCeYiYuan as zhyl6100060,\n" +
            "MianYiYingGuangXiaoQiuShu as zhyl6200000,\n" +
            "IgaRanSeQiangDu as zhyl6200001,\n" +
            "replace(replace(IgaChenJiBuWei,'GBM',1),'系膜区',2) as zhyl6200002,\n" +
            "replace(replace(replace(IgaChenJiXingShi,'颗粒',1),'线性',2),'块状',3) as zhyl6200003,\n" +
            "IggRanSeQiangDu as zhyl6200004,\n" +
            "replace(replace(IggChenJiBuWei,'GBM',1),'系膜区',2) as zhyl6200005,\n" +
            "replace(replace(replace(IggChenJiXingShi,'颗粒',1),'线性',2),'块状',3) as zhyl6200006,\n" +
            "IgmRanSeQiangDu as zhyl6200007,\n" +
            "replace(replace(IgmChenJiBuWei,'GBM',1),'系膜区',2) as zhyl6200008,\n" +
            "replace(replace(replace(IgmChenJiXingShi,'颗粒',1),'线性',2),'块状',3) as zhyl6200009,\n" +
            "C3RanSeQiangDu as zhyl6200010,\n" +
            "replace(replace(C3ChenJiBuWei,'GBM',1),'系膜区',2) as zhyl6200011,\n" +
            "replace(replace(replace(C3ChenJiXingShi,'颗粒',1),'线性',2),'块状',3) as zhyl6200012,\n" +
            "C1qRanSeQiangDu as zhyl6200013,\n" +
            "replace(replace(C1qChenJiBuWei,'GBM',1),'系膜区',2) as zhyl6200014,\n" +
            "replace(replace(replace(C1qChenJiXingShi,'颗粒',1),'线性',2),'块状',3) as zhyl6200015,\n" +
            "FraRanSeQiangDu as zhyl6200016,\n" +
            "replace(replace(FraChenJiBuWei,'GBM',1),'系膜区',2) as zhyl6200017,\n" +
            "replace(replace(replace(FraChenJiXingShi,'颗粒',1),'线性',2),'块状',3) as zhyl6200018,\n" +
            "MianYiYingGuangXiaoQiuMiaoShu as zhyl61000611,\n" +
            "GuangJingXiaoQiuShu as zhyl6200019,\n" +
            "case when GuangJingZhenDuan='轻微病变' then '0'  when GuangJingZhenDuan='系膜增生' then '1'  when GuangJingZhenDuan='膜性肾病' then '2' \n" +
            "when GuangJingZhenDuan='局灶节段肾小球硬化' then '3' \n" +
            "when GuangJingZhenDuan='膜增生性肾小球肾炎' then '4' \n" +
            "when GuangJingZhenDuan='其他' then '5' end as zhyl6200020,\n" +
            "GuangJingZhenDuanQiTa as zhyl6200021,\n" +
            "GuangJingZhenDuanJuTiMiaoShu as zhyl6200022\n" +
            "\n" +
            "from dbo.GuangJingTemp  where DMID='?' ";

    //电镜
    public final static String dianjing2 = " select \n" +
            "case when RBOBJ='患儿' then  '1'  when RBOBJ='患儿亲属' then  '2' end as zhyl6100064,\n" +
            "RBOBJDSC as zhyl6100065,\n" +
            "case when RBOCCUR='有' then '1' when RBOCCUR='无' then '0' end as zhyl6100066,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', RBDTC ) as bigint )*1000 AS zhyl6100067,\n" +
            "SITENAM as zhyl6100068,\n" +
            "case when EMOCCUR='有' then '1' when EMOCCUR='无' then '0' end as zhyl6200023,\n" +
            "case when GBMABNOR='有' then '1' when GBMABNOR='无' then '0' end as zhyl6200024,\n" +
            "case when EDDOCCUR='有' then '1' when EDDOCCUR='无' then '0' end as zhyl6200025,\n" +
            "RBDESC as zhyl6200026,\n" +
            "DIAGNOS as zhyl6200027,\n" +
            "null as zhyl6200028\n" +
            "\n" +
            "from dbo.RB where  DELMARK=0    and RBCAT='电镜'  and DMID='?'  ";  //and [SOURCE] is null

    //纯音测听
    public final static String chunyinceting2 = "  select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', PTDTC ) as bigint )*1000 AS zhyl6100070,\n" +
            "PTHOSP as zhyl6100071,\n" +
            "case when PTOCCUR='有' then '1' when PTOCCUR='无' then '0' end as zhyl6100072,\n" +
            "case when PTORRE='正常' then '1'  when PTORRE='异常' then '0'  end  as zhyl6100073,\n" +
            "PTDESC as zhyl6100074,\n" +
            "null as zhyl6100075\n" +
            "\n" +
            "\n" +
            "from  dbo.E_PT where  DELMARK=0  and DMID='?'   "; //and [SOURCE] is null
    //眼裂隙灯检查
    public final static String yanliedeng2 = " select dmid,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', ESDTC ) as bigint )*1000 AS zhyl6100076,\n" +
            "ESHOSP as zhyl6100077,\n" +
            "case when ESOCCUR='有' then '1' when ESOCCUR='无' then '0' end as zhyl6100078,\n" +
            "case when ESORRE='正常' then '1'  when ESORRE='异常' then '0'  end  as zhyl6100079,\n" +
            "ESDESC as zhyl6100080,\n" +
            "null as zhyl6100081\n" +
            "\n" +
            "from  dbo.E_ES where  DELMARK=0  and DMID='?'  "; //and [SOURCE] is null

    //其他检查
    public final static String qitajiancha2 = " select dmid,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', OTDTC ) as bigint )*1000 AS zhyl6100082,\n" +
            "OTHOSP as zhyl6100083,\n" +
            "OTDESC as zhyl6100084,\n" +
            "null as zhyl6100085\n" +
            "\n" +
            "from  dbo.E_OT where  DELMARK=0   and DMID='?'   ";

    //    基因检测
    public final static String jiyinjiance2 = " select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', GTDTC ) as bigint )*1000 AS zhyl6100086,\n" +
            "SITENAM as zhyl6100087,\n" +
            "case when SPTYPE='DNA'then '1' when SPTYPE='mRNA'then '2' end as zhyl6100088,\n" +
            "SPID as zhyl6100089,\n" +
            "replace(replace(replace(replace(replace(replace(replace(replace(replace(SPSOURCE,'_',','),'尿液','3'),'皮肤','8'),'转染的外周血淋巴细胞','2'),'外周血','1'),'唾液','4'),\n" +
            "'头发','5'),'羊水','6'),'绒毛膜细胞','7') as zhyl6100090,\n" +
            "REMAIN as zhyl6100091,\n" +
            "LOCATION as zhyl6100092,\n" +
            "case when GENE is not null then (\n" +
            "case when GENE='NPHS1' then '1'  when GENE='NPHS2' then '2'  when GENE='WT1' then '3'  when GENE='PLCE1' then '4'  when GENE='CD2AP' then '5'  when GENE='ACTN4' then '6'\n" +
            " when GENE='TRPC6' then '7'  when GENE='INF2' then '8'  when GENE='LMX1B' then '9'  when GENE='LAMB2' then '10'  when GENE='GLA' then '11'\n" +
            "   when GENE='ITGB4' then '12'  when GENE='SCARB2' then '13'  when GENE='COQ2' then '14'  when GENE='PDSS2' then '15'  when GENE='MTTL1' then '16'  when GENE='SMARCAL1' then '17'\n" +
            "    when GENE='SRY' then '18'  when GENE='COL4A3' then '19'  when GENE='COL4A4' then '20'  when GENE='COL4A5' then '21' else '22' end \n" +
            "    \n" +
            "    ) end as zhyl6100093,\n" +
            "    case when GTORRE='未检测到变异'  then '1' when GTORRE='异常'  then '2' when GTORRE='单核苷酸多态性'  then '3' when GTORRE='无法判断'  then '4'  end as zhyl6100094,\n" +
            "replace((replace(replace(replace(replace(replace(replace(replace(TYPEMU,'无义突变','1'),'错义突变','2'),'剪接突变','3'),'大缺失（缺失＞20个碱基）','4'),'小缺失（缺失≤20个碱基）','5'),'缺失插入','7'),\n" +
            "'缺失','6')),'插  入','5')  as zhyl6100095  ,\n" +
            "GTDESC as zhyl6100096,\n" +
            "\n" +
            "case when FATHER='是' then '1' when FATHER='否' then '0' when FATHER='不详' then '2' end as zhyl6200029,\n" +
            "FADSC as zhyl6200030,\n" +
            "case when MOTHER='是' then '1' when MOTHER='否' then '0'  when MOTHER='不详' then '2' end as zhyl6200031,\n" +
            "MODSC as zhyl6200032,\n" +
            "case when [OTHERS]='是' then '1' when [OTHERS]='否' then '0' when [OTHERS]='不详' then '2'  end as zhyl6200033,\n" +
            "OTHRELAT as zhyl6200034,\n" +
            "OTHDSC as zhyl6200035\n" +
            "\n" +
            "\n" +
            "\n" +
            "from dbo.GT where  DELMARK=0 and DMID='?' ";

//最终诊断

    public final static String zuizhongzhenduan2 = "SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', DIDTC ) as bigint )*1000 AS zhyl6100098,\n" +
            "case when DITYPE='拟诊' then '1'  when DITYPE='确诊' then '2' end as  zhyl6100099,\n" +
            "DICONTENT  as  zhyl6100100 \n" +
            "\n" +
            "from dbo.DI where  DELMARK=0  and [SOURCE] is null  and DMID='?' ";

    //血常规
    public final static String xuechanggui2 = " select \n" +
            " cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BRDTC ) as bigint )*1000 AS zhyl60000211,\n" +
            " BRHOSP as zhyl60000212,\n" +
            " HGB as zhyl600002131,\n" +
            " HGBREF as zhyl600002132\n" +
            "from dbo.E_BR where  DELMARK=0   and DMID='?' ";

    //尿钙/肌酐比
    public final static String niaogaijigan2 = "SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.PCDTC ) as bigint )*1000 AS zhyl60000221,\n" +
            "b.PCHOSP as zhyl60000222,\n" +
            "b.PCORRES as zhyl600002231,\n" +
            "b.PCSREF as zhyl600002232\n" +
            "from dbo.E_PC b\n" +
            "where  b.DELMARK=0 and b.PCTYPE='尿钙/肌酐比' and DMID='?' ";

    //24小时尿电解质
    public final static String niaodianjiezhi24h2 = "select *,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', HEDTC ) as bigint )*1000 AS zhyl60000231\n" +
            "from dbo.ehe1 \n" +
            "where   HETYPE='24小时尿电解质'   and  DELMARK=0 and  DMID='?'  ";

    //肾脏影像学检查
    public final static String shenzangyingxiang2 = " SELECT \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl60000241,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', KIDTC ) as bigint )*1000 AS zhyl60000242,\n" +
            "KIHOSP as zhyl60000243,\n" +
            "case when KIMETHOD='泌尿系超声' then 1 when KIMETHOD='CT' then 2 when KIMETHOD='MRI' then 3 when KIMETHOD='腹部X线平片' then 4  end as zhyl60000244,\n" +
            "case when KIORRES='正常' then '1'  when KIORRES='异常' then '0'  end as zhyl60000245,\n" +
            "LSL as zhyl60000246,\n" +
            "LSW as zhyl60000247,\n" +
            "LSH as zhyl60000248,\n" +
            "RSL as zhyl60000249,\n" +
            "RSW as zhyl60000250,\n" +
            "RSH as zhyl60001251,\n" +
            "case when KIECHO='正常' then '1'  when KIECHO='增强' then '0'  end as zhyl60001252,\n" +
            "KIDESC as zhyl60001253,\n" +
            "UPLOCCU as zhyl60001254\n" +
            "\n" +
            "from dbo.E_KI \n" +
            "where  DELMARK=0  and  DMID='?'  ";

    //肾动态检查
    public final static String shendongtai2 = " select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', RDDTC ) as bigint )*1000 AS zhyl60000251,\n" +
            "RDHOSP as zhyl60000252,\n" +
            "RDORRE as zhyl60000253,\n" +
            "null as zhyl60000254\n" +
            "from dbo.E_RD  where  DELMARK=0  and  DMID='?'   ";


    //肝、胆影像学检查
    public final static String gandanyingxiang2 = " SELECT \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl60000261,\n" +
            "case when LGIOCCUR='是' then 1  when LGIOCCUR='否' then 0 end as  zhyl60000262,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', LGIDTC ) as bigint )*1000 AS zhyl60000263,\n" +
            "LGIHOSP as zhyl60000264,\n" +
            "case when LGIMETHOD='超声' then 1 when LGIMETHOD='CT' then 2 when LGIMETHOD='MRI' then 3 end as zhyl60000265,\n" +
            "case when LGIORRES='正常' then 1  when LGIORRES='异常' then 0  end as  zhyl60000266,\n" +
            "case when LGISIZE='缩小' then 0  when LGISIZE='正常' then 1   when LGISIZE='增大' then 2  end as  zhyl60000267,\n" +
            "case when LGIECHO='正常' then 1  when LGIECHO='增强' then 0  end as zhyl60000268,\n" +
            "LGIDESC as zhyl60000269,\n" +
            "null as zhyl60000270\n" +
            "\n" +
            "from dbo.E_LGI  where  DELMARK=0 \n and  DMID='?' ";


    //肝活检
    public final static String ganhuojian2 = " select \n" +
            "case when KBOCCUR='有' then '1' when KBOCCUR='无' then '0' end as zhyl60000271,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', KBDTC ) as bigint )*1000 AS zhyl60000272,\n" +
            "SITENAM AS zhyl60000273,\n" +
            "KBORRE as zhyl60000274,\n" +
            "KBDSC as zhyl60000275,\n" +
            "null as zhyl60000276\n" +
            "\n" +
            "from dbo.KB  where  DELMARK=0 and DMID='?'  ";

    //眼科检查
    public final static String yankejiancha2 = " select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', ESDTC ) as bigint )*1000 AS zhyl60000281,\n" +
            "ESHOSP as zhyl60000282,\n" +
            "case when ESOCCUR='有' then 1 when ESOCCUR='无' then 0 end as zhyl60000283,\n" +
            "replace(replace(replace(replace(replace(replace(replace(ESORRE,'视网膜色素变性',1),'眼球运动障碍',2),'近视',3),'远视',4),'弱视',5),'眼底神经萎缩',6),'其他',7)  as zhyl60000284,\n" +
            "null as zhyl60000285\n" +
            "from dbo.E_ES  where  DELMARK=0  and DMID='?' ";

    //肾活检-肾组织IV胶原染色
    public final static String shenhuojianranse2 = "select \n" +
            "case when JCDX='患儿' then  '1'  when JCDX='患儿亲属' then  '2' end as zhyl60000291,\n" +
            "YHEGX as zhyl60000292,\n" +
            "case when SHJ='有' then '1' when SHJ='无' then '0'  end as zhyl60000293,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', JCRQ ) as bigint )*1000 AS  zhyl60000294,\n" +
            "JCYY as zhyl60000295,\n" +
            "case when SZZJYRS='有' then '1' when SZZJYRS='无' then '0'  end as zhyl60000296,\n" +
            "case when JCJG='正常' then 1  when JCJG='异常' then 0 end as zhyl600002971,\n" +
            "case when A3GBMFBFS='阴性'  then 0  when A3GBMFBFS='间断阳性'  then 1  when A3GBMFBFS='连续阳性'  then 2 end as zhyl600002972,\n" +
            "A3GBMRSQD as zhyl600002973,\n" +
            "case when A3TBMFBFS='阴性'  then 0  when A3TBMFBFS='间断阳性'  then 1  when A3TBMFBFS='连续阳性'  then 2 end as zhyl600002974,\n" +
            "A3TBMRSQD as zhyl600002975,\n" +
            "case when A4GBMFBFS='阴性'  then 0  when A4GBMFBFS='间断阳性'  then 1  when A4GBMFBFS='连续阳性'  then 2 end as zhyl600002976,\n" +
            "A4GBMRSQD as zhyl600002977,\n" +
            "case when A4TBMFBFS='阴性'  then 0  when A4TBMFBFS='间断阳性'  then 1  when A4TBMFBFS='连续阳性'  then 2 end as zhyl600002978,\n" +
            "A4TBMRSQD as zhyl600002979,\n" +
            "case when A5BMSFBFS='阴性'  then 0  when A5BMSFBFS='间断阳性'  then 1  when A5BMSFBFS='连续阳性'  then 2 end as zhyl600002980,\n" +
            "A5BMSRSQD as zhyl600002981,\n" +
            "case when A5TBMFBFS='阴性'  then 0  when A5TBMFBFS='间断阳性'  then 1  when A5TBMFBFS='连续阳性'  then 2 end as zhyl600002982,\n" +
            "A5TBMRSQD as zhyl600002983," +
            "case when A5GBMFBFS='阴性'  then 0  when A5GBMFBFS='间断阳性'  then 1  when A5GBMFBFS='连续阳性'  then 2 end as zhyl600002985,\n" +
            "A5GBMRSQD as zhyl600002984\n" +
            "\n" +
            "from   dbo.RBTEMP  where   DMID='?'";

    //皮肤活检
    public final static String pifihuojian2 = "  select * from (\n" +
            "select a.dmid,\n" +
            "case when a.SBOBJ='患儿' then  '1'  when a.SBOBJ='患儿亲属' then  '2' end as  zhyl60000321,\n" +
            "a.SBOBJDSC as zhyl60000322,\n" +
            "case when a.SBOCCUR='有' then '1' when a.SBOCCUR='无' then '0'  end as zhyl60000323,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', a.SBDTC ) as bigint )*1000 AS  zhyl60000324,\n" +
            "a.SITENAM as zhyl60000325,\n" +
            "case when a.SBORRE='正常' then 1  when a.SBORRE='异常' then 0 end as  zhyl60000326,\n" +
            "case when a.DISMODE='阴性'  then 0  when a.DISMODE='间断阳性'  then 1  when a.DISMODE='连续阳性'  then 2 end as zhyl60000327,\n" +
            "a.INTESTA as zhyl60000328,\n" +
            "case when b.DISMODE='阴性'  then 0  when b.DISMODE='间断阳性'  then 1  when b.DISMODE='连续阳性'  then 2 end as zhyl60000329,\n" +
            "b.INTESTA as zhyl60000330\n" +
            " \n" +
            " from  (select * from  (\n" +
            "select  * ,ROW_NUMBER() OVER (PARTITION BY DMID,SBDTC,SBCAT ORDER BY FORMID desc) AS num\n" +
            "from  dbo. SB where SBCAT='α1（IV）染色'  and DELMARK=0 \n" +
            ") s where num=1) a \n" +
            "left join \n" +
            "(select * from  (\n" +
            "select  * ,ROW_NUMBER() OVER (PARTITION BY DMID,SBDTC,SBCAT ORDER BY FORMID desc) AS num\n" +
            "from  dbo. SB where SBCAT='α5（IV）染色'  and DELMARK=0 \n" +
            ") s where num=1) b on a.DMID=b.DMID and a.SBDTC=b.SBDTC\n" +
            ") v where dmid='?' ";

    //酸中毒时尿PH
    public final static String suanzhongdu2 = "select \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as  zhyl60000331,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', PHDTC ) as bigint )*1000 AS zhyl60000332,\n" +
            "PHHOSP as zhyl60000333,\n" +
            "PH as zhyl600003341,\n" +
            "PHREF as zhyl600003342\n" +
            "\n" +
            "from dbo.E_ph where DELMARK=0  and dmid='?' ";

    //尿氨基酸分析
    public final static String niaoanjisuan2 = " select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', AADTC ) as bigint )*1000 AS zhyl60000341,\n" +
            "AAHOSP as zhyl60000342,\n" +
            "AAORRE as zhyl60000343\n" +
            "\n" +
            "from dbo.E_AA where DELMARK=0 and dmid='?'";

    //尿酸化试验
    public final static String niaosuanhuashiyan2 = " select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', UADTC ) as bigint )*1000 AS  zhyl60000351,\n" +
            "UAHOSP as zhyl60000352,\n" +
            "UAPH as zhyl600003531,\n" +
            "UAPHREF as zhyl600003532,\n" +
            "UABCB as zhyl600003541,\n" +
            "UABCBREF as zhyl600003542,\n" +
            "UATA as zhyl600003551,\n" +
            "UATAREF as zhyl600003552,\n" +
            "UAAI as zhyl600003561,\n" +
            "UAAIREF as zhyl600003562\n" +
            "\n" +
            "from dbo.E_UA where DELMARK=0 and dmid='?' ";

    //血氨基酸测定
    public final static String xueanjisuan2 = " select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BADTC ) as bigint )*1000 AS zhyl60000361,\n" +
            "BAHOSP as zhyl60000362,\n" +
            "BAORRE as zhyl60000363\n" +
            "\n" +
            "from dbo.E_BA  where DELMARK=0  and dmid='?' ";

    //尿有机酸测定
    public final static String niaoyoujisuan2 = " select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', UODTC ) as bigint )*1000 AS zhyl60000371,\n" +
            "UOHOSP as zhyl60000372,\n" +
            "UOORRE as zhyl60000373\n" +
            "\n" +
            "from dbo.E_UO   where DELMARK=0 and dmid='?' ";

    //肾钙化/肾结石
    public final static String shengaihua2 = "  select \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as  zhyl60000381,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', KIDTC ) as bigint )*1000 AS zhyl60000382,\n" +
            "KIHOSP as zhyl60000383,\n" +
            "case when RCCOCCUR='是' then 1 when RCCOCCUR='否' then 0 end  as zhyl60000387,\n" +
            "case when KIMETHOD='泌尿系超声' then 1 when KIMETHOD='CT' then 2 when KIMETHOD='MRI' then 3  when KIMETHOD='腹部X线平片' then 4 end as zhyl60000384,\n" +
            "LSL as zhyl600003841,\n" +
            "LSW as zhyl600003842,\n" +
            "LSH as zhyl600003843,\n" +
            "RSL as zhyl600003844,\n" +
            "RSW as zhyl600003845,\n" +
            "RSH as zhyl600003846," +
            "KIDESC as zhyl60000385\n" +
            "from dbo.E_KI   where DELMARK=0 and dmid='?' ";

    //骨骼X线片
    public final static String gugex2 = "  select \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as  zhyl60000391,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', CRDTC ) as bigint )*1000 AS zhyl60000392,\n" +
            "CRHOSP as zhyl60000393,\n" +
            "replace(replace(replace(replace(CRLOCATION,'腕骨','1'),'上肢骨','2'),'下肢骨','3'),'腰椎','4') as zhyl60000394,\n" +
            "CRORRE as zhyl60000395\n" +
            "\n" +
            "from dbo.E_CR   where DELMARK=0 and dmid='?' ";

    //血甲状旁腺素
    public final static String xuejiazhuangpang2 = " select \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl60000401,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', PTHDTC  ) as bigint )*1000 as  zhyl60000402,\n" +
            "PTH as zhyl600004031,\n" +
            "PTHREF as zhyl600004032\n" +
            "from dbo.E_PTH where DELMARK=0 and dmid='?'  ";


    //泌尿系统影像学检查
    public final static String miniaoxitongyingxiang2 = " select \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl60000411,\n" +
            "case when USTOCCUR='是' then 1 when USTOCCUR='否' then 0 end  as zhyl60000412,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', USTDTC  ) as bigint )*1000 as  zhyl60000413,\n" +
            "null as zhyl60000414,\n" +
            "case when USTMETHOD='泌尿系超声' then 1 when USTMETHOD='CT' then 2 when USTMETHOD='MRT' then 3 end as zhyl60000415,\n" +
            "case when USTORRES='正常' then 1  when USTORRES='异常' then 0 end as zhyl60000416,\n" +
            "case when USTSIZE='正常' then 1  when USTSIZE='缩小' then 0  when USTSIZE='增大' then 2 end as zhyl60000417,\n" +
            "case when USTECHO='正常' then 1  when USTECHO='增强' then 0 end as zhyl60000418,\n" +
            "case when URETER='扩张' then 1  when URETER='狭窄' then 2 end as zhyl60000419,\n" +
            "case when BLADDER='正常' then 1  when BLADDER='异常' then 0 end as zhyl60000420,\n" +
            "USTDESC as zhyl60000421,\n" +
            "null as zhyl60000422\n" +
            "from dbo.E_UST where DELMARK=0  and dmid='?'  ";

    //静脉肾盂造影
    public final static String jingmaishenyuzaoying2 = "select \n" +
            "\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', IVPDTC  ) as bigint )*1000 as  zhyl60000423,\n" +
            "case when IVPORRES='正常' then 1  when IVPORRES='异常' then 0 end as zhyl60000424,\n" +
            "IVPDESC as zhyl60000425,\n" +
            "null as zhyl60000426\n" +
            "from dbo.E_IVP where DELMARK=0 and dmid='?' ";


    //排泄性膀胱尿路造影
    public final static String paixiexingpangguang2 = "select \n" +
            "\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', MCUDTC  ) as bigint )*1000 as  zhyl60000431,\n" +
            "case when MCUORRES='正常' then 1  when MCUORRES='异常' then 0 end as zhyl60000432,\n" +
            "MCUDESC as zhyl60000433,\n" +
            "null as zhyl60000434\n" +
            "from dbo.E_MCU where DELMARK=0  and dmid='?' ";

    //泌尿系超声
    public final static String miniaoxichaoshen2=" select \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl600001441,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', KIDTC ) as bigint )*1000 AS zhyl600001442,\n" +
            "KIHOSP as zhyl600001443,\n" +
            "case when KIMETHOD='泌尿系超声' then 1 when KIMETHOD='CT' then 2 when KIMETHOD='MRI' then 3 when KIMETHOD='腹部X线平片' then 4 end as zhyl600001444,\n" +
            "case when KIORRES='正常' then '1'  when KIORRES='异常' then '0'  end as zhyl600001445,\n" +
            "LSL as zhyl600001446,\n" +
            "LSW as zhyl600001447,\n" +
            "LSH as zhyl600001448,\n" +
            "RSL as zhyl600001449,\n" +
            "RSW as zhyl600001450,\n" +
            "RSH as zhyl600001451,\n" +
            "case when KIECHO='正常' then '1'  when KIECHO='增强' then '0'  end as zhyl600001452,\n" +
            "KIDESC as zhyl600001453\n" +
            "from dbo.E_KI \n" +
            "where  DELMARK=0  and dmid='?'";

    //结局
    public final static String jieju2 = "  select \n" +
            "VERNAM as zhyl9100004,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', VERDTC ) as bigint )*1000 AS  zhyl9100005,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', ENDTC ) as bigint )*1000 AS  zhyl9100000,\n" +
            "case when DMRESULT='生存' then '1' when DMRESULT='死亡' then '2' when DMRESULT='不详' then '3'  end as zhyl9100001,\n" +
            "\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', DEATHDTC ) as bigint )*1000 as zhyl9200013,\n" +
            "replace(replace(replace(replace(replace(DEATHRESON,'_',','),'感染','1'),'电解质紊乱','2'),'心血管事件','3'),'其他','4') zhyl9200014,\n" +
            "RESONDSC  as zhyl9200015,\n" +
            "\n" +
            "\n" +
            "case when ISSSJ='是' then '1'when ISSSJ='否' then '0'  when ISSSJ='不详' then '2'   end as zhyl9200000,\n" +
            "case when CKDTYPE='CKDII期' then '1' when CKDTYPE='CKDIII期' then '2'   when CKDTYPE='CKDIV期' then '3'   when CKDTYPE='CKDV期' then '4' end as zhyl9200001,\n" +
            "\n" +
            "replace(replace(replace(replace(replace(replace(replace(OPERTYPE,'_',','),'腹膜透析','1'),'血液透析','2'),'肾移植','3'),'其它','4'),'染_',''),'他_','') as zhyl9200002,\n" +
            "\n" +
            "TPYEAR  as zhyl9200004,\n" +
            "FTYEAR as zhyl9200006,\n" +
            "XTYEAR  as zhyl9200008,\n" +
            "YZYEAR as zhyl9200010,\n" +
            "case when YZSOURCE='亲属' then '1'when YZSOURCE='非亲属' then '2' end  as zhyl9200011,\n" +
            "GYZYEAR as zhyl9200012\n" +
            "\n" +
            "\n" +
            "from dbo.DMEND where DELMARK=0  and DMID='?' ";

    //用药
    public final static String yongyao2 = "  \n" +
            "select  a.id as _id, \n" +
            "case when CMOCCUR='有' then '1'  when CMOCCUR='无' then '0'  end as zhyl7000001,\n" +
            "case when CMCAT='降压药' then '100000' when CMCAT='利尿剂' then '200000' when CMCAT='免疫抑制药物' then '300000' when CMCAT='调节电解质、酸碱平衡药' then '400000' when CMCAT='维生素、微量元素药物' then '500000'    end as zhyl7000002,\n" +
            "case when b.Column1 like '101%' or a.CMSCAT='ACEI' then '101000'\n" +
            "when b.Column1 like '102%' or a.CMSCAT='ARB' then '102000' \n" +
            "when b.Column1 like '201%' or a.CMSCAT='利尿剂' then '201000' \n" +
            "when b.Column1 like '301%' or a.CMSCAT='免疫抑制剂' then '301000' \n" +
            "when b.Column1 like '302%' or a.CMSCAT='糖皮质激素' then '302000' \n" +
            "when b.Column1 like '401%' or a.CMSCAT='电解质' then '401000' \n" +
            "when b.Column1 like '501%' or a.CMSCAT='维生素、微量元素药物' then '501000' \n" +
            "end  as zhyl7000003,\n" +
            "b.Column1 as zhyl7000004," +
            "case when CMDOSFRM='片剂' then '1'when CMDOSFRM='注射剂' then '2'when CMDOSFRM='胶囊剂' then '3'when CMDOSFRM='颗粒剂' then '4'when CMDOSFRM='丸剂' then '5'\n" +
            "when CMDOSFRM='栓剂' then '6'\n" +
            "when CMDOSFRM='溶液' then '7' end as zhyl7000005,\n" +
            "CMDOSE as zhyl7000006,\n" +
            "case when CMDOSU='g' then '0' when CMDOSU='mg' then '1' when CMDOSU='μg' then '2' when CMDOSU='L' then '3' when CMDOSU='dl' then '4'\n" +
            "when CMDOSU='ml' then '5'  when CMDOSU='v' then '6'  when CMDOSU='IU' then '7'  when CMDOSU='万U' then '8' END AS  zhyl7000007,\n" +
            "case when CMDOSFRQ like '%qd 每日一次%' then '1'\n" +
            "when CMDOSFRQ like '%bid 每日两次%' then '2'\n" +
            "when CMDOSFRQ like '%tid 每日三次%' then '3'\n" +
            "when CMDOSFRQ like '%qid 每日四次%' then '4'\n" +
            "when CMDOSFRQ like '%qh 每小时一次%' then '5'\n" +
            "when CMDOSFRQ like '%q2h 每两小时一次%' then '6'\n" +
            "when CMDOSFRQ like '%q4h 每四小时一次%' then '7'\n" +
            "when CMDOSFRQ like '%qn 每晚一次%' then '8'\n" +
            "when CMDOSFRQ like '%qod 每隔天一次s%' then '9'\n" +
            "when CMDOSFRQ like '%biw 每周两次%' then '10'\n" +
            "when CMDOSFRQ like '%hs 临睡前%' then '11'\n" +
            "when CMDOSFRQ like '%am 上午%' then '12'\n" +
            "when CMDOSFRQ like '%pm 下午%' then '13'\n" +
            "when CMDOSFRQ like '%prn 必要时%' then '14'\n" +
            "when CMDOSFRQ like '%sos 紧急时%' then '15'\n" +
            "when CMDOSFRQ like '%ac 饭前%' then '16'\n" +
            "when CMDOSFRQ like '%pc 饭后%' then '17'\n" +
            "when CMDOSFRQ like '%12n 中午12点%' then '18'\n" +
            "when CMDOSFRQ like '%12mn 午夜12点%' then '19' end as zhyl7000008,\n" +
            "CMDOSTOT as zhyl7000009,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', CMSTDTC ) as bigint )*1000 as zhyl7000010,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', CMENDTC ) as bigint )*1000 as zhyl7000011,\n" +
            "CMENRES as zhyl7000012,\n" +
            "case when CMSEOCCU='有' then '1' when CMSEOCCU='无' then '0' when CMSEOCCU='不详' then '2'  end as zhyl7000013,\n" +
            "CMSEDESC as zhyl7000014\n" +
            "\n" +
            "from dbo.CM  a  left join dbo.drug b on a.CMTRT=b.Column2  where a. DELMARK=0 and DMID='?'  ";


    //-------------------随访-------------------
//随访id
    public final static String suifang2 = "  SELECT ID from  dbo.VISIT where  DELMARK=0 and  DMID='?' ";
    //随访时间
    public final static String suifangtime = " select  cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', VDTC ) as bigint )*1000 as zhyl8000001 from  dbo.VISIT where ID='?'  ";
    public final static String suifangtime1 = " select  VDTC  from  dbo.VISIT where ID='?'  ";
    //体格检查
    public final static String suifangtigejiancha2 = "select b.DMID,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.VSDTC) as bigint )*1000 AS  zhyl8100000,\n" +
            "b.HEIGHT as zhyl8100001,\n" +
            "WEIGHT as zhyl8100002,\n" +
            "b.SBP as zhyl8200001,\n" +
            "b.DBP as zhyl8200002,\n" +
            "case when b12.PEOCCUR='是' then 1 when b12.PEOCCUR='否' then 0 when b12.PEOCCUR='不详' then 2 end  as zhyl8100004 ,\n" +
            "b12.PEDESCR as zhyl8100005\n" +
            "\n" +
            "from  dbo.VS b \n" +
            "left join (select * from  (\n" +
            "select  *,ROW_NUMBER() OVER (PARTITION BY VSID,PETEST  ORDER BY PEOCCUR desc) AS num\n" +
            "from dbo.PE where PETEST='佝偻病体征' and SOURCE='随访'\n" +
            ") s where num=1) b12 on b.ID =b12.VSID  \n" +
            "where b.DELMARK=0 and b.[SOURCE]='随访' and b.SOURCEID ='?' ";
    //血生化
    public final static String suifangxueshenghua2 = " select   \n" +
            "\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BBDTC ) as bigint )*1000 AS zhyl800000702,\n" +
            "BBHOSP as  zhyl800000703,\n" +
            "ALB as zhyl800000704,\n" +
            "SCR as zhyl8000007051,\n" +
            "SCRREF as zhyl8000007052,\n" +
            "BUN as zhyl8000007061,\n" +
            "BUNREF as zhyl8000007062,\n" +
            "TCHO as zhyl8000007071,\n" +
            "TCHOREF as zhyl8000007072,\n" +
            "ALT as zhyl8000007081,\n" +
            "ALTREF as zhyl8000007082,\n" +
            "AST as zhyl8000007091,\n" +
            "ASTREF as zhyl8000007092,\n" +
            "K as zhyl8000007101,\n" +
            "KREF as zhyl8000007102,\n" +
            "P as zhyl8000007111,\n" +
            "PREF as zhyl8000007112,\n" +
            "CA as zhyl8000007121,\n" +
            "CAREF as zhyl8000007122,\n" +
            "CL as zhyl8000007131,\n" +
            "CLREF as zhyl8000007132,\n" +
            "MG as zhyl8000007141,\n" +
            "MGREF as zhyl8000007142,\n" +
            "NA as zhyl8000007151,\n" +
            "NAREF as zhyl8000007152,\n" +
            "AG as zhyl8000007161,\n" +
            "AGREF as zhyl8000007162,\n" +
            "ALP as zhyl8000007171,\n" +
            "ALPREF as zhyl8000007172,\n" +
            "LD as zhyl8000007181,\n" +
            "LDREF as zhyl8000007182,\n" +
            "TBA as zhyl8000007191,\n" +
            "TBAREF as zhyl8000007192,\n" +
            "GGT as zhyl8000007201,\n" +
            "GGTREF as zhyl8000007202,\n" +
            "HCO3 as zhyl8000007211,\n" +
            "HCO3REF as zhyl8000007212,\n" +
            "LAT as zhyl8000007221,\n" +
            "LATREF as zhyl8000007222,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', VERDTC ) as bigint )*1000 AS  zhyl800000723\n" +
            "\n" +
            "\n" +
            "from dbo.E_BB  where  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?'  ";
    //尿常规
    public final static String suifangniaochanggui2 = "  SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.URDTC ) as bigint )*1000 AS zhyl800000102,\n" +
            "case when b.UPL='-' then '0' when b.UPL='+-' then '1' when b.UPL='+' then '2' when b.UPL='++' then '3'  \n" +
            "when b.UPL='+++' then '4'when b.UPL='+++++' then '5' end as zhyl800000104, \n" +
            "b.UPN as zhyl8000001051,\n" +
            "UPNREF as zhyl8000001052,\n" +
            "case when b.RBCAN='是' then '1' when b.RBCAN='否' then '0'  end as zhyl8000001061,\n" +
            "b.RBC as zhyl8000001062,\n" +
            "RBC1 as zhyl8000001063,\n" +
            "RBCDSC as zhyl8000001064,\n" +
            "SG as zhyl8000001081,\n" +
            "SGREF as zhyl8000001082,\n" +
            "GLU as zhyl8000001091,\n" +
            "GLUREF as zhyl8000001092,\n" +
            "PH as zhyl8000001111,\n" +
            "PHREF as zhyl8000001112\n" +
            "\n" +
            "from  dbo.E_UR b \n" +
            "where  b.DELMARK=0  and  [SOURCE]='随访'  and SOURCEID ='?'  ";
    //尿蛋白/肌酐
    public final static String suifangniaodanbaijigan2 = " SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.PCDTC ) as bigint )*1000 AS zhyl800000202,\n" +
            "PCNDB as zhyl800000204,\n" +
            "PCNJG as zhyl800000205,\n" +
            "PCORRES as zhyl8000002061,\n" +
            "PCSREF as zhyl8000002062\n" +
            "from dbo.E_PC b  \n" +
            "where  b.DELMARK=0  and PCTYPE ='尿蛋白/肌酐比' and  [SOURCE]='随访'   and SOURCEID ='?' ";
    //24小时尿蛋白定量
    public final static String suifang24niaodanbai2 = "  SELECT \n" +
            "\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.UPDTC ) as bigint )*1000 AS zhyl800000302,\n" +
            "b.UPDL as zhyl8000003041,\n" +
            "UPDLREF as zhyl8000003042,\n" +
            "UPWEIGHT as zhyl800000305,\n" +
            "b.UPUWP as zhyl8000003061,\n" +
            "b.UPUWPREF as zhyl8000003062\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_UP b on a.ID =b.DMID  \n" +
            "where  b.DELMARK=0  and  [SOURCE]='随访'   and SOURCEID ='?'  ";
    //24小时肌酐清除率
    public final static String suifang24jiganqingchu2 = " select *,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', HEDTC ) as bigint )*1000 AS zhyl800000402,\n" +
            "HEHOSP as zhyl800000403\n" +
            "from dbo.ehe1 \n" +
            "where   HETYPE='24小时肌酐清除率'   and  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?'  ";
    //肾早衰
    public final static String suifangshenzaoshuai2 = " select  \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', IRDTC ) as bigint )*1000 AS zhyl800000502,\n" +
            "IRHOSP as zhyl800000503,\n" +
            "MALB as zhyl8000005041,\n" +
            "MALBREF as zhyl8000005042,\n" +
            "case when MALBORRE='正常' then '1'   when MALBORRE='异常' then '0'   when MALBORRE='不详' then '2'  end  as  zhyl8000005043,\n" +
            "TRU as zhyl8000005051,\n" +
            "TRUREF as zhyl8000005052,\n" +
            "case when TRUORRE='正常' then '1'   when TRUORRE='异常' then '0'   when TRUORRE='不详' then '2'  end  as zhyl8000005053,\n" +
            "NAG as zhyl8000005061,\n" +
            "NAGREF as zhyl8000005062,\n" +
            "case when NAGORRE='正常' then '1'   when NAGORRE='异常' then '0'   when NAGORRE='不详' then '2'  end  as zhyl8000005063,\n" +
            "A1MG as zhyl8000005071,\n" +
            "A1MGREF as zhyl8000005072,\n" +
            "case when A1MGORRE='正常' then '1'   when A1MGORRE='异常' then '0'   when A1MGORRE='不详' then '2'  end  as zhyl8000005073,\n" +
            "MALBJG as zhyl8000005081,\n" +
            "MALBJGREF as zhyl8000005082,\n" +
            "case when MALBJGORRE='正常' then '1'   when MALBJGORRE='异常' then '0'   when MALBJGORRE='不详' then '2'  end  as zhyl8000005083,\n" +
            "IROTHER as zhyl800000509\n" +
            "\n" +
            "\n" +
            "from dbo.E_IR  where  DELMARK=0  and  [SOURCE]='随访'   and SOURCEID ='?'  ";

    //尿蛋白电泳
    public final static String suifangniaodanbaidianyong2 = "  select   \n" +
            "\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', UEDTC ) as bigint )*1000 AS zhyl800000602,\n" +
            "SPPORG  as zhyl8000006041,\n" +
            "SPPREF as zhyl8000006042,\n" +
            "ALBPORG as zhyl8000006051,\n" +
            "ALBPREF as zhyl8000006052,\n" +
            "BPPORG as zhyl8000006061,\n" +
            "BPPREF as zhyl8000006062\n" +
            "\n" +
            "from dbo.E_UE  where  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?' ";

    //纯音测听
    public final static String suifangchunyinceting2 = "select dmid,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', PTDTC ) as bigint )*1000 AS zhyl8100070,\n" +
            "case when PTORRE='正常' then '1'  when PTORRE='异常' then '0'  end  as zhyl8100073,\n" +
            "PTDESC as zhyl8100074\n" +
            "\n" +
            "from  dbo.E_PT where  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?' ";

    //眼裂隙灯检查
    public final static String suifangyanleixideng2 = " select dmid,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', ESDTC ) as bigint )*1000 AS zhyl8100076,\n" +
            "case when ESORRE='正常' then '1'  when ESORRE='异常' then '0'  end  as zhyl8100079,\n" +
            "ESDESC as zhyl8100080\n" +
            "from  dbo.E_ES where  DELMARK=0  and  [SOURCE]='随访'   and SOURCEID ='?' ";

    //随访血常规
    public final static String suifangxuechanggui2 = " select \n" +
            " cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', HGBDTC ) as bigint )*1000 AS zhyl80000211,\n" +
            " HGB as zhyl800002131,\n" +
            " HGBREF as zhyl800002132\n" +
            "from dbo.E_HGB where  DELMARK=0  and  [SOURCE]='随访'    and SOURCEID ='?' ";

    //24小时尿电解质
    public final static String suifang24niaodianjiezhi = " select *,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', HEDTC ) as bigint )*1000 AS zhyl80000231\n" +
            "from dbo.ehe1 \n" +
            "where   HETYPE='24小时尿电解质'   and  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?'  ";

    //肾脏影像学检查
    public final static String suifangshenzangyixuejiancha = " SELECT \n" +
            "\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', KIDTC ) as bigint )*1000 AS zhyl80000242,\n" +
            "KIHOSP as zhyl80000243,\n" +
            "case when KIMETHOD='泌尿系超声' then 1 when KIMETHOD='CT' then 2 when KIMETHOD='MRI' then 3 when KIMETHOD='腹部X线平片' then 4 end as zhyl80000244,\n" +
            "case when KIORRES='正常' then '1'  when KIORRES='异常' then '0'  end as zhyl80000245,\n" +
            "LSL as zhyl80000246,\n" +
            "LSW as zhyl80000247,\n" +
            "LSH as zhyl80000248,\n" +
            "RSL as zhyl80000249,\n" +
            "RSW as zhyl80000250,\n" +
            "RSH as zhyl80001251,\n" +
            "case when KIECHO='正常' then '1'  when KIECHO='增强' then '0'  end as zhyl80001252,\n" +
            "KIDESC as zhyl80001253\n" +
            "\n" +
            "\n" +
            "from dbo.E_KI \n" +
            "where  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?' ";

    //肝、胆影像学检查
    public final static String suifanggandanyingxiangxue = " SELECT \n" +
            "\n" +
            "case when LGIOCCUR='是' then 1  when LGIOCCUR='否' then 0 end as  zhyl80000262,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', LGIDTC ) as bigint )*1000 AS zhyl80000263,\n" +
            "case when LGIMETHOD='超声' then 1 when LGIMETHOD='CT' then 2 when LGIMETHOD='MRI' then 3 end as zhyl80000265,\n" +
            "case when LGIORRES='正常' then 1  when LGIORRES='异常' then 0  end as  zhyl80000266,\n" +
            "case when LGISIZE='缩小' then 0  when LGISIZE='正常' then 1   when LGISIZE='增大' then 2  end as  zhyl80000267,\n" +
            "case when LGIECHO='正常' then 1  when LGIECHO='增强' then 0  end as zhyl80000268,\n" +
            "LGIDESC as zhyl80000269\n" +
            "\n" +
            "from dbo.E_LGI  where  DELMARK=0  and  [SOURCE]='随访'   and SOURCEID ='?' ";

    //酸中毒时尿PH
    public final static String suifangsuanzhongdu = "select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', PHDTC ) as bigint )*1000 AS zhyl80000332,\n" +
            "\n" +
            "PH as zhyl800003341,\n" +
            "PHREF as zhyl800003342\n" +
            "\n" +
            "from dbo.E_ph where DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?' ";

    //肾钙化/肾结石
    public final static String suifangshengaihua = "select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', KIDTC ) as bigint )*1000 AS zhyl80000382,\n" +
            "case when RCCOCCUR='是' then 1 when RCCOCCUR='否' then 0 end  as zhyl80000387,\n" +
            "case when KIMETHOD='泌尿系超声' then 1 when KIMETHOD='CT' then 2 when KIMETHOD='MRI' then 3 when KIMETHOD='腹部X线平片' then 4  end as zhyl80000384\n" +
            "\n" +
            "from dbo.E_KI   where DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?' ";

    //骨骼X线片
    public final static String suifanggugex = " select \n" +
            "\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', CRDTC ) as bigint )*1000 AS zhyl80000392,\n" +
            "replace(replace(replace(replace(CRLOCATION,'腕骨','1'),'上肢骨','2'),'下肢骨','3'),'腰椎','4') as zhyl80000394,\n" +
            "CRORRE as zhyl80000395\n" +
            "\n" +
            "from dbo.E_CR   where DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?'  ";

    //血甲状旁腺素
    public final static String suifangxuejiapang = " select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', PTHDTC  ) as bigint )*1000 as  zhyl80000402,\n" +
            "PTH as zhyl800004031,\n" +
            "PTHREF as zhyl800004032\n" +
            "from dbo.E_PTH where DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?' ";

    //泌尿系统影像学检查
    public final static String suifangminiaoxiyingxiangjiancha = " select \n" +
            "case when USTOCCUR='是' then 1 when USTOCCUR='否' then 0 end  as zhyl80000412,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', USTDTC  ) as bigint )*1000 as  zhyl80000413,\n" +
            "null as zhyl80000414,\n" +
            "case when USTMETHOD='泌尿系超声' then 1 when USTMETHOD='CT' then 2 when USTMETHOD='MRT' then 3 end as zhyl80000415,\n" +
            "case when USTORRES='正常' then 1  when USTORRES='异常' then 0 end as zhyl80000416,\n" +
            "case when USTSIZE='正常' then 1  when USTSIZE='缩小' then 0  when USTSIZE='增大' then 2 end as zhyl80000417,\n" +
            "case when USTECHO='正常' then 1  when USTECHO='增强' then 0 end as zhyl80000418,\n" +
            "case when URETER='扩张' then 1  when URETER='狭窄' then 2 end as zhyl80000419,\n" +
            "case when BLADDER='正常' then 1  when BLADDER='异常' then 0 end as zhyl80000420,\n" +
            "USTDESC as zhyl80000421\n" +
            "\n" +
            "from dbo.E_UST where DELMARK=0  and  [SOURCE]='随访'   and SOURCEID ='?' ";

    //泌尿系超声
    public final static String suifangchaoshen2 = " select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.BUDTC ) as bigint )*1000 AS zhyl80000282,\n" +
            "BUHOSP as zhyl80000283,\n" +
            "null as zhyl80000284,\n" +
            "case when b.BUORRE='正常' then '1'  when b.BUORRE='异常' then '0'  end as zhyl80000285,\n" +
            "b.LSL as zhyl80000286,\n" +
            "b.LSW as zhyl80000287,\n" +
            "b.LSH as zhyl80000288,\n" +
            "b.RSL as zhyl80000289,\n" +
            "b.RSW as zhyl80000290,\n" +
            "b.RSH as zhyl80000291,\n" +
            "case when b.BUECHO='正常'  then '1'   when b.BUECHO='增强'  then '0'   end  as zhyl80000292,\n" +
            "b.BUDESC as zhyl80000293\n" +
            "\n" +
            "from  \n" +
            "(select * from  (\n" +
            "select  * ,ROW_NUMBER() OVER (PARTITION BY DMID,BUDTC,BUCAT ORDER BY BUDTC desc) AS num\n" +
            "from  dbo.E_BU where BUCAT='肾脏'  and DELMARK=0 and [SOURCE] ='随访'\n" +
            ") s where num=1) b   where  SOURCEID ='?'  ";

    //泌尿系超声
    public final static String suifangchaoshen4 = "SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', KIDTC ) as bigint )*1000 AS zhyl80000282,\n" +
            "KIHOSP as zhyl80000283,\n" +
            "case when KIMETHOD='泌尿系超声' then 1 when KIMETHOD='CT' then 2 when KIMETHOD='MRI' then 3 when KIMETHOD='腹部X线平片' then 4 end as zhyl80000284,\n" +
            "case when KIORRES='正常' then '1'  when KIORRES='异常' then '0'  end as zhyl80000285,\n" +
            "LSL as zhyl80000286,\n" +
            "LSW as zhyl80000287,\n" +
            "LSH as zhyl80000288,\n" +
            "RSL as zhyl80000289,\n" +
            "RSW as zhyl80000290,\n" +
            "RSH as zhyl80000291,\n" +
            "case when KIECHO='正常' then '1'  when KIECHO='增强' then '0'  end as zhyl80000292,\n" +
            "KIDESC as zhyl80000293\n" +
            "from dbo.E_KI \n" +
            "where  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?'  ";

    //肝、胆影像学检查
    public final static String gandanyingxiangxue4 = " SELECT \n" +
            "\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', LGIDTC ) as bigint )*1000 AS zhyl80000101,\n" +
            "case when LGIMETHOD='超声' then 1 when LGIMETHOD='CT' then 2 when LGIMETHOD='MRI' then 3 end as zhyl80000102,\n" +
            "case when LGISIZE='缩小' then 0  when LGISIZE='正常' then 1   when LGISIZE='增大' then 2  end as  zhyl80000103,\n" +
            "case when LGIECHO='正常' then 1  when LGIECHO='增强' then 0  end as zhyl80000104,\n" +
            "LGIDESC as zhyl80000105\n" +
            "\n" +
            "from dbo.E_LGI  where  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?' ";


    //用药
    public final static String suifangyongyao2 = " select  \n" +
            "case when CMOCCUR='有' then '1'  when CMOCCUR='无' then '0'  end as zhyl8100037,\n" +
            "case when CMCAT='降压药' then '100000' when CMCAT='利尿剂' then '200000' when CMCAT='免疫抑制药物' then '300000'  end as zhyl8100038,\n" +
            "case when b.Column1 like '101%' or a.CMSCAT='ACEI' then '101000'\n" +
            "when b.Column1 like '102%' or a.CMSCAT='ARB' then '102000' \n" +
            "when b.Column1 like '201%' or a.CMSCAT='利尿剂' then '201000' \n" +
            "when b.Column1 like '301%' or a.CMSCAT='免疫抑制剂' then '301000' \n" +
            "when b.Column1 like '302%' or a.CMSCAT='糖皮质激素' then '302000' \n" +
            "when b.Column1 like '401%' or a.CMSCAT='电解质' then '401000' \n" +
            "when b.Column1 like '501%' or a.CMSCAT='维生素、微量元素药物' then '501000' \n" +
            "end  as zhyl8100039,\n" +
            "b.Column1 as zhyl8100040,\n" +
            "\n" +
            "case when CMDOSFRM='片剂' then '1'when CMDOSFRM='注射剂' then '2'when CMDOSFRM='胶囊剂' then '3'when CMDOSFRM='颗粒剂' then '4'when CMDOSFRM='丸剂' then '5'\n" +
            "when CMDOSFRM='栓剂' then '6'\n" +
            "when CMDOSFRM='溶液' then '7' end as zhyl8100041,\n" +
            "CMDOSE as zhyl8100042,\n" +
            "case when CMDOSU='g' then '0' when CMDOSU='mg' then '1' when CMDOSU='μg' then '2' when CMDOSU='L' then '3' when CMDOSU='dl' then '4'\n" +
            "when CMDOSU='ml' then '5'  when CMDOSU='v' then '6'  when CMDOSU='IU' then '7'  when CMDOSU='万U' then '8' END AS  zhyl8100043,\n" +
            "case when CMDOSFRQ like '%qd 每日一次%' then '1'\n" +
            "when CMDOSFRQ like '%bid 每日两次%' then '2'\n" +
            "when CMDOSFRQ like '%tid 每日三次%' then '3'\n" +
            "when CMDOSFRQ like '%qid 每日四次%' then '4'\n" +
            "when CMDOSFRQ like '%qh 每小时一次%' then '5'\n" +
            "when CMDOSFRQ like '%q2h 每两小时一次%' then '6'\n" +
            "when CMDOSFRQ like '%q4h 每四小时一次%' then '7'\n" +
            "when CMDOSFRQ like '%qn 每晚一次%' then '8'\n" +
            "when CMDOSFRQ like '%qod 每隔天一次s%' then '9'\n" +
            "when CMDOSFRQ like '%biw 每周两次%' then '10'\n" +
            "when CMDOSFRQ like '%hs 临睡前%' then '11'\n" +
            "when CMDOSFRQ like '%am 上午%' then '12'\n" +
            "when CMDOSFRQ like '%pm 下午%' then '13'\n" +
            "when CMDOSFRQ like '%prn 必要时%' then '14'\n" +
            "when CMDOSFRQ like '%sos 紧急时%' then '15'\n" +
            "when CMDOSFRQ like '%ac 饭前%' then '16'\n" +
            "when CMDOSFRQ like '%pc 饭后%' then '17'\n" +
            "when CMDOSFRQ like '%12n 中午12点%' then '18'\n" +
            "when CMDOSFRQ like '%12mn 午夜12点%' then '19' end as zhyl8100044,\n" +
            "CMDOSTOT as zhyl8100045,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', CMSTDTC ) as bigint )*1000 as zhyl8100046,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', CMENDTC ) as bigint )*1000 as zhyl8100047,\n" +
            "CMENRES as zhyl8100048,\n" +
            "case when CMSEOCCU='有' then '1' when CMSEOCCU='无' then '0' when CMSEOCCU='不详' then '2'  end as zhyl8100049,\n" +
            "CMSEDESC as zhyl8100050\n" +
            "\n" +
            "from dbo.CM  a  left join dbo.drug b on a.CMTRT=b.Column2 where a. DELMARK=0  and (\n" +
            " (CMSTDTC IS NOT NULL AND CMENDTC IS NOT NULL AND '?' BETWEEN CMSTDTC AND CMENDTC) \n" +
            " or   (CMSTDTC IS NOT NULL AND CMENDTC IS NULL AND  '?' > CMSTDTC) )  and dmid='#'  ";

    //累及其他系统
    //肌肉骨骼
    public final static String jirouguge = " select  \n" +
            "case when OSOCCUR='其它' then  4  when OSOCCUR='多指畸形' then  2 end as zhyl12000001,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', OSDTC  ) as bigint )*1000 as  zhyl12000002,\n" +
            "OSDESC as zhyl12000003\n" +
            "from dbo.OtherSystem where DELMARK=0 and OSTYPE='肌肉骨骼' and dmid='?' ";
    //消化系统
    public final static String xiaohuaxitong = " select  \n" +
            "case when OSOCCUR='肛门闭锁' then  1   end as zhyl12000004,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', OSDTC  ) as bigint )*1000 as  zhyl12000005,\n" +
            "OSDESC as zhyl12000006\n" +
            "from dbo.OtherSystem where DELMARK=0 and OSTYPE='消化系统' and dmid='?' ";
    //先天性心脏病
    public final static String xiantianxingxinzangbing = " select  \n" +
            "case when OSOCCUR='房间隔缺损' then  1   end as zhyl12000007,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', OSDTC  ) as bigint )*1000 as  zhyl12000008,\n" +
            "OSDESC as zhyl12000009\n" +
            "from dbo.OtherSystem where DELMARK=0 and OSTYPE='先天性心脏病' and dmid='?' ";
    //中枢神经系统
    public final static String zhongshushenjing = " select  \n" +
            "case when OSOCCUR='其它' then  5  when OSOCCUR='脊柱裂' then  1   end as zhyl12000010,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', OSDTC  ) as bigint )*1000 as  zhyl12000011,\n" +
            "OSDESC as zhyl12000012\n" +
            "from dbo.OtherSystem where DELMARK=0 and OSTYPE='中枢神经系统' and dmid='?' ";
    //耳、面、颈部
    public final static String ermianjing = " select  \n" +
            "case when OSOCCUR='其它' then  5  when OSOCCUR='脊柱裂' then  1   end as zhyl12000013,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', OSDTC  ) as bigint )*1000 as  zhyl12000014,\n" +
            "OSDESC as zhyl12000015\n" +
            "from dbo.OtherSystem where DELMARK=0 and OSTYPE='耳、面、颈部' and dmid='?' ";
    //尿道下裂
    public final static String niaodaoxielie = " select  \n" +
            "case when OSOCCUR='是' then  1  when OSOCCUR='否' then  0   end as zhyl12000025,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', OSDTC  ) as bigint )*1000 as  zhyl12000026,\n" +
            "OSDESC as zhyl12000021\n" +
            "from dbo.OtherSystem where DELMARK=0 and OSTYPE='尿道下裂' and dmid='?' ";
    //染色体检测
    public final static String ranseti = " select  \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', VERDTC  ) as bigint )*1000 as  zhyl12000037,\n" +
            "CHU as zhyl12000034,\n" +
            "case when CHOCCUR='无法判断' then 2  when CHOCCUR='正常' then 1  when CHOCCUR='异常' then 0  end as zhyl12000035,\n" +
            "CHDESC as zhyl12000036\n" +
            "from dbo.Chromosome where DELMARK=0 and dmid='?' ";

}

