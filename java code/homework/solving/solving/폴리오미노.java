package solving;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 폴리오미노
{
    public static void main(String[] args)
    {
        ArrayList <String> A=new ArrayList<String>();
        ArrayList <String> B=new ArrayList<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String []ex=new String[]{};
    
        try {
            String ex1=br.readLine();
            ex=ex1.split("");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        for(int i=0;i<4;i++)
        {
            A.add(0,"A");
        }
        for(int i=0;i<2;i++){
            B.add(0,"B");
        }
        boolean dot_check=false;
        int count=0;
        int present_pointer=0;
        int present_pointer1=0;
        boolean done=false;
        while (true)
        {
            count=0;
            present_pointer1=present_pointer;
            if(done)
            {
                break;
            }
            // dot 찾기 반복문
            while(true)
            {   
                if(present_pointer>=ex.length)
                {
                    done=true;
                    break;
                }
                if(ex[present_pointer].equals(".")){
                    dot_check=true;
                    // present_pointer == dot 바로 다음인덱스
                    present_pointer++;
                    break;
                }
                present_pointer++;
                //count 는 dot 이전까지의 문자개수
                dot_check=false;
                count++;
            }
            
            if(dot_check)
            {
                if(count>=4)
                {
                    for(int i=0;i<(count/4)*4;i++)
                    {
                        if(ex[present_pointer1].equals(".")){
                            break;
                        }
                        ex[present_pointer1]="A";
                        present_pointer1++;
                    }
                    count=count%4;

                
                    if(count%2==0)
                    {
                        for(int i=0;i<count;i++)
                        {
                            if(ex[present_pointer1].equals(".")){
                                break;
                            }
                            ex[present_pointer1]="B";
                            present_pointer1++;
                        }
                    }else {
                        for(int i=0;i<ex.length;i++)
                        {
                            ex[i]="0";
                        }
                        ex[0]="-1";
                        done=true;
                        break;
                        
                    }
                }else {
                    if(count%2==0)
                    {
                        for(int i=0;i<count;i++)
                        {
                            if(ex[present_pointer1].equals(".")){
                                break;
                            }
                            ex[present_pointer1]="B";
                            present_pointer1++;
                        }
                    }else {
                        for(int i=0;i<ex.length;i++)
                        {
                            ex[i]="0";
                        }
                        ex[0]="-1";
                        done=true;
                        break;
                        
                    }
                }
                

            }
            // dot이 없을때
            else {
                
                    if(count>=4)
                    {
                        for(int i=0;i<(count/4)*4;i++)
                        {
                            if(ex[present_pointer1].equals(".")){
                                break;
                            }
                            ex[present_pointer1]="A";
                            present_pointer1++;
                        }
                        count=count%4;

                        if(count%2==0)
                        {
                            for(int i=0;i<count;i++)
                            {
                                if(ex[present_pointer1].equals(".")){
                                    break;
                                }
                                ex[present_pointer1]="B";
                                present_pointer1++;
                            }
                            done=true;
                            break;
                        }else {
                            for(int i=0;i<ex.length;i++)
                            {
                                ex[i]="0";
                            }
                            ex[0]="-1";

                            done=true;
                            break;
                            
                        }
                    }else {   if(count%2==0)
                        {
                            for(int i=0;i<count;i++)
                            {
                                if(ex[present_pointer1].equals(".")){
                                    break;
                                }
                                ex[present_pointer1]="B";
                                present_pointer1++;
                            }
                            done=true;
                            break;
                        }else {
                            for(int i=0;i<ex.length;i++)
                            {
                                ex[i]="0";
                            }
                            ex[0]="-1";

                            done=true;
                            break;
                            
                        }

                    }
                
            }
        }
        
        for(int i=0;i<ex.length;i++)
        {
            if(ex[i]=="-1"){
                System.out.println("-1");
                break;
            }
            System.out.printf(ex[i]);
        }
           
           
        
    }
}