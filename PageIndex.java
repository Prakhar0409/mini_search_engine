public class PageIndex{

	MyLinkedList<WordEntry> words=new MyLinkedList<WordEntry>();
	static int i=0;
	public void addPositionForWord(String str,Position p){
	// this code was used in assignment 4
	//		System.out.println("reaches start for str:"+str+" strlen"+str.length());
		Node<WordEntry> trav=words.head;
		WordEntry tmp;
		
		while(trav!=null){
			if(trav.data.equals(str)){
				tmp=trav.data;
				tmp.addPosition(p);
				return;
			}
			trav=trav.next;
		}
		
		//for making this unique
		boolean inserted=false;
		Node<WordEntry> a=words.head;
		while(a!=null){
			if(a.equals(str)){		//dont think this will work
			//	System.out.println("never entered here! wordentry addpositionforword");
				a.data.addPosition(p);
				inserted=true;
				break;
			}
			a=a.next;
		}
		if(inserted==false){
			tmp=new WordEntry(str);	
			tmp.addPosition(p);
			words.insert(tmp);
		}

		//words.insert(tmp);
		return;


		
	}

	public MyLinkedList<WordEntry> getWordEntries(){
		return words;
	}

}
