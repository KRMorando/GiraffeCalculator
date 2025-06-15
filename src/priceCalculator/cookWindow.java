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
	
	// ���� ����� �ɷ�ġ
	HashMap<String, JLabel> spec_map = new HashMap<String, JLabel>();
	
	// 1��� ����ġ, 2��� ����ġ, ��� �̼Ҹ� Ȯ��, �丮�� Ż Ȯ��, �丮 �뼺�� Ȯ��, ���� �ߵ� Ȯ��
	HashMap<String, JTextField> cook_statue = new HashMap<>();
	// 1��� ����ġ, 2��� ����ġ, �丮�� Ż Ȯ��
	HashMap<String, JTextField> cook_dice = new HashMap<>();
	
	// InfoList���� �丮���� ����, ���̽� �ʱ�ȭ
	HashMap<String, Double> info_cook_statue = infoList.getStatueCook();
	HashMap<String, Double> info_cook_dice = infoList.getDiceCook();
	
	private Point initialClick;
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
	public cookWindow() {
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocation(100, 100);
		frame.setResizable(false);		
		frame.setSize(1600, 800);
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
        
		// ���� �г�
//		mainSpecPanel = new GradientPanel(Color.decode("#8CC3FF"), Color.decode("#B0CEE5"));;
        mainPanel = new JPanel();
        mainPanel.setBorder(new LineBorder(Color.BLACK, 2, true));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setSize(1600, 800);
		
		// ���� �г�
		mainLeftPanel = new JPanel();
		mainLeftPanel.setLayout(new BorderLayout());
		mainLeftPanel.setPreferredSize(new Dimension(800, 770));
		
		// ���� ���� �г�
		specPanel = new JPanel();
		specPanel.setLayout(null);
		specPanel.setPreferredSize(new Dimension(800, 150));
		
		// ���� ���� ��
		specLabel = new JLabel();
		specLabel.setText("���� ����� �ɷ�ġ");
		specLabel.setHorizontalAlignment(JLabel.CENTER);
		specLabel.setVerticalAlignment(JLabel.CENTER);
		specLabel.setFont(SystemManager.normalTTF);
//		specLabel.setPreferredSize(new Dimension(800, 40));
		specLabel.setBounds(0, 0, 800, 40);
		
		// ���� ���� ����Ʈ �г�
		specListPanel = new JPanel();
		specListPanel.setLayout(new GridBagLayout());
		specListPanel.setOpaque(false);
		specListPanel.setBorder(null);
//		specListPanel.setPreferredSize(new Dimension(800, 110));
		specListPanel.setBounds(0, 40, 800, 110);

		// ���� ����Ʈ�� �� �гε�
		JPanel spec_level = makeSpecPanel("���õ�", spec_map);
		JPanel spec_grade1 = makeSpecPanel("1���", spec_map);
		JPanel spec_grade2 = makeSpecPanel("2���", spec_map);
		JPanel spec_grade3 = makeSpecPanel("3���", spec_map);
		JPanel spec_free = makeSpecPanel("��� �̼Ҹ� Ȯ��", spec_map);
		JPanel spec_death = makeSpecPanel("�丮�� Ż Ȯ��", spec_map);
		JPanel spec_success = makeSpecPanel("�丮 �뼺�� Ȯ��", spec_map);
		JPanel spec_grace = makeSpecPanel("���� �ߵ� Ȯ��", spec_map);
		
		// ����Ʈ �г�
		listPanel = new JPanel();
		listPanel.setLayout(new GridBagLayout());
		listPanel.setOpaque(false);
		listPanel.setPreferredSize(new Dimension(770, 620));
		// ��ũ�� �г�
		scrollPane = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setBorder(new LineBorder(Color.WHITE, 1));
		scrollPane.getVerticalScrollBar().setUnitIncrement(3);
		scrollPane.setPreferredSize(new Dimension(800, 570));
		
		statPanel = new JPanel();
		statPanel.setLayout(new BorderLayout());
		statPanel.setOpaque(false);
		statPanel.setPreferredSize(new Dimension(0, 80));
		
		statLabel = new JLabel();
		statLabel.setText("���� ���õ��� �������ּ���.");
		statLabel.setHorizontalAlignment(JLabel.CENTER);
		statLabel.setVerticalAlignment(JLabel.BOTTOM);
		statLabel.setFont(SystemManager.normalTTF);
		statLabel.setPreferredSize(new Dimension(0, 40));
		
		substatLabel = new JLabel();
		substatLabel.setText("�ɷ�ġ�� ���õ�, ����, ���̽��� ��� ���� �Ŀ� ���� �ϴܿ� ���� �� ������ ������ �ݿ��˴ϴ�.");
		substatLabel.setHorizontalAlignment(JLabel.CENTER);
		substatLabel.setVerticalAlignment(JLabel.TOP);
		substatLabel.setFont(SystemManager.smallTTF);
		substatLabel.setPreferredSize(new Dimension(0, 40));
		
		JPanel cook_levelPanel = new JPanel();
		cook_levelPanel.setLayout(new BorderLayout());
		cook_levelPanel.setPreferredSize(new Dimension(0, 100));
			
		JLabel cook_levelLabel = new JLabel();
		cook_levelLabel.setText("���õ�");
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
					e.consume(); // �Էµ� ���ڸ� �Һ��Ͽ� JTextField�� �Էµ��� �ʵ��� ��
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
            			System.out.println("���ڸ� �Է��ϼ���");
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
		cook_now1_label.setText("����� 1���");
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
		cook_now2_label.setText("����� 2���");
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
		cook_now3_label.setText("����� 3���");
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
		
		// ���� �Է�â �� ����
		statueLabel = new JLabel();
		statueLabel.setText("�����ϰ� �ִ� ���� ȿ���� �ϴܿ� �������ּ���.");
		statueLabel.setHorizontalAlignment(JLabel.CENTER);
		statueLabel.setVerticalAlignment(JLabel.CENTER);
		statueLabel.setFont(SystemManager.normalTTF);
		statueLabel.setPreferredSize(new Dimension(0, 100));
		
		// 1��� ����ġ, 2��� ����ġ, ��� �̼Ҹ�, �丮�� Ż Ȯ��, �丮 �뼺�� Ȯ��, ���� �ߵ� Ȯ��
		// ���� �Է�â ����
		JPanel cook_statue_1grade = makePanel("1��� ����ġ", cook_statue);
		JPanel cook_statue_2grade = makePanel("2��� ����ġ", cook_statue);
		JPanel cook_statue_free = makePanel("��� �̼Ҹ� Ȯ��", cook_statue);
		JPanel cook_statue_death = makePanel("�丮�� Ż Ȯ��", cook_statue);
		JPanel cook_statue_success = makePanel("�丮 �뼺�� Ȯ��", cook_statue);
		JPanel cook_statue_grace = makePanel("���� �ߵ� Ȯ��", cook_statue);

		// ���̽� �Է�â �� ����
		diceLabel = new JLabel();
		diceLabel.setText("�����ϰ� �ִ� ���̽� ȿ���� �ϴܿ� �������ּ���.");
		diceLabel.setHorizontalAlignment(JLabel.CENTER);
		diceLabel.setVerticalAlignment(JLabel.CENTER);
		diceLabel.setFont(SystemManager.normalTTF);
		diceLabel.setPreferredSize(new Dimension(0, 100));

		// 1��� ����ġ, 2��� ����ġ, �丮�� Ż Ȯ��
		// ���̽� �Է�â ����
		JPanel cook_dice_1grade = makePanel("1��� ����ġ", cook_dice);
		JPanel cook_dice_2grade = makePanel("2��� ����ġ", cook_dice);
		JPanel cook_dice_death = makePanel("�丮�� Ż Ȯ��", cook_dice);
		
		/* ====================================================	*/
		/* ==================== ���� �� =========================	*/
		/* ====================================================	*/
		
		// ��� ���� �г�
//		paddingPanel = new JPanel();
//		paddingPanel.setLayout(new BorderLayout());
//		paddingPanel.setPreferredSize(new Dimension(1, 800));
		
		/* ====================================================	*/
		/* ================== ��� ���� �� =====================	*/
		/* ====================================================	*/
		
		// ������ �г�
		mainRightPanel = new JPanel();
		mainRightPanel.setLayout(new BorderLayout());
		mainRightPanel.setPreferredSize(new Dimension(800, 770));
				
		// �ü� �г�
		pricePanel = new JPanel();
		pricePanel.setLayout(new BorderLayout());
		pricePanel.setPreferredSize(new Dimension(790, 150));
				
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
		priceTextArea.setBorder(new LineBorder(Color.BLACK, 1));
		priceTextArea.setOpaque(false);
		priceTextArea.setFont(SystemManager.smallTTF);
		priceTextArea.setPreferredSize(new Dimension(780, 1600));
		// ��ũ�� �г�
		priceScrollPane = new JScrollPane(priceTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		priceScrollPane.setBorder(new LineBorder(Color.WHITE, 1));
		priceScrollPane.getVerticalScrollBar().setUnitIncrement(5);
		priceScrollPane.setPreferredSize(new Dimension(800, 110));
		
		// ����Ʈ �г�
		priceListPanel = new JPanel();
		priceListPanel.setLayout(new GridBagLayout());
		priceListPanel.setOpaque(false);
		priceListPanel.setPreferredSize(new Dimension(780, 620));
		// ��ũ�� �г�
		priceListScrollPane = new JScrollPane(priceListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		priceListScrollPane.setBorder(new LineBorder(Color.WHITE, 1));
		priceListScrollPane.getVerticalScrollBar().setUnitIncrement(5);
		priceListScrollPane.setPreferredSize(new Dimension(800, 570));
		
		/* ====================================================	*/
		/* ==================== ������ �� =======================	*/
		/* ====================================================	*/
		
		// �ϴܹ�
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 1, 0));
		bottomPanel.setPreferredSize(new Dimension(800, 30));
		
		backBtn = new JButton();
		backBtn.setText("�������� �ʰ� �ݱ�");
		backBtn.setFont(SystemManager.smallTTF);
		backBtn.addActionListener(new ActionListener() {
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
		backBtn.setPreferredSize(new Dimension(200, 30));

		saveBtn = new JButton();
		saveBtn.setText("����");
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
		applyBtn.setText("����");
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
		exitBtn.setText("���� �� �ݱ�");
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
		/* ==================== �ϴܹ� �� =======================	*/
		/* ====================================================	*/
				
		// ���� �гο� ������Ʈ �߰�
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
		
		// ������ �гο� ������Ʈ �߰�
		pricePanel.add(priceLabel, BorderLayout.NORTH);
		pricePanel.add(priceScrollPane, BorderLayout.CENTER);
		
		mainRightPanel.add(pricePanel, BorderLayout.NORTH);
		mainRightPanel.add(priceListScrollPane, BorderLayout.CENTER);
		
		bottomPanel.add(saveBtn);
		bottomPanel.add(applyBtn);
		bottomPanel.add(backBtn);
		bottomPanel.add(exitBtn);
		
		// ���� �гο� ���� �߰�
		mainPanel.add(mainLeftPanel, BorderLayout.WEST);
//		mainPanel.add(paddingPanel, BorderLayout.CENTER);
		mainPanel.add(mainRightPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		add(mainPanel);

//		gbc = new GridBagConstraints();
//		gbc.fill = GridBagConstraints.CENTER;
//		gbc.anchor = GridBagConstraints.CENTER;
		
		// ���� �ε�
		loadSpec();
		
		setVisible(true);
	}
	
	public boolean isEmpty() {
		String errorCode = "";
		if(cook_levelTextField.getText().equals("") || Integer.parseInt(cook_levelTextField.getText()) == 0)				// ���õ�
			errorCode += "���õ��� ��� �ְų� 0 �Դϴ�!\n";
		if(cook_statue.get("1��� ����ġ").getText().equals(""))		// ���� ����
			errorCode += "[����] 1��� ����ġ�� ������ϴ�!\n";
		if(cook_statue.get("2��� ����ġ").getText().equals(""))
			errorCode += "[����] 2��� ����ġ�� ������ϴ�!\n";
		if(cook_statue.get("��� �̼Ҹ� Ȯ��").getText().equals(""))
			errorCode += "[����] ��� �̼Ҹ� Ȯ���� ������ϴ�!\n";
		if(cook_statue.get("�丮�� Ż Ȯ��").getText().equals(""))
			errorCode += "[����] �丮�� Ż Ȯ���� ������ϴ�!\n";
		if(cook_statue.get("�丮 �뼺�� Ȯ��").getText().equals(""))
			errorCode += "[����] �丮 �뼺�� Ȯ���� ������ϴ�!\n";
		if(cook_statue.get("���� �ߵ� Ȯ��").getText().equals(""))		// ���� ��
			errorCode += "[����] ���� �ߵ� Ȯ���� ������ϴ�!\n";
		if(cook_dice.get("1��� ����ġ").getText().equals(""))			// ���̽� ����
			errorCode += "[���̽�] 1��� ����ġ�� ������ϴ�!\n";
		if(cook_dice.get("2��� ����ġ").getText().equals(""))
			errorCode += "[���̽�] 2��� ����ġ�� ������ϴ�!\n";
		if(cook_dice.get("�丮�� Ż Ȯ��").getText().equals(""))		// ���̽� ��
			errorCode += "[���̽�] �丮�� Ż Ȯ���� ������ϴ�!\n";
		
		if(errorCode.equals(""))
			return false;
		else {
			JOptionPane.showMessageDialog(null, errorCode);
			return true;
		}
	}
	
	public void loadSpec() {
		cook_levelTextField.setText("" + infoList.getCookLevel());
		cook_statue.get("1��� ����ġ").setText("" + info_cook_statue.get("1��� ����ġ"));
		cook_statue.get("2��� ����ġ").setText("" + info_cook_statue.get("2��� ����ġ"));
		cook_statue.get("��� �̼Ҹ� Ȯ��").setText("" + info_cook_statue.get("��� �̼Ҹ� Ȯ��"));
		cook_statue.get("�丮�� Ż Ȯ��").setText("" + info_cook_statue.get("�丮�� Ż Ȯ��"));
		cook_statue.get("�丮 �뼺�� Ȯ��").setText("" + info_cook_statue.get("�丮 �뼺�� Ȯ��"));
		cook_statue.get("���� �ߵ� Ȯ��").setText("" + info_cook_statue.get("���� �ߵ� Ȯ��"));
		
		cook_dice.get("1��� ����ġ").setText("" + info_cook_dice.get("1��� ����ġ"));
		cook_dice.get("2��� ����ġ").setText("" + info_cook_dice.get("2��� ����ġ"));
		cook_dice.get("�丮�� Ż Ȯ��").setText("" + info_cook_dice.get("�丮�� Ż Ȯ��"));
		applySpec(false);
	}
	
	public void saveSpec() {
		// ����
		// 1��� ����ġ, 2��� ����ġ, ��� �̼Ҹ� Ȯ��, �丮�� Ż Ȯ��, �丮 �뼺�� Ȯ��, ���� �ߵ� Ȯ��
		HashMap<String, Double> statue = new HashMap<>();
		statue.put("1��� ����ġ", Double.parseDouble(cook_statue.get("1��� ����ġ").getText()));
		statue.put("2��� ����ġ", Double.parseDouble(cook_statue.get("2��� ����ġ").getText()));
		statue.put("��� �̼Ҹ� Ȯ��", Double.parseDouble(cook_statue.get("��� �̼Ҹ� Ȯ��").getText()));
		statue.put("�丮�� Ż Ȯ��", Double.parseDouble(cook_statue.get("�丮�� Ż Ȯ��").getText()));
		statue.put("�丮 �뼺�� Ȯ��", Double.parseDouble(cook_statue.get("�丮 �뼺�� Ȯ��").getText()));
		statue.put("���� �ߵ� Ȯ��", Double.parseDouble(cook_statue.get("���� �ߵ� Ȯ��").getText()));
		
		// ���̽�
		// 1��� ����ġ, 2��� ����ġ, �丮�� Ż Ȯ��
		HashMap<String, Double> dice = new HashMap<>();
		dice.put("1��� ����ġ", Double.parseDouble(cook_dice.get("1��� ����ġ").getText()));
		dice.put("2��� ����ġ", Double.parseDouble(cook_dice.get("2��� ����ġ").getText()));
		dice.put("�丮�� Ż Ȯ��", Double.parseDouble(cook_dice.get("�丮�� Ż Ȯ��").getText()));
		
		infoList.setCookLevel(Integer.parseInt(cook_levelTextField.getText()));
		infoList.setStatueCook(statue);
		infoList.setDiceCook(dice);
		infoList.saveSpecInfo();
		
		JOptionPane.showMessageDialog(null, "���� �Ǿ����ϴ�.");
	}
	
	public void applySpec(boolean alert) {
		// cook_spec �迭
		// ���õ�, 1��� ����ġ, 2��� ����ġ, 3��� ����ġ, ��� �̼Ҹ� Ȯ��, �丮�� Ż Ȯ��, �丮 �뼺�� Ȯ��, ���� �ߵ� Ȯ��
		double grade1 = 0 + Double.parseDouble(cook_statue.get("1��� ����ġ").getText()) + Double.parseDouble(cook_dice.get("1��� ����ġ").getText());
		double grade2 = 30 + Double.parseDouble(cook_statue.get("2��� ����ġ").getText()) + Double.parseDouble(cook_dice.get("2��� ����ġ").getText());
		double grade3 = 70;
		double free = Double.parseDouble(cook_statue.get("��� �̼Ҹ� Ȯ��").getText());
		double death = 7 - Double.parseDouble(cook_statue.get("�丮�� Ż Ȯ��").getText()) - Double.parseDouble(cook_dice.get("�丮�� Ż Ȯ��").getText());
		double success = Double.parseDouble(cook_statue.get("�丮 �뼺�� Ȯ��").getText());
		double grace = Double.parseDouble(cook_statue.get("���� �ߵ� Ȯ��").getText());
		
		// ���õ� ������ ���� ����ġ ���
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
		
		spec_map.get("���õ�").setText("" + level);
		spec_map.get("1���").setText("" + grade1 + "%");
		spec_map.get("2���").setText("" + grade2 + "%");
		spec_map.get("3���").setText("" + grade3 + "%");
		spec_map.get("��� �̼Ҹ� Ȯ��").setText("" + String.format("%.2f", free) + "%");
		spec_map.get("�丮�� Ż Ȯ��").setText("" + String.format("%.2f", death) + "%");
		spec_map.get("�丮 �뼺�� Ȯ��").setText("" + String.format("%.2f", success) + "%");
		spec_map.get("���� �ߵ� Ȯ��").setText("" + String.format("%.2f", grace) + "%");
		
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
			JOptionPane.showMessageDialog(null, "���� �Ǿ����ϴ�.");
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
		if(name.equals("���õ�")) {
			textField.setText("1");
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!Character.isDigit(c) && c != '\b') {
						e.consume(); // �Էµ� ���ڸ� �Һ��Ͽ� JTextField�� �Էµ��� �ʵ��� ��
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
						e.consume(); // �Էµ� ���ڸ� �Һ��Ͽ� JTextField�� �Էµ��� �ʵ��� ��
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
		
		if(name.equals("���õ�")) {
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
