package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Q1 {
    public static void main(String[] args) {
        Graph g1 = new Graph();
        int V = 9;
//        System.out.println("hello");
        ArrayList<Edge>[] graph = new ArrayList[V];
        g1.createGraph(graph);
//        g1.bfs(graph,V);
        g1.dfs(graph,V);

//        System.out.println(g1.hasPath(graph,V,6));
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
//        graph[6].add(new Edge(6, 7, 1));
//        graph[6].add(new Edge(6, 8, 1));
//        graph[7].add(new Edge(7, 6, 1));
//        graph[8].add(new Edge(8, 6, 1));
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

    }
//    private void bfsHelper(ArrayList<Edge>[] graph,int V,boolean[] visited){
////        visited[0] = true;
////        System.out.println(0);
////        ArrayList<Edge> first = graph[0];
//        System.out.println("bfsHelper");
//        Queue<Integer> q1 = new LinkedList<>();
//        q1.add(graph[0].getFirst().src);
//        while (!q1.isEmpty()){
//            int curr = q1.poll();
//            visited[curr] = true;
//            System.out.println(curr+" ");
//            ArrayList<Edge> vertex = graph[curr];
//            for (int i = 0; i < vertex.size(); i++) {
//                int dest = vertex.get(i).dest;
//                if (!visited[dest]){
//                    q1.add(dest);
//                }
//            }
//        }
//    }
//}


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