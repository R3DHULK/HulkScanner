import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PortScannerGUI extends JFrame {
    private JTextField ipField;
    private JTextField startPortField;
    private JTextField endPortField;
    private JButton scanButton;
    private JCheckBox showAllCheckBox;
    private JTextArea logArea;

    public PortScannerGUI() {
        setTitle("Port Scanner");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel ipLabel = new JLabel("Target IP: ");
        ipField = new JTextField("127.0.0.1");
        JLabel startPortLabel = new JLabel("Start Port: ");
        startPortField = new JTextField("1");
        JLabel endPortLabel = new JLabel("End Port: ");
        endPortField = new JTextField("1024");
        scanButton = new JButton("Scan");
        showAllCheckBox = new JCheckBox("Show all ports");
        logArea = new JTextArea();

        panel.add(ipLabel);
        panel.add(ipField);
        panel.add(startPortLabel);
        panel.add(startPortField);
        panel.add(endPortLabel);
        panel.add(endPortField);
        panel.add(showAllCheckBox);
        panel.add(scanButton);

        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scanPorts();
            }
        });

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(logArea), BorderLayout.CENTER);
    }

    private void scanPorts() {
        String targetIP = ipField.getText();
        int startPort = Integer.parseInt(startPortField.getText());
        int endPort = Integer.parseInt(endPortField.getText());
        boolean showAllPorts = showAllCheckBox.isSelected();

        logArea.setText(""); // Clear previous log

        ExecutorService executor = Executors.newFixedThreadPool(20);
        List<ScanPortTask> tasks = new ArrayList<>();

        for (int port = startPort; port <= endPort; port++) {
            ScanPortTask task = new ScanPortTask(targetIP, port);
            tasks.add(task);
            executor.execute(task);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
            // Wait for all tasks to complete
        }

        for (ScanPortTask task : tasks) {
            if (showAllPorts || task.isOpen()) {
                logArea.append("Port " + task.getPort() + " is " + (task.isOpen() ? "open" : "closed") + "\n");
            }
        }
    }

    private class ScanPortTask implements Runnable {
        private String targetIP;
        private int port;
        private boolean open;

        public ScanPortTask(String targetIP, int port) {
            this.targetIP = targetIP;
            this.port = port;
        }

        public boolean isOpen() {
            return open;
        }

        public int getPort() {
            return port;
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(targetIP, port), 1000);
                socket.close();
                open = true;
            } catch (IOException e) {
                open = false;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PortScannerGUI gui = new PortScannerGUI();
                gui.setVisible(true);
            }
        });
    }
}
