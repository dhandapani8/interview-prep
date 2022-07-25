class Dijkstra {
  public int[] shortestPath(int[][] edges, int n, int source) {
    int[] dist = new int[n];
    for(int i=0; i<n; i++) {
        dist[i] = Integer.MAX_VALUE;
    }

    Map<Integer, List<Edge>> adj = new HashMap<>();

    createAdj(edges, adj);
    dijkstra(adj, dist, k, n);
    
    return dist;
  }
  
  public void createAdj(int[][] edges, Map<Integer, List<Edge>> adj) {
      for(int i=0; i<edges.length; i++) {
          adj.putIfAbsent(edges[i][0], new ArrayList<>());
          adj.get(edges[i][0]).add(new Edge(edges[i][1], edges[i][2]));
      }
  }
    
  public void dijkstra(Map<Integer, List<Edge>> adj, int[] dist, int source, int n) {
      PriorityQueue<Edge> pq = new PriorityQueue<>();
      boolean[] visited = new boolean[n];
      pq.add(new Edge(source, 0));
      dist[source] = 0;

      while(!pq.isEmpty()) {
          Edge top = pq.poll();
          visited[top.v] = true;
          for(Edge edge: adj.getOrDefault(top.v, new ArrayList<>())) {
              if(visited[edge.v]) continue;

              int w = dist[top.v] + edge.w;
              if(w < dist[edge.v]) {
                  dist[edge.v] = w;
                  pq.add(new Edge(edge.v, w));
              }
          }
      }
  }
}

class Edge implements Comparable<Edge> {
    int v;
    int w;
    
    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }
    
    @Override
    public int compareTo(Edge o) {
        return w - o.w;
    }
}
