package slowpokeflash;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ListTests {

    private CreateLists create = new CreateLists();

    private LinkedList randomSimpleList() {
        return create.createSimpleList((int) (Math.random() * 9000 + 1));
    }

    private LinkedList randomLoopedList() {
        int randomLength = (int) (Math.random() * 9000 + 1);
        return create.loopList(create.createSimpleList(randomLength), (int) (Math.random() * randomLength + 1));
    }

    /* testing isNull */

    @Test
    void isNull_ReturnsTrue_IfListIsNull() {
        LinkedList nullList = null;

        assertTrue(LinkedList.isNull(nullList));
    }

    @Test
    void isNull_ReturnsFalse_IfListIsNotNull() {

        assertFalse(LinkedList.isNull(randomSimpleList()));
    }

    /* testing getLength */

    @Test
    void getLength_ShouldGetTheRightLength_WithGeneratedList() {
        LinkedList threeElemList = create.createSimpleList(3);

        assert LinkedList.getLength(threeElemList) == 3;
    }

    @Test
    void getLength_ThrowsIllegalArgumentException_IfListIsLooped() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            LinkedList.getLength(randomLoopedList());
        });

        assertEquals("List is looped, therefore has no length!", exception.getMessage());
    }

    /* testing hasNext */

    @Test
    void hasNext_ReturnsTrue_IfListHasTwoElements() {
        LinkedList twoElemList = create.createSimpleList(2);

        assertTrue(LinkedList.hasNext(twoElemList.head));
    }

    @Test
    void hasNext_ReturnsFalse_IfListHasOneElement() {
        LinkedList singleElemList = create.createSimpleList(0);

        assertFalse(LinkedList.hasNext(singleElemList.head));
    }

    @Test
    void hasNextNext_ReturnsTrue_IfListHasThreeElements() {
        LinkedList threeElemList = create.createSimpleList(3);

        assertTrue(LinkedList.hasNextNext(threeElemList.head));
    }

    @Test
    void hasNextNext_ReturnsFalse_IfListHasOneElement() {
        LinkedList singleElemList = create.createSimpleList(0);

        assertFalse(LinkedList.hasNextNext(singleElemList.head));
    }

    /* testing getLoopPoint */

    @Test
    void getLoopPoint_ReturnsLoopPoint_IfListIsLooped() {
        LinkedList autoList = create.createSimpleList(300);
        autoList = create.loopList(autoList, 33);

        assert LinkedList.getLoopPoint(autoList) == 33;
    }

    @Test
    void getLoopPoint_ThrowsIllegalArgumentException_IfListIsNotLooped() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            LinkedList.getLoopPoint(randomSimpleList());
        });

        assertEquals("List is not looped!", exception.getMessage());
    }

    /* testing isLooped */

    @Test
    void isLooped_ReturnsTrue_IfListIsLooped_Randomized() {

        assertTrue(LinkedList.isLooped(randomLoopedList()));
    }

    @Test
    void isLooped_ReturnsTrue_IfListIsLooped_Manually() {
        LinkedList autoList = create.createSimpleList(5);
        autoList = create.loopList(autoList, 2);

        assertTrue(LinkedList.isLooped(autoList));
    }

    @Test
    void isLooped_ReturnsTrue_IfListIsLoopedToHead_Manually() {
        LinkedList autoList = randomSimpleList();
        autoList = create.loopList(autoList, 0);

        assertTrue(LinkedList.isLooped(autoList));
    }

    @Test
    void isLooped_SetsLoopPointToZero_IfLoopToPositionIsGreaterThanLength() {
        LinkedList autoList = create.createSimpleList(5);
        autoList = create.loopList(autoList, 10);

        assert LinkedList.getLoopPoint(autoList) == 0;
    }

    @Test
    void isLooped_ReturnsFalse_IfListIsNotLooped_Randomized() {

        assertFalse(LinkedList.isLooped(randomSimpleList()));
    }

    @Test
    void isLooped_ReturnsTrue_IfListIsLooped_Primitive() {

        LinkedList primitiveList = new LinkedList();

        primitiveList.head = new LinkedList.Node(1);
        LinkedList.Node two = new LinkedList.Node(2);
        LinkedList.Node three = new LinkedList.Node(3);
        LinkedList.Node four = new LinkedList.Node(4);
        LinkedList.Node five = new LinkedList.Node(5);

        primitiveList.head.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = three;

        assertTrue(LinkedList.isLooped(primitiveList));
    }

}
