package priceCalculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class mainWindow extends JFrame {
	JPanel mainPanel;
	JButton cookBtn, minerBtn, farmerBtn, fisherBtn, priceBtn;
	GridBagConstraints gbc;
	
	mainWindow() {
        InfoList infoList = InfoList.getInstance();
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        setTitle("란도의 기린 계산기");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
		setLocation(500, 300);
		setResizable(false);
		setSize(500, 200);
		
		mainPanel = new JPanel();		
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setSize(500, 200);
		
		cookBtn = makeBtn("요리 시세", 180, 150);
		cookBtn.setFont(new Font(SystemManager.ttf, Font.PLAIN, 18));
		cookBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new cookWindow();
				dispose();
			}
		});
		
		minerBtn = makeBtn("광부 무역", 180, 150);
		minerBtn.setFont(new Font(SystemManager.ttf, Font.PLAIN, 18));
		minerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				new minerWindow();
//				dispose();
				JOptionPane.showMessageDialog(null, "아직 미구현입니다.");
			}
		});
		
		farmerBtn = makeBtn("농부 무역", 180, 150);
		farmerBtn.setFont(new Font(SystemManager.ttf, Font.PLAIN, 18));
		farmerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new farmerWindow();
				dispose();
			}
		});
		
		fisherBtn = makeBtn("어부 무역", 180, 150);
		fisherBtn.setFont(new Font(SystemManager.ttf, Font.PLAIN, 18));
		fisherBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new fisherWindow();
				dispose();
			}
		});
		
		priceBtn = makeBtn("아이템 시세", 180, 150);
		priceBtn.setFont(new Font(SystemManager.ttf, Font.PLAIN, 18));
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
		InsertComponent(mainPanel, fisherBtn, 3, 0, 1, 1);
		InsertComponent(mainPanel, priceBtn, 0, 1, 4, 1);
		add(mainPanel);
		
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