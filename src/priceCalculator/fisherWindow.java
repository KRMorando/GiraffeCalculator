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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class fisherWindow extends JFrame {
	JFrame frame = this;
	JPanel mainPanel, pricePanel, labelPanel, listPanel, bottomPanel;
	JLabel priceLabel, firstLabel, secondLabel, thirdLabel, priceProfitLabel, subLabel_1, subLabel_2;
	JTextArea priceTextArea;
	JCheckBox sharkCBox, lobCBox;
	JButton applyBtn, exitBtn;
	
	JScrollPane priceScrollPane, listScrollPane;
	
	LineBorder lb = new LineBorder(Color.black, 1, true);
	InfoList infoList = InfoList.getInstance();
	
	// 시세 리스트
	HashMap<String, Double> priceMap = new HashMap<>();
	
	// 일반 물고기, 숙련도 물고기 시세, 합판, 못 시세를 InfoList에서 가져오기
	String[] fishName = infoList.getFish();
	String[] pFishName = infoList.getpFish();
	HashMap<String, Double> fishPriceMap = infoList.getFishPrice();
	HashMap<String, Double> pFishPriceMap = infoList.getpFishPrice();
	HashMap<String, Double> woodPriceMap = infoList.getWoodPrice();
	HashMap<String, Double> nailPriceMap = infoList.getNailPrice();
	
	// 각 물고기의 시세 수익, 1등급, 2등급, 3등급 라벨
	HashMap<String, JLabel> fishListMap = new HashMap<>();
	HashMap<String, JLabel> pFishListMap = new HashMap<>();
	
	GridBagConstraints gbc;
	
	private Point initialClick;
	
	public fisherWindow() {
		gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.insets= new Insets(1, 1, 0, 0);
	    gbc.weightx = 1;
	    gbc.weighty = 1;
	    
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocation(0, 0);
		frame.setResizable(false);		
		frame.setSize(800, 800);
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
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setSize(800, 800);
		mainPanel.setBorder(lb);
		
		pricePanel = new JPanel();
		pricePanel.setLayout(new BorderLayout());
		pricePanel.setPreferredSize(new Dimension(800, 250));
		
		// 부가 설명
		priceLabel = new JLabel();
		priceLabel.setText("원하는 시간대의 시세를 복사하여 아래에 붙여넣어주세요.");
		priceLabel.setOpaque(false);
		priceLabel.setHorizontalAlignment(JLabel.CENTER);
		priceLabel.setVerticalAlignment(JLabel.CENTER);
		priceLabel.setFont(SystemManager.normalTTF);
		priceLabel.setPreferredSize(new Dimension(800, 40));
		
		// 시세 텍스트 영역
		priceTextArea = new JTextArea();
//		priceTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		priceTextArea.setOpaque(false);
		priceTextArea.setFont(SystemManager.smallTTF);
		priceTextArea.setPreferredSize(new Dimension(780, 1600));
		// 스크롤 패널
		priceScrollPane = new JScrollPane(priceTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		priceScrollPane.setBorder(new LineBorder(Color.BLACK, 1));
		priceScrollPane.getVerticalScrollBar().setUnitIncrement(5);
		priceScrollPane.setPreferredSize(new Dimension(800, 130));
		
		// 라벨 패널
		labelPanel = new JPanel();
		labelPanel.setLayout(null);
		labelPanel.setPreferredSize(new Dimension(800, 80));
		
		// 빈 라벨, 시세가, 1등급, 2등급, 3등급
		priceProfitLabel = new JLabel();
		priceProfitLabel.setText("시세가");
		priceProfitLabel.setHorizontalAlignment(JLabel.CENTER);
		priceProfitLabel.setVerticalAlignment(JLabel.CENTER);
		priceProfitLabel.setFont(SystemManager.normalTTF);
		priceProfitLabel.setBounds(180, 0, 160, 80);
		
		firstLabel = new JLabel();
		firstLabel.setText("1등급");
		firstLabel.setHorizontalAlignment(JLabel.CENTER);
		firstLabel.setVerticalAlignment(JLabel.CENTER);
		firstLabel.setFont(SystemManager.normalTTF);
		firstLabel.setBounds(330, 0, 160, 80);
				
		secondLabel = new JLabel();
		secondLabel.setText("2등급");
		secondLabel.setHorizontalAlignment(JLabel.CENTER);
		secondLabel.setVerticalAlignment(JLabel.CENTER);
		secondLabel.setFont(SystemManager.normalTTF);
		secondLabel.setBounds(480, 0, 160, 80);
				
		thirdLabel = new JLabel();
		thirdLabel.setText("3등급");
		thirdLabel.setHorizontalAlignment(JLabel.CENTER);
		thirdLabel.setVerticalAlignment(JLabel.CENTER);
		thirdLabel.setFont(SystemManager.normalTTF);
		thirdLabel.setBounds(630, 0, 160, 80);
		
		subLabel_1 = new JLabel();
		subLabel_1.setText("(물고기 x 16)");
		subLabel_1.setHorizontalAlignment(JLabel.CENTER);
		subLabel_1.setVerticalAlignment(JLabel.CENTER);
		subLabel_1.setFont(SystemManager.smallTTF);
		subLabel_1.setForeground(Color.GRAY);
		subLabel_1.setBounds(180, 45, 160, 40);
		
		subLabel_2 = new JLabel();
		subLabel_2.setText("(변동가 - (물고기 x 16 + 합판 x 3 + 못 x 2)");
		subLabel_2.setHorizontalAlignment(JLabel.CENTER);
		subLabel_2.setVerticalAlignment(JLabel.CENTER);
		subLabel_2.setFont(SystemManager.smallTTF);
		subLabel_2.setForeground(Color.GRAY);
		subLabel_2.setBounds(380, 45, 350, 40);
				
		/* ====================================================	*/
		/* ====================  시세 끝 	=======================	*/
		/* ====================================================	*/
		
		// 리스트 패널
		listPanel = new JPanel();
		listPanel.setLayout(new GridBagLayout());
		listPanel.setOpaque(false);
		listPanel.setPreferredSize(new Dimension(780, 1600));
		// 스크롤 패널
		listScrollPane = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		listScrollPane.setBorder(new LineBorder(Color.WHITE, 1));
		listScrollPane.getVerticalScrollBar().setUnitIncrement(7);
		listScrollPane.setPreferredSize(new Dimension(800, 510));
		
		// 일반 물고기, 숙련도 물고기 리스트 추가
		int col = 1;
		for(int i = 0; i < fishName.length; i++) {
			JLabel fishLabel = new JLabel();
			fishLabel.setText(fishName[i]);
			fishLabel.setHorizontalAlignment(JLabel.CENTER);
			fishLabel.setVerticalAlignment(JLabel.CENTER);
			fishLabel.setFont(SystemManager.normalTTF);
			
			JLabel priceLabel = new JLabel();
			priceLabel.setText("0.0");
			priceLabel.setHorizontalAlignment(JLabel.CENTER);
			priceLabel.setVerticalAlignment(JLabel.CENTER);
			priceLabel.setFont(SystemManager.normalTTF);
			
			JLabel firstLabel = new JLabel();
			firstLabel.setText("+0.0");
			firstLabel.setHorizontalAlignment(JLabel.CENTER);
			firstLabel.setVerticalAlignment(JLabel.CENTER);
			firstLabel.setFont(SystemManager.normalTTF);
			firstLabel.setForeground(Color.RED);
			firstLabel.setOpaque(true);
			
			JLabel secondLabel = new JLabel();
			secondLabel.setText("+0.0");
			secondLabel.setHorizontalAlignment(JLabel.CENTER);
			secondLabel.setVerticalAlignment(JLabel.CENTER);
			secondLabel.setFont(SystemManager.normalTTF);
			secondLabel.setForeground(Color.RED);
			secondLabel.setOpaque(true);
			
			JLabel thirdLabel = new JLabel();
			thirdLabel.setText("+0.0");
			thirdLabel.setHorizontalAlignment(JLabel.CENTER);
			thirdLabel.setVerticalAlignment(JLabel.CENTER);
			thirdLabel.setFont(SystemManager.normalTTF);
			thirdLabel.setForeground(Color.RED);
			thirdLabel.setOpaque(true);
			
			InsertComponent(gbc, listPanel, fishLabel, 0, col, 1, 1);
			InsertComponent(gbc, listPanel, priceLabel, 1, col, 1, 1);
			InsertComponent(gbc, listPanel, firstLabel, 2, col, 1, 1);
			InsertComponent(gbc, listPanel, secondLabel, 3, col, 1, 1);
			InsertComponent(gbc, listPanel, thirdLabel, 4, col, 1, 1);
			
			fishListMap.put(fishName[i] + "_시세가", priceLabel);
			fishListMap.put(fishName[i] + "_1", firstLabel);
			fishListMap.put(fishName[i] + "_2", secondLabel);
			fishListMap.put(fishName[i] + "_3", thirdLabel);
			col++;
		}
		
		for(int i = 0; i < pFishName.length; i++) {
			JLabel pFishLabel = new JLabel();
			pFishLabel.setText(pFishName[i]);
			pFishLabel.setHorizontalAlignment(JLabel.CENTER);
			pFishLabel.setVerticalAlignment(JLabel.CENTER);
			pFishLabel.setFont(SystemManager.normalTTF);
			
			JLabel priceLabel = new JLabel();
			priceLabel.setText("0.0");
			priceLabel.setHorizontalAlignment(JLabel.CENTER);
			priceLabel.setVerticalAlignment(JLabel.CENTER);
			priceLabel.setFont(SystemManager.normalTTF);
			
			JLabel firstLabel = new JLabel();
			firstLabel.setText("+0.0");
			firstLabel.setHorizontalAlignment(JLabel.CENTER);
			firstLabel.setVerticalAlignment(JLabel.CENTER);
			firstLabel.setFont(SystemManager.normalTTF);
			firstLabel.setForeground(Color.RED);
			firstLabel.setOpaque(true);
			
			JLabel secondLabel = new JLabel();
			secondLabel.setText("+0.0");
			secondLabel.setHorizontalAlignment(JLabel.CENTER);
			secondLabel.setVerticalAlignment(JLabel.CENTER);
			secondLabel.setFont(SystemManager.normalTTF);
			secondLabel.setForeground(Color.RED);
			secondLabel.setOpaque(true);
			
			JLabel thirdLabel = new JLabel();
			thirdLabel.setText("+0.0");
			thirdLabel.setHorizontalAlignment(JLabel.CENTER);
			thirdLabel.setVerticalAlignment(JLabel.CENTER);
			thirdLabel.setFont(SystemManager.normalTTF);
			thirdLabel.setForeground(Color.RED);
			thirdLabel.setOpaque(true);
			
			InsertComponent(gbc, listPanel, pFishLabel, 0, col, 1, 1);
			InsertComponent(gbc, listPanel, priceLabel, 1, col, 1, 1);
			InsertComponent(gbc, listPanel, firstLabel, 2, col, 1, 1);
			InsertComponent(gbc, listPanel, secondLabel, 3, col, 1, 1);
			InsertComponent(gbc, listPanel, thirdLabel, 4, col, 1, 1);
			
			pFishListMap.put(pFishName[i] + "_시세가", priceLabel);
			pFishListMap.put(pFishName[i] + "_1", firstLabel);
			pFishListMap.put(pFishName[i] + "_2", secondLabel);
			pFishListMap.put(pFishName[i] + "_3", thirdLabel);
			col++;
		}
		// 여백
		JPanel bottom = new JPanel(null);
		bottom.setPreferredSize(new Dimension(800, 1));
		
		/* ====================================================	*/
		/* ==================== 중간 표 끝 ======================	*/
		/* ====================================================	*/
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 1, 1));
		bottomPanel.setPreferredSize(new Dimension(800, 40));
		
		sharkCBox = new JCheckBox("철갑상어", true);
		sharkCBox.setPreferredSize(new Dimension(160, 38));
		
		lobCBox = new JCheckBox("랍스터",true);
		lobCBox.setPreferredSize(new Dimension(160, 38));
		
		applyBtn = new JButton();
		applyBtn.setText("적용");
		applyBtn.setFont(SystemManager.smallTTF);
		applyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isEmpty())
					applyPrice();
			}
		});
		applyBtn.setPreferredSize(new Dimension(130, 38));
		
		exitBtn = new JButton();
		exitBtn.setText("닫기");
		exitBtn.setFont(SystemManager.smallTTF);
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new mainWindow();
				dispose();
			}
		});
		exitBtn.setPreferredSize(new Dimension(130, 38));
		
		/* ====================================================	*/
		/* ==================== 하단바 끝 =======================	*/
		/* ====================================================	*/
		
		pricePanel.add(priceLabel, BorderLayout.NORTH);
		pricePanel.add(priceScrollPane, BorderLayout.CENTER);
		pricePanel.add(labelPanel, BorderLayout.SOUTH);
		
		labelPanel.add(priceProfitLabel);
		labelPanel.add(firstLabel);
		labelPanel.add(secondLabel);
		labelPanel.add(thirdLabel);
		labelPanel.add(subLabel_1);
		labelPanel.add(subLabel_2);
		
		bottomPanel.add(sharkCBox);
		bottomPanel.add(lobCBox);
		bottomPanel.add(applyBtn);
		bottomPanel.add(exitBtn);

		mainPanel.add(pricePanel, BorderLayout.NORTH);
		mainPanel.add(listScrollPane, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		frame.add(mainPanel);
		frame.setVisible(true);
	}
	
	public boolean isEmpty() {
		if(priceTextArea.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "원하는 시간대의 시세 칸이 비었습니다!");
			return true;
		} else
			return false;
	}
	
	public void applyPrice() {
		calPrice();
		
		String target = "일반";
		String maxKey = "";
		Double maxValue = 0.0;
		
		// 일반 물고기
		for(int i = 0; i < fishName.length; i++) {
			// 컴포넌트 불러오기
			JLabel priceLb = fishListMap.get(fishName[i] + "_시세가");
			JLabel fishLb_1 = fishListMap.get(fishName[i] + "_1");
			JLabel fishLb_2 = fishListMap.get(fishName[i] + "_2");
			JLabel fishLb_3 = fishListMap.get(fishName[i] + "_3");

			// 값 불러오기
			Double hourPrice_1 = priceMap.get(fishName[i] + "_1");
			Double hourPrice_2 = priceMap.get(fishName[i] + "_2");
			Double hourPrice_3 = priceMap.get(fishName[i] + "_3");
		
			Double fishPrice = fishPriceMap.get(fishName[i]);
			Double woodPrice_1 = woodPriceMap.get("매끈한 합판");
			Double woodPrice_2 = woodPriceMap.get("다듬어진 합판");
			Double woodPrice_3 = woodPriceMap.get("거친 합판");
			Double nailPrice_1 = nailPriceMap.get("튼튼한 못");
			Double nailPrice_2 = nailPriceMap.get("구부러진 못");
			Double nailPrice_3 = nailPriceMap.get("녹슨 못");
			
			// 컴포넌트 배경 색상 초기화
			fishLb_1.setBackground(null);
			fishLb_2.setBackground(null);
			fishLb_3.setBackground(null);
			
			// 1등급	(변동가 - (물고기 * 16 + 1등급 합판 * 3 + 1등급 못 * 2)
			String cal_1 = String.format("%.2f" , hourPrice_1 - (fishPrice * 16 + woodPrice_1 * 3 + nailPrice_1 * 2));
			// 2등급	(변동가 - (물고기 * 16 + 2등급 합판 * 3 + 2등급 못 * 2)
			String cal_2 = String.format("%.2f" , hourPrice_2 - (fishPrice * 16 + woodPrice_2 * 3 + nailPrice_2 * 2));
			// 3등급	(변동가 - (물고기 * 16 + 3등급 합판 * 3 + 3등급 못 * 2)
			String cal_3 = String.format("%.2f" , hourPrice_3 - (fishPrice * 16 + woodPrice_3 * 3 + nailPrice_3 * 2));
			
			// 시세가	(물고기 * 16)
			priceLb.setText("" + (fishPrice * 16));
			
			if(Double.parseDouble(cal_1) > 0) {
				fishLb_1.setForeground(Color.RED);
				fishLb_1.setText("+" + cal_1);
			} else {
				fishLb_1.setForeground(Color.BLUE);
				fishLb_1.setText(cal_1);
			}
			
			if(Double.parseDouble(cal_2) > 0) {
				fishLb_2.setForeground(Color.RED);
				fishLb_2.setText("+" + cal_2);
			} else {
				fishLb_2.setForeground(Color.BLUE);
				fishLb_2.setText(cal_2);
			}
			
			if(Double.parseDouble(cal_3) > 0) {
				fishLb_3.setForeground(Color.RED);
				fishLb_3.setText("+" + cal_3);
			} else {
				fishLb_3.setForeground(Color.BLUE);
				fishLb_3.setText(cal_3);
			}
			
			if(!fishName[i].equals("철갑상어")) {
				if(maxValue < Double.parseDouble(cal_1)) {
					maxKey = fishName[i] + "_1";
					maxValue = Double.parseDouble(cal_1);
				}
				
				if(maxValue < Double.parseDouble(cal_2)) {
					maxKey = fishName[i] + "_2";
					maxValue = Double.parseDouble(cal_2);
				}
				
				if(maxValue < Double.parseDouble(cal_3)) {
					maxKey = fishName[i] + "_3";
					maxValue = Double.parseDouble(cal_3);
				}
			}
			
		}
		
		// 숙련도 물고기
		for(int i = 0; i < pFishName.length; i++) {
			// 컴포넌트 불러오기
			JLabel priceLb = pFishListMap.get(pFishName[i] + "_시세가");
			JLabel fishLb_1 = pFishListMap.get(pFishName[i] + "_1");
			JLabel fishLb_2 = pFishListMap.get(pFishName[i] + "_2");
			JLabel fishLb_3 = pFishListMap.get(pFishName[i] + "_3");

			// 값 불러오기
			Double hourPrice_1 = priceMap.get(pFishName[i] + "_1");
			Double hourPrice_2 = priceMap.get(pFishName[i] + "_2");
			Double hourPrice_3 = priceMap.get(pFishName[i] + "_3");
		
			Double fishPrice = pFishPriceMap.get(pFishName[i]);
			Double woodPrice_1 = woodPriceMap.get("매끈한 합판");
			Double woodPrice_2 = woodPriceMap.get("다듬어진 합판");
			Double woodPrice_3 = woodPriceMap.get("거친 합판");
			Double nailPrice_1 = nailPriceMap.get("튼튼한 못");
			Double nailPrice_2 = nailPriceMap.get("구부러진 못");
			Double nailPrice_3 = nailPriceMap.get("녹슨 못");
			
			// 컴포넌트 배경 색상 초기화
			fishLb_1.setBackground(null);
			fishLb_2.setBackground(null);
			fishLb_3.setBackground(null);
			
			// 1등급	(변동가 - (물고기 * 16 + 1등급 합판 * 3 + 1등급 못 * 2)
			String cal_1 = String.format("%.2f" , hourPrice_1 - (fishPrice * 16 + woodPrice_1 * 3 + nailPrice_1 * 2));
			// 2등급	(변동가 - (물고기 * 16 + 2등급 합판 * 3 + 2등급 못 * 2)
			String cal_2 = String.format("%.2f" , hourPrice_2 - (fishPrice * 16 + woodPrice_2 * 3 + nailPrice_2 * 2));
			// 3등급	(변동가 - (물고기 * 16 + 3등급 합판 * 3 + 3등급 못 * 2)
			String cal_3 = String.format("%.2f" , hourPrice_3 - (fishPrice * 16 + woodPrice_3 * 3 + nailPrice_3 * 2));
			
			// 시세가	(물고기 * 16)
			priceLb.setText("" + (fishPrice * 16));
						
			if(Double.parseDouble(cal_1) > 0) {
				fishLb_1.setForeground(Color.RED);
				fishLb_1.setText("+" + cal_1);
			} else {
				fishLb_1.setForeground(Color.BLUE);
				fishLb_1.setText(cal_1);
			}
			
			if(Double.parseDouble(cal_2) > 0) {
				fishLb_2.setForeground(Color.RED);
				fishLb_2.setText("+" + cal_2);
			} else {
				fishLb_2.setForeground(Color.BLUE);
				fishLb_2.setText(cal_2);
			}
			
			if(Double.parseDouble(cal_3) > 0) {
				fishLb_3.setForeground(Color.RED);
				fishLb_3.setText("+" + cal_3);
			} else {
				fishLb_3.setForeground(Color.BLUE);
				fishLb_3.setText(cal_3);
			}

			if(maxValue < Double.parseDouble(cal_1)) {
				target = "숙련도";
				maxKey = pFishName[i] + "_1";
				maxValue = Double.parseDouble(cal_1);
			}
				
			if(maxValue < Double.parseDouble(cal_2)) {
				target = "숙련도";
				maxKey = pFishName[i] + "_2";
				maxValue = Double.parseDouble(cal_2);
			}
				
			if(maxValue < Double.parseDouble(cal_3)) {
				target = "숙련도";
				maxKey = pFishName[i] + "_3";
				maxValue = Double.parseDouble(cal_3);
			}
		}
//		System.out.println("Key: " + maxKey + "Value: " + maxValue);
		if(target.equals("일반"))
			fishListMap.get(maxKey).setBackground(Color.decode("#ee90c7"));
				
		else
			pFishListMap.get(maxKey).setBackground(Color.decode("#ee90c7"));
		JOptionPane.showMessageDialog(null, "적용이 완료되었습니다.");
	}
	
	public void calPrice() {
		String text = priceTextArea.getText();
		String[] lines = text.split("\n");
		String[] parts;
		String name;
		String value;

		for (String line : lines) {
			if(!line.contains("원가:"))
				continue;
			else {
                parts = line.split(":", 2);
                name = parts[0].trim();
                value = parts[1].trim();

                name = name.split("\\(")[0].trim() + "_" + name.split("\\(")[1].split("단")[0].trim();

                if (value.contains("변동후:")) {
                	value = value.replaceAll(",", "");
                	value = value.split("변동후:", 2)[1].split("변동률:",2)[0].trim();
                } else if (value.contains("현재가:")) {
                	value = value.replaceAll(",", "");
                	value = value.split("현재가:")[1].trim();
                }
                // 결과 출력
                try {
                	priceMap.put(name, Double.parseDouble(value));
                	System.out.println("name: " + name + ", value: " + value);
                } catch(Exception e) {
                	JOptionPane.showMessageDialog(null, "에러가 발생했습니다!\n시세 칸 마지막에 다른 문구가 들어간건 아닌지 확인해주시기 바랍니다!");
                }
            }
        }
		System.out.println(priceMap);
	}
	
	public void InsertComponent(GridBagConstraints gbc, JPanel panel, Component c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		panel.add(c, gbc);
	}
}