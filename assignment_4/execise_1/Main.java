/**
 * Main class where the execution will start from
 * @author Hussein Al Osman
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Thread;
import java.lang.ThreadGroup;

public class Main {



	/**
	 * Get all the files from a directory
	 * @param dir File instance representing the directory
	 * @return Array List of all the files in the directory
	 */
	private static ArrayList<File> getFiles (File dir){
		ArrayList<File> filesList = new ArrayList<File>();

		String [] fileNames = dir.list();

		for (String fileName: fileNames){
			File file = new File(dir.getAbsoluteFile()+"/"+fileName);
			if (file.isFile()){
				filesList.add(file);
			}

		}

		return filesList;
	}


	public static void main(String args[]) throws IOException {

		if (args.length > 0){ // Make sure an argument was passed to this program

			// The argument should represent a directory
			File dir = new File (args[0]);

			// Check if dir is in fact a directory
			if (dir.isDirectory()){

				// Read the files in the directory
				ArrayList<File> filesList = getFiles(dir);

				// Get the pattern that will be searched for by prompting the user
				System.out.println("Enter the pattern to look for:");
				Scanner scanIn = new Scanner(System.in);
				String pattern  = scanIn.nextLine();
				scanIn.close();

        ThreadGroup threadGroup = new ThreadGroup("Genome Search");

        for(int i = 0; i < filesList.size(); i++) {
          SearchTask task = new SearchTask(new SearchJob(filesList.get(i)), pattern);
          Thread thread = new Thread(threadGroup, task, "Thread: " + i);
          thread.start();
          System.out.println("Started Thread: " + i);
        }
			}
			else {
				// The argument specified is not a path for a directory
				System.err.println("Incorrect argument: must be a directory");
			}
		}
		else {
			// No argument were passed to the program
			System.err.println("Missing argument: files directory");
		}
    }

}
