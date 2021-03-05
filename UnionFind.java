public class UnionFind {
    private int size;
    private int no_of_components;
    private int[] elems_root_id;
    private int[] component_size;
    
    public UnionFind(int size) {
        if(size <= 0) return;
        
        this.size = no_of_components = size;
        elems_root_id = new int[size];
        component_size = new int[size];
        
        for(int i = 0; i < size; i++) {
            elems_root_id[i] = i;
            component_size[i] = 1;
        }
    }
    
    public int find_root_of(int index) {
        if(elems_root_id[index] == index) return index;
        int root = index;
        while(root != elems_root_id[root]) {
            root = elems_root_id[root];
        }
        while(index != root) {
            int next_elem = elems_root_id[index];
            elems_root_id[index] = root;
            index = next_elem;
        }
        return root;
    }
    
    public void merge(int index1, int index2) {
        int root1 = find_root_of(index1);
        int root2 = find_root_of(index2);
        
        if(root1 == root2) return;
        
        if(component_size[root1] < component_size[root2]){
            component_size[root2] += component_size[root1];
            elems_root_id[root1] = root2;
        }
        else {
            component_size[root1] += component_size[root2];
            elems_root_id[root2] = root1;
        }
      no_of_components--;  
    }
    
    public void display() {
        for(int i = 0; i < size; i++){
            System.out.println(elems_root_id[i]);
        }
    }
}