package app;

import java.util.List;

public interface FileHandler {
	
	void write(String path, List<Time> timeList);
	
	List<Time> read(String path);

}
