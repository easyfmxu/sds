public class UF {
    private int[] id;
    private int[] ranking;
    private int size;
    public UF(int n) {
        id = new int[n];
        ranking = new int[n];
        size = n;
        for (int i = 0; i < n; i++) {
          id[i] = i;
          ranking[i] = 0;
        }
    }
    public int find(int p) {
      while (p != id[p]) {
        id[p] = id[id[p]];
        p = id[p];
      }
      return p;
    }
    public boolean isConnected(int i, int j) {
      return find(i) == find(j);
    }
    public int size() {
      return size;
    }
    public void connect(int p, int q) {
      int i = find(p);
      int j = find(q);
      if (i == j) {
        return;
      } else if (ranking[i] < ranking[j]) {
            id[i] = j;
      } else if (ranking[i] > ranking[j]) {
        id[j] = i;
      }  else {
        id[j] = i;
        ranking[i]++;
      }
      size--;
    }
}
