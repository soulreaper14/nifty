package com.github.soulreaper14;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class NiftyCalculator {

  private static void init() {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception e) {

    }

    JFrame frame = new JFrame("Nifty");

    URL url = frame.getClass().getResource("/images/calculator.png");
    Image image = Toolkit.getDefaultToolkit().getImage(url);
    frame.setIconImage(image);

    // components for the input and output
    JLabel inputLabel = new JLabel("Input");
    JLabel outputLabel = new JLabel("Output");

    JTextField inputText = new JTextField(10);
    JTextField outputText = new JTextField(10);

    JButton calculateButton = new JButton("Calculate");

    JPanel centerPanel = new JPanel(new FlowLayout());
    centerPanel.add(inputLabel);
    centerPanel.add(inputText);
    centerPanel.add(outputLabel);
    centerPanel.add(outputText);
    centerPanel.add(calculateButton);

    // component for conversion
    String[] conversions = {
        "Feet : Inches",
        "Inches : Feet",
        "Years : Days",
        "Days : Years",
        "Kilometers : Miles",
        "Miles : Kilometers",
    };
    JComboBox<String> conversionComboBox = new JComboBox<>(conversions);

    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.add(conversionComboBox, BorderLayout.CENTER);

    calculateButton.addActionListener(e -> {
      try {
        String conversion = (String) conversionComboBox.getSelectedItem();
        String sInput = inputText.getText();
        double input = Double.parseDouble(sInput);
        double output = convert(conversion, input);

        DecimalFormat format = new DecimalFormat("0.000");
        outputText.setText(format.format(output));
      } catch (Exception e2) {

      }
    });

    frame.setLayout(new BorderLayout());
    frame.add(topPanel, BorderLayout.NORTH);
    frame.add(centerPanel, BorderLayout.CENTER);
    frame.pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int w = frame.getWidth();
    int h = frame.getHeight();
    int x = (screenSize.width - w) / 2;
    int y = (screenSize.height - h) / 2;

    frame.setLocation(x, y);
    frame.setVisible(true);

  }

  private static double convert(String conversion, double input) {
    if ("Feet : Inches".equals(conversion)) {
      return input * 12.0d;
    } else if ("Inches : Feet".equals(conversion)) {
      return input / 12.0d;
    } else if ("Years : Days".equals(conversion)) {
      return input * 365.25;
    } else if ("Days : Years".equals(conversion)) {
      return input / 365.25;
    } else if ("Kilometers : Miles".equals(conversion)) {
      return input / 1.609;
    } else if ("Miles : Kilometers".equals(conversion)) {
      return input * 1.609;
    }
    throw new IllegalArgumentException();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> init());
  }
}
