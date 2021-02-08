import java.util.*;

public class Main{
    public static void main(String [] args) {
        String s = new String("0000011111");
        long cur = 1L;
        Long res = 0L;
        for(int i = s.length()-1; i>=0; i--){
            if(s.charAt(i) == '1'){
                res += cur;
            }
            cur *= 2;
        }
        System.out.print(res);
    }
}

