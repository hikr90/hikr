package memberReg;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import main.Register;
import main.Writer;

public class MemberReg {
	
	Register rg = new Register();
	
	public MemberReg() {

		// ȸ����� â ����
		Frame cm_reg = new Frame("ȸ�� ���");
		cm_reg.setLayout(null);
		cm_reg.setBounds(600, 300, 400, 400);
		cm_reg.setVisible(true);

		//ȸ�� ��� �Է�â ����
		Label name_label = new Label("���̵�(�̸�)");
		Font font_name = new Font("", Font.BOLD, 13);
		name_label.setBounds(60, 70, 100, 30);
		name_label.setFont(font_name);
		cm_reg.add(name_label);
		// �̸�
		TextField tf_name = new TextField();
		tf_name.setBounds(60, 100, 200, 20);

		// ��ȭ��ȣ
		Label phone_label = new Label("��ȭ��ȣ(\"-\"����)");
		Font font_phone = new Font("",Font.BOLD,13);
		phone_label.setBounds(60, 130, 140, 30);
		phone_label.setFont(font_phone);
		cm_reg.add(phone_label);
		TextField tf_phone = new TextField();
		tf_phone.setBounds(60, 160, 200, 20);

		// ȸ�� ��� ��
		Label res_label = new Label("ȸ�� ������ �Է����ּ���.");
		res_label.setBounds(30, 30, 150, 50);
		cm_reg.add(res_label);

		cm_reg.add(tf_name);
		cm_reg.add(tf_phone);
		

		// ���� ��ư
		Button btn_save = new Button("����");
		btn_save.setBounds(300, 300, 50, 50);
		cm_reg.add(btn_save);
		btn_save.setEnabled(false);

		
		// ��ȭ��ȣ�� �Էµ��� ������ ���� �Ұ����ϰ� ����� �ڵ�
		tf_phone.addTextListener(new TextListener() {
			
			@Override
			public void textValueChanged(TextEvent e) {
				if(tf_phone.getText().length()==11) {
					btn_save.setEnabled(true);
				}else {
					btn_save.setEnabled(false);
				}
				
				
			}
		});
		
		
		// �����ư �׼� ������
		btn_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ���� ��ư�� ������ �� �׼�
				try {
					
						rg.setName(tf_name.getText());
						rg.setPhone(tf_phone.getText());
						Writer w = new Writer(rg);
						JOptionPane.showMessageDialog(cm_reg, "��� �Ϸ�");
					
					
					// ��� �Ϸ� ��, ��� â ����
					cm_reg.dispose();
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(cm_reg, "��� ����");								
				}
				
			}
		});



		// ȸ�� ��� â ���� ������
		cm_reg.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cm_reg.dispose();
			}
		});

		
		
		
	}
}
