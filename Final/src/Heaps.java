public class Heaps {
    private int[] storeData;
    private int heapSize;
    private int maxSize;

    public int[] getStoreData() {
        return storeData;
    }

    public void setStoreData(int[] storeData) {
        this.storeData = storeData;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public Heaps(int maximumSize){
        maxSize = maximumSize < 1  ? 100 : maximumSize;
        storeData = new int[maxSize];
        heapSize = 0;
    }
    public void insertData(int data){
        int position;
        heapSize++;
        storeData[heapSize-1] = data;
        position = heapSize - 1;
        while(position > 0 && storeData[position] > storeData[(position-1)/2]){
            swap(position, (position-1)/2);
            position = (position-1)/2;
        }
        printHeap();
        System.out.println();
    }
    public Boolean isFull(int[] data){
        return data.length > maxSize;
    }
    public void swap(int pos1, int pos2){
        int temp = storeData[pos1];
        storeData[pos1] = storeData[pos2] ;
        storeData[pos2] = temp ;
    }
    public void printHeap(){
        for(int i = 0; i < storeData.length; i++){
            if(storeData[i] != 0){
                System.out.print(storeData[i] + " ");
            }
        }
    }

    public int removeData(){
        int answer;
        answer = storeData[0];
        storeData[0] = storeData[heapSize-1];
        storeData[heapSize-1] = 0;
        heapSize--;
        fixHeap();
        return answer;
    }
    public void fixHeap() {
        int position = 0; int childPos;
        while (position*2 + 1 < heapSize) {
            childPos = position*2 + 1;
            if (childPos < heapSize-1 && storeData[childPos+1] > storeData[childPos])
                childPos++;
            if (storeData[position]>= storeData[childPos])
                return;
            swap(position, childPos);
            position = childPos;
        }
    }
    public void selectionSort(int[] data, int n) {
        int i,j,item;
        for (i = 1; i <= n-1; i++) {
            item = data[i];  j = i;
            while (j > 0 && data[j-1] > item) {
                data[j] = data[j-1];
                j--;
            }
            data[j] = item;
            printHeap();
            System.out.println();
        }
    }
}
