import java.util.ArrayList;


public class examRoom {

    public static boolean instructerArrived = false;
    public static boolean examStart = false;
    public static boolean classroomFilled= false;
    public static boolean examsCompleted= false;
    private static int examRound=0;
    private static boolean testFinished = false;
    public static int Nstudents =14;
    static int capacity =10;
    public static long time = System.currentTimeMillis();
    public static ArrayList<Student> classroom = new ArrayList<>();
    public static Student[] stud;
    private static int class_counter =0;
    public static int current_students =Nstudents-1;
    public static int examNeeded =2;



    public static final long elapsedTime()
    {
        return System.currentTimeMillis()- time;
    }

    public static void main(String[] args) {


        stud = new Student[14];

        for (int i =0; i<14;i++){

            stud[i] =new Student(Integer.toString(i));
        }
        new Instructor();


    }

    public static synchronized boolean getInstructorArrived() {
        return instructerArrived;
    }
    public static synchronized void setInstructorArrived(boolean b) {
         instructerArrived=b;
    }

    public static synchronized boolean  getExamStart(){
        return examStart;

    }

    public static synchronized boolean getClassFilled(){
        setClassFilled();
        return classroomFilled;
    }

    public static synchronized void setExamStart(boolean b){
        //if(b ==false)setInstructorArrived(false);
        examStart=b;
    }
    public static synchronized void setClassFilled(){

        if (class_counter >= capacity) {

            classroomFilled=true;
        }
        else{
            classroomFilled=false;
        }
    }

    public static synchronized void increaseClassCounter(){

        class_counter++;
    }
    public static synchronized void clearClassCounter(){
        classroom.clear();
        class_counter=0;
    }

    public static synchronized ArrayList<Student> getClassroom() {
        return classroom;
    }
    public static synchronized  void addStudent(Student s){
        classroom.add(s);


    }

    public static synchronized  void setExamRound() {
        examRound++;
        //System.out.println("End of Exam Round :" +examRound);

    }
    public static synchronized boolean getAllExamCompleted(){
        if(examRound>=3)examsCompleted=true;
        return examsCompleted;

    }

    public static synchronized  int getCurrent_Students(){
        return current_students;
    }
    public static synchronized  void setCurrent_Students(){
        current_students--;
    }

    public static synchronized  int getExamRound(){
        return examRound;
    }


}
