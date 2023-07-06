package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 한수  {
    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String N="";
        try {
            N=reader.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        int n=0;
        int count=0;
       int gap=0;
        int temp=0;
        int temp1=0;
        int temp2=0;
        while(n<Integer.parseInt(N))
        {
            n++;
            if(n>=1 && n<100)
            {
                count++;
            }else if(n>=100 && n<1000)
            {
                temp=n/100;
                temp1=(n%100)/10;
                temp2=(n%100)%10;

                if(temp > temp1)
                {
                    gap=temp-temp1;
                
                    if(temp1>temp2)
                    {
                        if(gap==temp1-temp2){
                            count++;
                       
                        }
                    }  
                }else if(temp<temp1){
                    gap=temp1-temp;
                    
                    if(temp2>temp1){
                        if(gap==temp2-temp1){
                            count++;
                          
                        }
                    }
                }else if(temp==temp1){
                    gap=temp1-temp;
                  
                    if(gap==temp2-temp1 )
                    {
                        count++;
                    
                    }
                }
            }
        }
        System.out.println(count);
    }

}
