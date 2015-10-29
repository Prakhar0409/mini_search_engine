import java.util.*;

public class InvertedPageIndex{
	MyHashTable allWords=new MyHashTable();;

	public void addPage(PageEntry p){
		Node<WordEntry> trav=p.pageIndex.words.head;
		while(trav!=null){
			allWords.addPositionsForWord(trav.data);
			trav=trav.next;
		} 
	}

	/*Assignment 5 exclusive code starts here*/
	public ArrayList<Comparable> getPagesWhichContainPhrase(String str[]){
		MySet<PageEntry> pagesWithAllGivenWords=allWords.getPagesWhichContainWord(str[0]);
		//the above list contains unique list of pages that contain the word str[0]


		MySet<PageEntry> ansPages=new MySet<PageEntry>();
		if(pagesWithAllGivenWords==null){
			//No page with the given phrase hence return null.
			return null;
		}



		MySet<Comparable> resultSet=null;

		Node<PageEntry> travPages=pagesWithAllGivenWords.head;
		boolean finaPhrase=true;
		while(travPages!=null){		//traverse on various pages that contained first word of the phrase
			MySet<Integer> positionsOfTheWord=allWords.getPositionsOfWordInAPage(str[0],travPages.data.pageName);
			Node<Integer> travPositions=positionsOfTheWord.head;	//reference to traverse on positions of word str[0]
			float r=0;							//relevance
			while(travPositions!=null){		//travesing through all positions of the word on that page
				finaPhrase=true;					//supposing that the full final phrase exists with this position entry
				
				for(int i=1;i<str.length;i++){		//traversing over the words to check if the are at the position expected 
					int hash_index=allWords.publicHashIndex(str[i]);	
					MyLinkedList<WordEntry> x=allWords.arr[hash_index];
					if(x==null){
						return null;	//no pages which contain the phrase since this word of the phrase has no wordEntry anywhere
					}
					Node<WordEntry> travWord=x.head;
					boolean found=false;	//bool to represent whether the word is found redundant boolean but logically makes sense
					boolean flag=false;		//flag represents if the word is next to the given previous word
					while(travWord!=null){
						if(travWord.data.str.equals(str[i])){
							found=true;
							WordEntry w=travWord.data;
							if(w.positions.PosOfQueryWord(travPositions.data + i,travPages.data.pageName,w.positions.root)){		
								flag=true;		//implies that the next word is correctly there on this page after this word
							}	 
						}
						travWord=travWord.next;
					}
					if(found==false || flag==false){		//redundant boolean found because flag true implies that found must be true
						finaPhrase=false;
						break;
					}
				}
				if(finaPhrase==true){		//the phrase exists corresponding to that particular position
					ansPages.addElement(travPages.data);		//adding the page corresponding to that position to the result set 
					
					int l=travPositions.data;		//calculating relevance
					for(int i=0;i<str.length;i++){
						r+=(float)1/((float)(l+i)*(l+i));
					}
					
				}
				travPositions=travPositions.next;
			}
			if(r>0){
				SearchResult tmp=new SearchResult(travPages.data,r);	//making a new entry corresponding to the particular page that contains atleast a relevant query
				if(resultSet==null){resultSet=new MySet<Comparable>();}
				resultSet.addElement(tmp);
			}
			travPages=travPages.next;
		}
		if(resultSet==null){
			return null;		//no page contains the phrase
		}
		ArrayList resultsInOrder=MySort.sortThisList(resultSet);

		return resultsInOrder;
	}

}