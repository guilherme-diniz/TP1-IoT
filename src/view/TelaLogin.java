package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	public TelaLogin() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 274, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(89, 11, 86, 14);
		contentPane.add(lblUsername);
		
		username = new JTextField();
		username.setBounds(89, 25, 86, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(89, 56, 86, 14);
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setToolTipText("password");
		password.setBounds(89, 70, 86, 20);
		
		contentPane.add(password);
		
		JCheckBox PassCheckBox = new JCheckBox("Show/Hide password");
		PassCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		PassCheckBox.addActionListener(e -> {
            if(PassCheckBox.isSelected())
            {
                password.setEchoChar((char)0);
            }
            else
            {
                password.setEchoChar('*');
            }
        });
		PassCheckBox.setBounds(6, 97, 246, 23);
		contentPane.add(PassCheckBox);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(89, 127, 86, 23);
		btnLogin.addActionListener(arg0 -> {

            String userText = username.getText();
            String passText = password.getText();

            if(userText.equals("alien") && passText.equals("password"))
            {
                TelaPrincipal principal = new TelaPrincipal();
                principal.show();
                dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid username or password");

            }
        });
		contentPane.add(btnLogin);
		
		
	
		
		
	}
}
