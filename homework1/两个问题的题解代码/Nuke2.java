
import java.util.Scanner;
import java.io.*;
class Nuke2{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();//read string input
		int n = name.length();
		String output = name.substring(0,1) + name.substring(2,n);
		System.out.println(output);
	}
}
