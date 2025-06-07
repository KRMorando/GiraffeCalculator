package priceCalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class priceWindow extends JFrame {
	JPanel mainPanel, listPanel;
	JLabel subLabel;
	JScrollPane scrollPane;
	GridBagConstraints gbc;
	
	public priceWindow() {
		setTitle("�ü�ǥ");
		
		mainPanel = new JPanel();
		subLabel = new JLabel();
		listPanel = new JPanel();
		
		// ���� �г�
		mainPanel.setLayout(new BorderLayout(0, 10));
		mainPanel.setSize(800, 800);
		
		// �ΰ� ����
		subLabel.setText("TABŰ�� ����Ͽ� ������ ĭ���� �Ѿ �� �ֽ��ϴ�.");
		subLabel.setHorizontalAlignment(JLabel.CENTER);
		subLabel.setVerticalAlignment(JLabel.BOTTOM);
		subLabel.setFont(SystemManager.smallTTF);
		subLabel.setPreferredSize(new Dimension(800, 50));

		// ����Ʈ �г�
		listPanel.setLayout(new GridBagLayout());
//		listPanel.setSize(800, 70);
		// ��ũ�� �г�
//		scrollPane = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
//		scrollPane.setBounds(0, 100, 800, 650);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
//		gbc.anchor = GridBagConstraints.CENTER;
		
		// ����Ʈ ��� �߰�
		// �Ϲ� ���۹�
		makePriceList(PriceList.cropsName, 0);
		// ���õ� ���۹�
		makePriceList(PriceList.pCropsName, 1);
		// �Ϲ� �����
		makePriceList(PriceList.fishName, 3);
		// ���õ� �����
		makePriceList(PriceList.pFishName, 5);
		
		mainPanel.add(subLabel, BorderLayout.NORTH);
		mainPanel.add(listPanel, BorderLayout.CENTER);
//		mainPanel.add(scrollPane);
		add(mainPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(100, 100);
		setResizable(false);		
		setSize(800, 800);
		setVisible(true);
	}
	
	public void makePriceList(String[] name, int col) {
		LineBorder lb = new LineBorder(Color.black, 1, true);
		for(int i=0;i<name.length;i++) {
			System.out.println(i);
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setPreferredSize(new Dimension(78, 100));
			
			JLabel label = new JLabel();
			label.setText(name[i]);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(SystemManager.smallTTF);
			label.setBounds(0, 0, 78, 30);
			
			JTextField textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
						e.consume(); // �Էµ� ���ڸ� �Һ��Ͽ� JTextField�� �Էµ��� �ʵ��� ��
					}
				}
			});
			textField.setHorizontalAlignment(JTextField.CENTER);
			textField.setBounds(0, 30, 78, 70);
			
			if(i==(name.length-1) || i == 9) {
				panel.setBorder(new MatteBorder(1, 1, 1, 2, Color.BLACK));
				label.setBorder(new MatteBorder(1, 1, 1, 2, Color.BLACK));
				textField.setBorder(new MatteBorder(1, 1, 1, 2, Color.BLACK));
			}
			else {
				panel.setBorder(lb);
				label.setBorder(lb);
				textField.setBorder(lb);
			}
			
			panel.add(label);
			panel.add(textField);
			InsertComponent(listPanel, panel, (i % 10), (col + (i / 10)), 1, 1);
		}
	}
	
	public void InsertComponent(JPanel panel, JPanel c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		panel.add(c, gbc);
	}
}
