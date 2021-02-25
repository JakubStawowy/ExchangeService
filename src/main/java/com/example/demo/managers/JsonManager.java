package com.example.demo.managers;

import java.util.List;
import java.util.Map;

public interface JsonManager<T extends String, S extends Number> {
    Map<T, S> getMap();
    List<T> getKeys();
}
