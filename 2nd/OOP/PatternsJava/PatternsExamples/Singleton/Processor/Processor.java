package com.Patterns.Singleton.Processor;

import com.Patterns.Singleton.Singleton.Singleton;

public class Processor
{
    private int _start;
    public Processor(int start)
    {
        _start = start;
        Singleton.GetInstance().Log("Processor створено.");
    }
    public int ProcessTo(int end)
    {
        int sum = 0;
        for (int i = _start; i <= end; ++i)
        {
            sum += i;
        }
        Singleton.GetInstance().Log(
                "Processor обчислив деяке значення: " + sum);
        return sum;
    }
}

