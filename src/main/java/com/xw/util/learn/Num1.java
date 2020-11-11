package com.xw.util.learn;


import java.util.Arrays;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class Num1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(towSum(new int[]{2, 7, 11, 15}, 9)));
    }

    public static int[] towSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int num = nums[i];
                int num1 = nums[j];
                System.out.println(num + " + " + num1);
                if (target == num + num1) {
                    return new int[]{num, num1};
                }
            }
        }
        return null;
    }



}
