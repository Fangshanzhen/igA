package com.igA.demo.constant;

public class PediatricKidneyDatabaseConstant {

    //id
    public final static String KidneyIdSql2 = "select a.id  from dbo.DM a  where a.Del_Mark=0 and a.DataTypeId='2' ";
//一般资料
    public final static String yibanziliao2 = "select a.id , SUBNAM as zhyl1100001,SUBJMRID as zhyl1100002,case when DMSUBRE='门诊' then '1' when DMSUBRE='住院' then '2' end as zhyl1100003,\n" +
            "case when SEX='男' then '1' when SEX='女' then '2' end as zhyl1100004, d.a1 as zhyl1100005,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BRIHDTC) as bigint )*1000 AS zhyl1100006, AGEY as zhyl1100023,\n" +
            "c.a  as zhyl1110023, SUBADRRE as zhyl1110024,SUBZIPCD as zhyl1110026,SUBHTEL as zhyl1110028,SUBMTEL as zhyl1110029,\n" +
            "SUBMTEL2 as zhyl1110030, case when a.PACOMAT ='否' then 0  when a.PACOMAT ='是' then 1 end as  zhyl1200031, null as zhyl1200030,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', DMVISDTC) as bigint )*1000 AS  zhyl1100031,\n" +
            "REGHOID as zhyl1100032,INVNAM as zhyl1100033,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', DMDTC) as bigint )*1000 AS  zhyl1100034,\n" +
            "VERNAM as zhyl1100007,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', VERDTC) as bigint )*1000 AS  zhyl1100008,\n" +
            "case when a.DMSIIC ='否' then 0  when a.DMSIIC ='是' then 1 end as zhyl1100009\n" +
            "\n" +
            "from dbo.DM a left join dbo.DICTABLEINFO b on a.SUBPROVINCE=b.[Path] \n" +
            "left join dbo.area c on b.CODE=c.c  \n" +
            "left join dbo.nation d on a.ETHNIC=d.b1  where a.id='?'";

//现病史
    public final static String xianbingshi2 ="select * from dbo.mh2 where id='?'";
//个人史
    public final static String gerenshi2 ="select * from dbo.gerenshi  where id='?'";
//  家族史
    public final static String jiazhushi2 ="select * from dbo.fh1 where FHSUBREL='#' and  DMID='?'  ";
//体格检查
    public final static String tigejiancha2 ="select a.ID,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.VSDTC) as bigint )*1000 AS  zhyl5000001,\n" +
            "b.HEIGHT as zhyl5000007,WEIGHT as zhyl5000020,b.BSA as zhyl5000021,\n" +
            "b.SBP as zhyl5100022,b.DBP as zhyl5100023\n" +
            "from dbo.DM a left join dbo.VS b on a.ID =b.DMID \n" +
            "\n" +
            "where b.[SOURCE]='体格检查' and b.DELMARK=0  and  b.DMID='?'  ";
//尿常规
    public final static String niaochanggui2 ="SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.URDTC ) as bigint )*1000 AS zhyl6100000,\n" +
            "b.URHOSP as zhyl6100001,\n" +
            "case when b.UPL='-' then '0' when b.UPL='+-' then '1' when b.UPL='+' then '2' when b.UPL='++' then '3'  \n" +
            "when b.UPL='+++' then '4'when b.UPL='+++++' then '5' end as zhyl6100002, b.UPN as zhyl6100003,\n" +
            "case when b.RBCAN='是' then '0' when b.RBCAN='否' then '1'  end as zhyl6200000,b.RBCDSC as zhyl6200001,\n" +
            "case when b.RBCFORM='肾小球性' then '0' when b.RBCFORM='非肾小球性' then '1'  end as zhyl6200002,\n" +
            "b.RBCFOSC as zhyl6200003\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_UR b on a.ID =b.DMID  where  b.[SOURCE] is null and b.DELMARK=0  and  b.DMID='?'  ";
//尿蛋白肌酐比
    public final static String niaodanbaijigan2 =" SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.PCDTC ) as bigint )*1000 AS zhyl6100006,\n" +
            "b.PCHOSP as zhyl6100007,\n" +
            "b.PCNDB as zhyl6100008,\n" +
            "b.PCNJG as zhyl6100009,\n" +
            "b.PCORRES as zhyl6100010\n" +
            "\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_PC b on a.ID =b.DMID \n" +
            "where  b.DELMARK=0 and b.[SOURCE] is null and  b.DMID='?' ";
//24h尿蛋白定量
    public final static String niaodanbaidingliang24h2 =" SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.UPDTC ) as bigint )*1000 AS zhyl6100011,\n" +
            "b.UPHOSP as zhyl6100012,\n" +
            "b.UPDL as zhyl6100013,\n" +
            "b.UPWEIGHT as zhyl6100014,\n" +
            "b.UPUWP as zhyl6100015\n" +
            "\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_UP b on a.ID =b.DMID \n" +
            "where  b.DELMARK=0 and b.[SOURCE] is null  and  b.DMID='?' ";
//24h肌酐清除率
    public final static String jiganqingchu24h2 ="select *,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', HEDTC ) as bigint )*1000 AS zhyl6100016 from dbo.ehe1 where DMID='?'  and HETYPE='24小时肌酐清除率' AND [SOURCE] is null and  DELMARK=0 ";
//肾早损
    public final static String shenzaosuai2 =" select   \n" +
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
            "IROTHER as zhyl6100030\n" +
            "\n" +
            "from dbo.E_IR  where  DELMARK=0 and [SOURCE] is null and DMID='?' ";

//尿蛋白电泳
    public final static String niaodanbaidianyong2 =" select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', UEDTC ) as bigint )*1000 AS zhyl6100116,\n" +
            "UEHOSP as zhyl6100117,\n" +
            "SPPORG  as zhyl6100118,\n" +
            "ALBPORG as zhyl6100119,\n" +
            "BPPORG as zhyl6100120\n" +
            "\n" +
            "from dbo.E_UE  where  DELMARK=0  and DMID='?'  "; //and [SOURCE] is null

//血生化
    public final static String xueshenghua2 =" select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BBDTC ) as bigint )*1000 AS zhyl6100122,\n" +
            "BBHOSP as zhyl6100123,\n" +
            "ALB as zhyl6100124,\n" +
            "SCR  as zhyl6100125,\n" +
            "BUN as zhyl6100126,\n" +
            "TCHO as zhyl6100127,\n" +
            "ALT as zhyl6100128,\n" +
            "AST as zhyl6100129,\n" +
            "K as zhyl6100130,\n" +
            "P as zhyl6100131\n" +
            "\n" +
            "from dbo.E_BB  where  DELMARK=0  and DMID='?' ";//and [SOURCE] is null

//免疫球蛋白
    public final static String mianyiqiudanbai2 =" select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', IGDTC ) as bigint )*1000 AS zhyl6100132,\n" +
            "IGHOSP as zhyl6100133,\n" +
            "IGG as zhyl6100134,\n" +
            "IGA as zhyl6100135,\n" +
            "IGM as zhyl6100136\n" +
            "\n" +
            "from dbo.E_IG  where  DELMARK=0   and DMID='?' ";  // and [SOURCE] is null
//血补体
    public final static String xuebuti2 ="  select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BCDTC ) as bigint )*1000 AS zhyl6100137,\n" +
            "BCHOSP as zhyl6100138,\n" +
            "C3 as zhyl6100139,\n" +
            "C4 as zhyl6100140\n" +
            "\n" +
            "\n" +
            "from dbo.E_BC  where  DELMARK=0 and DMID='?'  ";  // and [SOURCE] is null
//感染筛查
    public final static String ganranshuaicha2 ="  select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', ISDTC ) as bigint )*1000 AS zhyl6100141,\n" +
            "ISHOSP as zhyl6100142,\n" +
            "case when ISORRE='阴性' then '0'  when ISORRE='阳性' then '1' end   as zhyl6100143,\n" +
            "ISDESC as zhyl6100144\n" +
            "\n" +
            "from dbo.E_IS  where  DELMARK=0  and DMID='?'  ";// and [SOURCE] is null

//TORCH
    public final static String TORCH2 ="  select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', TODTC ) as bigint )*1000 AS zhyl6100031,\n" +
            "TOHOSP as zhyl6100032,\n" +
            "case when TOORRE='阴性' then '0'  when TOORRE='阳性' then '1' end   as zhyl6100033,\n" +
            "TODESC as zhyl6100034\n" +
            "\n" +
            "from dbo.E_TO  where  DELMARK=0  and DMID='?'   ";
//超声心动
    public final static String chaoshenxindong2 =" select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', EGDTC ) as bigint )*1000 AS zhyl6100035,\n" +
            "EGHOSP as zhyl6100036,\n" +
            "case when EGORRE='异常' then '0'  when EGORRE='正常' then '1' end   as zhyl6100037,\n" +
            "case when CAST(EGDESC AS varchar(max))='室缺' then '0' when CAST(EGDESC AS varchar(max))='房缺' then '1' when CAST(EGDESC AS varchar(max))='左心室肥大' then '2' when CAST(EGDESC AS varchar(max))='肺动脉瓣狭窄' then '3' when CAST(EGDESC AS varchar(max))='分散性主动脉瓣下狭窄' then '4' \n" +
            "when CAST(EGDESC AS varchar(max))='三尖瓣下移' then '5' when CAST(EGDESC AS varchar(max))='其他' then '6'   end  as zhyl6100038,\n" +
            "null as zhyl6100039\n" +
            "\n" +
            "from dbo.E_EG  where  DELMARK=0  and DMID='?' ";
//腹部B超
    public final static String fububichao2 =" select a.DMID,\n" +
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
            "null as zhyl6100054\n" +
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
            ") s where num=1) b on a.DMID=b.DMID where a.DMID='?' ";
//免疫荧光-光镜
    public final static String guangjing2 ="  select \n" +
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
            "when GuangJingZhenDuan='系膜增生肾小球肾炎' then '4' \n" +
            "when GuangJingZhenDuan='其他' then '5' end as zhyl6200020,\n" +
            "GuangJingZhenDuanQiTa as zhyl6200021,\n" +
            "GuangJingZhenDuanJuTiMiaoShu as zhyl6200022\n" +
            "\n" +
            "from dbo.GuangJingTemp  where DMID='?' ";

//电镜
    public final static String dianjing2 =" select \n" +
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
    public final static String chunyinceting2 ="  select \n" +
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
    public final static String yanliedeng2 =" select dmid,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', ESDTC ) as bigint )*1000 AS zhyl6100076,\n" +
            "ESHOSP as zhyl6100077,\n" +
            "case when ESOCCUR='有' then '1' when ESOCCUR='无' then '0' end as zhyl6100078,\n" +
            "case when ESORRE='正常' then '1'  when ESORRE='异常' then '0'  end  as zhyl6100079,\n" +
            "ESDESC as zhyl6100080,\n" +
            "null as zhyl6100081\n" +
            "\n" +
            "from  dbo.E_ES where  DELMARK=0  and DMID='?'  "; //and [SOURCE] is null

//其他检查
    public final static String qitajiancha2 =" select dmid,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', OTDTC ) as bigint )*1000 AS zhyl6100082,\n" +
            "OTHOSP as zhyl6100083,\n" +
            "OTDESC as zhyl6100084,\n" +
            "null as zhyl6100085\n" +
            "\n" +
            "from  dbo.E_OT where  DELMARK=0   and DMID='?'   ";

//    基因检测
    public final static String jiyinjiance2 =" select \n" +
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
            "case when FATHER='是' then '1' when FATHER='否' then '2'  end as zhyl6200029,\n" +
            "FADSC as zhyl6200030,\n" +
            "case when MOTHER='是' then '1' when MOTHER='否' then '2'  end as zhyl6200031,\n" +
            "MODSC as zhyl6200032,\n" +
            "case when [OTHERS]='是' then '1' when [OTHERS]='否' then '2'  end as zhyl6200033,\n" +
            "OTHRELAT as zhyl6200034,\n" +
            "OTHDSC as zhyl6200035\n" +
            "\n" +
            "\n" +
            "\n" +
            "from dbo.GT where  DELMARK=0 and DMID='?' ";

//最终诊断

    public final static String zuizhongzhenduan2 ="SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', DIDTC ) as bigint )*1000 AS zhyl6100098,\n" +
            "case when DITYPE='拟诊' then '1'  when DITYPE='确诊' then '2' end as  zhyl6100099,\n" +
            "DICONTENT\n" +
            "\n" +
            "from dbo.DI where  DELMARK=0  and [SOURCE] is null  and DMID='?' " ;


}

