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
		
		// Frame ����
		Frame main = new Frame("ȸ�� ���� ���α׷�");
		main.setLayout(null);
		main.setBounds(500, 200, 720, 390);
		
		ImageIcon img = new ImageIcon("����.png");
		JLabel jl = new JLabel(img);
		jl.setBounds(0, 0, 720, 390);
		
		Button btn1 = new Button("ȸ�� ���"); Button btn2 = new Button("ȸ�� ��ȸ");
		Button btn3 = new Button("ȸ����"); Button btn4 = new Button("��ǰ ����");
		Button btn6 = new Button("������");

		// Lable�߰�
		JLabel main_label = new JLabel("ȸ�� ���� �ý���");
		Font font = new Font("�������", Font.BOLD, 35);
		main_label.setBounds(240,70,330,60);
		main_label.setFont(font);
		main_label.setForeground(Color.WHITE);
		main_label.setOpaque(false);
		main_label.setBackground(Color.WHITE);
		main.add(main_label);

		// ��ư �߰�
		main.add(btn1); main.add(btn2);
		main.add(btn3); main.add(btn4);
		main.add(btn6);

		btn1.setBounds(130, 300, 60, 30); btn2.setBounds(230, 300, 60, 30);
		btn3.setBounds(330, 300, 60, 30); btn4.setBounds(430, 300, 60, 30);
		btn6.setBounds(530, 300, 60, 30);

		// �� ��ư �̺�Ʈ ������
		ActionListener act = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (e.getActionCommand()) {

				// ȸ�� ���
				case "ȸ�� ���":

					MemberReg mr = new MemberReg();


					break;

					// ȸ�� ��ȸ
				case "ȸ�� ��ȸ" :

					MemberSrc ms = new MemberSrc();
					
					break;
					
				// ȸ���� ���ý� �̺�Ʈ ������
				case "ȸ����": 
					MembershipMain mm = new MembershipMain();
					break;
					
				case "��ǰ ����" :
					
					// ������ ����
					HairMenu hair = new HairMenu();
					
					
					break;
				
				} // switch 

			} // ActionPerformed �������̵�
		}; // ������ ��ư �̺�Ʈ ������

		btn1.addActionListener(act); btn2.addActionListener(act);
		btn3.addActionListener(act); btn4.addActionListener(act);



		// ������ ��ư �̺�Ʈ ������
		btn6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});





		// main â ���� ������
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
