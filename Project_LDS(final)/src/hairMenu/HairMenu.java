package hairMenu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import main.Loader;
import main.Register;
import main.Writer;

public class HairMenu {

	public HairMenu() {

		Register rg = new Register();
		Price p = new Price();
		
		Frame product = new Frame("메뉴");
		product.setBounds(600, 100, 800, 600);
		product.setLayout(null);
		product.setVisible(true);
		product.setResizable(false);

		// 제품 창 우상단 관리자
		product.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				product.dispose();
			}
		});

		// 제품 버튼
		Button m1 = new Button("컷트");
		Button m2 = new Button("파마");
		Button m3 = new Button("매직");
		Button m4 = new Button("볼륨매직");
		Button m5 = new Button("염색");
		Button m6 = new Button("클리닉");

		// 버튼 배치
		m1.setBounds(60, 100, 100, 100); m2.setBounds(190, 100, 100, 100);
		m3.setBounds(60, 220, 100, 100); m4.setBounds(190, 220, 100, 100);
		m5.setBounds(60, 340, 100, 100); m6.setBounds(190, 340, 100, 100);
		product.add(m1); product.add(m2); product.add(m3); product.add(m4);
		product.add(m5); product.add(m6);

		// 제품버튼 클릭시 선택상품을 보여줄 textArea
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 18);
		TextArea menuT = new TextArea();
		menuT.setFont(f);
		menuT.setEditable(false);
		menuT.setBounds(300, 100, 300, 340);
		product.add(menuT);

		// 회원 조회 버튼
		Button mb = new Button("회원검색");
		mb.setBounds(610, 140, 100, 50);
		product.add(mb);

		TextField m_tf = new TextField();
		m_tf.setBounds(610, 100, 100, 30);
		Font tf_f = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
		m_tf.setFont(tf_f);
		product.add(m_tf);

		Label menu_l = new Label("회원조회후 제품을 선택해 주세요");
		menu_l.setBounds(50, 60, 300, 40);
		menu_l.setFont(f);
		product.add(menu_l);

		
		
		
		// 제품버튼 감지자
		m1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuT.append("컷트 : " + 18000 + "\n");
				final int PRICE = 18000;
				p.setPrice(PRICE);
			}
		});

		m2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				menuT.append("파마 : " + 100000 + "\n");
				final int PRICE = 100000;
				p.setPrice(PRICE);
			}
		});

		m3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				menuT.append("매직 : " + 80000 + "\n");
				final int PRICE = 80000;
				p.setPrice(PRICE);
			}
		});

		m4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				menuT.append("볼륨매직 : " + 100000 + "\n");
				final int PRICE = 100000;
				p.setPrice(PRICE);
			}
		});

		m5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				menuT.append("염색 : " + 80000 + "\n");
				final int PRICE = 80000;
				p.setPrice(PRICE);
			}
		});

		m6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				menuT.append("클리닉 : " + 70000 + "\n");
				final int PRICE = 70000;
				p.setPrice(PRICE);
			}
		});

		// 제품 버튼 선택시 결제 여부를 묻는 창
		Button payment = new Button("결제");
		payment.setBounds(610, 340, 100, 100);
		product.add(payment);
		
		// 회원조회완료후 결제 버튼 활성화하기위한 작업
		payment.setEnabled(false);
		
		//결제 버튼 감지자
		payment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frame payment = new Frame("결제");
				payment.setBounds(1100, 350, 350, 200);
				payment.setLayout(null);
				payment.setVisible(true);
				payment.setResizable(false);

				Label pmL = new Label("결제를 하시겠습니까?");
				pmL.setBounds(70, 65, 210, 30);
				Font f1 = new Font("나눔고딕", Font.BOLD, 20);
				pmL.setFont(f1);
				payment.add(pmL);

				Button pm = new Button("결제");
				Button exit1 = new Button("취소");
				pm.setBounds(70, 120, 80, 50);
				exit1.setBounds(190, 120, 80, 50);
				payment.add(pm);
				payment.add(exit1);

				pm.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						Loader l = new Loader(m_tf.getText());
						
						
						
						// 포인트가 - 값이 안나오게끔 했습니다.
						if((l.getRegister().getM_price() - p.getPrice()) < 0) {
							
							JOptionPane.showMessageDialog(null, "잔액이 부족합니다.");
							payment.dispose();
							product.dispose();

						}else {
							
							rg.setName(l.getRegister().getName());
							rg.setPhone(l.getRegister().getPhone());
							rg.setM_rating(l.getRegister().getM_rating());
							rg.setM_price(l.getRegister().getM_price() - p.getPrice());
							Writer w = new Writer(rg);

							menuT.append("------------------------------\n");
							menuT.append("현재 회원 금액 : " + l.getRegister().getM_price() + "\n");
							menuT.append("총결제금액 : " + p.getPrice() + "\n");
							menuT.append("결제 후 잔액 : " + (l.getRegister().getM_price() - p.getPrice()) + "\n");
							
							p.setPrice(-p.getPrice());
							
							// 결제 버튼클릭시 영수증 출력 프레임
							Frame receipt = new Frame("영수증");
							receipt.setBounds(1100, 350, 350, 200);
							receipt.setLayout(null);
							receipt.setVisible(true);
							receipt.setResizable(false);
							
							payment.dispose();
							
							// 영수증 창 우상단 감지자
							receipt.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosing(WindowEvent e) {
									receipt.dispose();
								}
							});
							
							Label re = new Label("영수증 Y/N");
							Font font1 = new Font("나눔고딕", Font.BOLD, 20);
							re.setBounds(120, 60, 300, 60);
							re.setFont(font1);
							receipt.add(re);
							
							Button outP = new Button("출력");
							Button ex = new Button("취소");
							outP.setBounds(60, 120, 100, 50);
							ex.setBounds(190, 120, 100, 50);
							receipt.add(outP);
							receipt.add(ex);
							
							// 영수증 출력창의 버튼 감지자
							outP.addActionListener(new ActionListener() {
								
								FileWriter fw = null;
								BufferedWriter bw = null;
								
								@Override
								public void actionPerformed(ActionEvent e) {
									
									String msg = menuT.getText();
									
									try {
										FileDialog fd = new FileDialog(receipt, "저장");
										fd.setVisible(true);
										
										String path = fd.getDirectory() + fd.getFile();
										
										fw = new FileWriter(path);
										bw = new BufferedWriter(fw);
										bw.write(msg);
										
										if (fd.getFile() != null) {
											JOptionPane.showMessageDialog(receipt, "출력성공");
										}
										
									} catch (Exception e2) {
									} finally {
										try {
											bw.close();
											fw.close();
										} catch (Exception e3) {
										}
									}
									menuT.setText("");
									receipt.dispose();
								}
							});// outP
							
							// 영수증 출력창 취소버튼 감지자
							ex.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									receipt.dispose();
									menuT.setText("");
								}
							});// ex
						}
						

					}
				});// 결제확인 버튼 감지자

				exit1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						payment.dispose();
					}
				});

				payment.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						payment.dispose();
					};
				});

			}// 결제버튼 감지자
		});
		
		// 회원검색 버튼 감지자
				mb.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Loader l = new Loader(m_tf.getText());
							menuT.append("성함 : " + l.getRegister().getName() + "\n");
							menuT.append("휴대폰 : " + l.getRegister().getPhone() + "\n");
							menuT.append("회원권 잔액 : " + l.getRegister().getM_price() + "\n");
							menuT.append("--------------------------\n");
							
							if(!m_tf.getText().trim().equalsIgnoreCase("")) {
								payment.setEnabled(true);
							}
							
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "회원 정보가 없습니다.");
						}
						
					}
				});
				
		
	}

}// HairMenu
