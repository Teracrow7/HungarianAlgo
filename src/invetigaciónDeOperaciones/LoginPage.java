package invetigaciónDeOperaciones;

import java.awt.Color; 
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class LoginPage implements ActionListener{
	 ImageIcon iconTec = new ImageIcon("tec.png");
	HashMap<String,String> users = new HashMap<String,String>();
	String userID, password;
	JLabel labelUser = new JLabel("User:");
	JLabel labelPass = new JLabel("Password:");
	JFrame loginFrame = new JFrame();
	JTextField userTextField = new JTextField();
	JPasswordField passwordTextField = new JPasswordField();
	JLabel loginMessage = new JLabel();
	
		public LoginPage(HashMap<String,String> tecUsers) {
		
			users = tecUsers;
			createTextFields();
			createLoginPage();
			
		}
		
		private void createLoginPage() {
			loginFrame.setIconImage(iconTec.getImage());
			loginMessage.setBounds(125,250, 250, 35);
			loginMessage.setFont(new Font(null,Font.ITALIC,25));
			
			loginFrame.add(loginMessage);
			loginFrame.add(labelPass);
			loginFrame.add(labelUser);
			loginFrame.add(new EnterButton());
			loginFrame.add(new ResetButton());
			loginFrame.add(userTextField);
			loginFrame.add(passwordTextField);
			loginFrame.add(new PanelBG());
			loginFrame.setSize(420,420);
			loginFrame.setResizable(false);
			loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			loginFrame.setTitle("TecNM");
			loginFrame.setLocationRelativeTo(null);
			loginFrame.setVisible(true);
		}
		
		
		public void createTextFields() {
			
			labelUser.setBounds(100,100,75,25);
			labelUser.setForeground(Color.BLACK);
			labelPass.setBounds(100,150,75,25);
			labelPass.setForeground(Color.BLACK);
			
			
			
			passwordTextField.setBounds(200, 150, 125, 25);
			passwordTextField.setVisible(true);
			userTextField.setBounds(200, 100, 125, 25);
			userTextField.setVisible(true);
			
			
		}


		
		class EnterButton extends JButton implements ActionListener{
			 private Color color1 = new Color(32, 0, 44);
			 private Color color2 = new Color(203, 180, 212);
			 
			EnterButton(){
				
				this.setBounds(225, 200, 100, 25);
				this.addActionListener(this);
				this.setText("Enter");
				this.setForeground(Color.white);
				this.setFocusable(false);
				this.setContentAreaFilled(false);
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));
				this.setBorder(new EmptyBorder(10,20,10,20));
				}
			
			public void paint(Graphics g) {
				
				int Height = getHeight();
				int Width = getWidth();
				BufferedImage img = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2D = img.createGraphics();
				g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gp = new GradientPaint(0,0,color1,Width,0,color2);
				g2D.setPaint(gp);
				g2D.fillRoundRect(0, 0, Width, Height, Height, Height);
				g.drawImage(img,0,0,null);
				
				super.paintComponent(g);
				
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==this) {
					 userID = userTextField.getText();
					 password = String.valueOf(passwordTextField.getPassword());
					 
					 if(users.containsKey(userID)) {
						 if(users.get(userID).equals(password)) {
						 loginMessage.setForeground(Color.GREEN);
						 loginMessage.setText("Access Correct");
						 Menu menu = new Menu();
						 menu.createPrincipalFrame();
						 }
						 else {
								loginMessage.setForeground(Color.red);
								loginMessage.setText("Access Denied");
								}
					 }
					 else {
							loginMessage.setForeground(Color.red);
							loginMessage.setText("Access Denied");
							}
				}
				
				
			}
			
			
		}
		
		
		class ResetButton extends JButton implements ActionListener{
			private Color color1 = new Color(32, 0, 44);
			private Color color2 = new Color(203, 180, 212);
			 
			ResetButton(){
				this.setBounds(115, 200, 100, 25);
				this.setText("Reset");
				this.addActionListener(this);
				this.setForeground(Color.white);
				this.setFocusable(false);
				this.setContentAreaFilled(false);
				this.setCursor(new Cursor(Cursor.HAND_CURSOR));
				this.setBorder(new EmptyBorder(10,20,10,20));
			}
			
			public void paint(Graphics g) {
				int Width = getWidth();
				int Height = getHeight();
				
				BufferedImage img = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2D = img.createGraphics();
				g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gp = new GradientPaint(0,0,color1,Width,0,color2);
				g2D.setPaint(gp);
				g2D.fillRoundRect(0, 0, Width, Height, Height, Height);
				g.drawImage(img,0,0,null);
				
				super.paintComponent(g);
				
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==this) {
					userTextField.setText("");
					passwordTextField.setText("");
				}
				
				
			}
			 
		}
		class PanelBG extends JPanel{
			
			
			private Color color1 = new Color(0, 45, 98);
			private Color color2 = new Color(124, 185, 232);
			
			public PanelBG(){
				
				this.setPreferredSize(new Dimension(420,420));
			}
			
			public void paint(Graphics g) {
				super.paint(g);
				
				Graphics2D g2D = (Graphics2D) g;
				
				int h = getHeight();
				int w = getWidth();
				
				GradientPaint gp = new GradientPaint(0,0,color1,0,h,color2);
				
				g2D.setPaint(gp);
				g2D.fillRect(0, 0, w, h);
				
				
			}
			
			

		}
		
		
		

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
}
	 
	 
	 
	



	
