package com.exaltit.rpncalc.service;

import com.exaltit.rpncalc.domain.Stack;
import com.exaltit.rpncalc.domain.StackRequest;
import com.exaltit.rpncalc.domain.StackValueRequest;

import java.util.List;

public interface RpnService {

    /**
     * create new stack of integers
     *
     * @param request
     * @return
     */
    Stack<Integer> createStack(StackRequest request);

    /**
     * find stack by id
     *
     * @param id
     * @return stack retrieved
     */
    Stack<Integer> findStackById(Long id);

    /**
     * find all available stacks
     *
     * @return
     */
    List<Stack<Integer>> findAllAvailableStacks();

    /**
     * delete stack by id
     *
     * @param stackId
     */
    void deleteStack(Long stackId);

    /**
     * push a new value to a stack
     *
     * @param id      of specific stack
     * @param request contain new value to push it in stack
     * @return stack with new value
     */
    Stack<Integer> pushNewValueToStack(Long id, StackValueRequest request);

    /**
     * apply operand to a stack
     *
     * @param op      operand to apply
     * @param stackId
     * @return stack with new values
     */
    Stack<Integer> applyOpToAStack(Character op, Long stackId);
}
