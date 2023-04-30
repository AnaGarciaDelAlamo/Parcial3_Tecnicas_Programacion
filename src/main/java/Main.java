import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Barco battleship = new Barco("Battleship", 4, 0, 0);
        Barco canoe = new Barco("Canoe", 1, 0, 1);
        Barco frigate = new Barco("Frigate", 3, 0, 2);

        Graph<Barco> g = new Graph<>();
        g.addVertex(battleship);
        g.addVertex(canoe);
        g.addVertex(frigate);

        g.addEdge(battleship, canoe, distanciaEntreBarcos(battleship, canoe));
        g.addEdge(battleship, frigate, distanciaEntreBarcos(battleship, frigate));
        g.addEdge(canoe, frigate, distanciaEntreBarcos(canoe, frigate));


        System.out.println("Grafo: ");
        Graph.pintarGraph(g);

        System.out.println("Recorrido en profundidad: ");
        try{
            List<Barco> recorrido = g.depthFirstSearch(battleship);
            for(Barco barco : recorrido ){
                System.out.println(barco.getNombre());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("Camino más corto: ");
        try{
            List<Barco> camino = g.shortestPath(battleship, frigate);
            for(Barco barco : camino){
                System.out.println(barco.getNombre());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Barco maxAristas = null;
        int aristas = -1;
        for(Barco barco : g.getVertex()){
            int aristasBarco = g.obtainAdjacents(barco).size();
            if (aristasBarco > aristas){
                aristas = aristasBarco;
                maxAristas = barco;
            }
        }
        System.out.println("Barco con más aristas: " + maxAristas.getNombre());
        g.removeVertex(maxAristas);
    }

    public static double distanciaEntreBarcos(Barco b1, Barco b2){
        double distancia = Math.sqrt(Math.pow(b1.getPosX() - b2.getPosX(), 2) + Math.pow(b1.getPosY() - b2.getPosY(), 2));
        return distancia;
    }
}
