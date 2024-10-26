import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class SortAlgorithms{
    private static Clip audioClip;

    // Sound
    private static void playSound(String filePath) {
        File audioFile = new File(filePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void stopSound() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
            audioClip.close();
        }
    }

    // Merge Sort

    public static void mergeSort(int[] arr, JPanel panel, JButton runButton) {
        if (arr.length <= 1) return;

        new Thread(() -> playSound("src/sound.wav")).start();
    
        new Thread(() -> {
            mergeSort(arr, 0, arr.length - 1, panel);
            SwingUtilities.invokeLater(() -> {
                runButton.setEnabled(true);
                stopSound();
            });
        }).start();
    }
    
    private static void mergeSort(int[] arr, int leftStart, int rightEnd, JPanel panel) {
        if (leftStart >= rightEnd) return;
    
        int middle = (leftStart + rightEnd) / 2;
        
        mergeSort(arr, leftStart, middle, panel);
        mergeSort(arr, middle + 1, rightEnd, panel);
        merge(arr, leftStart, middle, rightEnd, panel);
    }
    
    private static void merge(int[] arr, int leftStart, int middle, int rightEnd, JPanel panel) {
        int leftSize = middle - leftStart + 1;
        int rightSize = rightEnd - middle;
    
        int[] left = new int[leftSize];
        int[] right = new int[rightSize];
    
        System.arraycopy(arr, leftStart, left, 0, leftSize);
        System.arraycopy(arr, middle + 1, right, 0, rightSize);
    
        int l = 0, r = 0, i = leftStart;
    
        while (l < leftSize && r < rightSize) {
            if (left[l] <= right[r]) {
                arr[i++] = left[l++];
            } else {
                arr[i++] = right[r++];
            }
    
            SwingUtilities.invokeLater(() -> panel.repaint());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    
        while (l < leftSize) {
            arr[i++] = left[l++];
    
            SwingUtilities.invokeLater(() -> panel.repaint());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    
        while (r < rightSize) {
            arr[i++] = right[r++];
    
            SwingUtilities.invokeLater(() -> panel.repaint());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    

    // Bubble Sort
 
    public static void bubbleSort(int[] array, JPanel panel, JButton runButton) {
        new Thread(() -> playSound("src/sound.wav")).start();
        
        new Thread(() -> {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - 1 - i; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                    panel.repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            SwingUtilities.invokeLater(() -> {
                runButton.setEnabled(true);
                stopSound();
            });
        }).start();
    }


    // Selection Sort

    public static void selectionSort(int[] arr, JPanel panel, JButton runButton) {
        new Thread(() -> playSound("src/sound.wav")).start();

        new Thread(() -> {
            int n = arr.length;
    
            for (int i = 0; i < n; i++) {
                int small = i;
    
                for (int j = i + 1; j < n; j++) {
                    if (arr[small] > arr[j]) {
                        small = j;
                    }
                }
    
                if (small != i) {
                    int temp = arr[small];
                    arr[small] = arr[i];
                    arr[i] = temp;
    
                    SwingUtilities.invokeLater(() -> panel.repaint());
    
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
    
            SwingUtilities.invokeLater(() -> {
                runButton.setEnabled(true);
                stopSound();
            });
        }).start();
    }
    
    

    // Insersion Sort

    public static void insertionSort(int[] arr, JPanel panel, JButton runButton) {
        new Thread(() -> playSound("src/sound.wav")).start();

        new Thread(() -> {
            for (int i = 1; i < arr.length; i++) {
                int key = arr[i];
                int j = i - 1;
    
                while (j >= 0 && arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
    
                    SwingUtilities.invokeLater(() -> panel.repaint());
                    
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                arr[j + 1] = key;
    
                SwingUtilities.invokeLater(() -> panel.repaint());
                
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    
            SwingUtilities.invokeLater(() -> {
                runButton.setEnabled(true);
                stopSound();
            });
        }).start();
    }
    
    
    // Quick Sort

    public static int partition(int[] arr, int low, int high, JPanel panel) {
        int pivot = arr[high];
        int i = low - 1;
    
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
    
                SwingUtilities.invokeLater(() -> panel.repaint());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    
        i++;
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
    
        SwingUtilities.invokeLater(() -> panel.repaint());
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        return i;
    }
    
    public static void quickSortHelper(int[] arr, int low, int high, JPanel panel, JButton runButton) {
        if (low < high) {
            int pidx = partition(arr, low, high, panel); // Pass panel to partition
    
            quickSortHelper(arr, low, pidx - 1, panel, runButton);
            quickSortHelper(arr, pidx + 1, high, panel, runButton);
        }
    
        if (low == 0 && high == arr.length - 1) {
            SwingUtilities.invokeLater(() -> runButton.setEnabled(true));
        }
    }
    
    public static void quickSort(int[] arr, JPanel panel, JButton runButton) {
        new Thread(() -> playSound("src/sound.wav")).start();

        new Thread(() -> {
            quickSortHelper(arr, 0, arr.length - 1, panel, runButton);
            SwingUtilities.invokeLater(() -> {
                runButton.setEnabled(true);
                stopSound();
            });
        }).start();
    }
    
}
