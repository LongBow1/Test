package sort;


/**
 * 1.将待排序的关键字序列（R1,R2,...Rn）构建大顶堆，此堆为初始的无序区.
 *
 * 2.将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区
 * (R1,R2,......Rn-1)和新的有序区(Rn),且满足R[1,2...n-1]<=R[n];
 *
 * 3.由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,......Rn-1)调整为新堆，
 * 然后再次将R[1]与无序区最后一个元素交换，得到新的无序区(R1,R2....Rn-2)和新的有序区(Rn-1,Rn)。
 * 不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。
 */
public class HeapSort {
    public static void BuildMaxHeap(int arr[], int n, int i){//n为完全二叉树个数，i为根节点位置
        printArray(arr);
        System.out.println("");
        int largest = i; // 初始化根
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        // left > root
        if (l < n && arr[l] > arr[largest])
            largest = l; //largest表示此时最大值的位置

        // right > root
        if (r < n && arr[r] > arr[largest])
            largest = r;
        // 如果最大值不是根节点，调整
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            // BuildMaxHeap层层找到最最大值
            BuildMaxHeap(arr, n, largest);
        }

    }
    //打印函数
    public static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
    }

    public static void main(String[] args) {
        int arr[] = {7, 3, 8, 5, 1, 2};
        int n = arr.length;
        // 初始化堆 初始化后就可以得到 根节点为最大值
        for (int i = n / 2 - 1; i >= 0; i--){
            BuildMaxHeap(arr, n, i);
        }

        //依次把最大值沉下去 从右到左断开最后一个元素，重新构造大顶堆 也就是从小到大 排序
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            BuildMaxHeap(arr, i, 0);
        }
        // 打印结果
        System.out.println("结果：");
        printArray(arr);
    }
}
