package com.littlefox.easypoi.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class Xirr {

    private double days = 0;

    /**
     * Convenience method for getting an instance of a {@link Builder}.
     * @return new Builder
     */
//
//    private final XirrDetails details;
//
//    private Double guess = null;
//
//    /**
//     * Construct an Xirr instance for the given transactions.
//     * @param tx the transactions
//     * @throws IllegalArgumentException if there are fewer than 2 transactions
//     * @throws IllegalArgumentException if all the transactions are on the same date
//     * @throws IllegalArgumentException if all the transactions negative (deposits)
//     * @throws IllegalArgumentException if all the transactions non-negative (withdrawals)
//     */
//    public Xirr(double days, double guess, CashFlowTransaction... tx) {
//        this(Arrays.asList(tx), days, guess);
//    }
//
//    /**
//     * Construct an Xirr instance for the given transactions.
//     * @param txs the transactions
//     * @throws IllegalArgumentException if there are fewer than 2 transactions
//     * @throws IllegalArgumentException if all the transactions are on the same date
//     * @throws IllegalArgumentException if all the transactions negative (deposits)
//     * @throws IllegalArgumentException if all the transactions non-negative (withdrawals)
//     */
//    public Xirr(Collection<CashFlowTransaction> txs, double days, double guess) {
//        this(txs, null, days, guess);
//    }
//
//    private Xirr(Collection<CashFlowTransaction> txs, NewtonRaphson.Builder builder, double days, Double guess) {
//        if (txs.size() < 2) {
//            throw new IllegalArgumentException(
//                "Must have at least two transactions");
//        }
//        details = txs.stream().collect(XirrDetails.collector());
//        details.validate();
//
//    }

}
