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

		// ȸ���� ���� �޴� Ŭ���� �����Ǵ� ȸ���� ���� â
		Frame mb = new Frame();
		mb.setLayout(null);
		mb.setBounds(600, 280, 500, 300);
		mb.setVisible(true);

		// �� �߰�
		Label m_menu = new Label("���Ͻô� �޴��� �������ּ���.");
		Font font = new Font("�������", Font.BOLD, 20);
		m_menu.setBounds(50, 100, 400, 50);

		m_menu.setFont(font);
		m_menu.setVisible(true);
		mb.add(m_menu);

		// ȸ���� ���� ��ư
		Button btn = new Button("ȸ���� ����");
		btn.setBounds(70, 190, 100, 50);

		// ȸ���� ���� ��ư
		Button btn1 = new Button("ȸ���� ����");
		btn1.setBounds(200, 190, 100, 50);

		// ȸ���� ��� ��ư
		Button btn2 = new Button("ȸ���� ȯ��");
		btn2.setBounds(330, 190, 100, 50);

		// ��ư �߰�
		mb.add(btn);
		mb.add(btn1);
		mb.add(btn2);

		// ��ư �׼�
		ActionListener act = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (e.getActionCommand()) {

				case "ȸ���� ����" :

					// ȸ���� ���� â ����
					Frame m_buy= new Frame();
					m_buy.setLayout(null);
					m_buy.setBounds(800, 300, 400, 300);
					m_buy.setVisible(true);

					// �� ����
					Label main_label = new Label("ȸ�� ����");
					Font fonts = new Font("�������", Font.BOLD, 20);
					main_label.setBounds(50, 50, 100, 30);
					main_label.setFont(fonts);
					m_buy.add(main_label);

					Label sub_label = new Label("�����ϰ����ϴ� ȸ�� �˻� / ȸ����(��ȭ��ȣ ��4�ڸ�)");
					sub_label.setBounds(50, 100, 300, 50);
					m_buy.add(sub_label);

					// �������� �Է��� �ؽ�Ʈ�ʵ� ����
					TextField input = new TextField(10);
					input.setVisible(true);

					// �˻���ư
					Button btn_src = new Button("�˻�");

					//Panel����
					Panel pa = new Panel();

					pa.add(input);
					pa.add(btn_src);
					pa.setBounds(50, 150, 180, 50);
					m_buy.add(pa,BorderLayout.SOUTH);


					// �˻� ��ư�� Ŭ���� ���
					btn_src.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							try {

								Loader l = new Loader(input.getText());


								if(input.getText().equalsIgnoreCase(l.getRegister().getName()+"("+l.getRegister().getPhone().substring(7, 11)+")")) {
									Frame m_select = new Frame();
									m_select.setBounds(700, 400, 400, 400);
									m_select.setVisible(true);

									// ȸ�� ������ �˷��ִ� ��
									Label show_info = new Label(l.getRegister().getName()+"���� ����");
									show_info.setFont(font);
									show_info.setBounds(0, 0, 30, 30);
									m_select.add(show_info,BorderLayout.NORTH);


									// �߰��� ��ġ�� �ؽ�Ʈ �����
									TextArea ta_info = new TextArea();
									ta_info.setEditable(false);
									ta_info.setBounds(0, 0, 300, 300);
									m_select.add(ta_info,BorderLayout.CENTER);

									ta_info.append("���� : "+l.getRegister().getName()+"\n");
									ta_info.append("�޴��� : "+l.getRegister().getPhone()+"\n");
									ta_info.append("ȸ���� �ܾ� : "+l.getRegister().getM_price()+"\n");
									ta_info.append("��� : "+l.getRegister().getM_rating()+"\n");


									// ������ ���ϴ� ��ư�� ��
									Panel m_charge = new Panel();
									m_select.add(m_charge,BorderLayout.SOUTH);

									// ���� ��ư
									Button btn1 = new Button("5���� ����");
									Button btn2 = new Button("10���� ����");
									Button btn3 = new Button("15���� ����");
									Button btn4 = new Button("20���� ����");

									m_charge.add(btn1);
									m_charge.add(btn2);
									m_charge.add(btn3);
									m_charge.add(btn4);

									// �� ��ư �ݾ� ���� ����
									ActionListener act = new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent arg0) {

											switch (arg0.getActionCommand()) {
											case "5���� ����":
												int result1 = JOptionPane.showConfirmDialog(null, "�����ݾ� 5����, �����Ͻðڽ��ϱ�?", "���� �ȳ�",JOptionPane.YES_NO_OPTION);

												if(result1==0) {
													rg.setName(l.getRegister().getName());
													rg.setPhone(l.getRegister().getPhone());
													rg.setM_rating(l.getRegister().getM_rating());
													rg.setM_price(l.getRegister().getM_price()+50000);
													Writer w = new Writer(rg);

													JOptionPane.showMessageDialog(null, "���� �Ϸ�");

													// ������ �Ϸ�Ǹ� ����
													m_select.dispose();
													m_buy.dispose();

												}

												break;

											case "10���� ����":
												int result2 = JOptionPane.showConfirmDialog(null, "�����ݾ� 10����, �����Ͻðڽ��ϱ�?", "���� �ȳ�",JOptionPane.YES_NO_OPTION);

												if(result2==0) {
													rg.setName(l.getRegister().getName());
													rg.setPhone(l.getRegister().getPhone());
													rg.setM_price(l.getRegister().getM_price()+100000);
													rg.setM_rating(l.getRegister().getM_rating());

													Writer w = new Writer(rg);



													JOptionPane.showMessageDialog(null, "���� �Ϸ�");

													// ������ �Ϸ�Ǹ� ����
													m_select.dispose();
													m_buy.dispose();

												}

												break;

											case "15���� ����":
												int result3 = JOptionPane.showConfirmDialog(null, "�����ݾ� 15����, �����Ͻðڽ��ϱ�?", "���� �ȳ�",JOptionPane.YES_NO_OPTION);

												if(result3==0) {
													rg.setName(l.getRegister().getName());
													rg.setPhone(l.getRegister().getPhone());
													rg.setM_rating(l.getRegister().getM_rating());
													rg.setM_price(l.getRegister().getM_price()+150000);
													Writer w = new Writer(rg);

													JOptionPane.showMessageDialog(null, "���� �Ϸ�");

													// ������ �Ϸ�Ǹ� ����
													m_select.dispose();
													m_buy.dispose();

												}

												break;

											case "20���� ����":
												int result4 = JOptionPane.showConfirmDialog(null, "�����ݾ� 20����, �����Ͻðڽ��ϱ�?", "���� �ȳ�",JOptionPane.YES_NO_OPTION);

												if(result4==0) {
													rg.setName(l.getRegister().getName());
													rg.setPhone(l.getRegister().getPhone());
													rg.setM_rating(l.getRegister().getM_rating());
													rg.setM_price(l.getRegister().getM_price()+200000);
													Writer w = new Writer(rg);

													JOptionPane.showMessageDialog(null, "���� �Ϸ�");

													// ������ �Ϸ�Ǹ� ����
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


									// ȸ�� ����â ���� �̺�Ʈ ������
									m_select.addWindowListener(new WindowAdapter() {
										public void windowClosing(WindowEvent e) {
											m_select.dispose();
										}
									});

								}


							} catch (Exception e2) {
								// TODO: handle exception
								JOptionPane.showMessageDialog(m_buy, "�������� �ʴ� ȸ���Դϴ�.");
							}

						}
					});

					//ȸ���� ���� ���� �̺�Ʈ Ŭ����
					m_buy.addWindowListener( new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							m_buy.dispose();
						};
					});

					break;

				case "ȸ���� ����" :
					// ����
					JFrame mb1 = new JFrame("no image");
					mb1.setBounds(450,160, 691, 638);

					//�̹���
					ImageIcon image = new ImageIcon("C://menu.png");
					JLabel imagelabel = new JLabel(image); 
					
					//ȸ���� ���� â ���� �̺�Ʈ
					mb1.addWindowListener( new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							mb1.dispose();
						}
					});
					
					mb1.add(imagelabel);
					mb1.setVisible(true);
					
					break;


				case "ȸ���� ȯ��" :

					// ȯ�� ����â
					Frame m_refund= new Frame();
					m_refund.setLayout(null);
					m_refund.setBounds(800, 300, 400, 300);
					m_refund.setVisible(true);

					// �� ����
					Label refund_label = new Label("ȸ�� ����");
					refund_label.setBounds(50, 50, 100, 30);
					refund_label.setFont(font);
					m_refund.add(refund_label);

					Label sub_refund = new Label("����ϰ����ϴ� ȸ�� �˻� / ȸ����(��ȭ��ȣ ��4�ڸ�)");
					sub_refund.setBounds(50, 100, 300, 50);
					m_refund.add(sub_refund);

					// �������� �Է��� �ؽ�Ʈ�ʵ� ����
					TextField input2 = new TextField(10);
					input2.setVisible(true);

					// �˻���ư
					Button btn_src2 = new Button("�˻�");

					//Panel����
					Panel pa2 = new Panel();

					pa2.add(input2);
					pa2.add(btn_src2);
					pa2.setBounds(50, 150, 180, 50);
					m_refund.add(pa2,BorderLayout.SOUTH);

					// �˻��� �Էµ� ���
					btn_src2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							Loader l = new Loader(input2.getText());

							try {
								// �̸��� ������ �ε�
								if(input2.getText().equalsIgnoreCase(l.getRegister().getName()+"("+l.getRegister().getPhone().substring(7, 11)+")")) {

									// ����â
									Frame m_select2 = new Frame();
									m_select2.setBounds(700, 400, 400, 400);
									m_select2.setVisible(true);

									// ȸ�� ������ �˷��ִ� ��
									Label show_info = new Label(l.getRegister().getName()+"���� ����");
									show_info.setFont(font);
									show_info.setBounds(0, 0, 30, 30);
									m_select2.add(show_info,BorderLayout.NORTH);


									// �߰��� ��ġ�� �ؽ�Ʈ �����
									TextArea ta_info = new TextArea();
									ta_info.setEditable(false);
									ta_info.setBounds(0, 0, 300, 300);
									m_select2.add(ta_info,BorderLayout.CENTER);

									ta_info.append("���� : "+l.getRegister().getName()+"\n");
									ta_info.append("�޴��� : "+l.getRegister().getPhone()+"\n");
									ta_info.append("ȸ���� �ܾ� : "+l.getRegister().getM_price()+"\n");
									ta_info.append("��� : "+l.getRegister().getM_rating()+"\n");

									// ȯ�� / ��� ��ư
									Panel p3 = new Panel();
									Button btn_refund = new Button("ȯ��");
									Button btn_cancel = new Button("���");
									p3.add(btn_refund);
									p3.add(btn_cancel);
									m_select2.add(p3,BorderLayout.SOUTH);

									// ȯ�� ��ư�� ���� ���
									btn_refund.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											int result5 = JOptionPane.showConfirmDialog(null, l.getRegister().getName()+"���� �ݾ��� ȯ���Ͻðڽ��ϱ�?", "ȯ�� �ȳ�", JOptionPane.YES_NO_OPTION);

											if(l.getRegister().getM_price()==0) {
												
												if(result5==0) {
													JOptionPane.showMessageDialog(null, "�ܾ��� �����ϴ�.");	
													m_select2.dispose();
													m_refund.dispose();
												}else {
													m_select2.dispose();
													m_refund.dispose();
												}

											}else {
												if(result5==0) {
													// (�߿�!)ȸ������ 5���� �����ϰ�,  1������ ������ ��, 10���� ������ �ߴµ� 10������ ȯ���ϰ� ������ 1������ ���ƾ��ϴµ� �Ʒ����� �׳� 0���� ���� ȯ��
													rg.setName(l.getRegister().getName());
													rg.setPhone(l.getRegister().getPhone());
													rg.setM_rating(l.getRegister().getM_rating());
													rg.setM_price(0);
													Writer w = new Writer(rg);

													JOptionPane.showMessageDialog(null, "ȯ�� �Ϸ�");

													// ȯ���� �Ϸ�Ǹ� ����
													m_select2.dispose();		
													m_refund.dispose();

												}


											}
										}
									});


									// ��� ��ư�� ���� ���
									btn_cancel.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											m_select2.dispose();											
										}
									});


									// ȯ�� ����â ���� �̺�Ʈ ������
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
								JOptionPane.showMessageDialog(null, "�������� �ʴ� ȸ���Դϴ�.");
							}



						}
					});
					
					// ȸ���� ����â ���� ������
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

		// ����â �̺�Ʈ ������
		mb.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mb.dispose();
			}
		});





	}
}
