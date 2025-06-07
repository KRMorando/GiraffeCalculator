package priceCalculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class priceWindow extends JFrame {
	JPanel mainPanel, listPanel;
	ArrayList<JPanel> panelList = new ArrayList<>();
	JLabel titleLabel, subLabel;
	GridBagConstraints gbc;
	
	public priceWindow() {
		int i;

		mainPanel = new JPanel();
		titleLabel = new JLabel();
		subLabel = new JLabel();
		listPanel = new JPanel();
		
		mainPanel.setLayout(null);
		mainPanel.setSize(800, 800);
		mainPanel.setBackground(Color.RED);
		
		titleLabel.setText("시세표");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(SystemManager.titleTTF);
		titleLabel.setBounds(0, 0, 800, 50);
		
		subLabel.setText("TAB키를 사용하여 오른쪽 칸으로 넘어갈 수 있습니다.");
		subLabel.setHorizontalAlignment(JLabel.CENTER);
		subLabel.setFont(SystemManager.smallTTF);
		subLabel.setBounds(0, 50, 800, 50);

		listPanel.setLayout(new GridBagLayout());
		listPanel.setBounds(0, 100, 800, 700);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		// 5 / 790
		LineBorder lb = new LineBorder(Color.black, 1, true);
		int col = 10;
		for(i=0;i<PriceList.cropsName.length;i++) {
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(lb);
			panel.setSize(79, 100);
			
			JLabel label = new JLabel();
			label.setText(PriceList.cropsName[i]);
			label.setFont(SystemManager.smallTTF);
			label.setBounds(0, 0, 79, 30);
			
			JTextField textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
						e.consume(); // 입력된 문자를 소비하여 JTextField에 입력되지 않도록 함
					}
				}
			});
			textField.setBounds(0, 30, 79, 70);
			
			panel.add(label);
			panel.add(textField);
			InsertComponent(listPanel, panel, i, 0, 1, 1);
		}
		
		mainPanel.add(titleLabel);
		mainPanel.add(subLabel);
		mainPanel.add(listPanel);
		add(mainPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(100, 100);
		setResizable(false);		
		setSize(800, 800);
		setVisible(true);
	}
	
	public void InsertComponent(JPanel panel, Component c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		panel.add(c, gbc);
	}
}
