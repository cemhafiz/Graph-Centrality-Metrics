public class Graph {
	private int[][] adjacency; // to keep edges
	private int size;
	private int[] betweenness; // to keep betweenness value of every node in graph

	public Graph(int size) {
		adjacency = new int[size][size];
		this.size = size;
		betweenness = new int[size];
	}

	public void addEdge(String source, String destination, int weight) {

		adjacency[Integer.parseInt(source) - 1][Integer.parseInt(destination) - 1] = weight;   // Since the graph is undirected, adding adjacency for both
		adjacency[Integer.parseInt(destination) - 1][Integer.parseInt(source) - 1] = weight;
	}

	public int size() {
		return this.size;
	}

	public int[][] getAdjacency() {
		return adjacency;
	}
	
	int minDistance(int[] distances, boolean[] shortest_path_finished) {
		
		int min = Integer.MAX_VALUE, min_index = 0;
		
		for(int i = 0; i < size; i++) {
			if(shortest_path_finished[i] == false && distances[i] <= min) {
				min = distances[i];
				min_index = i;
			}
		}
		return min_index;
	}
	
	public int[] getBetweenness() {
		return betweenness;
	}
	
	public int Dijkstra(String source) {   // Using Dijkstra's Algorithm to find the closest path between two nodes
		
		int distances[] = new int[size];
		
		boolean[] shortest_path_finished = new boolean[size];
		
		for(int i = 0; i < size; i++) {
			distances[i] = Integer.MAX_VALUE;
			shortest_path_finished[i] = false;
		}
		
		distances[Integer.parseInt(source) - 1] = 0;
		
		for(int i = 0; i < size - 1; i++) {
			int temp_vertex = minDistance(distances, shortest_path_finished);
			shortest_path_finished[temp_vertex] = true;
			
			for(int j = 0; j < size; j++)
				if(!shortest_path_finished[j] && adjacency[temp_vertex][j] != 0 && distances[temp_vertex] != Integer.MAX_VALUE
				&& distances[temp_vertex] + adjacency[temp_vertex][j] < distances[j]) {
					distances[j] = distances[temp_vertex] + adjacency[temp_vertex][j];
					betweenness[temp_vertex]++;  // In a shortest path, increasing betweenness value of both nodes
					betweenness[j]++;
				}
		}
		int sum = 0;
		for(int i = 0; i < size; i++) {
			if(distances[i] != Integer.MAX_VALUE)  // Control for disconnected Nodes
				sum += distances[i];
			//System.out.println("Distance from source " + source + " vertex to " + (i + 1) + " vertex : " + distances[i]);
		}
		return sum;  // Returning the sum value of one node's distance to others
	}
}