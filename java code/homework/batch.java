// package homework;

// import java.io.FileInputStream;
// import java.io.FileOutputStream;

// import java.io.IOException;
// import java.util.Scanner;

// public class batch {

// public static void main(String[] args) throws IOException {

// FileInputStream inputStream = new FileInputStream("./src/1.inp");
// Scanner scan = new Scanner(inputStream);
// FileOutputStream outputStream = new FileOutputStream("./src/batch.out");

// int n = scan.nextInt();

// int a;

// int idle = 0;
// int total = 0;
// int start = 0;

// while (start != n) {
// int count = 0;
// while ((a = scan.nextInt()) != -1) {
// count += 1;
// if (count % 2 != 0) {
// total += a;
// } else if (count % 2 == 0) {
// idle += a;
// total += a;
// }

// }
// start += 1;
// }
// StringBuilder s = new StringBuilder();
// s.append(idle + " " + total);

// outputStream.write(s.toString().getBytes());
// outputStream.close();
// scan.close();
// }

// }
