import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CacheLRUTest {

    @Test
    public void testPutAndGet() {
        CacheLRU<String, String> cache = new CacheLRU<>(3);

        cache.put("1", "A");
        cache.put("2", "B");
        cache.put("3", "C");

        assertEquals("A", cache.get("1")); // Chave "1" tem o valor "A"
        assertEquals("B", cache.get("2"));
        assertEquals("C", cache.get("3"));
    }

    @Test
    public void testEvictionOrderLRU() {
        CacheLRU<String, String> cache = new CacheLRU<>(3);

        cache.put("1", "A");
        cache.put("2", "B");
        cache.put("3", "C");
        
        cache.get("1");
        
        cache.put("4", "D");
        
        assertNull(cache.get("2"));       // Deve ter sido removido
        assertEquals("A", cache.get("1")); // O valor de "1" deve ser "null", pois foi acessado recentemente
        assertEquals("C", cache.get("3"));
        assertEquals("D", cache.get("4"));
        assertEquals(3, cache.size());    // A capacidade da cache deve ser 3
    }

    @Test
    public void testRemoveKey() {
        CacheLRU<Integer, String> cache = new CacheLRU<>(2);

        cache.put(1, "One");
        cache.put(2, "Two");

        cache.remove(1);

        assertNull(cache.get(1));         // Verifica se a chave 1 foi removida
        assertEquals("Two", cache.get(2)); // Verifica se a chave 2 ainda está na cache
        assertEquals(1, cache.size());    // Verifica o tamanho da cache
    }
}
