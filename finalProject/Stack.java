package finalProject;
public interface Stack<Item> extends Iterable<Item> {
    
    boolean isEmpty();           // returns true if nothing in the stack, false otherwise
    int size();                  // returns the number of elements in the stack
    void push(Item item);        // pushes the given item onto the top of the stack
    Item pop();                  // pops the top-most item from the stack, returning it
    //Iterator<Item> iterator(); // required by Iterable<Item>; returns an iterator for the stack
}