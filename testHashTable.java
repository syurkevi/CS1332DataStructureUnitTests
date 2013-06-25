import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

/*
 * Class for JUnit testing a Hash Table
 * It is far from complete, but a few test cases are better than none (add some)
 */
public class testHashTable{
    LinearProbingHashtable<Integer,String> idTable;

    LinearProbingHashtable<Integer,String> poemTable=new LinearProbingHashtable<Integer,String>();
    
    @Before
    public void initBST(){
        poemTable.put(0,"Not in ");
        poemTable.put(8,"And took the");
        poemTable.put(5,"An angel");
        poemTable.put(7,"gray path");
        poemTable.put(4,"came today");
        poemTable.put(6,"visited this");
        poemTable.put(9,"cube away.");
        poemTable.put(2,"in wrath");
        poemTable.put(1,"cruelty, not");
        poemTable.put(3,"The Reaper");
    }

    @Test 
    public void testCreate(){
		// Testing constructors
        
        idTable=new LinearProbingHashtable<Integer,String>();
        assertEquals(0.75,idTable.getLF());

        idTable=new LinearProbingHashtable<Integer,String>(42);
        assertEquals(42,idTable.getArray().length);

        idTable=new LinearProbingHashtable<Integer,String>(42,0.4);
        assertEquals(42,idTable.getArray().length);
        assertEquals(0.4,idTable.getLF());
        
        //idTable=new LinearProbingHashtable<Integer,String>(-1);//should throw exception
        //idTable=new LinearProbingHashtable<Integer,String>(-1,-1);//should throw exception
    }
    
    @Test 
    public void testClear(){
        idTable=new LinearProbingHashtable<Integer,String>();
        assertEquals(0,idTable.size());
        assertTrue(idTable.isEmpty());
        idTable.clear();
        assertEquals(0,idTable.size());
        assertTrue(idTable.isEmpty());
        idTable.clear();
        assertEquals(0,idTable.size());
        idTable.put(1,"hello,hello");
        assertEquals(1,idTable.size());
        idTable.clear();
        assertEquals(0,idTable.size());
        assertTrue(idTable.isEmpty());
    }
    
    @Test 
    public void testPutNoRehash(){
        idTable=new LinearProbingHashtable<Integer,String>(11,0.5);
        assertEquals(11,idTable.getArray().length);
		idTable.put(7,"I");
		idTable.put(77, "see");
		idTable.put(-44, "trees");
		idTable.put(5, "of");
		idTable.put(5, "hakuna matata");
		idTable.put(5, "of");
		idTable.put(1, "green");
        assertEquals("of",idTable.get(5));
        assertEquals(11,idTable.getArray().length);
        assertEquals(5,idTable.size());
    }	
        
    @Test
    public void testGet(){
        System.out.println(" A poem: ");
        for(int i=0;i<40;++i){
            if(poemTable.get(i)!=null){
                System.out.println(poemTable.get(i));
            }
        }
    }
    
    @Test
    public void testPutNull(){
        idTable=new LinearProbingHashtable<Integer,String>(11,0.5);
        idTable.put(3,null);
        assertNull(idTable.get(3));
    }
		
    @Test
    public void testPutRehash(){
        idTable=new LinearProbingHashtable<Integer,String>(11,0.5);
        assertEquals(11,idTable.getArray().length);
		idTable.put(0,"I");
		idTable.put(1,"am");
		idTable.put(2,"the");
		idTable.put(3,"very");
		idTable.put(4,"model");
		idTable.put(5,"of");
		idTable.put(6,"a");
		idTable.put(-7,"Modern-Major");
		idTable.put(8, "General");
        assertTrue((idTable.getArray().length>11));
        assertEquals(8,idTable.size());
		idTable.put(-7, null);
		assertTrue(idTable.containsKey(-7));
		assertTrue(idTable.containsValue("General"));
		assertTrue(idTable.containsValue(null));
    }
		
    @Test
    public void testRemove(){
        assertEquals(10,poemTable.size());
		assertEquals("gray path",poemTable.remove(7));
        assertEquals(9,poemTable.size());
        assertNull(poemTable.remove(9999));
        assertFalse(poemTable.containsKey(7));
        assertEquals(9,poemTable.size());
    }
				
    @Test
    public void testPutAll(){
        idTable=new LinearProbingHashtable<Integer,String>(11,0.5);
        idTable.put(0,":(");
        assertEquals(1,Arrays.asList(idTable.getArray()).size());

		LinearProbingHashtable <Integer,String> copyMap=new LinearProbingHashtable<Integer,String>();
        copyMap.put(77,"Bring");
        copyMap.put(7,"a");
        copyMap.put(-7,"shrubbery");
        assertFalse(idTable.containsKey(77));
		idTable.putAll(copyMap);
        assertEquals(4,Arrays.asList(idTable.getArray()).size());
        copyMap.put(0,":)");
		idTable.putAll(copyMap);
        assertEquals(4,Arrays.asList(idTable.getArray()).size());
        assertTrue(idTable.containsKey(77));
        assertFalse(idTable.containsValue(":("));
        assertTrue(idTable.containsValue(":)"));
    }


    @Test
    public void testKeys(){
		Set<Integer> keys=poemTable.keySet();
        assertEquals(10,keys.size());
		assertTrue(keys.contains(0));
		assertTrue(keys.contains(1));
		assertTrue(keys.contains(2));
		assertTrue(keys.contains(3));
		assertTrue(keys.contains(9));
		assertFalse(keys.contains(-1));
		assertFalse(keys.contains(40));
    }

    @Test
    public void testValues(){
		Collection<String> values=poemTable.values();		
        assertEquals(10,values.size());
        assertTrue(values.contains("An angel"));
        assertTrue(values.contains("I don't blame you"));
    }

}
