package priceCalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

public class cookWindow extends JFrame {
//	GradientPanel mainSpecPanel;
	JFrame frame = this;
	JPanel mainPanel, mainLeftPanel, mainRightPanel;
//	JPanel paddingPanel;
	JPanel specPanel, specListPanel, listPanel, statPanel, cook_now1_panel, cook_now2_panel, cook_now3_panel, bottomPanel, pricePanel, priceListPanel;
	JLabel specLabel, statLabel, substatLabel, cook_now1_label, cook_now1_numLabel, cook_now2_label, cook_now2_numLabel, cook_now3_label, cook_now3_numLabel, statueLabel, diceLabel, priceLabel;
	JButton saveBtn, applyBtn, backBtn, exitBtn;
	JTextField cook_levelTextField;
	JTextArea priceTextArea;
	
	JScrollPane scrollPane, priceScrollPane, priceListScrollPane;
	GridBagConstraints lgbc, sgbc;
	
	LineBorder lb = new LineBorder(Color.black, 1, true);

	InfoList infoList = InfoList.getInstance();
	
	// 현재 적용된 능력치
	HashMap<String, JLabel> spec_map = new HashMap<String, JLabel>();
	
	// 1등급 가중치, 2등급 가중치, 재료 미소모 확률, 요리가 탈 확률, 요리 대성공 확률, 은혜 발동 확률
	HashMap<String, JTextField> cook_statue = new HashMap<>();
	// 1등급 가중치, 2등급 가중치, 요리가 탈 확률
	HashMap<String, JTextField> cook_dice = new HashMap<>();
	
	// InfoList에서 요리사의 동상, 다이스 초기화
	HashMap<String, Double> info_cook_statue = infoList.getStatueCook();
	HashMap<String, Double> info_cook_dice = infoList.getDiceCook();
	
	private Point initialClick;
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
	public cookWindow() {
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocation(100, 100);
		frame.setResizable(false);		
		frame.setSize(1600, 800);
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
		
		lgbc = new GridBagConstraints();
        lgbc.fill = GridBagConstraints.BOTH;
        lgbc.insets = new Insets(2, 2, 2, 0);
        lgbc.weightx = 1;
        lgbc.weighty = 1;
        
        sgbc = new GridBagConstraints();
        sgbc.fill = GridBagConstraints.BOTH;
        sgbc.insets= new Insets(0, 1, 0, 0);
        sgbc.weightx = 1;
        sgbc.weighty = 1;
        
		// 메인 패널
//		mainSpecPanel = new GradientPanel(Color.decode("#8CC3FF"), Color.decode("#B0CEE5"));;
        mainPanel = new JPanel();
        mainPanel.setBorder(new LineBorder(Color.BLACK, 2, true));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setSize(1600, 800);
		
		// 왼쪽 패널
		mainLeftPanel = new JPanel();
		mainLeftPanel.setLayout(new BorderLayout());
		mainLeftPanel.setPreferredSize(new Dimension(800, 770));
		
		// 현재 스펙 패널
		specPanel = new JPanel();
		specPanel.setLayout(null);
		specPanel.setPreferredSize(new Dimension(800, 150));
		
		// 현재 스펙 라벨
		specLabel = new JLabel();
		specLabel.setText("현재 적용된 능력치");
		specLabel.setHorizontalAlignment(JLabel.CENTER);
		specLabel.setVerticalAlignment(JLabel.CENTER);
		specLabel.setFont(SystemManager.normalTTF);
//		specLabel.setPreferredSize(new Dimension(800, 40));
		specLabel.setBounds(0, 0, 800, 40);
		
		// 현재 스펙 리스트 패널
		specListPanel = new JPanel();
		specListPanel.setLayout(new GridBagLayout());
		specListPanel.setOpaque(false);
		specListPanel.setBorder(null);
//		specListPanel.setPreferredSize(new Dimension(800, 110));
		specListPanel.setBounds(0, 40, 800, 110);

		// 스펙 리스트에 들어갈 패널들
		JPanel spec_level = makeSpecPanel("숙련도", spec_map);
		JPanel spec_grade1 = makeSpecPanel("1등급", spec_map);
		JPanel spec_grade2 = makeSpecPanel("2등급", spec_map);
		JPanel spec_grade3 = makeSpecPanel("3등급", spec_map);
		JPanel spec_free = makeSpecPanel("재료 미소모 확률", spec_map);
		JPanel spec_death = makeSpecPanel("요리가 탈 확률", spec_map);
		JPanel spec_success = makeSpecPanel("요리 대성공 확률", spec_map);
		JPanel spec_grace = makeSpecPanel("은혜 발동 확률", spec_map);
		
		// 리스트 패널
		listPanel = new JPanel();
		listPanel.setLayout(new GridBagLayout());
		listPanel.setOpaque(false);
		listPanel.setPreferredSize(new Dimension(770, 620));
		// 스크롤 패널
		scrollPane = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setBorder(new LineBorder(Color.WHITE, 1));
		scrollPane.getVerticalScrollBar().setUnitIncrement(3);
		scrollPane.setPreferredSize(new Dimension(800, 570));
		
		statPanel = new JPanel();
		statPanel.setLayout(new BorderLayout());
		statPanel.setOpaque(false);
		statPanel.setPreferredSize(new Dimension(0, 80));
		
		statLabel = new JLabel();
		statLabel.setText("현재 숙련도를 기입해주세요.");
		statLabel.setHorizontalAlignment(JLabel.CENTER);
		statLabel.setVerticalAlignment(JLabel.BOTTOM);
		statLabel.setFont(SystemManager.normalTTF);
		statLabel.setPreferredSize(new Dimension(0, 40));
		
		substatLabel = new JLabel();
		substatLabel.setText("능력치는 숙련도, 동상, 다이스를 모두 기입 후에 우측 하단에 저장 및 적용을 누르면 반영됩니다.");
		substatLabel.setHorizontalAlignment(JLabel.CENTER);
		substatLabel.setVerticalAlignment(JLabel.TOP);
		substatLabel.setFont(SystemManager.smallTTF);
		substatLabel.setPreferredSize(new Dimension(0, 40));
		
		JPanel cook_levelPanel = new JPanel();
		cook_levelPanel.setLayout(new BorderLayout());
		cook_levelPanel.setPreferredSize(new Dimension(0, 100));
			
		JLabel cook_levelLabel = new JLabel();
		cook_levelLabel.setText("숙련도");
		cook_levelLabel.setHorizontalAlignment(JLabel.CENTER);
		cook_levelLabel.setFont(SystemManager.smallTTF);
		cook_levelLabel.setPreferredSize(new Dimension(0, 30));
			
		cook_levelTextField = new JTextField();
		cook_levelTextField.setHorizontalAlignment(JTextField.CENTER);
		cook_levelTextField.setFont(new Font(SystemManager.ttf, Font.PLAIN, 22));
		cook_levelTextField.setText("1");
		cook_levelTextField.addFocusListener(selectAllOnFocus);
		((AbstractDocument) cook_levelTextField.getDocument()).setDocumentFilter(new DecimalFilter100());
		cook_levelTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != '\b') {
					e.consume(); // 입력된 문자를 소비하여 JTextField에 입력되지 않도록 함
				}
			}
		});
		cook_levelTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkNumber();
            }
            public void removeUpdate(DocumentEvent e) {
                checkNumber();
            }
            public void changedUpdate(DocumentEvent e) {
                checkNumber();
            }

            private void checkNumber() {
            	if(!cook_levelTextField.getText().equals("")) {
            		int level = Integer.parseInt(cook_levelTextField.getText());
            		try {
            			if(level > 100) {
            				cook_levelTextField.setText("100");
            			} else if(level > 50) {
            				cook_now1_numLabel.setText("20%");
            				cook_now2_numLabel.setText("14%");
            			} else if(level > 40) {
            				cook_now1_numLabel.setText("15%");
            				cook_now2_numLabel.setText("12%");
            			} else if(level > 30) {
            				cook_now1_numLabel.setText("11%");
            				cook_now2_numLabel.setText("10%");
            			} else if(level > 20) {
            				cook_now1_numLabel.setText("7%");
            				cook_now2_numLabel.setText("8%");
            			} else if(level > 10) {
            				cook_now1_numLabel.setText("4%");
            				cook_now2_numLabel.setText("6%");
            			} else if(level == 0) {
            				cook_now1_numLabel.setText("0%");
            				cook_now2_numLabel.setText("0%");
            			} else {
            				cook_now1_numLabel.setText("2%");
            				cook_now2_numLabel.setText("3%");
            			}
            		} catch (NumberFormatException ex) {
            			ex.printStackTrace();
            			System.out.println("숫자를 입력하세요");
            		}
            	} else {
            		cook_now1_numLabel.setText("0%");
    				cook_now2_numLabel.setText("0%");
            	}
            }
        });
		cook_levelTextField.setPreferredSize(new Dimension(0, 70));
			
		cook_levelPanel.setBorder(lb);
		cook_levelLabel.setBorder(lb);
		cook_levelTextField.setBorder(lb);

		cook_levelPanel.add(cook_levelLabel, BorderLayout.NORTH);
		cook_levelPanel.add(cook_levelTextField, BorderLayout.CENTER);
		
		cook_now1_panel = new JPanel();
		cook_now1_panel.setLayout(new BorderLayout());
		cook_now1_panel.setPreferredSize(new Dimension(120, 100));
			
		cook_now1_label = new JLabel();
		cook_now1_label.setText("적용될 1등급");
		cook_now1_label.setHorizontalAlignment(JLabel.CENTER);
		cook_now1_label.setFont(SystemManager.smallTTF);
		cook_now1_label.setPreferredSize(new Dimension(120, 30));
			
		cook_now1_numLabel = new JLabel("2%");
		cook_now1_numLabel.setHorizontalAlignment(JTextField.CENTER);
		cook_now1_numLabel.setFont(new Font(SystemManager.ttf, Font.PLAIN, 22));
		cook_now1_numLabel.setFocusable(false);
		cook_now1_numLabel.setBackground(Color.WHITE);
		cook_now1_numLabel.setPreferredSize(new Dimension(120, 70));
			
		cook_now1_panel.setBorder(lb);
		cook_now1_label.setBorder(lb);
		cook_now1_numLabel.setBorder(lb);

		cook_now1_panel.add(cook_now1_label, BorderLayout.NORTH);
		cook_now1_panel.add(cook_now1_numLabel, BorderLayout.CENTER);
		
		cook_now2_panel = new JPanel();
		cook_now2_panel.setLayout(new BorderLayout());
		cook_now2_panel.setPreferredSize(new Dimension(120, 100));
			
		cook_now2_label = new JLabel();
		cook_now2_label.setText("적용될 2등급");
		cook_now2_label.setHorizontalAlignment(JLabel.CENTER);
		cook_now2_label.setFont(SystemManager.smallTTF);
		cook_now2_label.setPreferredSize(new Dimension(120, 30));
			
		cook_now2_numLabel = new JLabel("3%");
		cook_now2_numLabel.setHorizontalAlignment(JTextField.CENTER);
		cook_now2_numLabel.setFont(new Font(SystemManager.ttf, Font.PLAIN, 22));
		cook_now2_numLabel.setFocusable(false);
		cook_now2_numLabel.setBackground(Color.WHITE);
		cook_now2_numLabel.setPreferredSize(new Dimension(120, 70));
			
		cook_now2_panel.setBorder(lb);
		cook_now2_label.setBorder(lb);
		cook_now2_numLabel.setBorder(lb);

		cook_now2_panel.add(cook_now2_label, BorderLayout.NORTH);
		cook_now2_panel.add(cook_now2_numLabel, BorderLayout.CENTER);
		
		cook_now3_panel = new JPanel();
		cook_now3_panel.setLayout(new BorderLayout());
		cook_now3_panel.setPreferredSize(new Dimension(120, 100));
			
		cook_now3_label = new JLabel();
		cook_now3_label.setText("적용될 3등급");
		cook_now3_label.setHorizontalAlignment(JLabel.CENTER);
		cook_now3_label.setFont(SystemManager.smallTTF);
		cook_now3_label.setPreferredSize(new Dimension(120, 30));
		
		cook_now3_numLabel = new JLabel("0%");
		cook_now3_numLabel.setHorizontalAlignment(JTextField.CENTER);
		cook_now3_numLabel.setFont(new Font(SystemManager.ttf, Font.PLAIN, 22));
		cook_now3_numLabel.setFocusable(false);
		cook_now3_numLabel.setBackground(Color.WHITE);
		cook_now3_numLabel.setPreferredSize(new Dimension(120, 70));
			
		cook_now3_panel.setBorder(lb);
		cook_now3_label.setBorder(lb);
		cook_now3_numLabel.setBorder(lb);

		cook_now3_panel.add(cook_now3_label, BorderLayout.NORTH);
		cook_now3_panel.add(cook_now3_numLabel, BorderLayout.CENTER);
		
		// 동상 입력창 라벨 생성
		statueLabel = new JLabel();
		statueLabel.setText("보유하고 있는 동상 효과를 하단에 기입해주세요.");
		statueLabel.setHorizontalAlignment(JLabel.CENTER);
		statueLabel.setVerticalAlignment(JLabel.CENTER);
		statueLabel.setFont(SystemManager.normalTTF);
		statueLabel.setPreferredSize(new Dimension(0, 100));
		
		// 1등급 가중치, 2등급 가중치, 재료 미소모, 요리가 탈 확률, 요리 대성공 확률, 은혜 발동 확률
		// 동상 입력창 생성
		JPanel cook_statue_1grade = makePanel("1등급 가중치", cook_statue);
		JPanel cook_statue_2grade = makePanel("2등급 가중치", cook_statue);
		JPanel cook_statue_free = makePanel("재료 미소모 확률", cook_statue);
		JPanel cook_statue_death = makePanel("요리가 탈 확률", cook_statue);
		JPanel cook_statue_success = makePanel("요리 대성공 확률", cook_statue);
		JPanel cook_statue_grace = makePanel("은혜 발동 확률", cook_statue);

		// 다이스 입력창 라벨 생성
		diceLabel = new JLabel();
		diceLabel.setText("보유하고 있는 다이스 효과를 하단에 기입해주세요.");
		diceLabel.setHorizontalAlignment(JLabel.CENTER);
		diceLabel.setVerticalAlignment(JLabel.CENTER);
		diceLabel.setFont(SystemManager.normalTTF);
		diceLabel.setPreferredSize(new Dimension(0, 100));

		// 1등급 가중치, 2등급 가중치, 요리가 탈 확률
		// 다이스 입력창 생성
		JPanel cook_dice_1grade = makePanel("1등급 가중치", cook_dice);
		JPanel cook_dice_2grade = makePanel("2등급 가중치", cook_dice);
		JPanel cook_dice_death = makePanel("요리가 탈 확률", cook_dice);
		
		/* ====================================================	*/
		/* ==================== 왼쪽 끝 =========================	*/
		/* ====================================================	*/
		
		// 가운데 여백 패널
//		paddingPanel = new JPanel();
//		paddingPanel.setLayout(new BorderLayout());
//		paddingPanel.setPreferredSize(new Dimension(1, 800));
		
		/* ====================================================	*/
		/* ================== 가운데 여백 끝 =====================	*/
		/* ====================================================	*/
		
		// 오른쪽 패널
		mainRightPanel = new JPanel();
		mainRightPanel.setLayout(new BorderLayout());
		mainRightPanel.setPreferredSize(new Dimension(800, 770));
				
		// 시세 패널
		pricePanel = new JPanel();
		pricePanel.setLayout(new BorderLayout());
		pricePanel.setPreferredSize(new Dimension(790, 150));
				
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
		priceTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		priceTextArea.setOpaque(false);
		priceTextArea.setFont(SystemManager.smallTTF);
		priceTextArea.setPreferredSize(new Dimension(780, 1600));
		// 스크롤 패널
		priceScrollPane = new JScrollPane(priceTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		priceScrollPane.setBorder(new LineBorder(Color.WHITE, 1));
		priceScrollPane.getVerticalScrollBar().setUnitIncrement(5);
		priceScrollPane.setPreferredSize(new Dimension(800, 110));
		
		// 리스트 패널
		priceListPanel = new JPanel();
		priceListPanel.setLayout(new GridBagLayout());
		priceListPanel.setOpaque(false);
		priceListPanel.setPreferredSize(new Dimension(780, 620));
		// 스크롤 패널
		priceListScrollPane = new JScrollPane(priceListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		priceListScrollPane.setBorder(new LineBorder(Color.WHITE, 1));
		priceListScrollPane.getVerticalScrollBar().setUnitIncrement(5);
		priceListScrollPane.setPreferredSize(new Dimension(800, 570));
		
		/* ====================================================	*/
		/* ==================== 오른쪽 끝 =======================	*/
		/* ====================================================	*/
		
		// 하단바
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 1, 0));
		bottomPanel.setPreferredSize(new Dimension(800, 30));
		
		backBtn = new JButton();
		backBtn.setText("저장하지 않고 닫기");
		backBtn.setFont(SystemManager.smallTTF);
		backBtn.addActionListener(new ActionListener() {
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
		backBtn.setPreferredSize(new Dimension(200, 30));

		saveBtn = new JButton();
		saveBtn.setText("저장");
		saveBtn.setFont(SystemManager.smallTTF);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isEmpty())
					saveSpec();
			}
		});
		saveBtn.setPreferredSize(new Dimension(130, 30));
		
		applyBtn = new JButton();
		applyBtn.setText("적용");
		applyBtn.setFont(SystemManager.smallTTF);
		applyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isEmpty())
					applySpec(true);
			}
		});
		applyBtn.setPreferredSize(new Dimension(130, 30));
		
		
		exitBtn = new JButton();
		exitBtn.setText("저장 및 닫기");
		exitBtn.setFont(SystemManager.smallTTF);
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isEmpty()) {
					saveSpec();
					new mainWindow();
					dispose();
				}
			}
		});
		exitBtn.setPreferredSize(new Dimension(130, 30));
		
		/* ====================================================	*/
		/* ==================== 하단바 끝 =======================	*/
		/* ====================================================	*/
				
		// 왼쪽 패널에 컴포넌트 추가
//		specPanel.add(specLabel, BorderLayout.NORTH);
//		specPanel.add(specListPanel, BorderLayout.CENTER);
		specPanel.add(specLabel);
		specPanel.add(specListPanel);
		
		InsertComponent(sgbc, specListPanel, spec_level, 0, 0, 1, 1);
		InsertComponent(sgbc, specListPanel, spec_grade1, 1, 0, 1, 1);
		InsertComponent(sgbc, specListPanel, spec_grade2, 2, 0, 1, 1);
		InsertComponent(sgbc, specListPanel, spec_grade3, 3, 0, 1, 1);
		InsertComponent(sgbc, specListPanel, spec_free, 4, 0, 1, 1);
		InsertComponent(sgbc, specListPanel, spec_death, 5, 0, 1, 1);
		InsertComponent(sgbc, specListPanel, spec_success, 6, 0, 1, 1);
		InsertComponent(sgbc, specListPanel, spec_grace, 7, 0, 1, 1);
		
		InsertComponent(lgbc, listPanel, statPanel, 0, 0, 4, 1);
		InsertComponent(lgbc, listPanel, cook_levelPanel, 0, 1, 1, 1);
		InsertComponent(lgbc, listPanel, cook_now1_panel, 1, 1, 1, 1);
		InsertComponent(lgbc, listPanel, cook_now2_panel, 2, 1, 1, 1);
		InsertComponent(lgbc, listPanel, cook_now3_panel, 3, 1, 1, 1);
		InsertComponent(lgbc, listPanel, statueLabel, 0, 2, 4, 1);
		InsertComponent(lgbc, listPanel, cook_statue_1grade, 0, 3, 1, 1);
		InsertComponent(lgbc, listPanel, cook_statue_2grade, 1, 3, 1, 1);
		InsertComponent(lgbc, listPanel, cook_statue_free, 2, 3, 1, 1);
		InsertComponent(lgbc, listPanel, cook_statue_death, 3, 3, 1, 1);
		InsertComponent(lgbc, listPanel, cook_statue_success, 0, 4, 1, 1);
		InsertComponent(lgbc, listPanel, cook_statue_grace, 1, 4, 1, 1);
		InsertComponent(lgbc, listPanel, diceLabel, 0, 5, 4, 1);
		InsertComponent(lgbc, listPanel, cook_dice_1grade, 0, 6, 1, 1);
		InsertComponent(lgbc, listPanel, cook_dice_2grade, 1, 6, 1, 1);
		InsertComponent(lgbc, listPanel, cook_dice_death, 2, 6, 1, 1);
		
		statPanel.add(statLabel, BorderLayout.NORTH);
		statPanel.add(substatLabel, BorderLayout.CENTER);
		
		mainLeftPanel.add(specPanel, BorderLayout.NORTH);
		mainLeftPanel.add(scrollPane, BorderLayout.CENTER);
		
		// 오른쪽 패널에 컴포넌트 추가
		pricePanel.add(priceLabel, BorderLayout.NORTH);
		pricePanel.add(priceScrollPane, BorderLayout.CENTER);
		
		mainRightPanel.add(pricePanel, BorderLayout.NORTH);
		mainRightPanel.add(priceListScrollPane, BorderLayout.CENTER);
		
		bottomPanel.add(saveBtn);
		bottomPanel.add(applyBtn);
		bottomPanel.add(backBtn);
		bottomPanel.add(exitBtn);
		
		// 메인 패널에 최종 추가
		mainPanel.add(mainLeftPanel, BorderLayout.WEST);
//		mainPanel.add(paddingPanel, BorderLayout.CENTER);
		mainPanel.add(mainRightPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		add(mainPanel);

//		gbc = new GridBagConstraints();
//		gbc.fill = GridBagConstraints.CENTER;
//		gbc.anchor = GridBagConstraints.CENTER;
		
		// 스펙 로딩
		loadSpec();
		
		setVisible(true);
	}
	
	public boolean isEmpty() {
		String errorCode = "";
		if(cook_levelTextField.getText().equals("") || Integer.parseInt(cook_levelTextField.getText()) == 0)				// 숙련도
			errorCode += "숙련도가 비어 있거나 0 입니다!\n";
		if(cook_statue.get("1등급 가중치").getText().equals(""))		// 동상 시작
			errorCode += "[동상] 1등급 가중치가 비었습니다!\n";
		if(cook_statue.get("2등급 가중치").getText().equals(""))
			errorCode += "[동상] 2등급 가중치가 비었습니다!\n";
		if(cook_statue.get("재료 미소모 확률").getText().equals(""))
			errorCode += "[동상] 재료 미소모 확률이 비었습니다!\n";
		if(cook_statue.get("요리가 탈 확률").getText().equals(""))
			errorCode += "[동상] 요리가 탈 확률이 비었습니다!\n";
		if(cook_statue.get("요리 대성공 확률").getText().equals(""))
			errorCode += "[동상] 요리 대성공 확률이 비었습니다!\n";
		if(cook_statue.get("은혜 발동 확률").getText().equals(""))		// 동상 끝
			errorCode += "[동상] 은혜 발동 확률이 비었습니다!\n";
		if(cook_dice.get("1등급 가중치").getText().equals(""))			// 다이스 시작
			errorCode += "[다이스] 1등급 가중치가 비었습니다!\n";
		if(cook_dice.get("2등급 가중치").getText().equals(""))
			errorCode += "[다이스] 2등급 가중치가 비었습니다!\n";
		if(cook_dice.get("요리가 탈 확률").getText().equals(""))		// 다이스 끝
			errorCode += "[다이스] 요리가 탈 확률이 비었습니다!\n";
		
		if(errorCode.equals(""))
			return false;
		else {
			JOptionPane.showMessageDialog(null, errorCode);
			return true;
		}
	}
	
	public void loadSpec() {
		cook_levelTextField.setText("" + infoList.getCookLevel());
		cook_statue.get("1등급 가중치").setText("" + info_cook_statue.get("1등급 가중치"));
		cook_statue.get("2등급 가중치").setText("" + info_cook_statue.get("2등급 가중치"));
		cook_statue.get("재료 미소모 확률").setText("" + info_cook_statue.get("재료 미소모 확률"));
		cook_statue.get("요리가 탈 확률").setText("" + info_cook_statue.get("요리가 탈 확률"));
		cook_statue.get("요리 대성공 확률").setText("" + info_cook_statue.get("요리 대성공 확률"));
		cook_statue.get("은혜 발동 확률").setText("" + info_cook_statue.get("은혜 발동 확률"));
		
		cook_dice.get("1등급 가중치").setText("" + info_cook_dice.get("1등급 가중치"));
		cook_dice.get("2등급 가중치").setText("" + info_cook_dice.get("2등급 가중치"));
		cook_dice.get("요리가 탈 확률").setText("" + info_cook_dice.get("요리가 탈 확률"));
		applySpec(false);
	}
	
	public void saveSpec() {
		// 동상
		// 1등급 가중치, 2등급 가중치, 재료 미소모 확률, 요리가 탈 확률, 요리 대성공 확률, 은혜 발동 확률
		HashMap<String, Double> statue = new HashMap<>();
		statue.put("1등급 가중치", Double.parseDouble(cook_statue.get("1등급 가중치").getText()));
		statue.put("2등급 가중치", Double.parseDouble(cook_statue.get("2등급 가중치").getText()));
		statue.put("재료 미소모 확률", Double.parseDouble(cook_statue.get("재료 미소모 확률").getText()));
		statue.put("요리가 탈 확률", Double.parseDouble(cook_statue.get("요리가 탈 확률").getText()));
		statue.put("요리 대성공 확률", Double.parseDouble(cook_statue.get("요리 대성공 확률").getText()));
		statue.put("은혜 발동 확률", Double.parseDouble(cook_statue.get("은혜 발동 확률").getText()));
		
		// 다이스
		// 1등급 가중치, 2등급 가중치, 요리가 탈 확률
		HashMap<String, Double> dice = new HashMap<>();
		dice.put("1등급 가중치", Double.parseDouble(cook_dice.get("1등급 가중치").getText()));
		dice.put("2등급 가중치", Double.parseDouble(cook_dice.get("2등급 가중치").getText()));
		dice.put("요리가 탈 확률", Double.parseDouble(cook_dice.get("요리가 탈 확률").getText()));
		
		infoList.setCookLevel(Integer.parseInt(cook_levelTextField.getText()));
		infoList.setStatueCook(statue);
		infoList.setDiceCook(dice);
		infoList.saveSpecInfo();
		
		JOptionPane.showMessageDialog(null, "저장 되었습니다.");
	}
	
	public void applySpec(boolean alert) {
		// cook_spec 배열
		// 숙련도, 1등급 가중치, 2등급 가중치, 3등급 가중치, 재료 미소모 확률, 요리가 탈 확률, 요리 대성공 확률, 은혜 발동 확률
		double grade1 = 0 + Double.parseDouble(cook_statue.get("1등급 가중치").getText()) + Double.parseDouble(cook_dice.get("1등급 가중치").getText());
		double grade2 = 30 + Double.parseDouble(cook_statue.get("2등급 가중치").getText()) + Double.parseDouble(cook_dice.get("2등급 가중치").getText());
		double grade3 = 70;
		double free = Double.parseDouble(cook_statue.get("재료 미소모 확률").getText());
		double death = 7 - Double.parseDouble(cook_statue.get("요리가 탈 확률").getText()) - Double.parseDouble(cook_dice.get("요리가 탈 확률").getText());
		double success = Double.parseDouble(cook_statue.get("요리 대성공 확률").getText());
		double grace = Double.parseDouble(cook_statue.get("은혜 발동 확률").getText());
		
		// 숙련도 레벨에 따른 가중치 계산
		int level = Integer.parseInt(cook_levelTextField.getText());
		if(level > 50) {
			grade1 += 20;
			grade2 += 14;
			grace += 3;
		} else if(level > 40) {
			grade1 += 15;
			grade2 += 12;
			grace += 2.5;
		} else if(level > 30) {
			grade1 += 11;
			grade2 += 10;
			grace += 2;
		} else if(level > 20) {
			grade1 += 7;
			grade2 += 8;
			grace += 1.5;
		} else if(level > 10) {
			grade1 += 4;
			grade2 += 6;
			grace += 1;
		} else {
			grade1 += 2;
			grade2 += 3;
			grace += 0.5;
		}
		
		if((grade1 + grade2) >= 100) {
			grade3 = 0;
			grade2 = 100 - grade1;
		} else
			grade3 = 100 - (grade1 + grade2);
		
		free += level * 0.1;
		death -= level * 0.1;
		if(death < 0)
			death = 0;
		success += level * 0.05;
		
		spec_map.get("숙련도").setText("" + level);
		spec_map.get("1등급").setText("" + grade1 + "%");
		spec_map.get("2등급").setText("" + grade2 + "%");
		spec_map.get("3등급").setText("" + grade3 + "%");
		spec_map.get("재료 미소모 확률").setText("" + String.format("%.2f", free) + "%");
		spec_map.get("요리가 탈 확률").setText("" + String.format("%.2f", death) + "%");
		spec_map.get("요리 대성공 확률").setText("" + String.format("%.2f", success) + "%");
		spec_map.get("은혜 발동 확률").setText("" + String.format("%.2f", grace) + "%");
		
		double food_count = 1728;
		int food_1st = 0;
		int food_2nd = 0;
		int food_3th = 0;
		
		food_count += food_count * free / 100;
		food_count += food_count * success / 100;
		food_count += food_count * grace / 100;
		food_count -= food_count * death / 100;
		
		food_1st += food_count * grade1 / 100;
		food_2nd += food_count * grade2 / 100;
		food_3th += food_count * grade3 / 100;
		
		if(alert)
			JOptionPane.showMessageDialog(null, "적용 되었습니다.");
	}
	
	public JPanel makePanel(String name, HashMap<String, JTextField> list) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(120, 100));
			
		JLabel label = new JLabel();
		label.setText(name);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(SystemManager.smallTTF);
		label.setPreferredSize(new Dimension(120, 30));
			
		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setFont(new Font(SystemManager.ttf, Font.PLAIN, 22));
		textField.addFocusListener(selectAllOnFocus);
		((AbstractDocument) textField.getDocument()).setDocumentFilter(new DecimalFilter());
		if(name.equals("숙련도")) {
			textField.setText("1");
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!Character.isDigit(c) && c != '\b') {
						e.consume(); // 입력된 문자를 소비하여 JTextField에 입력되지 않도록 함
					}
				}
			});
		}
		else {
			textField.setText("0.0");
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!Character.isDigit(c) && c != '.' && c != '\b') {
						e.consume(); // 입력된 문자를 소비하여 JTextField에 입력되지 않도록 함
					}
				}
			});
		}
		textField.setPreferredSize(new Dimension(120, 70));
			
		panel.setBorder(lb);
		label.setBorder(lb);
		textField.setBorder(lb);

		panel.add(label, BorderLayout.NORTH);
		panel.add(textField, BorderLayout.CENTER);
		
		list.put(name, textField);
		return panel;
	}
	
	public JPanel makeSpecPanel(String name, HashMap<String, JLabel> map) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension((800/7), 105));
		
		JLabel label = new JLabel();
		label.setText(name);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(SystemManager.smallTTF);
		label.setPreferredSize(new Dimension((800/7), 40));
		
		JLabel numLabel = new JLabel();
		
		if(name.equals("숙련도")) {
			numLabel.setText("1");
			numLabel.setHorizontalAlignment(JTextField.CENTER);
			numLabel.setFont(new Font(SystemManager.ttf, Font.PLAIN, 22));
			numLabel.setFocusable(false);
			numLabel.setBackground(Color.WHITE);
			numLabel.setPreferredSize(new Dimension((800/7), 65));
		} else {
			numLabel.setText("0.0%");
			numLabel.setHorizontalAlignment(JTextField.CENTER);
			numLabel.setFont(new Font(SystemManager.ttf, Font.PLAIN, 22));
			numLabel.setFocusable(false);
			numLabel.setBackground(Color.WHITE);
			numLabel.setPreferredSize(new Dimension((800/7), 65));
		}
		
		panel.setBorder(null);
		label.setBorder(new LineBorder(Color.BLACK, 1, false));
		numLabel.setBorder(new LineBorder(Color.BLACK, 1, false));

		panel.add(label, BorderLayout.NORTH);
		panel.add(numLabel, BorderLayout.CENTER);
		
		map.put(name, numLabel);
		return panel;
	}
	
	public void InsertComponent(GridBagConstraints gbc, JPanel panel, Component c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		panel.add(c, gbc);
	}
}
