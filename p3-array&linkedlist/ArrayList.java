public class ArrayList <T extends Comparable<T>> implements List<T>{
    private boolean isSorted;
    private T[] arr;
    private int size;
    public ArrayList(){
        isSorted = true;
        arr = (T[]) new Comparable[2];
        size = 0;
    }

    public void doubleArray(){
        T[] newArr = (T[]) new Comparable[arr.length*2];
        for(int i = 0; i< arr.length; i++){
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    @Override
    public boolean add(T element) {
        if (element == null){
            return false;
        }
        if (size() == arr.length){
            doubleArray();
        }
        arr[size()] = element;
        size++;
        if(size()>1 && arr[size()-2].compareTo(arr[size()-1])>0){
            isSorted = false;
        }
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (element == null || index < 0 || index >= size()){
            return false;
        }
        if (size() == arr.length){
            doubleArray();
        }
        for(int i = size(); i > index; i--){
            arr[i] = arr[i-1];
        }
        arr[index] = element;
        size++;
        if(index == 0 && arr[0].compareTo(arr[1])>0){
            isSorted = false;
        } else if (arr[index].compareTo(arr[index+1])>0){
            isSorted = false;
        }
        return true;
    }

    @Override
    public void clear() {
        isSorted = true;
        arr = (T[]) new Comparable[2];
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= arr.length){
            return null;
        }
        return arr[index];
    }

    @Override
    public int indexOf(T element) {
        if(element == null){
            return -1;
        }
        if(isSorted) {
            int low = 0;
            int high = size()-1;
            while(low<= high){
                int mid = (low+high) / 2;
                if (element.compareTo(arr[mid])<0){
                    high = mid -1;
                } else if (element.compareTo(arr[mid])>0){
                    low = mid + 1;
                } else if (mid == 0 || arr[mid-1].compareTo(element) != 0){
                    return mid;
                } else {
                    high = mid;
                }
            }
            return -1;
        } else {
            int currIndex = 0;
            while (currIndex != size() && arr[currIndex].compareTo(element) != 0) {
                if (arr[currIndex].compareTo(element) != 0) {
                    currIndex++;
                }
            }
            if (currIndex >= size()) {
                return -1;
            }
            return currIndex;
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort() {
        if(!isSorted) {
            int minIndex;
            T temp;
            for (int i = 0; i < size() - 1; i++) {
                minIndex = i;
                for (int j = i + 1; j < size(); j++) {
                    if (arr[j].compareTo(arr[minIndex]) < 0)
                        minIndex = j;
                }
                temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
        isSorted = true;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        T data = arr[index];
        for(int i = index; i<size()-1; i++){
            arr[i] = arr[i+1];
        }
        arr[size()-1] = null;
        size--;
        isSorted = true;
        int currIndex = 0;
        while(size() != 0 && currIndex != size()-1){
            if(size()>1 && arr[currIndex].compareTo(arr[currIndex+1])>0){
                isSorted = false;
                break;
            }
            currIndex++;
        }
        return data;
    }

    @Override
    public void equalTo(T element) {
        if (element == null) {
            return;
        }
        int currIndex = 0;
        while (currIndex != size()) {
            if (arr[currIndex].compareTo(element) != 0) {
                remove(currIndex);
                currIndex--;
            }
            currIndex++;
        }
    }

    @Override
    public void reverse() {
        int currIndex = 0;
        while (currIndex != size()/2){
            T temp = arr[currIndex];
            arr[currIndex] = arr[size()-1-currIndex];
            arr[size()-1-currIndex] = temp;
            currIndex++;
        }
        isSorted = true;
        currIndex = 0;
        while(size() != 0 && currIndex != size()-1){
            if(size()>1 && arr[currIndex].compareTo(arr[currIndex+1])>0){
                isSorted = false;
                break;
            }
            currIndex++;
        }
    }

    @Override
    public void merge(List<T> otherList) {
        ArrayList<T> other = (ArrayList<T>) otherList;
        sort();
        other.sort();
        T[] merge = (T[]) new Comparable[size()+ other.size()];
        int currIndex = 0;
        int arrIndex = 0;
        int otherIndex = 0;
        while(true){
            if (arrIndex == size()){
                for (int i = otherIndex; i<other.size(); i++){
                    merge[currIndex] = other.arr[i];
                    currIndex++;
                }
                break;
            }
            if (otherIndex == other.size()){
                for (int i = arrIndex; i<size(); i++){
                    merge[currIndex] = arr[i];
                    currIndex++;
                }
                break;
            }
            if(arr[arrIndex].compareTo(other.arr[otherIndex])<=0){
                merge[currIndex] = arr[arrIndex];
                arrIndex++;
            } else {
                merge[currIndex] = other.arr[otherIndex];
                otherIndex++;
            }
            currIndex++;
        }
        size += other.size();
        arr = merge;
    }

    @Override
    public boolean rotate(int n) {
        if (size() == 0){
            return false;
        } else {
            n = n % size();
        }
        if (n <= 0){
            return false;
        }
        for(int i = 0; i<n; i++){
            T temp = arr[size()-1];
            for(int j = size()-1; j > 0; j--){
                arr[j] = arr[j-1];
            }
            arr[0] = temp;
        }
        isSorted = true;
        int currIndex = 0;
        while(size() != 0 && currIndex != size()-1){
            if(size()>1 && arr[currIndex].compareTo(arr[currIndex+1])>0){
                isSorted = false;
                break;
            }
            currIndex++;
        }
        return true;
    }

    public String toString(){
        String s = "";
        int index = 0;
        while(index != size()){
            s += arr[index] + "\n";
            index++;
        }
        return s;
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }
}

// Written by Jeffrey Do, do000043