package leetcode;

import java.util.Arrays;
import java.util.List;

public class addTwoNumbers {
    public static void main(String[] args) {

        ListNode l1 = initListNode(Arrays.asList(2,4,3));
        ListNode l2 = initListNode(Arrays.asList(5,6,4));
        ListNode listNode = addTwoNumbersV2(l1,l2);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode head;
        ListNode next;
        ListNode current;

        ListNode(){

        }
        ListNode(int x) {
            val = x;
        }
        ListNode(int x, ListNode n){
            val = x;
            next = n;
        }

        void add(int v){
            if(head == null){
                head = new ListNode(v);
                current = head;
            }else {
                current.next = new ListNode(v);
                current = current.next;
            }

        }

    }

    static ListNode initListNode(List<Integer> arrays){
        ListNode listNode = new ListNode();
        for(int i=0;i< arrays.size(); i++){
            listNode.add(arrays.get(i));
        }
        return listNode;
    }

    public static ListNode addTwoNumbersV2(ListNode l1, ListNode l2){
        ListNode t2 = l2;
        boolean tenMark = false;
        while (l1 != null){
            l1.val = tenMark ? l1.val+1 : l1.val;
            if(t2 != null){
                l1.val = l1.val+t2.val;
                t2 = t2.next;
            }
            if(l1.val >= 10){
                tenMark = true;
                l1.val = l1.val-10;
            }
            l1 = l1.next;
        }
        if (t2 != null){
            l1.next = t2;
            l1 = l1.next;
            while (l1 != null){
                l1.val = tenMark ? l1.val +1 : l1.val;
                if(l1.val >= 10){
                    tenMark = true;
                    l1.val = l1.val-10;
                }
                l1 = l1.next;
            }
        }
        if(tenMark){
            l1.next = new ListNode(1);
        }
        return l1;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int tmpVal = l1.val +l2.val;
        ListNode sumList = new ListNode(tmpVal%10);
        ListNode t1 = l1.next;
        ListNode t2 = l2.next;
        while(t1 != null && t2 != null){
            tmpVal = tmpVal/10 + t1.val + t2.val;
            sumList.next = new ListNode(tmpVal%10);
            sumList.next = sumList.next.next;
            t1 = t1.next;
            t2 = t2.next;
        }
        while(t1 != null){
            tmpVal = l1.next.val + tmpVal/10;
            sumList.next = new ListNode(tmpVal%10);
            sumList.next = sumList.next.next;
            t1 = t1.next;
        }
        while(t2 != null){
            tmpVal = l2.next.val + tmpVal/10;
            sumList.next = new ListNode(tmpVal%10);
            sumList.next = sumList.next.next;
            t2 = t2.next;
        }
        if(tmpVal/10  == 1){
            sumList.next = sumList.next.next;
            sumList.next = new ListNode(1);
        }
        return sumList;

    }
}
