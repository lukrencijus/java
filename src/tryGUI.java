import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class tryGUI {

    public static JFrame frame;
    private JPanel panel;

    private ImageIcon image;
    private JLabel label;

    private JButton insertButton;
    private JButton displayButton;
    private JButton editButton;
    private JButton removeButton;
    private JButton exitButton;

    ActivityManager activityManager = new ActivityManager();
    ActivityManagerGUI activityManagerGUI = new ActivityManagerGUI();
    Activity activity = new Activity();
    FileManager fileManager = new FileManager();

    Scanner read = new Scanner(System.in);

    File filePath = new File("./JSON_files/storage.json");

    public tryGUI() {
        frame = new JFrame();

        insertButton = new JButton("Insert Activities");
        displayButton = new JButton("Display Activities");
        editButton = new JButton("Edit Activities");
        removeButton = new JButton("Remove Activities");
        exitButton = new JButton("Exit Time Manager");

        image = new ImageIcon("./Icons/icon.png");

        label = new JLabel();
        label.setText("Welcome to Time Manager!");
        label.setIcon(image);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(6, 1));

        panel.add(label);
        panel.add(insertButton);
        panel.add(displayButton);
        panel.add(editButton);
        panel.add(removeButton);
        panel.add(exitButton);
        panel.setBackground(Color.PINK);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Time Manager");
        frame.pack();
        frame.setVisible(true);
        frame.setIconImage(image.getImage());

        //Insert button is pressed
        insertButton.addActionListener(e -> {
            String activityName = JOptionPane.showInputDialog(frame, "Enter the name of your activity", "Insert activity", JOptionPane.PLAIN_MESSAGE);
            if (activityName != null)
            {
                try {
                    activity.setName(activityName);
                } catch (Exceptions ex) {
                    throw new RuntimeException(ex);
                }

                String date = JOptionPane.showInputDialog(frame, "Enter date (YYYY-MM-DD)", "Insert activity", JOptionPane.PLAIN_MESSAGE);
                if (date != null)
                {
                    try {
                        activityManager.formatValidatorDate(date);
                        activity.setDate(date);
                    } catch (Exceptions ex) {
                        throw new RuntimeException(ex);
                    }

                    String startingTime = JOptionPane.showInputDialog(frame, "Insert starting time (HH:MM)", "Insert activity", JOptionPane.PLAIN_MESSAGE);
                    if (startingTime != null)
                    {
                        int flag = 0;
                        try {
                            activityManager.formatValidatorTime(startingTime, flag);
                            flag++;
                            activity.setStartTime(startingTime);
                        } catch (Exceptions ex) {
                            throw new RuntimeException(ex);
                        }

                        String endingTime = JOptionPane.showInputDialog(frame, "Insert ending time (HH:MM)", "Insert activity", JOptionPane.PLAIN_MESSAGE);
                        if(endingTime != null)
                        {
                            try {
                                activityManager.formatValidatorTime(endingTime, flag);
                                activity.setEndTime(endingTime);
                            } catch (Exceptions ex) {
                                throw new RuntimeException(ex);
                            }

                            String comments = JOptionPane.showInputDialog(frame, "Insert comments (optional)", "Insert activity", JOptionPane.PLAIN_MESSAGE);
                            activity.setComments(comments);
                            String interrupts = JOptionPane.showInputDialog(frame, "Insert interrupts (optional)", "Insert activity", JOptionPane.PLAIN_MESSAGE);
                            activity.setInterrupts(interrupts);

                            try {
                                FileManager.appendData(activity, filePath);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(frame, "Inserting activities was cancelled", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Inserting activities was cancelled", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Inserting activities was cancelled", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Inserting activities was cancelled", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Display button is pressed
        displayButton.addActionListener(e -> {
            String displayActivities = JOptionPane.showInputDialog(frame, "Please insert date that you want to check (YYYY-MM-DD)\n" +
                    "If nothing is entered, it will be set to today's", "Displaying activities", JOptionPane.PLAIN_MESSAGE);
            if (displayActivities != null) {
                try {
                    activityManagerGUI.extraction(displayActivities, filePath);
                } catch (Exceptions ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Displaying activities was cancelled", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Edit button is pressed
        editButton.addActionListener(e -> {
            String displayActivities = JOptionPane.showInputDialog(frame, "Please insert date that you want to edit (YYYY-MM-DD)\n" +
                    "If nothing is entered, it will be set to today's", "Editing activities", JOptionPane.PLAIN_MESSAGE);
            if (displayActivities != null) {
                try {
                    activityManagerGUI.extractionWithEditing(displayActivities, filePath);
                } catch (Exceptions ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Editing activities was cancelled", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Remove button is pressed
        removeButton.addActionListener(e -> {
            String removeActivities = JOptionPane.showInputDialog(frame, "Please insert date that you want to remove (YYYY-MM-DD)\n" +
                    "If nothing is entered, it will be set to today's", "Removing activities", JOptionPane.PLAIN_MESSAGE);
            if (removeActivities != null) {
                try {
                    activityManagerGUI.extractionForRemoval(removeActivities, filePath);
                } catch (Exceptions ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Removing activities was cancelled", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        exitButton.addActionListener(e -> {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });

    }

    public static void main(String[] args) {
        new tryGUI();
    }

}