package homework;

import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class page {
    public static void main(String[] args) throws IOException {
        File file1 = new File("./java code/1.inp");
        BufferedReader br = new BufferedReader(new FileReader(file1));
        BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("./java code/1.out"));

        String m = br.readLine();
        StringBuilder sr = new StringBuilder();

        int memory_size = Integer.parseInt(m);
        ArrayList<Integer> future_strings = new ArrayList<Integer>();
        int[] FIFO_memory_strings= new int[memory_size];
        int[] FIFO_future_strings= new int[10001];
        int[] LRU_memory_strings= new int[memory_size];
        int[] LRU_future_strings= new int[10001];
        int[] OP_memory_strings= new int[memory_size];
        int[] OP_future_strings= new int[10001];

        while (true) {
            String a = br.readLine();
            if(a!= null)
                future_strings.add(Integer.parseInt(a));
            else 
                break;
        
        }

      
        for (int i = 0; i < future_strings.size(); i++) {
            FIFO_future_strings[i]=future_strings.get(i);
            LRU_future_strings[i]=future_strings.get(i);
            OP_future_strings[i]=future_strings.get(i);
        }
        for(int i=0; i<memory_size;i++)
        {
            FIFO_memory_strings[i]=-1;
            LRU_memory_strings[i]=-1;
            OP_memory_strings[i]=-1;
        }

        
        int FIFO_fault_count = 0;
        int FIFO_flag=0;
        int count=0;
        while(true)
        {
            int next_request=FIFO_future_strings[count];
            boolean hit=false;
            if(next_request==-1)
                break;

                
            for(int i=0;i<memory_size;i++)
            {
                if(FIFO_memory_strings[i]==next_request)
                {
                    hit=true;
                    break;
                }
            }

                if(hit==false)
                {
                    FIFO_fault_count++;
                    FIFO_memory_strings[FIFO_flag]=next_request;
                
                    FIFO_flag++;
                    if(FIFO_flag== memory_size)
                    {
                        FIFO_flag=0;
                    }
                }
                count++;
        }

       
        int[]  TimeStamp= new int[memory_size];
        for(int i=0;i< memory_size;i++)
        {
            TimeStamp[i]=0;
        }
      
        int currentTime=1;
        int LRU_fault_count=0;
        int count1=0;
        while(true)
        {
            int next_request1=LRU_future_strings[count1];
            if(next_request1==-1)
                break;
            boolean LRU_hit=false;
            for(int i=0;i<memory_size;i++)
            {
                if(LRU_memory_strings[i]==next_request1)
                {
                    LRU_hit=true;
                    TimeStamp[i]=currentTime;
                    currentTime++;
                    break;
                }
            }
            
            if(LRU_hit==false)
            {
                int min=TimeStamp[0];
                int min_idx=0;
                for(int i=1;i<memory_size;i++)
                {
                    if(min>TimeStamp[i])
                    {
                        min=TimeStamp[i];
                        min_idx=i;
                    }
                }
                LRU_memory_strings[min_idx]=next_request1;
                TimeStamp[min_idx]=currentTime;
                currentTime++;
                LRU_fault_count++;
            }
            count1++;
        }

     
        int currentTime1=1;
        int OP_fault_count=0;
        boolean OP_hit=false;
        boolean a=false;
        int count2=0;
        
        while(true)
        {
            int next_request2=OP_future_strings[count2];

            if(next_request2==-1)
                break;
           
            for(int i=0;i<memory_size;i++)
            {    OP_hit=false;
                if(OP_memory_strings[i]==next_request2)
                {
                    OP_hit=true;
                    currentTime1++;
                    break;
                }
            }
            
            if(OP_hit==false)
            {
                a=false;
               for(int i=0;i<memory_size;i++)
               {
                    if(OP_memory_strings[i]==-1)
                    {
                        OP_memory_strings[i]=next_request2;
                        OP_fault_count++;
                        currentTime1++;
                        a=true;
                        break;
                    }
               }
           
               if(a==false)
               {
                    for(int i=0;i<memory_size;i++)
                    {
                    
                        TimeStamp[i]=10001;
                       
                    }
                 
                 for(int i=0;i<memory_size;i++)
                 {
                    for(int j= currentTime1;j<OP_future_strings.length-1;j++)
                    {
                        if(OP_future_strings[j]==OP_memory_strings[i])
                        {
                            TimeStamp[i]=j;
                            break;
                        }
                    }
                 } 
                    int max=TimeStamp[0];
                    int max_idx=0;
                    for(int i=1;i<TimeStamp.length;i++)
                    {
                        if(max<TimeStamp[i])
                        {
                            max=TimeStamp[i];
                            max_idx=i;
                        }
                    }
               
                
                OP_memory_strings[max_idx]=next_request2;
                currentTime1++;
                OP_fault_count++;
                }
            }
            count2++;
        }
        
        sr.append("FIFO: " + FIFO_fault_count + "\n"+"LRU: " + LRU_fault_count + "\n"+"OPT: " + OP_fault_count);
        bw.write(sr.toString().getBytes());
        bw.close();
        br.close();
    }

}
