import Constants;

import java.util.*;
import java.io.*;

public class Day6 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new FileReader(Constants.FILE_PATH + "day6")));
        List<Orbit> input = new ArrayList<Orbit>();
        Map<String, ArrayList<String>> orbits = new HashMap<String, ArrayList<String>>();
        while (in.hasNextLine()) {
            String[] line = in.nextLine().split("\\)");
            input.add(new Orbit(line[0], line[1]));
        }

        // for(Orbit orbit: input) {
        // if(!orbits.containsKey(orbit.a))
        // orbits.put(orbit.a, orbit.b);
        // }

        for (Orbit orbit : input) {

            for (Map.Entry<String, ArrayList<String>> orbitEntry : orbits.entrySet()) {
                System.out.println("a: " + orbitEntry.getKey() + ", b: " + orbitEntry.getValue());
            }

            if (!orbits.containsKey(orbit.a)) {
                orbits.put(orbit.a, new ArrayList<String>() {
                    {
                        add(orbit.b);
                        if (orbits.containsKey(orbit.b))
                            addAll(orbits.get(orbit.b));
                    }
                });
            } else {
                ArrayList<String> existingOrbits = orbits.get(orbit.a);
                existingOrbits.add(orbit.b);
                if (orbits.containsKey(orbit.b))
                    existingOrbits.addAll(orbits.get(orbit.b));
                orbits.put(orbit.a, existingOrbits);
            }
            for (Map.Entry<String, ArrayList<String>> orbitEntry : orbits.entrySet()) {
                if (orbitEntry.getValue().contains(orbit.a) && !orbitEntry.getValue().contains(orbit.b)) {
                    ArrayList<String> newValues = orbitEntry.getValue();
                    if (orbits.containsKey(orbit.b))
                        newValues.addAll(orbits.get(orbit.b));
                    newValues.add(orbit.b);
                    orbits.replace(orbitEntry.getKey(), newValues);
                }
            }
            System.out.println();
        }

        int totalOrbits = 0;
        for (Map.Entry<String, ArrayList<String>> orbitEntry : orbits.entrySet()) {
            totalOrbits += orbitEntry.getValue().size();
            System.out.println("a: " + orbitEntry.getKey() + ", b: " + orbitEntry.getValue());
        }

        System.out.println(totalOrbits);

        in.close();
    }

    private static class Orbit {
        String a, b;

        Orbit(String a, String b) {
            this.a = a;
            this.b = b;
        }
    }
}

class Vertex {
    final private String id;
    final private String name;

    public Vertex(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}

class Edge {
    private final String id;
    private final Vertex source;
    private final Vertex destination;
    private final int weight;

    public Edge(String id, Vertex source, Vertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }

}

class Graph {
    private final List<Vertex> vertexes;
    private final List<Edge> edges;

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

}

class DijkstraAlgorithm {

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(Vertex source) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and NULL
     * if no path exists
     */
    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}