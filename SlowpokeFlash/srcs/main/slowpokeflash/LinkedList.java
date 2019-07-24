package slowpokeflash;

class LinkedList {
    Node head;

    static class Node {
        int data;
        Node next;

        Node(int pos) {
            data = pos;
            next = null;
        }
    }

    /* returns true if list is looped */
    static boolean isLooped(LinkedList list) {

        if (isNull(list)) {
            return false;
        }

        Node slowpoke = list.head;
        Node flash = list.head;

        while (hasNext(flash) & hasNextNext(flash)) {

            slowpoke = slowpoke.next;
            flash = flash.next.next;

            if (slowpoke == flash) return true;
        }

        return false;

    }

    /* returns the position where the loop begins */
    static int getLoopPoint(LinkedList list) {

        if (!isLooped(list)) {
            throw new IllegalArgumentException("List is not looped!");
        }

        int loopPoint = 0;
        Node slowpoke = list.head;
        Node flash = list.head;

        while (hasNext(flash) && hasNextNext(flash)) {

            slowpoke = slowpoke.next;
            flash = flash.next.next;

            if (slowpoke == flash) break;
        }

        // search for the loop start
        slowpoke = list.head;
        while (slowpoke != flash) {
            loopPoint++;
            slowpoke = slowpoke.next;
            flash = flash.next;
        }
        return loopPoint;
    }

    /* returns true if list is null */
    static boolean isNull(LinkedList list) {
        return list == null;
    }

    /* returns true if node has next node */
    static boolean hasNext(Node node) {
        return node.next != null;
    }

    /* returns true if node has next.next node */
    static boolean hasNextNext(Node node) {
        return hasNext(node) && node.next.next != null;
    }

    /* returns the length of the list */
    static int getLength(LinkedList list) {
        if (isLooped(list)) {
            throw new IllegalArgumentException("List is looped, therefore has no length!");
        }
        Node temp = list.head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

}
