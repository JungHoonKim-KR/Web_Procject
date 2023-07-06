// package homework;

// import java.util.PriorityQueue;
// import java.util.ArrayList;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;

// import java.io.IOException;
// import java.util.Scanner;

// class Process implements Comparable<Process> {
// public Integer Pno;
// public int nextTime = 0;
// public int cpu_time = 0;
// public int start;
// public int restart;
// public int wait_time = 0;

// Process(Integer num, boolean ready, int start, int nextTime) {
// this.Pno = num;
// this.start = start;
// this.nextTime = nextTime;
// }

// @Override
// public int compareTo(Process p) {
// if (this.nextTime > p.nextTime)
// return 1;
// else if (this.nextTime < p.nextTime)
// return -1;
// else {
// if (this.Pno > p.Pno)
// return 1;
// else if (this.Pno < p.Pno)
// return -1;
// else
// return 0;
// }
// }
// }

// public class srtf {
// public static void main(String[] args) throws IOException {
// FileInputStream inputStream = new FileInputStream("./java code/2.inp");
// Scanner scan = new Scanner(inputStream);
// FileOutputStream outputStream = new FileOutputStream("./java code/2.out");

// int n = Integer.parseInt(scan.nextLine());
// ArrayList<Process> process = new ArrayList<>(n);
// PriorityQueue<Process> ready = new PriorityQueue<>();

// for (int i = 0; i < n; i++) {
// int start = 0;
// int nextTime = 0;
// String s = scan.nextLine();
// String[] strArr = s.split(" ");
// start = Integer.parseInt(strArr[0]);
// nextTime = Integer.parseInt(strArr[1]);
// process.add(new Process(i, false, start, nextTime));
// }

// int wait_time = 0;
// int present_time = 0;
// int count = 0;
// while (true) {
// if (ready.isEmpty()) {

// if (count == n)
// break;

// for (int i = 0; i < process.size(); i++) {
// if (present_time == process.get(i).start) {
// ready.add(process.get(i));
// }
// }
// if (ready.isEmpty()) {
// present_time += 1;
// }
// }
// if (!ready.isEmpty()) {
// ready.peek().cpu_time += 1;
// ready.peek().nextTime -= 1;
// present_time += 1;
// wait_time += ready.size() - 1;
// if (ready.peek().nextTime == 0) {
// ready.remove();
// count += 1;
// }
// for (int i = 0; i < process.size(); i++) {

// if (present_time == process.get(i).start) {
// if (!process.isEmpty()) {
// ready.add(process.get(i));
// }
// }
// }
// }
// }

// StringBuilder s = new StringBuilder();

// s.append(wait_time);
// outputStream.write(s.toString().getBytes());
// outputStream.close();
// scan.close();
// }
// }
