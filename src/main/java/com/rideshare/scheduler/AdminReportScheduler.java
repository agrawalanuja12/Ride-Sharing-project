package com.rideshare.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AdminReportScheduler {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Run every day at 1 AM
    @Scheduled(cron = "0 0 1 * * ?")
    public void generateDailyAdminReport() {
        String sql = """
            INSERT INTO admin_reports (total_rides, total_earnings, average_rating)
            SELECT
                COUNT(*) AS total_rides,
                COALESCE(SUM(p.amount), 0) AS total_earnings,
                COALESCE(AVG(r.rating), 5) AS average_rating
            FROM rides rd
            LEFT JOIN payments p ON rd.id = p.ride_id
            LEFT JOIN ratings r ON rd.id = r.ride_id
            WHERE DATE(rd.end_time) = CURDATE()
        """;

        jdbcTemplate.update(sql);
        System.out.println("✅ Daily admin report inserted.");
    }
}
