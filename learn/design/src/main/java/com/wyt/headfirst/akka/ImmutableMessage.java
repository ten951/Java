package com.wyt.headfirst.akka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/8.
 */
public final class ImmutableMessage {
    private final int sequenceNumber;
    private final List<String> values;

    public ImmutableMessage(int sequenceNumber, List<String> values) {
        this.sequenceNumber = sequenceNumber;
        this.values = Collections.unmodifiableList(new ArrayList<>(values));
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public List<String> getValues() {
        return values;
    }
}
