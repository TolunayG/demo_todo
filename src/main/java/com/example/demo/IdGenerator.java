package com.example.demo;

public final class IdGenerator
{
    private IdGenerator() { }

    private static long value;

    public synchronized static long getNextValue()
    {
        return ++value;
    }
}