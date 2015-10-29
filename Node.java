public class Node<T>{
	T data;
	Node<T> next=null;

	public Node(){
		next=null;
	}
	public Node(T element){
		data=element;
	}
	public Node(T element,Node<T> next){
		data=element;
		this.next=next;
	}
}