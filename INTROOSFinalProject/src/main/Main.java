package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
//		Scanner sc = new Scanner(System.in);
//		String currPath = "C:\\";
//		String input[];
//		System.out.println(currPath + ">");
//		
//		input = sc.nextLine().split(" ");
//		
//		if(input[0].equalsIgnoreCase("cd")){
//			if(!currPath.equalsIgnoreCase("c:\\") || !currPath.equalsIgnoreCase("d:\\"))
//				currPath = currPath + input[1] + "\\";
//		}
//		System.out.println(currPath);
		
		File s = new File("C:\\Users\\Patricia\\Documents\\Dir2\\file1.txt");
		File d = new File("C:\\Users\\Patricia\\Documents\\Dir1\\file1.txt");
		File dir = new File("C:\\Users\\Patricia\\Documents\\Dir1");
		
//		copyFile(s, d);
//		deleteFile(s);
//		moveFile(s, d);
		showContents(dir);
		
		
	}
	
	private static void copyFile( File from, File to ) throws IOException {
	    Files.copy( from.toPath(), to.toPath() );
	}
	
	private static void deleteFile( File from ) throws IOException {
	    Files.delete( from.toPath() );
	}
	
	private static void moveFile( File from, File to ) throws IOException {
	    copyFile(from, to);
	    deleteFile(from);
	}
	
	private static void showContents( File dir ) throws IOException {
		 File[] filesList = dir.listFiles();
	        for(File f : filesList){
	            if(f.isDirectory())
	                showContents(f);
	            if(f.isFile()){
	                System.out.println(f.getName());
	                System.out.println(getSize(f));
	                System.out.println(getDateCreated(f));
	                System.out.println(getDateModified(f));
	                System.out.println(getUser(f));
	            }
	        }
	}
	
	private static String getSize(File f){
		String size = "";
		
		if(f.exists()){
			
			long bytes = f.length();
			long kilobytes = (bytes / 1024);
			long megabytes = (kilobytes / 1024);
			long gigabytes = (megabytes / 1024);
			long terabytes = (gigabytes / 1024);
			long petabytes = (terabytes / 1024);
			long exabytes = (petabytes / 1024);
			long zettabytes = (exabytes / 1024);
			long yottabytes = (zettabytes / 1024);
			
			if(bytes < 1000){
				size = bytes + " bytes";
			}else if(kilobytes < 1000){
				size = kilobytes + " KB";
			}else if(megabytes < 1000){
				size = megabytes + "MB";
			}else if(megabytes < 1000){
				size = gigabytes + "GB";
			}else if(gigabytes < 1000){
				size = terabytes + "TB";
			}	
		}

		return size;
	}
	
	private static String getDateCreated( File f ) throws IOException {
		 Path path = f.toPath();
		 BasicFileAttributes attr;
		 String date = "";
		 
	    attr = Files.readAttributes(path, BasicFileAttributes.class);
	    date = "Date Created: " + attr.creationTime().toString().substring(0, 10);

	    return date;
	}
	
	private static String getDateModified( File f ) throws IOException {
		 Path path = f.toPath();
		 BasicFileAttributes attr;
		 String date = "";
		 
	    attr = Files.readAttributes(path, BasicFileAttributes.class);
	    date = "Date Modified: " + attr.lastModifiedTime().toString().substring(0, 10);

	    return date;
	}
	
	private static String getUser( File f ) throws IOException {
		String name[];
		UserPrincipal owner = Files.getOwner(f.toPath());
		
		name = owner.getName().split("-");
		name[0] = "User: " + name[0];
		
		return name[0];
	}
	
	public boolean errorChecking(String[] line)
	{
		boolean error = false;
		switch(line[0].toUpperCase())
		{
			case "CD":
				if (line.length == 2)
					error = false;
				else error = true;
				break;
			case "DIR":
				if (line.length == 2)
					error = false;
				else error = true;
				break;
			case "COPY":
				if (line.length == 3)
					error = false;
				else error = true;
				break;
			case "MOVE":
				if (line.length == 3)
					error = false;
				else error = true;
				break;
			case "DEL":
				if (line.length == 2)
					error = false;
				else error = true;
				break;
			case "REN":
				if (line.length == 3)
					error = false;
				else error = true;
				break;
		}
		return error;
	}

}
