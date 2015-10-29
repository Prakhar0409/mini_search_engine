all:
	javac MySet.java
	javac MyLinkedList.java
	javac Node.java
	javac Position.java
	javac avltree.java
	javac WordEntry.java
	javac PageIndex.java
	javac PageEntry.java
	javac MyHashTable.java
	javac InvertedPageIndex.java
	javac SearchEngine.java
	javac SearchResult.java
	javac checker.java

run:
	java checker

clean:
	rm *.class
