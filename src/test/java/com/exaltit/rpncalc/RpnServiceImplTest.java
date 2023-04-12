package com.exaltit.rpncalc;

import com.exaltit.rpncalc.cache.StackRepository;
import com.exaltit.rpncalc.domain.Stack;
import com.exaltit.rpncalc.domain.StackRequest;
import com.exaltit.rpncalc.exception.NullValueException;
import com.exaltit.rpncalc.service.RpnService;
import com.exaltit.rpncalc.service.RpnServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RpnServiceImplTest {

    @Mock
    private StackRepository stackRepository;

    private RpnService rpnService = new RpnServiceImpl();

    private List<Stack<Integer>> stacks;


    @Before
    public void init() {
        this.stacks = new ArrayList<>();
        this.stacks.add(new Stack<>(1l));
        this.stacks.add(new Stack<>(2l));
    }

    @Test
    public void test_create_new_stack_when_request_is_null_should_throws_null_value_exception() {
        NullValueException nullValueException = Assertions.assertThrows(NullValueException.class, () -> {
            this.rpnService.createStack(null);
        });
        Assertions.assertEquals("StackRequest must not be null", nullValueException.getMessage());
    }

    @Test
    public void test_create_new_stack_then_last_stack_in_list_should_be_equals_to_new_stack_added() {
        StackRequest request = new StackRequest();
        Stack created = this.rpnService.createStack(request);
        Mockito.when(this.rpnService.findAllAvailableStacks()).thenReturn(this.stacks);

        Assertions.assertEquals(created.getId(), this.stacks.get(this.stacks.size() - 1).getId());
    }
}
