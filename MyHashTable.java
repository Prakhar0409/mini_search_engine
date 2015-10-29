public class MyHashTable{

/*	public static void main(String[] args){
		MyHashTable ht=new MyHashTable();
		String a="aa";
		int ans=ht.getHashIndex(a);
		System.out.println(ans);
	}
*/
	int num=10000000;
	@SuppressWarnings("unchecked")
	MyLinkedList<WordEntry>[] arr=(MyLinkedList<WordEntry>[]) new MyLinkedList[num];

	private int getHashIndex(String str){
		int hash=0;
		
		str=str.toLowerCase();
		for(int i=0;i<str.length();i++){
			hash+=(int)str.charAt(i);
			hash=hash%num;
		}
		return hash;
	}

	public int publicHashIndex(String str){
		return getHashIndex(str);
	}

	public void addPositionsForWord(WordEntry w){
		int hash=getHashIndex(w.str);
		if(arr[hash]==null){
			arr[hash]=new MyLinkedList<WordEntry>();
			arr[hash].insert(w);
			return;
		}

		Node<WordEntry> trav=arr[hash].head;
		while(trav!=null){
			if(trav.data.equals(w)){

				//merge positions of w and arr[t]'s this wordEntry(tav.data) together
				trav.data.addPositions(w.positions1);
				return;
			}
			trav=trav.next;
		}
		//will reach here only if there is no word entry corresponding to word given to the function 
		arr[hash].insert(w);
		return;
	}


	public MySet<PageEntry> getPagesWhichContainWord(String str){	//returns a set of pagenetries that contain the queried word
		int hash=getHashIndex(str);
		if(arr[hash]==null){	
			return null;		//throw exception that such a word does not exist in any of the page entries so return null
		}
		Node<WordEntry> trav=arr[hash].head;
		MySet<PageEntry> pagesWithWord=null;
		boolean wordFound=false;	//flag that tells whether the wordEntry for the specified word exists
		WordEntry word=null;
		while(trav!=null){
			if(trav.data.equals(str)){
				word=trav.data;
				wordFound=true;
				break;
			}
			trav=trav.next;
		}
		if(wordFound==false){
			//throw exception that no webpage contains the word by returning null
			return null;
		}else{
			pagesWithWord=new MySet<PageEntry>();
			Node<Position> travPos=word.positions1.head;
			while(travPos!=null){
				pagesWithWord.addElement(travPos.data.p);
				travPos=travPos.next;
			}
			return pagesWithWord;	
		}
	}

	public MySet<Integer> getPositionsOfWordInAPage(String wordName,String pageName){
			int hash=getHashIndex(wordName);
			if(arr[hash]==null){
				return null;		//throw exception because no wordEntry for the word
			}
			Node<WordEntry> trav=arr[hash].head;
			if(trav==null){
				//throw Exception no such word exists	//logically u will never reach here
				return null;
			}
			
			boolean wordFound=false;	//flag to tell if wordEntry for the word is found or not
			WordEntry word=null;
			while(trav!=null){
				if(trav.data.equals(wordName)){
					word=trav.data;
					wordFound=true;
					break;
				}
				trav=trav.next;
			}
			if(wordFound==false){
				//throw Exception no such word exists
				return null;				
			}else if(word!=null){		// a word exists if the return value is an empty myset then the word doesnt occur on the specified webpage
				Node<Position> travPos=word.positions1.head;
				MySet<Integer> wordPos=null;
				while(travPos!=null){
					if(travPos.data.p.equals(pageName)){
						if(wordPos==null){wordPos=new MySet<Integer>();}
						wordPos.addElement(travPos.data.wordIndex);
					}
					travPos=travPos.next;
				}
				return wordPos;				
			}
			return null;	//u will never reach here


			//assignment 5 code



	}
}