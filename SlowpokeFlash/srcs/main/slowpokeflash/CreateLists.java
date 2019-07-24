package slowpokeflash;

class CreateLists {

    /* creates a simple list with preferred length */
    LinkedList createSimpleList(int length) {

        LinkedList newList = new LinkedList();
        newList.head = new LinkedList.Node(0);
        LinkedList.Node elem = newList.head;

        // add elements
        for (int i = 1; i < length; i++) {
            elem.next = new LinkedList.Node(i);
            elem = elem.next;
        }

        return newList;
    }

    /* loops the list to the preferred position */
    LinkedList loopList(LinkedList list, int loopToPosition) {
        LinkedList.Node elem = list.head;
        LinkedList.Node loopPoint = null;
        int length = LinkedList.getLength(list);

        // position to loop to cannot be greater than list length!
        if (loopToPosition > length) {
            loopToPosition = 0;
        }

        // search & set point to loop to & set elem to end point
        for (int i = 0; i < length - 1; i++) {
            if (i == loopToPosition) {
                loopPoint = elem;
            }
            elem = elem.next;
        }

        elem.next = loopPoint;

        return list;
    }
}
