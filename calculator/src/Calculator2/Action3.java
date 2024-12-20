package Calculator2;

import javax.swing.*;
import java.awt.event.*;

public class Action3 implements ActionListener {
    private JTextField textField;   // 수식 입력용 필드
    private JTextField resultField; // 결과 표시용 필드
    private JTextArea historyField; // 계산 기록 표시용 필드
    private static boolean firstInput = true; // 첫 입력 상태
    private static double operand1 = 0;  // 첫 번째 피연산자
    private static double operand2 = 0;  // 두 번째 피연산자
    private static double previousResult = 0;  // 이전 계산값 (결과 기억)
    private static String operator = ""; // 연산자

    // 생성자
    public Action3(JTextField textField, JTextField resultField, JTextArea historyField) {
        this.textField = textField;
        this.resultField = resultField;
        this.historyField = historyField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // 숫자와 소수점 처리
        if ("0123456789.".contains(command)) {
            if (firstInput) {
                textField.setText(command);  // 새로운 입력 시작
                firstInput = false;
            } else {
                textField.setText(textField.getText() + command); // 숫자나 소수점을 추가
            }
        }
        // 연산자 처리: +, -, ×, ÷
        else if ("+-×÷".contains(command)) {
            // 첫 번째 연산자 입력 시 operand1에 값 저장
            if (operator.isEmpty()) {
                operand1 = Double.parseDouble(textField.getText());
            } else {
                // 연산자가 다시 눌리면 이전 결과(operand1)를 사용하여 연산
                operand2 = Double.parseDouble(textField.getText());
                performCalculation();
            }
            operator = command;  // 연산자 업데이트
            textField.setText(""); // 다음 피연산자 입력을 위해 입력 필드 비움
        }
        // "=" 버튼 처리 (계산 실행)
        else if (command.equals("=")) {
            operand2 = Double.parseDouble(textField.getText());
            performCalculation();
            operator = ""; // 연산자 초기화
        }
        // "C" 버튼 처리 (전체 초기화)
        else if (command.equals("C")) {
            textField.setText("");  // 입력 필드 초기화
            resultField.setText("");  // 결과 필드 초기화
            historyField.setText(""); // 계산 기록 초기화
            operand1 = operand2 = previousResult = 0;  // 연산 값 초기화
            operator = "";  // 연산자 초기화
            firstInput = true;  // 입력 초기화
        }
        // "CE" 버튼 처리 (현재 입력만 초기화)
        else if (command.equals("CE")) {
            textField.setText("");  // 현재 입력만 초기화
        }
        // "Exit" 버튼 처리 (계산기 종료)
        else if (command.equals("Exit")) {
            System.exit(0); // 계산기 종료
        }
    }

    // 계산 수행 및 기록 업데이트
    private void performCalculation() {
        double result = 0;
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "×":
                result = operand1 * operand2;
                break;
            case "÷":
                if (operand2 != 0) {
                    result = operand1 / operand2;
                } else {
                    resultField.setText("Error");  // 나누기 0 에러 처리
                    historyField.append(operand1 + " ÷ " + operand2 + " = Error (Division by 0)\n");
                    return;
                }
                break;
            default:
                break;
        }

        // 계산 결과 출력
        previousResult = result;
        resultField.setText((result == (int) result) ? String.format("%d", (int) result) : String.valueOf(result));
        historyField.append(operand1 + " " + operator + " " + operand2 + " = " + result + "\n");

        operand1 = result;  // 결과값을 operand1에 저장하여 이후 계산에 사용
        firstInput = true; // 다음 입력을 위해 초기화
    }
}
