package javaTPCProject;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Project01_F {
	JTextField address;
	JLabel resAddress, resX, resY, jibunAddress;
	JLabel imageLabel;
	
	public void initGUI() {
		JFrame frm = new JFrame("Map View");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = frm.getContentPane();
		imageLabel = new JLabel("��������");
		JPanel pan = new JPanel();
		JLabel addressLbl = new JLabel("�ּ��Է�");
		address = new JTextField(50);
		JButton btn = new JButton("Ŭ��");
		pan.add(addressLbl);
		pan.add(address);
		pan.add(btn);
		btn.addActionListener(new NaverMap(this));		
		JPanel pan1=new JPanel();
		pan1.setLayout(new GridLayout(4, 1));
		resAddress = new JLabel("���θ�");
		jibunAddress = new JLabel("�����ּ�");
		resX = new JLabel("�浵");
		resY = new JLabel("����");
		pan1.add(resAddress);
		pan1.add(jibunAddress);
		pan1.add(resX);
		pan1.add(resY);
		c.add(BorderLayout.NORTH, pan);
		c.add(BorderLayout.CENTER, imageLabel);
		c.add(BorderLayout.SOUTH, pan1);
		frm.setSize(730,660);
		frm.setVisible(true);
	}
	public static void main(String[] args) {
        new Project01_F().initGUI();
	}
}