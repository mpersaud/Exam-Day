import java.util.Random;

public class Instructor extends Thread {

    Random rand = new Random();
    int exam_num=0;


    public void msg(String m) {
        System.out.println("[" + (examRoom.elapsedTime()) + "] " + getName() + ": " + m);
    }

    public Instructor(){
        setName("Instructor");
        start();
    }

    @Override
    public void run() {


        while(!examRoom.getAllExamCompleted()) {

        //exam room should empty before the instructor arrives
            examRoom.clearClassCounter();

            instructorArrival();
            timeTillExam();
            examDuration();
            examCleanup();
            instructorBreak();

    }
    msg("Exam Grades for Students");

        for (int j = 0; j< examRoom.stud.length; j++){

            msg(examRoom.stud[j].getName()+": ("+ examRoom.stud[j].exam_grades[0] +","+ examRoom.stud[j].exam_grades[1]+ ","+ examRoom.stud[j].exam_grades[2]+")");
        }

        try {
            examRoom.stud[0].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg("leaves to go home as all students have left");

    }

    private void instructorBreak() {
        try {
            Thread.currentThread().sleep(rand.nextInt(4000));
        } catch (InterruptedException e) {
            //
        }
    }

    public void instructorArrival(){
        try {
            Thread.currentThread().sleep(rand.nextInt(3000));
        } catch (InterruptedException e) {
            //
        }
        msg("has arrived!");
        examRoom.setInstructorArrived(true);
    }

    public void timeTillExam(){
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            //
        }
        examRoom.setExamStart(true);
        msg("Exam Starting");
    }
    public void examDuration(){
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            //
        }
        msg("Exam Finished");

    }

    public void examCleanup(){
        /*
        for (Student e : examRoom.getClassroom()) {
            e.interrupt();
            e.exam_grades[exam_num] = rand.nextInt(90)+10;
        }
        */

        for (int counter = 0; counter < examRoom.getClassroom().size(); counter++) {
              Student temp =examRoom.getClassroom().get(counter);
                msg("collects "+temp.getName()+"'s test");
                temp.interrupt();
                temp.exam_grades[exam_num] = rand.nextInt(90)+10;

            }
        exam_num++;
        examRoom.setInstructorArrived(false);
        examRoom.setExamStart(false);
        examRoom.setClassFilled();
        examRoom.clearClassCounter();
        examRoom.setExamRound();
        msg("End of Exam Round :" + examRoom.getExamRound());
    }
}
