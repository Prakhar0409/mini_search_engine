import java.io.*;
import java.util.*;

public class SearchEngine{
	InvertedPageIndex invPageIndex;
	MySet<String> allPages=new MySet<String>();
	public SearchEngine() {
		// ...
		invPageIndex=new InvertedPageIndex();

	}

	public void performAction(String actionMessage) {
		//....
		String[] arr;
		arr=actionMessage.split(" ");

/****************************************************************************/
		if(arr[0].equals("addPage")){
			System.out.println("addPage "+arr[1]);
			try{
				if(allPages.IsMember(arr[1])){
					//throw exception
					System.out.println("Page already exists");
					return;
				}
				PageEntry tmpPageEntry=new PageEntry(arr[1]);
				//System.out.println("pageentry made succesfull n i m very sure it is right");
				allPages.addElement(arr[1]);
				invPageIndex.addPage(tmpPageEntry);	
				//System.out.println("page added");			
			}catch(IOException e){
				System.out.println("There has been an error in reading the specified file.");
			}

/*****************************************************************************/
		}else if(arr[0].equals("queryFindPagesWhichContainWord")){
			System.out.println("queryFindPagesWhichContainWord "+arr[1]);
			arr[1]=structure(arr[1]);

			MySet<PageEntry> pagesWithWord=invPageIndex.allWords.getPagesWhichContainWord(arr[1]);
			if(pagesWithWord==null){
				//throw exception
				System.out.println("No webpage contains word "+arr[1]);
				return;
			}
			Node<PageEntry> trav=pagesWithWord.head;	//traverse through the pages in the result set
			
			String[] str=new String[1];
			str[0]=arr[1];
			MySet<Comparable> ans=new MySet<Comparable>();
			while(trav!=null){
				float r=trav.data.getRelevanceOfPage(str,false);
				SearchResult tmp=new SearchResult(trav.data,r);
				ans.addElement(tmp);
				trav=trav.next;
			}

			ArrayList ansInOrder=MySort.sortThisList(ans);
			for (int i=0;i<ansInOrder.size()-1 ;i++ ) {
				System.out.print(((SearchResult)ansInOrder.get(i)).p.pageName+" r="+((SearchResult)ansInOrder.get(i)).relevance+", ");
			}
			int i=ansInOrder.size()-1;
			System.out.println(((SearchResult)ansInOrder.get(i)).p.pageName+" r="+((SearchResult)ansInOrder.get(i)).relevance);		


/********************************************************************************/
		}else if(arr[0].equals("queryFindPositionsOfWordInAPage")){
			
			//arr[1] is word and arr[2] is name of webPage
			arr[1]=structure(arr[1]);
			System.out.println("queryFindPositionsOfWordInAPage "+arr[1]+" "+arr[2]);
			
			if(allPages.IsMember(arr[2])==false){
				System.out.println("No such page exists or added");	//throw error because the page has not been added
				return;
			}
		
			MySet<Integer> wordPos=invPageIndex.allWords.getPositionsOfWordInAPage(arr[1],arr[2]);
			if(wordPos==null){
				//throw exception
				System.out.println("webpage "+arr[2]+" does not contain word "+arr[1]);
				return;
			}
			Node<Integer> trav=wordPos.head;
			if(trav==null){
				//throw exception though you will never reach here
				System.out.println("webpage "+arr[2]+" does not contain word "+arr[1]);
				return;
			}
			while(trav.next!=null){
				System.out.print(trav.data+", ");
				trav=trav.next;
			}
			System.out.println(trav.data);

/**************************************************************************************/

		}else if(arr[0].equals("queryFindPagesWhichContainAllWords")){
			for(int i=0;i<arr.length;i++){
				System.out.print(arr[i]+" ");
				arr[i]=structure(arr[i]);			//make the words to lower case remove plurals etc
			}
			System.out.println();
			MySet<PageEntry> pagesWithWords=invPageIndex.allWords.getPagesWhichContainWord(arr[1]);
			for(int i=2;i<arr.length;i++){
				MySet<PageEntry> tmpPagesWithWord=invPageIndex.allWords.getPagesWhichContainWord(arr[i]);	
				if(pagesWithWords!=null){
					pagesWithWords=pagesWithWords.intersection(tmpPagesWithWord);
				}else{
					System.out.println("No page contains all the words");	//throw exception
					return;
				}
			}
			if(pagesWithWords==null){
				System.out.println("no page contains all the words");	//throw exception
				return;
			}


			String str[]=new String[arr.length-1];
			for(int i=0;i<arr.length-1;i++){
				str[i]=arr[i+1];
			}
			
			Node<PageEntry> trav=pagesWithWords.head;
			MySet<Comparable> ans=new MySet<Comparable>();
			while(trav!=null){
				float r=trav.data.getRelevanceOfPage(str,false);
				SearchResult tmp=new SearchResult(trav.data,r);
				ans.addElement(tmp);
				trav=trav.next;
			}

			ArrayList ansInOrder=MySort.sortThisList(ans);
			for (int i=0;i<ansInOrder.size()-1 ;i++ ) {
				System.out.print(((SearchResult)ansInOrder.get(i)).p.pageName+" r="+((SearchResult)ansInOrder.get(i)).relevance+", ");
			}
			int i=ansInOrder.size()-1;
			System.out.println(((SearchResult)ansInOrder.get(i)).p.pageName+" r="+((SearchResult)ansInOrder.get(i)).relevance);

/****************************************************************************************************************/
		}else if(arr[0].equals("queryFindPagesWhichContainAnyOfTheseWords")){
			for(int i=0;i<arr.length;i++){
				System.out.print(arr[i]+" ");
				arr[i]=structure(arr[i]);			//make the words to lower case remove plurals etc
			}
			System.out.println();
			MySet<PageEntry> pagesWithWords=invPageIndex.allWords.getPagesWhichContainWord(arr[1]);
			for(int i=2;i<arr.length;i++){
				MySet<PageEntry> tmpPagesWithWord=invPageIndex.allWords.getPagesWhichContainWord(arr[i]);	
				if(pagesWithWords!=null){
					pagesWithWords=pagesWithWords.union(tmpPagesWithWord);	
				}else{
					pagesWithWords=tmpPagesWithWord;
				}
				
			}
			if(pagesWithWords==null){
				System.out.println("No page contains any of the words!");	//throw exception
				return;
			}

			String str[]=new String[arr.length-1];
			for(int i=0;i<arr.length-1;i++){
				str[i]=arr[i+1];
			}
			
			Node<PageEntry> trav=pagesWithWords.head;
			MySet<Comparable> ans=new MySet<Comparable>();
			while(trav!=null){
				float r=trav.data.getRelevanceOfPage(str,false);
				SearchResult tmp=new SearchResult(trav.data,r);
				ans.addElement(tmp);
				trav=trav.next;
			}

			ArrayList ansInOrder=MySort.sortThisList(ans);
			for (int i=0;i<ansInOrder.size()-1 ;i++ ) {
				System.out.print(((SearchResult)ansInOrder.get(i)).p.pageName+" r="+((SearchResult)ansInOrder.get(i)).relevance+", ");
			}
			int i=ansInOrder.size()-1;
			System.out.println(((SearchResult)ansInOrder.get(i)).p.pageName+" r="+((SearchResult)ansInOrder.get(i)).relevance);
/******************************************************************************************/
		}else if(arr[0].equals("queryFindPagesWhichContainPhrase")){
			for(int i=0;i<arr.length;i++){
				System.out.print(arr[i]+" ");
				arr[i]=structure(arr[i]);			//make the words to lower case remove plurals etc
			}
			System.out.println();
			
			String str[]=new String[arr.length-1];
			for(int i=0;i<arr.length-1;i++){
				str[i]=arr[i+1];
			}
			
			ArrayList ansInOrder=invPageIndex.getPagesWhichContainPhrase(str);
			if(ansInOrder==null){		//throw exception
				System.out.println("No page that contains the phrase!");
				return;
			}

			for (int i=0;i<ansInOrder.size()-1 ;i++ ) {
				System.out.print(((SearchResult)ansInOrder.get(i)).p.pageName+" r="+((SearchResult)ansInOrder.get(i)).relevance+", ");
			}
			int i=ansInOrder.size()-1;
			System.out.println(((SearchResult)ansInOrder.get(i)).p.pageName+" r="+((SearchResult)ansInOrder.get(i)).relevance);
		}

	}



	public String structure(String wordstr){
		wordstr=wordstr.toLowerCase();
		wordstr=wordstr.replaceAll("[{}()<>=.,\";?#!\\-:\\[\\]]"," ");
		wordstr=wordstr.replaceAll("'","");
		if(wordstr.equals("stacks")){wordstr="stack";}
		else if(wordstr.equals("structures")){wordstr="structure";}
		else if(wordstr.equals("applications")){wordstr="application";}
		return wordstr;
	}

}
