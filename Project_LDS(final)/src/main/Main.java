package main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import hairMenu.HairMenu;
import memberReg.MemberReg;
import memberSrc.MemberSrc;
import membershipMain.MembershipMain;


public class Main {

	public static void main(String[] args) {
		
		Register rg = new Register();
		
		// Frame 생성
		Frame main = new Frame("회원 관리 프로그램");
		main.setLayout(null);
		main.setBounds(500, 200, 720, 390);
		
		ImageIcon img = new ImageIcon("메인.png");
		JLabel jl = new JLabel(img);
		jl.setBounds(0, 0, 720, 390);
		
		Button btn1 = new Button("회원 등록"); Button btn2 = new Button("회원 조회");
		Button btn3 = new Button("회원권"); Button btn4 = new Button("제품 선택");
		Button btn6 = new Button("나가기");

		// Lable추가
		JLabel main_label = new JLabel("회원 관리 시스템");
		Font font = new Font("나눔고딕", Font.BOLD, 35);
		main_label.setBounds(240,70,330,60);
		main_label.setFont(font);
		main_label.setForeground(Color.WHITE);
		main_label.setOpaque(false);
		main_label.setBackground(Color.WHITE);
		main.add(main_label);

		// 버튼 추가
		main.add(btn1); main.add(btn2);
		main.add(btn3); main.add(btn4);
		main.add(btn6);

		btn1.setBounds(130, 300, 60, 30); btn2.setBounds(230, 300, 60, 30);
		btn3.setBounds(330, 300, 60, 30); btn4.setBounds(430, 300, 60, 30);
		btn6.setBounds(530, 300, 60, 30);

		// 각 버튼 이벤트 감지자
		ActionListener act = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (e.getActionCommand()) {

				// 회원 등록
				case "회원 등록":

					MemberReg mr = new MemberReg();


					break;

					// 회원 조회
				case "회원 조회" :

					MemberSrc ms = new MemberSrc();
					
					break;
					
				// 회원권 선택시 이벤트 감지자
				case "회원권": 
					MembershipMain mm = new MembershipMain();
					break;
					
				case "제품 선택" :
					
					// 생성자 실행
					HairMenu hair = new HairMenu();
					
					
					break;
				
				} // switch 

			} // ActionPerformed 오버라이딩
		}; // 메인의 버튼 이벤트 감시자

		btn1.addActionListener(act); btn2.addActionListener(act);
		btn3.addActionListener(act); btn4.addActionListener(act);



		// 나가기 버튼 이벤트 감지자
		btn6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});





		// main 창 우상단 관리자
		main.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		main.add(jl);
		main.setVisible(true);
	}
}
