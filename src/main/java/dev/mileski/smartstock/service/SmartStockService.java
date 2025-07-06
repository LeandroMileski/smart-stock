package dev.mileski.smartstock.service;

import org.springframework.stereotype.Service;

@Service
public class SmartStockService {

    private final ReportService reportService;

    public SmartStockService(ReportService reportService) {
        this.reportService = reportService;
    }

    public void start(String reportPath) {
        // 1. Validate the report path
        if (reportPath == null || reportPath.isEmpty()) {
            throw new IllegalArgumentException("Report path cannot be null or empty");
        }
        // 2. Check if the report file exists
        java.io.File reportFile = new java.io.File(reportPath);
        if (!reportFile.exists()) {
            throw new IllegalArgumentException("Report file does not exist at the specified path: " + reportPath);
        }
        // 3. Process the stock report
        try {
            var stockItems = reportService.readStockReport(reportPath);
            // Process the stock items as needed
            // For example, you might want to filter, aggregate, or transform the data
            // This part is left as a placeholder for your specific processing logic
        } catch (Exception e) {
            throw new RuntimeException("Failed to process the stock report", e);
        }

        // 4. Log results of the processing to mongoDB

    }
}
