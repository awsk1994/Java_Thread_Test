package com.example;

/* Tutorial: https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-application.html */

/**
 * Created by awong on 3/5/2019.
 */

/*=== NonThread Example ===*/
class MyTask{
    void executeTask(){
        for(int doc=1;doc<=10;doc++){
            System.out.println("@@ Printing Document #" + doc + " - Printer 2");
        }
    }
};

class NonThreadExample {
    void start(){
        /*
            Non-Threaded Application:
            Threads always execute jobs sequentially.
            Job n does not happen until Job n-1 is completed.
         */
        System.out.println("==Non-Threaded Application Started==");

        // Job 1
        MyTask task = new MyTask();
        task.executeTask();

        // Job 2
        for(int doc=1;doc<=10;doc++){
            System.out.println("^^ Printing Document #" + doc + " - Printer 1");
        }

        System.out.println("==Non-Threaded Application Started==");
    };
};

/*=== Thread Example ===*/
class ThreadTask extends Thread{
    @Override
    public void run(){
        for(int doc=1;doc<=10;doc++){
            System.out.println("&& Printing Document #" + doc + " - Printer 3");
        }
    }
};

class ThreadExample{
    void start(){
        ThreadTask threadTask = new ThreadTask();
        threadTask.start(); // Internally calls the run method.

        // Job 2
        for(int doc=1;doc<=10;doc++){
            System.out.println("^^ Printing Document #" + doc + " - Printer 1");
        };
    };
};

/*=== Interface Thread Example ===*/
class ParentThread{
    void parentPrint(){
        System.out.println("Called from Parent");
    }
};

class ChildThread extends ParentThread implements Runnable{
    void childPrint(){
        System.out.println("Called from Child");
    };

    public void run(){  // Reserved method for Runnable interface.
        childPrint();
        parentPrint();
    };
};

class InterfaceThreadExample{
    void start(){
        Runnable childThread = new ChildThread();
        Thread task = new Thread(childThread);
        task.start();
    };
};

/*=== Main Thread ===*/
public class ThreadTest {

    // Main method represents main thread
    public static void main(String[] args){
        /*
        // Example of nonThreadTask - Sequential
        NonThreadExample nonThreadExample = new NonThreadExample();
        nonThreadExample.start();
        */

        /*
        // Example of Thread - Parallel/Concurrent
        ThreadExample threadExample = new ThreadExample();  // Created child/worker thread.
        threadExample.start();
        */

        /*
        // Example of Implementing Threads
        InterfaceThreadExample interfaceThreadExample = new InterfaceThreadExample();
        interfaceThreadExample.start();
        */
    };
};
