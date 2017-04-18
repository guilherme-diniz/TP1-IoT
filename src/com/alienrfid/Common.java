package com.alienrfid;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guilherme.diniz on 4/17/17.
 */
public class Common {

    public static final int INTERVAL_MODE = 15; // Segundos
    public static final String AUTOMATE_IP = "150.164.0.244";
    public static Map<String, Integer> tagsMap = new HashMap<>();
    public static DefaultTableModel tableModel;
    public static long readCount = 0;

    public static void setTableModel() {
        tableModel = new DefaultTableModel(
                new Object[] { "ID", "Reads" , "Read Rate (read/s)", "Success Rate (%)"}, 0
        );
        for (Map.Entry<String,Integer> entry : tagsMap.entrySet()) {
            double readRate = entry.getValue() * 1.0 / INTERVAL_MODE;
            double successRate = (entry.getValue() * 1.0 / readCount) * 100;
            tableModel.addRow(new Object[] { entry.getKey(), entry.getValue(), String.format("%.2f", readRate), String.format("%.2f", successRate)});
        }
    }
}
