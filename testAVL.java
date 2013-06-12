import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Iterator;

/*
 * Some testing  Requirements: 
 * Tree will need a getHead() method
 * Tree will need displayTree() method
 * Tree's nodes should support getData(), getHeight()
 */


public class testAVL{
    AVLTree<Integer> tree=new AVLTree<Integer>();
    @Before
    public void initBST(){
    }
    
    @Test
    public void addPDF(){
        System.out.println("The following should match up with the reference pdf by JD");
        tree.add(10);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(10));
        assertEquals(tree.getHead().getHeight(),0);
        
        tree.add(5);
        tree.displayTree();
        assertEquals(tree.getHead().getLeft().getData(),new Integer(5));
        assertEquals(tree.getHead().getHeight(),1);

        tree.add(15);
        tree.displayTree();
        assertEquals(tree.getHead().getRight().getData(),new Integer(15));

        tree.add(-1);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(10));
        assertEquals(tree.getHead().getHeight(),2);
        tree.add(25);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(10));
        tree.add(30);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(10));
        tree.add(28);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(10));
        assertEquals(tree.getHead().getHeight(),3);

        tree.add(26);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(10));
        tree.add(40);
        tree.displayTree();
        
        assertEquals(tree.getHead().getData(),new Integer(10));
        tree.add(14);
        assertEquals(tree.getHead().getData(),new Integer(25));
        assertEquals(tree.getHead().getHeight(),3);
        tree.displayTree();
        tree.add(13);
        tree.displayTree();
        System.out.println("End PDF add sequence");

    }
    
    
    //
    //Some removing tests
    //

    @Test
    public void testBlankRemove(){
        AVLTree<Integer> remTree=new AVLTree<Integer>();
        assertEquals(0,remTree.size());
        assertFalse(remTree.remove(0));
        assertFalse(remTree.remove(9999));
        assertFalse(remTree.remove(null));
    }
    @Test
    public void testLeafRemove(){
        AVLTree<Integer> remTree=new AVLTree<Integer>();
        remTree.addAll(new ArrayList<Integer>(Arrays.asList(20,10,30,5,35,15,25)));
        assertEquals(remTree.size(),7);
        assertTrue(remTree.remove(25));
        assertEquals(remTree.size(),6);
        assertFalse(remTree.contains(25));
        
        Node<Integer> head=remTree.getHead();
        assertEquals(head.getHeight(),2);//constants height->no rotations, atleast one leaf present
        
        assertTrue(remTree.contains(15));
        assertTrue(remTree.remove(15));
        assertEquals(remTree.size(),5);
        assertFalse(remTree.contains(15));
        assertEquals(head.getHeight(),2);

        assertTrue(remTree.contains(5));
        assertTrue(remTree.remove(5));
        assertEquals(remTree.size(),4);
        assertFalse(remTree.contains(5));
        assertEquals(head.getHeight(),2);

        assertTrue(remTree.contains(35));
        assertTrue(remTree.remove(35));
        assertEquals(remTree.size(),3);
        assertFalse(remTree.contains(35));
        assertEquals(head.getHeight(),1);//changes after removing all 4 leaves

        //simple 2 child node
        //remTree.displayTree();
    }
    
    @Test
    public void test1ChildRemove(){
        AVLTree<Integer> remTree=new AVLTree<Integer>();
        remTree.addAll(new ArrayList<Integer>(Arrays.asList(20,10,30,5,25,15)));
        assertEquals(remTree.size(),6);
        assertTrue(remTree.remove(30));
        assertFalse(remTree.contains(30));
        assertEquals(remTree.size(),5);
        
        Node<Integer> head=remTree.getHead();
        assertEquals(head.getHeight(),2);
        int testData=head.getRight().getData();
        int testDataHeight=head.getRight().getHeight();
        assertEquals(testData,25);//test correct re-assignment
        assertEquals(testDataHeight,0);//test correct height
        //25 on the right, 10->5,15 on left
        //remTree.displayTree();

        assertFalse(remTree.remove(9009));//remove non existant from tree
    }
    
    @Test
    public void testRootRemove(){
        AVLTree<Integer> remTree=new AVLTree<Integer>();
        remTree.addAll(new ArrayList<Integer>(Arrays.asList(10,0,30,20,40)));
        assertEquals(remTree.getHead().getHeight(),2);
        assertTrue(remTree.remove(10));//removes root
        assertFalse(remTree.contains(10));
        assertEquals(remTree.getHead().getHeight(),2);//height should not change, multiple leaves

        
        Node<Integer> head=remTree.getHead();
        assertEquals(head.getHeight(),2);
        int testData=head.getData();
        assertEquals(testData,20);//test correct re-assignment
        //20 root, 0 left, 30, 40 right
        //remTree.displayTree();

        assertFalse(remTree.remove(9009));//remove non existant from tree
    }
    
    @Test
    public void testRemoveOnebyOne(){
        AVLTree<Integer> remTree=new AVLTree<Integer>();
        remTree.addAll(new ArrayList<Integer>(Arrays.asList(20,10,30,0,40)));
        assertEquals(remTree.size(),5);
        assertTrue(remTree.remove(0));
        assertTrue(remTree.remove(40));
        assertTrue(remTree.remove(30));
        assertTrue(remTree.remove(20));
        assertTrue(remTree.remove(10));
        assertFalse(remTree.remove(10));//extra, just to test removal of empty
        assertEquals(remTree.size(),0);
    }

    
    @Test
    public void testRemoveWRot(){
        AVLTree<Integer> remTree=new AVLTree<Integer>();
        remTree.addAll(new ArrayList<Integer>(Arrays.asList(50,25,75,20,30,70,80,27,69,77,82,76)));
        assertEquals(remTree.getHead().getHeight(),4);
        assertTrue(remTree.remove(82));
        assertEquals(remTree.getHead().getHeight(),3);//the height should lessen after the rotation of the leaves
    }

    @Test
    public void testRemoveWRot2(){
        AVLTree<Integer> remTree=new AVLTree<Integer>();
        remTree.addAll(new ArrayList<Integer>(Arrays.asList(50,25,75,20,30,70,80,27,69,77,82,76)));
        //remTree.displayTree();
        assertEquals(remTree.getHead().getHeight(),4);
        assertTrue(remTree.remove(69));
        assertEquals(remTree.getHead().getHeight(),3);//the height should lessen after the rotation of the leaves
        //remTree.displayTree();
    }
    
    @Test
    public void testRemoveWRot3(){
        AVLTree<Integer> remTree=new AVLTree<Integer>();
        remTree.addAll(new ArrayList<Integer>(Arrays.asList(50,25,75,20,30,70,80,27,69,77,82,76)));
        //remTree.displayTree();
        Node<Integer> head=remTree.getHead();
        int testData=head.getData();
        assertEquals(testData,50);//test correct re-assignment

        assertEquals(remTree.getHead().getHeight(),4);
        assertTrue(remTree.remove(20));
        assertEquals(remTree.getHead().getHeight(),3);//the height should lessen after the rotation of the leaves
        testData=head.getData();
        assertEquals(testData,75);//test correct re-assignment, huge rotation at root
        //remTree.displayTree();
        
    }
    @Test
    public void testSize(){
        AVLTree<Integer> sizeTree=new AVLTree<Integer>();
        assertEquals(sizeTree.size(),0);
        sizeTree.add(0);
        assertEquals(sizeTree.size(),1);
        sizeTree.clear();
        assertEquals(sizeTree.size(),0);
        sizeTree.clear();
        sizeTree.clear();
        assertEquals(sizeTree.size(),0);
        addIncreasing(9001,sizeTree);
        assertEquals(sizeTree.size(),9001);
        sizeTree.add(-1);
        assertEquals(sizeTree.size(),9002);
        sizeTree.remove(-1);
        assertEquals(sizeTree.size(),9001);
        sizeTree.clear();
        assertEquals(sizeTree.size(),0);
    }

    @Test
    public void testIsEmpty(){
        AVLTree<Integer> eTree=new AVLTree<Integer>();
        assertTrue(eTree.isEmpty());
        eTree.add(0);
        assertFalse(eTree.isEmpty());
        eTree.clear();
        assertTrue(eTree.isEmpty());
        eTree.clear();
        assertTrue(eTree.isEmpty());
        addIncreasing(9001,eTree);
        assertFalse(eTree.isEmpty());
        eTree.add(-1);
        eTree.remove(-1);
        assertFalse(eTree.isEmpty());
        eTree.clear();
        assertTrue(eTree.isEmpty());
    }
    //
    //Some Add/rotation tests
    //
    
    @Test
    public void testAddSimple(){
        AVLTree<Integer> addTree=new AVLTree<Integer>();
        assertEquals(addTree.size(),0);
        addTree.add(5); 
        assertEquals(addTree.size(),1);
        assertEquals(addTree.getHead().getHeight(),0);
        
        addTree.add(6); 
        assertEquals(addTree.size(),2);
        assertEquals(addTree.getHead().getHeight(),1);
        assertEquals(addTree.getHead().getRight().getHeight(),0);

        addTree.add(4); 
        assertEquals(addTree.size(),3);
        assertEquals(addTree.getHead().getHeight(),1);
        assertEquals(addTree.getHead().getLeft().getHeight(),0);
    }

    @Test
    public void testAddRotR(){
        AVLTree<Integer> addTree=new AVLTree<Integer>();
        assertEquals(addTree.size(),0);
        addTree.add(5); 
        assertEquals(addTree.size(),1);
        assertEquals(addTree.getHead().getHeight(),0);
        
        addTree.add(6); 
        assertEquals(addTree.size(),2);
        assertEquals(addTree.getHead().getHeight(),1);
        assertEquals(addTree.getHead().getRight().getHeight(),0);

        addTree.add(7); 
        assertEquals(addTree.size(),3);
        assertEquals(addTree.getHead().getHeight(),1);
        assertEquals(addTree.getHead().getLeft().getHeight(),0);
        assertEquals(addTree.getHead().getRight().getHeight(),0);
    }

    @Test
    public void testAddRotL(){
        AVLTree<Integer> addTree=new AVLTree<Integer>();
        assertEquals(addTree.size(),0);
        addTree.add(5); 
        assertEquals(addTree.size(),1);
        assertEquals(addTree.getHead().getHeight(),0);
        
        addTree.add(4); 
        assertEquals(addTree.size(),2);
        assertEquals(addTree.getHead().getHeight(),1);
        assertEquals(addTree.getHead().getLeft().getHeight(),0);

        addTree.add(3); 
        assertEquals(addTree.size(),3);
        assertEquals(addTree.getHead().getHeight(),1);
        assertEquals(addTree.getHead().getLeft().getHeight(),0);
        assertEquals(addTree.getHead().getRight().getHeight(),0);
    }
    
    @Test
    public void testAddRotLR(){
        AVLTree<Integer> addTree=new AVLTree<Integer>();
        assertEquals(addTree.size(),0);
        addTree.add(5); 
        assertEquals(addTree.size(),1);
        assertEquals(addTree.getHead().getHeight(),0);
        
        addTree.add(7); 
        assertEquals(addTree.size(),2);
        assertEquals(addTree.getHead().getHeight(),1);
        assertEquals(addTree.getHead().getRight().getHeight(),0);

        addTree.add(6); 
        assertEquals(addTree.size(),3);
        assertEquals(addTree.getHead().getHeight(),1);
        assertEquals(addTree.getHead().getLeft().getHeight(),0);
        assertEquals(addTree.getHead().getRight().getHeight(),0);
    }
    
    @Test
    public void testAddRotRL(){
        AVLTree<Integer> addTree=new AVLTree<Integer>();
        assertEquals(addTree.size(),0);
        addTree.add(5); 
        assertEquals(addTree.size(),1);
        assertEquals(addTree.getHead().getHeight(),0);
        
        addTree.add(3); 
        assertEquals(addTree.size(),2);
        assertEquals(addTree.getHead().getHeight(),1);
        assertEquals(addTree.getHead().getLeft().getHeight(),0);

        addTree.add(4); 
        assertEquals(addTree.size(),3);
        assertEquals(addTree.getHead().getHeight(),1);
        assertEquals(addTree.getHead().getLeft().getHeight(),0);
        assertEquals(addTree.getHead().getRight().getHeight(),0);
    }
    //remove methods test more rotation cases
    //
    //


    @Test
    public void testRemovePDF(){
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(-1);
        tree.add(25);
        tree.add(30);
        tree.add(28);
        tree.add(26);
        tree.add(40);
        tree.add(14);
        tree.add(13);
        System.out.println("The following should match up with the pdf by JD");
        tree.remove(13);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(25));
        assertEquals(tree.getHead().getHeight(),3);
        
        tree.remove(14);
        tree.displayTree();
        tree.remove(25);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(26));
        assertEquals(tree.getHead().getHeight(),3);

        tree.remove(5);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(26));
        assertEquals(tree.getHead().getHeight(),2);

        tree.remove(26);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(28));
        assertEquals(tree.getHead().getHeight(),2);
        tree.remove(10);
        tree.displayTree();
        tree.remove(15);
        tree.displayTree();
        tree.remove(-1);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(30));
        assertEquals(tree.getHead().getHeight(),1);

        tree.remove(30);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(40));
        assertEquals(tree.getHead().getHeight(),1);
        
        tree.remove(40);
        tree.displayTree();
        assertEquals(tree.getHead().getData(),new Integer(28));
        tree.remove(28);
        tree.displayTree();
        tree.remove(28);
        assertNull(tree.getHead());
        System.out.println("End PDF remove sequence");
    }
    
    @Test
    public void testInOrderTraverse(){
        AVLTree<Integer> tree1=new AVLTree<Integer>();
        addIncreasing(100,tree1);
        AVLTree<Integer> tree2=new AVLTree<Integer>();
        addDecreasing(100,tree2);
        AVLTree<Integer> tree3=new AVLTree<Integer>();
        addRandom(100,tree3);
        Iterator<Integer> ioIt=tree1.iterator();
        boolean treeIncreasing=true;
        int prev=ioIt.next();
        while(ioIt.hasNext()){
            int next=ioIt.next();
            if(next<prev){
                treeIncreasing=false;
                break;
            }
        }
        assertTrue(treeIncreasing);
        
        
        treeIncreasing=true;
        ioIt=tree2.iterator();
        prev=ioIt.next();
        while(ioIt.hasNext()){
            int next=ioIt.next();
            if(next<prev){
                treeIncreasing=false;
                break;
            }
        }
        assertTrue(treeIncreasing);
        
        treeIncreasing=true;
        ioIt=tree3.iterator();
        prev=ioIt.next();
        while(ioIt.hasNext()){
            int next=ioIt.next();
            if(next<prev){
                treeIncreasing=false;
                break;
            }
        }
        assertTrue(treeIncreasing);
    }

    @Test
    public void containsTest(){
        AVLTree<Integer> tree=new AVLTree<Integer>();
        assertFalse(tree.contains(2));
        tree.addAll(new ArrayList<Integer>(Arrays.asList(50,25,75,20,30,70,80,27,69,77,82,76)));
        assertTrue(tree.contains(50));
        assertTrue(tree.contains(30));
        assertTrue(tree.contains(76));
        assertFalse(tree.contains(0));
        assertFalse(tree.contains(2));
        assertFalse(tree.contains(null));
    }

    /*
     * populates tree with random numbers
     */
    public static void addRandom(int num, AVLTree tree){
        Random generator=new Random();
        int i=0;
        while(i<num){
            tree.add(generator.nextInt());
            i++;
        }
    }
    /*
     *poulates tree in increasing order
     */
    public static void addIncreasing(int num, AVLTree tree){
        int i=0;
        while(i<num){
            tree.add(i++);
        }
    }
    /*
     *poulates tree in decreasing order
     */
    public static void addDecreasing(int num, AVLTree tree){
        int i=num;
        while(i>0){
            tree.add(i--);
        }
    }
}
