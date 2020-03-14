package leetcode;

import java.util.Arrays;
import java.util.List;

public class addTwoNumbers {
    public static void main(String[] args) {

        ListNode l1 = initListNode(Arrays.asList(2,4,3));
        ListNode l2 = initListNode(Arrays.asList(5,6,4));
        ListNode listNode = addTwoNumbers(l1,l2);
        while (listNode.next != null){
            System.out.println(listNode.val);
            listNode.next = listNode.next.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }

    static ListNode initListNode(List<Integer> arrays){
        ListNode listNode = new ListNode(arrays.get(0));
        for(int i=1;i< arrays.size(); i++){
            listNode.next = new ListNode(arrays.get(i));
            listNode.next = listNode.next.next;
        }
        return listNode;
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
