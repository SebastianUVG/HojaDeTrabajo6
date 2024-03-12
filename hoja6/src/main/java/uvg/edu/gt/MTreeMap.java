package uvg.edu.gt;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;

class MTreeMap<K, V> implements Map<K,V>{

	private TreeMap<K,V> treeMap = new TreeMap<K,V>();

	@Override
	public V put(K key, V value) {
		V val = treeMap.put(key, value);
		return val;
	}

	@Override
	public boolean containsKey(K key) {
		return treeMap.containsKey(key);
	}

	@Override
	public V get(K key) {
		return treeMap.get(key);
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super V> action) {
		treeMap.forEach(action);
	}

	@Override
	public Set<java.util.Map.Entry<K,V>> entrySet() {
		return treeMap.entrySet();
	}

}