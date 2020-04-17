package edu.smith.cs.csc212.sorts;

import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.TODOErr;
import me.jjfoley.adt.impl.JavaList;

public class MergeSort {

	/**
	 * This method walks through two sorted input lists and creates an output list that contains all elements from the two inputs.
	 * @param lhs - a sorted list.
	 * @param rhs  - a sorted list.
	 * @return a sorted list containing all of the items from lhs and rhs.
	 */
	public static ListADT<Integer> combineTwoSortedLists(ListADT<Integer> lhs, ListADT<Integer> rhs) {
		ListADT<Integer> output = new JavaList<>();
		while (!lhs.isEmpty() && !rhs.isEmpty()) {
			if (lhs.getFront() > rhs.getFront()) {
				output.addBack(rhs.getFront());
				rhs.removeFront();
				continue;
			} else {
				output.addBack(lhs.getFront());
				lhs.removeFront();
				continue;
			}
		}
		
		if (rhs.isEmpty())	{
			output.addAll(lhs);
		} else if (lhs.isEmpty()) {
			output.addAll(rhs);
		}
		return output;
	}
	
	/**
	 * Recursively sort this list by breaking it in half and piecing it back together.
	 * You will need to call {@linkplain #combineTwoSortedLists(ListADT, ListADT)}.
     *
	 * @param input - the input list.
	 * @return a new list containing the sorted output.
	 */
	public static ListADT<Integer> doMergeSortRecursively(ListADT<Integer> input) {
		int mid = input.size()/2;
		if (input.size()<=1) {
			return input;
		} else {
			ListADT<Integer> left = input.slice(0, mid);
			ListADT<Integer> right = input.slice(mid, input.size());
			left = doMergeSortRecursively(left);
			right = doMergeSortRecursively(right);
			return combineTwoSortedLists(left, right);
		}
	}
	
	/**
	 * Iteratively sort this list by breaking it in half and piecing it back together.
	 * You will need to call {@linkplain #combineTwoSortedLists(ListADT, ListADT)}.
	 * 
	 * @param input - the input list.
	 * @return a new list containing the sorted output.
	 */
	public static ListADT<Integer> doMergeSortIteratively(ListADT<Integer> input) {
		ListADT<ListADT<Integer>> work = new JavaList<>();
		for (int i=0; i<input.size(); i++) {
			ListADT<Integer> elem = new JavaList<>();
			elem.addBack(input.getIndex(i));
			work.addBack(elem);
			System.out.println(work.size());
		}
		
		while (work.size()>1) {
			
			work.addBack(combineTwoSortedLists(work.getIndex(0), work.getIndex(1)));
			work.removeFront();
			work.removeFront();
			System.out.println(work.size());
		}
		
		return work.getBack();
	}
}
