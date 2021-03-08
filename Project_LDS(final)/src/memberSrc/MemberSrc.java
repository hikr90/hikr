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
		
		// 회원 조회 창 생성
		Frame cm_find = new Frame("회원 조회");
		cm_find.setBounds(600, 300, 400, 400);
		cm_find.setVisible(true);

		// Pannel 생성
		Panel input = new Panel();

		
		TextField tf_ifm = new TextField("이름+(핸드폰 뒤4자리)");
		
		// 패널에 집어넣을 입력창과 입력버튼
		TextField tf_input = new TextField(tf_ifm.getText());
		Button btn_input = new Button("검색");

	
		input.add(tf_input);
		input.add(btn_input);
		cm_find.add(input,BorderLayout.NORTH);
		
		tf_input.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				tf_input.setText("");
				tf_input.requestFocus();
			}; {};
		});
		
		
		// 회원 조회 출력 영역
		TextArea ta_info = new TextArea("",0,0,TextArea.SCROLLBARS_NONE);
		ta_info.setEditable(false);
		ta_info.setBounds(10, 10, 600, 350);
		cm_find.add(ta_info,BorderLayout.CENTER);
		
		// 수정 및 삭제 버튼 영역
		Panel EditnSave = new Panel();

		Button btn_edit = new Button("수정");
		
		
		Button btn_del = new Button("나가기");
		
		// 나가기 버튼 눌렀을때 액션리스너
		btn_del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cm_find.dispose();
				
			}
		});

		EditnSave.add(btn_edit);
		EditnSave.add(btn_del);

		
		cm_find.add(EditnSave,BorderLayout.SOUTH);
		
		// 수정과 삭제 버튼은 검색 버튼이 클릭되어 값을 TextArea에 뿌렸을 때만 오픈
		btn_edit.setEnabled(false);
		btn_del.setEnabled(false);
		
		// 검색창 선택시, 이벤트 감지자
		// 여기에 입력창을 조회시의 메서드를 추가해주시면 됩니다.
		btn_input.addActionListener(new ActionListener() {
			
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Loader l = new Loader(tf_input.getText());
				
				// 존재하지 않는 name 검색시, 존재하지 않는다는 표현 출력
				try {
					ta_info.setText("성함 : "+l.getRegister().getName()+"\n핸드폰 : "
																+l.getRegister().getPhone()+"\n회원권 잔여 금액 : "
																+l.getRegister().getM_price()+"\n등급 : "
																+l.getRegister().getM_rating());
						
					
					// 검색 창에 값이 들어와서 검색이 된 후, 검색 버튼이 눌려지면 수정 및 삭제 버튼 활성화
					if(!tf_input.getText().trim().equalsIgnoreCase("")) {
						btn_edit.setEnabled(true);
						btn_del.setEnabled(true);
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(cm_find, "존재하지 않는 아이디입니다.");
				}
				
			}
		});

		// 수정 버튼 입력시 이벤트 감지자
		btn_edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Frame cm_edit = new Frame("회원 정보");
				cm_edit.setBounds(450, 400, 400, 250);
				cm_edit.setLayout(null);
				cm_edit.setVisible(true);
				
			
				
				Button btn1 = new Button("수정");
				Button btn2 = new Button("삭제");
				
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
				
				// 수정 버튼을 눌렀을 때 액션리스너
				btn1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						int result = JOptionPane.showConfirmDialog(cm_edit, "저장하시겠습니까?", "저장", JOptionPane.YES_NO_OPTION );
					
						if(result==0) {
							
							name=tf_1.getText();
							phone = tf_2.getText();
							rg.setName(name);
							rg.setPhone(phone);
							rg.setM_price(m_price);
							rg.setM_rating(m_rating);
							Writer w = new Writer(rg);
							Delete del = new Delete(tf_input.getText());
							
							
							JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
							cm_find.dispose();
							cm_edit.dispose();

						} else {
							cm_find.dispose();
							cm_edit.dispose();
						}
						
						
						
						
						
					}
				});
				// 삭제 버튼을 눌렀을 때 액션리스너
				btn2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						
						int result = JOptionPane.showConfirmDialog(cm_edit, "정말로 삭제하시겠습니까 ?","삭제",JOptionPane.YES_NO_OPTION);
						
						if(result==0) {
							
							Delete del = new Delete(tf_input.getText());
							JOptionPane.showMessageDialog(null, "삭제되었습니다.");
							cm_find.dispose();
							cm_edit.dispose();
						}else {
							cm_find.dispose();
							cm_edit.dispose();
						}
						
					}
				});
				
				// 정보 수정 수정창 우상단 이벤트 관리자
				cm_edit.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						cm_edit.dispose();
					};
				});
				

				
			}
		});
		


		// 회원 조희 창 우상단 관리자 
		cm_find.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				cm_find.dispose();
			}

		});					
	
	
	
	
	}
}
