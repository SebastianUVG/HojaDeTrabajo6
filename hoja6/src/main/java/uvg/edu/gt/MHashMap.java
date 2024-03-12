package uvg.edu.gt;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.HashMap;

class MHashMap<K, V> implements Map<K,V> {

	private HashMap<K,V> hashMap = new HashMap<K,V>();

	@Override
	public V put(K key, V value) {
		V val = hashMap.put(key, value);
		return val;
	}

	@Override
	public boolean containsKey(Object arg0) {
		return hashMap.containsKey(arg0);
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super V> action) {
		hashMap.forEach(action);
	}

	@Override
	public Set<java.util.Map.Entry<K,V>> entrySet() {
		return hashMap.entrySet();
	}

	@Override
	public V get(K key) {
		return hashMap.get(key);
	}

}