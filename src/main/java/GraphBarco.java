import java.util.*;

public class GraphBarco {

    private Map<Barco, Set<Barco>> adjacencyList = new HashMap<>();

    public boolean addVertex(Barco v){
        if(!adjacencyList.containsKey(v)){
            adjacencyList.put(v, new HashSet<Barco>());
            return true;
        }
        return false;
    }

    public boolean addEdge(Barco v1, Barco v2){
        addVertex(v1);
        addVertex(v2);

        Set<Barco> adyacents = adjacencyList.get(v1);
        if(adyacents.contains(v2)){
            return false;
        }

        double dis = Math.sqrt(Math.pow(v1.getPosX() - v2.getPosX(), 2) + Math.pow(v1.getPosY() - v2.getPosY(), 2));
        adyacents.add(v2);
        adjacencyList.get(v2).add(v1);
        return true;
    }

    public Set<Barco> obtainAdjacents(Barco v) throws Exception{
        if(!adjacencyList.containsKey(v)){
            throw new Exception("El vértice no existe");
        }
        return adjacencyList.get(v);
    }

    public boolean containsVertex(Barco v){
        return adjacencyList.containsKey(v);
    }

    public void dephFirstSearch(Barco v){
        Set<Barco> visited = new HashSet<>();
        Stack<Barco> stack = new Stack<>();
        stack.push(v);

        while (!stack.isEmpty()){
            Barco curr = stack.pop();
            if(!visited.contains(curr)){
                visited.add(curr);
                System.out.println(curr.getNombre());
                for (Barco vecino : adjacencyList.get(curr)){
                    stack.push(vecino);
                }
            }
        }

    }

    public List<Barco> shortestPath(Barco s, Barco e) throws Exception{
        Map<Barco, Barco> trace = new HashMap<>();
        Map<Barco, Double> distance = new HashMap<>();
        PriorityQueue<Barco> pq = new PriorityQueue<>(Comparator.comparingDouble(distance::get));
        pq.add(s);
        distance.put(s, 0.0);

        while(!pq.isEmpty()){
            Barco curr = pq.poll();
            if(curr.equals(e)){
                break;
            }
            double currDistance = distance.get(curr);
            for(Barco vecino : obtainAdjacents(curr)){
                double newDistance = currDistance + getDistance(curr, vecino);
                if (!distance.containsKey(vecino) || newDistance < distance.get(vecino)){
                    distance.put(vecino, newDistance);
                    pq.add(vecino);
                    trace.put(vecino, curr);
                }
            }
        }
        if(!trace.containsKey(e)){
            return null;
        }

        List<Barco> path = new ArrayList<>();
        Barco curr = e;
        while(curr != null){
            path.add(curr);
            curr = trace.get(curr);
        }
        return path;
    }




    private double getDistance(Barco v1, Barco v2){
        return Math.sqrt(Math.pow(v1.getPosX() - v2.getPosX(), 2) + Math.pow(v1.getPosY() - v2.getPosY(), 2));
    }
}
