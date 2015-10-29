public class MyLinkedList<T>{
	Node<T> head=null;
	int size=0;

	public MyLinkedList(){
		head=null;
	}

	public void insert(T element){
		Node<T> tmp=new Node<T>(element,head);
		head=tmp;
		size++;
		return;
	}

	public T delete(T element){
		if(head==null){
			//throw Exception--to be done
			System.out.println("Exception: no elements in the list");
			return null;
		}
		Node<T> trav1=head;
		Node<T> trav2=head.next;
		while(trav2!=null){
			if(trav2.data.equals(element)){
				trav1.next=trav2.next;
				size--;
				return trav2.data;
			}
			trav1=trav2;
			trav2=trav2.next;
		}
		System.out.println("Exception: no elements in the list");
		return null;
	}

	public boolean IsMember(T element){
		Node<T> trav=head;
		while(trav!=null){
			if(trav.data.equals(element)){
				return true;
			}
			trav=trav.next;
		}
		return false;
	}

}
