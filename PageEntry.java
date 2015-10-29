import java.io.*;

public class PageEntry{
	String pageName;
	PageIndex pageIndex;
	MyLinkedList<String> stopWords;

	public PageEntry(String pageNamer)throws IOException{
	
		
		StopWords();

		this.pageName=pageNamer;

		String dir="./webpages/";
		
		File f=new File(dir+pageName);
		
		FileReader fr=new FileReader(dir+pageName);
		
		BufferedReader br=new BufferedReader(fr);
		
		String line;
		String[] words;
		int wordNum=0;
		
		pageIndex=new PageIndex();
		while((line=br.readLine())!=null){
			if(line.length()==0){continue;}
		//	System.out.println("previous: "+line);
			line=line.toLowerCase();
			line=removePunctuation(line);
		//	System.out.println("new: "+line);
			words=line.split("\\s+");

			for(String read: words){
				wordNum++;
			//	if(read.equals("function"))
			//	System.out.print(read+"-->wordNum :"+wordNum+" ; ");
				if(stopWords.IsMember(read)==false){
					if(read.equals("stacks")){read="stack";}
					else if(read.equals("structures")){read="structure";}
					else if(read.equals("applications")){read="application";}
					Position tt=new Position(this,wordNum);
					pageIndex.addPositionForWord(read,tt);						
				}

			}
			//System.out.println();
		}
		//System.out.println(pageIndex.words.head.data.str+" size:"+pageIndex.words.size);
	}

	public void StopWords(){
		stopWords=new MyLinkedList<String>();
		stopWords.insert("a");
		stopWords.insert("an");
		stopWords.insert("the");
		stopWords.insert("they");
		stopWords.insert("these");
		stopWords.insert("this");
		stopWords.insert("for");
		stopWords.insert("is");
		stopWords.insert("are");
		stopWords.insert("was");
		stopWords.insert("of");
		stopWords.insert("or");
		stopWords.insert("and");
		stopWords.insert("does");
		stopWords.insert("will");
		stopWords.insert("whose");
	}

	public String removePunctuation(String line){
		line=line.replaceAll("[{}()<>=,\";?#!\\-:\\[\\]]"," ");
		line=line.replaceAll("['.]","");
/*		line=line.replaceAll("\\{"," ");
		line=line.replaceAll("\\}"," ");
		line=line.replaceAll("\\["," ");
		line=line.replaceAll("\\]"," ");
		line=line.replaceAll("\\<"," ");
		line=line.replaceAll("\\>"," ");
		line=line.replaceAll("\\="," ");
		line=line.replaceAll("\\("," ");
		line=line.replaceAll("\\)"," ");
		line=line.replaceAll("\\."," ");
		line=line.replaceAll("\\,"," ");
		line=line.replaceAll("\\;"," ");
		line=line.replaceAll("\\'","");
		line=line.replaceAll("\""," ");
		line=line.replaceAll("\\?"," ");
		line=line.replaceAll("\\#"," ");
		line=line.replaceAll("\\!"," ");
		line=line.replaceAll("\\-"," ");
		line=line.replaceAll("\\:"," ");
*/
		return line;
	}

	public boolean equals(PageEntry a){
		return (pageName.equals(a.pageName));
	}

	public boolean equals(String a){
		return (pageName.equals(a));
	}


	public PageIndex getPageIndex(){
		return pageIndex;
	}

	/*Assignment 5 exclusive code starts here*/
	public float getRelevanceOfPage(String str[],boolean doTheseWordsRepresentAPhrase){
		float relevance=0;
		
			for(int i=0;i<str.length;i++){
				Node<WordEntry> wordTrav=pageIndex.words.head;
				while(wordTrav!=null){
					if(wordTrav.data.str.equals(str[i])){
						Node<Position> positionTrav=wordTrav.data.positions1.head;
						while(positionTrav!=null){
							if(positionTrav.data.p.equals(this)){
								int x=positionTrav.data.wordIndex;
								relevance +=(float)((float)1/(float)(x*x));	
							}
							positionTrav=positionTrav.next;
						}
						
					}
					wordTrav=wordTrav.next;
				}
			}
			return relevance;			

		
		// else{
		// 	for(int i=0;i<str.length;i++){
		// 		Node<WordEntry> wordTrav=pageIndex.words.head;
		// 		while(wordTrav!=null){
		// 			if(wordTrav.data.equals(str[i])){
		// 				WordEntry word=wordTrav.data;

		// 			}
		// 			wordTrav=wordTrav.next;
		// 		}
		// 	}
		// }

	}
}
