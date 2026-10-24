# CPU Scheduler

Java console simulations of CPU scheduling algorithms, with text timelines, per-process waiting/turnaround values, and averages.

## Included algorithms

The default [`Main`](src/Main.java) entry point asks for a round-robin quantum, then runs shortest remaining time first (**SRTF**), non-preemptive priority, and round robin (**RR**). Additional source files contain FCFS, SJF, and preemptive-priority variants.

## Compile and run

Use JDK 11 or later (`String.strip()` is used in source). From the repository root:

```bash
javac -d out src/*.java
java -cp out Main
```

Enter a **positive** integer quantum. Keep the working directory at the repository root, because input filenames are hardcoded.

## Default input files

- `testdata1 for SRTF.txt`: comma-separated process ID, arrival time, and burst time, ending with the source's expected comment/sentinel line.
- `testdata1 for priortiy.txt`: alternating process ID lines and `burst,priority[,arrival]` lines. The filename's spelling matches the source.
- `testdata21 for RR.txt`: alternating process ID and burst-time lines; the loader assigns successive arrival times starting at zero.

Individual entry points such as `java -cp out SRTF` can run a single simulation. `RR`'s standalone entry uses quantum `1`.

## Limitations

This is a coursework simulator, not an operating-system scheduler. Input formats/values are not robustly validated. Some reported timing values differ from textbook definitions for nonzero arrivals; for example, SRTF stores completion time as turnaround time. RR also does not fully model arrival-aware ready-queue behavior. Use the source and known examples to check results rather than assuming every printed metric is correct.

No comprehensive algorithm validation or runtime testing was performed for this documentation refresh.
