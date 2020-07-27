/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.HashMap;
import javafx.util.Pair; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
//matrix sort
public class Main
{
	public static void main(String[] args) {
	    int a[][] = {{8,4,1},{4,4,1},{4,8,9}};
	    for(int i = 0; i < a.length; i++){
	        for(int j = 0; j < a[0].length; j++){
	            System.out.print(a[i][j]);
	        }
	        System.out.println("");
	    }
		//matrix sort
	    int i = 0;
	    int j = a[0].length-1;
	    int k,m;
	    List<Integer> b = new ArrayList<Integer>();
	    while(i < a.length){
	        if(j != 0){
	            k = i;
	            m = j;
	            while(k < a.length && m < a[0].length){
	                b.add(a[k][m]);
	                k++;
	                m++;
	            }
	            Collections.sort(b); 
	            k = i;
    	        m = j;
	            for(int x = 0; x < b.size(); x++){
	                a[k][m] = b.get(x);
	                k++;
	                m++;
	            }
	            j--;
	            b.clear();
	        }
	        else{
	            k = i;
	            m = j;
	            while(k < a.length && m < a[0].length){
	                System.out.println("k" + k + "m" + m);
	                b.add(a[k][m]);
	                System.out.println("b" + a[k][m]);
	                k++;
	                m++;
	            }
	            Collections.sort(b); 
	            k = i;
    	        m = j;
	            for(int x = 0; x < b.size(); x++){
	                a[k][m] = b.get(x);
	                k++;
	                m++;
	            }
	            i++;
	            b.clear();
	        }
	        
	    }
	    
	   for(i = 0; i < a.length; i++){
	        for(j = 0; j < a[0].length; j++){
	            System.out.print(a[i][j]);
	        }
	        System.out.println("");
	    }
	}
}