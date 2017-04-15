package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alienrfid.RFIDCommunication;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.GridLayout;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private String readerIP;
	private JTable table_1;
	private JTable table_2;
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
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        readerIP = (String)cb.getSelectedItem();
		        System.out.println(readerIP);
			}
		});
		
		panel.add(comboBox);
		
		JButton button = new JButton("Confirma");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					 
		            new RFIDCommunication(readerIP);
		        } catch(AlienReaderException e2) {
		            System.out.println("Error: " + e2.toString());
		        }
				
			}
		});
		button.setBounds(440, 10, 75, 23);
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
		table_2 = new JTable(model);
		scrollPane_1.setViewportView(table_2);
		table_2.setModel(new DefaultTableModel(
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
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_ActiveMode = new GroupLayout(ActiveMode);
		gl_ActiveMode.setHorizontalGroup(
			gl_ActiveMode.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ActiveMode.createSequentialGroup()
					.addGap(409)
					.addComponent(btnRead))
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 924, GroupLayout.PREFERRED_SIZE)
		);
		gl_ActiveMode.setVerticalGroup(
			gl_ActiveMode.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ActiveMode.createSequentialGroup()
					.addComponent(btnRead)
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE))
		);
		
		
		DefaultTableModel model2 = new DefaultTableModel();
		table_1 = new JTable(model2);
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
				new Object[][]{
				},
				new String[]{
					"ID","Discovered","Last Seen","Antenna","Reads"
				}
				));
		ActiveMode.setLayout(gl_ActiveMode);
	}
}
