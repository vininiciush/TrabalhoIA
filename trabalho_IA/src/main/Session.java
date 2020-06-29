package main;

public class Session {
	
	private static int numGenerations;
	private static Session instance;
	
	private Session() {}
	
	public static Session getInstance() {
        if (instance == null)
            instance = new Session();
        return instance;
    }

	public int getNumGenerations() {
		return numGenerations;
	}

	public void setNumGenerations(int numGenerations) {
		Session.numGenerations = numGenerations;
	}
}
