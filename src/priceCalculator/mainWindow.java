package priceCalculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class mainWindow extends JFrame {
	JPanel mainPanel;
	JButton cookBtn, minerBtn, farmerBtn, priceBtn;
	GridBagConstraints gbc;
	
	mainWindow() {
        InfoList infoList = InfoList.getInstance();
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        setTitle("란도의 기린 계산기");
		
		mainPanel = new JPanel();		
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setSize(400, 200);
		
		cookBtn = makeBtn("요리", 180, 150);
		cookBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new cookWindow();
				dispose();
			}
		});
		
		minerBtn = makeBtn("광부", 180, 150);
//		minerBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new minerWindow();
//				dispose();
//			}
//		});
		
		farmerBtn = makeBtn("무역", 180, 150);
//		farmerBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new farmerWindow();
//				dispose();
//			}
//		});
		
		priceBtn = makeBtn("아이템 시세", 180, 150);
		priceBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new priceWindow();
				dispose();
			}
		});
		
		
		/*	컴포넌트 add							*/
		//mainPanel.setBackground(Color.RED);
		
		InsertComponent(mainPanel, cookBtn, 0, 0, 1, 1);
		InsertComponent(mainPanel, minerBtn, 1, 0, 1, 1);
		InsertComponent(mainPanel, farmerBtn, 2, 0, 1, 1);
		InsertComponent(mainPanel, priceBtn, 0, 1, 3, 1);
		add(mainPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
		setLocation(200, 200);
		setResizable(false);
		setSize(400, 200);
		setVisible(true);
	}
	
	public JButton makeBtn(String name, int size_x, int size_y) {
		JButton btn = new JButton();
		btn.setText(name);
		btn.setBorderPainted(false);
		btn.setFont(SystemManager.normalTTF);
		//btn.setPreferredSize(new Dimension(size_x, size_y));
		return btn;
	}
	
	public void InsertComponent(JPanel panel, Component c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		panel.add(c, gbc);
	}
	
	public static void main(String[] args) {
		UIManager.put("Button.focus", new Color(0, 0, 0, 0));
		new mainWindow();
	}
}