
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
        System.out.println("["+(examRoom.elapsedTime())+"] "+getName()+": "+m);
    }
    @Override
    public void run() {

        while (!examRoom.getAllExamCompleted() && examsTaken!= examRoom.examNeeded) {
            ///end condition

            //arrival
            studentArrival();
            //busy waiting
            waitforInstructor();
            //switching priorities to push up in ready queue
            switchPriorities();

            if (!examRoom.getClassFilled() && !examRoom.getExamStart()) {

                enterExamRoom();
                takeExam();
            } else {
                waitTillExamEnds();

            }
        }

        leave();

    }

    private void waitTillExamEnds() {
        if (examRoom.getClassFilled()) msg("cannot enter ,Class is Filled!");
        else if (examRoom.getExamStart()) msg("cannt enter,Exam already Started!");
        yield();
        yield();

        //busy waiting
        while (examRoom.getExamStart()) {

            if (examRoom.getAllExamCompleted()) break;
            msg("is waiting for Instructor and Exam to end");
            randWait();


        }
    }
    private void takeExam() {
        try {
            Thread.currentThread().sleep(10000000);
        } catch (InterruptedException e) {
            Thread.currentThread().setPriority(MAX_PRIORITY);
            msg("leaves Exam Room");
            examsTaken++;
        }
        Thread.currentThread().setPriority(NORM_PRIORITY);

        try {
            Thread.currentThread().sleep(rand.nextInt(4000));

        } catch (InterruptedException e) {
            //
        }
    }

    public void leave(){
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(id== examRoom.getCurrent_Students()){
            examRoom.setCurrent_Students();
            msg("finishes their Exams and goes home");
        }
        else if(examRoom.stud[id+1].isAlive()){

            try {
                examRoom.stud[id+1].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            msg("finishes their Exams and goes home");


        }
    }
    public void studentArrival(){
        try {
            Thread.currentThread().sleep(rand.nextInt(2000));
        } catch (InterruptedException e) {
            //
        }
        msg("has arrived and is waiting at Examroom");

    }

    public void waitforInstructor(){
        while (!examRoom.getInstructorArrived()) {

            if (examRoom.getAllExamCompleted()) break;

            try {
                Thread.currentThread().sleep(rand.nextInt(2000));


            } catch (InterruptedException e) {
                //
            }
            msg("is waiting for Instructor");

        }
    }

    public void randWait(){
        try {
            Thread.currentThread().sleep(rand.nextInt(4000));


        } catch (InterruptedException e) {
            //
        }
    }

    public void enterExamRoom(){
        examRoom.addStudent(this);
        msg(" entered classroom");
        examRoom.setClassFilled();
        examRoom.increaseClassCounter();
    }
    public void Wait(){

        try {
            Thread.currentThread().sleep(2000);


        } catch (InterruptedException e) {
            //
        }
    }
    public void switchPriorities(){
        Thread.currentThread().setPriority(getPriority() + 1);
        try {
            Thread.currentThread().sleep(rand.nextInt(500));
            //msg("is waiting for Instructor");

        } catch (InterruptedException e) {
            //

        }
        Thread.currentThread().setPriority(getPriority() - 1);

    }
}
