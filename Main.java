import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    // Declare variables to be used throughout the program like the story display area
    private static JTextArea storyArea;
    // Each button has an associated command which is checked in if-else statements within the actionPerformed() method.
    private static JButton northButton, eastButton, southButton, westButton, yesButton, noButton;
    // The game state is updated as the user makes choices, allowing the program to check the state in if-else conditions and react accordingly.
    private static String state; 
    public static void main(String[] args) {
        // Create the frame.
        JFrame frame = new JFrame("Central Park Adventure");
        JPanel canvas = new JPanel();
        canvas.setPreferredSize(new Dimension(800, 600));
        canvas.setLayout(new BorderLayout());

        // Set up the text area for displaying the story
        storyArea = new JTextArea();
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setText("You're standing at the SW corner of Central Park, with Columbus Circle behind you. Which way do you want to go (n,e,s,w)?");
        canvas.add(new JScrollPane(storyArea), BorderLayout.CENTER);

        // Set up the button panel for direction buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        northButton = new JButton("North");
        eastButton = new JButton("East");
        southButton = new JButton("South");
        westButton = new JButton("West");

        // Add action listeners for direction buttons
        northButton.addActionListener(new DirectionListener("North"));
        eastButton.addActionListener(new DirectionListener("East"));
        southButton.addActionListener(new DirectionListener("South"));
        westButton.addActionListener(new DirectionListener("West"));

        buttonPanel.add(northButton);
        buttonPanel.add(eastButton);
        buttonPanel.add(southButton);
        buttonPanel.add(westButton);

        canvas.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(canvas);
        
        // Initialize the game state
        state = "start";

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // Listener class for handling direction button actions
    static class DirectionListener implements ActionListener {
        private String command;

        public DirectionListener(String command) {
            this.command = command;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (state.equals("start")) {
                if (command.equals("North")) {
                    storyArea.setText("You enter the park and walk NORTH until you reach Tavern on the Green. There's a lively wedding going on, do you want to crash it? (y,n)");
                    showYesNoButtons();
                    state = "north";
                } else if (command.equals("East")) {
                    storyArea.setText("You enter the park and walk EAST until you reach the castle playground.");
                    disableDirectionButtons();
                } else if (command.equals("South")) {
                    storyArea.setText("You were drawn into the lure of the Time Warner Shops and spent all of your savings at Sephora. Game over!");
                    disableDirectionButtons();
                } else if (command.equals("West")) {
                    storyArea.setText("You were drawn into the lure of the Time Warner Shops and spent all of your savings at Whole Foods. Game over!");
                    disableDirectionButtons();
                }
            } else if (state.equals("north")) {
                if (command.equals("Yes")) {
                    storyArea.setText("You decide to sneak into the wedding and promptly get arrested. Turns out it was for a high-profile celebrity. Oops!");
                    disableYesNoButtons();
                } else if (command.equals("No")) {
                    storyArea.setText("You keep walking NORTH until you reach Sheep's Meadow.");
                    disableYesNoButtons();
                }
            }
        }
    }

    // Shows Yes/No buttons for specific choices
    private static void showYesNoButtons() {
        yesButton = new JButton("Yes");
        noButton = new JButton("No");

        yesButton.addActionListener(new DirectionListener("Yes"));
        noButton.addActionListener(new DirectionListener("No"));

        JPanel yesNoPanel = new JPanel();
        yesNoPanel.add(yesButton);
        yesNoPanel.add(noButton);

        storyArea.getParent().add(yesNoPanel, BorderLayout.NORTH);
        storyArea.getParent().revalidate();
        storyArea.getParent().repaint();
    }

    // Disables Yes/No buttons once a choice has been made
    private static void disableYesNoButtons() {
        yesButton.setEnabled(false);
        noButton.setEnabled(false);
    }

    // Disables the direction buttons once a game-ending choice has been made
    private static void disableDirectionButtons() {
        northButton.setEnabled(false);
        eastButton.setEnabled(false);
        southButton.setEnabled(false);
        westButton.setEnabled(false);
    }
    
}