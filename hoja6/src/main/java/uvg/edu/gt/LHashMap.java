package uvg.edu.gt;



import java.util.LinkedHashMap;
import java.util.Set;
import java.util.function.BiConsumer;

class LHashMap<K, V> implements Map<K,V>{

	private LinkedHashMap<K,V> linkedHashMap = new LinkedHashMap<K,V>();

	@Override
	public V put(K key, V value) {
		V val = linkedHashMap.put(key, value);
		return val;
	}

	@Override
	public boolean containsKey(K key) {
		return linkedHashMap.containsKey(key);
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super V> action) {
		linkedHashMap.forEach(action);
	}

	@Override
	public V get(K key) {
		return linkedHashMap.get(key);
	}

	@Override
	public Set<java.util.Map.Entry<K,V>> entrySet() {
		return linkedHashMap.entrySet();
	}
	

}