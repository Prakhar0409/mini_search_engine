public class Position{
	//page p;
	//wordposition i;
	PageEntry p;
	int wordIndex;

	public Position(PageEntry p, int wordIndex){
		this.p=p;
		this.wordIndex=wordIndex;
	}

	public boolean equals(Position a){
		return (p==a.p && wordIndex==a.wordIndex);
	}

	public PageEntry getPageEntry(){
		return p;
	}

	public int getWordIndex(){
		return wordIndex;
	}
}