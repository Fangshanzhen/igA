package com.igA.demo.constant;

public class PediatricKidneyDatabaseConstant {

    public final static String KidneyIdSql2 = "select a.id  from dbo.DM a  where a.Del_Mark=0 and a.DataTypeId='2' ";

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


    public final static String xianbingshi2 ="select * from dbo.mh2 where id='?'";

    public final static String gerenshi2 ="select * from dbo.gerenshi  where id='?'";

    public final static String jiazhushi2 ="select * from dbo.fh1 where FHSUBREL='#' and  DMID='?'  ";

}
