public class Main {

    public static boolean instructerArrived = false;
    public static boolean examStart = false;
    public static boolean classroomFilled= false;
    public static boolean examsCompleted= false;
    private static int examRound=0;
    private static boolean testFinished = false;
    int Nsdtudents =0;
    static int capacity =1;
    public static long time = System.currentTimeMillis();
    public static Student[] classroom;
    public static Student[] stud;
    private static int class_counter =0;

    public static final long elapsedTime()
    {
        return System.currentTimeMillis()- time;
    }

    public static void main(String[] args) {


        classroom = new Student[capacity];
        //stud = new Student[2];

        for (int i =0; i<2;i++){

            new Student(Integer.toString(i));
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
    }

    public static synchronized void increaseClassCounter(){

        //classroom[class_counter] = (Student)Student.currentThread();
        class_counter++;
    }
    public static synchronized void clearClassCounter(){
        classroom = new Student[capacity];
        class_counter=0;
    }

    public static synchronized Student[] getClassroom() {
        return classroom;
    }
    public static synchronized  void addStudent(){
        classroom[class_counter] = (Student)Student.currentThread();

    }

    public static synchronized  void setExamRound() {
        examRound++;
        System.out.println("Exam Round :" +examRound);

    }
    public static synchronized boolean getExamCompleted(){
        if(examRound>=3)examsCompleted=true;
        return examsCompleted;

    }

    public static synchronized boolean getTestFinished() {
        return testFinished;
    }
    public static synchronized  void setTestFinished(boolean b){
        testFinished=b;
    }

}
