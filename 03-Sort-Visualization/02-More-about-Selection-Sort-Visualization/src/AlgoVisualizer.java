import java.awt.*;
import java.util.Random;

public class AlgoVisualizer {

    private static int DELAY = 40;

    private int N;
    private int[] numbers;
    private AlgoFrame frame;

    public AlgoVisualizer(int N, AlgoFrame frame){
        this.N = N;
        this.frame = frame;

        numbers = new int[N];

        // 根据frame的大小计算合理数值
        Random rand = new Random();
        for( int i = 0 ; i < N ; i ++)
            numbers[i] = rand.nextInt(800) + 1;

    }

    public void run(){

        frame.setNumbers(numbers, 0,-1,-1);
        AlgoVisHelper.pause(DELAY);
        for( int i = 0 ; i < N ; i ++ ){
            // 寻找[i, n)区间里的最小值的索引
            int minIndex = i;
            frame.setNumbers(numbers, i, -1, minIndex);
            for( int j = i + 1 ; j < N ; j ++ ){
                frame.setNumbers(numbers, i, j, minIndex);
                AlgoVisHelper.pause(DELAY/4);
                if( numbers[j] < numbers[minIndex] ){
                    minIndex = j;
                    frame.setNumbers(numbers, i, j, minIndex);
                }
            }

            swap( numbers , i , minIndex);
            frame.setNumbers(numbers, i+1, -1, -1);
            AlgoVisHelper.pause(DELAY);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Selection Visualization", sceneWidth,sceneHeight);

            int N = 200;
            // int N = 100;
            AlgoVisualizer vis = new AlgoVisualizer(N, frame);
            new Thread(() -> {
                vis.run();
            }).start();
        });
    }
}