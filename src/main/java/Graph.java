import java.util.*;

public class Graph <V>{
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    public boolean addVertex(V v){
        if(!adjacencyList.containsKey(v)){
            adjacencyList.put(v, new HashSet<V>());
            return true;
        }
        return false;
    }

    public boolean addEdge(V v1, V v2, double v){
        addVertex(v1);
        addVertex(v2);

        Set<V> adyacents = adjacencyList.get(v1);
        if (adyacents.contains(v2)){
            return false;
        }
        adyacents.add(v2);
        return true;
    }

    public Set<V> obtainAdjacents(V v) throws Exception{
        if(!adjacencyList.containsKey(v)){
            throw new Exception("El vértice no existe");
        }
        return adjacencyList.get(v);
    }

    public boolean containsVertex(V v){
        return adjacencyList.containsKey(v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(V vertice : adjacencyList.keySet()){
            sb.append(vertice.toString());
            sb.append(": ");
            sb.append(adjacencyList.get(vertice).toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public List<V> onePath(V v1, V v2) throws Exception{
        Map<V, V> trace = new HashMap<>();
        Stack<V> open = new Stack<>();
        open.push(v1);
        trace.put(v1, null);
        boolean found = false;

        while(!open.isEmpty() && !found){
            V v = open.pop();
            found = v.equals(v2);
            if(found){
                break;
            }
                for(V s : obtainAdjacents(v)){
                    if(!trace.containsKey(s)){
                        open.push(s);
                        trace.put(s, v);
                    }
                }
            }

            if (found){
                List<V> path = new ArrayList<>();
                V current = v2;
                while(current != null){
                    path.add(0, current);
                    current = trace.get(current);
                }
                return path;
            }else{
                return null;
            }
        }

        public List<V> depthFirstSearch(V startV){
        List<V> visited = new ArrayList<>();
        Stack<V> stack = new Stack<>();
        stack.push(startV);

        while(!stack.empty()){
            V v = stack.pop();
            if(!visited.contains(v)){
                visited.add(v);
                for(V vecino : adjacencyList.get(v)){
                    if(!visited.contains(vecino)){
                        stack.push(vecino);
                    }
                }
            }
        }
return visited;

    }

    public List<V> shortestPath(V v1, V v2) throws Exception{
        Map<V, V> trace = new HashMap<>();
        Queue<V> open = new LinkedList<>();
        open.add(v1);
        trace.put(v1, null);
        boolean found = false;

        while(!open.isEmpty() && !found){
            V v = open.poll();
            found = v.equals(v2);
            if(found){
                break;
            }
            for(V s : obtainAdjacents(v)){
                if(!trace.containsKey(s)){
                    open.add(s);
                    trace.put(s, v);
                }
            }
        }
        if (found){
            List<V> path = new ArrayList<>();
            V curr = v2;
            while(curr != null){
                path.add(0, curr);
                curr = trace.get(curr);
            }
            return path;
        }else {
            return null;
        }

    }

    public Barco[] getVertex(){
        Barco[] barcos = new Barco[adjacencyList.size()];
        int i = 0;
        for(V vertice : adjacencyList.keySet()){
            barcos[i++] = (Barco)vertice;
        }
        return barcos;
    }

    public void removeVertex(V max){
        adjacencyList.remove(max);
        for(V vertice : adjacencyList.keySet()){
            adjacencyList.get(vertice).remove(max);
        }
    }

    public static void pintarGraph(Graph grafo) throws Exception{
        for(int i = 0; i < grafo.getVertex().length; i++){
            System.out.println(grafo.getVertex()[i].toString() + " -> " + grafo.obtainAdjacents(grafo.getVertex()[i]).toString());
        }
    }
}
