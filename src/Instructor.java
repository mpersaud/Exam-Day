import java.util.ArrayList;
import java.util.Random;

public class Instructor extends Thread {

    Random rand = new Random();



    public void msg(String m) {
        System.out.println("[" + (Main.elapsedTime()) + "] " + getName() + ": " + m);
    }

    public Instructor(){
        setName("Instructor");
        start();
    }

    @Override
    public void run() {

        while(true) {
            ///arrival of instruct


            if(Main.getExamCompleted())break;
            randWait();
            msg("arrived");
            Main.setInstructorArrived(true);
        /*

        Sleep for exact time and then start exam

         */
            sleep();

            /// starting exam and then sleeping for exact time
            msg("Exam Started");
            Main.setExamStart(true);
            Main.setInstructorArrived(false);


            sleep();

            //for all sleeping students , interupt, and add exam grade to them

            Student[] students = Main.getClassroom();
            //catching exception if array is empty

                for (int i = 0; i < students.length; i++) {
                    //System.out.println(students[i].getUncaughtExceptionHandler());
                    students[i].interrupt();
                }
                Main.clearClassCounter();
                Main.setExamStart(false);
                Main.setTestFinished(true);
                Main.setExamRound();


            try {
                Thread.currentThread().sleep(rand.nextInt(5000));

            } catch (InterruptedException e) {
                //
            }




        }


    }

    public void randWait(){

        try {
            Thread.currentThread().sleep(rand.nextInt(2000));


        } catch (InterruptedException e) {
            //
        }
    }

    public void sleep(){
        try {
            Thread.currentThread().sleep(2000);


        } catch (InterruptedException e) {
            //
        }
    }
}
