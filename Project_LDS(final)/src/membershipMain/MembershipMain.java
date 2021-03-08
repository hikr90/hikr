package membershipMain;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.Loader;
import main.Register;
import main.Writer;

public class MembershipMain {

	public MembershipMain() {

		Register rg = new Register();

		// 회원권 메인 메뉴 클릭시 생성되는 회원권 정보 창
		Frame mb = new Frame();
		mb.setLayout(null);
		mb.setBounds(600, 280, 500, 300);
		mb.setVisible(true);

		// 라벨 추가
		Label m_menu = new Label("원하시는 메뉴를 선택해주세요.");
		Font font = new Font("나눔고딕", Font.BOLD, 20);
		m_menu.setBounds(50, 100, 400, 50);

		m_menu.setFont(font);
		m_menu.setVisible(true);
		mb.add(m_menu);

		// 회원권 구매 버튼
		Button btn = new Button("회원권 구매");
		btn.setBounds(70, 190, 100, 50);

		// 회원권 종류 버튼
		Button btn1 = new Button("회원권 종류");
		btn1.setBounds(200, 190, 100, 50);

		// 회원권 취소 버튼
		Button btn2 = new Button("회원권 환불");
		btn2.setBounds(330, 190, 100, 50);

		// 버튼 추가
		mb.add(btn);
		mb.add(btn1);
		mb.add(btn2);

		// 버튼 액션
		ActionListener act = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (e.getActionCommand()) {

				case "회원권 구매" :

					// 회원권 구매 창 생성
					Frame m_buy= new Frame();
					m_buy.setLayout(null);
					m_buy.setBounds(800, 300, 400, 300);
					m_buy.setVisible(true);

					// 라벨 생성
					Label main_label = new Label("회원 선택");
					Font fonts = new Font("나눔고딕", Font.BOLD, 20);
					main_label.setBounds(50, 50, 100, 30);
					main_label.setFont(fonts);
					m_buy.add(main_label);

					Label sub_label = new Label("충전하고자하는 회원 검색 / 회원명(전화번호 뒷4자리)");
					sub_label.setBounds(50, 100, 300, 50);
					m_buy.add(sub_label);

					// 유저명을 입력할 텍스트필드 생성
					TextField input = new TextField(10);
					input.setVisible(true);

					// 검색버튼
					Button btn_src = new Button("검색");

					//Panel생성
					Panel pa = new Panel();

					pa.add(input);
					pa.add(btn_src);
					pa.setBounds(50, 150, 180, 50);
					m_buy.add(pa,BorderLayout.SOUTH);


					// 검색 버튼이 클릭된 경우
					btn_src.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							try {

								Loader l = new Loader(input.getText());


								if(input.getText().equalsIgnoreCase(l.getRegister().getName()+"("+l.getRegister().getPhone().substring(7, 11)+")")) {
									Frame m_select = new Frame();
									m_select.setBounds(700, 400, 400, 400);
									m_select.setVisible(true);

									// 회원 정보를 알려주는 라벨
									Label show_info = new Label(l.getRegister().getName()+"님의 정보");
									show_info.setFont(font);
									show_info.setBounds(0, 0, 30, 30);
									m_select.add(show_info,BorderLayout.NORTH);


									// 중간에 위치할 텍스트 에어리어
									TextArea ta_info = new TextArea();
									ta_info.setEditable(false);
									ta_info.setBounds(0, 0, 300, 300);
									m_select.add(ta_info,BorderLayout.CENTER);

									ta_info.append("성함 : "+l.getRegister().getName()+"\n");
									ta_info.append("휴대폰 : "+l.getRegister().getPhone()+"\n");
									ta_info.append("회원권 잔액 : "+l.getRegister().getM_price()+"\n");
									ta_info.append("등급 : "+l.getRegister().getM_rating()+"\n");


									// 충전을 원하는 버튼과 라벨
									Panel m_charge = new Panel();
									m_select.add(m_charge,BorderLayout.SOUTH);

									// 충전 버튼
									Button btn1 = new Button("5만원 충전");
									Button btn2 = new Button("10만원 충전");
									Button btn3 = new Button("15만원 충전");
									Button btn4 = new Button("20만원 충전");

									m_charge.add(btn1);
									m_charge.add(btn2);
									m_charge.add(btn3);
									m_charge.add(btn4);

									// 각 버튼 금액 별로 동작
									ActionListener act = new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent arg0) {

											switch (arg0.getActionCommand()) {
											case "5만원 충전":
												int result1 = JOptionPane.showConfirmDialog(null, "충전금액 5만원, 충전하시겠습니까?", "충전 안내",JOptionPane.YES_NO_OPTION);

												if(result1==0) {
													rg.setName(l.getRegister().getName());
													rg.setPhone(l.getRegister().getPhone());
													rg.setM_rating(l.getRegister().getM_rating());
													rg.setM_price(l.getRegister().getM_price()+50000);
													Writer w = new Writer(rg);

													JOptionPane.showMessageDialog(null, "충전 완료");

													// 충전이 완료되면 종료
													m_select.dispose();
													m_buy.dispose();

												}

												break;

											case "10만원 충전":
												int result2 = JOptionPane.showConfirmDialog(null, "충전금액 10만원, 충전하시겠습니까?", "충전 안내",JOptionPane.YES_NO_OPTION);

												if(result2==0) {
													rg.setName(l.getRegister().getName());
													rg.setPhone(l.getRegister().getPhone());
													rg.setM_price(l.getRegister().getM_price()+100000);
													rg.setM_rating(l.getRegister().getM_rating());

													Writer w = new Writer(rg);



													JOptionPane.showMessageDialog(null, "충전 완료");

													// 충전이 완료되면 종료
													m_select.dispose();
													m_buy.dispose();

												}

												break;

											case "15만원 충전":
												int result3 = JOptionPane.showConfirmDialog(null, "충전금액 15만원, 충전하시겠습니까?", "충전 안내",JOptionPane.YES_NO_OPTION);

												if(result3==0) {
													rg.setName(l.getRegister().getName());
													rg.setPhone(l.getRegister().getPhone());
													rg.setM_rating(l.getRegister().getM_rating());
													rg.setM_price(l.getRegister().getM_price()+150000);
													Writer w = new Writer(rg);

													JOptionPane.showMessageDialog(null, "충전 완료");

													// 충전이 완료되면 종료
													m_select.dispose();
													m_buy.dispose();

												}

												break;

											case "20만원 충전":
												int result4 = JOptionPane.showConfirmDialog(null, "충전금액 20만원, 충전하시겠습니까?", "충전 안내",JOptionPane.YES_NO_OPTION);

												if(result4==0) {
													rg.setName(l.getRegister().getName());
													rg.setPhone(l.getRegister().getPhone());
													rg.setM_rating(l.getRegister().getM_rating());
													rg.setM_price(l.getRegister().getM_price()+200000);
													Writer w = new Writer(rg);

													JOptionPane.showMessageDialog(null, "충전 완료");

													// 충전이 완료되면 종료
													m_select.dispose();
													m_buy.dispose();

												}

												break;

											}
										}
									};

									btn1.addActionListener(act);
									btn2.addActionListener(act);
									btn3.addActionListener(act);
									btn4.addActionListener(act);


									// 회원 정보창 우상단 이벤트 관리자
									m_select.addWindowListener(new WindowAdapter() {
										public void windowClosing(WindowEvent e) {
											m_select.dispose();
										}
									});

								}


							} catch (Exception e2) {
								// TODO: handle exception
								JOptionPane.showMessageDialog(m_buy, "존재하지 않는 회원입니다.");
							}

						}
					});

					//회원권 구매 우상단 이벤트 클래스
					m_buy.addWindowListener( new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							m_buy.dispose();
						};
					});

					break;

				case "회원권 종류" :
					// 민재
					JFrame mb1 = new JFrame("no image");
					mb1.setBounds(450,160, 691, 638);

					//이미지
					ImageIcon image = new ImageIcon("C://menu.png");
					JLabel imagelabel = new JLabel(image); 
					
					//회원권 종류 창 우상단 이벤트
					mb1.addWindowListener( new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							mb1.dispose();
						}
					});
					
					mb1.add(imagelabel);
					mb1.setVisible(true);
					
					break;


				case "회원권 환불" :

					// 환불 정보창
					Frame m_refund= new Frame();
					m_refund.setLayout(null);
					m_refund.setBounds(800, 300, 400, 300);
					m_refund.setVisible(true);

					// 라벨 생성
					Label refund_label = new Label("회원 선택");
					refund_label.setBounds(50, 50, 100, 30);
					refund_label.setFont(font);
					m_refund.add(refund_label);

					Label sub_refund = new Label("취소하고자하는 회원 검색 / 회원명(전화번호 뒷4자리)");
					sub_refund.setBounds(50, 100, 300, 50);
					m_refund.add(sub_refund);

					// 유저명을 입력할 텍스트필드 생성
					TextField input2 = new TextField(10);
					input2.setVisible(true);

					// 검색버튼
					Button btn_src2 = new Button("검색");

					//Panel생성
					Panel pa2 = new Panel();

					pa2.add(input2);
					pa2.add(btn_src2);
					pa2.setBounds(50, 150, 180, 50);
					m_refund.add(pa2,BorderLayout.SOUTH);

					// 검색이 입력된 경우
					btn_src2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							Loader l = new Loader(input2.getText());

							try {
								// 이름이 같으면 로드
								if(input2.getText().equalsIgnoreCase(l.getRegister().getName()+"("+l.getRegister().getPhone().substring(7, 11)+")")) {

									// 정보창
									Frame m_select2 = new Frame();
									m_select2.setBounds(700, 400, 400, 400);
									m_select2.setVisible(true);

									// 회원 정보를 알려주는 라벨
									Label show_info = new Label(l.getRegister().getName()+"님의 정보");
									show_info.setFont(font);
									show_info.setBounds(0, 0, 30, 30);
									m_select2.add(show_info,BorderLayout.NORTH);


									// 중간에 위치할 텍스트 에어리어
									TextArea ta_info = new TextArea();
									ta_info.setEditable(false);
									ta_info.setBounds(0, 0, 300, 300);
									m_select2.add(ta_info,BorderLayout.CENTER);

									ta_info.append("성함 : "+l.getRegister().getName()+"\n");
									ta_info.append("휴대폰 : "+l.getRegister().getPhone()+"\n");
									ta_info.append("회원권 잔액 : "+l.getRegister().getM_price()+"\n");
									ta_info.append("등급 : "+l.getRegister().getM_rating()+"\n");

									// 환불 / 취소 버튼
									Panel p3 = new Panel();
									Button btn_refund = new Button("환불");
									Button btn_cancel = new Button("취소");
									p3.add(btn_refund);
									p3.add(btn_cancel);
									m_select2.add(p3,BorderLayout.SOUTH);

									// 환불 버튼을 누른 경우
									btn_refund.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											int result5 = JOptionPane.showConfirmDialog(null, l.getRegister().getName()+"님의 금액을 환불하시겠습니까?", "환불 안내", JOptionPane.YES_NO_OPTION);

											if(l.getRegister().getM_price()==0) {
												
												if(result5==0) {
													JOptionPane.showMessageDialog(null, "잔액이 없습니다.");	
													m_select2.dispose();
													m_refund.dispose();
												}else {
													m_select2.dispose();
													m_refund.dispose();
												}

											}else {
												if(result5==0) {
													// (중요!)회원권을 5만원 충전하고,  1만원이 남았을 때, 10만원 충전을 했는데 10만원을 환불하고 싶으면 1만원이 남아야하는데 아래식은 그냥 0으로 전액 환불
													rg.setName(l.getRegister().getName());
													rg.setPhone(l.getRegister().getPhone());
													rg.setM_rating(l.getRegister().getM_rating());
													rg.setM_price(0);
													Writer w = new Writer(rg);

													JOptionPane.showMessageDialog(null, "환불 완료");

													// 환불이 완료되면 종료
													m_select2.dispose();		
													m_refund.dispose();

												}


											}
										}
									});


									// 취소 버튼을 누른 경우
									btn_cancel.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											m_select2.dispose();											
										}
									});


									// 환불 정보창 우상단 이벤트 감지자
									m_select2.addWindowListener(new WindowAdapter() {
										@Override
										public void windowClosing(WindowEvent e) {
											m_select2.dispose();
										}
									});

								}



							} catch (Exception e2) {
								// TODO: handle exception
								e2.printStackTrace();
								JOptionPane.showMessageDialog(null, "존재하지 않는 회원입니다.");
							}



						}
					});
					
					// 회원권 구매창 우상단 감지자
					m_refund.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							m_refund.dispose();
						}
					});
					

					break;

				}
			}

		};

		btn.addActionListener(act); 
		btn1.addActionListener(act);
		btn2.addActionListener(act);




		mb.setVisible(true);

		// 메인창 이벤트 감지자
		mb.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mb.dispose();
			}
		});





	}
}
