package cities;
// BFS and Graph classes from https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class solution {

	public static class Graph {
		private int V; // No. of vertices
		private LinkedList<Integer> adj[]; // Adjacency Lists

		// Constructor
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Graph(int v) {
			V = v;
			adj = new LinkedList[v];
			for (int i = 0; i < v; ++i)
				adj[i] = new LinkedList();
		}

		// Function to add an edge into the graph
		void addEdge(int v, int w) {
			adj[v].add(w);
			// System.out.println("v = " + v);
			// System.out.println("w = " + w);
		}

		// prints BFS traversal from a given source s
		int BFS(int s, HashSet<Integer> v) {
			int count = 0;
			// System.out.println("s = " + s);
			// Mark all the vertices as not visited(By default
			// set as false)
			boolean visited[] = new boolean[V];

			// Create a queue for BFS
			LinkedList<Integer> queue = new LinkedList<Integer>();

			// Mark the current node as visited and enqueue it
			visited[s] = true;
			queue.add(s);

			while (queue.size() != 0) {
				// Dequeue a vertex from queue and print it
				s = queue.poll();
				// System.out.print(s + " ");
				v.add(s);
				// System.out.println(v.toString());

				// Get all adjacent vertices of the dequeued vertex s
				// If a adjacent has not been visited, then mark it
				// visited and enqueue it
				Iterator<Integer> i = adj[s].listIterator();
				while (i.hasNext()) {
					int n = i.next();
					if (!visited[n]) {
						visited[n] = true;
						queue.add(n);
						v.add(n);
						// System.out.println("n = " + n);
						count++;
					}
				}
			}
			// System.out.println("visited = " + Arrays.toString(visited)); 
			// System.out.println("count = " + count);
			//System.out.println("in BFS...");
			return count;
		}
	}

	static int roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
		if (c_lib <= c_road) {
			return n * c_lib;
		}
		HashSet<Integer> v = new HashSet<Integer>();
		Graph g = new Graph(n);
		// System.out.println(cities.length);
		// System.out.println("n " + n);
		// System.out.println("g " + g.V);
		for (int i = 0; i < cities.length; i++) {
			g.addEdge(cities[i][0] - 1, cities[i][1] - 1);
		}
		int cost = 0;
		for (int i = n-1; i >= 0; i--) {
			if (!v.contains(i)) {
				//System.out.println("Calling BFS");
				//System.out.println(v.toString());
				//System.out.println(i);
				cost += (g.BFS(i, v) * c_road) + c_lib;
				//System.out.println("After BFS");
				//System.out.println(v.toString());
			}
		}
		return cost;
	}

	// Declare a linked list of nodes
	// while size of linkedList < no. cities
	// Check if each linked list contains a vertex
	// If not, recurse (bfs) on missing vertex
	// Return this as a linked list

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		for (int a0 = 0; a0 < q; a0++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int c_lib = in.nextInt();
			int c_road = in.nextInt();
			int[][] cities = new int[m][2];
			for (int cities_i = 0; cities_i < m; cities_i++) {
				for (int cities_j = 0; cities_j < 2; cities_j++) {
					cities[cities_i][cities_j] = in.nextInt();
				}
			}
			int result = roadsAndLibraries(n, c_lib, c_road, cities);
			System.out.println(result);
		}
		in.close();
	}
}
