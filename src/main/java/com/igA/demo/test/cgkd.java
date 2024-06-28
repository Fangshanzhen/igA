package com.igA.demo.test;

import com.igA.demo.data.*;
import com.igA.demo.utils.JDBCUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.igA.demo.constant.PediatricKidneyDatabaseConstant2.KidneyIdSql2;
import static com.igA.demo.data.igAData.commonExecute;

//遗传病-早发蛋白尿数据

/**
 * start： 1开始传数据， 0或者其他不传数据
 * type:
 * 1		Alport综合征         1
 * 2		蛋白尿性肾脏疾病     9
 * 3		肾小管疾病          3
 * 4		肾脏囊性疾病        5
 * 5		先天性肾脏尿路畸形  7
 */

@Slf4j
public class cgkd {

    public static void main(String[] args) throws Exception {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtils.getConnection("sqlserver", "127.0.0.1", "1433", "master", "master", "sa", "123456");
        } catch (SQLException e) {
            log.error("database connection error: " + e, "");
            return; // 如果数据库连接失败，直接返回
        }
        log.info("数据库连接成功");


        String type = "1";  //表明不同的数据
        String start = "0"; //1开始传数据， 0或者其他不传数据

        if (connection != null) {
            // 设置连接的持久性
            connection.setHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);
            List<String> idList = new ArrayList<>();
            idList = commonExecute(connection, statement, resultSet, KidneyIdSql2.replace("#", type));

//            idList = Arrays.asList("2c95808a641e4b5d01644068febd021b");

            int poolSize = 3; // 调整线程池大小
            // 创建一个固定大小的线程池
            ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
            // 创建一个AtomicInteger来统计成功运行的次数
            AtomicInteger successCount = new AtomicInteger(0);

            try {
                for (final String id : idList) {
                    executorService.submit(() -> {
                        try {
                            PediatricKidneyData2.transformData(null, "admin", "72d0645981154de34f35e03d06c626cc", id, start, type);
                            successCount.incrementAndGet(); // 成功运行后递增计数器
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            } finally {
                executorService.shutdown();
                try {
                    // 等待所有任务完成
                    if (!executorService.awaitTermination(60, TimeUnit.MINUTES)) {
                        executorService.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    executorService.shutdownNow();
                    Thread.currentThread().interrupt();
                }
            }

            // 输出成功运行的次数
            log.info("成功运行的次数: " + successCount.get());
        }

        // 关闭资源
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            log.error("Error closing resources: " + e, "");
        }
    }


}
