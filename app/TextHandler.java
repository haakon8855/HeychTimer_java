package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextHandler implements FileHandler {
	
	private String sep = ";"; // Separator string

	@Override
	public void write(String path, List<Time> timeList) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(path);
			for (int i = 0; i < timeList.size()-1; i++) {
				Time time = timeList.get(i);
				writer.println(i+1 + sep + 
							   time.getTime() + sep + 
							   time.getTimeString() + sep +
							   time.getScramble());
			}
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("The system cannot find the specified file: " + path);
		}
	}

	@Override
	public List<Time> read(String path) {
		Scanner scanner;
		List<Time> timeList = new ArrayList<>();
		
		try {
			scanner = new Scanner(new File(path));
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				String[] parts = s.split(sep);
				
				long time = Long.parseLong(parts[1]);
				String scramble = parts[3];
						
				timeList.add(new Time(time, new Scramble(scramble)));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("The system cannot find the specified file, "
					+ "creating the default /" + path);
			write(path, new ArrayList<>());
		}
		
		return timeList;
	}

}















