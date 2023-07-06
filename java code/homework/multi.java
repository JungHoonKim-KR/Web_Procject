// package homework;

// import java.util.List;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.util.Collections;
// import java.io.IOException;
// import java.util.Scanner;

// public class multi {

// public static void main(String[] args) throws IOException {

// FileInputStream inputStream = new FileInputStream("./java code/1.inp");
// Scanner scan = new Scanner(inputStream);
// FileOutputStream outputStream = new FileOutputStream("./java code/1.out");

// int n = Integer.parseInt(scan.nextLine());

// ArrayList<ArrayList<Integer>> process = new ArrayList<ArrayList<Integer>>();

// for (int i = 0; i < n; i++) {
// String s = scan.nextLine();
// String[] strArr = s.split(" ");

// ArrayList<Integer> nums = new ArrayList<>();
// for (String num : strArr) {
// nums.add(Integer.parseInt(num));
// }
// process.add(nums);
// }

// ArrayList<Boolean> isIO = new ArrayList<Boolean>(n);
// for (int i = 0; i < n; i++) {
// isIO.add(false);
// }

// ArrayList<Integer> processEndTime = new ArrayList<Integer>(n);
// for (int i = 0; i < n; i++) {
// processEndTime.add(0);
// }
// int idle_time = 0;
// int num = 0;
// int present_time = 0;
// int io_time = 0;
// int cpu_time = 0;
// int last_p = 0;

// while (process.size() != 0) {

// if (isIO.get(num)) {
// if (processEndTime.get(num) <= present_time) {
// isIO.set(num, false);
// } else {
// num += 1;
// if (num == process.size()) {
// idle_time += 1;
// present_time += 1;
// num = 0;
// }
// continue;
// }
// }
// if (!isIO.get(num)) {

// cpu_time = process.get(num).get(0);

// if (cpu_time == -1) {
// if (process.size() == 1) {
// idle_time -= last_p;
// }
// process.remove(num);
// isIO.remove(num);
// processEndTime.remove(num);
// num = 0;
// } else {

// present_time += cpu_time;
// process.get(num).remove(0);

// io_time = process.get(num).get(0);
// if (process.size() == 1 && process.get(num).size() == 2) {
// last_p = io_time;

// } else if (process.size() == 1 && process.get(num).size() == 3) {
// present_time += io_time;
// idle_time += io_time;
// process.get(num).remove(0);
// present_time += process.get(num).get(0);
// break;
// }

// if (io_time == -1) {
// process.remove(num);
// isIO.remove(num);
// processEndTime.remove(num);
// num = 0;
// } else {
// int k = present_time + io_time;
// processEndTime.set(num, k);
// process.get(num).remove(0);
// isIO.set(num, true);
// num = 0;
// }
// }
// }
// }

// StringBuilder s = new StringBuilder();
// s.append(idle_time + " " + present_time);

// outputStream.write(s.toString().getBytes());
// outputStream.close();
// scan.close();
// }

// }
