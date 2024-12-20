package Calculator3;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    private JFrame frame;
    private JTextField display;      // 결과를 표시하는 텍스트 필드
    private JLabel equationLabel;   // 수식을 표시하는 라벨
    private Calculator calculator;  // Calculator 인스턴스

    public CalculatorGUI() {
        calculator = new Calculator(); // Calculator 초기화
        createUI();  // GUI 생성
    }

    private void createUI() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(330, 550); // 기본 창 크기
        frame.setMinimumSize(new Dimension(330, 550)); // 최소 크기 설정
        frame.setLayout(new BorderLayout());

        // 텍스트 영역을 담을 상위 패널 생성
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.setPreferredSize(new Dimension(330, 100)); // 텍스트 영역 전체 크기 설정

        // 수식 디스플레이 설정
        equationLabel = new JLabel(" "); // 초기에는 빈 값
        equationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        equationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        equationLabel.setPreferredSize(new Dimension(330, 50)); // 수식 창 크기 설정
        textPanel.add(equationLabel, BorderLayout.NORTH);

        // 결과 디스플레이 설정
        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setPreferredSize(new Dimension(330, 50)); // 결과 창 크기 설정
        textPanel.add(display, BorderLayout.SOUTH);

        // 텍스트 패널을 프레임의 북쪽에 추가
        frame.add(textPanel, BorderLayout.NORTH);

        // 버튼 패널 설정
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] buttons = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "C", "0", "=", "/"
        };

        // 버튼 배치
        int row = 0, col = 0;
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));

            // 버튼 색상 설정
            if (text.equals("=")) {
                button.setBackground(Color.RED);
                button.setForeground(Color.WHITE);
            } else if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/")) {
                button.setBackground(Color.BLUE);
                button.setForeground(Color.WHITE);
            } else if (text.equals("C")) {
                button.setBackground(Color.LIGHT_GRAY);
                button.setForeground(Color.BLACK);
            } else {
                button.setBackground(Color.GRAY);
                button.setForeground(Color.BLACK);
            }

            button.setFocusPainted(false); // 버튼 클릭 시 테두리 제거
            button.addActionListener(new ButtonClickListener());

            gbc.gridx = col; // 현재 열 위치
            gbc.gridy = row; // 현재 행 위치
            gbc.weightx = 1.0; // 버튼의 가로 크기 비율
            gbc.weighty = 0.8; // 버튼의 세로 크기 비율
            buttonPanel.add(button, gbc);

            col++;
            if (col > 3) { // 한 행에 4개의 버튼을 추가 후 다음 행으로 이동
                col = 0;
                row++;
            }
        }

        frame.add(buttonPanel, BorderLayout.CENTER); // 버튼 패널을 중앙에 배치
        frame.setVisible(true);  // 화면을 보이게 함
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();
            try {
                calculator.handleInput(input); // Calculator로 입력 전달
                display.setText(calculator.getDisplayText()); // 결과를 표시

                // 수식 업데이트
                String equation = calculator.getEquationText();
                equationLabel.setText(equation); // 수식 표시
            } catch (Exception ex) {
                display.setText("Error");
                equationLabel.setText(" "); // 오류 발생 시 수식 초기화
            }
        }
    }
}
