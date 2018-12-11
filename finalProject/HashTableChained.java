//SUBMITTED BY: ARVIN WANG AND DAVID TRANTHAM
package finalProject;

public class HashTableChained {

	private int M;
	private Chain[] a;
	
	public HashTableChained(int M) {
		this.M = M;
		a = (Chain []) new Chain[M];
		for (int i = 0; i < M; i++) {
			a[i] = new Chain();
		}
	}
	
	public int hash(String name) {
		return (name.hashCode() & 0x7fffffff) % M; //reverse the first of the hashcode to 0 and keep the rest unchanged. Avoid negative;
	}
	
	public User get(String name) {
		return a[hash(name)].get(name);
	}
	
	public void put(User user) {
		a[hash(user.name())].put(user);
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i <= a.length - 1; i++) {
			s += i + ":";
			s += a[i];
		}
		return s;
	}
}
