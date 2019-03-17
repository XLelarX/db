package com.company;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public class AbsCol<T> extends AbstractCollection<T> {
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
