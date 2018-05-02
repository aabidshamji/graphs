package cities; 

import java.util.*;


public class solution {
	/*
	 * @param n, the number of nodes in the graph 
	 * @param m, the number of roads
	 * @param c_lib, the cost of building a library 
	 * @param c_road, the cost of building a road 
	 * @param discovered, a boolean array that is true when the nodes has been discovered 
	 * @param adjList, the graph represented as an adj list 
	 * 
	 * @returns, cost, a long that represents the minimum cost of giving each city access to a library 
	 */
    public static long RoadsAndLibraries (int n, int m, long c_lib, long c_road, boolean[] discovered, ArrayList<ArrayList<Integer>> adjList) {
        long cost = 0; 
        for (int i = 0; i < adjList.size(); i++) {
            if (!discovered[i]) {
                long edges = dfs(i, 1, discovered, adjList);
                cost += c_lib; 
                if (c_lib < c_road) {
                    cost = cost + ((edges - 1) * c_lib); 
                } else {
                    cost = cost + ((edges - 1) * c_road); 
                }
            }
        }

        return cost; 
    }
    
    /*
     * Performs depth first search from the given node to return the number of road that are needed to be built 
     * @param v, the node to start dfs from 
     * @param count, the value to return 
     * @param discovered, a boolean array that is true when the nodes has been discovered 
     * @param adjList, adjList, the graph represented as an adj list
     * 
     * @return count, the number of roads that are in that sub-graph 
     */
    public static long dfs (int v, long count, boolean[] discovered, ArrayList<ArrayList<Integer>> adjList) {
    	discovered[v] = true; 
        ArrayList<Integer> arr = adjList.get(v);
        for (Integer vertex : arr) {
            if (!discovered[vertex]) {
                count = 1 + dfs(vertex, count, discovered, adjList);
            }
        }
        return count; 
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt();
            int m = in.nextInt();
            long c_lib = in.nextLong();
            long c_road = in.nextLong();

            ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(); 

            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<Integer>());
            } 
            
            boolean[] discovered = new boolean[(int) n];
            
            for (int j = 0; j < m; j++) {
                int node1 = in.nextInt();
                int node2 = in.nextInt();
                adjList.get(node1 - 1).add(node2 - 1);
                adjList.get(node2 - 1).add(node1 - 1);
            }


            long result = RoadsAndLibraries(n, m, c_lib, c_road, discovered, adjList);
            System.out.println(result);
        }
        in.close();
    }
}