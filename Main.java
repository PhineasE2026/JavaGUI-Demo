import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    // Declare GUI objects & story progression variables
    private static JTextArea storyArea;
    private static JButton upButton, rightButton, downButton, leftButton, yesButton, noButton, restartButton;
    private static String state;
    private static String scenario;
    private static String startText = "You're standing at the SW entrance of Central Park, with Columbus Circle behind you. Which way do you want to go? (Click a DIRECTION)";

    // *** STORY PROGRESSION SECTION ***
    static class DirectionListener implements ActionListener {
        private String command;

        public DirectionListener(String command) {
            this.command = command;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (command.equals("Restart")) {
                restartGame();
            } 
            else if (state.equals("start")) {
                processStateStart();
            } 
            else if (state.equals("s1_tavern") && scenario.equals("s1_none")) {
                processWeddingChoice();
            } 
            else if (state.equals("s1_tavern") && scenario.equals("s1_noWedding")) {
                processSheepsMeadow();
            }
            // ADD MORE ELSE IF STATEMENTS FOR FURTHER STORY BRANCHES!!!
        }

        private void processStateStart() {
            if (command.equals("Up")) {
                storyArea.setText("You walk north to Tavern on the Green and see a lively wedding celebration. Do you want to crash it? (Click YES/NO)");
                toggleDirectionButtons(false);
                toggleYesNoButtons(true);
                state = "s1_tavern";
                scenario = "s1_none";
            }
        }

        private void processWeddingChoice() {
            if (command.equals("Yes")) {
                storyArea.setText("You attempt to sneak into the wedding and promptly get arrested! Game over.");
                endGame();
            } else if (command.equals("No")) {
                storyArea.setText("You pass the wedding and keep walking, reaching Sheep's Meadow. It's peaceful here. (Click a DIRECTION)");
                toggleYesNoButtons(false);
                toggleDirectionButtons(true);
                scenario = "s1_noWedding";
            }
        }

        private void processSheepsMeadow() {
            if (command.equals("Up")) {
                storyArea.setText("You walk further north and see Belvedere Castle in the distance. Do you want to go inside?");
                toggleDirectionButtons(false);
                toggleYesNoButtons(true);
                state = "s2_castle";
            }
        }
    }

    // *** GUI CUSTOMIZATION BELOW ***
    public static void main(String[] args) {
        JFrame frame = new JFrame("Central Park Adventure");
        JPanel canvas = new JPanel();
        canvas.setPreferredSize(new Dimension(500, 500));
        canvas.setLayout(new BorderLayout());
        Color backgroundColor = new Color(0xbee1e6);
        canvas.setBackground(backgroundColor);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(backgroundColor);

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setBackground(backgroundColor);

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("CentralPark.png");
        Image scaledImage = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imagePanel.add(imageLabel);

        contentPanel.add(imagePanel, BorderLayout.NORTH);

        storyArea = new JTextArea();
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setMargin(new Insets(8, 8, 8, 8));
        storyArea.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
        storyArea.setText(startText);

        JScrollPane storyScrollPane = new JScrollPane(storyArea);
        storyScrollPane.setPreferredSize(new Dimension(500, 200));
        contentPanel.add(storyScrollPane, BorderLayout.CENTER);

        canvas.add(contentPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        upButton = createButton("⬆", "Up", new Color(0x695c99));
        rightButton = createButton("➡", "Right", new Color(0x695c99));
        downButton = createButton("⬇", "Down", new Color(0x695c99));
        leftButton = createButton("⬅", "Left", new Color(0x695c99));
        yesButton = createButton("✓ YES", "Yes", new Color(0x247b7b));
        noButton = createButton("✗ NO ", "No", new Color(0xaf4d98));
        restartButton = createButton("RESTART", "Restart", new Color(0x979dac));

        gbc.gridx = 1; gbc.gridy = 0;
        buttonPanel.add(upButton, gbc);
        gbc.gridx = 2; gbc.gridy = 1;
        buttonPanel.add(rightButton, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        buttonPanel.add(downButton, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        buttonPanel.add(leftButton, gbc);

        JPanel spacerPanel = new JPanel();
        spacerPanel.setBackground(backgroundColor);
        gbc.gridx = 3; gbc.gridy = 1; gbc.gridheight = 3;
        buttonPanel.add(spacerPanel, gbc);

        gbc.gridx = 4; gbc.gridy = 0; gbc.gridheight = 1;
        buttonPanel.add(yesButton, gbc);
        gbc.gridx = 4; gbc.gridy = 1;
        buttonPanel.add(noButton, gbc);
        gbc.gridx = 4; gbc.gridy = 2;
        buttonPanel.add(restartButton, gbc);

        yesButton.setEnabled(false);
        noButton.setEnabled(false);

        canvas.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(canvas);

        state = "start";
        scenario = "s0";

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static JButton createButton(String text, String command, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.addActionListener(new DirectionListener(command));
        return button;
    }

    // *** DO NOT MODIFY THE CODE BELOW ***
    private static void restartGame() {
        storyArea.setText(startText);
        state = "start";
        scenario = "s0";
        toggleDirectionButtons(true);
        toggleYesNoButtons(false);
    }

    private static void toggleDirectionButtons(boolean enable) {
        upButton.setEnabled(enable);
        rightButton.setEnabled(enable);
        downButton.setEnabled(enable);
        leftButton.setEnabled(enable);
    }

    private static void toggleYesNoButtons(boolean enable) {
        yesButton.setEnabled(enable);
        noButton.setEnabled(enable);
    }

    private static void endGame() {
        toggleDirectionButtons(false);
        toggleYesNoButtons(false);
    }
}
