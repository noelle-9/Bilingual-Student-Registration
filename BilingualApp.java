// Name:Noelle Dacayo
// Date: April 21, 2023
// App Name: BilingualApp
// Description: An app that allows the user to choose between english or french to be displyayed

import javax.swing.*;
// import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

// "Language Selection || Sélection de la langue"
// "Please Choose Your Language || S'il vous plaît Choisissez votre langue"

public class BilingualApp {

    static JFrame window;
    static JPanel panel;
    static GridBagConstraints gridbag;
    static JLabel bannerLabel,nameLabel, programLabel;
    static JButton registerButton;
    static JTextField nameTextField, programTextField;

    // Variables
    static String title;
    static String banner;
    static String name;
    static String program;
    static String register;
    static String enterAllInfo;
    static String warning;
    static String complete;
    static String registered;


     /**
     * Loads the language the user chose
     */
    static void loadLanguage(String fileName)
    {
        Scanner fileScanner;

        File file = new File(fileName);

        try {
            fileScanner         = new Scanner(file, "UTF-8");
            title               = fileScanner.nextLine();
            banner              = fileScanner.nextLine();
            name                = fileScanner.nextLine();
            program             = fileScanner.nextLine();
            register            = fileScanner.nextLine();
            enterAllInfo        = fileScanner.nextLine();
            warning             = fileScanner.nextLine();
            complete            = fileScanner.nextLine();
            registered          = fileScanner.nextLine();
            fileScanner.close();

            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(window, "Could not open " + file.getName(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }


    /**
     * Prompts the user to select a language for the student registration 
     */
    static void langSelection()
    {
        // Variables
        final int ENGLISH = 0, FRENCH = 1;

        // String array containing options
        String [] lang = {"English", "French"};

        int selectedLang = JOptionPane.showOptionDialog(null, "Please Choose Your Language || S'il vous plaît Choisissez votre langue", "Language Selection || Sélection de la langue", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("hold.png"), lang, null);
            
        switch (selectedLang) {
            default:
            case ENGLISH:
                loadLanguage("English.lang");
                break;
        
            case FRENCH:
                loadLanguage("French.lang");
                break;
        }
    }

    /**
     * Executed when [Register] button is clicked
     * Shows an error in case the user doesn't have a name or program
     * Save the student in a data file
     */
    static void registerClick() {
        // Variables
        String name = nameTextField.getText();
        String program = programTextField.getText();
        String fileName;
        FileWriter fileWriter;
        File file;

        // Error in case the student name is empty
        if (name.equals("") || program.equals("")) {
            JOptionPane.showMessageDialog(window, (enterAllInfo), (warning),
                    JOptionPane.ERROR_MESSAGE);
        } else {
            // Format the file name
            fileName = name.split(" ")[0] + ".data";

            // Create a new file
            file = new File(fileName);

            try {
                // Prepare to write in the file
                fileWriter = new FileWriter(file);

                // Start writing in the file
                fileWriter.write(name + "\n" + program);

                // Finished writing, close the file
                fileWriter.close();

                // Tell the user that the student was saved in the file.data
                JOptionPane.showMessageDialog(window, registered + fileName, (register),
                        JOptionPane.INFORMATION_MESSAGE);

                // Error in case we can't write in the file
            } catch (Exception e) {
                JOptionPane.showMessageDialog(window, "Cannot write in the file: " + fileName, "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }

            // Clear the GUI
            nameTextField.setText("");
            programTextField.setText("");

        }

    }

    public static void main(String[] args) {

        langSelection();

        window = new JFrame(title);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setIconImage(new ImageIcon("AGH.png").getImage());
        window.setResizable(false);

        Font labels = new Font("Monospaced", Font.PLAIN, 20);
        Font buttons = new Font("Monospaced", Font.PLAIN, 15);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PANEL
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        panel = new JPanel(new GridBagLayout());
        gridbag = new GridBagConstraints();

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // NAME TEXTFIELD
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        nameTextField = new JTextField();
        programTextField = new JTextField();

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // LABEL
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        bannerLabel = new JLabel(banner);
        nameLabel = new JLabel(name);
        programLabel = new JLabel(program);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // SAVE BUTTON
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        registerButton = new JButton(register);
        registerButton.addActionListener(event -> registerClick());
        registerButton.setPreferredSize(new Dimension(100, 30));
        registerButton.setFont(buttons);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PLACE PANEL
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        window.add(panel);
        gridbag.anchor = GridBagConstraints.WEST;

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PLACE LABEL
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        gridbag.gridy = 0;        
        gridbag.insets = new Insets(0, 70, 10, 0);
        panel.add(bannerLabel, gridbag);
        gridbag.insets = new Insets(0, 0, 0, 0);
        bannerLabel.setPreferredSize(new Dimension(400, 70));
        bannerLabel.setFont(labels);
        // ----------------------------------------------------------------------
        gridbag.gridy = 1;
        gridbag.insets = new Insets(10, 10, 10, 10);
        panel.add(nameLabel, gridbag);
        // ----------------------------------------------------------------------
        gridbag.gridy = 3;
        panel.add(programLabel, gridbag);

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PLACE NAME TEXTFIELD
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        gridbag.gridy = 2;
        
        // gridbag.fill = GridBagConstraints.HORIZONTAL; 
        panel.add(nameTextField, gridbag);
        nameTextField.setPreferredSize(new Dimension(450, 45));
        // ----------------------------------------------------------------------
        gridbag.gridy = 4;
        panel.add(programTextField, gridbag);
        programTextField.setPreferredSize(new Dimension(450, 45));
        
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // PLACE BUTTON
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        gridbag.gridy = 5;
        // gridbag.insets = new Insets(20, 35, 10, 10);
        gridbag.fill = GridBagConstraints.HORIZONTAL; 
        panel.add(registerButton, gridbag);
        gridbag.insets = new Insets(0, 0, 0, 0);
        
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        window.pack();
        window.setVisible(true);
    }
}