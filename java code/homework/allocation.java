/*
package homework;

import java.io.FileReader;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Process implements Comparable<Process> {
    public int num = 0;
    public int start_time = 0;
    public int run_time = 0;
    public int end_time = 0;
    public int size = 0;

    Process(int num, ArrayList<Integer> arr) {
        this.num = num;
        int i = 0;
        while (!arr.isEmpty()) {
            if (i == 0) {
                this.start_time = arr.remove(0);
                i++;
            } else if (i == 1) {
                this.run_time = arr.remove(0);
                this.end_time = start_time + run_time;
                i++;
            } else {
                this.size = arr.remove(0);
                i++;
            }
        }
    }

    @Override
    public int compareTo(Process p) {
        if (this.start_time > p.start_time)
            return 1;
        else if (this.start_time < p.start_time)
            return -1;
        else {
            if (this.num > p.num)
                return 1;
            else if (this.num < p.num)
                return -1;
            else
                return 0;
        }
    }

}

public class allocation {
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("./java code/1.inp");
        BufferedReader br = new BufferedReader(reader);
        BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream("./java code/1.out"));

        int process_size = Integer.parseInt(br.readLine().trim());
        ArrayList<Integer[]> memory_state = new ArrayList<Integer[]>();
        ArrayList<Process> process = new ArrayList<Process>();
        ArrayList<Integer> start_time = new ArrayList<Integer>();
        ArrayList<Integer> run_time = new ArrayList<Integer>();

        PriorityQueue<Process> sorted_process = new PriorityQueue<Process>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<Process> wait_queue = new ArrayList<Process>();

        String line;
        StringBuilder sr = new StringBuilder();

        for (int i = 0; i < process_size; i++) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            line = br.readLine();
            StringTokenizer tokens = new StringTokenizer(line);
            while (tokens.hasMoreTokens()) {
                arr.add(Integer.parseInt(tokens.nextToken()));
            }
            process.add(new Process(i, arr));
            start_time.add(process.get(i).start_time);
            run_time.add(process.get(i).run_time);
            sorted_process.add(process.get(i));
        }

        memory_state.add(new Integer[] { 0, -1, 1000 });

        boolean done = false;
        int present_time = 0;

        while (true) {
            if (done == true)
                break;
            if (sorted_process.size() != 0 && sorted_process.peek().start_time != present_time) {
                present_time++;
            } else if (sorted_process.size() == 0) {
                int m = 100000;
                for (int i = 0; i < memory_state.size(); i++) {
                    if (memory_state.get(i)[1] != -1) {
                        if (process.get(memory_state.get(i)[1]).end_time < m) {
                            m = process.get(memory_state.get(i)[1]).end_time;
                        }
                    }
                }
                present_time = m;
            } else {
                while (true) {
                    if (done == true)
                        break;
                    if (!sorted_process.isEmpty()) {
                        int next_start_time = sorted_process.peek().start_time;
                        if (next_start_time != present_time) {
                            break;
                        }

                        int count = 0;
                        for (int i = 0; i < memory_state.size(); i++) {
                            if (memory_state.get(i)[1] == -1
                                    && memory_state.get(i)[2] >= sorted_process.peek().size) {
                                int hole_size = memory_state.get(i)[2];
                                memory_state.add(i, new Integer[] { memory_state.get(i)[0], sorted_process.peek().num,
                                        sorted_process.peek().size });

                                if (memory_state.get(i + 1)[1] == -1) {
                                    memory_state.get(i + 1)[0] = memory_state.get(i)[0] + memory_state.get(i)[2];
                                    if (i + 1 == memory_state.size() - 1) {
                                        memory_state.get(i + 1)[2] = 1000 - memory_state.get(i + 1)[0];

                                        if (memory_state.get(i + 1)[2] == 0) {
                                            memory_state.remove(i + 1);
                                        }

                                    } else {
                                        memory_state.get(i + 1)[2] = memory_state.get(i + 1)[2]
                                                - memory_state.get(i)[2];
                                    }

                                    if (sorted_process.peek().num == process_size - 1) {
                                        result.add(memory_state.get(i)[0]);
                                        done = true;
                                    }

                                    sorted_process.remove();
                                    break;
                                } else if (memory_state.get(i)[0]
                                        + memory_state.get(i)[2] != memory_state.get(i + 1)[0]) {

                                    memory_state.add(i + 1,
                                            new Integer[] { memory_state.get(i)[0] + memory_state.get(i)[2], -1,
                                                    hole_size - memory_state.get(i)[2] });
                                    if (sorted_process.peek().num == process_size - 1) {
                                        result.add(memory_state.get(i)[0]);
                                        done = true;
                                    }
                                    sorted_process.remove();
                                    break;
                                } else {
                                    memory_state.get(i + 1)[0] = memory_state.get(i)[0] + memory_state.get(i)[2];
                                    memory_state.get(i + 1)[2] -= memory_state.get(i)[2];
                                    if (sorted_process.peek().num == process_size - 1) {
                                        result.add(memory_state.get(i)[0]);
                                        done = true;
                                    }
                                    sorted_process.remove();
                                    break;
                                }
                            } else {
                                count++;
                            }

                            if (count == memory_state.size()) {
                                wait_queue.add(sorted_process.peek());
                                sorted_process.remove();
                            }
                        }
                    } else {
                        break;
                    }
                }
            }

            boolean re_count = false;
            for (int i = 0; i < memory_state.size(); i++) {
                {
                    int num = memory_state.get(i)[1];
                    if (memory_state.get(i)[1] == -1)
                        continue;
                    else if (present_time == process.get(num).end_time) {
                        if (i == 0) {
                            if (memory_state.size() > 1) {
                                if (memory_state.get(i + 1)[1] == -1) {
                                    memory_state.get(i)[1] = -1;
                                    memory_state.get(i)[2] += memory_state.get(i + 1)[2];
                                    memory_state.remove(i + 1);
                                    i -= 1;
                                } else {
                                    memory_state.get(i)[1] = -1;
                                }
                            } else {
                                memory_state.get(i)[1] = -1;
                            }
                            re_count = true;

                        } else if (i == memory_state.size() - 1) {

                            if (memory_state.get(i - 1)[1] == -1) {
                                memory_state.get(i - 1)[2] += memory_state.get(i)[2];
                                memory_state.remove(i);
                            } else {
                                memory_state.get(i)[1] = -1;
                            }
                            re_count = true;
                        } else {
                            if (memory_state.get(i - 1)[1] == -1 && memory_state.get(i + 1)[1] != -1) {
                                memory_state.get(i - 1)[2] += memory_state.get(i)[2];
                                memory_state.get(i + 1)[0] = memory_state.get(i - 1)[2] + memory_state.get(i - 1)[0];
                                memory_state.remove(i);
                                i -= 1;
                            } else if (memory_state.get(i - 1)[1] != -1 && memory_state.get(i + 1)[1] == -1) {
                                memory_state.get(i)[1] = -1;
                                memory_state.get(i)[2] += memory_state.get(i + 1)[2];
                                memory_state.remove(i + 1);
                                i -= 1;
                            } else if (memory_state.get(i - 1)[1] == -1 && memory_state.get(i + 1)[1] == -1) {
                                memory_state.get(i - 1)[2] += memory_state.get(i)[2] + memory_state.get(i + 1)[2];
                                memory_state.remove(i);
                                memory_state.remove(i);
                                i -= 1;

                            } else {
                                memory_state.get(i)[1] = -1;
                            }
                            re_count = true;
                        }
                    }
                }
            }

            if (re_count) {
                for (int j = 0; j < wait_queue.size(); j++) {
                    int memory_state_size = memory_state.size();
                    for (int i = 0; i < memory_state_size; i++) {
                        if (memory_state.get(i)[1] == -1
                                && memory_state.get(i)[2] >= wait_queue.get(j).size) {
                            int hole_size = memory_state.get(i)[2];
                            memory_state.add(i, new Integer[] { memory_state.get(i)[0], wait_queue.get(j).num,
                                    wait_queue.get(j).size });

                            if (memory_state.get(i + 1)[1] == -1) {
                                memory_state.get(i + 1)[0] = memory_state.get(i)[0] + memory_state.get(i)[2];
                                if (i + 1 == memory_state_size - 1) {
                                    memory_state.get(i + 1)[2] = 1000 - memory_state.get(i + 1)[0];
                                } else {
                                    memory_state.get(i + 1)[2] = memory_state.get(i + 1)[2] - memory_state.get(i)[2];
                                }

                                if (wait_queue.get(j).num == process_size - 1) {
                                    result.add(memory_state.get(i)[0]);
                                    done = true;
                                }
                                wait_queue.get(j).start_time = present_time;
                                wait_queue.get(j).end_time = wait_queue.get(j).start_time + wait_queue.get(j).run_time;
                                wait_queue.remove(j);
                                j -= 1;
                                break;
                            } else if (memory_state.get(i)[0] + memory_state.get(i)[2] != memory_state.get(i + 1)[0]) {

                                memory_state.add(i + 1, new Integer[] { memory_state.get(i)[2], -1,
                                        hole_size - memory_state.get(i + 1)[0] });
                                if (wait_queue.get(j).num == process_size - 1) {
                                    result.add(memory_state.get(i)[0]);
                                    done = true;
                                }
                                wait_queue.get(j).start_time = present_time;
                                wait_queue.get(j).end_time = wait_queue.get(j).start_time + wait_queue.get(j).run_time;
                                wait_queue.remove(j);
                                j -= 1;
                                break;
                            } else {
                                memory_state.get(i + 1)[0] = memory_state.get(i)[0] + memory_state.get(i)[2];
                                memory_state.get(i + 1)[2] -= memory_state.get(i)[2];
                                if (wait_queue.get(j).num == process_size - 1) {
                                    result.add(memory_state.get(i)[0]);
                                    done = true;
                                }
                                wait_queue.get(j).start_time = present_time;
                                wait_queue.get(j).end_time = wait_queue.get(j).start_time + wait_queue.get(j).run_time;
                                wait_queue.remove(j);
                                j -= 1;
                                break;
                            }
                        }
                    }
                }
            }

        }

        sorted_process.clear();
        for (int i = 0; i < process_size; i++) {
            process.get(i).start_time = start_time.get(i);
            process.get(i).run_time = run_time.get(i);
            process.get(i).end_time = start_time.get(i) + run_time.get(i);
            sorted_process.add(process.get(i));
        }

        memory_state.clear();
        wait_queue.clear();
        memory_state.add(new Integer[] { 0, -1, 1000 });

        boolean done1 = false;
        int present_time1 = 0;

        int f = 0;
        while (true) {

            if (done1 == true)
                break;
            if (sorted_process.size() != 0 && sorted_process.peek().start_time != present_time1) {
                present_time1++;
            } else if (sorted_process.size() == 0) {
                int m = 100000;
                for (int i = 0; i < memory_state.size(); i++) {
                    if (memory_state.get(i)[1] != -1) {
                        if (process.get(memory_state.get(i)[1]).end_time < m) {
                            m = process.get(memory_state.get(i)[1]).end_time;
                        }
                    }
                }
                present_time1 = m;
            } else {
                while (true) {
                    if (done1 == true)
                        break;
                    if (!sorted_process.isEmpty()) {
                        int next_start_time = sorted_process.peek().start_time;
                        if (next_start_time != present_time1) {
                            break;
                        }
                        int count = 0;
                        int min_hole = 1001;
                        int min_hole_idx = 0;

                        for (int s = 0; s < memory_state.size(); s++) {
                            if (memory_state.get(s)[2] == 0) {
                                memory_state.remove(s);
                            }
                        }
                        for (int s = 0; s < memory_state.size(); s++) {
                            if (memory_state.get(s)[1] == -1)
                                if (memory_state.get(s)[2] >= sorted_process.peek().size
                                        && memory_state.get(s)[2] != 0) {
                                    if (min_hole > memory_state.get(s)[2]) {
                                        min_hole = memory_state.get(s)[2];
                                        min_hole_idx = s;
                                    }
                                    count++;
                                }
                        }
                        if (count >= 1) {
                            int hole_size = memory_state.get(min_hole_idx)[2];
                            memory_state.add(min_hole_idx, new Integer[] { memory_state.get(min_hole_idx)[0],
                                    sorted_process.peek().num, sorted_process.peek().size });

                            if (memory_state.get(min_hole_idx + 1)[1] == -1) {
                                memory_state.get(min_hole_idx + 1)[0] = memory_state.get(min_hole_idx)[0]
                                        + memory_state.get(min_hole_idx)[2];
                                if (min_hole_idx + 1 == memory_state.size() - 1) {
                                    memory_state.get(min_hole_idx + 1)[2] = 1000
                                            - memory_state.get(min_hole_idx + 1)[0];

                                    if (memory_state.get(min_hole_idx + 1)[2] == 0) {
                                        memory_state.remove(min_hole_idx + 1);
                                    }
                                } else {
                                    memory_state.get(min_hole_idx + 1)[2] = memory_state.get(min_hole_idx + 1)[2]
                                            - memory_state.get(min_hole_idx)[2];
                                    if (memory_state.get(min_hole_idx + 1)[2] == 0) {
                                        memory_state.remove(min_hole_idx + 1);
                                    }
                                }

                                if (sorted_process.peek().num == process_size - 1) {
                                    result.add(memory_state.get(min_hole_idx)[0]);
                                    done1 = true;
                                }
                                sorted_process.remove();
                                break;
                            } else if (memory_state.get(min_hole_idx)[0]
                                    + memory_state.get(min_hole_idx)[2] != memory_state.get(min_hole_idx + 1)[0]) {
                                memory_state.add(min_hole_idx + 1,
                                        new Integer[] {
                                                memory_state.get(min_hole_idx)[0]
                                                        + memory_state.get(min_hole_idx)[2],
                                                -1, hole_size - memory_state.get(min_hole_idx)[2] });
                                if (sorted_process.peek().num == process_size - 1) {
                                    result.add(memory_state.get(min_hole_idx)[0]);
                                    done1 = true;
                                }
                                sorted_process.remove();
                                break;
                            } else {
                                memory_state.get(min_hole_idx + 1)[0] = memory_state.get(min_hole_idx)[0]
                                        + memory_state.get(min_hole_idx)[2];
                                memory_state.get(min_hole_idx + 1)[2] -= memory_state.get(min_hole_idx)[2];
                                if (sorted_process.peek().num == process_size - 1) {
                                    result.add(memory_state.get(min_hole_idx)[0]);
                                    done1 = true;
                                }
                                sorted_process.remove();
                                break;
                            }
                        } else {
                            wait_queue.add(sorted_process.peek());
                            sorted_process.remove();
                        }
                    } else {
                        break;
                    }
                }
            }

            boolean re_count1 = false;
            for (int i = 0; i < memory_state.size(); i++) {
                {
                    int num = memory_state.get(i)[1];
                    if (memory_state.get(i)[1] == -1)
                        continue;
                    else if (present_time1 == process.get(num).end_time) {
                        if (i == 0) {
                            if (memory_state.size() > 1) {
                                if (memory_state.get(i + 1)[1] == -1) {
                                    memory_state.get(i)[1] = -1;
                                    memory_state.get(i)[2] += memory_state.get(i + 1)[2];
                                    memory_state.remove(i + 1);
                                    i -= 1;
                                } else {
                                    memory_state.get(i)[1] = -1;
                                }
                            } else {
                                memory_state.get(i)[1] = -1;
                            }
                            re_count1 = true;

                        } else if (i == memory_state.size() - 1) {

                            if (memory_state.get(i - 1)[1] == -1) {
                                memory_state.get(i - 1)[2] += memory_state.get(i)[2];
                                memory_state.remove(i);
                            } else {
                                memory_state.get(i)[1] = -1;
                            }
                            re_count1 = true;
                        } else {
                            if (memory_state.get(i - 1)[1] == -1 && memory_state.get(i + 1)[1] != -1) {
                                memory_state.get(i - 1)[2] += memory_state.get(i)[2];
                                memory_state.get(i + 1)[0] = memory_state.get(i - 1)[2] + memory_state.get(i - 1)[0];
                                memory_state.remove(i);
                                i -= 1;
                            } else if (memory_state.get(i - 1)[1] != -1 && memory_state.get(i + 1)[1] == -1) {
                                memory_state.get(i)[1] = -1;
                                memory_state.get(i)[2] += memory_state.get(i + 1)[2];
                                memory_state.remove(i + 1);
                                i -= 1;
                            } else if (memory_state.get(i - 1)[1] == -1 && memory_state.get(i + 1)[1] == -1) {
                                memory_state.get(i - 1)[2] += memory_state.get(i)[2] + memory_state.get(i + 1)[2];
                                memory_state.remove(i);
                                memory_state.remove(i);
                                i -= 1;

                            } else {
                                memory_state.get(i)[1] = -1;
                            }
                            re_count1 = true;
                        }
                    }
                }
            }

            if (re_count1) {
                for (int j = 0; j < wait_queue.size(); j++) {
                    int count = 0;
                    int min_hole = 1001;
                    int min_hole_idx = 0;
                    for (int s = 0; s < memory_state.size(); s++) {
                        if (memory_state.get(s)[2] == 0) {
                            memory_state.remove(s);
                        }
                    }

                    for (int s = 0; s < memory_state.size(); s++) {
                        if (memory_state.get(s)[1] == -1) {
                            if (memory_state.get(s)[2] >= wait_queue.get(j).size && memory_state.get(s)[2] != 0) {
                                if (min_hole > memory_state.get(s)[2]) {
                                    min_hole = memory_state.get(s)[2];
                                    min_hole_idx = s;
                                }
                                count++;
                            }
                        }
                    }
                    if (count >= 1) {
                        if (memory_state.get(min_hole_idx)[2] >= wait_queue.get(j).size) {
                            int hole_size = memory_state.get(min_hole_idx)[2];
                            memory_state.add(min_hole_idx,
                                    new Integer[] { memory_state.get(min_hole_idx)[0], wait_queue.get(j).num,
                                            wait_queue.get(j).size });

                            if (memory_state.get(min_hole_idx + 1)[1] == -1) {
                                memory_state.get(min_hole_idx + 1)[0] = memory_state.get(min_hole_idx)[0]
                                        + memory_state.get(min_hole_idx)[2];

                                if (min_hole_idx + 1 == memory_state.size() - 1) {
                                    memory_state.get(min_hole_idx + 1)[2] = 1000
                                            - memory_state.get(min_hole_idx + 1)[0];
                                    if (memory_state.get(min_hole_idx + 1)[2] == 0) {
                                        memory_state.remove(min_hole_idx + 1);
                                    }
                                } else {
                                    memory_state.get(min_hole_idx + 1)[2] = memory_state.get(min_hole_idx + 1)[2]
                                            - memory_state.get(min_hole_idx)[2];
                                    if (memory_state.get(min_hole_idx + 1)[2] == 0) {
                                        memory_state.remove(min_hole_idx + 1);
                                    }
                                }

                                if (wait_queue.get(j).num == process_size - 1) {
                                    result.add(memory_state.get(min_hole_idx)[0]);
                                    done1 = true;
                                }
                                wait_queue.get(j).start_time = present_time1;
                                wait_queue.get(j).end_time = wait_queue.get(j).start_time + wait_queue.get(j).run_time;
                                wait_queue.remove(j);
                                j -= 1;

                            } else if (memory_state.get(min_hole_idx)[0]
                                    + memory_state.get(min_hole_idx)[2] != memory_state.get(min_hole_idx + 1)[0]) {

                                memory_state.add(min_hole_idx + 1, new Integer[] { memory_state.get(min_hole_idx)[2],
                                        -1, hole_size - memory_state.get(min_hole_idx + 1)[0] });
                                if (wait_queue.get(j).num == process_size - 1) {
                                    result.add(memory_state.get(min_hole_idx)[0]);
                                    done1 = true;
                                }
                                wait_queue.get(j).start_time = present_time1;
                                wait_queue.get(j).end_time = wait_queue.get(j).start_time + wait_queue.get(j).run_time;
                                wait_queue.remove(j);
                                j -= 1;

                            } else {
                                memory_state.get(min_hole_idx + 1)[0] = memory_state.get(min_hole_idx)[0]
                                        + memory_state.get(min_hole_idx)[2];
                                memory_state.get(min_hole_idx + 1)[2] -= memory_state.get(min_hole_idx)[2];
                                if (wait_queue.get(j).num == process_size - 1) {
                                    result.add(memory_state.get(min_hole_idx)[0]);
                                    done1 = true;
                                }
                                wait_queue.get(j).start_time = present_time1;
                                wait_queue.get(j).end_time = wait_queue.get(j).start_time + wait_queue.get(j).run_time;
                                wait_queue.remove(j);
                                j -= 1;

                            }
                        }
                    }
                }
            }

        }

        sorted_process.clear();
        for (int i = 0; i < process_size; i++) {
            process.get(i).start_time = start_time.get(i);
            process.get(i).run_time = run_time.get(i);
            process.get(i).end_time = start_time.get(i) + run_time.get(i);
            sorted_process.add(process.get(i));
        }

        memory_state.clear();
        wait_queue.clear();
        memory_state.add(new Integer[] { 0, -1, 1000 });

        boolean done2 = false;
        int present_time2 = 0;
        while (true) {
            if (done2 == true)
                break;
            if (sorted_process.size() != 0 && sorted_process.peek().start_time != present_time2) {
                present_time2++;
            } else if (sorted_process.size() == 0) {
                int m = 100000;
                for (int i = 0; i < memory_state.size(); i++) {
                    if (memory_state.get(i)[1] != -1) {
                        if (process.get(memory_state.get(i)[1]).end_time < m) {
                            m = process.get(memory_state.get(i)[1]).end_time;
                        }
                    }
                }
                present_time2 = m;
            } else {
                while (true) {
                    if (done2 == true)
                        break;

                    if (!sorted_process.isEmpty()) {
                        int next_start_time = sorted_process.peek().start_time;
                        if (next_start_time != present_time2) {
                            break;
                        }
                        int memory_state_size = memory_state.size();
                        int count2 = 0;
                        int max_hole = 0;
                        int max_hole_idx = 0;

                        for (int s = 0; s < memory_state.size(); s++) {
                            if (memory_state.get(s)[2] == 0) {
                                memory_state.remove(s);
                            }
                        }
                        for (int s = 0; s < memory_state.size(); s++) {
                            if (memory_state.get(s)[1] == -1)
                                if (memory_state.get(s)[2] >= sorted_process.peek().size
                                        && memory_state.get(s)[2] != 0) {
                                    if (max_hole < memory_state.get(s)[2]) {
                                        max_hole = memory_state.get(s)[2];
                                        max_hole_idx = s;
                                    }
                                    count2++;
                                }
                        }
                        if (count2 >= 1) {
                            if (memory_state.get(max_hole_idx)[1] == -1
                                    && memory_state.get(max_hole_idx)[2] >= sorted_process.peek().size) {
                                int hole_size = memory_state.get(max_hole_idx)[2];
                                memory_state.add(max_hole_idx,
                                        new Integer[] { memory_state.get(max_hole_idx)[0], sorted_process.peek().num,
                                                sorted_process.peek().size });
                                if (memory_state.get(max_hole_idx + 1)[1] == -1) {
                                    memory_state.get(max_hole_idx + 1)[0] = memory_state.get(max_hole_idx)[0]
                                            + memory_state.get(max_hole_idx)[2];

                                    if (max_hole_idx + 1 == memory_state.size() - 1) {
                                        memory_state.get(max_hole_idx + 1)[2] = 1000
                                                - memory_state.get(max_hole_idx + 1)[0];

                                        if (memory_state.get(max_hole_idx + 1)[2] == 0) {
                                            memory_state.remove(max_hole_idx + 1);
                                        }
                                    } else {
                                        memory_state.get(max_hole_idx + 1)[2] = memory_state.get(max_hole_idx + 1)[2]
                                                - memory_state.get(max_hole_idx)[2];
                                    }

                                    if (sorted_process.peek().num == process_size - 1) {
                                        result.add(memory_state.get(max_hole_idx)[0]);
                                        done2 = true;
                                    }
                                    sorted_process.remove();
                                    break;
                                } else if (memory_state.get(max_hole_idx)[0]
                                        + memory_state.get(max_hole_idx)[2] != memory_state.get(max_hole_idx + 1)[0]) {

                                    memory_state.add(max_hole_idx + 1,
                                            new Integer[] { memory_state.get(max_hole_idx)[2], -1,
                                                    hole_size - memory_state.get(max_hole_idx + 1)[0] });
                                    if (sorted_process.peek().num == process_size - 1) {
                                        result.add(memory_state.get(max_hole_idx)[0]);
                                        done2 = true;
                                    }
                                    sorted_process.remove();
                                    break;
                                } else {
                                    memory_state.get(max_hole_idx + 1)[0] = memory_state.get(max_hole_idx)[0]
                                            + memory_state.get(max_hole_idx)[2];
                                    memory_state.get(max_hole_idx + 1)[2] -= memory_state.get(max_hole_idx)[2];
                                    if (sorted_process.peek().num == process_size - 1) {
                                        result.add(memory_state.get(max_hole_idx)[0]);
                                        done2 = true;
                                    }
                                    sorted_process.remove();
                                    break;
                                }
                            }
                        } else {
                            wait_queue.add(sorted_process.peek());
                            sorted_process.remove();
                        }
                    } else {
                        break;
                    }
                }
            }

            boolean re_count2 = false;
            for (int i = 0; i < memory_state.size(); i++) {
                {
                    int num = memory_state.get(i)[1];
                    if (memory_state.get(i)[1] == -1)
                        continue;
                    else if (present_time2 == process.get(num).end_time) {
                        if (i == 0) {
                            if (memory_state.size() > 1) {
                                if (memory_state.get(i + 1)[1] == -1) {
                                    memory_state.get(i)[1] = -1;
                                    memory_state.get(i)[2] += memory_state.get(i + 1)[2];
                                    memory_state.remove(i + 1);
                                    i -= 1;
                                } else {
                                    memory_state.get(i)[1] = -1;
                                }
                            } else {
                                memory_state.get(i)[1] = -1;
                            }
                            re_count2 = true;
                        } else if (i == memory_state.size() - 1) {

                            if (memory_state.get(i - 1)[1] == -1) {
                                memory_state.get(i - 1)[2] += memory_state.get(i)[2];
                                memory_state.remove(i);

                            } else {
                                memory_state.get(i)[1] = -1;
                            }
                            re_count2 = true;
                        } else {
                            if (memory_state.get(i - 1)[1] == -1 && memory_state.get(i + 1)[1] != -1) {
                                memory_state.get(i - 1)[2] += memory_state.get(i)[2];
                                memory_state.get(i + 1)[0] = memory_state.get(i - 1)[2] + memory_state.get(i - 1)[0];
                                memory_state.remove(i);
                                i -= 1;
                            } else if (memory_state.get(i - 1)[1] != -1 && memory_state.get(i + 1)[1] == -1) {
                                memory_state.get(i)[1] = -1;
                                memory_state.get(i)[2] += memory_state.get(i + 1)[2];
                                memory_state.remove(i + 1);
                                i -= 1;
                            } else if (memory_state.get(i - 1)[1] == -1 && memory_state.get(i + 1)[1] == -1) {
                                memory_state.get(i - 1)[2] += memory_state.get(i)[2] + memory_state.get(i + 1)[2];
                                memory_state.remove(i);
                                memory_state.remove(i);
                                i -= 1;
                            } else {
                                memory_state.get(i)[1] = -1;
                            }
                            re_count2 = true;
                        }
                    }
                }
            }

            if (re_count2) {
                for (int j = 0; j < wait_queue.size(); j++) {
                    int count2 = 0;
                    int max_hole = 0;
                    int max_hole_idx = 0;
                    for (int s = 0; s < memory_state.size(); s++) {
                        if (memory_state.get(s)[2] == 0) {
                            memory_state.remove(s);
                        }
                    }

                    for (int s = 0; s < memory_state.size(); s++) {
                        if (memory_state.get(s)[1] == -1) {
                            if (memory_state.get(s)[2] >= wait_queue.get(j).size && memory_state.get(s)[2] != 0) {
                                if (max_hole < memory_state.get(s)[2]) {
                                    max_hole = memory_state.get(s)[2];
                                    max_hole_idx = s;
                                }
                                count2++;
                            }
                        }
                    }
                    if (count2 >= 1) {
                        if (memory_state.get(max_hole_idx)[2] >= wait_queue.get(j).size) {
                            int hole_size = memory_state.get(max_hole_idx)[2];
                            memory_state.add(max_hole_idx,
                                    new Integer[] { memory_state.get(max_hole_idx)[0], wait_queue.get(j).num,
                                            wait_queue.get(j).size });

                            if (memory_state.get(max_hole_idx + 1)[1] == -1) {
                                memory_state.get(max_hole_idx + 1)[0] = memory_state.get(max_hole_idx)[0]
                                        + memory_state.get(max_hole_idx)[2];
                                if (max_hole_idx + 1 == memory_state.size() - 1) {
                                    memory_state.get(max_hole_idx + 1)[2] = 1000
                                            - memory_state.get(max_hole_idx + 1)[0];
                                    if (memory_state.get(max_hole_idx + 1)[2] == 0) {
                                        memory_state.remove(max_hole_idx + 1);
                                    }

                                } else {
                                    memory_state.get(max_hole_idx + 1)[2] = memory_state.get(max_hole_idx + 1)[2]
                                            - memory_state.get(max_hole_idx)[2];
                                    if (memory_state.get(max_hole_idx + 1)[2] == 0) {
                                        memory_state.remove(max_hole_idx + 1);
                                    }
                                }

                                if (wait_queue.get(j).num == process_size - 1) {
                                    result.add(memory_state.get(max_hole_idx)[0]);
                                    done2 = true;
                                }
                                wait_queue.get(j).start_time = present_time2;
                                wait_queue.get(j).end_time = wait_queue.get(j).start_time + wait_queue.get(j).run_time;
                                wait_queue.remove(j);
                                j -= 1;

                            } else if (memory_state.get(max_hole_idx)[0]
                                    + memory_state.get(max_hole_idx)[2] != memory_state.get(max_hole_idx + 1)[0]) {

                                memory_state.add(max_hole_idx + 1, new Integer[] { memory_state.get(max_hole_idx)[2],
                                        -1, hole_size - memory_state.get(max_hole_idx + 1)[0] });
                                if (wait_queue.get(j).num == process_size - 1) {
                                    result.add(memory_state.get(max_hole_idx)[0]);
                                    done2 = true;
                                }
                                wait_queue.get(j).start_time = present_time2;
                                wait_queue.get(j).end_time = wait_queue.get(j).start_time + wait_queue.get(j).run_time;
                                wait_queue.remove(j);
                                j -= 1;

                            } else {
                                memory_state.get(max_hole_idx + 1)[0] = memory_state.get(max_hole_idx)[0]
                                        + memory_state.get(max_hole_idx)[2];
                                memory_state.get(max_hole_idx + 1)[2] -= memory_state.get(max_hole_idx)[2];
                                if (wait_queue.get(j).num == process_size - 1) {
                                    result.add(memory_state.get(max_hole_idx)[0]);
                                    done2 = true;
                                }
                                wait_queue.get(j).start_time = present_time2;
                                wait_queue.get(j).end_time = wait_queue.get(j).start_time + wait_queue.get(j).run_time;
                                wait_queue.remove(j);
                                j -= 1;

                            }
                        }
                    }
                }
            }

        }

        for (int i = 0; i < result.size(); i++) {
            sr.append(result.get(i) + "\n");
        }
        bs.write(sr.toString().getBytes());
        bs.close();
        br.close();

    }

}
*/