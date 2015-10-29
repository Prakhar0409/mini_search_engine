import java.util.*;
public class MySort{
	public static ArrayList<Comparable> sortThisList(MySet<Comparable> listOfSortableEntries){
		ArrayList<Comparable> ans=new ArrayList<Comparable>();
		Node<Comparable> trav=listOfSortableEntries.head;

		while(trav!=null){
			ans.add(trav.data);
			trav=trav.next;
		}

		int swaps=-1;
		while(swaps!=0){
			swaps=0;
			for(int i=1;i<ans.size();i++){
				if(ans.get(i-1).compareTo(ans.get(i))<0){
	//				swap(ans.get(i-1),ans.get(i));
					Comparable tmp=ans.get(i-1);

					ans.set(i-1,ans.get(i));
					ans.set(i,tmp);
					swaps++;
				}
				//System.out.println("here i am"+ans.size());
			}
		}
		//Sorted
		return ans;
	}

}