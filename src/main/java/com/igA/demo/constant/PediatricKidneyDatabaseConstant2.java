package com.igA.demo.constant;

//早发蛋白尿

public class PediatricKidneyDatabaseConstant2 {

    //id
    public final static String KidneyIdSql2 = "select a.id  from dbo.DM a  where a.Del_Mark=0 and a.DataTypeId='#' ";
    //一般资料
    public final static String yibanziliao2 = "select a.id , SUBNAM as zhyl1100001,SUBJMRID as zhyl1100002,case when DMSUBRE='门诊' then '1' when DMSUBRE='住院' then '2' end as zhyl1100003,\n" +
            "case when SEX='男' then '1' when SEX='女' then '2' end as zhyl1100004, d.b1 as zhyl1100005,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BRIHDTC) as bigint )*1000 AS zhyl1100006, AGEY as zhyl1100023,\n" +
            "c.a  as zhyl1110023, SUBADRRE as zhyl1110024,SUBZIPCD as zhyl1100026,SUBHTEL as zhyl1110028,SUBMTEL as zhyl1110029,\n" +
            "SUBMTEL2 as zhyl1110030, case when a.PACOMAT ='否' then 0  when a.PACOMAT ='是' then 1 end as  zhyl1200031, null as zhyl1200030,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', DMVISDTC) as bigint )*1000 AS  zhyl1100031,\n" +
            "REGHOID as zhyl1100032,INVNAM as zhyl1100033,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', DMDTC) as bigint )*1000 AS  zhyl1100034,\n" +
            "VERNAM as zhyl1100007,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', VERDTC) as bigint )*1000 AS  zhyl1100008,\n" +
            "case when a.DMSIIC ='否' then 0  when a.DMSIIC ='是' then 1 end as zhyl1100009\n" +
            "\n" +
            "from dbo.DM a left join dbo.DICTABLEINFO b on a.SUBPROVINCE=b.[Path] \n" +
            "left join dbo.area c on b.CODE=c.c  \n" +
            "left join dbo.nation d on a.ETHNIC=d.a1  where a.id='?'";

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
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.URDTC ) as bigint )*1000 AS zhyl6100000,\n" +
            "b.URHOSP as zhyl6100001,\n" +
            "case when b.UPL='-' then '0' when b.UPL='+-' then '1' when b.UPL='+' then '2' when b.UPL='++' then '3'  \n" +
            "when b.UPL='+++' then '4'when b.UPL='+++++' then '5' end as zhyl6100002, b.UPN as zhyl6100003,\n" +
            "case when b.RBCAN='是' then '1' when b.RBCAN='否' then '0'  end as zhyl6200100,b.RBCDSC as zhyl6200101,\n" +
            "case when b.RBCFORM='肾小球性' then '0' when b.RBCFORM='非肾小球性' then '1'  end as zhyl6200102,\n" +
            "b.RBCFOSC as zhyl6200103,\n" +
            "case when  b.[SOURCE]='随访'  then '1' else '0' end as zhyl6100101\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_UR b on a.ID =b.DMID \n" +
            "where  b.DELMARK=0  and  b.DMID='?'  ";

    //24小时尿钙体重比
    public final static String niaogaitizhong2 = " select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', UCDTC  ) as bigint )*1000 as  zhyl61000061,\n" +
            "UCHOSP as zhyl61000062,\n" +
            "UCDL as zhyl61000063,\n" +
            "UCWEIGHT as zhyl61000064,\n" +
            "UCUWP as zhyl61000065\n" +
            "from dbo.E_UC where DELMARK=0 and  DMID='?'";

    //尿蛋白肌酐比
    public final static String niaodanbaijigan2 = " SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.PCDTC ) as bigint )*1000 AS zhyl6100006,\n" +
            "b.PCHOSP as zhyl6100007,\n" +
            "b.PCNDB as zhyl6100008,\n" +
            "b.PCNJG as zhyl6100009,\n" +
            "b.PCORRES as zhyl6100010,\n" +
            "case when  b.[SOURCE]='随访'  then '1' else '0' end as zhyl6100102\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_PC b on a.ID =b.DMID \n" +
            "where  b.DELMARK=0 and  b.PCTYPE ='尿蛋白/肌酐比'  and   b.DMID='?' ";
    //24h尿蛋白定量
    public final static String niaodanbaidingliang24h2 = " SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.UPDTC ) as bigint )*1000 AS zhyl6100011,\n" +
            "b.UPHOSP as zhyl6100012,\n" +
            "b.UPDL as zhyl6100013,\n" +
            "b.UPWEIGHT as zhyl6100014,\n" +
            "b.UPUWP as zhyl6100015,\n" +
            "case when  b.[SOURCE]='随访'  then '1' else '0' end as zhyl6100103\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_UP b on a.ID =b.DMID  \n" +
            "where  b.DELMARK=0  and  b.DMID='?' ";
    //24h肌酐清除率
    public final static String jiganqingchu24h2 = "select *,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', HEDTC ) as bigint )*1000 AS zhyl6100016,\n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl6100104\n" +
            "from dbo.ehe1 \n" +
            "where   HETYPE='24小时肌酐清除率'   and  DELMARK=0 and  DMID='?'  ";
    //肾早损
    public final static String shenzaosuai2 = " select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', IRDTC ) as bigint )*1000 AS zhyl6100023,\n" +
            "IRHOSP as zhyl6100024,\n" +
            "MALB as zhyl6100106,\n" +
            "case when MALBORRE='正常' then '1'   when MALBORRE='异常' then '0'   when MALBORRE='不详' then '2'  end  as zhyl6100107,\n" +
            "TRU as zhyl6100108,\n" +
            "case when TRUORRE='正常' then '1'   when TRUORRE='异常' then '0'   when TRUORRE='不详' then '2'  end  as zhyl6100109,\n" +
            "NAG as zhyl6100110,\n" +
            "case when NAGORRE='正常' then '1'   when NAGORRE='异常' then '0'   when NAGORRE='不详' then '2'  end  as zhyl6100111,\n" +
            "A1MG as zhyl6100112,\n" +
            "case when A1MGORRE='正常' then '1'   when A1MGORRE='异常' then '0'   when A1MGORRE='不详' then '2'  end  as zhyl6100113,\n" +
            "MALBJG as zhyl6100114,\n" +
            "case when MALBJGORRE='正常' then '1'   when MALBJGORRE='异常' then '0'   when MALBJGORRE='不详' then '2'  end  as zhyl6100115,\n" +
            "IROTHER as zhyl6100030,\n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl6100105\n" +
            "\n" +
            "from dbo.E_IR  where  DELMARK=0 and DMID='?' ";

    //尿蛋白电泳
    public final static String niaodanbaidianyong2 = " select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', UEDTC ) as bigint )*1000 AS zhyl6100116,\n" +
            "UEHOSP as zhyl6100117,\n" +
            "SPPORG  as zhyl6100118,\n" +
            "ALBPORG as zhyl6100119,\n" +
            "BPPORG as zhyl6100120\n" +
            "\n" +
            "from dbo.E_UE  where  DELMARK=0  and DMID='?'  "; //and [SOURCE] is null

    //血生化
    public final static String xueshenghua2 = " select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BBDTC ) as bigint )*1000 AS zhyl6100122,\n" +
            "BBHOSP as zhyl6100123,\n" +
            "ALB as zhyl6100124,\n" +
            "SCR  as zhyl6100125,\n" +
            "BUN as zhyl6100126,\n" +
            "TCHO as zhyl6100127,\n" +
            "ALT as zhyl6100128,\n" +
            "AST as zhyl6100129,\n" +
            "K as zhyl6100130,\n" +
            "P as zhyl6100131,\n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl6100121\n" +
            "\n" +
            "from dbo.E_BB  where  DELMARK=0   and DMID='?' ";//and [SOURCE] is null

    //免疫球蛋白
    public final static String mianyiqiudanbai2 = " select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', IGDTC ) as bigint )*1000 AS zhyl6100132,\n" +
            "IGHOSP as zhyl6100133,\n" +
            "IGG as zhyl6100134,\n" +
            "IGA as zhyl6100135,\n" +
            "IGM as zhyl6100136\n" +
            "\n" +
            "from dbo.E_IG  where  DELMARK=0   and DMID='?' ";  // and [SOURCE] is null
    //血补体
    public final static String xuebuti2 = "  select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BCDTC ) as bigint )*1000 AS zhyl6100137,\n" +
            "BCHOSP as zhyl6100138,\n" +
            "C3 as zhyl6100139,\n" +
            "C4 as zhyl6100140\n" +
            "\n" +
            "\n" +
            "from dbo.E_BC  where  DELMARK=0 and DMID='?'  ";  // and [SOURCE] is null
    //感染筛查
    public final static String ganranshuaicha2 = "  select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', ISDTC ) as bigint )*1000 AS zhyl6100141,\n" +
            "ISHOSP as zhyl6100142,\n" +
            "case when ISORRE='阴性' then '0'  when ISORRE='阳性' then '1' end   as zhyl6100143,\n" +
            "ISDESC as zhyl6100144\n" +
            "\n" +
            "from dbo.E_IS  where  DELMARK=0  and DMID='?'  ";// and [SOURCE] is null

    //TORCH
    public final static String TORCH2 = "  select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', TODTC ) as bigint )*1000 AS zhyl6100031,\n" +
            "TOHOSP as zhyl6100032,\n" +
            "case when TOORRE='阴性' then '0'  when TOORRE='阳性' then '1' end   as zhyl6100033,\n" +
            "TODESC as zhyl6100034\n" +
            "\n" +
            "from dbo.E_TO  where  DELMARK=0  and DMID='?'   ";
    //超声心动
    public final static String chaoshenxindong2 = " select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', EGDTC ) as bigint )*1000 AS zhyl6100035,\n" +
            "EGHOSP as zhyl6100036,\n" +
            "case when EGORRE='异常' then '0'  when EGORRE='正常' then '1' end   as zhyl6100037,\n" +
            "case when CAST(EGDESC AS varchar(max))='室缺' then '0' when CAST(EGDESC AS varchar(max))='房缺' then '1' when CAST(EGDESC AS varchar(max))='左心室肥大' then '2' when CAST(EGDESC AS varchar(max))='肺动脉瓣狭窄' then '3' when CAST(EGDESC AS varchar(max))='分散性主动脉瓣下狭窄' then '4' \n" +
            "when CAST(EGDESC AS varchar(max))='三尖瓣下移' then '5' when CAST(EGDESC AS varchar(max))='其他' then '6'   end  as zhyl6100038,\n" +
            "null as zhyl6100039\n" +
            "\n" +
            "from dbo.E_EG  where  DELMARK=0  and DMID='?' ";
    //腹部B超
    public final static String fububichao2 = " select * from (\n" +
            "select a.DMID,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', a.BUDTC ) as bigint )*1000 AS zhyl6100040,\n" +
            "a.BUHOSP as zhyl6100041,\n" +
            "case when a.BUORRE='正常' then '1'  when a.BUORRE='异常' then '0'  end as zhyl6100042,\n" +
            "case when a.BUSIZE='正常' then '1' when a.BUSIZE='增大' then '0' when a.BUSIZE='缩小' then '2' end as zhyl6100043,\n" +
            "a.BUDESC as zhyl6100044,\n" +
            "case when b.BUORRE='正常' then '1'  when b.BUORRE='异常' then '0'  end as zhyl6100045,\n" +
            "b.LSL as zhyl6100046,\n" +
            "b.LSW as zhyl6100047,\n" +
            "b.LSH as zhyl6100048,\n" +
            "b.RSL as zhyl6100049,\n" +
            "b.RSW as zhyl6100050,\n" +
            "b.RSH as zhyl6100051,\n" +
            "b.BUECHO as zhyl6100052,\n" +
            "b.BUDESC as zhyl6100053,\n" +
            "null as zhyl6100054,\n" +
            "'0' as zhyl6100145\n" +
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
            ") s where num=1) b on a.DMID=b.DMID\n" +
            "\n" +
            "\n" +
            "UNION \n" +
            "\n" +
            "select a.DMID,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', a.BUDTC ) as bigint )*1000 AS zhyl6100040,\n" +
            "a.BUHOSP as zhyl6100041,\n" +
            "case when a.BUORRE='正常' then '1'  when a.BUORRE='异常' then '0'  end as zhyl6100042,\n" +
            "case when a.BUSIZE='正常' then '1' when a.BUSIZE='增大' then '0' when a.BUSIZE='缩小' then '2' end as zhyl6100043,\n" +
            "a.BUDESC as zhyl6100044,\n" +
            "case when b.BUORRE='正常' then '1'  when b.BUORRE='异常' then '0'  end as zhyl6100045,\n" +
            "b.LSL as zhyl6100046,\n" +
            "b.LSW as zhyl6100047,\n" +
            "b.LSH as zhyl6100048,\n" +
            "b.RSL as zhyl6100049,\n" +
            "b.RSW as zhyl6100050,\n" +
            "b.RSH as zhyl6100051,\n" +
            "case when b.BUECHO='正常'  then '1'   when b.BUECHO='增强'  then '0'   end   as zhyl6100052,\n" +
            "b.BUDESC as zhyl6100053,\n" +
            "null as zhyl6100054,\n" +
            "'1' as zhyl6100145\n" +
            "\n" +
            "\n" +
            "from  (select * from  (\n" +
            "select  * ,ROW_NUMBER() OVER (PARTITION BY DMID,BUDTC,BUCAT ORDER BY BUDTC desc) AS num\n" +
            "from  dbo.E_BU where BUCAT='肝脏'  and DELMARK=0 and [SOURCE] ='随访'\n" +
            ") s where num=1) a \n" +
            "left join \n" +
            "(select * from  (\n" +
            "select  * ,ROW_NUMBER() OVER (PARTITION BY DMID,BUDTC,BUCAT ORDER BY BUDTC desc) AS num\n" +
            "from  dbo.E_BU where BUCAT='肾脏'  and DELMARK=0 and [SOURCE] ='随访'\n" +
            ") s where num=1) b on a.DMID=b.DMID and a.BUDTC=b.BUDTC " +
            ") v  where DMID='?' ";
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
            "\n" +
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
            " HGB as zhyl60000213\n" +
            "from dbo.E_BR where  DELMARK=0  and DMID='?' ";

    //尿钙/肌酐比
    public final static String niaogaijigan2 = " SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.PCDTC ) as bigint )*1000 AS zhyl60000221,\n" +
            "b.PCHOSP as zhyl60000222,\n" +
            "b.PCORRES as zhyl60000223\n" +
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
            "case when KIMETHOD='泌尿系超声' then 1 when KIMETHOD='CT' then 2 when KIMETHOD='MRI' then 3 end as zhyl60000244,\n" +
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
            "case when b.Column1 like '101%' then '101000'\n" +
            "when b.Column1 like '102%' then '102000' \n" +
            "when b.Column1 like '201%' then '201000' \n" +
            "when b.Column1 like '301%' then '301000' \n" +
            "when b.Column1 like '302%' then '302000' \n" +
            "when b.Column1 like '401%' then '401000' \n" +
            "when b.Column1 like '501%' then '501000' \n" +
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
    //体格检查
    public final static String suifangtigejiancha2 = "select b.DMID,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.VSDTC) as bigint )*1000 AS  zhyl8100000,\n" +
            "b.HEIGHT as zhyl8100001,WEIGHT as zhyl8100002,\n" +
            "b.SBP as zhyl8200001,b.DBP as zhyl8200002\n" +
            "\n" +
            "from  dbo.VS b \n" +
            "where b.DELMARK=0 and [SOURCE]='随访' and SOURCEID ='?'  ";
    //血生化
    public final static String suifangxueshenghua2 = " select   \n" +
            "\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BBDTC ) as bigint )*1000 AS zhyl8100004,\n" +
            "SCR as zhyl8100005,\n" +
            "BUN as zhyl8100006,\n" +
            "ALT as zhyl80000031,\n" +
            "AST as zhyl80000032,\n" +
            "CA as zhyl80000033,\n" +
            "P as zhyl80000034,\n" +
            "K as zhyl80000035,\n" +
            "HCO3 as zhyl8100036,\n" +
            "ALP as zhyl8100037,\n" +
            "GGT as zhyl8100038,\n" +
            "TBA as zhyl8100039\n" +
            "\n" +
            "from dbo.E_BB  where  DELMARK=0  and  [SOURCE]='随访' and SOURCEID ='?'  ";
    //尿常规
    public final static String suifangniaochanggui2 = "  SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.URDTC ) as bigint )*1000 AS zhyl8100007,\n" +
            "case when b.UPL='-' then '0' when b.UPL='+-' then '1' when b.UPL='+' then '2' when b.UPL='++' then '3'  \n" +
            "when b.UPL='+++' then '4'when b.UPL='+++++' then '5' end as zhyl8100008, b.UPN as zhyl8100009,\n" +
            "case when b.RBCAN='是' then '1' when b.RBCAN='否' then '0'  end as zhyl8100010,b.RBCDSC as zhyl8100011\n" +
            "\n" +
            "\n" +
            "from  dbo.E_UR b \n" +
            "where  b.DELMARK=0  and  [SOURCE]='随访'  and SOURCEID ='?'  ";
    //尿蛋白/肌酐
    public final static String suifangniaodanbaijigan2 = " SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.PCDTC ) as bigint )*1000 AS zhyl8100012,\n" +
            "b.PCORRES as zhyl8100013\n" +
            "from dbo.E_PC b  \n" +
            "where  b.DELMARK=0  and PCTYPE ='尿蛋白/肌酐比' and  [SOURCE]='随访'   and SOURCEID ='?' ";
    //24小时尿蛋白定量
    public final static String suifang24niaodanbai2 = "  SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.UPDTC ) as bigint )*1000 AS zhyl8100014,\n" +
            "b.UPDL as zhyl8100015,\n" +
            "b.UPWEIGHT as zhyl8100016,\n" +
            "b.UPUWP as zhyl8100017\n" +
            "from dbo.E_UP b  \n" +
            "where  b.DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?'  ";
    //24小时肌酐清除率
    public final static String suifang24jiganqingchu2 = " select *,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', HEDTC ) as bigint )*1000 AS zhyl8100018\n" +
            "from dbo.ehe1 \n" +
            "where   HETYPE='24小时肌酐清除率'   and  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?'  ";
    //肾早衰
    public final static String suifangshenzaoshuai2 = " select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', IRDTC ) as bigint )*1000 AS zhyl8100024,\n" +
            "MALBJG as zhyl8100025,\n" +
            "case when MALBJGORRE='正常' then '1'   when MALBJGORRE='异常' then '0'   when MALBJGORRE='不详' then '2'  end  as zhyl8100026\n" +
            "from dbo.E_IR  where  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?'  ";
    //泌尿系超声
    public final static String suifangchaoshen2 = " select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.BUDTC ) as bigint )*1000 AS zhyl8100027,\n" +
            "case when b.BUORRE='正常' then '1'  when b.BUORRE='异常' then '0'  end as zhyl8100028,\n" +
            "b.LSL as zhyl8100029,\n" +
            "b.LSW as zhyl8100030,\n" +
            "b.LSH as zhyl8100031,\n" +
            "b.RSL as zhyl8100032,\n" +
            "b.RSW as zhyl8100033,\n" +
            "b.RSH as zhyl8100034,\n" +
            "case when b.BUECHO='正常'  then '1'   when b.BUECHO='增强'  then '0'   end  as zhyl8100035,\n" +
            "b.BUDESC as zhyl8100036\n" +
            "\n" +
            "from  \n" +
            "(select * from  (\n" +
            "select  * ,ROW_NUMBER() OVER (PARTITION BY DMID,BUDTC,BUCAT ORDER BY BUDTC desc) AS num\n" +
            "from  dbo.E_BU where BUCAT='肾脏'  and DELMARK=0 and [SOURCE] ='随访'\n" +
            ") s where num=1) b   where  SOURCEID ='?'  ";

    //肾脏影像学检查
    public final static String suifangchaoshen4 = "SELECT \n" +
            "\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', KIDTC ) as bigint )*1000 AS zhyl8100027,\n" +
            "LSL as zhyl8100029,\n" +
            "LSW as zhyl8100030,\n" +
            "LSH as zhyl8100031,\n" +
            "RSL as zhyl8100032,\n" +
            "RSW as zhyl8100033,\n" +
            "RSH as zhyl8100034,\n" +
            "case when KIMETHOD='泌尿系超声' then 1 when KIMETHOD='CT' then 2 when KIMETHOD='MRI' then 3 end as zhyl81000341,\n" +
            "case when KIECHO='正常' then '1'  when KIECHO='增强' then '0'  end as zhyl8100035,\n" +
            "KIDESC as zhyl8100036\n" +
            "\n" +
            "from dbo.E_KI \n" +
            "where  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?' ";

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
            "case when CMCAT='降压药' then '100000' when CMCAT='利尿剂' then '200000' when CMCAT='免疫抑制药物' then '300000' when CMCAT='调节电解质、酸碱平衡药' then '400000' when CMCAT='维生素、微量元素药物' then '500000'   end as zhyl8100038,\n" +
            "case when b.Column1 like '101%' then '101000'\n" +
            "when b.Column1 like '102%' then '102000' \n" +
            "when b.Column1 like '201%' then '201000' \n" +
            "when b.Column1 like '301%' then '301000' \n" +
            "when b.Column1 like '302%' then '302000' \n" +
            "when b.Column1 like '401%' then '401000' \n" +
            "when b.Column1 like '501%' then '501000' \n" +
            "end  as zhyl8100039,\n" +
            "b.Column1 as zhyl8100040," +
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
            "from dbo.CM  a  left join dbo.drug b on a.CMTRT=b.Column2 where a. DELMARK=0 and  a.[SOURCE]='随访'   and a.SOURCEID ='?' ";
}

