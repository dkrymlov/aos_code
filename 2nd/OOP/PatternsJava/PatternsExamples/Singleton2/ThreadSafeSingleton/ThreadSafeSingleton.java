package com.Patterns.Singleton2.ThreadSafeSingleton;

public class ThreadSafeSingleton
{
    private ThreadSafeSingleton()
    {
        // Читаємо дані з деякого файлу і дістаємо номер останнього запису
        // _logCount = вичитане значення
    }
    private int _logCount = 0;
    private static ThreadSafeSingleton _loggerInstance;
    private static final Object locker = new Object();
    public static ThreadSafeSingleton GetInstance()
    {
        synchronized (locker)
        {
            if (_loggerInstance == null)
            {
                _loggerInstance = new ThreadSafeSingleton();
            }
        }
        return _loggerInstance;
    }
    public void Log(String message)
    {
        System.out.println(_logCount + ": " + message);;
        _logCount++;
    }
}

