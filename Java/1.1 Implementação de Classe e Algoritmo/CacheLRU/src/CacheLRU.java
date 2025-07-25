import java.util.LinkedHashMap;  
import java.util.Map;  

public class CacheLRU<K, V> {
    
    private final int capacity; 
    private final Map<K, V> cache; 
    
    public CacheLRU(int capacity) {
        this.capacity = capacity;  
        
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true); 
    }
    
    public void put(K key, V value) {
        
        if (cache.size() >= capacity) {
            K eldestKey = cache.entrySet().iterator().next().getKey(); 
            cache.remove(eldestKey); 
        }
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.getOrDefault(key, null);
    }
    
    public void remove(K key) {
        cache.remove(key);
    }
    
    public int size() {
        
        return cache.size();
    }
    
    public static void main(String[] args) {
        
        CacheLRU<String, String> cache = new CacheLRU<>(3);

        // Inserindo 3 pares chave-valor na cache
        cache.put("1", "A");  // Adiciona a chave "1" com o valor "A"
        cache.put("2", "B");  // Adiciona a chave "2" com o valor "B"
        cache.put("3", "C");  // Adiciona a chave "3" com o valor "C"
        
        System.out.println(cache.get("1")); // A
        
        cache.put("4", "D");  // A chave "2" será removida porque foi a menos recentemente usada
        
        System.out.println(cache.get("2")); // null
        
        System.out.println(cache.size());   // 3
    }
}
