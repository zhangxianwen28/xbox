package com.xw.util.leetcode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class Num2 {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(4);
        list.add(3);
        List<Integer> list2 = new LinkedList<>();
        list2.add(5);
        list2.add(6);
        list2.add(4);
        addTwoNumbers(list, list2);
    }

    public static int addTwoNumbers(List<Integer> list, List<Integer> list2) {
        String collect = list.stream().sorted(Comparator.reverseOrder()).map(String::valueOf).collect(Collectors.joining());
        System.out.println(collect);

        return 0;
    }

}

