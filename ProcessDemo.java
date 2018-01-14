import java.util.ArrayList;
import java.util.Scanner;

public class ProcessDemo 
{
	private static String[] getProcessArgs(String cmd)
	{
		String shell = "/bin/bash", option = "-c";
		if(System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
		{
			shell = "cmd"; option="/C";
		}
		return new String[] {shell, option, cmd};
	}
	
	private static String[] system(String cmd)
	{
	   ArrayList<String> outputList = new ArrayList<>();  
	   try
	   {
	      Process process = (new ProcessBuilder(getProcessArgs(cmd)))
	    		  .redirectErrorStream(true)
	    		  .start();
	      Scanner scanner = new Scanner(process.getInputStream());
	      while(scanner.hasNext()) outputList.add(scanner.nextLine());
	      scanner.close();
	    }
	    catch(Exception e)
	    {
	       System.err.println("commanmd error");
	    }
	    return(outputList.toArray(new String[outputList.size()]));
	}

	
	public static void main(String[] args) 
	{
	
		String results[] = system("cal; ls -al");
		for(String s: results)
		{
			System.out.println(s);
		}
		// System.getProperty("user.dir") returns the absolute file path.
		System.out.println("\nFile path: "+System.getProperty("user.dir"));

	}

}
