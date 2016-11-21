package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.Main;
import view.panel.PanImgload;
import DB.DBManager;

public class frmSignup extends JFrame {
   private JLayeredPane layeredPane = new JLayeredPane(); // 셀로판지 식;
   private boolean idCheck = false;
   BufferedImage img = null;
   JTextField idTextField,nameTextField,phoneTextField;
   JPasswordField passwordField;
   JButton btnJoin,btnClose;
   Main main;
   public static void main(String[] args) {
      frmSignup Signup = new frmSignup();
   }

   public frmSignup() {
      setTitle("회원 가입");
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setSize(432, 640);
      setLayout(null);
      setVisible(true);
      
      Dimension frameSize = this.getSize();
      Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation((windowSize.width - frameSize.width) / 2,
            (windowSize.height - frameSize.height) / 2);
      
      
      setPanel(layeredPane).setBounds(0,0,432,640);
      JPanel backGround = new PanImgload("img/signup.png");
      setPanel(backGround).setBounds(0,0,432,640);
      
      btnClose = new JButton(new ImageIcon("img/취소.png"));
      btnJoin = new JButton(new ImageIcon("img/가입.png"));
      btnClose.setBounds(230, 470, 120, 55);
      btnJoin.setBounds(75, 470, 120, 55);
      btnBlind(btnJoin);
      btnBlind(btnClose);
      
      idTextField = new JTextField(15);
	  idTextField.setBounds(150, 230, 190, 30);
	  layeredPane.add(idTextField);
      idTextField.setOpaque(false);
      idTextField.setForeground(Color.pink);
      idTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      
      passwordField = new JPasswordField(15);
	  passwordField.setBounds(149, 285, 190, 30);
	  layeredPane.add(passwordField);
      passwordField.setOpaque(false);
      passwordField.setForeground(Color.pink);
      passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      
      nameTextField = new JTextField(15);
	  nameTextField.setBounds(149, 343, 190, 30);
	  layeredPane.add(nameTextField);
      nameTextField.setOpaque(false);
      nameTextField.setForeground(Color.pink);
      nameTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      
      phoneTextField = new JTextField(15);
	  phoneTextField.setBounds(149, 402, 190, 30);
	  layeredPane.add(phoneTextField);
      phoneTextField.setOpaque(false);
      phoneTextField.setForeground(Color.pink);
      phoneTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());      
      
      
      btnJoin.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  System.out.println("가입버튼 눌림");
        	  String strID = idTextField.getText().trim();
        	  String strName = nameTextField.getText().trim();
        	  String strPhone = phoneTextField.getText().trim();
        	  char[] pass = passwordField.getPassword();
      		  String strPass = new String(pass);
      		  
      		  idCheck = DBManager.UserIDCheck(strID);
      		  if(strID.length() != 0 && strPass.length() != 0 && 
								strName.length() != 0 && strPhone.length() != 0){
          		  if(idCheck){ //아이디 사용 가능
          			  if(DBManager.InsertUser(strID, strPass, strName, strPhone, 1))
          			  {
          				JOptionPane.showMessageDialog(null, "회원 가입이 완료되었습니다.");
          			  }else{
          				JOptionPane.showMessageDialog(null, "회원가입 실패입니다.");
          			  }
          		  }else{ //���̵� �ߺ�
          			JOptionPane.showMessageDialog(null, "아이디 중복입니다.");
          		  }
      		  }else{
      			JOptionPane.showMessageDialog(null, "공백이 존재해서는 안됩니다.");
      		  }
  		  
          }
      });
      btnClose.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  dispose();
          }
      });
      
      add(setJLayered(backGround,btnJoin,btnClose));
      
   }

	public void btnBlind(JButton btn) {
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setContentAreaFilled(false);
	}

   public void setMain(Main main) {
	   this.main = main;
   }
   
   //배경투명처리 및 레이아웃 널
   //J컴포넌트로 받는 이유는 J패널 이외에 다른 것도 받기 위해서 예를들면 레이어드패인
   public JComponent setPanel(JComponent panel){
      panel.setLayout(null);
      panel.setOpaque(false);
      return panel;
   }
   
   //다중 parameter로 잡았음
   //코드중복을줄이기위해 안써도 상관없음 ㅋㅋ
   public JLayeredPane setJLayered(Component...components){
        int i = 0;
        for (Component component : components)
            layeredPane.add(component, new Integer(i++));
        return layeredPane;
    }
}