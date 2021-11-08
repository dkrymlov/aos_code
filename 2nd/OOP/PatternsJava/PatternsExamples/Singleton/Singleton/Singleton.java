package com.Patterns.Singleton.Singleton;
public class Singleton
{
    private Singleton() { }
    private int _logCount = 0;
    private static Singleton _singletonInstance = null;
    public static Singleton GetInstance()
    {
        if (_singletonInstance == null)
        {
            _singletonInstance = new Singleton();

        }
        return _singletonInstance;
    }
    public void Log(String message)
    {
        System.out.println(_logCount + ": " + message);;
        _logCount++;
    }
}

