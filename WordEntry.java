public class WordEntry{
	static int timesOfOcuurance=0;
	String str;
	MyLinkedList<Position> positions1;	//this should be AVL tree for assignment5
	avltree positions;



	public WordEntry(String word){
		str=word;
		positions1=new MyLinkedList<Position>();		//*ass4 replace
		positions=new avltree();
	}
	public WordEntry(){
//*		positions=new MyLinkedList<Position>();
		positions=new avltree();
	}

	public boolean equals(String str1){
		return (str.equals(str1));
	}

	public boolean equals(WordEntry str1){
		return (str.equals(str1.str));
	}

	public void addPosition(Position position){
		//if(positions1==null){positions1==new MyLinkedList<Position>();}
		//System.out.println("word being added: "+str);
		positions1.insert(position);
		positions.treeInsert(position.p,position.wordIndex);
	/*	if(str.equals("stack")){
				System.out.println("addPosition: positions for word stack");
				positions.inorder(positions.root);
		}
	*/
		return;

	}

	public void addPositions(MyLinkedList<Position> positioner){
		Node<Position> trav=positioner.head;
		while(trav!=null){
		//	System.out.println("word being added via addPositions: "+str);
			positions1.insert(trav.data);//*assignment 4 code
			positions.treeInsert(trav.data.p,trav.data.wordIndex);
	/*		if(str.equals("stack")){
				System.out.println("positions for word stack");
				positions.inorder(positions.root);
			}
	*/
			trav=trav.next;
		}

		/*
			//OR
		while(trav.next !=null){
			trav=trav.next;
		}
		trav=head;
		head=positions.head;
			
		*/
	}
//*
	// public MyLinkedList<Position> getAllPositionsForThisWord(){
	// 	return positions;
	// }
//*

	public MyLinkedList<Position> getAllPositionsForThisWord(){
		MyLinkedList<Position> allpos=new MyLinkedList<Position>();
		allpos=positions.allPos(positions.root);
		return allpos;
	}

}
