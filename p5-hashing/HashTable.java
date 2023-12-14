import java.util.Scanner;
import java.io.*;

public class HashTable<T> {
    private NGen<T>[] table;
    private String type = "general";
    private T[] arr;

    public HashTable(String filename, int size, String type){
        this.type = type;
        CreateTable(filename);
        table = new NGen[size];
        for (T t : arr) {
            add(t);
        }
    }

    public HashTable(String filename){
        CreateTable(filename);
        table = new NGen[100];
        for (T t : arr) {
            add(t);
        }
    }

    public void CreateTable(String filename){
        Scanner readFile = null;
        String s;
        int count = 0;
        try {
            readFile = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + filename + " not found");
            System.exit(1);
        }

        System.out.println();
        while (readFile.hasNext()) {
            readFile.next();
            count++;
        }
        String[] arr = new String[count];
        try {
            readFile = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + filename + " not found");
            System.exit(1);
        }
        int idx = 0;
        while (readFile.hasNext()) {
            s = readFile.next();
            arr[idx] = s;
            idx++;
        }
        this.arr = (T[]) arr;
    }


    public void add(T item) {
        int hash;
        if(type.equals("specific")){
            hash = hash1(item);
        } else {
            hash = hash2(item);
        }

        NGen<T> ptr = table[hash];
        while(ptr != null){
            if (item.equals(ptr.getData())) {
                return;
            }
            ptr = ptr.getNext();
        }
        table[hash] = new NGen<>(item, table[hash]);
    }

    public void display(){
        int uniqueTokens = 0;
        int nonEmptyIdx = 0;
        int maxChain = 0;
        for (int i = 0; i < table.length; i++){
            NGen<T> ptr = table[i];
            int total = 0;
            while(ptr != null){
                total++;
                uniqueTokens++;
                ptr = ptr.getNext();
            }
            if (total != 0){
                nonEmptyIdx++;
            }
            if (total > maxChain){
                maxChain = total;
            }
            System.out.println(i + " : "+ total);
        }
        System.out.println("average collision length: "+ uniqueTokens/nonEmptyIdx);
        System.out.println("longest chain: "+ maxChain);
        System.out.println("empty indices: "+ (table.length-nonEmptyIdx));
        System.out.println("non-empty indices: "+nonEmptyIdx);
    }
    /*
        Hash function explanations:
        hash1:
            Implementation:
                Adding each character in the string as an int.
            Performance on gettysburg.txt:
                Smallest table length with an average collision length <= 6: 24
        hash2:
            Implementation:
                26 * the first character + last character + length of string
            Performance on gettysburg.txt:
                Smallest table length with an average collision length <= 6: 24
        hash3:
            Implementation:
                Adding each character + the length of the list * the index of the character
            Performance on gettysburg.txt:
                Smallest table length with an average collision length <= 6: 24
        The performance of the hash functions are the same?
        Odd and even table lengths performance change in negligible.
        Average decreases by every prime number table length.
        No reason why hash2 was chosen for general case, other than being different for the specific case.
        Specific case keywords.txt performance:
            hash1:
                Smallest table length with the longest chain of <= 3: 43
            hash2:
                Smallest table length with the longest chain of <= 3: 46
            hash3:
                Smallest table length with the longest chain of <= 3: 51
        hash1 performs better than the rest.
     */
    public int hash1(T key){
        String s = key.toString();
        int num = 0;
        for(int i = 0; i < s.length(); i++){
            num += s.charAt(i);
        }
        num = num % table.length;
        return num;
    }

    public int hash2(T key){
        String s = key.toString();
        int num;
        if(s.length() == 1){
            num = s.charAt(0);
        } else {
            num = 26 * s.charAt(0) + s.charAt(s.length()-1) + s.length();
        }
        num = num % table.length;
        return num;
    }

    public int hash3(T key){
        String s = key.toString();
        int num = 0;
        for(int i = 0; i<s.length(); i++){
            num += s.charAt(i) + i * s.length();
        }
        num = num % table.length;
        return num;
    }


    public static void main(String[] args) {
        HashTable hash = new HashTable("gettysburg.txt", 100,"general");
        hash.display();

        HashTable hash2 = new HashTable("keywords.txt", 100,"specific");
        hash2.display();
    }
}
// Written by Jeffrey Do, do000043
