//SUBMITTED BY: ARVIN WANG AND DAVID TRANTHAM
package finalProject;
public interface Queue<Item> extends Iterable<Item> {
    
    boolean isEmpty();
     int size();
     void enqueue(Item item);
     Item dequeue();
}
