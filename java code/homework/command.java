// package homework;

// import java.io.BufferedOutputStream;
// import java.io.BufferedReader;
// import java.io.FileOutputStream;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Arrays;

// public class command {

// 	public static void main(String[] args) throws IOException {
// 		FileReader reader = new FileReader("./src/1.inp");
// 		BufferedReader br = new BufferedReader(reader);
// 		BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream("./src/command.out"));

// 		int n = Integer.parseInt(br.readLine());
// 		String[] UnixA = { "ls", "mkdir", "rmdir", "rm", "cp", "mv", "clear", "pwd", "cat", "man", "date", "find",
// 				"grep", "more", "diff", "ed", "sort", "lsattr", "pushd", "popd", "ps", "kill", "halt", "ifconfig",
// 				"fsck", "free", "debugfs", "lpr" };
// 		ArrayList<String> Unix = new ArrayList<>(Arrays.asList(UnixA));
// 		String[] DOSA = { "dir", "md", "rd", "del", "copy", "rename", "cls", "cd", "type", "help", "time", "find",
// 				"findstr", "more", "comp", "edlin", "sort", "attrib", "pushd", "popd", "taskmgr", "tskill", "shutdown",
// 				"ipconfig", "chkdsk", "mem", "scandisk", "print" };
// 		ArrayList<String> DOS = new ArrayList<>(Arrays.asList(DOSA));
// 		String[] input = new String[n];
// 		String line;
// 		int i = 0;
// 		while ((line = br.readLine()) != null) {
// 			input[i] = line;
// 			i += 1;
// 		}
// 		StringBuilder s = new StringBuilder();
// 		for (int j = 0; j < input.length; j++) {
// 			if (Unix.contains(input[j])) {
// 				int idx = Unix.indexOf(input[j]);
// 				s.append(Unix.get(idx) + " -> " + DOS.get(idx) + "\n");
// 			} else if (DOS.contains(input[j])) {
// 				int idx = DOS.indexOf(input[j]);
// 				s.append(DOS.get(idx) + " -> " + Unix.get(idx) + "\n");
// 			}

// 		}
// 		bs.write(s.toString().getBytes());
// 		bs.close();
// 		br.close();
// 	}

// }
