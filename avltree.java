public class avltree{
	node1 root=null;


	public avltree(node1 a){
		root=a;
		return;
	}

	public avltree(){
		return;
	}

	public MyLinkedList<Position> allPos(node1 pnode){
		if(pnode==null){return null;}

		MyLinkedList<Position> allposl= new MyLinkedList<Position>();
		MyLinkedList<Position> allposr= new MyLinkedList<Position>();
		
		allposl=allPos(pnode.left);
		allposl.insert(pnode.data);
		allposr=allPos(pnode.right);
		Node<Position> trav=allposr.head;
		while(trav!=null){
			allposl.insert(trav.data);
			trav=trav.next;
		}
		
		return allposl;
	}

	//search in an avl tree is same as in a binary tree
	public boolean PosOfQueryWord(int a,String page_name,node1 n){
		if(n==null){return false;}
		if(n.data.wordIndex==a && n.data.p.pageName.equals(page_name)){return true;}
		else if(n.data.wordIndex>a){
			return PosOfQueryWord(a,page_name,n.left);
		}else{
			return PosOfQueryWord(a,page_name,n.right);
		}
	}

	public void inorder(node1 trav){
		if(trav==null){return;}
		System.out.println("left");
		inorder(trav.left);
		//print the current node -- donno how
		System.out.println("center");
		System.out.println("position is pagename:"+trav.data.p.pageName+" wordIndex:"+trav.data.wordIndex);
		System.out.println("right");
		inorder(trav.right);
	}
	public void treeInsert(PageEntry p,int wordIndex){
		//if(root==null){root=new node1(p,wordIndex);return;}
		//System.out.println("root is null"+root);
		root=this.insert(root,p,wordIndex);
		//System.out.println("returns back so some other problem");
		return;
	}


	public node1 insert(node1 pnode,PageEntry page_entry,int wordIndex){
		if(pnode==null){
			node1 tmp=new node1(page_entry,wordIndex);
			return tmp;
		}
		//System.out.println("there is empty before");
		if(pnode.data.wordIndex>wordIndex){
			pnode.left=this.insert(pnode.left,page_entry,wordIndex);
		}
		else{
			pnode.right=this.insert(pnode.right,page_entry,wordIndex);
		}

		if(pnode.left!=null && pnode.right!=null){
			pnode.height=(pnode.left.height)>(pnode.right.height)? pnode.left.height+1:pnode.right.height +1 ;	
			pnode.balance=pnode.left.height-pnode.right.height;
		}else if(pnode.left==null && pnode.right==null){pnode.height=1;pnode.balance=0;}
		else if(pnode.left==null ){pnode.height=pnode.right.height+1;pnode.balance=-pnode.right.height;}
		else{pnode.height=pnode.left.height+1;pnode.balance=pnode.left.height;}
		
		//System.out.println("ht at pnode: "+pnode.data.wordIndex+" ht:"+pnode.height);
		

		//the left left case
		if(pnode.left!=null && pnode.balance>1 && wordIndex<pnode.left.data.wordIndex){
		//	System.out.println("from here1 at pnode: "+pnode.data.wordIndex);
			return rightRotate(pnode);
		}
		//right right case
		if(pnode.right!=null && pnode.balance<-1 && wordIndex>=pnode.right.data.wordIndex){
		//	System.out.println("from here2 at pnode: "+pnode.data.wordIndex);
			return leftRotate(pnode);
		}
		//left rght case
		if(pnode.left!=null && pnode.balance>1 && wordIndex>=pnode.left.data.wordIndex){
		//	System.out.println("from here3 at pnode: "+pnode.data.wordIndex);
			pnode.left=leftRotate(pnode.left);
			return rightRotate(pnode);
		}
		//right left case
		if(pnode.right!=null && pnode.balance<-1 && wordIndex<pnode.right.data.wordIndex){
		//	System.out.println("from here4 at pnode: "+pnode.data.wordIndex);
			pnode.right=rightRotate(pnode.right);
			return leftRotate(pnode);
		}

	
		return pnode;
	}



	public node1 leftRotate(node1 pnode){
		node1 r=pnode.right;
		pnode.right=r.left;
		r.left=pnode;
		if(pnode.left!=null && pnode.right!=null){
			pnode.height=(pnode.left.height)>(pnode.right.height)? pnode.left.height+1:pnode.right.height +1 ;	
			pnode.balance=pnode.left.height-pnode.right.height;
		}else if(pnode.left==null && pnode.right==null){pnode.height=1;pnode.balance=0;}
		else if(pnode.left==null ){pnode.height=pnode.right.height+1;pnode.balance=-pnode.right.height;}
		else{pnode.height=pnode.left.height+1;pnode.balance=pnode.left.height;}

		if(r.left!=null && r.right!=null){
			r.height=(r.left.height)>(r.right.height)? r.left.height+1:r.right.height +1 ;	
			r.balance=r.left.height-r.right.height;
		}else if(r.left==null && r.right==null){r.height=1;r.balance=0;}
		else if(r.left==null ){r.height=r.right.height+1;r.balance=-r.right.height;}
		else{r.height=r.left.height+1;r.balance=r.left.height;}


		return r;
	}
	public node1 rightRotate(node1 pnode){
		node1 l=pnode.left;
		pnode.left=l.right;
		l.right=pnode;
		if(pnode.left!=null && pnode.right!=null){
			pnode.height=(pnode.left.height)>(pnode.right.height)? pnode.left.height+1:pnode.right.height +1 ;	
			pnode.balance=pnode.left.height-pnode.right.height;
		}else if(pnode.left==null && pnode.right==null){pnode.height=1;pnode.balance=0;}
		else if(pnode.left==null ){pnode.height=pnode.right.height+1;pnode.balance=-pnode.right.height;}
		else{pnode.height=pnode.left.height+1;pnode.balance=pnode.left.height;}

		if(l.left!=null && l.right!=null){
			l.height=(l.left.height)>(l.right.height)? l.left.height+1:l.right.height +1 ;	
			l.balance=l.left.height-l.right.height;
		}else if(l.left==null && l.right==null){l.height=1;l.balance=0;}
		else if(l.left==null ){l.height=l.right.height+1;l.balance=-l.right.height;}
		else{l.height=l.left.height+1;l.balance=l.left.height;}


		return l;
	}

	

}

class node1{
	Position data;
	node1 left=null;
	node1 right=null;
	int height=0;
	int balance=0;

	public node1(){
		this.data.p=null;
		this.data.wordIndex=0;
		height=1;
	}

	public node1(PageEntry a,int b){
		data=new Position(a,b);
		height=1;
	}


}