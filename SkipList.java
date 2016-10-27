public class SkipList {
    //Node left, right, up, down;
    static public class SkipListNode {
      int val;
      SkipListNode left,right, up, down;
      public SkipListNode(int v) {
        val = v;
      }

    }

    SkipListNode root;
    boolean hasMax = false;
    boolean hasMin = false;

    /*
       -------++------------____________
       |     |       |     |            |
       -------______________  -----------
       |     |             |            |
       -------_________________________
     */
    public SkipList() {
      root = new SkipListNode(Integer.MAX_VALUE);
      SkipListNode tail = new SkipListNode(Integer.MIN_VALUE);

      root.right = tail;
      tail.left = root;
      total = 0;
      layer = 1;
    }
    private int total;
    private int layer;

    Random random = new Random();
    public void delete(int target) {        //may check.
         if (target == Integer.MAX_VALUE) {
           if (hasMax != false) {
             hasMax = false;
             total--;
           }
           return;
         }
         if (target == Integer.MIN_VALUE) {
           if (hasMin != false) {
             total--;
             hasMin = false;
           }
           return;
         }
      SkipListNode node = findNode(target);
      while (node != null) {
              node.left.right = node.right;
              node.right.left = node.left;
              node = node.up;
      }
    }
    public void add(int target) {   //how to generate more layer?
      //if found. ok .
      //if total size > [...];
      //fork a new layer over that.
      /*
      if (find(target)) {
        return;
      }
      */
      SkipListNode insertPoint = findInsertPoint(target);
      if(insertPoint == null) {
        return;
      }
      total++;
      if (target == Integer.MAX_VALUE) {
        hasMax = true;
        return;
      }
      if (target == Integer.MIN_VALUE) {
        hasMin = true;
        return;
      }
      //update layer    need kind of lock...
      if (total > 1 << layer) {
          SkipListNode newRoot = new SkipListNode(Integer.MAX_VALUE);
          SkipListNode newTail = new SkipListNode(Integer.MIN_VALUE);
          newRoot.left = newTail;
          newTail.right = newRoot;
          newRoot.up = root;
          root.down = newRoot;
          root = newRoot;
          layer++;
      }

      SkipListNode right = insertPoint.right;
      SkipListNode cur = new SkipListNode(target);
      insertPoint.right = cur;
      cur.right = right;
      cur.left = insertPoint;
      right.left = cur;

      boolean copy = random.nextBoolean();
      SkipListNode pre = cur;
      while (insertPoint != null && copy) {
        while (insertPoint.down == null) {
          insertPoint = insertPoint.left;
        }
        insertPoint = insertPoint.down;

        right = insertPoint.right;

        cur = new SkipListNode(target);

        insertPoint.right = cur;
        cur.right = right;
        cur.left = insertPoint;
        right.left = cur;

        pre.down = cur;
        cur.up = pre;
        pre = cur;
      }

    }

    private SkipListNode findInsertPoint(int target) {
      SkipListNode cur = root;
      SkipListNode insert = root;
      while (cur != null) {
        if(cur.val == target) {
          return null;
        } else if (cur.val < target)  {
          cur = cur.right;
        }  else {        //cur.val > target, pre <target
          cur = cur.left.up;
          insert = cur.left;//
        }
      }
      return insert;
    }
    public SkipListNode findNode(int target) {
       //SkipListNode pre = null;
       SkipListNode cur = root;
       while (cur != null) {
         if(cur.val == target) {
           return cur;
         } else if (cur.val < target)  {
           cur = cur.left.right;
         }  else {
           cur = cur.left.up;
         }
       }
       return null;
    }
    public boolean find(int target) {
       if (target == Integer.MAX_VALUE) {
           return hasMax;
       }
       if (target == Integer.MIN_VALUE) {
         return hasMin;
       }
       SkipListNode node = findNode(target);
       return node != null;
    }

    //
  }

 /*
 
    public class SkipNode {
        int val;
        SkipNode right, down;
        public SkipNode(int x) { val = x;}
    }
    //possibility random(x) -->
    public class SkipList {
        //
        public boolean find (int val) {return false;}
        public void insert(int val) {/*from the top. find a place a < x < b   down... last level and then add and go back add. */};
        public void delete(int val) {/*from the top , fid a place a < x < b  down, and delete and delete up.*/};

    }
*/
