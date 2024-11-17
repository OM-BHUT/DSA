package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Q1 {
    public static void main(String[] args) {
        Graph g1 = new Graph();
        int V = 7;
//        System.out.println("hello");
        ArrayList<Edge>[] graph = new ArrayList[V];
        g1.createGraph(graph);
        boolean[] visited = new boolean[V];
//        g1.bfs(graph,V);
//        g1.dfs(graph,V);
//        System.out.println(g1.cycleDetection(graph,V));
//        System.out.println(g1.hasPath(graph,V,6));
//        System.out.println(g1.isBipartite(graph,V));
        System.out.println(g1.isBipartite2(graph,V));
    }
}

class Graph{
    void createGraph(ArrayList<Edge>[] graph) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));
        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 3, 1));
        graph[2].add(new Edge(2, 0, 1));
        graph[2].add(new Edge(2, 4, 1));
        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[3].add(new Edge(3, 5, 1));
        graph[4].add(new Edge(4, 2, 1));
        graph[4].add(new Edge(4, 3, 1));
        graph[4].add(new Edge(4, 5, 1));
        graph[5].add(new Edge(5, 3, 1));
        graph[5].add(new Edge(5, 4, 1));
        graph[5].add(new Edge(5, 6, 1));
        graph[6].add(new Edge(6, 5, 1));
        //new graph
//        graph[0].add(new Edge(0,1,1));
//        graph[0].add(new Edge(0,2,1));
//        graph[1].add(new Edge(1,0,1));
//        graph[1].add(new Edge(1,3,1));
//        graph[2].add(new Edge(2,0,1));
//        graph[2].add(new Edge(2,3,1));
//        graph[3].add(new Edge(3,1,1));
//        graph[3].add(new Edge(3,2,1));

    }

    public void bfs(ArrayList<Edge>[] graph,int V){
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]){
                bfsHelper(graph,V,visited,i);
            }
        }
    }
    private void bfsHelper(ArrayList<Edge>[] graph,int V,boolean[] visited,int start){
        Queue<Integer> q1 = new LinkedList<>();
        q1.add(start);
        visited[start] = true;
        while (!q1.isEmpty()){
            int curr = q1.poll();
            System.out.print(curr+" ");
            ArrayList<Edge> vertex = graph[curr];
            for (int i = 0; i < vertex.size(); i++) {
                int dest = vertex.get(i).dest;
                if (!visited[dest]){
                    visited[dest] = true;
                    q1.add(dest);
                }
            }
        }
    }
    public void dfs(ArrayList<Edge>[] graph,int V){
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]){
                dfs(graph,V,i,visited);
            }
        }
    }
    private void dfs(ArrayList<Edge>[] graph,int V,int curr,boolean[] visited){
        System.out.print(curr+" ");
        visited[curr] = true;
        ArrayList<Edge> vertex = graph[curr];
        for (int i = 0; i < vertex.size(); i++) {
            int dest = vertex.get(i).dest;
            if(!visited[dest]){
                dfs(graph,V,dest,visited);
            }
        }
    }
    public boolean hasPath(ArrayList<Edge>[] graph,int V,int dest){
        boolean[] visited = new boolean[V];
        return hasPath(graph,V,0,dest,visited);
    }
    private boolean hasPath(ArrayList<Edge>[] graph,int V,int curr,int dest,boolean[] visited){
        if (curr==dest) return true;
        visited[curr] = true;
        boolean check = false;
//        ArrayList<Edge> vertex = graph[curr];
        for (Edge edge:graph[curr]) {
            int neighbour = edge.dest;
            if (!visited[neighbour]){
                check = hasPath(graph,V,neighbour,dest,visited);
            }
        }
        return check;
        }
        boolean cycleDetection(ArrayList<Edge>[] graph,int V){
            boolean[] visited = new boolean[V];
            return cycleDetection(graph,V,visited,0,0);
        }
        private boolean cycleDetection(ArrayList<Edge>[] graph,int V,boolean[] visited,int curr,int parent){
            visited[curr] = true;
            boolean check = false;
            for (Edge edge : graph[curr]){
                if (visited[edge.dest] && parent!=edge.dest){
                    return true;
                }
                if (!visited[edge.dest] && cycleDetection(graph,V,visited,edge.dest,curr)){
//                    check = cycleDetection(graph,V,visited,edge.dest,curr);
                    return true;
                }
            }
            return false;
        }
        boolean isBipartite(ArrayList<Edge>[] graph,int V){
            int[] color = new int[V];
            Arrays.fill(color,-1);
            Queue<Integer> q1 = new LinkedList<>();
            q1.add(0);
            color[0] = 0;
            while (!q1.isEmpty()){
                int curr = q1.poll();
                for(Edge edge:graph[curr]){
                    if (color[curr]==color[edge.dest]){
                        return false;
                    }
                    if (color[edge.dest] == -1){
                        color[edge.dest] = color[curr] == 0 ? 1 : 0;
                        q1.add(edge.dest);
                    }
                }
            }
            return true;
        }
        
        boolean isBipartite2(ArrayList<Edge>[] graph,int V){
            if (!cycleDetection(graph,V)){
                return true;
            }
             return V % 2 == 0;

        }
        private int sizeOfGraph(ArrayList<Edge>[] graph,int V){
            int count = 0;
            for (int i = 0; i < V; i++) {
                for (Edge edge:graph[i]){
                    count++;
                }
            }

            System.out.println(count);
            return count;
        }
    }



class Edge{
    int src;
    int dest;
    int weight;
    public Edge(int src,int dest,int weight){
        this.src=src;
        this.dest=dest;
        this.weight=weight;
    }
}