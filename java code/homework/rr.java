/* 
package homework;

import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

class Process implements Comparable<Process> {
    public Integer Pno;
    public int EndTime;
    ArrayList<Integer> queue = new ArrayList<Integer>();

    Process(Integer num, int EndTime, ArrayList<Integer> queue) {
        this.Pno = num;
        this.EndTime = EndTime;
        this.queue = queue;
    }

    @Override
    public int compareTo(Process p) {
        if (this.EndTime > p.EndTime)
            return 1;
        else if (this.EndTime < p.EndTime)
            return -1;
        else {
            if (this.Pno > p.Pno)
                return 1;
            else if (this.Pno < p.Pno)
                return -1;
            else
                return 0;
        }
    }
}

public class rr {
    public static void main(String[] args) throws IOException {
        File file1 = new File("./java code/1.inp");
        BufferedReader br = new BufferedReader(new FileReader(file1));
        BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("./java code/1.out"));

        String a = br.readLine();
        String[] st = a.split(" ");
        int n = Integer.parseInt(st[0]);
        int time_slice = Integer.parseInt(st[1]);

        ArrayList<Process> process = new ArrayList<>(n);
        ArrayList<Process> process1 = new ArrayList<>(n);
        PriorityQueue<Process> ready = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            String[] strArr = s.split(" ");
            ArrayList<Integer> queue = new ArrayList<>();
            for (int j = 0; j < strArr.length - 1; j++) {
                queue.add(Integer.parseInt(strArr[j]));
            }
            int endTime = queue.get(0);
            queue.remove(0);
            process.add(new Process(i, endTime, queue));

        }
        int idle_time = 0;
        int present_time = 0;
        int count = 0;
        int io_time = 0;
        ArrayList<Integer> r = new ArrayList<>();
        StringBuilder s = new StringBuilder();

        while (true) {
            if (count == n)
                break;

            for (int i = 0; i < process.size(); i++) {
                if (process.get(i).EndTime <= present_time) {
                    if (!ready.contains(process.get(i)) && process.get(i).queue.size() != 0) {
                        ready.add(process.get(i));
                    }

                }
            }
            if (ready.isEmpty()) {
                present_time += 1;
                idle_time += 1;
                continue;
            }
            if (!ready.isEmpty()) {

                if (!ready.peek().queue.isEmpty()) {
                    if (ready.peek().queue.get(0) <= time_slice) {
                        present_time += ready.peek().queue.get(0);
                        ready.peek().EndTime = present_time;
                        ready.peek().queue.remove(0);

                        if (ready.peek().queue.isEmpty()) {
                            ready.remove();
                            count += 1;
                            for (int i = 0; i < process.size(); i++) {
                                if (process.get(i).queue.isEmpty()) {
                                    process1.add(process.get(i));

                                    process.remove(i);
                                }
                            }

                        } else {
                            if (ready.peek().queue.size() > 1) {
                                io_time = ready.peek().queue.get(0);
                                ready.peek().EndTime += io_time;
                                ready.peek().queue.remove(0);
                                ready.remove();
                            } else if (ready.peek().queue.size() == 1) {
                                io_time = ready.peek().queue.get(0);
                                ready.peek().EndTime += io_time;
                                ready.peek().queue.remove(0);
                                ready.remove();
                                for (int i = 0; i < process.size(); i++) {
                                    if (process.get(i).queue.isEmpty()) {
                                        process1.add(process.get(i));

                                        process.remove(i);
                                    }
                                }
                                count += 1;
                            }
                        }

                    } else {
                        present_time += time_slice;
                        ready.peek().EndTime = present_time;
                        ready.peek().queue.set(0, ready.peek().queue.get(0) - time_slice);

                        Process p = ready.peek();
                        ready.remove();
                        ready.add(p);
                    }
                } else {
                    ready.remove();

                }
            }

        }

        s.append(idle_time + "\n");

        for (int i = 0; i < process1.size(); i++) {
            for (int j = 0; j < process1.size(); j++)
                if (process1.get(j).Pno == i) {
                    s.append(process1.get(j).EndTime + "\n");
                }

        }

        bw.write(s.toString().getBytes());
        bw.close();
        br.close();
    }

}
*/