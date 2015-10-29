public class MySet<T>{
	Node<T> head=null;

	public boolean IsMember(T element){
		if(head==null){return false;}
		Node<T> trav=head;
		while(trav!=null){
			if(trav.data.equals(element)){
				return true;
			}
			trav=trav.next;
		}
		return false;
	}

	

	public void addElement(T element){
		if(head==null){
			head=new Node<T>(element,null);
			return;
		}
		if(this.IsMember(element)==false){
			Node<T> tmp=new Node<T>(element,head);
			head=tmp;			
		}
		return;
	}

	public MySet<T> union(MySet<T> otherSet){
		MySet<T> tmp=null;
		Node<T> trav=this.head;
		while(trav!=null){
			if(tmp==null){tmp=new MySet<T>();}
			tmp.addElement(trav.data);
			trav=trav.next;
		}
		trav=otherSet.head;
		while(trav!=null){
			if(tmp==null){tmp=new MySet<T>();}
			tmp.addElement(trav.data);
			trav=trav.next;
		}
		return tmp;
	}

	public MySet<T> intersection(MySet<T> otherSet){
		MySet<T> tmp=null;
		Node<T> trav1=this.head;
		Node<T> trav2=otherSet.head;
		while(trav1!=null){
			trav2=otherSet.head;
			while(trav2!=null){
				if(trav1.data.equals(trav2.data)){
					if(tmp==null){tmp=new MySet<T>();}
					tmp.addElement(trav1.data);
				}
				trav2=trav2.next;
			}
			trav1=trav1.next;
		}
		return tmp;
	}

}

