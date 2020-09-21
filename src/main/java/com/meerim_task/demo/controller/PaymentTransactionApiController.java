package com.meerim_task.demo.controller;

import com.meerim_task.demo.exception.ConflictException;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.facade.PaymentTransactionFacade;
import com.meerim_task.demo.facade.dto.CancelPaymentTransactionRequestDto;
import com.meerim_task.demo.facade.dto.CreatePaymentTransactionRequestDto;
import com.meerim_task.demo.facade.dto.PaymentTransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user/{userId}/payment_transactions")
public class PaymentTransactionApiController {
    private final PaymentTransactionFacade paymentTransactionFacade;

    @PostMapping("/doCreate")
    public PaymentTransactionDto create(@PathVariable Long userId, @RequestBody @Valid CreatePaymentTransactionRequestDto request) throws NotFoundException {
        return paymentTransactionFacade.create(userId, request);
    }

    @PostMapping("{parentId}/doCancel")
    public PaymentTransactionDto cancel(@PathVariable Long userId, @PathVariable Long parentId, @RequestBody @Valid CancelPaymentTransactionRequestDto request) throws NotFoundException, ConflictException {
        return paymentTransactionFacade.cancel(userId, parentId, request);
    }
}
