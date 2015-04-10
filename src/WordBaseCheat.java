import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;


public class WordBaseCheat {
	static String BOARD_LOCATION;
	static int _across=10;
	static int _down=13;
	static char[][] board;
	static int _startRow;
	static int _startCol;
	static ArrayList<String> wordList = new ArrayList<String>();
	static ArrayList<String> dict = new ArrayList<String>();
	static Scanner in;
	
	public static void main(String[] args) throws IOException {
		in = new Scanner(System.in);
		while(true){
			init();
			printBoard();
			play();
		}
	}

	private static void init() throws IOException {
		System.out.println("welcome to wordbase cheatcode!");
		
		board = new char[_down][_across];
		System.out.println("key in board location.");
		BOARD_LOCATION = in.next();
		BOARD_LOCATION = BOARD_LOCATION.concat(".txt");
		loadBoard();
		BufferedReader reader = new BufferedReader(new FileReader("ospd.txt"));
		String line=reader.readLine();
		while (line!=null){
			dict.add(line);
			line = reader.readLine();
		}
	}
	
	private static void loadBoard() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(BOARD_LOCATION));
		String line = br.readLine();
		int i=0;
		while(line!=null){
			int j=0;
			StringTokenizer st = new StringTokenizer(line," ");
			while(st.hasMoreTokens()){
				board[i][j]=st.nextToken().trim().charAt(0);
				j++;
			}
			i++;
			line = br.readLine();
		}
		
	}

	private static void play() {
		System.out.println("Key in the position of the square you want to start your word with:");
		System.out.println("Key in row position.");
		_startRow = in.nextInt();
		System.out.println("Key in col position.");
		_startCol = in.nextInt();
		System.out.println("letter is: "+board[_startRow][_startCol]);
		
		findBestWord(_startRow,_startCol);
		printFilteredWordList();

		
	}

	private static void printFilteredWordList() {
		for(String word:wordList){
			if(dictContainsExact(word)){
				System.out.println(word);
			}
		}
	}


	private static void findBestWord(int row, int col) {
		Letter root = new Letter(board[row][col],row,col);
		addLayer(root,10,1);
		printWords(root);
		
		
	}
	private static void addLayer(Letter parent, int maxLayer,int currLayer) {
		if(currLayer==maxLayer){
			return;
		}else{
			addChildren(parent);
			String parentStr = parentString(parent);
			pruneChildren(parent,parentStr);
			for(Letter child: parent._children){
				addLayer(child,maxLayer,currLayer+1);
			}
				
		}
		
		
	}

	private static void printWords(Letter letter) {
		
		if(!letter.hasChildren()){
			wordList.add(parentString(letter));
		}else{
			
			for(Letter child: letter._children){
				printWords(child);
			}
		}
		
		
	}

	private static void pruneChildren(Letter parent,String str) {
		StringBuilder sb;
		ArrayList<Letter> childToDelete = new ArrayList<Letter>();
		for(Letter child: parent._children){
			sb= new StringBuilder();
			sb.append(str);
			sb.append(child._val);
			if(!dictContains(sb.toString())){
				childToDelete.add(child);
			}
		}
		for(Letter child: childToDelete){
			parent.deleteChild(child);
		}
	}

	private static boolean dictContains(String string) {
		for(int i=0;i<dict.size();i++){
			if(dict.get(i).startsWith(string)){
				return true;
			}
		}
		return false;
	}
	private static boolean dictContainsExact(String string) {
		for(int i=0;i<dict.size();i++){
			if(dict.get(i).equals(string)){
				return true;
			}
		}
		return false;
	}

	public static String parentString(Letter letter){
		StringBuilder sb = new StringBuilder();
		sb.append(letter._val);
		while(!letter.isRoot()){
			letter=letter.getParent();
			sb.append(letter._val);
		}
		sb.reverse();
		return sb.toString();
	}
		
		
	public static void addChildren(Letter letter){
		int row = letter.getRowIndex();
		int col = letter.getColIndex();
		for(int i=-1;i<=1;i++){
			for(int j=-1;j<=1;j++){
				if (row+i<0||col+j<0||row+i>_down-1||col+j>_across-1){
					continue;
				}else if(i==0 && j ==0){
					continue;
				}else{
					Letter child = new Letter(board[row+i][col+j],row+i,col+j);
					if(!child.isParentOf(letter)){
						letter.addChild(child);
					}
				}
			}
		}
	}

	public static void addLine(String line, int index){
		System.out.println(line);
		System.out.printf("board is %d down, and %s across\n",_down, _across);
		for(int i =0;i<_across;i++){
			board[index][i]=line.charAt(i);
		}
	}
	
	public static void printBoard(){
		for(int i=0;i<_down;i++){
			for(int j=0;j<_across;j++){
				System.out.print(board[i][j]+" ");
			}
			System.out.println("\n");
		}
	}
}
