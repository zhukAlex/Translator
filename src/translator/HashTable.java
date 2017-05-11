/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

/**
 *
 * @author Алексей
 */
public class HashTable {
    
    public static int getHash(String word, int size){
    
        int a = 54059; 
        int b = 76963; 
        int h = 37;
        int i = 0;
        char c[] = word.toCharArray();
        
        while ( i < word.length() )
        {
            h = (h * a) ^ (c[i] * b);
            i++;
        }
        
        return Math.abs(h % size);
    }
}
