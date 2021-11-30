package test;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class LoginTest {

	public String staticID = null;
	public String staticPW = null;
	
	private JFrame frame;
	private final JPanel Login = new JPanel();
	private JPasswordField txtPW;
	private JPasswordField txtSignUpPW;
	protected Object main;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginTest window = new LoginTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public LoginTest() {
		initialize();
	}
	
	
	private void initialize() {
		frame = new JFrame("Login");
		frame.setBounds(100, 100, 450, 300);
		//위치결정(가로위치,세로위치,가로길이,세로길이)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창닫았을시 프로세스까지 종료
		frame.getContentPane().setLayout(null); //절대위치 사용
		frame.setResizable(false);//창 크기 조절할수 없도록
		frame.setLocationRelativeTo(null);//윈도우 창 가운데 띄움
		
		JPanel SignUp = new JPanel();
		SignUp.setLayout(null);//Sign의 배치관리자 제거
		SignUp.setBounds(0, 0, 444, 257);//위치결정
		frame.getContentPane().add(SignUp);//JFrame에 붙도록
		
		JLabel label = new JLabel("ID");
		label.setHorizontalAlignment(SwingConstants.RIGHT); //수평 되도록
		//label.setFont(new Font("Arial", Font.PLAIN, 15)); // 폰트설정 가능(글꼴,타입,사이즈)
		label.setBounds(43, 85, 62, 18);//위치 결정
		SignUp.add(label);
		
		JLabel label_1 = new JLabel("PW");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT); //수평되도록
		label_1.setBounds(43, 120, 62, 18);
		SignUp.add(label_1);
		
		JTextPane txtSignUpID = new JTextPane();
		txtSignUpID.setBounds(133, 85, 135, 18);
		SignUp.add(txtSignUpID);
		
		txtSignUpPW = new JPasswordField();;
		txtSignUpPW.setBounds(133, 120, 135, 18);
		SignUp.add(txtSignUpPW);
		
		JButton btnToLogin = new JButton("Sign Up");
		btnToLogin.setBounds(283, 85, 99, 53);
		SignUp.add(btnToLogin);
		Login.setBounds(0, 0, 444, 265);
		frame.getContentPane().add(Login);
		Login.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(43, 85, 62, 18);
		Login.add(lblNewLabel);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPw.setBounds(43, 120, 62, 18);
		Login.add(lblPw);
		
		JTextPane txtID = new JTextPane();
		txtID.setBounds(133, 85, 135, 18);
		Login.add(txtID);
		
		txtPW = new JPasswordField();
		txtPW.setBounds(133, 120, 135, 18);
		Login.add(txtPW);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(283, 85, 99, 53);
		Login.add(btnLogin);
		
		JButton btnToSignUp = new JButton("Sign Up");
		btnToSignUp.setBounds(283, 144, 99, 26);
		Login.add(btnToSignUp);
		

	
		SignUp.setVisible(false);

		

		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtID.getText().equals(staticID) && txtPW.getText().equals(staticPW)) {
					JOptionPane.showMessageDialog(null, "Login Success!");
					new MainMenu();//새로운 창 열기
					frame.dispose();
				} else { //ID,PW 틀릴경우
					JOptionPane.showMessageDialog(null, "Please Check your ID or PW");
					txtID.setText(null);
					txtPW.setText(null);
				}
				
			}
		});
		
		btnToSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtID.setText(null);
				txtPW.setText(null);
				Login.setVisible(false);
				SignUp.setVisible(true);
				
			}
		});
		
		btnToLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				staticID = txtSignUpID.getText();
				staticPW = txtSignUpPW.getText();
				
				txtSignUpID.setText(null);
				txtSignUpPW.setText(null);
				
				Login.setVisible(true);
				SignUp.setVisible(false);
			}
		});
	}

}
	