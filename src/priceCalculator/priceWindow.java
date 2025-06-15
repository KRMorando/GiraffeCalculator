package priceCalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;

public class priceWindow extends JFrame {
	InfoList infoList = InfoList.getInstance();
	
//	GradientPanel mainPanel;
	JFrame frame;
	JPanel mainPanel;
	JPanel listPanel, bottomPanel;
	JLabel subLabel, cropsLabel, pCropsLabel, fishLabel, pFishLabel, woodLabel, nailLabel, lastSaveLabel;
	JButton saveBtn, save_exitBtn, exitBtn; 
	JScrollPane scrollPane;
	GridBagConstraints gbc;
	
	// ���۹� �迭
	HashMap<String, JTextField> cropsMap = new HashMap<>();
	HashMap<String, Double> cropsPrice = infoList.getCropsPrice();
	
	// ���õ� ���۹� �迭
	HashMap<String, JTextField> pCropsMap = new HashMap<>();
	HashMap<String, Double> pCropsPrice = infoList.getpCropsPrice();
	
	// ����� �迭
	HashMap<String, JTextField> fishMap = new HashMap<>();
	HashMap<String, Double> fishPrice = infoList.getFishPrice();
	
	// ���õ� ����� �迭
	HashMap<String, JTextField> pFishMap = new HashMap<>();
	HashMap<String, Double> pFishPrice = infoList.getpFishPrice();
	
	// ���� �迭
	HashMap<String, JTextField> woodMap = new HashMap<>();
	HashMap<String, Double> woodPrice = infoList.getWoodPrice();
	
	// �� �迭
	HashMap<String, JTextField> nailMap = new HashMap<>();
	HashMap<String, Double> nailPrice = infoList.getNailPrice();
	
	private Point initialClick;
	
	LineBorder lb = new LineBorder(Color.black, 1, true);
	
	String[] crops = infoList.getCrops();
	String[] pCrops = infoList.getpCrops();
	String[] fish = infoList.getFish();
	String[] pFish = infoList.getpFish();
	String[] wood = infoList.getWood();
	String[] nail = infoList.getNail();
	
	public priceWindow() {
		frame = this;
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocation(0, 0);
		frame.setResizable(false);		
		frame.setSize(1500, 800);
		// JFrame�� ��ġ�� �����ϱ� ���� MouseListener
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint(); // ���� Ŭ�� ��ġ ����
            }
        });

        // ���콺 �巡�� �̺�Ʈ ó��
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currentPoint = frame.getLocation(); // ���� ������ ��ġ
                Point newLocation = new Point(currentPoint.x + e.getX() - initialClick.x, currentPoint.y + e.getY() - initialClick.y);
                frame.setLocation(newLocation); // ������ ��ġ ����
            }
            @Override
            public void mouseMoved(MouseEvent e) {} // �ʿ������ �����ؾ���
        });
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1, 1, 0, 0);
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		// ���� �г�
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.setBorder(lb);
		mainPanel.setPreferredSize(new Dimension(1500, 800));
		
		// �ΰ� ����
		subLabel = new JLabel();
		subLabel.setText("TABŰ�� ����Ͽ� ������ ĭ���� �Ѿ �� �ֽ��ϴ�.");
		subLabel.setOpaque(false);
		subLabel.setHorizontalAlignment(JLabel.CENTER);
		subLabel.setVerticalAlignment(JLabel.CENTER);
		subLabel.setFont(SystemManager.normalTTF);
		subLabel.setPreferredSize(new Dimension(1400, 100));

		// ����Ʈ �г�
		listPanel = new JPanel();
		listPanel.setLayout(new GridBagLayout());
//		listPanel.setBorder(new MatteBorder(3, 0, 0, 0, Color.GREEN));
		listPanel.setPreferredSize(new Dimension(1480, 800));
		// ��ũ�� �г�
		scrollPane = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(7);
		scrollPane.setPreferredSize(new Dimension(1500, 650));
		
		// ����Ʈ ��� �߰�
		cropsLabel = new JLabel();
		cropsLabel.setText("�Ϲ� ���۹�");
		cropsLabel.setOpaque(true);
		cropsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cropsLabel.setVerticalAlignment(SwingConstants.CENTER);
		cropsLabel.setFont(SystemManager.normalTTF);
		
		pCropsLabel = new JLabel();
		pCropsLabel.setText("���õ� ���۹�");
		pCropsLabel.setOpaque(true);
		pCropsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pCropsLabel.setVerticalAlignment(SwingConstants.CENTER);
		pCropsLabel.setFont(SystemManager.normalTTF);
		
		fishLabel = new JLabel();
		fishLabel.setText("�Ϲ� �����");
		fishLabel.setOpaque(true);
		fishLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fishLabel.setVerticalAlignment(SwingConstants.CENTER);
		fishLabel.setFont(SystemManager.normalTTF);
		
		pFishLabel = new JLabel();
		pFishLabel.setText("���õ� �����");
		pFishLabel.setOpaque(true);
		pFishLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pFishLabel.setVerticalAlignment(SwingConstants.CENTER);
		pFishLabel.setFont(SystemManager.normalTTF);
		
		woodLabel = new JLabel();
		woodLabel.setText("����");
		woodLabel.setOpaque(true);
		woodLabel.setHorizontalAlignment(SwingConstants.CENTER);
		woodLabel.setVerticalAlignment(SwingConstants.CENTER);
		woodLabel.setFont(SystemManager.normalTTF);
		
		nailLabel = new JLabel();
		nailLabel.setText("��");
		nailLabel.setOpaque(true);
		nailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nailLabel.setVerticalAlignment(SwingConstants.CENTER);
		nailLabel.setFont(SystemManager.normalTTF);
		
		makePriceList(infoList.getCrops(), cropsMap, 0);				// �Ϲ� ���۹�
		makePriceList(infoList.getpCrops(), pCropsMap,  1);				// ���õ� ���۹�
		makePriceList(infoList.getFish(), fishMap, 3);					// �Ϲ� �����
		makePriceList(infoList.getpFish(), pFishMap, 5);				// ���õ� �����
		makePriceList(infoList.getWood(), woodMap, 6);					// ����
		makePriceList(infoList.getNail(), nailMap, 7);					// ��
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 1));
		bottomPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		bottomPanel.setPreferredSize(new Dimension(1500, 30));
		
		lastSaveLabel = new JLabel();
		lastSaveLabel.setText("������ �ֽ�ȭ: " + infoList.getLastSave());
		lastSaveLabel.setOpaque(false);
		lastSaveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastSaveLabel.setVerticalAlignment(SwingConstants.CENTER);
		lastSaveLabel.setFont(SystemManager.normalTTF);
		lastSaveLabel.setPreferredSize(new Dimension(800, 30));
		
		saveBtn = new JButton();
		saveBtn.setText("����");
		saveBtn.setOpaque(false);
		saveBtn.setFont(SystemManager.smallTTF);
//		saveBtn.setBorderPainted(false);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isEmpty())
					savePrice();
			}
		});
		saveBtn.setPreferredSize(new Dimension(100, 30));
		
		exitBtn = new JButton();
		exitBtn.setText("�������� �ʰ� �ݱ�");
		exitBtn.setOpaque(false);
		exitBtn.setBorder(lb);
		exitBtn.setFont(SystemManager.smallTTF);
//		exitBtn.setBorderPainted(false);
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "������ �������� �ʰ� �����ðڽ��ϱ�?");
	            if (result == JOptionPane.YES_OPTION) {
	                new mainWindow();
					dispose();
	            } else {
	            }
			}
		});
		exitBtn.setPreferredSize(new Dimension(160, 30));
		
		save_exitBtn = new JButton();
		save_exitBtn.setText("�����ϰ� �ݱ�");
		save_exitBtn.setOpaque(false);
		save_exitBtn.setBorder(lb);
		save_exitBtn.setFont(SystemManager.smallTTF);
//		save_exitBtn.setBorderPainted(false);
		save_exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isEmpty()) {
					savePrice();
					new mainWindow();
					dispose();
				}
			}
		});
		save_exitBtn.setPreferredSize(new Dimension(150, 30));
		
		InsertComponent(listPanel, cropsLabel, 0, 0, 1, 1);
		InsertComponent(listPanel, pCropsLabel, 0, 1, 1, 2);
		InsertComponent(listPanel, fishLabel, 0, 3, 1, 2);
		InsertComponent(listPanel, pFishLabel, 0, 5, 1, 1);
		InsertComponent(listPanel, woodLabel, 0, 6, 1, 1);
		InsertComponent(listPanel, nailLabel, 0, 7, 1, 1);
		
		bottomPanel.add(lastSaveLabel);
		bottomPanel.add(saveBtn);
		bottomPanel.add(exitBtn);
		bottomPanel.add(save_exitBtn);
		
		mainPanel.add(subLabel, BorderLayout.NORTH);
//		mainPanel.add(listPanel, BorderLayout.CENTER);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		add(mainPanel);
		
		loadPrice();
		setVisible(true);
	}
	
	// ����ִ��� Ȯ��
	public boolean isEmpty() {
		String errorCode = "";
		
		for(int i = 0; i < crops.length; i++)
			if(cropsMap.get(crops[i]).getText().equals(""))
				errorCode += "[�Ϲ� ���۹�] " + crops[i] + "��(��) ������ϴ�!\n";
		for(int i = 0; i < pCrops.length; i++)
			if(pCropsMap.get(pCrops[i]).getText().equals(""))
				errorCode += "[���õ� ���۹�] " + pCrops[i] + "��(��) ������ϴ�!\n";
		for(int i = 0; i < fish.length; i++)
			if(fishMap.get(fish[i]).getText().equals(""))
				errorCode += "[�Ϲ� �����] " + fish[i] + "��(��) ������ϴ�!\n";
		for(int i = 0; i < pFish.length; i++)
			if(pFishMap.get(pFish[i]).getText().equals(""))
				errorCode += "[���õ� �����] " + pFish[i] + "��(��) ������ϴ�!\n";
		for(int i = 0; i < wood.length; i++)
			if(woodMap.get(wood[i]).getText().equals(""))
				errorCode += "[����] " + wood[i] + "��(��) ������ϴ�!\n";
		for(int i = 0; i < nail.length; i++)
			if(nailMap.get(nail[i]).getText().equals(""))
				errorCode += "[��] " + nail[i] + "��(��) ������ϴ�!\n";
		
		if(errorCode.equals(""))
			return false;
		else {
			JOptionPane.showMessageDialog(null, errorCode + "0 �Ǵ� ���ڸ� �������ּ���.");
			return true;
		}
	}
	
	// �����ϱ�
	public void savePrice() {
		for(int i = 0; i < crops.length; i++) 
			cropsPrice.put(crops[i], Double.parseDouble(cropsMap.get(crops[i]).getText()));
		for(int i = 0; i < pCrops.length; i++) 
			pCropsPrice.put(pCrops[i], Double.parseDouble(pCropsMap.get(pCrops[i]).getText()));
		for(int i = 0; i < fish.length; i++) 
			fishPrice.put(fish[i], Double.parseDouble(fishMap.get(fish[i]).getText()));
		for(int i = 0; i < pFish.length; i++) 
			pFishPrice.put(pFish[i], Double.parseDouble(pFishMap.get(pFish[i]).getText()));
		for(int i = 0; i < wood.length; i++) 
			woodPrice.put(wood[i], Double.parseDouble(woodMap.get(wood[i]).getText()));
		for(int i = 0; i < nail.length; i++) 
			nailPrice.put(nail[i], Double.parseDouble(nailMap.get(nail[i]).getText()));
		
		infoList.setCropsPrice(cropsPrice);
		infoList.setpCropsPrice(pCropsPrice);
		infoList.setFishPrice(fishPrice);
		infoList.setpFishPrice(pFishPrice);
		infoList.setWoodMap(woodPrice);
		infoList.setNailMap(nailPrice);
		infoList.savePriceInfo();
		
		JOptionPane.showMessageDialog(null, "���� �Ǿ����ϴ�.");
	}
	
	// �ҷ�����
	public void loadPrice() {
		for(int i = 0; i < crops.length; i++) 
			cropsMap.get(crops[i]).setText("" + cropsPrice.get(crops[i]));
		
		for(int i = 0; i < pCrops.length; i++)  
			pCropsMap.get(pCrops[i]).setText("" + pCropsPrice.get(pCrops[i]));
		
		for(int i = 0; i < fish.length; i++) 
			fishMap.get(fish[i]).setText("" + fishPrice.get(fish[i]));
		
		for(int i = 0; i < pFish.length; i++) 
			pFishMap.get(pFish[i]).setText("" + pFishPrice.get(pFish[i]));
		
		for(int i = 0; i < wood.length; i++) 
			woodMap.get(wood[i]).setText("" + woodPrice.get(wood[i]));
		
		for(int i = 0; i < nail.length; i++) 
			nailMap.get(nail[i]).setText("" + nailPrice.get(nail[i]));
	}
	
	public void makePriceList(String[] name, HashMap<String, JTextField> map, int col) {
		// ���� FocusListener �߰�
        FocusAdapter selectAllOnFocus = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // FocusEvent�� Ű���� ��Ŀ���� ���� �߻��� ��츸 ����
                if (e.getComponent() instanceof JTextField && e.getOppositeComponent() != null) {
                    SwingUtilities.invokeLater(() -> {
                        ((JTextField) e.getComponent()).selectAll();
                    });
                }
            }
        };
		for(int i=0;i<name.length;i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setPreferredSize(new Dimension(78, 100));
			
			JLabel label = new JLabel();
			label.setText(name[i]);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setFont(SystemManager.smallTTF);
			label.setPreferredSize(new Dimension(78, 30));
			
			JTextField textField = new JTextField();
			textField.setText("0.0");
			textField.setHorizontalAlignment(JTextField.CENTER);
			textField.setFont(SystemManager.smallTTF);
			textField.addFocusListener(selectAllOnFocus);
			((AbstractDocument) textField.getDocument()).setDocumentFilter(new DecimalFilter());
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!Character.isDigit(c) && c != '.' && c != '\b') {
						e.consume(); // �Էµ� ���ڸ� �Һ��Ͽ� JTextField�� �Էµ��� �ʵ��� ��
					}
				}
			});
			textField.setPreferredSize(new Dimension(78, 70));
			
			map.put(name[i], textField);
			
			panel.setBorder(null);
			label.setBorder(lb);
			textField.setBorder(lb);
			
			panel.add(label, BorderLayout.NORTH);
			panel.add(textField, BorderLayout.CENTER);
			InsertComponent(listPanel, panel, 1 + (i % 10), (col + (i / 10)), 1, 1);
		}
	}
	
	
	public void InsertComponent(JPanel panel, Component c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		panel.add(c, gbc);
	}
}
