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

            ///arrival of instruct
        int i=0;
    while(!Main.getExamCompleted()) {
        Main.clearClassCounter();

        randWait();
        msg("arrived");
        Main.setInstructorArrived(true);

        sleep();
        Main.setExamStart(true);
        msg("Exam Starting");

        sleep();
        msg("Exam Ending");

        for (Student e :
                Main.getClassroom()) {
            e.interrupt();
            e.exam_grades[i] = rand.nextInt(90)+10;

        }
        i++;
        Main.setInstructorArrived(false);
        Main.setExamStart(false);
        Main.setClassFilled();
        Main.clearClassCounter();
        Main.setExamRound();

        randWait();
    }
        msg("Exam Grades for Students");

        for (int j=0;j<Main.stud.length;j++){

            msg(Main.stud[j].getName()+": ("+Main.stud[j].exam_grades[0] +","+ Main.stud[j].exam_grades[1]+ ","+ Main.stud[j].exam_grades[2]+")");
        }

        try {
            Main.stud[0].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg("leaves to go home as all students have left");


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
            Thread.currentThread().sleep(5000);


        } catch (InterruptedException e) {
            //
        }
    }
}
