import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cem_Hafizogullari_2016510034 {
	
	public static void main(String[] args) throws IOException {
		
		// Reading files
		
		BufferedReader facebook_txt = new BufferedReader(new FileReader("facebook_social_network.txt"));
		BufferedReader facebook_txt_vertex_count = new BufferedReader(new FileReader("facebook_social_network.txt"));
		
		BufferedReader karate_txt = new BufferedReader(new FileReader("karate_club_network.txt"));
		BufferedReader karate_txt_vertex_count = new BufferedReader(new FileReader("karate_club_network.txt"));
		
		int facebook_graph_vertex_n = 0;
		int karate_graph_vertex_n = 0;
		
		String read;
		
		while((read = facebook_txt_vertex_count.readLine()) != null) {
			String[] s = read.split(" ");
			if(Integer.parseInt(s[0]) > facebook_graph_vertex_n)
				facebook_graph_vertex_n = Integer.parseInt(s[0]);
			else if(Integer.parseInt(s[1]) > facebook_graph_vertex_n)
				facebook_graph_vertex_n = Integer.parseInt(s[1]);
		}
		
		while((read = karate_txt_vertex_count.readLine()) != null) {
			String[] s = read.split(" ");
			if(Integer.parseInt(s[0]) > karate_graph_vertex_n)
				karate_graph_vertex_n = Integer.parseInt(s[0]);
			else if(Integer.parseInt(s[1]) > karate_graph_vertex_n)
				karate_graph_vertex_n = Integer.parseInt(s[1]);
		}
		
		// Creating graphs
		
		Graph facebook_graph = new Graph((int) facebook_graph_vertex_n);
		Graph karate_graph = new Graph((int) karate_graph_vertex_n);
		
		while((read = facebook_txt.readLine()) != null) {
			String[] s = read.split(" ");
			facebook_graph.addEdge(s[0], s[1], 1);
		}
		
		while((read = karate_txt.readLine()) != null) {
			String[] s = read.split(" ");
			karate_graph.addEdge(s[0], s[1], 1);
		}
		
		facebook_txt.close();
		karate_txt.close();
		facebook_txt_vertex_count.close();
		karate_txt_vertex_count.close();
		
		// Getting values
		PriorityQueue Karate_Closeness = new PriorityQueue(karate_graph.size());
		PriorityQueueDescendingOrder Karate_Betweenness = new PriorityQueueDescendingOrder(karate_graph.size());
		int[] karate_betweenness_array = karate_graph.getBetweenness();
		
		for(int i = 1; i <= karate_graph.size(); i++) {
			QueueElement element = new QueueElement(String.valueOf(i), karate_graph.Dijkstra(String.valueOf(i)));
			if(element.getPriority() > karate_graph.size())    // Not counting disconnected nodes control
				Karate_Closeness.enqueue(element);
			element = new QueueElement(String.valueOf(i), karate_betweenness_array[i - 1]);
			if(element.getPriority() > karate_graph.size())    // Not counting disconnected nodes control
				Karate_Betweenness.enqueue(element);
		}
		// Printing values
		System.out.println("Zachary Karate Club Network – The Highest Node for Betweennes and the value : " + 
		Karate_Betweenness.peek().getData() + " - " + Karate_Betweenness.peek().getPriority());
		System.out.println("Zachary Karate Club Network – The Highest Node for Closeness and the value : " + 
		Karate_Closeness.peek().getData() + " - " + Karate_Closeness.peek().getPriority());
		
		
		// Getting values
		PriorityQueue Facebook_Closeness = new PriorityQueue(facebook_graph.size());
		PriorityQueueDescendingOrder Facebook_Betweenness = new PriorityQueueDescendingOrder(facebook_graph.size());
		int[] facebook_betweenness_array = facebook_graph.getBetweenness();
		
		for(int i = 1; i <= facebook_graph.size(); i++) {
			QueueElement element = new QueueElement(String.valueOf(i), facebook_graph.Dijkstra(String.valueOf(i)));
			if(element.getPriority() > facebook_graph.size())    // Not counting disconnected nodes control
				Facebook_Closeness.enqueue(element);
			element = new QueueElement(String.valueOf(i), facebook_betweenness_array[i - 1]);
			if(element.getPriority() > facebook_graph.size())    // Not counting disconnected nodes control
				Facebook_Betweenness.enqueue(element);
		}
		// Printing values
		System.out.println("Facebook Social Network - The Highest Node for Betweennes and the value  : " + 
		Facebook_Betweenness.peek().getData() + " - " + Facebook_Betweenness.peek().getPriority());
		System.out.println("Facebook Social Network – The Highest Node for Closeness and the value : " + 
		Facebook_Closeness.peek().getData() + " - " + Facebook_Closeness.peek().getPriority());
		
		// Run time on my computer : around 5 seconds
	}
}