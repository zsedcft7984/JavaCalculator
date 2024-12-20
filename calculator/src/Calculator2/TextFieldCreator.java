package Calculator2;

import javax.swing.*;
import java.awt.*;

public class TextFieldCreator {

    // 수식 입력용 텍스트 필드를 생성하는 메서드
    public static JTextField createFormulaTextField() {
        JTextField textField = new JTextField("0");
        textField.setFont(new Font("Arial", Font.BOLD, 60));  // 폰트 크기 키우기
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // FontMetrics를 사용하여 폰트 크기에 맞는 텍스트 필드 크기 계산
        FontMetrics fm = textField.getFontMetrics(textField.getFont());
        int width = fm.stringWidth(textField.getText());
        int height = fm.getHeight();

        // 텍스트 필드의 크기를 텍스트에 맞게 조정
        textField.setPreferredSize(new Dimension(width + 20, height + 20));  // 여백을 더해줌

        return textField;
    }

    // 연산자 표시용 텍스트 필드를 생성하는 메서드
    public static JTextField createOperatorTextField() {
        JTextField operatorField = new JTextField();
        operatorField.setFont(new Font("Arial", Font.BOLD, 26));  // 연산자 폰트 크기 설정
        operatorField.setHorizontalAlignment(SwingConstants.RIGHT);
        operatorField.setBackground(Color.WHITE);
        operatorField.setEditable(false);  // 편집 불가
        operatorField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // FontMetrics를 사용하여 결과 텍스트 필드의 크기 계산
        FontMetrics fmOperator = operatorField.getFontMetrics(operatorField.getFont());
        int widthOperator = fmOperator.stringWidth(operatorField.getText());
        int heightOperator = fmOperator.getHeight();
        operatorField.setPreferredSize(new Dimension(widthOperator + 20, heightOperator + 20));  // 여백을 더해줌

        return operatorField;
    }

    // 결과 표시용 텍스트 필드를 생성하는 메서드
    public static JTextField createResultTextField() {
        JTextField resultField = new JTextField();
        resultField.setFont(new Font("Arial", Font.BOLD, 40));  // 폰트 크기 설정
        resultField.setHorizontalAlignment(SwingConstants.RIGHT);
        resultField.setBackground(Color.WHITE);
        resultField.setEditable(false);  // 편집 불가
        resultField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // FontMetrics를 사용하여 결과 텍스트 필드의 크기 계산
        FontMetrics fmResult = resultField.getFontMetrics(resultField.getFont());
        int widthResult = fmResult.stringWidth(resultField.getText());
        int heightResult = fmResult.getHeight();
        resultField.setPreferredSize(new Dimension(widthResult + 20, heightResult + 20));  // 여백을 더해줌

        return resultField;
    }
}
