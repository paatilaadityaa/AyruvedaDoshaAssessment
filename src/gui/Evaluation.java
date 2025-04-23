package gui;
import database.DatabaseManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class Evaluation extends JFrame {
    private String username;
    private JPanel panel;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private int currentQuestion = 0;
    private Map<Integer, Integer[]> doshaCountsMap = new HashMap<>();

    private static final String[] QUESTIONS = {
            "How is your body build?", "What is your hair type?", "What is the color of your hair?"
    };
    private static final String[][] OPTIONS = {
            {"Thin and lean", "Medium", "Well built"},
            {"Dry with split ends", "Normal, thin, more hair fall", "Greasy heavy"},
            {"Pale Brown", "Grey", "Black"}
    };

    public Evaluation(String username) {
        this.username = username;
        initialize();
    }

    private void initialize() {
        setTitle("Dosha Assessment");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panel = new JPanel(new GridLayout(5, 1));
        setContentPane(panel);
        
        questionLabel = new JLabel();
        panel.add(questionLabel);
        optionButtons = new JRadioButton[3];
        for (int i = 0; i < 3; i++) {
            optionButtons[i] = new JRadioButton();
            panel.add(optionButtons[i]);
            optionButtons[i].setActionCommand(String.valueOf(i + 1));
            optionButtons[i].addActionListener(this::handleOptionButtonClick);
        }
        
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> handleNextButton());
        panel.add(nextButton);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> handleSubmitButton());
        panel.add(submitButton);

        setQuestion(0);
    }

    private void handleOptionButtonClick(ActionEvent e) {
        doshaCountsMap.put(currentQuestion, new Integer[]{Integer.parseInt(e.getActionCommand()), 0, 0});
    }

    private void handleNextButton() {
        if (currentQuestion < QUESTIONS.length - 1) {
            currentQuestion++;
            setQuestion(currentQuestion);
        } else {
            handleSubmitButton();
        }
    }

    private void handleSubmitButton() {
        int pittaDosha = 0, kaphaDosha = 0, vataDosha = 0;
        for (Integer[] doshaCount : doshaCountsMap.values()) {
            switch (doshaCount[0]) {
                case 1 -> pittaDosha++;
                case 2 -> kaphaDosha++;
                case 3 -> vataDosha++;
            }
        }
        String dominantDosha = (kaphaDosha >= pittaDosha && kaphaDosha >= vataDosha) ? "Kapha" :
                (pittaDosha >= kaphaDosha && pittaDosha >= vataDosha) ? "Pitta" : "Vata";

        DatabaseManager.saveResult(username, dominantDosha);
        JOptionPane.showMessageDialog(this, "Your dominant dosha type is " + dominantDosha);
    }

    private void setQuestion(int index) {
        questionLabel.setText(QUESTIONS[index]);
        for (int i = 0; i < 3; i++) {
            optionButtons[i].setText(OPTIONS[index][i]);
            optionButtons[i].setSelected(false);
        }
    }
}