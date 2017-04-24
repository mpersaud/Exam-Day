
import java.util.Arrays;
import java.util.Random;


public class Student extends Thread {


    Random rand = new Random();

    int [] exam_grades = new int[3];
    int examsTaken=0;
    int id =0;



    public Student(String id){

        setName("Student"+id);
        this.id = Integer.parseInt(id);
        Arrays.fill(exam_grades,0);
        //System.out.println(student.getName());
        start();
    }

    public void msg(String m) {
        System.out.println("["+(Main.elapsedTime())+"] "+getName()+": "+m);
    }
    @Override
    public void run() {

        while (!Main.getExamCompleted() && examsTaken!= Main.examNeeded) {
            ///end condition


            //arrival
            randWait();
            msg("waiting at class room ");


            //busy waiting
            while (!Main.getInstructorArrived()) {

                if (Main.getExamCompleted()) break;

                randWait();
                msg("is waiting for Instructor");

            }

            //switching priorities to push up in ready queue
            Thread.currentThread().setPriority(getPriority() + 1);
            try {
                Thread.currentThread().sleep(rand.nextInt(500));
                //msg("is waiting for Instructor");

            } catch (InterruptedException e) {
                //

            }
            Thread.currentThread().setPriority(getPriority() - 1);
            //not working
            Main.setClassFilled();
            if (!Main.getClassFilled() && !Main.getExamStart()) {


                Main.setClassFilled();
                Main.increaseClassCounter();
                Main.addStudent();
                msg(" entered classroom");

                try {
                    Thread.currentThread().sleep(10000);


                } catch (InterruptedException e) {
                    msg(" finished exam!");
                    examsTaken++;
                }

                randWait();

            } else {
                if (Main.getClassFilled()) msg("Class is Filled");
                else if (Main.getExamStart()) msg("Exam already Started");
                yield();
                yield();

                //busy waiting
                while (Main.getExamStart()) {

                    if (Main.getExamCompleted()) break;
                    msg("is waiting for Instructor and Exam to end");
                    randWait();


                }

            }
        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(id==Main.getCurrent_Students()){
            Main.setCurrent_Students();
            msg("finishes their Exams and goes home");
        }
        else if(Main.stud[id+1].isAlive()){

            try {
                Main.stud[id+1].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            msg("finishes their Exams and goes home");


        }



    }

    public void randWait(){

        try {
            Thread.currentThread().sleep(rand.nextInt(2000));


        } catch (InterruptedException e) {
            //
        }
    }
    public void Wait(){

        try {
            Thread.currentThread().sleep(2000);


        } catch (InterruptedException e) {
            //
        }
    }
}
