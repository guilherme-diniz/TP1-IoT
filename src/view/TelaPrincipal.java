package view;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alien.enterpriseRFID.notify.MessageListenerService;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;
import com.alienrfid.AutoMessageListener;
import com.alienrfid.Common;
import com.alienrfid.RFIDCommunication;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

public class TelaPrincipal extends JFrame {

    private JPanel contentPane;
	private String readerIP;
	private JTable tableActiveMode;
	private JTable tableAutoModel;

	private RFIDCommunication rfid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 934, 40);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"150.164.10.41", "150.164.10.42"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(318, 11, 116, 20);
		comboBox.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            readerIP = (String)cb.getSelectedItem();
            System.out.println(readerIP);
        });
		
		panel.add(comboBox);
		
		JButton button = new JButton("Connect");
		button.addActionListener(e -> {

            try {
               this.rfid = new RFIDCommunication(readerIP, "alien", "password");
            } catch(AlienReaderException e2) {
                System.out.println("Error: " + e2.toString());
            }

        });
		button.setBounds(440, 10, 100, 23);
		panel.add(button);
		contentPane.setLayout(null);
		contentPane.add(panel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 56, 929, 434);
		contentPane.add(tabbedPane);
		
		JLayeredPane AutomateMode = new JLayeredPane();
		tabbedPane.addTab("Automate Mode", null, AutomateMode, null);

		JButton button_1 = new JButton("Read");
		button_1.setHorizontalTextPosition(SwingConstants.CENTER);

		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_AutomateMode = new GroupLayout(AutomateMode);
		gl_AutomateMode.setHorizontalGroup(
			gl_AutomateMode.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AutomateMode.createSequentialGroup()
					.addGap(409)
					.addComponent(button_1))
				.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 924, GroupLayout.PREFERRED_SIZE)
		);
		gl_AutomateMode.setVerticalGroup(
			gl_AutomateMode.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AutomateMode.createSequentialGroup()
					.addComponent(button_1)
					.addGap(11)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE))
		);
		DefaultTableModel model = new DefaultTableModel();
		tableAutoModel = new JTable(model);
		scrollPane_1.setViewportView(tableAutoModel);
		tableAutoModel.setModel(new DefaultTableModel(
				new Object[][]{
				},
				new String[]{
					"ID","Discovered","Last Seen","Antenna","Reads"
				}
				));
		AutomateMode.setLayout(gl_AutomateMode);
		
		JLayeredPane ActiveMode = new JLayeredPane();
		tabbedPane.addTab("Active Mode", null, ActiveMode, null);
		
		JButton btnRead = new JButton("Read");
		btnRead.setHorizontalTextPosition(SwingConstants.CENTER);

        JButton btnClear = new JButton("Clear");
        btnClear.setHorizontalTextPosition(SwingConstants.CENTER);

		JLabel readLabel = new JLabel();

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout glActiveMode = new GroupLayout(ActiveMode);
		glActiveMode.setHorizontalGroup(
			glActiveMode.createParallelGroup(Alignment.LEADING)
				.addGroup(glActiveMode.createSequentialGroup()
					.addGap(409)
                    .addComponent(btnRead)
                    .addComponent(btnClear)
                    .addComponent(readLabel))
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 924, GroupLayout.PREFERRED_SIZE)
		);
		glActiveMode.setVerticalGroup(
			glActiveMode.createParallelGroup(Alignment.LEADING)
				.addGroup(glActiveMode.createSequentialGroup()
                    .addGroup(glActiveMode.createParallelGroup().addComponent(btnRead)
                            .addComponent(btnClear)
                            .addComponent(readLabel))
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE))
		);
		

		tableActiveMode = new JTable();
		scrollPane.setViewportView(tableActiveMode);
		ActiveMode.setLayout(glActiveMode);

		//ACTIVE
		btnRead.addActionListener(e -> {
		    btnClear.doClick();
		    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		    if (this.rfid == null) {
		        JOptionPane.showMessageDialog(this, "Connect to the reader first.");
		        return;
            }

            try {
                long t= System.currentTimeMillis();
                long end = t+ Common.INTERVAL_MODE * 1000;
                while(System.currentTimeMillis() < end) {
                    Tag[] tags = this.rfid.read();
                    if (tags == null) {
                        continue;
                    }
                    for (Tag tag : tags) {
                        if (Common.tagsMap.containsKey(tag.getTagID())) {
                            Common.tagsMap.put(tag.getTagID(), Common.tagsMap.get(tag.getTagID()) + 1);
                        } else {
                            Common.tagsMap.put(tag.getTagID(), 1);
                        }
                    }
                }
                Common.readCount = this.rfid.getReadCount();
                Common.setTableModel();
                tableActiveMode.setModel(Common.tableModel);
                readLabel.setText("Reads: " + this.rfid.getReadCount());
                this.setCursor(Cursor.getDefaultCursor());
            } catch (AlienReaderException e1) {
                e1.printStackTrace();
                this.setCursor(Cursor.getDefaultCursor());
            }
        });

		btnClear.addActionListener(e -> {
		    this.rfid.restart();
		    Common.tagsMap.clear();
		    readLabel.setText("");
		    tableActiveMode.setModel(new DefaultTableModel());
        });

		//AUTOMATE
        button_1.addActionListener(e -> {
            try {
                Common.tagsMap.clear();
                this.rfid.setAutomateMode(Common.AUTOMATE_IP);
                MessageListenerService service = new MessageListenerService(4000);
                service.setMessageListener(new AutoMessageListener());
                service.startService();
                tableAutoModel.setModel(Common.tableModel);
            } catch (AlienReaderException | IOException e1) {
                e1.printStackTrace();
            }
        });
	}


}
