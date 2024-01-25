import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Arrays;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class UI 
{
    private JFrame frame;
    private JPanel panel;
    private GridBagConstraints gbc;
    private JComboBox<String> fromComboBox, toComboBox;
    private JTextField fromtf, totf;
    private JButton convertingBtn, swapBtn;
    private JLabel  waitingLabel, noConnectionLabel;
    private Font fontDefault = new Font("Dubai", Font.BOLD, 16);

    public void Start()
    {
        frame = new JFrame("OK");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1; // [0, 1]
        gbc.insets = new Insets(10, 10, 10, 10);
        //gbc.ipadx = 5;
        //gbc.ipady = 5;

        fromComboBox = new JComboBox<>(CurrencyConvesionURL.worldCurrencies);
        toComboBox = new JComboBox<>(CurrencyConvesionURL.worldCurrencies);
        fromtf = new JTextField("FROM");
        totf = new JTextField("TO");
        waitingLabel = new JLabel("WAITING...");
        noConnectionLabel = new JLabel("YOU ARE NOT CONNECTED");
        convertingBtn = new JButton("CONVERTING");
        swapBtn = new JButton("SWAP");

        int usdIndex = Arrays.asList(CurrencyConvesionURL.worldCurrencies).indexOf("USD");
        int vndIndex = Arrays.asList(CurrencyConvesionURL.worldCurrencies).indexOf("VND");
        fromComboBox.setSelectedIndex(usdIndex);
        toComboBox.setSelectedIndex(vndIndex);
        
        totf.setEditable(false);

        fromtf.setForeground(Color.GRAY);
        totf.setForeground(Color.GRAY);
        noConnectionLabel.setForeground(Color.RED);

        noConnectionLabel.setHorizontalAlignment(JLabel.CENTER);

        convertingBtn.setBorder(CreateBorder(Color.BLACK, 2));
        fromComboBox.setBorder(CreateBorder(Color.BLACK, 2));
        toComboBox.setBorder(CreateBorder(Color.BLACK, 2));
        swapBtn.setBorder(CreateBorder(Color.BLACK, 2));
        fromtf.setBorder(CreateBorder(Color.BLACK, 2));
        totf.setBorder(CreateBorder(Color.BLACK, 2));
        waitingLabel.setBorder(null);

        convertingBtn.setFont(fontDefault);
        fromComboBox.setFont(fontDefault);
        toComboBox.setFont(fontDefault);
        swapBtn.setFont(fontDefault);
        fromtf.setFont(fontDefault);
        totf.setFont(fontDefault);
        waitingLabel.setFont(new Font("Dubai", Font.BOLD + Font.ITALIC, 20));
        noConnectionLabel.setFont(new Font("Dubai", Font.BOLD + Font.ITALIC, 20));
        
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(fromComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(toComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(fromtf, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(totf, gbc);

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(swapBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(waitingLabel, gbc);        

        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(convertingBtn, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(noConnectionLabel, gbc);

        AddListener();

        frame.add(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        waitingLabel.setVisible(false);

        if (IsInternetAvailable())
            noConnectionLabel.setVisible(false);
        else
            noConnectionLabel.setVisible(true);
    }

    private void AddListener()
    {
        // click chuột vào frame thì những component có focus sẽ được xử lý
        frame.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                frame.requestFocusInWindow();
            }
        });

        fromComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SetData();
            }
        });

        toComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SetData();
            }
        });

        fromtf.addFocusListener(new FocusListener() 
        {
            // nhận được focus, tức là người dùng đã chọn vào nó 
            // hoặc di chuyển đến nó bằng bàn phím hoặc chuột
            @Override
            public void focusGained(FocusEvent e) 
            {
                if (fromtf.getText().equals("FROM")) 
                {
                    fromtf.setText("");
                    fromtf.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) 
            {
                if (!fromtf.getText().isEmpty())
                {
                    String inputStr = RemoveComma(fromtf.getText());
                    if (IsInputValid(inputStr))
                    {
                        fromtf.setText(CorrectNumber(inputStr));
                        SetData();
                    }
                    else
                    {
                        fromtf.setText("FROM");
                        fromtf.setForeground(Color.GRAY);
                    }
                }
                else
                {
                    fromtf.setText("FROM");
                    fromtf.setForeground(Color.GRAY);                    
                }
            }
        });
        fromtf.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent e) 
            {
                // Khi con nháy soạn thảo vẫn còn trong textfield
                // nhưng nhấn Enter vẫn thực hiện convert
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    if (!fromtf.getText().isEmpty())
                    {
                        String inputStr = RemoveComma(fromtf.getText());
                        if (IsInputValid(inputStr))
                        {
                            // no focus lost
                            fromtf.setText(CorrectNumber(inputStr));
                            StartConverting();
                        }
                        else
                        {
                            fromtf.setText("FROM");
                            fromtf.setForeground(Color.GRAY);
                        }
                    }
                    else
                    {
                        fromtf.setText("FROM");
                        fromtf.setForeground(Color.GRAY);                    
                    }
                }
            }
        });

        swapBtn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int fromIndex = fromComboBox.getSelectedIndex();
                int toIndex = toComboBox.getSelectedIndex();
                // swap
                int tmp = fromIndex;
                fromIndex = toIndex;
                toIndex = tmp;
                fromComboBox.setSelectedIndex(fromIndex);
                toComboBox.setSelectedIndex(toIndex);
            }
        });

        convertingBtn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if (!fromtf.getText().isEmpty())
                {
                    String inputStr = RemoveComma(fromtf.getText());
                    if (IsInputValid(inputStr))
                    {
                        StartConverting();
                    }
                    else
                    {
                        fromtf.setText("FROM");
                        fromtf.setForeground(Color.GRAY);
                    }
                }
                else
                {
                    fromtf.setText("FROM");
                    fromtf.setForeground(Color.GRAY);                    
                }
            }
        });
    }

    /**
     * Chưa hiểu SwingWorker mấy...
     */
    private void StartConverting()
    {
        if (IsInternetAvailable()) 
        {
            // Use SwingWorker for background task
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() 
            {
                @Override
                protected Void doInBackground() throws IOException
                {
                    // Wait for the data to be retrieved from the URL
                    waitingLabel.setVisible(true);
                    //System.out.println("waiting: " + waitingLabel.isVisible());
                    DataProcessing.GetResultFromURL();
                    return null;
                }

                @Override
                protected void done() 
                {
                    try 
                    {
                        if (DataProcessing.toOutputStr != null && !DataProcessing.toOutputStr.isEmpty()) 
                        {
                            totf.setText(DataProcessing.toOutputStr);
                            waitingLabel.setVisible(false);
                            //System.out.println("completed: " + waitingLabel.isVisible());
                            noConnectionLabel.setVisible(false);
                        }
                    } 
                    catch (Exception ex) 
                    {
                        ex.printStackTrace();
                    }
                }
            };
            // Execute the SwingWorker
            worker.execute();
        } 
        else 
        {
            noConnectionLabel.setVisible(true);
        }
    }

    private void SetData()
    {
        if (!fromtf.getText().isEmpty())
        {
            String inputStr = RemoveComma(fromtf.getText());
            if (IsInputValid(inputStr))
                DataProcessing.fromInputStr = (CorrectNumber(inputStr));
        }

        // set(fromUnit)
        DataProcessing.fromUnit = (String)fromComboBox.getSelectedItem();
        // set(toUnit)
        DataProcessing.toUnit = (String)toComboBox.getSelectedItem();
    }

    private String RemoveComma(String str) // (1)
    {
        String regex = "^0*,.*";
        if (str.contains(","))
        {
            if (str.matches(regex))
                return "Error format";
            return str.replace(",", "");
        }
        return str;
    }

    private boolean IsInputValid(String str) // (2)
    {
        String regex = "^[0-9,\\.]*$";
        boolean match = str.matches(regex);
        return match;
    }

    private String CorrectNumber(String str) // (3)
    {
        String[] parts = str.split("\\.");

        String result = parts[0].replaceFirst("^0+(?!$)", "");

        if (parts.length > 1)
        {
            if ((Integer.parseInt(parts[0]) > 1 && Integer.parseInt(parts[1]) == 0))
                result += parts[1].replaceAll("0*$", "");
            else if ((Integer.parseInt(parts[0]) == 0 && Integer.parseInt(parts[1]) == 0))
            {
                result = "";
                fromtf.setText("FROM");
                fromtf.setForeground(Color.GRAY);                  
            }
            else
                result += "." + parts[1].replaceAll("0*$", "");
        }
        return result;
    }
    private Border CreateBorder(Color c, int tn)
    {
        return new LineBorder(c, tn);
    }

    private boolean IsInternetAvailable()
    {
        try (Socket socket = new Socket()) // tự động đóng sau khi try hoàn thành
        {
            int port = 80;
            SocketAddress socketAddress = new InetSocketAddress("www.google.com", port);
            socket.connect(socketAddress, 3000); // thời gian chạy 3 giây
            return true;
        } 
        catch (IOException e)
        {
            return false; // Either timeout or unreachable or failed DNS lookup.
        }
    }
}
