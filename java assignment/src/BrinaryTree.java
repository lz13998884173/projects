import java.util.ArrayList;

class BinaryTree {
    public Node root = null;
     
    BinaryTree(){}
    BinaryTree(int key,String from,String destination,String status) {
        root = new Node(key,from,destination,status);
        root.leftChild  = null;
        root.rightChild = null;
    }
    //search
    public Node findKey(int value) {

        Node current = root;
        
        while (true) {
            if (value == current.key) {
                return current;
            } else if (value < current.key) {
                current = current.leftChild;
                
            } else if (value > current.key) {
                current = current.rightChild;
               
            }
             
            if (current == null) {
                return null;
            }
            
        }
        
    }   
    
    
    //insert
        public String insert(int key,String from,String destination,String status) {

            String error = null;
             
            Node node = new Node(key,from,destination,status);
            if (root == null) {
                root = node;
                root.leftChild  = null;
                root.rightChild = null;
            } else {
                Node current = root;
                Node parent = null;
                while (true) {
                    if (key < current.key) {
                        parent = current;
                        current = current.leftChild;
                        if (current == null) {
                            parent.leftChild = node;
                            break;
                        }
                    } else if (key > current.key) {
                        parent = current;
                        current = current.rightChild;
                        if (current == null) {
                            parent.rightChild = node;
                            break;
                        }
                    } else {
                        error = "having same value in binary tree";
                    }   
                } // end of while
            }
            return error;
        }  
        
        public ArrayList<String[]> Tree_print() {  
    	    root.printNode();
    	    return root.ta;
    	}  
        public void inOrderTraverse() {

            System.out.print("inOrderTraverse:");
            inOrderTraverse(root);
            System.out.println();
        }
         
        private void inOrderTraverse(Node node) {
            if (node == null) 
                return ;
             
            inOrderTraverse(node.leftChild);
            node.display();
            inOrderTraverse(node.rightChild);
        }    //inOrderTraverse
        public void preOrderTraverse() {

            System.out.print("preOrderTraverse:");
            preOrderTraverse(root);
            System.out.println();
        }
         
        private void preOrderTraverse(Node node) {
            if (node == null) 
                return ;
             
            node.display();
            preOrderTraverse(node.leftChild);
            preOrderTraverse(node.rightChild);
        }   //preOrderTraverse
        public void postOrderTraverse() {

            System.out.print("postOrderTraverse:");
            postOrderTraverse(root);
            System.out.println();
        }
         
        private void postOrderTraverse(Node node) {
            if (node == null) 
                return ;
             
            postOrderTraverse(node.leftChild);
            postOrderTraverse(node.rightChild);
            node.display();
        }  //postOrderTraverse
        public int getMinValue() {

            Node current = root;
            while (true) {
                if (current.leftChild == null)
                    return current.key;
                 
                current = current.leftChild;
            }
        }      //get the mixnum/minnum
        
        
        private Node getSuccessor(Node delNode) {
            Node successor = delNode;
            Node successorParent = null;
            Node current = delNode.rightChild;
             
            while (current != null) {
                successorParent = successor;
                successor = current;
                current = current.leftChild;
            }
             
           
            //if the next node is not the rightChild node of the deleted node 
            if (successor != delNode.rightChild) {
                
                successorParent.leftChild = successor.rightChild;
                
                successor.rightChild = delNode.rightChild;
            }
           
            
            successor.leftChild = delNode.leftChild;
             
            return successor;
        }
        public boolean delete(int value) {
        	      Node current = root;    
        	      Node parent = null;     
        	      boolean isLeftChild = true;   
        	       
        	      while (true) {
        	          if (value == current.key) {
        	              break;
        	          } else if (value < current.key) {
        	              isLeftChild = true;
        	              parent = current;
        	              current = current.leftChild;
        	          } else {
        	              isLeftChild = false;
        	              parent = current;
        	              current = current.rightChild;
        	          }
        	           
        	          
        	          if (current == null)
        	              return false;
        	      }
        	       
        	      
        	      if (current.leftChild == null && current.rightChild == null) {
        	          
        	          if (current == root) {
        	              root = null;
        	          } else {
        	              
        	              if (isLeftChild) {
        	                  parent.leftChild  = null;
        	              } else { 
        	                  parent.rightChild = null;
        	              }
        	          }
        	      } 
        	      
        	      else if (current.rightChild == null) {
        	          
        	          if (current == root) {
        	              root = current.leftChild;
        	          } else {
        	              
        	              if (isLeftChild) {
        	                  parent.leftChild = current.leftChild;
        	              } else {  
        	                  parent.rightChild = current.leftChild;
        	              }
        	          }
        	      }
        	      
        	      else if (current.leftChild == null) {
        	          
        	          if (current == root) {
        	              root = current.rightChild;
        	          } else {
        	              
        	              if (isLeftChild) {
        	                  parent.leftChild = current.rightChild;
        	              } else {  
        	                  parent.rightChild = current.rightChild;
        	              }
        	          }
        	      }
        	      
        	      else {
        	          Node successor = getSuccessor(current);
        	          
        	          if (current == root) {
        	              root = successor;
        	          } else {
        	              
        	              if (isLeftChild) {
        	                  parent.leftChild = successor;
        	              } else {  
        	                  parent.rightChild = successor;
        	              }
        	          }
        	      }
        	      current = null;
        	      return true;
        	  }
         //delete
}