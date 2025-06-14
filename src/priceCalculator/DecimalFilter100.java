
package priceCalculator;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

// 소수점 둘째 자리까지만 입력 허용 + 100 초과 시 자동 100으로 고정
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
		// 공백은 허용
		if (text.isEmpty()) return "";
		
		// 유효한 숫자 형태인지 확인 (소수 둘째자리까지)
		if (!text.matches("\\d+")) return null;
		
		try {
			int value = Integer.parseInt(text);
			if (value > 100) {
				return "100";  // 100 초과 시 자동 고정
			} else {
				return text;
			}
		} catch (NumberFormatException e) {
			return null;
		}
	}
}