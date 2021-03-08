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

		// 회원등록 창 생성
		Frame cm_reg = new Frame("회원 등록");
		cm_reg.setLayout(null);
		cm_reg.setBounds(600, 300, 400, 400);
		cm_reg.setVisible(true);

		//회원 등록 입력창 생성
		Label name_label = new Label("아이디(이름)");
		Font font_name = new Font("", Font.BOLD, 13);
		name_label.setBounds(60, 70, 100, 30);
		name_label.setFont(font_name);
		cm_reg.add(name_label);
		// 이름
		TextField tf_name = new TextField();
		tf_name.setBounds(60, 100, 200, 20);

		// 전화번호
		Label phone_label = new Label("전화번호(\"-\"제외)");
		Font font_phone = new Font("",Font.BOLD,13);
		phone_label.setBounds(60, 130, 140, 30);
		phone_label.setFont(font_phone);
		cm_reg.add(phone_label);
		TextField tf_phone = new TextField();
		tf_phone.setBounds(60, 160, 200, 20);

		// 회원 등록 라벨
		Label res_label = new Label("회원 정보를 입력해주세요.");
		res_label.setBounds(30, 30, 150, 50);
		cm_reg.add(res_label);

		cm_reg.add(tf_name);
		cm_reg.add(tf_phone);
		

		// 저장 버튼
		Button btn_save = new Button("저장");
		btn_save.setBounds(300, 300, 50, 50);
		cm_reg.add(btn_save);
		btn_save.setEnabled(false);

		
		// 전화번호가 입력되지 않으면 저장 불가능하게 만드는 코드
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
		
		
		// 저장버튼 액션 관리자
		btn_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 저장 버튼을 눌렀을 때 액션
				try {
					
						rg.setName(tf_name.getText());
						rg.setPhone(tf_phone.getText());
						Writer w = new Writer(rg);
						JOptionPane.showMessageDialog(cm_reg, "등록 완료");
					
					
					// 등록 완료 후, 등록 창 종료
					cm_reg.dispose();
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(cm_reg, "등록 실패");								
				}
				
			}
		});



		// 회원 등록 창 우상단 관리자
		cm_reg.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cm_reg.dispose();
			}
		});

		
		
		
	}
}
