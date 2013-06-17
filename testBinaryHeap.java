import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/*
 * Class for JUnit testing a max heap
 * It is far from complete, but a few test cases are better than none (add some)
 */
public class testBinaryHeap{
    BinaryHeap<Integer> heap=new BinaryHeap<Integer>();
    BinaryHeap<String> s_heap=new BinaryHeap<String>(new compStr());
    
    private class compStr implements Comparator<String> {
        public int compare(String a, String b){
            return a.length()-b.length();
        }
        public boolean equals(String a,String b){
            return (compare(a,b)==0);
        }   
    }
    @Before
    public void initBST(){

    }

    @Test public void testCustomComp(){
        s_heap.add("a");
        s_heap.add("ab");
        s_heap.add("abc");
        s_heap.add("abcd");
        s_heap.add("abcde");
        assertEquals((String)s_heap.peek(),"abcde");
        s_heap.remove();
        s_heap.remove();
        s_heap.remove();
        assertEquals((String)s_heap.peek(),"ab");
        s_heap.remove();
        s_heap.remove();
        assertNull(s_heap.remove());
    }
    
    @Test
    public void testEmpty(){
        assertTrue(heap.isEmpty());
        heap.add(null);
        assertFalse(heap.isEmpty());
        heap.add(0);
        heap.add(1);
        assertFalse(heap.isEmpty());
        heap.remove();
        heap.remove();
        assertFalse(heap.isEmpty());
        heap.remove();
        assertTrue(heap.isEmpty());
        heap.add(1);
        assertFalse(heap.isEmpty());
        heap.remove();
        assertTrue(heap.isEmpty());
    }

    /* wonky with generics
    @Test
    public void testHeapSort(){
        for(int i=-2;i<3;++i){
            for(int k=0;k<20;++k){
                heap.add(i*k+k);
            }
        }
        Comparable[] sorted=heap.heapSort(); 
        Iterator<Comparable> ioIt=Arrays.asList(sorted).iterator();
        boolean increasing=true;
        Comparable prev=ioIt.next();
        while(ioIt.hasNext()){
                Comparable next=ioIt.next();
                if(next.compareTo(prev)<0){
                increasing=false;
                break;
            }
        }
        assertTrue(increasing);
    }
    */
    @Test
    public void testAdd(){
        //add to empty
        heap.add(0);
        assertEquals(heap.size(),1);
        assertEquals((int)heap.peek(),0);
        //add smaller leaf 
        heap.add(-1);
        assertEquals(heap.size(),2);
        assertEquals((int)heap.peek(),0);
        //add larger leaf , should swim()
        heap.add(7);
        assertEquals(heap.size(),3);
        assertEquals((int)heap.peek(),7);

        //add larger leaves, should keep swimming until array re-allocates atleast once
        for(int i=8;i<9001;++i){
            heap.add(i);
            assertEquals(heap.size(),i-4);
            assertEquals((int)heap.peek(),i);
        }
        //add smaller leaves
        for(int i=0;i>-100;--i){
            heap.add(i);
        }
        assertEquals((int)heap.peek(),9000);//previos top, should change with smaller leaves
        assertEquals((int)heap.size(),9096);

    }
    @Test
    public void testAddNull(){
        heap.add(null);
        assertEquals(heap.size(),1);
        assertNull(heap.peek());//previous top, should change with smaller leaves
    }

    @Test
    public void testRemoveEmpty(){
        heap.remove();
    }
    @Test
    public void testRemove(){
        //populate heap
        for(int i=0;i<100;++i){
            heap.add(i);
        }
        //remove head
        assertEquals((int)heap.remove(),99);
        assertEquals(heap.size(),99);
        //repeatedly
        for(int i=98;i>0;i--){
            assertEquals((int)heap.remove(),i);
            assertEquals(heap.size(),i);
        }
    }
    @Test
    public void removeNull(){
        heap.add(7);
        heap.add(null);
        assertEquals(heap.size(),2);
        assertNull(heap.remove());
        assertEquals(heap.size(),1);
        assertEquals((int)heap.peek(),7);
    }



}
