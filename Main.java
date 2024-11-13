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
    private static String startText = "It's the apocalypse, and you're currently attending a register in Costco. People are bustling around the store, stacking on top of each other. Will you stay at the CostCo? (Click YES/NO)";

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
            else if (state.equals("s2_castle")) {
                processLeavingChoice();
            }
            else if (state.equals("s2_bread")) {
                processBreadChoice();
            }
            // ADD MORE ELSE IF STATEMENTS FOR FURTHER STORY BRANCHES!!!
        }

        private void processStateStart() {
            if (command.equals("Yes")) {
                storyArea.setText("You took the cash from the register and crouched under the desk. You see a fire axe over by the wall left, snacks and food forward, and a horde of zombies that broke in from your right. (Click a DIRECTION)");
                toggleDirectionButtons(true);
                toggleYesNoButtons(false);
                state = "s1_tavern";
                scenario = "s1_none";
            }
        }

        private void processWeddingChoice() {
            if (command.equals("Right")) {
                storyArea.setText("You rush the zombies and are immediately overwhelmed. Game over. (RESTART)");
                endGame();
            } else if (command.equals("Left")) {
                storyArea.setText("You sprinted over to the end of the registers and used your mega elbow attack to break the glass. You reached in for the axe and took it out, holding it high above your head. Will you go left and leave the store with your axe, or go right and go to the bread aisle? (Click a DIRECTION)");
                toggleYesNoButtons(false);
                toggleDirectionButtons(true);
                scenario = "s1_noWedding";
            }
        }

        private void processSheepsMeadow() {
            if (command.equals("Right")) {
                storyArea.setText("You slide over to the bread aisle but a horde of zombies pops out from around the corner! Hero... I'm afraid you may not survive this one... You have to use your secret ability... (Click YES/NO)");
                toggleDirectionButtons(false);
                toggleYesNoButtons(true);
                state = "s2_bread";
            }
            else if (command.equals("Left")) {
                storyArea.setText("You barreled out through the front doors and rose your axe at the zombies. They cowered away in fear. Go up to the warehouse or right to the beachfront? (CLICK A DIRECTION)");
                toggleDirectionButtons(true);
                toggleYesNoButtons(false);
                state = "s2_castle";
            }
        }

        private void processLeavingChoice() {
            if (command.equals("Up")) {
                storyArea.setText("After a long walk, you made it to the warehouse. As you opened the doors, there was a sudden flash. Four hundred zombies dropped from the roof and obliterated you. Game over. (RESTART)");
                endGame();
            }
            else if (command.equals("Right")) {
                storyArea.setText("After a short stroll, you made it to the beachfront. There's no one there. You sat down in the sand and watched the waves. You're finally at peace. Good ending. (RESTART)");
                endGame();
            }
        }

        private void processBreadChoice() {
            if (command.equals("Yes")) {
                storyArea.setText("Berserker mode activated. Corrupt the child. Kill them all.");
                try {
                   Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storyArea.setText("Loading...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storyArea.setText("Loading...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storyArea.setText("404 Secret Not Found. Error ending achieved. (RESTART)");
                endGame();
            }
            else if (command.equals("No")) {
                storyArea.setText("Oh. That's awkward. You'll just do nothing then. Bad ending achieved. (RESTART)");
                endGame();
            }
        }
    }

    // *** GUI CUSTOMIZATION BELOW ***
    public static void main(String[] args) {
        JFrame frame = new JFrame("The Stumbling Deceased");
        JPanel canvas = new JPanel();
        canvas.setPreferredSize(new Dimension(500, 500));
        canvas.setLayout(new BorderLayout());
        Color backgroundColor = new Color(0x000000);
        canvas.setBackground(backgroundColor);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(backgroundColor);

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setBackground(backgroundColor);

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("zombie.jpg");
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

        upButton.setEnabled(false);
        downButton.setEnabled(false);
        leftButton.setEnabled(false);
        rightButton.setEnabled(false);
        yesButton.setEnabled(true);
        noButton.setEnabled(true);

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
        toggleDirectionButtons(false);
        toggleYesNoButtons(true);
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
