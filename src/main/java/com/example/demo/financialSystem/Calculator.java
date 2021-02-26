package com.example.demo.financialSystem;

import java.util.Map;


public interface Calculator<T extends String, S extends Number> {
    CashAmountWithCurrency count(final Map<T, S> currencyMap);
}
