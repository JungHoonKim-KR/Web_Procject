// package homework;

// import java.io.FileReader;
// import java.io.BufferedOutputStream;
// import java.io.BufferedReader;

// import java.io.FileOutputStream;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.StringTokenizer;

// class Process {
// int num;
// ArrayList<Integer> max = new ArrayList<Integer>();
// ArrayList<Integer> allocate = new ArrayList<Integer>();
// ArrayList<Integer> need = new ArrayList<Integer>();

// Process(int num, ArrayList<Integer> arr) {
// this.num = num;

// for (int i = 0; i < arr.size(); i++) {
// max.add(arr.get(i));

// }
// }

// void Alloc(ArrayList<Integer> arr) {
// ArrayList<Integer> a = new ArrayList<Integer>();

// a = arr;
// for (int i = 0; i < a.size(); i++) {
// allocate.add(a.get(i));
// need.add(max.get(i) - allocate.get(i));
// }

// }

// }

// public class banker {

// public static void main(String[] args) throws IOException {
// FileReader reader = new FileReader("./java code/1.inp");
// BufferedReader br = new BufferedReader(reader);
// BufferedOutputStream bs = new BufferedOutputStream(new
// FileOutputStream("./java code/1.out"));

// int process_size = 0;
// int resourse_size = 0;
// ArrayList<Integer> resourse = new ArrayList<Integer>();
// ArrayList<Process> process = new ArrayList<Process>();
// ArrayList<String> queue = new ArrayList<String>();
// ArrayList<Integer> available = new ArrayList<Integer>();
// ArrayList<String> wait_queue = new ArrayList<String>();

// String line;
// StringBuilder sr = new StringBuilder();
// for (int i = 0; i < 2; i++) {
// if (i == 0) {
// line = br.readLine();
// ArrayList<Integer> arr = new ArrayList<Integer>();
// StringTokenizer tokens = new StringTokenizer(line);
// while (tokens.hasMoreTokens()) {
// arr.add(Integer.parseInt(tokens.nextToken()));
// }

// process_size = arr.get(0);
// resourse_size = arr.get(1);
// } else if (i == 1) {
// line = br.readLine();
// ArrayList<Integer> arr = new ArrayList<Integer>();
// StringTokenizer tokens = new StringTokenizer(line);
// while (tokens.hasMoreTokens()) {
// arr.add(Integer.parseInt(tokens.nextToken()));
// }
// for (int j = 0; j < arr.size(); j++) {
// resourse.add(arr.get(j));
// }
// }
// }
// line = br.readLine();
// ArrayList<Integer> arr = new ArrayList<Integer>();

// for (int i = 0; i < process_size; i++) {
// line = br.readLine();
// arr.clear();
// StringTokenizer tokens = new StringTokenizer(line);
// while (tokens.hasMoreTokens()) {
// arr.add(Integer.parseInt(tokens.nextToken()));
// }

// process.add(new Process(i, arr));

// }
// arr.clear();
// line = br.readLine();
// for (int i = 0; i < process_size; i++) {
// arr.clear();
// line = br.readLine();
// StringTokenizer tokens = new StringTokenizer(line);
// while (tokens.hasMoreTokens()) {
// arr.add(Integer.parseInt(tokens.nextToken()));
// }
// process.get(i).Alloc(arr);
// }

// line = br.readLine();
// while ((line = br.readLine()) != null) {
// queue.add(line);
// }

// queue.remove(queue.size() - 1);

// for (int j = 0; j < resourse.size(); j++) {
// int sum = 0;

// for (int i = 0; i < process.size(); i++) {
// sum += process.get(i).allocate.get(j);
// }
// available.add(resourse.get(j) - sum);
// }

// while (true) {
// if (queue.isEmpty())
// break;

// String r = queue.get(0).substring(0, 7);

// String s = queue.get(0).substring(8, queue.get(0).length());
// r = r.trim();
// s = s.trim();
// String[] s1 = s.split(" ");

// if (r.equals("request")) {
// ArrayList<Integer> request = new ArrayList<Integer>();
// for (int i = 0; i < s1.length; i++) {
// request.add(Integer.parseInt(s1[i]));
// }

// int num = request.get(0);
// request.remove(0);

// int count_available = 0;
// int count_need = 0;

// for (int i = 0; i < request.size(); i++) {
// if (available.get(i) - request.get(i) >= 0 && process.get(num).need.get(i) -
// request.get(i) >= 0) {
// count_available += 1;
// count_need += 1;
// } else if (available.get(i) - request.get(i) < 0
// && process.get(num).need.get(i) - request.get(i) >= 0) {
// count_need++;
// } else {
// break;
// }
// }

// if (count_available == resourse_size && count_need == resourse_size) {
// int need_count = 0;
// for (int i = 0; i < process.size(); i++) {

// ArrayList<Integer> temp_available = new ArrayList<Integer>();
// ArrayList<Integer> temp_need = new ArrayList<Integer>();
// need_count = 0;
// for (int j = 0; j < available.size(); j++) {
// temp_available.add(available.get(j) - request.get(j));
// if (i == num) {
// temp_need.add(process.get(num).need.get(j) - request.get(j));
// } else {
// temp_need.add(process.get(num).need.get(j));
// }
// if (temp_need.get(j) <= temp_available.get(j) && temp_need.get(j) >= 0) {
// need_count += 1;
// } else {
// break;
// }
// }

// if (need_count == resourse_size) {
// break;
// }

// }
// if (need_count == resourse_size) {

// for (int i = 0; i < available.size(); i++) {

// process.get(num).allocate.set(i, process.get(num).allocate.get(i) +
// request.get(i));
// available.set(i, available.get(i) - request.get(i));
// process.get(num).need.set(i,
// process.get(num).max.get(i) - process.get(num).allocate.get(i));
// }

// for (int item : available) {
// sr.append(item);
// sr.append(" ");
// }
// sr.append("\n");
// queue.remove(0);

// } else {
// wait_queue.add(queue.get(0));
// queue.remove(0);

// for (int item : available) {
// sr.append(item);
// sr.append(" ");
// }
// sr.append("\n");

// }

// } else if (count_need == resourse_size && count_available != resourse_size) {
// wait_queue.add(queue.get(0));
// queue.remove(0);

// for (int item : available) {
// sr.append(item);
// sr.append(" ");
// }
// sr.append("\n");
// } else {
// queue.remove(0);

// for (int item : available) {
// sr.append(item);
// sr.append(" ");
// }
// sr.append("\n");
// }

// } else if (r.equals("release")) {

// ArrayList<Integer> release = new ArrayList<Integer>();
// for (int i = 0; i < s1.length; i++) {
// release.add(Integer.parseInt(s1[i]));
// }
// int num = release.get(0);
// release.remove(0);

// int never = 0;

// for (int j = 0; j < available.size(); j++) {
// if (process.get(num).allocate.get(j) < release.get(j)) {
// never += 1;
// break;
// }
// }
// if (never == 1) {
// queue.remove(0);
// for (int item : available) {
// sr.append(item);
// sr.append(" ");
// }
// sr.append("\n");
// continue;
// }

// for (int i = 0; i < release.size(); i++) {
// process.get(num).allocate.set(i, process.get(num).allocate.get(i) -
// release.get(i));
// available.set(i, available.get(i) + release.get(i));
// process.get(num).need.set(i, process.get(num).max.get(i) -
// process.get(num).allocate.get(i));
// }

// queue.remove(0);

// for (int i = 0; i < wait_queue.size(); i++) {
// String a = wait_queue.get(i).substring(8, wait_queue.get(i).length());

// ArrayList<Integer> request1 = new ArrayList<Integer>();
// a = a.trim();
// String[] a1 = a.split(" ");

// for (int j = 0; j < s1.length; j++) {
// request1.add(Integer.parseInt(a1[j]));
// }

// num = Integer.parseInt(a1[0]);
// request1.remove(0);

// int k = 0;
// int count_available = 0;
// int count_need = 0;
// for (int j = 0; j < request1.size(); j++) {

// if (available.get(j) - request1.get(j) >= 0
// && process.get(num).need.get(j) - request1.get(j) >= 0) {
// count_available += 1;
// count_need += 1;
// } else if (available.get(j) - request1.get(j) < 0
// && process.get(num).need.get(j) - request1.get(j) >= 0) {
// count_need++;
// } else {
// break;
// }

// if (k == resourse_size) {
// break;
// }
// }

// if (count_available == resourse_size && count_need == resourse_size) {
// boolean wait_OK = false;
// for (int k1 = 0; k1 < process.size(); k1++) {
// k = 0;
// for (int j = 0; j < available.size(); j++) {

// if (num == k1) {
// if (process.get(k1).need.get(j) - request1.get(j) <= available.get(j)
// - request1.get(j))
// k += 1;
// } else {
// if (process.get(k1).need.get(j) <= available.get(j) - request1.get(j))
// k += 1;
// }
// }
// if (k == available.size()) {
// wait_OK = true;
// break;
// }
// }

// if (wait_OK) {
// for (int j = 0; j < available.size(); j++) {
// process.get(num).allocate.set(j, process.get(num).allocate.get(j) +
// request1.get(j));
// available.set(j, available.get(j) - request1.get(j));
// process.get(num).need.set(j,
// process.get(num).max.get(j) - process.get(num).allocate.get(j));
// }

// wait_queue.remove(i);
// i = -1;
// } else {

// }

// } else if (count_need == resourse_size && count_available != resourse_size) {

// } else {
// wait_queue.remove(i);
// i = -1;
// }
// }

// for (int item : available) {
// sr.append(item);
// sr.append(" ");
// }
// sr.append("\n");
// }

// }
// bs.write(sr.toString().getBytes());
// bs.close();
// br.close();

// }

// }
