package com.exaltit.rpncalc.controller;

import com.exaltit.rpncalc.cache.RpnCache;
import com.exaltit.rpncalc.domain.Stack;
import com.exaltit.rpncalc.domain.StackRequest;
import com.exaltit.rpncalc.domain.StackValueRequest;
import com.exaltit.rpncalc.exception.NotFoundException;
import com.exaltit.rpncalc.exception.OperationException;
import com.exaltit.rpncalc.service.RpnService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/rpn")
public class RpnController {
    @Autowired
    private RpnService rpnService;

    @GetMapping("/operands")
    @ApiResponse(code = 200, message = "List of all operands.", response = Character.class, responseContainer = "List")
    public ResponseEntity<List<Character>> getAllOperands() {
        return new ResponseEntity<>(RpnCache.OPERANDS, HttpStatus.OK);
    }

    @PostMapping("/op/{op}/stack/{stackId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The stack returned.", response = Stack.class),
            @ApiResponse(code = 404, message = "Stack not found.", response = NotFoundException.class),
            @ApiResponse(code = 409, message = "Can't do operation.", response = OperationException.class)})
    public ResponseEntity<Stack<Integer>> applyOpToAStack(@PathVariable("op") Character op,
                                                          @PathVariable("stackId") Long stackId) {
        try {
            Stack stack = this.rpnService.applyOpToAStack(op, stackId);
            return new ResponseEntity<>(stack, HttpStatus.OK);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (OperationException opException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/stack")
    @ApiResponse(code = 200, message = "The stack created.", response = Stack.class)
    public ResponseEntity<Stack<Integer>> createNewStack(@RequestBody final StackRequest request) {
        Stack<Integer> createdStack = rpnService.createStack(request);
        return new ResponseEntity<>(createdStack, HttpStatus.OK);
    }

    @GetMapping("/stacks")
    @ApiResponse(code = 200, message = "List stacks.", response = Stack.class, responseContainer = "List")
    public ResponseEntity<List<Stack<Integer>>> getAllStacks() {
        List<Stack<Integer>> allStacks = this.rpnService.findAllAvailableStacks();
        return new ResponseEntity<>(allStacks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The stack returned.", response = Stack.class),
            @ApiResponse(code = 404, message = "Stack not found.", response = NotFoundException.class)})
    public ResponseEntity<Stack> getStackById(@PathVariable("id") Long stackId) {
        try {
            Stack stack = this.rpnService.findStackById(stackId);
            return new ResponseEntity<>(stack, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The stack deleted.", response = Stack.class),
            @ApiResponse(code = 404, message = "Stack not found.", response = NotFoundException.class)})
    public void deleteStackById(@PathVariable("id") Long stackId) {
        try {
            this.rpnService.deleteStack(stackId);
        } catch (NotFoundException e) {
            System.out.println("error : " + e.getMessage());
        }
    }

    @PostMapping("/stack/{stackId}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The stack with new value returned.", response = Stack.class),
            @ApiResponse(code = 404, message = "Stack not found.", response = NotFoundException.class)})
    public ResponseEntity<Stack> pushValueInStackById(@PathVariable("stackId") Long stackId, @RequestBody StackValueRequest request) {
        try {
            Stack stack = this.rpnService.pushNewValueToStack(stackId, request);
            return new ResponseEntity<>(stack, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
