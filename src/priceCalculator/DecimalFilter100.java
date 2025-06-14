
package priceCalculator;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

// �Ҽ��� ��° �ڸ������� �Է� ��� + 100 �ʰ� �� �ڵ� 100���� ����
public class DecimalFilter100 extends DocumentFilter {
	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {
		StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
		sb.insert(offset, string);
		
		String validated = getValidDecimal(sb.toString());
		if (validated != null) {
			super.replace(fb, 0, fb.getDocument().getLength(), validated, attr);
		}
	}
	
	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {
		StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
		sb.replace(offset, offset + length, text);
		
		String validated = getValidDecimal(sb.toString());
		if (validated != null) {
			super.replace(fb, 0, fb.getDocument().getLength(), validated, attrs);
		}
	}
	
	private String getValidDecimal(String text) {
		// ������ ���
		if (text.isEmpty()) return "";
		
		// ��ȿ�� ���� �������� Ȯ�� (�Ҽ� ��°�ڸ�����)
		if (!text.matches("\\d+")) return null;
		
		try {
			int value = Integer.parseInt(text);
			if (value > 100) {
				return "100";  // 100 �ʰ� �� �ڵ� ����
			} else {
				return text;
			}
		} catch (NumberFormatException e) {
			return null;
		}
	}
}