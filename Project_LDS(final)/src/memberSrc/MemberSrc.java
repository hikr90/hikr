package memberSrc;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import main.Delete;
import main.Loader;
import main.Register;
import main.Writer;

public class MemberSrc {
	
	Register rg = new Register();
	static String name = "";
	static String phone = "";
	static int m_price = 0;
	static String m_rating = "";
	
	
	public MemberSrc() {
		
		// ȸ�� ��ȸ â ����
		Frame cm_find = new Frame("ȸ�� ��ȸ");
		cm_find.setBounds(600, 300, 400, 400);
		cm_find.setVisible(true);

		// Pannel ����
		Panel input = new Panel();

		
		TextField tf_ifm = new TextField("�̸�+(�ڵ��� ��4�ڸ�)");
		
		// �гο� ������� �Է�â�� �Է¹�ư
		TextField tf_input = new TextField(tf_ifm.getText());
		Button btn_input = new Button("�˻�");

	
		input.add(tf_input);
		input.add(btn_input);
		cm_find.add(input,BorderLayout.NORTH);
		
		tf_input.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				tf_input.setText("");
				tf_input.requestFocus();
			}; {};
		});
		
		
		// ȸ�� ��ȸ ��� ����
		TextArea ta_info = new TextArea("",0,0,TextArea.SCROLLBARS_NONE);
		ta_info.setEditable(false);
		ta_info.setBounds(10, 10, 600, 350);
		cm_find.add(ta_info,BorderLayout.CENTER);
		
		// ���� �� ���� ��ư ����
		Panel EditnSave = new Panel();

		Button btn_edit = new Button("����");
		
		
		Button btn_del = new Button("������");
		
		// ������ ��ư �������� �׼Ǹ�����
		btn_del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cm_find.dispose();
				
			}
		});

		EditnSave.add(btn_edit);
		EditnSave.add(btn_del);

		
		cm_find.add(EditnSave,BorderLayout.SOUTH);
		
		// ������ ���� ��ư�� �˻� ��ư�� Ŭ���Ǿ� ���� TextArea�� �ѷ��� ���� ����
		btn_edit.setEnabled(false);
		btn_del.setEnabled(false);
		
		// �˻�â ���ý�, �̺�Ʈ ������
		// ���⿡ �Է�â�� ��ȸ���� �޼��带 �߰����ֽø� �˴ϴ�.
		btn_input.addActionListener(new ActionListener() {
			
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Loader l = new Loader(tf_input.getText());
				
				// �������� �ʴ� name �˻���, �������� �ʴ´ٴ� ǥ�� ���
				try {
					ta_info.setText("���� : "+l.getRegister().getName()+"\n�ڵ��� : "
																+l.getRegister().getPhone()+"\nȸ���� �ܿ� �ݾ� : "
																+l.getRegister().getM_price()+"\n��� : "
																+l.getRegister().getM_rating());
						
					
					// �˻� â�� ���� ���ͼ� �˻��� �� ��, �˻� ��ư�� �������� ���� �� ���� ��ư Ȱ��ȭ
					if(!tf_input.getText().trim().equalsIgnoreCase("")) {
						btn_edit.setEnabled(true);
						btn_del.setEnabled(true);
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(cm_find, "�������� �ʴ� ���̵��Դϴ�.");
				}
				
			}
		});

		// ���� ��ư �Է½� �̺�Ʈ ������
		btn_edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Frame cm_edit = new Frame("ȸ�� ����");
				cm_edit.setBounds(450, 400, 400, 250);
				cm_edit.setLayout(null);
				cm_edit.setVisible(true);
				
			
				
				Button btn1 = new Button("����");
				Button btn2 = new Button("����");
				
				Panel p = new Panel();
				
				
				Loader loader = new Loader( tf_input.getText());
				
				
				TextField tf_1 = new TextField(loader.getRegister().getName());
				
				TextField tf_2 = new TextField(loader.getRegister().getPhone());
				
				
				tf_1.setBounds(60, 60, 200, 20);
				tf_2.setBounds(60, 100, 200, 20);
				
				p.add(btn1);
				p.add(btn2);
				cm_edit.add(p);
				p.setBounds(290, 210, 100, 30);
			
				
				cm_edit.add(tf_1);
				cm_edit.add(tf_2);
				
				// ���� ��ư�� ������ �� �׼Ǹ�����
				btn1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						int result = JOptionPane.showConfirmDialog(cm_edit, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION );
					
						if(result==0) {
							
							name=tf_1.getText();
							phone = tf_2.getText();
							rg.setName(name);
							rg.setPhone(phone);
							rg.setM_price(m_price);
							rg.setM_rating(m_rating);
							Writer w = new Writer(rg);
							Delete del = new Delete(tf_input.getText());
							
							
							JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
							cm_find.dispose();
							cm_edit.dispose();

						} else {
							cm_find.dispose();
							cm_edit.dispose();
						}
						
						
						
						
						
					}
				});
				// ���� ��ư�� ������ �� �׼Ǹ�����
				btn2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						
						int result = JOptionPane.showConfirmDialog(cm_edit, "������ �����Ͻðڽ��ϱ� ?","����",JOptionPane.YES_NO_OPTION);
						
						if(result==0) {
							
							Delete del = new Delete(tf_input.getText());
							JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
							cm_find.dispose();
							cm_edit.dispose();
						}else {
							cm_find.dispose();
							cm_edit.dispose();
						}
						
					}
				});
				
				// ���� ���� ����â ���� �̺�Ʈ ������
				cm_edit.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						cm_edit.dispose();
					};
				});
				

				
			}
		});
		


		// ȸ�� ���� â ���� ������ 
		cm_find.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				cm_find.dispose();
			}

		});					
	
	
	
	
	}
}
