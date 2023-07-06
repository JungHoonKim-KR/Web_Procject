// package homework;

// import java.io.BufferedReader;
// import java.io.BufferedOutputStream;
// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;

// public class page1 {
//     public static void main(String[] args) throws IOException {
//         File file1 = new File("page.inp");
//         BufferedReader br = new BufferedReader(new FileReader(file1));
//         BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("page.out"));

//         String m = br.readLine();
//         ArrayList<Integer> future_strings = new ArrayList<Integer>();
//         ArrayList<Integer> FIFO_memory_strings = new ArrayList<Integer>();
//         ArrayList<Integer> FIFO_future_strings = new ArrayList<Integer>();
//         ArrayList<Integer> LRU_memory_strings = new ArrayList<Integer>();
//         ArrayList<Integer> LRU_future_strings = new ArrayList<Integer>();
//         ArrayList<Integer> OP_memory_strings = new ArrayList<Integer>();
//         ArrayList<Integer> OP_future_strings = new ArrayList<Integer>();

//         StringBuilder sr = new StringBuilder();

//         int memory_size = Integer.parseInt(m);

//         while (true) {
//             String a = br.readLine();
//             if (Integer.parseInt(a) == -1)
//                 break;
//             else {
//                 future_strings.add(Integer.parseInt(a));
//             }
//         }

//         for (int i = 0; i < future_strings.size(); i++) {
//             FIFO_future_strings.add(future_strings.get(i));
//             LRU_future_strings.add(future_strings.get(i));
//             OP_future_strings.add(future_strings.get(i));
//         }

//         int FIFO_fault_count = 0;
//         boolean a = false;

//         while (true) {
//             if (FIFO_memory_strings.size() == memory_size || FIFO_future_strings.size() == 0) {
//                 break;
//             }

//             for (int j = 0; j < FIFO_memory_strings.size(); j++) {
//                 System.out.println(FIFO_memory_strings);
//                 if (FIFO_memory_strings.get(j) == FIFO_future_strings.get(0)) {
//                     a = true;
//                     break;
//                 } else
//                     a = false;
//             }

//             if (a == true) {
//                 FIFO_future_strings.remove(0);
//             } else {
//                 FIFO_memory_strings.add(FIFO_future_strings.get(0));
//                 FIFO_future_strings.remove(0);
//                 FIFO_fault_count++;
//             }

//         }

//         boolean FIFO_hit = true;
//         int FIFO_flag = 0;

//         while (true) {
//             if (FIFO_future_strings.isEmpty()) {
//                 break;
//             }

//             for (int i = 0; i < FIFO_memory_strings.size(); i++) {
//                 if (FIFO_memory_strings.get(i) == FIFO_future_strings.get(0)) {
//                     FIFO_future_strings.remove(0);
//                     FIFO_hit = true;
//                     break;
//                 } else {
//                     FIFO_hit = false;
//                 }
//             }

//             if (FIFO_hit == false) {
//                 if (FIFO_flag == FIFO_memory_strings.size()) {
//                     FIFO_flag = 0;
//                     FIFO_memory_strings.add(FIFO_flag, FIFO_future_strings.get(0));
//                     FIFO_memory_strings.remove(FIFO_flag + 1);
//                     FIFO_future_strings.remove(0);
//                 } else {
//                     FIFO_memory_strings.add(FIFO_flag, FIFO_future_strings.get(0));
//                     FIFO_memory_strings.remove(FIFO_flag + 1);
//                     FIFO_future_strings.remove(0);
//                 }
//                 FIFO_flag++;
//                 FIFO_fault_count++;
//             }

//         }

//         boolean LRU_hit = false;
//         int LRU_fault_count = 0;
//         boolean b = false;

//         while (true) {
//             b = false;
//             if (LRU_memory_strings.size() == memory_size || LRU_future_strings.size() == 0) {
//                 break;
//             }
//             int duplicated_idx = 0;
//             for (int j = 0; j < LRU_memory_strings.size(); j++) {
//                 if (LRU_memory_strings.get(j) == LRU_future_strings.get(0)) {
//                     b = true;
//                     duplicated_idx = j;
//                     break;
//                 } else
//                     b = false;
//             }
//             if (b == true) {
//                 LRU_memory_strings.add(0, LRU_memory_strings.remove(duplicated_idx));
//                 LRU_future_strings.remove(0);
//             } else {
//                 LRU_memory_strings.add(0, LRU_future_strings.get(0));
//                 LRU_future_strings.remove(0);
//                 LRU_fault_count++;
//             }
//         }

//         while (true) {
//             if (LRU_future_strings.isEmpty()) {
//                 break;
//             }
//             for (int i = 0; i < LRU_memory_strings.size(); i++) {
//                 if (LRU_memory_strings.get(i) == LRU_future_strings.get(0)) {
//                     LRU_future_strings.remove(0);
//                     LRU_hit = true;
//                     LRU_memory_strings.add(0, LRU_memory_strings.remove(i));
//                     break;
//                 } else {
//                     LRU_hit = false;
//                 }
//             }

//             if (LRU_hit == false) {
//                 LRU_fault_count++;
//                 LRU_memory_strings.remove(LRU_memory_strings.size() - 1);
//                 LRU_memory_strings.add(0, LRU_future_strings.remove(0));
//             }
//         }

//         boolean OP_hit = false;
//         int OP_fault_count = 0;
//         boolean c = false;
//         while (true) {
//             if (OP_memory_strings.size() == memory_size || OP_future_strings.size() == 0) {
//                 break;
//             }
//             for (int j = 0; j < OP_memory_strings.size(); j++) {
//                 if (OP_memory_strings.get(j) == OP_future_strings.get(0)) {
//                     c = true;
//                     break;
//                 } else
//                     c = false;
//             }
//             if (c == true) {
//                 OP_future_strings.remove(0);
//             } else {
//                 OP_memory_strings.add(OP_future_strings.get(0));
//                 OP_future_strings.remove(0);
//                 OP_fault_count++;
//             }
//         }

//            while (true) {
//             if (OP_future_strings.isEmpty()) {
//                 break;
//             }
//             for (int j = 0; j < OP_memory_strings.size(); j++) {
//                 if (OP_memory_strings.get(j) == OP_future_strings.get(0)) {
//                     OP_future_strings.remove(0);
//                     OP_hit = true;
//                     break;
//                 } else {
//                     OP_hit = false;
//                 }
//             }
//             ArrayList<Integer> check_OP = new ArrayList<Integer>();
//             for (int i = 0; i < OP_memory_strings.size(); i++) {
//                 check_OP.add(OP_memory_strings.get(i));
//             }
           
//             if (OP_hit == false) {
//                 OP_fault_count++;
//                 for (int k = 1; k < OP_future_strings.size(); k++) {
//                     if (check_OP.size()==1) {
//                         break;
//                     }
//                     for (int j = 0; j < check_OP.size(); j++) {
//                         if (OP_future_strings.get(k) == check_OP.get(j)) {
//                             check_OP.remove(check_OP.indexOf(check_OP.get(j))); 
//                             break;
//                         }
//                     }
//                 }
               
//                 int idx = OP_memory_strings.indexOf(check_OP.get(0));
//                 OP_memory_strings.remove(idx);
//                 OP_memory_strings.add(OP_memory_strings.size()-1, OP_future_strings.remove(0));
//             }
//         }

//         sr.append("FIFO: " + FIFO_fault_count + "\n"+"LRU: " + LRU_fault_count + "\n"+"OPT: " + OP_fault_count);
//         bw.write(sr.toString().getBytes());
//         bw.close();
//         br.close();
//     }

// }
