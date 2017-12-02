package LinkedList;

import node.Node;

/*
  Inserta un nuevo Node despues en una posición determinada, el programa preguntara cuantos elementos hay en la lista y se asegurara por el indice donde se va a insertar
l Obtiene el valor de un Node en una determinada posición, por medio del indice pasar un valor y traer el contenido del Node en la posicion dada por ese valor
l Consulta la posición de un elemento en la lista, traera por ejemploa la posicion del objetro y me traera el indice del mismo
l Actualiza el valor de un Node que se encuentre en la lista ubicado por un valor de referencia, buscara el valor por la posicion y reemplazara el objeto por un nuevo objeto
 
 * */

public class doubleLinkedList<T> {

	private Node<T> start = null, end=null;
	
	public doubleLinkedList(){
		start = new Node<T>();
		start.setIndex(-1);
		end = new Node<T>();
		end.setIndex(-1);
	}
	public doubleLinkedList(T value){
		this();
		
		end.setBack(new Node<T>(value));
		end.getBack().setIndex(0);
		start.setNext(end.getBack());
	}
	
	public boolean addForIndex(int indx, T newvalue) {
		if(indx > Size()|| indx <= 0) return false;
		indx = indx-1;
		
		Node<T> finder = Search(indx);
		
		Node<T> Node = new Node<T>(newvalue);
		
		if (finder != null && finder.getNext()!=null) {
			Node.setNext(finder.getNext());
			finder.setNext(Node);
			Node.setBack(finder);
			Node.getNext().setBack(Node);
			Reindex();
			return true;
		} else {
			
			addNew(newvalue);
			return false; 
		}
	}

	public Node<T> Search(int index) {
		return Search(index, start, end);
	}

	private Node<T> Search(int index, Node<T> start, Node<T> end) {
		if (start.getNext().getIndex() == (index))
		{
			return start.getNext();
		} else if (end.getBack().getIndex() == (index)) 
		{
			return end.getBack();
		} else if (start.getNext().equals(end) || start.equals(end))
		{
			System.out.println("Valor inexistente");
			return null;
		}
		return Search(index, start.getNext(), end.getBack());
	}
	public Node<T> Search(T value){
		return Search(value, start, end);
	}

	private Node<T> Search(T value, Node<T> start, Node<T> end) {
		System.out.println(start.getNext());
		if (start.getNext() == null || end.getBack() == null) {
			return null;
		} else if (start.getNext().getValue().equals(value)) {
			System.out.println(start.getNext().getPos());
			System.out.println(start.getNext().getIndex());
			return start.getNext();
		} else if (end.getBack().getValue().equals(value)) {
			System.out.println(end.getBack().getPos());
			System.out.println(end.getBack().getIndex());
			System.out.println();
			return end.getBack();
		} else if ((start.getNext().equals(end)) || (start.equals(end))) {
			System.out.println("Valor inexistente");
			return null;
		}
		return Search(value, start.getNext(), end.getBack());
	}
	
	
	
	//----------------------------------------------------------------------
	
	public void addNew(T value) {
		Node<T> tmp = start.getNext();
		start.setNext(new Node<T>(value));
	}
	
	/*
	public boolean add(T value, int guia) {
		
		Node<T> tmp=start;
		int con = 0;
		while(tmp.getNext()!=null) {
			tmp=tmp.getNext();
			tmp.setIndex(con++);
		}if (con == guia) {
			if (tmp != null && tmp.getBack()!= null) {
				tmp.getBack().setNext(new Node<T>(value));
				tmp.getBack().getNext().setBack(tmp.getBack());
				tmp.setBack(tmp.getBack().getNext());
				tmp .getBack().setNext(tmp);
			}
		}else
			addEnd(value);
		return true;
		
	}
	*/
	public void addStart(T value){
		Node<T> tmp = start.getNext();
		start.setNext(new Node<T>(value));
		
		if (tmp == null) {
			end.setBack(start.getNext());
			start.getNext().setIndex(0);
		}else {
			start.getNext().setNext(tmp);
			tmp.setBack(start.getNext());
		}
	}
	
	public void addEnd(T value){
		Node<T> tmp = end.getBack();
		end.setBack(new Node<T>(value));
		
		if (tmp == null) {
			start.setNext(end.getBack());
			end.getBack().setIndex(0);
		}else {
			end.getBack().setBack(tmp);
			tmp.setNext(end.getBack());
		}
	}
	
	public boolean Remove(T value){
		Node<T> tmp = Search(value);
		if (tmp!= null) {
			if (tmp.getNext()!=null) {
				tmp.getNext().setBack(tmp.getBack());
			}else {
				end.setBack(tmp.getBack());
			}
			if (tmp.getBack()!=null) {
				tmp.getBack().setNext(tmp.getNext());
			}else {
				start.setNext(tmp.getNext());
			}
			return true;
		}
		return false;
	}
	
	public Node<T> SearchIndex(long index){
		return SearchIndex(index, start, end);
	}
	
	private Node<T> SearchIndex(long index, Node<T> start, Node<T> end){
			Reindex();
		if (start.getNext() == null || end.getBack() == null) {
			System.out.println("Pos nel");
			return null;
		}
		else if (start.getNext().getIndex() == (index)) {
			return start.getNext();
		}
		else if (end.getBack().getIndex() == (index)) {
			return end.getBack();
		}else if ((start.getNext().equals(end)) || (start.equals(end))) {
			System.out.println("Pos tampoco");
			return null;
		}
		return SearchIndex(index, start.getNext(), end.getBack());
	}
	
	/*
	public Node<T> Search(T value){
		return Search(value, start, end);
	}
	
	private Node<T> Search(T value, Node<T> start, Node<T> end){
		if (start.getNext() == null || end.getBack() == null) {
			System.out.println("Pos nel");
			return null;
		}
		else if (start.getNext().getValue().equals(value)) {
			return start.getNext();
		}
		else if (end.getBack().getValue().equals(value)) {
			return end.getBack();
		}else if ((start.getNext().equals(end)) || (start.equals(end))) {
			System.out.println("Pos tampoco");
			return null;
		}
		return Search(value, start.getNext(), end.getBack());
	}
	
	*/
	
	public void pranter(){
		Node<T> tmp = end;
		while (tmp.getBack()!=null){
			tmp = tmp.getBack();
			System.out.println(tmp.getValue());
		}
	}	
	public void pronter(){
		Node<T> tmp = start;
		while (tmp.getNext()!=null){
			tmp = tmp.getNext();
			System.out.println(tmp.getValue());
		}
	}
	public void List() {
		Node<T> tmp = start;
		while (tmp.getNext()!=null){		
			System.out.println(tmp.getNext());
		}
	}
	
	public boolean AddBefore(T value, T newvalue){
		
		Node<T> finder = Search(value);
		
		if (finder != null && finder.getBack()!= null) {
			
			finder.getBack().setNext(new Node<T>(newvalue));
			
			finder.getBack().getNext().setBack(finder.getBack());
			finder.setBack(finder.getBack().getNext());
			finder .getBack().setNext(finder);
		}else
			addEnd(newvalue);
		return true;
	}

	public boolean AddAfter(T value, T newvalue){
		Node<T> finder = Search(value);
		
		if (finder != null && finder.getNext()!= null) {
			
			finder.getNext().setBack(new Node<T>(newvalue));
			finder.getNext().getBack().setNext(finder.getNext());
			finder.setNext(finder.getNext().getBack());
			finder .getNext().setBack(finder);
		}else
			addEnd(newvalue);
		return true;
	}
	
	public void RemoveAfter(T value){
		Node<T> finder = Search(value);
		if (finder != null) {
			finder = finder.getNext();
			Remove(finder.getValue());
		}
	}
	
	public void RemoveBefore(T value){
		Node<T> finder = Search(value);
		if (finder != null) {
			finder = finder.getBack();
			Remove(finder.getValue());
			
		}
	}
	
	public void ClearAll(){
		Node<T> st = start;
		while (st.getNext() != null) {
			st = st.getNext();
			Remove(st.getValue());
		}
	}
	
	public void removeFirst(){
		Node<T> ini = start;
		if(ini != null)
			ini = ini.getNext();
		Remove(ini.getValue());
	}
	
	public void removeLast(){
		Node<T> fin = end;
		if (fin != null)
			fin = fin.getBack();
		Remove(fin.getValue());
	}
	
	public void GetLast(){
		Node<T> fin = end;
		if(fin != null)
			System.out.println("El ultimo es: " + fin.getBack().getValue());
	}
	
	public void GetFirst(){
		Node<T> st = start;
		if (st != null)
		System.out.println("El primero es: " + st.getNext().getValue());
	}
	
	public void Reindex() {
		Node<T> tmp=start;
		int con = 0;
		while(tmp.getNext()!=null) {
			tmp=tmp.getNext();
			tmp.setIndex(con++);
		}
	}
	
	public void IndexOf(T value){
		Reindex();
		Node<T> finder = Search(value);
		if (finder != null) {
			System.out.println("La posici�n de: "+ value + " esta en: " + finder.getIndex());
		}
	}
	
	public long Size(){		
		Node<T> ini = start;
		Reindex();
		while (ini.getNext() != null) {
			ini = ini.getNext();
		}
		//System.out.println("El tama�o es de: " + ini.getIndex());
		return ini.getIndex();
	}
	
	public void Remplace(T value, T newvalue){
		Node<T> finder = Search(value);
		if (finder != null) {
			finder.setValue(newvalue);
		}
	}
	
	public String IsEmpty(){
		Node<T> tmp = start;
		Node<T> tmp2 = end;
		if (tmp.getNext() == null && tmp2.getNext() == null) {
			return "La lista se encuentra vacia morro";
		}else {
			return "La lista no se encuentra vacia morro";
		}
	}
	
	public void ExisteElemento(T value){
		Node<T> finder = Search(value);
		if (finder != null) {
			System.out.println("El elemento se encuentra en la lista");
		}else {
			System.out.println("El elemento no se encuentra en la lista");
		}
	}
	
	public void Ocurrencia (T value){
		int cont = 0;
		Node<T> tmp = start;
		
		if (tmp.getNext() == tmp) {
			tmp = tmp.getNext();
			
			cont++;
		}
		System.out.println("El numero de veces que se repite es: " + cont);
	}
	
	
	/*
	public void Maximo(){
		Node<T> tmp = start;
		if (tmp.getNext() >= tmp) {
			System.out.println(tmp);
		}
	}
	*/
}	
	/*
	public boolean SonIguales(doubleLinkedList<Integer> num, doubleLinkedList<Integer> num2){	
		int equal = 1;
		 if(num == null && num2 == null){
		        equal = 1;
		    }
		    else if((num == null && num2 != null) || (num !=null && num2 == null)){
		        equal = 0;
		    }
		    else{
		    while(num != null){
		        if(num.start.getNext() != num2.start.getNext() || num2.start.getNext() == null){
		            equal = 0;
		            break;
		        }
		        num.start = num.start.getNext();
		        num2.start = num2.start.getNext();
		    }
		    if(num2.start.getNext() != null){
		        equal = 0;
		    }
		}
	} return false;
  }
*/