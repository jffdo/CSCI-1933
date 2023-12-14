public class LinkedList <T extends Comparable<T>> implements List<T>{
    private boolean isSorted;
    private Node<T> start,last;
    private int size;
    public LinkedList(){
        isSorted = true;
        start = new Node<>(null, null);
        last = start;
        size = 0;
    }

    @Override
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        Node<T> newNode = new Node<>(element, null);
        if (last != start && last.getData().compareTo(newNode.getData())>0) {
            isSorted = false;
        }
        last.setNext(newNode);
        last = newNode;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (element == null || index < 0 || index >= size()) {
            return false;
        }
        Node<T> ptr = start.getNext();
        Node<T> trailer = start;
        int currIndex = 0;
        while (currIndex != index){
            ptr = ptr.getNext();
            trailer = trailer.getNext();
            currIndex++;
        }
        Node<T> newNode = new Node<>(element, ptr);
        trailer.setNext(newNode);
        if (index == 0 && ptr.getData().compareTo(newNode.getData())<0) {
            isSorted = false;
        } else if (ptr.getData().compareTo(newNode.getData())<0){
            isSorted = false;
        }
        size++;
        return true;
    }

    @Override
    public void clear() {
        start.setNext(null);
        last = start;
        size = 0;
        isSorted = true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        Node<T> ptr = start.getNext();
        int currIndex = 0;
        while (currIndex != index){
            ptr = ptr.getNext();
            currIndex++;
        }
        return ptr.getData();
    }

    @Override
    public int indexOf(T element) {
        if (element == null){
            return -1;
        }
        if(isSorted) {
            int low = 0;
            int high = size() - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (element.compareTo(get(mid)) < 0) {
                    high = mid - 1;
                } else if (element.compareTo(get(mid)) > 0) {
                    low = mid + 1;
                } else if (mid == 0 || get(mid - 1).compareTo(element) != 0) {
                    return mid;
                } else {
                    high = mid;
                }
            }
            return -1;
        } else {
            Node<T> ptr = start;
            T item = ptr.getData();
            int currIndex = 0;
            while (ptr.getNext() != null && item != element) {
                ptr = ptr.getNext();
                item = ptr.getData();
                if (item.compareTo(element) != 0) {
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
        if (!isSorted){
            Node<T> ptr = start.getNext();
            Node<T> trailer = start;
            Node<T> ptr2,trailer2,minNode,temp,minTrailer;
            while(ptr != null) {
                minNode = ptr;
                minTrailer = trailer;
                ptr2 = minNode.getNext();
                trailer2 = minNode;
                while (ptr2 != null) {
                    if (ptr2.getData().compareTo(minNode.getData()) < 0) {
                        minNode = ptr2;
                        minTrailer = trailer2;
                    }
                    ptr2 = ptr2.getNext();
                    trailer2 = trailer2.getNext();
                }
                trailer.setNext(minNode);
                minTrailer.setNext(ptr);
                temp = minNode.getNext();
                minNode.setNext(ptr.getNext());
                ptr.setNext(temp);

                ptr = minNode.getNext();
                trailer = minNode;

                last = minNode;
            }
            isSorted = true;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        Node<T> ptr = start.getNext();
        Node<T> trailer = start;
        int currIndex = 0;
        while (currIndex != index){
            ptr = ptr.getNext();
            trailer = trailer.getNext();
            currIndex++;
        }
        T data = ptr.getData();
        trailer.setNext(ptr.getNext());
        ptr = start.getNext();
        isSorted = true;
        while(ptr != null && ptr.getNext() != null){
            if (ptr.getData().compareTo(ptr.getNext().getData()) > 0) {
                isSorted = false;
                break;
            }
            ptr = ptr.getNext();
        }
        if (index == size()-1){
            last = trailer;
        }
        size--;
        return data;
    }

    @Override
    public void equalTo(T element) {
        if (element == null) {
            return;
        }
        Node<T> ptr = start;
        if(isSorted){
            int currIndex = 0;
            while (ptr.getNext() != null && currIndex != indexOf(element)) {
                ptr = ptr.getNext();
                currIndex++;
            }
            int total = 1;
            ptr = ptr.getNext();
            start.setNext(ptr);
            while(ptr.getNext() != null  && ptr.getNext().getData().compareTo(element) == 0){
                ptr = ptr.getNext();
                total++;
            }
            last = ptr;
            last.setNext(null);
            size = total;
        }else {
            T item;
            int currIndex = 0;
            while (ptr.getNext() != null) {
                ptr = ptr.getNext();
                item = ptr.getData();
                if (item.compareTo(element) != 0) {
                    remove(currIndex);
                    currIndex--;
                }
                currIndex++;
            }
        }
    }

    @Override
    public void reverse() {
        if (start.getNext() == null || start.getNext().getNext() == null){
            return;
        }
        Node<T> ptr = start.getNext().getNext();
        Node<T> trailer = start.getNext();

        while(ptr != null){
            trailer.setNext(ptr.getNext());
            ptr.setNext(start.getNext());
            start.setNext(ptr);
            ptr = trailer.getNext();
        }
        last = trailer;

        ptr = start.getNext();
        isSorted = true;
        while(ptr != null && ptr.getNext() != null){
            if (ptr.getData().compareTo(ptr.getNext().getData()) > 0) {
                isSorted = false;
                break;
            }
            ptr = ptr.getNext();
        }
    }

    @Override
    public void merge(List<T> otherList) {
        LinkedList<T> other = (LinkedList<T>) otherList;
        sort();
        other.sort();
        Node<T> ptr = start.getNext();
        Node<T> otherPtr = other.start.getNext();
        LinkedList<T> merge = new LinkedList<>();
        while(true){
            if (ptr == null){
                Node<T> mergePtr = merge.last;
                mergePtr.setNext(otherPtr);
                last = other.last;
                break;
            }
            if (otherPtr == null){
                Node<T> mergePtr = merge.last;
                mergePtr.setNext(ptr);
                break;
            }
            if (ptr.getData().compareTo(otherPtr.getData())<=0){
                merge.add(ptr.getData());
                ptr = ptr.getNext();
            } else {
                merge.add(otherPtr.getData());
                otherPtr = otherPtr.getNext();
            }

        }
        start.setNext(merge.start.getNext());
        size += other.size();
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
        Node<T> ptr = start.getNext();
        Node<T> temp = ptr;
        Node<T> trailer = start;
        for(int i = 0; i < size()-n; i++){
            ptr = ptr.getNext();
            trailer = trailer.getNext();
        }
        start.setNext(ptr);
        trailer.setNext(null);
        last.setNext(temp);
        last = trailer;
        ptr = start.getNext();
        isSorted = true;
        while(ptr != null && ptr.getNext() != null){
            if (ptr.getData().compareTo(ptr.getNext().getData()) > 0) {
                isSorted = false;
                break;
            }
            ptr = ptr.getNext();
        }
        return true;
    }

    public String toString(){
        String s = "";
        Node<T> ptr = start.getNext();
        while(ptr != null){
            s += ptr.getData().toString() + "\n";
            ptr = ptr.getNext();
        }
        return s;
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }
}

// Written by Jeffrey Do, do000043