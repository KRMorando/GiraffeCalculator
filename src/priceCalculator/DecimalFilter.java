package priceCalculator;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

class DecimalFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.insert(offset, string);

        if (isValidDecimal(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        } // else: ����
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.replace(offset, offset + length, text);

        if (isValidDecimal(sb.toString())) {
            super.replace(fb, offset, length, text, attrs);
        } // else: ����
    }

    private boolean isValidDecimal(String text) {
        // ���� ���
        if (text.isEmpty()) return true;

        // ���� or �Ҽ����� ���� + �Ҽ��� �ִ� 1��
        if (!text.matches("\\d*(\\.\\d{0,2})?")) return false;

        return true;
    }
}