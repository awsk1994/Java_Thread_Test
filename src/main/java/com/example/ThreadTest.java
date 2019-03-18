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

/*=== MultiThreading Example ===*/
class Printer{
    void printDocs(int numCopies, String docName){
        for(int i=0; i<numCopies;i++){
            System.out.println("Printing page " + i + " of doc, " + docName + ".");
        }
    };

    synchronized void synchronizePrintDocs(int numCopies, String docName){
        for(int i=0; i<numCopies;i++){
            System.out.println("Synchronize Printing page " + i + " of doc, " + docName + ".");
        }
    };
};

class PrinterThread extends Thread{
        Printer p;
        String docName;

        PrinterThread(Printer p, String docName){
            this.p = p;
            this.docName = docName;
        };

        @Override
        public void run(){
            p.printDocs(5, docName);
        };
};

class MultiThreadingExample{
    void start(){
        Printer p = new Printer();
        PrinterThread docOne = new PrinterThread(p, "MultiThreading A's doc.");
        PrinterThread docTwo = new PrinterThread(p, "MultiThreading B's doc.");

        docOne.start();
        docTwo.start();

        /*
        Res:
            Printing page 0 of doc, B's doc..
            Printing page 0 of doc, A's doc..
            Printing page 1 of doc, A's doc..
            Printing page 1 of doc, B's doc..
            Printing page 2 of doc, B's doc..
            Printing page 2 of doc, A's doc..
            Printing page 3 of doc, B's doc..
            Printing page 3 of doc, A's doc..
            Printing page 4 of doc, B's doc..
            Printing page 4 of doc, A's doc..

          When you do multithreading, the process of each thread switches (to create an illusion of parallel processing).
          But as you can see from the results, if this was a real printer, it'll be a pain to have to then sort it by doc A and B.
          To fix this, we need to introduce synchronization - MultiThreadingWithSynchronization
         */
    };
};

/*=== SynchronizationExample ===*/
class SynchronizationExample{
    void start() throws InterruptedException {
        Printer p = new Printer();
        PrinterThread docOne = new PrinterThread(p, "Synchronized A's doc.");
        PrinterThread docTwo = new PrinterThread(p, "Synchronized B's doc.");

        docOne.start();
        try {
            docOne.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        docTwo.start();

        /*
        Res:
            Printing page 0 of doc, Synchronized A's doc..
            Printing page 1 of doc, Synchronized A's doc..
            Printing page 2 of doc, Synchronized A's doc..
            Printing page 3 of doc, Synchronized A's doc..
            Printing page 4 of doc, Synchronized A's doc..
            Printing page 0 of doc, Synchronized B's doc..
            Printing page 1 of doc, Synchronized B's doc..
            Printing page 2 of doc, Synchronized B's doc..
            Printing page 3 of doc, Synchronized B's doc..
            Printing page 4 of doc, Synchronized B's doc..

        By using join, docTwo waits until docOne to finish before getting printed.
        But what happens when we have multiple docs to print? It would be ugly to put joins everywhere.
        Next, we will introduce the synchronized block.
         */
    };
};

/*=== Synchronized Method Example ===*/

class SynchronizePrinterThread extends Thread{
    Printer p;
    String docName;

    SynchronizePrinterThread(Printer p, String docName){
        this.p = p;
        this.docName = docName;
    };

    @Override
    public void run(){
        p.synchronizePrintDocs(5, docName);
    };
};

class SynchronizeMethodExample{
    void start(){
        Printer p = new Printer();
        SynchronizePrinterThread docOne = new SynchronizePrinterThread(p, "Synchronized method. A's doc.");
        SynchronizePrinterThread docTwo = new SynchronizePrinterThread(p, "Synchronized method. B's doc.");

        docOne.start();
        docTwo.start();

        /*
        Res:
            Synchronize Printing page 0 of doc, Synchronized method. A's doc..
            Synchronize Printing page 1 of doc, Synchronized method. A's doc..
            Synchronize Printing page 2 of doc, Synchronized method. A's doc..
            Synchronize Printing page 3 of doc, Synchronized method. A's doc..
            Synchronize Printing page 4 of doc, Synchronized method. A's doc..
            Synchronize Printing page 0 of doc, Synchronized method. B's doc..
            Synchronize Printing page 1 of doc, Synchronized method. B's doc..
            Synchronize Printing page 2 of doc, Synchronized method. B's doc..
            Synchronize Printing page 3 of doc, Synchronized method. B's doc..
            Synchronize Printing page 4 of doc, Synchronized method. B's doc..

         When it reaches synchronizePrintDocs() method, it will add a lock on the printer object so no other thread can access it until the method is completed.
         */
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

        /*
        // Example of MultiThreading
        MultiThreadingExample multiThreadingExample = new MultiThreadingExample();
        multiThreadingExample.start();
        */

        /*
        // Example of Synchronization (using Join)
        SynchronizationExample synchronizationExample = new SynchronizationExample();
        synchronizationExample.start();
        */

        /*
        // Example of Synchronized Method
        SynchronizeMethodExample synchronizeMethodExample = new SynchronizeMethodExample();
        synchronizeMethodExample.start();
        */

    };
};
