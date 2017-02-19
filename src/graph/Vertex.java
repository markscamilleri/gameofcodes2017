package graph;

public class Vertex {
        private final String name;
        
        public Vertex(String name) {
            this.name = name;
        }
    
        public String getName() {
            return name;
        }
        
        @Override
        public boolean equals(Object o) {
            return o instanceof Vertex && name.equals(((Vertex) o).getName());
        }
    
        @Override
        public String toString() {
            return this.name;
        }
    }