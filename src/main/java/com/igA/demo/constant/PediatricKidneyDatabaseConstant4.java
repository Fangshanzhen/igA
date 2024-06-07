package com.igA.demo.constant;

//肾脏囊性疾病

public class PediatricKidneyDatabaseConstant4 {
    //id
    public final static String KidneyIdSql4 = "select a.id  from dbo.DM a  where a.Del_Mark=0 and a.DataTypeId='4' ";
    //一般资料
    public final static String yibanziliao4 = "select a.id , SUBNAM as zhyl1100001,SUBJMRID as zhyl1100002,case when DMSUBRE='门诊' then '1' when DMSUBRE='住院' then '2' end as zhyl1100003,\n" +
            "case when SEX='男' then '1' when SEX='女' then '2' end as zhyl1100004, d.a1 as zhyl1100005,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BRIHDTC) as bigint )*1000 AS zhyl1100006, AGEY as zhyl1100023,\n" +
            "c.a  as zhyl1110023, SUBADRRE as zhyl1110024,SUBZIPCD as zhyl1110026,SUBHTEL as zhyl1110028,SUBMTEL as zhyl1110029,\n" +
            "SUBMTEL2 as zhyl1110030, case when a.PACOMAT ='否' then 0  when a.PACOMAT ='是' then 1 end as  zhyl1200031, \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', DMVISDTC) as bigint )*1000 AS  zhyl1100031,\n" +
            "REGHOID as zhyl1100032,INVNAM as zhyl1100033,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', DMDTC) as bigint )*1000 AS  zhyl1100034,\n" +
            "VERNAM as zhyl1100007,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', VERDTC) as bigint )*1000 AS  zhyl1100008,\n" +
            "case when a.DMSIIC ='否' then 0  when a.DMSIIC ='是' then 1 end as zhyl1100009\n" +
            "\n" +
            "from dbo.DM a left join dbo.DICTABLEINFO b on a.SUBPROVINCE=b.[Path] \n" +
            "left join dbo.area c on b.CODE=c.c  \n" +
            "left join dbo.nation d on a.ETHNIC=d.b1  where a.id='?'";


    //现病史
    public final static String xianbingshi4 ="select * from dbo.mh2 where flag='4' and id='?'";
    //个人史
    public final static String gerenshi4 ="select * from dbo.gerenshi4  where id='?'";

    //体格检查
    public final static String tigejiancha4 ="select * from dbo.tige  where id='?'";


    //尿常规
    public final static String niaochanggui4 ="SELECT \n" +
            "\n" +
            "case when  b.[SOURCE]='随访'  then '1' else '0' end as zhyl6100001,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.URDTC ) as bigint )*1000 AS zhyl6100002,\n" +
            "b.URHOSP as zhyl6100003,\n" +
            "case when b.UPL='-' then '0' when b.UPL='+-' then '1' when b.UPL='+' then '2' when b.UPL='++' then '3'  \n" +
            "when b.UPL='+++' then '4'when b.UPL='+++++' then '5' end as zhyl6100004,\n" +
            "b.UPN as zhyl6100005,\n" +
            "UPH as zhyl6100006,\n" +
            "SG as zhyl6100007,\n" +
            "RBC as zhyl6100008,\n" +
            "RBCDSC as zhyl6100009,\n" +
            "GLU as zhyl6100010\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_UR b on a.ID =b.DMID \n" +
            "where  b.DELMARK=0  and  b.DMID='?'  ";

    //尿蛋白肌酐比
    public final static String niaodanbaijigan4 =" SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.PCDTC ) as bigint )*1000 AS zhyl6100012,\n" +
            "b.PCHOSP as zhyl6100013,\n" +
            "b.PCORRES as zhyl6100014,\n" +
            "\n" +
            "case when  b.[SOURCE]='随访'  then '1' else '0' end as zhyl6100011\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_PC b on a.ID =b.DMID \n" +
            "where  b.DELMARK=0 and b.PCTYPE='尿蛋白/肌酐比' and   b.DMID='?' ";

    //24h尿蛋白定量
    public final static String niaodanbaidingliang24h4 =" SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.UPDTC ) as bigint )*1000 AS zhyl6100016,\n" +
            "b.UPHOSP as zhyl6100017,\n" +
            "b.UPDL as zhyl6100018,\n" +
            "b.UPWEIGHT as zhyl6100019,\n" +
            "b.UPUWP as zhyl6100020,\n" +
            "case when  b.[SOURCE]='随访'  then '1' else '0' end as zhyl6100015\n" +
            "\n" +
            "from dbo.DM a left join dbo.E_UP b on a.ID =b.DMID  \n" +
            "where  b.DELMARK=0  and  b.DMID='?' ";

    //24h肌酐清除率
    public final static String jiganqingchu24h4 ="select *,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', HEDTC ) as bigint )*1000 AS zhyl6100022,\n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl6100021\n" +
            "from dbo.ehe1 \n" +
            "where   HETYPE='24小时肌酐清除率' and  DELMARK=0 and  DMID='?'  ";


    //肾早损
    public final static String shenzaosuai4 =" select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', IRDTC ) as bigint )*1000 AS zhyl6100030,\n" +
            "IRHOSP as zhyl6100031,\n" +
            "MALB as zhyl6100032,\n" +
            "case when MALBORRE='正常' then '1'   when MALBORRE='异常' then '0'   when MALBORRE='不详' then '2'  end  as zhyl6100033,\n" +
            "TRU as zhyl6100034,\n" +
            "case when TRUORRE='正常' then '1'   when TRUORRE='异常' then '0'   when TRUORRE='不详' then '2'  end  as zhyl6100035,\n" +
            "NAG as zhyl6100036,\n" +
            "case when NAGORRE='正常' then '1'   when NAGORRE='异常' then '0'   when NAGORRE='不详' then '2'  end  as zhyl6100037,\n" +
            "A1MG as zhyl6100038,\n" +
            "case when A1MGORRE='正常' then '1'   when A1MGORRE='异常' then '0'   when A1MGORRE='不详' then '2'  end  as zhyl6100039,\n" +
            "MALBJG as zhyl6100040,\n" +
            "case when MALBJGORRE='正常' then '1'   when MALBJGORRE='异常' then '0'   when MALBJGORRE='不详' then '2'  end  as zhyl6100041,\n" +
            "IROTHER as zhyl6100042,\n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl6100029\n" +
            "\n" +
            "from dbo.E_IR  where  DELMARK=0  and DMID='?' ";


    //尿蛋白电泳
    public final static String niaodanbaidianyong4 =" select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', UEDTC ) as bigint )*1000 AS zhyl6100043,\n" +
            "UEHOSP as zhyl6100044,\n" +
            "SPPORG  as zhyl6100045,\n" +
            "ALBPORG as zhyl6100046,\n" +
            "BPPORG as zhyl6100047\n" +
            "\n" +
            "from dbo.E_UE  where  DELMARK=0  and DMID='?'  "; //and [SOURCE] is null

    //血生化
    public final static String xueshenghua4 =" select   \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl6100048,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BBDTC ) as bigint )*1000 AS zhyl6100049,\n" +
            "BBHOSP as zhyl6100050,\n" +
            "SCR as zhyl6100051,\n" +
            "BUN as zhyl6100052,\n" +
            "ALT as zhyl6100053,\n" +
            "AST as zhyl6100054,\n" +
            "CA as zhyl6100055,\n" +
            "P as zhyl6100056,\n" +
            "K as zhyl6100057,\n" +
            "HCO3 as zhyl6100058,\n" +
            "ALP as zhyl6100059,\n" +
            "GGT as zhyl6100060,\n" +
            "TBA as zhyl6100061\n" +
            "\n" +
            "from dbo.E_BB  where  DELMARK=0  and DMID='?' ";

    //血常规
    public final static String xuechanggui4 =" select \n" +
            " cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BRDTC ) as bigint )*1000 AS zhyl6100062,\n" +
            " BRHOSP as zhyl6100063,\n" +
            " HGB as zhyl6100064\n" +
            "\n" +
            "from dbo.E_BR  where  DELMARK=0  and DMID='?' ";

    //尿钙/肌酐比
    public final static String niaogaijigan4 =" SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.PCDTC ) as bigint )*1000 AS zhyl6100065,\n" +
            "b.PCHOSP as zhyl6100066,\n" +
            "b.PCORRES as zhyl6100067\n" +
            "\n" +
            "\n" +
            "from dbo.E_PC b\n" +
            "where  b.DELMARK=0 and b.PCTYPE='尿钙/肌酐比'  and DMID='?' ";


    //24小时尿电解质
    public final static String niaodianjiezhi24h4 ="select *,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', HEDTC ) as bigint )*1000 AS zhyl6100068\n" +
            "from dbo.ehe1 \n" +
            "where   HETYPE='24小时尿电解质'   and  DELMARK=0  and  DMID='?'  ";

    //肾脏影像学检查
    public final static String shenzangyingxiang4= " SELECT \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl6100073,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', KIDTC ) as bigint )*1000 AS zhyl6100074,\n" +
            "KIHOSP as zhyl6100075,\n" +
            "case when KIMETHOD='泌尿系超声' then 1 when KIMETHOD='CT' then 2 when KIMETHOD='MRI' then 3 end as zhyl6100076,\n" +
            "case when KIORRES='正常' then '1'  when KIORRES='异常' then '0'  end as zhyl6100077,\n" +
            "LSL as zhyl6100078,\n" +
            "LSW as zhyl6100079,\n" +
            "LSH as zhyl6100080,\n" +
            "RSL as zhyl6100081,\n" +
            "RSW as zhyl6100082,\n" +
            "RSH as zhyl6100083,\n" +
            "case when KIECHO='正常' then '1'  when KIECHO='增强' then '0'  end as zhyl6100084,\n" +
            "KIDESC as zhyl6100085,\n" +
            "UPLOCCU as zhyl6100086\n" +
            "\n" +
            "from dbo.E_KI \n" +
            "where  DELMARK=0  and  DMID='?'  ";


    //肾动态检查
    public final static String shendongtai4=" select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', RDDTC ) as bigint )*1000 AS zhyl6100087,\n" +
            "RDHOSP as zhyl6100088,\n" +
            "RDORRE as zhyl6100089,\n" +
            "null as zhyl6100090\n" +

            "from dbo.E_RD where   DELMARK=0  and  DMID='?'   ";

    //肝、胆影像学检查
    public final static String gandanyingxiang4=" SELECT \n" +
            "case when  [SOURCE]='随访'  then '1' else '0' end as zhyl6100091,\n" +
            "case when LGIOCCUR='是' then 1  when LGIOCCUR='否' then 0 end as  zhyl6100092,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', LGIDTC ) as bigint )*1000 AS zhyl6100093,\n" +
            "LGIHOSP as zhyl6100094,\n" +
            "case when LGIMETHOD='超声' then 1 when LGIMETHOD='CT' then 2 when LGIMETHOD='MRI' then 3 end as zhyl6100095,\n" +
            "case when LGIORRES='正常' then 1  when LGIORRES='异常' then 0  end as  zhyl6100096,\n" +
            "case when LGISIZE='缩小' then 0  when LGISIZE='正常' then 1   when LGISIZE='增大' then 2  end as  zhyl6100097,\n" +
            "case when LGIECHO='正常' then 1  when LGIECHO='增强' then 0  end as zhyl6100098,\n" +
            "LGIDESC as zhyl6100099,\n" +
            "null as zhyl6100100\n" +
            "\n" +
            "from dbo.E_LGI  where  DELMARK=0 and  DMID='?' ";

    //免疫荧光-光镜
    public final static String guangjing4 ="  select \n" +
            "dmid,\n" +
            "\n" +
            "case when JianChaDuiXiang='患儿' then  '1'  when JianChaDuiXiang='患儿亲属' then  '2' end as zhyl6100116,\n" +
            "YuHuanErGuanXi as zhyl6100117,\n" +
            "case when ShenHuoJian='有' then '1' when ShenHuoJian='无' then '0' end as zhyl6100118,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', JianChaRiQi ) as bigint )*1000 AS zhyl6100119,\n" +
            "JianCeYiYuan as zhyl6100120,\n" +
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
            "from dbo.GuangJingTemp   where DMID='?' ";

    //电镜
    public final static String dianjing4 =" select DMID,\n" +
            "case when RBOBJ='患儿' then  '1'  when RBOBJ='患儿亲属' then  '2' end as zhyl6100124,\n" +
            "RBOBJDSC as zhyl6100125,\n" +
            "case when RBOCCUR='有' then '1' when RBOCCUR='无' then '0' end as zhyl6100126,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', RBDTC ) as bigint )*1000 AS zhyl6100127,\n" +
            "SITENAM as zhyl6100128,\n" +
            "case when EMOCCUR='有' then '1' when EMOCCUR='无' then '0' end as zhyl6200023,\n" +
            "case when GBMABNOR='有' then '1' when GBMABNOR='无' then '0' end as zhyl6200024,\n" +
            "case when EDDOCCUR='有' then '1' when EDDOCCUR='无' then '0' end as zhyl6200025,\n" +
            "RBDESC as zhyl6200026,\n" +
            "DIAGNOS as zhyl6200027,\n" +
            "null as zhyl6200028\n" +
            "\n" +
            "from dbo.RB where  DELMARK=0  and RBCAT='电镜'  and DMID='?'  ";  //and [SOURCE] is null

    //肝活检
    public final static String ganhuojian4 =" select \n" +
            "case when KBOCCUR='有' then '1' when KBOCCUR='无' then '0' end as zhyl6100130,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', KBDTC ) as bigint )*1000 AS zhyl6100131,\n" +
            "SITENAM AS zhyl6100132,\n" +
            "KBORRE as zhyl6100133,\n" +
            "KBDSC as zhyl6100134,\n" +
            "null as zhyl6100135\n" +
            "from dbo.KB  where  DELMARK=0  and DMID='?'  ";

    //眼科检查
    public final static String yankejiancha4 =" select \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', ESDTC ) as bigint )*1000 AS zhyl6100136,\n" +
            "ESHOSP as zhyl6100137,\n" +
            "case when ESOCCUR='有' then 1 when ESOCCUR='无' then 0 end as zhyl6100138,\n" +
            "replace(replace(replace(replace(replace(replace(replace(ESORRE,'视网膜色素变性',1),'眼球运动障碍',2),'近视',3),'远视',4),'弱视',5),'眼底神经萎缩',6),'其他',7)  as zhyl6100139,\n" +
            "null as zhyl6100140\n" +
            "from dbo.E_ES  where  DELMARK=0   and DMID='?' ";

    //其他检查
    public final static String qitajiancha4 =" select dmid,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', OTDTC ) as bigint )*1000 AS zhyl6100141,\n" +
            "OTHOSP as zhyl6100142,\n" +
            "OTDESC as zhyl6100143,\n" +
            "null as zhyl6100144\n" +
            "\n" +
            "from  dbo.E_OT where  DELMARK=0 and DMID='?'  ";

    //基因检测
    public final static String jiyinjiance4 =" select dmid,\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', GTDTC ) as bigint )*1000 AS zhyl6100145,\n" +
            "SITENAM as zhyl6100146,\n" +
            "case when SPTYPE='DNA'then '1' when SPTYPE='mRNA'then '2' end as zhyl6100147,\n" +
            "SPID as zhyl6100148,\n" +
            "replace(replace(replace(replace(replace(replace(replace(replace(replace(SPSOURCE,'_',','),'尿液','3'),'皮肤','8'),'转染的外周血淋巴细胞','2'),'外周血','1'),'唾液','4'),\n" +
            "'头发','5'),'羊水','6'),'绒毛膜细胞','7') as zhyl6100149,\n" +
            "REMAIN as zhyl6100150,\n" +
            "LOCATION as zhyl6100151,\n" +
            "case when GENE is not null then (\n" +
            "case when GENE='NPHS1' then '1'  when GENE='NPHS2' then '2'  when GENE='WT1' then '3'  when GENE='PLCE1' then '4'  when GENE='CD2AP' then '5'  when GENE='ACTN4' then '6'\n" +
            " when GENE='TRPC6' then '7'  when GENE='INF2' then '8'  when GENE='LMX1B' then '9'  when GENE='LAMB2' then '10'  when GENE='GLA' then '11'\n" +
            "   when GENE='ITGB4' then '12'  when GENE='SCARB2' then '13'  when GENE='COQ2' then '14'  when GENE='PDSS2' then '15'  when GENE='MTTL1' then '16'  when GENE='SMARCAL1' then '17'\n" +
            "    when GENE='SRY' then '18'  when GENE='COL4A3' then '19'  when GENE='COL4A4' then '20'  when GENE='COL4A5' then '21' else '22' end \n" +
            "    \n" +
            "    ) end as zhyl6100152,\n" +
            "    case when GTORRE='未检测到变异'  then '1' when GTORRE='异常'  then '2' when GTORRE='单核苷酸多态性'  then '3' when GTORRE='无法判断'  then '4'  end as zhyl6100153,\n" +
            "replace((replace(replace(replace(replace(replace(replace(replace(TYPEMU,'无义突变','1'),'错义突变','2'),'剪接突变','3'),'大缺失（缺失＞20个碱基）','4'),'小缺失（缺失≤20个碱基）','5'),'缺失插入','7'),\n" +
            "'缺失','6')),'插  入','5')  as zhyl6100154  ,\n" +
            "GTDESC as zhyl6100155,\n" +
            "\n" +
            "case when FATHER='是' then '1' when FATHER='否' then '0' when FATHER='不详' then '2' end as zhyl6200036,\n" +
            "FADSC as zhyl6200037,\n" +
            "case when MOTHER='是' then '1' when MOTHER='否' then '0'  when MOTHER='不详' then '2'  end as zhyl6200038,\n" +
            "MODSC as zhyl6200039,\n" +
            "case when [OTHERS]='是' then '1' when [OTHERS]='否' then '0'  when [OTHERS]='不详' then '2'  end as zhyl6200040,\n" +
            "OTHRELAT as zhyl6200041,\n" +
            "OTHDSC as zhyl6200042\n" +
            "\n" +
            "from dbo.GT where  DELMARK=0 and DMID='?'   ";


    //最终诊断
    public final static String zuizhongzhenduan4 = "  SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', DIDTC ) as bigint )*1000 AS zhyl6100157,\n" +
            "case when DITYPE='拟诊' then '1'  when DITYPE='确诊' then '2' end as  zhyl6100158,\n" +
            "DICONTENT  as zhyl6100159\n" +
            "\n" +
            "from dbo.DI where  DELMARK=0  and [SOURCE] is null and DMID='?' ";


    //-------------------随访-------------------

    //血生化
    public final static String suifangxueshenghua4 =" select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', BBDTC ) as bigint )*1000 AS zhyl8100004,\n" +
            "SCR as zhyl8100005,\n" +
            "BUN as zhyl8100006,\n" +
            "ALT as zhyl8100051,\n" +
            "AST as zhyl8100052,\n" +
            "CA as zhyl8100053,\n" +
            "P as zhyl8100054,\n" +
            "K as zhyl8100055,\n" +
            "HCO3 as zhyl8100056,\n" +
            "ALP as zhyl8100057,\n" +
            "GGT as zhyl8100058,\n" +
            "TBA as zhyl8100059\n" +
            "from dbo.E_BB  where  DELMARK=0  and  [SOURCE]='随访' and SOURCEID ='?' ";

    //尿蛋白/肌酐
    public final static String suifangniaodanbaijigan4 =" SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.PCDTC ) as bigint )*1000 AS zhyl8100012,\n" +
            "b.PCORRES as zhyl8100013\n" +
            "from dbo.E_PC b  \n" +
            "where  b.DELMARK=0  and PCTYPE ='尿蛋白/肌酐比' and  [SOURCE]='随访'   and SOURCEID ='?' ";
    //24小时尿蛋白定量
    public final static String suifang24niaodanbai4 ="  SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', b.UPDTC ) as bigint )*1000 AS zhyl8100014,\n" +
            "b.UPDL as zhyl8100015,\n" +
            "b.UPWEIGHT as zhyl8100016,\n" +
            "b.UPUWP as zhyl8100017\n" +
            "from dbo.E_UP b  \n" +
            "where  b.DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?'  ";

    //24小时肌酐清除率
    public final static String suifang24jiganqingchu4 =" select *,cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', HEDTC ) as bigint )*1000 AS zhyl8100018\n" +
            "from dbo.ehe1 \n" +
            "where   HETYPE='24小时肌酐清除率'   and  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?'  ";


    //肾早衰
    public final static String suifangshenzaoshuai4=" select   \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', IRDTC ) as bigint )*1000 AS zhyl8100024,\n" +
            "MALBJG as zhyl8100025,\n" +
            "case when MALBJGORRE='正常' then '1'   when MALBJGORRE='异常' then '0'   when MALBJGORRE='不详' then '2'  end  as zhyl8100026\n" +
            "from dbo.E_IR  where  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?'  ";

    //肾脏影像学检查
    public final static String suifangshenzaoyingxiangxue4= "SELECT \n" +
            "\n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', KIDTC ) as bigint )*1000 AS zhyl8100027,\n" +
            "LSL as zhyl8100029,\n" +
            "LSW as zhyl8100030,\n" +
            "LSH as zhyl8100031,\n" +
            "RSL as zhyl8100032,\n" +
            "RSW as zhyl8100033,\n" +
            "RSH as zhyl8100034,\n" +
            "case when KIMETHOD='泌尿系超声' then 1 when KIMETHOD='CT' then 2 when KIMETHOD='MRI' then 3 end as zhyl8100076,\n" +
            "case when KIECHO='正常' then '1'  when KIECHO='增强' then '0'  end as zhyl8100035,\n" +
            "KIDESC as zhyl8100036\n" +
            "\n" +
            "from dbo.E_KI \n" +
            "where  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?' ";

    //肝、胆影像学检查
    public final static String gandanyingxiangxue4=" SELECT \n" +
            "cast( DATEDIFF(SECOND, '1970-01-01 00:00:00', LGIDTC ) as bigint )*1000 AS zhyl8100093,\n" +
            "case when LGIMETHOD='超声' then 1 when LGIMETHOD='CT' then 2 when LGIMETHOD='MRI' then 3 end as zhyl8100095,\n" +
            "case when LGISIZE='缩小' then 0  when LGISIZE='正常' then 1   when LGISIZE='增大' then 2  end as  zhyl8100097,\n" +
            "case when LGIECHO='正常' then 1  when LGIECHO='增强' then 0  end as zhyl8100098,\n" +
            "LGIDESC as zhyl8100099\n" +
            "\n" +
            "from dbo.E_LGI  where  DELMARK=0 and  [SOURCE]='随访'   and SOURCEID ='?' ";

}
