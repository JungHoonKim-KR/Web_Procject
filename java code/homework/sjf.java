// package homework;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collections;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;

// import java.io.IOException;
// import java.util.Scanner;

// public class sjf {
// public static class Process implements Comparable<Process> {
// public Integer Pno;
// ArrayList<Integer> queue = new ArrayList<>();
// public int EndTime;
// public boolean isIO;

// Process(Integer num, boolean isIO, int EndTime, ArrayList<Integer> queue) {
// this.Pno = num;
// this.isIO = isIO;
// this.EndTime = EndTime;
// this.queue = queue;
// }

// void isIO(boolean isIO) {
// this.isIO = isIO;
// }

// @Override
// public int compareTo(Process o) {

// if (this.queue.get(0) == o.queue.get(0)) {
// if (this.EndTime == o.EndTime) {
// return this.Pno - o.Pno;
// }
// return this.EndTime - o.EndTime;
// }

// return this.queue.get(0) - o.queue.get(0);

// }
// }

// public static class UnreadyQueue implements Comparable<UnreadyQueue> {
// public Process process;

// UnreadyQueue(Process process) {
// this.process = process;
// }

// @Override
// public int compareTo(UnreadyQueue o) {
// return this.process.EndTime - o.process.EndTime;

// }

// }

// public static int present_time = 0;

// public static void main(String[] args) throws IOException {
// FileInputStream inputStream = new FileInputStream("sjf.inp");
// Scanner scan = new Scanner(inputStream);
// FileOutputStream outputStream = new FileOutputStream("sjf.out");

// int n = Integer.parseInt(scan.nextLine());
// ArrayList<Process> process = new ArrayList<>(n);
// ArrayList<UnreadyQueue> notReadyProcess = new ArrayList<>(n);

// for (int i = 0; i < n; i++) {
// ArrayList<Integer> queue = new ArrayList<>();
// int endTime = 0;
// String s = scan.nextLine();
// String[] strArr = s.split(" ");
// endTime = Integer.parseInt(strArr[0]);
// for (int k = 1; k < strArr.length - 1; k++) {
// queue.add(Integer.parseInt(strArr[k]));
// }
// notReadyProcess.add(new UnreadyQueue(new Process(i, false, endTime, queue)));
// }

// int idle_time = 0;
// int io_time = 0;
// int cpu_time = 0;
// int[] result_present_time = new int[n];

// while (!notReadyProcess.isEmpty() || !process.isEmpty()) {

// Collections.sort(notReadyProcess);

// for (int i = 0; i < notReadyProcess.size(); i++) {
// if (notReadyProcess.get(i).process.EndTime <= present_time) {
// process.add(notReadyProcess.remove(i).process);
// i -= 1;
// } else {
// break;
// }
// }

// if (process.isEmpty()) {
// idle_time += 1;
// present_time += 1;
// } else {
// Collections.sort(process);
// Process p1 = process.remove(0);
// cpu_time = p1.queue.remove(0);

// p1.EndTime = present_time + cpu_time;
// present_time += cpu_time;
// p1.isIO = true;

// if (!p1.queue.isEmpty()) {
// io_time = p1.queue.remove(0);
// p1.EndTime += io_time;
// System.out.println("io = " + io_time);
// p1.isIO = true;
// if (!p1.queue.isEmpty()) {
// notReadyProcess.add(new UnreadyQueue(p1));
// } else {
// result_present_time[p1.Pno] = p1.EndTime;
// }
// } else {
// result_present_time[p1.Pno] = p1.EndTime;
// }

// }

// }

// StringBuilder s = new StringBuilder();
// s.append(idle_time + "\n");
// for (int i = 0; i < n; i++) {
// s.append(result_present_time[i] + "\n");
// }
// outputStream.write(s.toString().getBytes());
// outputStream.close();
// scan.close();
// }
// }