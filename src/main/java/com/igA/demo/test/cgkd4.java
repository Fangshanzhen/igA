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

import static com.igA.demo.constant.PediatricKidneyDatabaseConstant4.KidneyIdSql4;
import static com.igA.demo.data.igAData.commonExecute;

////遗传病-早发蛋白尿数据
@Slf4j
public class cgkd4 {

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
        if (connection != null) {
            // 设置连接的持久性
            connection.setHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);
            List<String> idList = new ArrayList<>();
            idList = commonExecute(connection, statement, resultSet, KidneyIdSql4);

//            idList= Arrays.asList("2c95808a641e4b5d01649e94e5230acf");

            int numberOfIds = idList.size();
            int poolSize = 4; // 根据你的机器配置和任务复杂度调整线程池大小

            // 创建一个固定大小的线程池
            ExecutorService executorService = Executors.newFixedThreadPool(poolSize);

            // 创建一个AtomicInteger来统计成功运行的次数
            AtomicInteger successCount = new AtomicInteger(0);

            try {
                for (final String id : idList) {
                    executorService.submit(() -> {
                        try {
                            PediatricKidneyData4.transformData(null, null, null, id);
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
