package com.company;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

public class ConfigWindow extends JFrame {
    public static String worldName;
    public static int tileWidth;
    private int inputValue;
    public static boolean newWorld = false;

    private ImageIcon img;
    private JButton confirm;
    private JButton cancel;
    private JButton select;
    private JLabel name;
    private JLabel size;
    private JLabel totalSize;
    private JTextField nameInput;
    private JTextField sizeInput;

    public ConfigWindow() {
        initButtons();
        initLabels();
        initInputs();

        img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/builder_icon.png")));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Mario Builder");
        setIconImage(img.getImage());
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel labelsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        labelsPanel.add(name);
        labelsPanel.add(nameInput);
        labelsPanel.add(size);
        labelsPanel.add(sizeInput);
        totalSize.setText("Total map size: ");
        totalSize.setForeground(Color.GRAY);
        totalSize.setFont(totalSize.getFont().deriveFont(12.0f));
        labelsPanel.add(totalSize);
        Insets insets = new Insets(10, 10, 10, 10);
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right));
        add(labelsPanel, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(confirm);
        buttonsPanel.add(select);
        buttonsPanel.add(cancel);
        add(buttonsPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initButtons() {
        confirm = new JButton("Confirm");
        confirm.setFocusable(false);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                newWorld = true;
                worldName = nameInput.getText();
                tileWidth = inputValue;
                Editor editorWindow = new Editor();
            }
        });

        select = new JButton("Select world");
        select.setFocusable(false);
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                File defaultDirectory = new File("Worlds/");
                fileChooser.setCurrentDirectory(defaultDirectory);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    worldName = selectedFile.getName().substring(0, selectedFile.getName().lastIndexOf('.'));
                    Editor editorWindow = new Editor();
                }
            }
        });

        cancel = new JButton("Cancel");
        cancel.setFocusable(false);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void initLabels() {
        name = new JLabel("World name: ");
        size = new JLabel("World width (blocks): ");
        totalSize = new JLabel();
        totalSize.setPreferredSize(new Dimension(200, 20));
    }

    private void initInputs() {
        nameInput = new JTextField();
        sizeInput = new JTextField();

        sizeInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        sizeInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                inputValue = Integer.parseInt(sizeInput.getText());
                int calculatedSize = inputValue * 48;
                totalSize.setText("<html>Total map size: " + calculatedSize + "px x 720px</html>");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

}
