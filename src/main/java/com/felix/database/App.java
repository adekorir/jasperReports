package com.felix.database;

import com.sun.javafx.event.EventUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Felix
 */
public class App {
    public static void main(String[] args) {
        // get list of registered databases
        Database[] databases = {
            new MySQLDatabase("farm", "root", ""),
            new DerbyDatabase("//localhost:1527;databaseName=data/testDB;create=true"),
            new DerbyDatabase("data/petsDB;create=true")
        };
        
        // initialize databases
        for (Database db: databases) {
            try {
                db.init();
            } catch (SQLException e) {
                System.err.printf("Error initializing database: %s %s %n",db, e.getMessage());
            }
        }
        
        // todo: do something with the databases
        // display pets report
        try (final Connection conn = databases[0].connect()) {  // get the mysql database
            final JasperReport jreport = JasperCompileManager.compileReport(App.class.getResourceAsStream("reports/pets-report.jrxml"));
            final JasperPrint jprint = JasperFillManager.fillReport(jreport, new HashMap<>(), conn);
            // display the report
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Pets Report");
                JRViewer viewer = new JRViewer(jprint);
                frame.add(viewer);
                frame.setSize(600, 700);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // tear down the database
        for (Database db: databases) {
            try {
                db.tearDown();
            } catch (SQLException e) {
                System.err.printf("Error tearing down database %s: %s %n", db, e.getMessage());
            }
        }
    }
}
