package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;
import DB.DBManager;
import data.UserInformationData;

public class frmLogin extends JFrame implements ActionListener {

	Main main;
	BufferedImage img = null;
	JTextField loginTextField;
	JPasswordField passwordField;
	JButton btnLogin, btnId, btnPass, btnJoin;
	// 생성자
	public frmLogin() {
		setTitle("로그인 테스트");
		setSize(1040, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 레이아웃 설정
		setLayout(null);
		
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
				(windowSize.height - frameSize.height) / 2);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1040, 800);
		layeredPane.setLayout(null);

		// 패널1
		// 이미지 받아오기
		try {
			img = ImageIO.read(new File("img/imback.png"));
		} catch (IOException e) {
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}

		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1040, 800);

		// 로그인 필드
		loginTextField = new JTextField(15);
		loginTextField.setBounds(430, 410, 160, 27);
		layeredPane.add(loginTextField);
		loginTextField.setOpaque(false);
		loginTextField.setForeground(Color.pink);
		//loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		// 패스워드
		passwordField = new JPasswordField(15);
		passwordField.setBounds(430, 460, 160, 27);
		passwordField.setOpaque(false);
		passwordField.setForeground(Color.pink);
		// passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		layeredPane.add(passwordField);

		// 로그인버튼 추가
		btnLogin = new JButton(new ImageIcon("img/login.png"));
		btnJoin = new JButton(new ImageIcon("img/join.png"));
		btnLogin.setBounds(575, 398, 104, 48);
		btnJoin.setBounds(580, 450, 104, 48);
		btnId = new JButton();
		btnId.setBounds(346, 400, 100, 50);
		btnPass = new JButton();
		btnPass.setBounds(345, 450, 100, 50);

		// 버튼 투명처리

		btnId.setText("I  D");
		btnPass.setText("Password");
		btnBlind(btnLogin);
		btnBlind(btnId);
		btnBlind(btnPass);
		btnBlind(btnJoin);

		btnLogin.addActionListener(this);
        btnJoin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("ȸ������ ����");
            	main.showFrameSignup();
            }
        });

		layeredPane.add(btnLogin);
		layeredPane.add(btnId);
		layeredPane.add(btnPass);
		layeredPane.add(btnJoin);

		// 마지막 추가들
		layeredPane.add(panel);
		add(layeredPane);
		setVisible(true);
	}
	
	public void setMain(Main main){
		this.main=main;
	}

	public void actionPerformed(ActionEvent e) {
		String id = loginTextField.getText();
		char[] pass = passwordField.getPassword();
		String password = new String(pass);

		if (id.equals("") || password.equals("")) {
			// 메시지를 날린다.
			JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 모두 입력해주세요.");
		} else {

			// 로그인 참 거짓 여부를 판단
			UserInformationData uid = DBManager.UserIDLogin(id, password);
			if (uid!=null) {
				// 로그인 성공일 경우
				JOptionPane.showMessageDialog(null, "로그인 성공!");
				///
				//if(id==admin)
				//frmAdmin
				if(id.equals("admin")){
					main.showFrameadmin(this);
				}else{
					main.showFrameMain(this);
				}
			} else {
				// 로그인 실패일 경우
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다.");
			}

		}
		password = null;
	}

	public void btnBlind(JButton btn) {
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
	}

	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

}//