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
	
	// �ü� ����Ʈ
	HashMap<String, Double> priceMap = new HashMap<>();
	
	// �Ϲ� �����, ���õ� ����� �ü�, ����, �� �ü��� InfoList���� ��������
	String[] fishName = infoList.getFish();
	String[] pFishName = infoList.getpFish();
	HashMap<String, Double> fishPriceMap = infoList.getFishPrice();
	HashMap<String, Double> pFishPriceMap = infoList.getpFishPrice();
	HashMap<String, Double> woodPriceMap = infoList.getWoodPrice();
	HashMap<String, Double> nailPriceMap = infoList.getNailPrice();
	
	// �� ������� �ü� ����, 1���, 2���, 3��� ��
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
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setSize(800, 800);
		mainPanel.setBorder(lb);
		
		pricePanel = new JPanel();
		pricePanel.setLayout(new BorderLayout());
		pricePanel.setPreferredSize(new Dimension(800, 250));
		
		// �ΰ� ����
		priceLabel = new JLabel();
		priceLabel.setText("���ϴ� �ð����� �ü��� �����Ͽ� �Ʒ��� �ٿ��־��ּ���.");
		priceLabel.setOpaque(false);
		priceLabel.setHorizontalAlignment(JLabel.CENTER);
		priceLabel.setVerticalAlignment(JLabel.CENTER);
		priceLabel.setFont(SystemManager.normalTTF);
		priceLabel.setPreferredSize(new Dimension(800, 40));
		
		// �ü� �ؽ�Ʈ ����
		priceTextArea = new JTextArea();
//		priceTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		priceTextArea.setOpaque(false);
		priceTextArea.setFont(SystemManager.smallTTF);
		priceTextArea.setPreferredSize(new Dimension(780, 1600));
		// ��ũ�� �г�
		priceScrollPane = new JScrollPane(priceTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		priceScrollPane.setBorder(new LineBorder(Color.BLACK, 1));
		priceScrollPane.getVerticalScrollBar().setUnitIncrement(5);
		priceScrollPane.setPreferredSize(new Dimension(800, 130));
		
		// �� �г�
		labelPanel = new JPanel();
		labelPanel.setLayout(null);
		labelPanel.setPreferredSize(new Dimension(800, 80));
		
		// �� ��, �ü���, 1���, 2���, 3���
		priceProfitLabel = new JLabel();
		priceProfitLabel.setText("�ü���");
		priceProfitLabel.setHorizontalAlignment(JLabel.CENTER);
		priceProfitLabel.setVerticalAlignment(JLabel.CENTER);
		priceProfitLabel.setFont(SystemManager.normalTTF);
		priceProfitLabel.setBounds(180, 0, 160, 80);
		
		firstLabel = new JLabel();
		firstLabel.setText("1���");
		firstLabel.setHorizontalAlignment(JLabel.CENTER);
		firstLabel.setVerticalAlignment(JLabel.CENTER);
		firstLabel.setFont(SystemManager.normalTTF);
		firstLabel.setBounds(330, 0, 160, 80);
				
		secondLabel = new JLabel();
		secondLabel.setText("2���");
		secondLabel.setHorizontalAlignment(JLabel.CENTER);
		secondLabel.setVerticalAlignment(JLabel.CENTER);
		secondLabel.setFont(SystemManager.normalTTF);
		secondLabel.setBounds(480, 0, 160, 80);
				
		thirdLabel = new JLabel();
		thirdLabel.setText("3���");
		thirdLabel.setHorizontalAlignment(JLabel.CENTER);
		thirdLabel.setVerticalAlignment(JLabel.CENTER);
		thirdLabel.setFont(SystemManager.normalTTF);
		thirdLabel.setBounds(630, 0, 160, 80);
		
		subLabel_1 = new JLabel();
		subLabel_1.setText("(����� x 16)");
		subLabel_1.setHorizontalAlignment(JLabel.CENTER);
		subLabel_1.setVerticalAlignment(JLabel.CENTER);
		subLabel_1.setFont(SystemManager.smallTTF);
		subLabel_1.setForeground(Color.GRAY);
		subLabel_1.setBounds(180, 45, 160, 40);
		
		subLabel_2 = new JLabel();
		subLabel_2.setText("(������ - (����� x 16 + ���� x 3 + �� x 2)");
		subLabel_2.setHorizontalAlignment(JLabel.CENTER);
		subLabel_2.setVerticalAlignment(JLabel.CENTER);
		subLabel_2.setFont(SystemManager.smallTTF);
		subLabel_2.setForeground(Color.GRAY);
		subLabel_2.setBounds(380, 45, 350, 40);
				
		/* ====================================================	*/
		/* ====================  �ü� �� 	=======================	*/
		/* ====================================================	*/
		
		// ����Ʈ �г�
		listPanel = new JPanel();
		listPanel.setLayout(new GridBagLayout());
		listPanel.setOpaque(false);
		listPanel.setPreferredSize(new Dimension(780, 1600));
		// ��ũ�� �г�
		listScrollPane = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		listScrollPane.setBorder(new LineBorder(Color.WHITE, 1));
		listScrollPane.getVerticalScrollBar().setUnitIncrement(7);
		listScrollPane.setPreferredSize(new Dimension(800, 510));
		
		// �Ϲ� �����, ���õ� ����� ����Ʈ �߰�
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
			
			fishListMap.put(fishName[i] + "_�ü���", priceLabel);
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
			
			pFishListMap.put(pFishName[i] + "_�ü���", priceLabel);
			pFishListMap.put(pFishName[i] + "_1", firstLabel);
			pFishListMap.put(pFishName[i] + "_2", secondLabel);
			pFishListMap.put(pFishName[i] + "_3", thirdLabel);
			col++;
		}
		// ����
		JPanel bottom = new JPanel(null);
		bottom.setPreferredSize(new Dimension(800, 1));
		
		/* ====================================================	*/
		/* ==================== �߰� ǥ �� ======================	*/
		/* ====================================================	*/
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 1, 1));
		bottomPanel.setPreferredSize(new Dimension(800, 40));
		
		sharkCBox = new JCheckBox("ö�����", true);
		sharkCBox.setPreferredSize(new Dimension(160, 38));
		
		lobCBox = new JCheckBox("������",true);
		lobCBox.setPreferredSize(new Dimension(160, 38));
		
		applyBtn = new JButton();
		applyBtn.setText("����");
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
		exitBtn.setText("�ݱ�");
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
		/* ==================== �ϴܹ� �� =======================	*/
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
			JOptionPane.showMessageDialog(null, "���ϴ� �ð����� �ü� ĭ�� ������ϴ�!");
			return true;
		} else
			return false;
	}
	
	public void applyPrice() {
		calPrice();
		
		String target = "�Ϲ�";
		String maxKey = "";
		Double maxValue = 0.0;
		
		// �Ϲ� �����
		for(int i = 0; i < fishName.length; i++) {
			// ������Ʈ �ҷ�����
			JLabel priceLb = fishListMap.get(fishName[i] + "_�ü���");
			JLabel fishLb_1 = fishListMap.get(fishName[i] + "_1");
			JLabel fishLb_2 = fishListMap.get(fishName[i] + "_2");
			JLabel fishLb_3 = fishListMap.get(fishName[i] + "_3");

			// �� �ҷ�����
			Double hourPrice_1 = priceMap.get(fishName[i] + "_1");
			Double hourPrice_2 = priceMap.get(fishName[i] + "_2");
			Double hourPrice_3 = priceMap.get(fishName[i] + "_3");
		
			Double fishPrice = fishPriceMap.get(fishName[i]);
			Double woodPrice_1 = woodPriceMap.get("�Ų��� ����");
			Double woodPrice_2 = woodPriceMap.get("�ٵ���� ����");
			Double woodPrice_3 = woodPriceMap.get("��ģ ����");
			Double nailPrice_1 = nailPriceMap.get("ưư�� ��");
			Double nailPrice_2 = nailPriceMap.get("���η��� ��");
			Double nailPrice_3 = nailPriceMap.get("�콼 ��");
			
			// ������Ʈ ��� ���� �ʱ�ȭ
			fishLb_1.setBackground(null);
			fishLb_2.setBackground(null);
			fishLb_3.setBackground(null);
			
			// 1���	(������ - (����� * 16 + 1��� ���� * 3 + 1��� �� * 2)
			String cal_1 = String.format("%.2f" , hourPrice_1 - (fishPrice * 16 + woodPrice_1 * 3 + nailPrice_1 * 2));
			// 2���	(������ - (����� * 16 + 2��� ���� * 3 + 2��� �� * 2)
			String cal_2 = String.format("%.2f" , hourPrice_2 - (fishPrice * 16 + woodPrice_2 * 3 + nailPrice_2 * 2));
			// 3���	(������ - (����� * 16 + 3��� ���� * 3 + 3��� �� * 2)
			String cal_3 = String.format("%.2f" , hourPrice_3 - (fishPrice * 16 + woodPrice_3 * 3 + nailPrice_3 * 2));
			
			// �ü���	(����� * 16)
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
			
			if(!fishName[i].equals("ö�����")) {
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
		
		// ���õ� �����
		for(int i = 0; i < pFishName.length; i++) {
			// ������Ʈ �ҷ�����
			JLabel priceLb = pFishListMap.get(pFishName[i] + "_�ü���");
			JLabel fishLb_1 = pFishListMap.get(pFishName[i] + "_1");
			JLabel fishLb_2 = pFishListMap.get(pFishName[i] + "_2");
			JLabel fishLb_3 = pFishListMap.get(pFishName[i] + "_3");

			// �� �ҷ�����
			Double hourPrice_1 = priceMap.get(pFishName[i] + "_1");
			Double hourPrice_2 = priceMap.get(pFishName[i] + "_2");
			Double hourPrice_3 = priceMap.get(pFishName[i] + "_3");
		
			Double fishPrice = pFishPriceMap.get(pFishName[i]);
			Double woodPrice_1 = woodPriceMap.get("�Ų��� ����");
			Double woodPrice_2 = woodPriceMap.get("�ٵ���� ����");
			Double woodPrice_3 = woodPriceMap.get("��ģ ����");
			Double nailPrice_1 = nailPriceMap.get("ưư�� ��");
			Double nailPrice_2 = nailPriceMap.get("���η��� ��");
			Double nailPrice_3 = nailPriceMap.get("�콼 ��");
			
			// ������Ʈ ��� ���� �ʱ�ȭ
			fishLb_1.setBackground(null);
			fishLb_2.setBackground(null);
			fishLb_3.setBackground(null);
			
			// 1���	(������ - (����� * 16 + 1��� ���� * 3 + 1��� �� * 2)
			String cal_1 = String.format("%.2f" , hourPrice_1 - (fishPrice * 16 + woodPrice_1 * 3 + nailPrice_1 * 2));
			// 2���	(������ - (����� * 16 + 2��� ���� * 3 + 2��� �� * 2)
			String cal_2 = String.format("%.2f" , hourPrice_2 - (fishPrice * 16 + woodPrice_2 * 3 + nailPrice_2 * 2));
			// 3���	(������ - (����� * 16 + 3��� ���� * 3 + 3��� �� * 2)
			String cal_3 = String.format("%.2f" , hourPrice_3 - (fishPrice * 16 + woodPrice_3 * 3 + nailPrice_3 * 2));
			
			// �ü���	(����� * 16)
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
				target = "���õ�";
				maxKey = pFishName[i] + "_1";
				maxValue = Double.parseDouble(cal_1);
			}
				
			if(maxValue < Double.parseDouble(cal_2)) {
				target = "���õ�";
				maxKey = pFishName[i] + "_2";
				maxValue = Double.parseDouble(cal_2);
			}
				
			if(maxValue < Double.parseDouble(cal_3)) {
				target = "���õ�";
				maxKey = pFishName[i] + "_3";
				maxValue = Double.parseDouble(cal_3);
			}
		}
//		System.out.println("Key: " + maxKey + "Value: " + maxValue);
		if(target.equals("�Ϲ�"))
			fishListMap.get(maxKey).setBackground(Color.decode("#ee90c7"));
				
		else
			pFishListMap.get(maxKey).setBackground(Color.decode("#ee90c7"));
		JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
	}
	
	public void calPrice() {
		String text = priceTextArea.getText();
		String[] lines = text.split("\n");
		String[] parts;
		String name;
		String value;

		for (String line : lines) {
			if(!line.contains("����:"))
				continue;
			else {
                parts = line.split(":", 2);
                name = parts[0].trim();
                value = parts[1].trim();

                name = name.split("\\(")[0].trim() + "_" + name.split("\\(")[1].split("��")[0].trim();

                if (value.contains("������:")) {
                	value = value.replaceAll(",", "");
                	value = value.split("������:", 2)[1].split("������:",2)[0].trim();
                } else if (value.contains("���簡:")) {
                	value = value.replaceAll(",", "");
                	value = value.split("���簡:")[1].trim();
                }
                // ��� ���
                try {
                	priceMap.put(name, Double.parseDouble(value));
                	System.out.println("name: " + name + ", value: " + value);
                } catch(Exception e) {
                	JOptionPane.showMessageDialog(null, "������ �߻��߽��ϴ�!\n�ü� ĭ �������� �ٸ� ������ ���� �ƴ��� Ȯ�����ֽñ� �ٶ��ϴ�!");
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