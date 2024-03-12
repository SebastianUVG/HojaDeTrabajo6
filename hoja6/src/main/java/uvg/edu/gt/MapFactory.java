package uvg.edu.gt;

class MapFactory<K, V> {
    public Map<K, V> getMap(int mapType) {
        switch (mapType) {
            case 1:
                return new MHashMap<K,V>();
            case 2:
                return new MTreeMap<K,V>();
            case 3:
                return new LHashMap<K,V>();
            default:
                return null;
        }
    }
}