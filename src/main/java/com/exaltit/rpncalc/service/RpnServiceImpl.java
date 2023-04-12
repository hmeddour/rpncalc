package com.exaltit.rpncalc.service;

import com.exaltit.rpncalc.cache.StackRepository;
import com.exaltit.rpncalc.domain.Stack;
import com.exaltit.rpncalc.domain.StackRequest;
import com.exaltit.rpncalc.domain.StackValueRequest;
import com.exaltit.rpncalc.exception.NotFoundException;
import com.exaltit.rpncalc.exception.NullValueException;
import com.exaltit.rpncalc.exception.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RpnServiceImpl implements RpnService {

    @Autowired
    private StackRepository stackRepository;

    @Override
    public Stack<Integer> createStack(StackRequest request) {
        if (request == null)
            throw new NullValueException("StackRequest must not be null");

        return this.stackRepository.addStack(request);
    }

    @Override
    public Stack<Integer> findStackById(Long id) throws NotFoundException {
        return this.stackRepository.findStack(id);
    }

    @Override
    public List<Stack<Integer>> findAllAvailableStacks() {
        return this.stackRepository.findAllStacks();
    }

    @Override
    public void deleteStack(Long stackId) {
        this.stackRepository.deleteStack(stackId);
    }

    @Override
    public Stack<Integer> pushNewValueToStack(Long id, StackValueRequest request) {
        Stack<Integer> stack = this.findStackById(id);
        stack.addValue(request.getValue());
        return stack;
    }

    @Override
    public Stack<Integer> applyOpToAStack(Character op, Long stackId) {
        Stack stack = this.stackRepository.findStack(stackId);
        List<Integer> values = stack.getValues();
        // check if stack contains more than 2 elements
        if (values == null || values.isEmpty() || values.size() < 2)
            throw new OperationException(String
                    .format("To apply the operation {}, the stack with id {} must have more than 2 values",
                            op.charValue(),
                            stackId));

        // get the two last values of stack
        int lastIndex = values.size() - 1;
        int beforeLastIndex = lastIndex - 1;
        Integer lastValue = values.get(lastIndex);
        Integer beforeLastValue = values.get(beforeLastIndex);
        Integer result = switch (op) {
            case '+' -> lastValue + beforeLastValue;
            case '-' -> lastValue - beforeLastValue;
            case '*' -> lastValue * beforeLastValue;
            case '/' -> lastValue / beforeLastValue;
            default -> 0;
        };
        values.remove(lastValue);
        values.remove(beforeLastValue);
        values.add(result);
        stack.setValues(values);

        return stack;
    }

    private void validate(Stack stack) {

    }
}
