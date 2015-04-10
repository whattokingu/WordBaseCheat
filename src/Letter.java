import java.util.ArrayList;


public class Letter {
	ArrayList<Letter> _children;
	Letter _parent=null;
	char _val;
	int _rowIndex;
	int _colIndex;
	int _ID;
	
	public Letter(char val,int row,int col) {
		_children = new ArrayList<Letter>();
		_val = val;
		_rowIndex=row;
		_colIndex=col;
		_ID=row*1000+col;
	}
	
	public int getID(){
		return _ID;
	}
	
	public int getRowIndex(){
		return _rowIndex;
	}
	public int getColIndex(){
		return _colIndex;
	}
	public void setParent(Letter letter){
		_parent = letter;
	}
	
	public Letter getParent() {
		return _parent;
	}
	public void deleteChild(Letter letter){
		_children.remove(letter);
	}
	public int numChild(){
		return _children.size();
	}
	
	public boolean isRoot(){
		if (_parent==null){
			return true;
		}
		return false;
	}
	
	public void addChild(Letter letter){
		_children.add(letter);
		letter.setParent(this);
	}
	public boolean hasChildren(){
		if(_children.size()!=0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isParentOf(Letter letter){
		if(letter.getParent()==null){
			return false;
		}else if(letter.getParent().getID()==this.getID()){
			return true;
		}else{
			return this.isParentOf(letter.getParent());
		}
		
	}

	public void print() {
		System.out.printf("letter: %c, ID=%d\n",_val,_ID);
		
	}
	
	
}
