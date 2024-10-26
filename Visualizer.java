import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Visualizer {
    private static int[] array;
    private static JPanel panel;
    private static JButton runButton;
    private static JComboBox<String> algorithmComboBox;
    private static boolean isRunning = false;
    private static int height = 600;
    private static int width = 700;
    private static final int size = 40;

    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawArray(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));

        JButton selectButton = new JButton("Generate Array");
        runButton = new JButton("Run");

        String[] algorithms = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Quick Sort", "Merge Sort"};
        algorithmComboBox = new JComboBox<>(algorithms);

        selectButton.addActionListener(e -> {
            array = generateRandomArray(size);
            panel.repaint();
        });

        runButton.addActionListener(e -> {
            if (array != null) {
                if (isSorted(array)) {
                    JOptionPane.showMessageDialog(frame, "The array is already sorted!");
                } else if (!isRunning) {
                    isRunning = true;
                    runButton.setEnabled(false);
                    String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                    switch (selectedAlgorithm) {
                        case "Bubble Sort":
                            runBubbleSort();
                            break;
                        case "Selection Sort":
                            runSelectionSort();
                            break;
                        case "Insertion Sort":
                            runInsertionSort();
                            break;
                        case "Quick Sort":
                            runQuickSort();
                            break;
                        case "Merge Sort":
                            runMergeSort();
                            break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please generate the array first.");
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Sorting Algorithm:"));
        inputPanel.add(algorithmComboBox);
        inputPanel.add(selectButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(runButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        array = generateRandomArray(size);
        panel.repaint();
    }

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100) + 1;
        }
        return arr;
    }

    private static void drawArray(Graphics g) {
        if (array == null || array.length == 0) return;
    
        int gap = 2;
        int totalGaps = gap * (array.length - 1);
        int availableWidth = panel.getWidth() - totalGaps;
        int barWidth = availableWidth / array.length;
    
        int maxH = panel.getHeight();
    
        for (int i = 0; i < array.length; i++) {
            g.setColor(Color.BLUE);
            int height = (int) ((double) array[i] / 100 * maxH);
            g.fillRect(i * (barWidth + gap), maxH - height, barWidth, height);
        }
    }

    private static void runBubbleSort() {
        SortAlgorithms.bubbleSort(array, panel, runButton);
        isRunning = false;
    }

    private static void runInsertionSort() {
        SortAlgorithms.insertionSort(array, panel, runButton);
        isRunning = false;
    }

    private static void runSelectionSort() {
        SortAlgorithms.selectionSort(array, panel, runButton);
        isRunning = false;
    }

    private static void runQuickSort() {
        SortAlgorithms.quickSort(array, panel, runButton);
        isRunning = false;
    }

    private static void runMergeSort() {
        SortAlgorithms.mergeSort(array, panel, runButton);
        isRunning = false;
    }
}
