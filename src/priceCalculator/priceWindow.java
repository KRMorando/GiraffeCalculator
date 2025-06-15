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
	
	// 농작물 배열
	HashMap<String, JTextField> cropsMap = new HashMap<>();
	HashMap<String, Double> cropsPrice = infoList.getCropsPrice();
	
	// 숙련도 농작물 배열
	HashMap<String, JTextField> pCropsMap = new HashMap<>();
	HashMap<String, Double> pCropsPrice = infoList.getpCropsPrice();
	
	// 물고기 배열
	HashMap<String, JTextField> fishMap = new HashMap<>();
	HashMap<String, Double> fishPrice = infoList.getFishPrice();
	
	// 숙련도 물고기 배열
	HashMap<String, JTextField> pFishMap = new HashMap<>();
	HashMap<String, Double> pFishPrice = infoList.getpFishPrice();
	
	// 합판 배열
	HashMap<String, JTextField> woodMap = new HashMap<>();
	HashMap<String, Double> woodPrice = infoList.getWoodPrice();
	
	// 못 배열
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
		// JFrame의 위치를 변경하기 위한 MouseListener
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint(); // 최초 클릭 위치 저장
            }
        });

        // 마우스 드래그 이벤트 처리
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currentPoint = frame.getLocation(); // 현재 프레임 위치
                Point newLocation = new Point(currentPoint.x + e.getX() - initialClick.x, currentPoint.y + e.getY() - initialClick.y);
                frame.setLocation(newLocation); // 프레임 위치 변경
            }
            @Override
            public void mouseMoved(MouseEvent e) {} // 필요없지만 구현해야함
        });
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1, 1, 0, 0);
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		// 메인 패널
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.setBorder(lb);
		mainPanel.setPreferredSize(new Dimension(1500, 800));
		
		// 부가 설명
		subLabel = new JLabel();
		subLabel.setText("TAB키를 사용하여 오른쪽 칸으로 넘어갈 수 있습니다.");
		subLabel.setOpaque(false);
		subLabel.setHorizontalAlignment(JLabel.CENTER);
		subLabel.setVerticalAlignment(JLabel.CENTER);
		subLabel.setFont(SystemManager.normalTTF);
		subLabel.setPreferredSize(new Dimension(1400, 100));

		// 리스트 패널
		listPanel = new JPanel();
		listPanel.setLayout(new GridBagLayout());
//		listPanel.setBorder(new MatteBorder(3, 0, 0, 0, Color.GREEN));
		listPanel.setPreferredSize(new Dimension(1480, 800));
		// 스크롤 패널
		scrollPane = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(7);
		scrollPane.setPreferredSize(new Dimension(1500, 650));
		
		// 리스트 요소 추가
		cropsLabel = new JLabel();
		cropsLabel.setText("일반 농작물");
		cropsLabel.setOpaque(true);
		cropsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cropsLabel.setVerticalAlignment(SwingConstants.CENTER);
		cropsLabel.setFont(SystemManager.normalTTF);
		
		pCropsLabel = new JLabel();
		pCropsLabel.setText("숙련도 농작물");
		pCropsLabel.setOpaque(true);
		pCropsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pCropsLabel.setVerticalAlignment(SwingConstants.CENTER);
		pCropsLabel.setFont(SystemManager.normalTTF);
		
		fishLabel = new JLabel();
		fishLabel.setText("일반 물고기");
		fishLabel.setOpaque(true);
		fishLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fishLabel.setVerticalAlignment(SwingConstants.CENTER);
		fishLabel.setFont(SystemManager.normalTTF);
		
		pFishLabel = new JLabel();
		pFishLabel.setText("숙련도 물고기");
		pFishLabel.setOpaque(true);
		pFishLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pFishLabel.setVerticalAlignment(SwingConstants.CENTER);
		pFishLabel.setFont(SystemManager.normalTTF);
		
		woodLabel = new JLabel();
		woodLabel.setText("합판");
		woodLabel.setOpaque(true);
		woodLabel.setHorizontalAlignment(SwingConstants.CENTER);
		woodLabel.setVerticalAlignment(SwingConstants.CENTER);
		woodLabel.setFont(SystemManager.normalTTF);
		
		nailLabel = new JLabel();
		nailLabel.setText("못");
		nailLabel.setOpaque(true);
		nailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nailLabel.setVerticalAlignment(SwingConstants.CENTER);
		nailLabel.setFont(SystemManager.normalTTF);
		
		makePriceList(infoList.getCrops(), cropsMap, 0);				// 일반 농작물
		makePriceList(infoList.getpCrops(), pCropsMap,  1);				// 숙련도 농작물
		makePriceList(infoList.getFish(), fishMap, 3);					// 일반 물고기
		makePriceList(infoList.getpFish(), pFishMap, 5);				// 숙련도 물고기
		makePriceList(infoList.getWood(), woodMap, 6);					// 합판
		makePriceList(infoList.getNail(), nailMap, 7);					// 못
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 1));
		bottomPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		bottomPanel.setPreferredSize(new Dimension(1500, 30));
		
		lastSaveLabel = new JLabel();
		lastSaveLabel.setText("마지막 최신화: " + infoList.getLastSave());
		lastSaveLabel.setOpaque(false);
		lastSaveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastSaveLabel.setVerticalAlignment(SwingConstants.CENTER);
		lastSaveLabel.setFont(SystemManager.normalTTF);
		lastSaveLabel.setPreferredSize(new Dimension(800, 30));
		
		saveBtn = new JButton();
		saveBtn.setText("저장");
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
		exitBtn.setText("저장하지 않고 닫기");
		exitBtn.setOpaque(false);
		exitBtn.setBorder(lb);
		exitBtn.setFont(SystemManager.smallTTF);
//		exitBtn.setBorderPainted(false);
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "정말로 저장하지 않고 닫으시겠습니까?");
	            if (result == JOptionPane.YES_OPTION) {
	                new mainWindow();
					dispose();
	            } else {
	            }
			}
		});
		exitBtn.setPreferredSize(new Dimension(160, 30));
		
		save_exitBtn = new JButton();
		save_exitBtn.setText("저장하고 닫기");
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
	
	// 비어있는지 확인
	public boolean isEmpty() {
		String errorCode = "";
		
		for(int i = 0; i < crops.length; i++)
			if(cropsMap.get(crops[i]).getText().equals(""))
				errorCode += "[일반 농작물] " + crops[i] + "가(이) 비었습니다!\n";
		for(int i = 0; i < pCrops.length; i++)
			if(pCropsMap.get(pCrops[i]).getText().equals(""))
				errorCode += "[숙련도 농작물] " + pCrops[i] + "가(이) 비었습니다!\n";
		for(int i = 0; i < fish.length; i++)
			if(fishMap.get(fish[i]).getText().equals(""))
				errorCode += "[일반 물고기] " + fish[i] + "가(이) 비었습니다!\n";
		for(int i = 0; i < pFish.length; i++)
			if(pFishMap.get(pFish[i]).getText().equals(""))
				errorCode += "[숙련도 물고기] " + pFish[i] + "가(이) 비었습니다!\n";
		for(int i = 0; i < wood.length; i++)
			if(woodMap.get(wood[i]).getText().equals(""))
				errorCode += "[합판] " + wood[i] + "가(이) 비었습니다!\n";
		for(int i = 0; i < nail.length; i++)
			if(nailMap.get(nail[i]).getText().equals(""))
				errorCode += "[못] " + nail[i] + "가(이) 비었습니다!\n";
		
		if(errorCode.equals(""))
			return false;
		else {
			JOptionPane.showMessageDialog(null, errorCode + "0 또는 숫자를 기입해주세요.");
			return true;
		}
	}
	
	// 저장하기
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
		
		JOptionPane.showMessageDialog(null, "저장 되었습니다.");
	}
	
	// 불러오기
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
		// 공통 FocusListener 추가
        FocusAdapter selectAllOnFocus = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // FocusEvent가 키보드 포커스로 인해 발생한 경우만 선택
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
						e.consume(); // 입력된 문자를 소비하여 JTextField에 입력되지 않도록 함
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
