package com.exaltit.rpncalc.cache;

import com.exaltit.rpncalc.domain.Stack;
import com.exaltit.rpncalc.domain.StackRequest;
import com.exaltit.rpncalc.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class StackRepository {

    private final Map<Long, Stack<Integer>> stacks = new ConcurrentHashMap<>();

    public Stack addStack(StackRequest request) {
        int key = stacks.size() + 1;
        return this.stacks.put(Long.valueOf(key), new Stack<>(Long.valueOf(key), request.getValues()));
    }

    public Stack findStack(Long id) {
        Stack stack = stacks.get(id);
        if (stack == null) {
            throw new NotFoundException(String.format("Stack with id %s is not found", id));
        }
        return stack;
    }

    public List<Stack<Integer>> findAllStacks() {
        return this.stacks
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    public void deleteStack(Long id) {
        Stack stack = this.findStack(id);
        this.stacks.remove(stack);
    }
}
