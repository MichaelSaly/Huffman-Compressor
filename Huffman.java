package huffman;
import java.util.PriorityQueue;
import java.util.Scanner;
public class Huffman {
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your sentence:");
		String mainString = sc.nextLine();
		sc.close();
		
		int[] ASCIIarray = new int[256];
		String binaryString = new String();
		
		for(int i = 0; i < mainString.length(); i++) {
			if(mainString.charAt(i) >= 64)
				binaryString = binaryString + Integer.toBinaryString((int)mainString.charAt(i)) + " ";
			else
				binaryString = binaryString + "0" + Integer.toBinaryString((int)mainString.charAt(i)) + " ";
			
			ASCIIarray[(int)mainString.charAt(i)]++;
		}
		System.out.println(binaryString);
		System.out.println();
		PriorityQueue <Tree> PQ = new PriorityQueue <Tree>();
		Tree[] trees = new Tree[mainString.length()];
		
		int counter = 0;
		for(int i = 0; i < 256; i++) {
			if(ASCIIarray[i] > 0) {
				trees[counter] = new Tree();
				trees[counter].insert((char)i);
				trees[counter].weighting = ASCIIarray[i];
				PQ.add(trees[counter]);
				System.out.println("'" + (char)i + "' appeared " + ASCIIarray[i] + " times");
				counter++;
			}
		}
		
		Tree choose1 = new Tree();
		Tree choose2 = new Tree();
		Tree finalTree = new Tree();

		while(true) {
			if(PQ.size() == 1) {
				finalTree = PQ.poll();
				break;
			} else {
				choose1 = PQ.poll();
				choose2 = PQ.poll();
				Tree temp = new Tree();
				temp.root = new Node();
				temp.weighting = choose1.weighting + choose2.weighting;
				temp.root.leftChild = choose1.root;
				temp.root.rightChild = choose2.root;
				PQ.add(temp);
			}
		}
		System.out.println();
		System.out.print("Code table: ");
		for(int i = 0; i < 256; i++) {
			if(ASCIIarray[i] > 0) {
				System.out.print(finalTree.getCode((char)i) + "(" + (char)i + ") ");
			}
		}
		System.out.println();
		System.out.println();
		String compressedCode = new String();
		for(int i = 0; i < mainString.length(); i++) {
			compressedCode+=finalTree.getCode((char)mainString.charAt(i)) + " ";
		}
		System.out.println(compressedCode);
		System.out.println();
		System.out.println("Compressed by " + String.format("%.2f", (double)compressedCode.length()/(double)binaryString.length()*100) + "% (" + binaryString.length() + " bits to " + compressedCode.length() + " bits).");
	}
}
