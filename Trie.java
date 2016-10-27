class TrieNode {
    // Initialize your data structure here.
    //final char c;
    final TrieNode[] children = new TrieNode[26];
    boolean isWord;
    /*
    public TrieNode( char c) {
        this.c = c;
    }
    */
}

public class Trie {
    private final  TrieNode root = new TrieNode();

    public Trie() {
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null) {
            return;
        }
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode child = cur.children[c - 'a'];
            if (child == null) {
                child = new TrieNode();
                cur.children[c - 'a'] = child;
            }
            cur = child;
        }
        cur.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode res = searchNode(word);
        return res != null && res.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }
    private TrieNode searchNode(String str) {
        if(str == null) {
            return null;
        }
        TrieNode cur = root;
        for (int i = 0; i < str.length(); i++) {
            TrieNode child = cur.children[str.charAt(i) - 'a'];
            if (child == null) {
                return null;
            }
            cur = child;
        }
        return cur;
    }
}

