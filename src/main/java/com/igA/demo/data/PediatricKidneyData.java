package com.igA.demo.data;

import com.igA.demo.utils.JDBCUtils;
import com.igA.demo.utils.ResultSetUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.json.JSONException;

import static com.igA.demo.constant.DataTransform.*;
import static com.igA.demo.constant.PediatricKidneyDatabaseConstant.KidneyIdSql;
import static com.igA.demo.data.igAData.commonExecute;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogChannelFactory;

@Slf4j
public class PediatricKidneyData {

    public static void transformData(String baseUrl, String admin, String password, String id) throws Exception {


        Connection connection = null; //默认postgresql
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection("sqlserver", "43.143.220.216", "1433", "PediatricKidneyDatabase", "PediatricKidneyDatabase", "sa", "6774566sa!");
        } catch (SQLException e) {
            log.error("database connection error: " + e, "");
        }

        log.info("数据库连接成功");
        if (connection != null) {
            // 设置连接的持久性
            connection.setHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);
            List<String> idList = new ArrayList<>();

            idList = commonExecute(connection, statement, resultSet, KidneyIdSql);
            if (id != null) {
                idList = Collections.singletonList(id);
            }

            if (idList != null && idList.size() > 0) {
                for (String s : idList) {

                    JSONObject jsonObject = new JSONObject();
                    Map<String, Object> map = new HashMap<>();
                }
            }






        }

    }
}
