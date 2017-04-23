import java.util.Random;


public class Student extends Thread {


    Random rand = new Random();

    int exam_1=0;
    int exam_2=0;
    int exam_3=0;


    public Student(String id){

        setName("Student"+id);
        //System.out.println(student.getName());
        start();
    }

    public void msg(String m) {
        System.out.println("["+(Main.elapsedTime())+"] "+getName()+": "+m);
    }
    @Override
    public void run() {

        while(true) {

            ///end condition

            if(Main.getExamCompleted())break;
            //arrival
            randWait();
            msg("waiting at class room ");


            //busy waiting
            while (!Main.getInstructorArrived()) {

                if(Main.getExamCompleted())break;

                randWait();
                msg("is waiting for Instructor");

            }

            //switching priorities to push up in ready que
            Thread.currentThread().setPriority(getPriority() + 1);
            try {
                Thread.currentThread().sleep(rand.nextInt(500));
                //msg("is waiting for Instructor");

            } catch (InterruptedException e) {
                //

            }
            Thread.currentThread().setPriority(getPriority() - 1);


            //checking if classroom is full or test started
            Main.setClassFilled();
            if (!Main.getClassFilled() && !Main.getExamStart()) {

                //Main.setInstructorArrived(false);

                msg("entered classroom");

                Main.addStudent();
                Main.increaseClassCounter();
                Main.setClassFilled();


                while (!Main.getExamStart()) {
                    msg(" waiting for exam");
                    Wait();
                }
                msg(" taking Exam");
                try {
                    Thread.currentThread().sleep(500000);
                    //msg("is waiting for Instructor");

                } catch (InterruptedException e) {
                    msg("Test has finished");


                }
                /*
                try {
                    Thread.currentThread().sleep(rand.nextInt(5000));
                    //msg("is waiting for Instructor");

                } catch (InterruptedException e) {

                }
                */
                 Main.clearClassCounter();
                 Main.setExamStart(false);
            } else {

                if(Main.getClassFilled()) msg("Class is filled ");
                else if(Main.getExamStart()) msg("Exam Started");

                yield();
                yield();

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
    public void Wait(){

        try {
            Thread.currentThread().sleep(2000);


        } catch (InterruptedException e) {
            //
        }
    }
}
