// package homework;

// import java.io.FileReader;
// import java.io.BufferedOutputStream;
// import java.io.BufferedReader;

// import java.io.FileOutputStream;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.Comparator;
// import java.util.HashSet;

// import java.util.List;
// import java.util.Set;

// public class test {

// public static void main(String[] args) throws IOException {

// FileReader reader = new FileReader("./src/2.inp");
// BufferedReader br = new BufferedReader(reader);
// BufferedOutputStream bs = new BufferedOutputStream(new
// FileOutputStream("./src/test.out"));

// int n = Integer.parseInt(br.readLine());
// ArrayList<String[]> names = new ArrayList<>();
// String[] last_names = new String[n];
// String line;
// int max = 0;

// while ((line = br.readLine()) != null) {
// String[] arr = line.split(" ");
// if (arr[1].length() > max) {
// max = arr[1].length();
// }
// names.add(arr);
// }

// for (int i = 0; i < n; i++) {
// last_names[i] = names.get(i)[2];
// }
// Collections.sort(names, new Comparator<String[]>() {
// @Override
// public int compare(String[] o1, String[] o2) {
// return Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]);
// }
// });
// StringBuilder s = new StringBuilder();
// for (int i = 0; i < names.size(); i++) {

// System.out.print(names.get(i)[0] + " " + names.get(i)[1]);
// s.append(names.get(i)[0] + " " + names.get(i)[1]);

// for (int j = 0; j < max - names.get(i)[1].length(); j++) {
// System.out.print(" ");
// s.append(" ");
// }
// System.out.println(" " + names.get(i)[2]);
// s.append(" " + names.get(i)[2] + "\n");
// }

// Set<String> set = new HashSet<>(Arrays.asList(last_names));

// List<String> set2 = new ArrayList<>(set);
// Collections.sort(set2);

// System.out.println();
// s.append("\n");
// for (int i = 0; i < set2.size(); i++) {
// String dup = set2.get(i);
// int count = Collections.frequency(Arrays.asList(last_names), dup);
// if (count > 1) {
// System.out.println(dup + " " + count);
// s.append(dup + " " + count + "\n");
// }

// }

// bs.write(s.toString().getBytes());
// bs.close();
// br.close();
// }
// }
