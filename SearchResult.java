import java.lang.*;
public class SearchResult implements Comparable{
	PageEntry p;
	float relevance;

	public SearchResult(){
		p=null;
		relevance=0;
	}
	public SearchResult(PageEntry page,float r){
		p=page;
		relevance=r;
	}
	public PageEntry getPageEntry(){
		return p;
	}
	public float getRelevance(){
		return relevance;
	}
	public int compareTo(Object b){
		SearchResult a=(SearchResult) b;
		if(relevance==a.relevance){
			return 0;
		}
		else if(relevance>a.relevance){
			return 1;
		}else{
			return -1;
		}
	}
}