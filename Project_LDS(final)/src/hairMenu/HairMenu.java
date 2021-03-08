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
		
		Frame product = new Frame("�޴�");
		product.setBounds(600, 100, 800, 600);
		product.setLayout(null);
		product.setVisible(true);
		product.setResizable(false);

		// ��ǰ â ���� ������
		product.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				product.dispose();
			}
		});

		// ��ǰ ��ư
		Button m1 = new Button("��Ʈ");
		Button m2 = new Button("�ĸ�");
		Button m3 = new Button("����");
		Button m4 = new Button("��������");
		Button m5 = new Button("����");
		Button m6 = new Button("Ŭ����");

		// ��ư ��ġ
		m1.setBounds(60, 100, 100, 100); m2.setBounds(190, 100, 100, 100);
		m3.setBounds(60, 220, 100, 100); m4.setBounds(190, 220, 100, 100);
		m5.setBounds(60, 340, 100, 100); m6.setBounds(190, 340, 100, 100);
		product.add(m1); product.add(m2); product.add(m3); product.add(m4);
		product.add(m5); product.add(m6);

		// ��ǰ��ư Ŭ���� ���û�ǰ�� ������ textArea
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 18);
		TextArea menuT = new TextArea();
		menuT.setFont(f);
		menuT.setEditable(false);
		menuT.setBounds(300, 100, 300, 340);
		product.add(menuT);

		// ȸ�� ��ȸ ��ư
		Button mb = new Button("ȸ���˻�");
		mb.setBounds(610, 140, 100, 50);
		product.add(mb);

		TextField m_tf = new TextField();
		m_tf.setBounds(610, 100, 100, 30);
		Font tf_f = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
		m_tf.setFont(tf_f);
		product.add(m_tf);

		Label menu_l = new Label("ȸ����ȸ�� ��ǰ�� ������ �ּ���");
		menu_l.setBounds(50, 60, 300, 40);
		menu_l.setFont(f);
		product.add(menu_l);

		
		
		
		// ��ǰ��ư ������
		m1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuT.append("��Ʈ : " + 18000 + "\n");
				final int PRICE = 18000;
				p.setPrice(PRICE);
			}
		});

		m2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				menuT.append("�ĸ� : " + 100000 + "\n");
				final int PRICE = 100000;
				p.setPrice(PRICE);
			}
		});

		m3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				menuT.append("���� : " + 80000 + "\n");
				final int PRICE = 80000;
				p.setPrice(PRICE);
			}
		});

		m4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				menuT.append("�������� : " + 100000 + "\n");
				final int PRICE = 100000;
				p.setPrice(PRICE);
			}
		});

		m5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				menuT.append("���� : " + 80000 + "\n");
				final int PRICE = 80000;
				p.setPrice(PRICE);
			}
		});

		m6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				menuT.append("Ŭ���� : " + 70000 + "\n");
				final int PRICE = 70000;
				p.setPrice(PRICE);
			}
		});

		// ��ǰ ��ư ���ý� ���� ���θ� ���� â
		Button payment = new Button("����");
		payment.setBounds(610, 340, 100, 100);
		product.add(payment);
		
		// ȸ����ȸ�Ϸ��� ���� ��ư Ȱ��ȭ�ϱ����� �۾�
		payment.setEnabled(false);
		
		//���� ��ư ������
		payment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frame payment = new Frame("����");
				payment.setBounds(1100, 350, 350, 200);
				payment.setLayout(null);
				payment.setVisible(true);
				payment.setResizable(false);

				Label pmL = new Label("������ �Ͻðڽ��ϱ�?");
				pmL.setBounds(70, 65, 210, 30);
				Font f1 = new Font("�������", Font.BOLD, 20);
				pmL.setFont(f1);
				payment.add(pmL);

				Button pm = new Button("����");
				Button exit1 = new Button("���");
				pm.setBounds(70, 120, 80, 50);
				exit1.setBounds(190, 120, 80, 50);
				payment.add(pm);
				payment.add(exit1);

				pm.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						Loader l = new Loader(m_tf.getText());
						
						
						
						// ����Ʈ�� - ���� �ȳ����Բ� �߽��ϴ�.
						if((l.getRegister().getM_price() - p.getPrice()) < 0) {
							
							JOptionPane.showMessageDialog(null, "�ܾ��� �����մϴ�.");
							payment.dispose();
							product.dispose();

						}else {
							
							rg.setName(l.getRegister().getName());
							rg.setPhone(l.getRegister().getPhone());
							rg.setM_rating(l.getRegister().getM_rating());
							rg.setM_price(l.getRegister().getM_price() - p.getPrice());
							Writer w = new Writer(rg);

							menuT.append("------------------------------\n");
							menuT.append("���� ȸ�� �ݾ� : " + l.getRegister().getM_price() + "\n");
							menuT.append("�Ѱ����ݾ� : " + p.getPrice() + "\n");
							menuT.append("���� �� �ܾ� : " + (l.getRegister().getM_price() - p.getPrice()) + "\n");
							
							p.setPrice(-p.getPrice());
							
							// ���� ��ưŬ���� ������ ��� ������
							Frame receipt = new Frame("������");
							receipt.setBounds(1100, 350, 350, 200);
							receipt.setLayout(null);
							receipt.setVisible(true);
							receipt.setResizable(false);
							
							payment.dispose();
							
							// ������ â ���� ������
							receipt.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosing(WindowEvent e) {
									receipt.dispose();
								}
							});
							
							Label re = new Label("������ Y/N");
							Font font1 = new Font("�������", Font.BOLD, 20);
							re.setBounds(120, 60, 300, 60);
							re.setFont(font1);
							receipt.add(re);
							
							Button outP = new Button("���");
							Button ex = new Button("���");
							outP.setBounds(60, 120, 100, 50);
							ex.setBounds(190, 120, 100, 50);
							receipt.add(outP);
							receipt.add(ex);
							
							// ������ ���â�� ��ư ������
							outP.addActionListener(new ActionListener() {
								
								FileWriter fw = null;
								BufferedWriter bw = null;
								
								@Override
								public void actionPerformed(ActionEvent e) {
									
									String msg = menuT.getText();
									
									try {
										FileDialog fd = new FileDialog(receipt, "����");
										fd.setVisible(true);
										
										String path = fd.getDirectory() + fd.getFile();
										
										fw = new FileWriter(path);
										bw = new BufferedWriter(fw);
										bw.write(msg);
										
										if (fd.getFile() != null) {
											JOptionPane.showMessageDialog(receipt, "��¼���");
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
							
							// ������ ���â ��ҹ�ư ������
							ex.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									receipt.dispose();
									menuT.setText("");
								}
							});// ex
						}
						

					}
				});// ����Ȯ�� ��ư ������

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

			}// ������ư ������
		});
		
		// ȸ���˻� ��ư ������
				mb.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Loader l = new Loader(m_tf.getText());
							menuT.append("���� : " + l.getRegister().getName() + "\n");
							menuT.append("�޴��� : " + l.getRegister().getPhone() + "\n");
							menuT.append("ȸ���� �ܾ� : " + l.getRegister().getM_price() + "\n");
							menuT.append("--------------------------\n");
							
							if(!m_tf.getText().trim().equalsIgnoreCase("")) {
								payment.setEnabled(true);
							}
							
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "ȸ�� ������ �����ϴ�.");
						}
						
					}
				});
				
		
	}

}// HairMenu
