package Calculator2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    private JFrame frame;
    private JTextField textField;  // 수식 입력용 필드
    private JTextField resultField; // 결과 표시용 필드
    private JTextField operatorField; // 연산자 표시용 필드
    private JPanel buttonPanel; // 버튼 패널

    // 생성자
    public Calculator() {
        // JFrame 생성
        frame = new JFrame("계산기");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // 가로 크기 확장
        frame.setLayout(new BorderLayout(10, 10));
        frame.setMinimumSize(new Dimension(800, 600));

        // 좌측 패널 (텍스트 필드와 버튼)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(10, 10));

        // 수식 입력 텍스트 필드
        textField = TextFieldCreator.createFormulaTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        leftPanel.add(textField, BorderLayout.NORTH);

        // 연산자 표시용 필드와 결과 표시용 필드를 2행 1열 GridLayout으로 배치
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(2, 1)); // 2행 1열 GridLayout

        // 연산자 표시 필드
        operatorField = TextFieldCreator.createOperatorTextField();
        operatorField.setFont(new Font("Arial", Font.BOLD, 26));
        operatorField.setHorizontalAlignment(SwingConstants.RIGHT);
        operatorField.setEditable(false); // 연산자 필드는 편집 불가
        resultPanel.add(operatorField);

        // 결과 표시 필드
        resultField = TextFieldCreator.createResultTextField();
        resultField.setFont(new Font("Arial", Font.BOLD, 40));
        resultField.setHorizontalAlignment(SwingConstants.RIGHT);
        resultField.setEditable(false); // 결과 필드는 편집 불가
        resultPanel.add(resultField);

        leftPanel.add(resultPanel, BorderLayout.CENTER);

        // 버튼 패널
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5)); // 6행 4열 그리드 레이아웃
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 버튼 배열
        String[] buttons = {
            "%", "CE", "C", "Exit",
            "1/x", "x²", "√x", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "+/-", "0", ".", "="
        };

        // 버튼 추가
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(80, 60));

            // 버튼 이벤트 리스너 추가
            button.addActionListener(new Action3(textField, resultField, null));

            buttonPanel.add(button);
        }

        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        // JSplitPane으로 좌측 패널과 우측 패널을 나눔
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, new JPanel());
        splitPane.setDividerLocation(500); // 초기 분할 위치 (좌측 500px)
        splitPane.setResizeWeight(0.7); // 크기 조정 비율
        splitPane.setContinuousLayout(true);

        frame.add(splitPane, BorderLayout.CENTER);
    }

    // 계산기 화면을 표시하는 메서드
    public void show() {
        frame.setVisible(true);
    }
}
