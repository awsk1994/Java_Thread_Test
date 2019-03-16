



# Summary

 - Thread is a lightweight subprocess.

 - Smallest unit of a program.

 - Contains a separate path of executiWon.

 - Every java program contains at least one thread.

 - Thread is created & controlled by the java.lang.Thread class


# Thread Lifecycle

{New, Runnable, Waiting, Running, Terminated}


New:

A new thread begins its life cycle in this stsate & remains here until the program starts the thread - -also known as "born thread".

Runnable:

Once a newly born thread starts, the thread comes under runnable tate. A thread stays in this state until it is executing its task.

Running:

In this state, a thread starts executing by entering run() method, and the yield() method can send them to go back to the Runnable state.

Waiting:

Thread enters this state when it is temporarily in an inactive state. i.e. it is still alive, but is not eligible to run. It can be in waiting, sleeping or blocked state. It can go back to Runnable state.

Terminated:

A runnable thread enters this terminated state when it completes its task or otherwise terminates.

# Create a thread

Main method in java -> main thread:
     - must be the last thread to finish execution.

![Java Main Thread](img/JavaMainThread.JPG")

When you have long-running operation, you don't want to put the load on the main thread because that's the reason programs go unresponsive. Instead, you want to create thread and allocate the load, and leave main thread ready for inputs.

## Daemon Thread
Thread directly ran from JVM.


## 2 ways

### 1. Thread Class

 - Thread class is extended only if there is a need to override other methods of it.
 - tight coupling

```
    public class Thread extends OBject implements Runnable
```

a) Create a thread class

b) Override run() method

c) Create object of the class

d) Invoke start() method to execute custom threads run()

```
public class MyThread extends Thread {
    public void run() {
        System.out.println("Alex Thread");
    };

    public static void main(String[] args){
        MyThread obj = new MyThread();
        obj.start();
    };
}
```

### 2. Runnable Interface (useful because multi-inheritance is not allowed in Java)

 - Runnable is implemented only if there is a need of a special run method.
 - loose coupling

```
    public interface Runnable
```

a) Create a thread class implementing Runnnable interface

b) Override run() method

c) Create object of the class

d) Invoke start() method using the object


```
public class MyThread implements Runnable{
    public void run(){
        System.out.println("Alex Thead");
    };

    public static void main(String[] args){
        Thread t = new Thread(new MyThread());
        t.start();
    };
};
```

# Resources

Great YouTube Video:

https://www.youtube.com/watch?v=TCd8QIS-2KI

Build a Java Project:

https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-application.html


# Todo:

Tight vs Loose Coupling : https://www.geeksforgeeks.org/coupling-in-java/

https://www.youtube.com/watch?v=TCd8QIS-2KI (until 24:30)