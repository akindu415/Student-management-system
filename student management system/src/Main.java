import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Main {

    // Initializing a file variable named FILE_NAME
    private static final String FILE_NAME= "studentdetails.txt";

    public static void main(String[] args) {
        //Initializing variables
        int available_seats = 100;
        String[][] students = new String[100][7];//2d array being used
        int student_count = 0;
        Scanner input = new Scanner(System.in);

//Main menu
        while (true) {
            System.out.println("MAIN MENU");
            System.out.println("""
                    1. Check available seats
                    2. Register student (with ID)
                    3. Delete student
                    4. Find student (with student ID)
                    5. Store student details into a file
                    6. Load student details from the file to the system
                    7. View the list of students based on their names
                    8.Add additional details
                    c.Generate a summary
                    d.Generate a complete report
                    9. Exit""");
            System.out.println("Enter your choice :");//asking for user input
            String choice = input.next();
            switch (choice) {
                case "1":
                    checkavailableseats(available_seats, student_count);
                    break;
                case "2":
                    checkavailableseats(available_seats, student_count);
                    student_count= registerstudent(students, student_count, available_seats);
                    break;
                case "3":
                    deletestudent(students, student_count);
                    break;
                case "4":
                    findstudent(students, student_count);
                    break;
                case "5":
                    storefile(students,student_count);
                    break;
                case "6":
                    student_count= loadfile(students, student_count);
                    break;
                case "7":
                    sorter(students,student_count);
                    break;
                case "8":
                    additionalDetails(students,student_count);
                    break;
                case "c":
                    generatesummary(students,student_count);
                    break;
                case "d":
                    getreport(students,student_count);
                    break;
                case "9":
                    System.out.println("Exiting the program.");
                    input.close();
                    System.exit(0);
                default://if the user enter a wront number instead of the given numbers
                    System.out.println("Invalid choice,please try again.");
            }
        }
    }

// method to check available seats
    public static int checkavailableseats(int available_seats, int student_count){
        int seats = available_seats - student_count;
        System.out.println("No of seats available :"+seats);
        return seats;
    }

// method to register a student
    public static int registerstudent(String[][] students, int student_count, int available_seats){
        Scanner input = new Scanner(System.in);
        // when there are no seats in the system
        if (available_seats <= student_count){
            System.out.println("no seats available");
            return student_count;
        }else{
            while(true){
                try {
                    System.out.println("Enter your student ID");//student id
                    int identity = input.nextInt();
                    String stringid = Integer.toString(identity);// converting integer to string
                    if (stringid.length() == 7) {
                        students[student_count][0]= "w"+ stringid;// adding a "w" to the student id
                    } else {
                        System.out.println("Invalid output please try again");
                        continue;
                    }
                    //catch is used to avoid user entering any letters or symbols.
                }catch (InputMismatchException var){
                    System.out.println("You can not enter any letters or any special characters. only enter numbers ");
                    input.next();
                    continue;
                }
                //entering other information of the user(first and last name);
                System.out.println("Enter your First name");
                String fname = input.next();
                students[student_count][1]= fname;

                System.out.println("Enter your Last name");
                String lname = input.next();
                students[student_count][2]= lname;

                System.out.println( "Student "+students[student_count][1]+" " +students[student_count][2]+" saved successfully");
                student_count++;
                break;
            }
        }

        return student_count;
    }
//method to delete student
    public static int deletestudent(String[][] students,int student_count){
        Scanner input = new Scanner(System.in);

        while(true){
            // when there are no students registered
            if(student_count == 0 ){
                System.out.println("No students. Register a student first");
                break;
            }
            try{
                System.out.print("Enter ID of the student you want to delete: ");
                int id = input.nextInt();
                String str = Integer.toString(id);//Integer to string
                if (str.length() == 7) {
                    String deleter = "w" + str;// adding w in front
             //Iterating through the array from begin to end
                    for (int i = 0; i < student_count; i++) {
                        // if the student ID equals to user input
                        if (students[i][0].equals(deleter)) {
                            for (int j = i;j < student_count;j++) {
                                students[j] = students[j + 1];
                            }

                                student_count--;
                                System.out.println("student"+ deleter + "deleted successfully");
                                return  student_count;
                        }else{
                            System.out.println("Incorrect id");
                        }
                    }
                }

            }catch (InputMismatchException var){
                System.out.println("You can not enter any letters or any special characters. only enter numbers ");
            }
        }
        System.out.println("student Id not found");
        return student_count;
    }

//method to find student
    public static void findstudent(String[][] students,int student_count){
            if (student_count == 0){
                System.out.println("No students. Register a student first");
                return;
            }

            Scanner input = new Scanner(System.in);
            System.out.println("STUDENT FINDER");
            System.out.println("Enter student ID :");
            String id ="w"+ input.next();
            System.out.println("Finding results for " +id);

            boolean studentfound = false;//setting up a boolean as student found to decide whether a student has been found or not
            for (int i = 0; i< student_count; i++) {//iterating through array
                if (students[i][0].equals(id)) {
                    //printing out student information if id equals to user input
                    System.out.println("Student ID :" + students[i][0]);
                    System.out.println("First name :" + students[i][1]);
                    System.out.println("Last name :" + students[i][2]);
                    System.out.println("Grades:"+students[i][3]+","+students[i][4]+","+ students[i][5]);
                    studentfound = true;
                    break;
                }
            }
            //if student not found
                if(!studentfound){
                    System.out.println("Student not found try again");
                }
            }


//storing all information in a text file
    public static void storefile(String[][] students,int student_count){
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            //iterrating through each item in array and writing in the textfile
            for (int i = 0; i < student_count; i++){
                writer.write(students[i][0]+ ","+ students[i][1]+","+students[i][2]+","+students[i][3]+","+students[i][4]+","+ students[i][5]+","+students[i][6]+"\n");
            }

            System.out.println("Sucessfully stored information into the file");
            writer.close();
            //if file not found
        }catch (IOException e){
            System.out.println("Something went wrong. File not found");
            e.printStackTrace();
        }
    }

//method to load data from file back to the system
    public static int loadfile(String[][] students,int student_count){
        try {
            File studentinfo = new File(FILE_NAME);
            Scanner fileread = new Scanner(studentinfo);//reading file from studentinfo
            while (fileread.hasNextLine()){
                String line = fileread.nextLine();//reads the next line and stores as a string
                String[] details = line.split(",");//split where "," is
                if (details.length == 9) {  // Ensure the correct number of details
                    students[student_count][0] = details[0];
                    students[student_count][1] = details[1];
                    students[student_count][2] = details[2];
                    students[student_count][3] = details[3];
                    students[student_count][4] = details[4];
                    students[student_count][5] = details[5];
                    students[student_count][6] = details[6];
                    student_count++;
                }else {
                    System.out.println("Invaid");
                }
            }
            fileread.close();
            System.out.println("Student details loaded from file.");
        }catch (IOException e){
            System.out.println("Error while reading the file.");
            e.printStackTrace();
        }
        return student_count;
    }

//method to sort Students names in order
    public static void sorter(String[][] students, int student_count){
        if (student_count == 0){
            System.out.println("No students to display. Register some students first.");
        }
        for (int i=0;i<student_count-1;i++){
            for(int j=0;j<student_count-i-1;j++){
                // compareTo returns positive if lexiocographically greater than
                if (students[j][1].compareTo(students[j+1][1])>0){
                    String[] temp = students[j];//declare temporary string
                    students[j]= students[j+1];
                    students[j+1]= temp;
                }
            }
        }
        System.out.println("List of students based on their names:");
        for (int i=0;i<student_count;i++){
            System.out.println("Student ID: " + students[i][0] + ", First Name: " + students[i][1] + ", Last Name: " + students[i][2]);
        }
    }

// method to enter additional details(marks)
    public static void additionalDetails(String[][] students, int student_count){
        Scanner input= new Scanner(System.in);

        if(student_count == 0){
            System.out.println("No students registered yet");//if no students in the array
            return;
        }
        System.out.println("Enter student Id:");//user input
        String id = "w"+input.next();

        boolean studentfound = false;
        for (int i=0;i<student_count;i++){
            if(students[i][0].equals(id)){
                //asking marks for module 1
                System.out.println("Enter marks for module 1:");
                int mark1 =input.nextInt();
                students[i][3]= Integer.toString(mark1);//converting to string since the array datatype is string

                //asking marks for module 2
                System.out.println("Enter marks for module 2:");
                int mark2 =input.nextInt();
                students[i][4]= Integer.toString(mark2);

                //asking marks for module 3
                System.out.println("Enter marks for module 3:");
                int mark3 =input.nextInt();
                students[i][5]= Integer.toString(mark3);

                //create new student object
                Student student =new Student(students[i][0],students[i][1],students[i][2]);
                //calls the getmodule method on student object
                Module[] modules = student.getModules();
                //sets the marks
                modules[0].setMark(mark1);
                modules[1].setMark(mark2);
                modules[2].setMark(mark3);

                //calls the calculategrade method on student object
                student.calculateGrades();

                //getting grades using getgrade method
                students[i][6]=modules[0].getGrade() +","+ modules[1].getGrade() +","+ modules[2].getGrade();

                System.out.println("Marks added and grades grades calculated");//prints after adding
                studentfound = true;
                break;
            }
        }
        if (!studentfound){
            System.out.println("Student not found try again");
        }
    }

    //TASK 3
//method to generate a summary
    public static void generatesummary(String[][] students,int student_count){
        System.out.println("SUMMARY");
        //prints the student count
        System.out.println("The total student registrations :"+student_count);
        // initialize  a variable as studentsmorethan40
        int studentsmorethan40 = 0;
        for(int i= 0; i <student_count;i++){
            int mark1 = Integer.parseInt(students[i][3]);
            int mark2 = Integer.parseInt(students[i][4]);
            int mark3 = Integer.parseInt(students[i][5]);
            //if marks are more than 40 1 is added to studentsmorethan40
            if (mark1 > 40 && mark2>40 && mark3>40){
                studentsmorethan40++;
            }
        }
        System.out.println("No of students who scored more than 40 in all modules :"+ studentsmorethan40);//output
    }

//method to generate a report
    public static void getreport(String[][] students, int student_count){

        //sort the list of student details based on average of students
        //using bubble sort
        for(int i = 0;i<student_count;i++){
            for(int j = 0; j<student_count-i-1;j++){
                int avg1 =(Integer.parseInt(students[j][3])+Integer.parseInt(students[j][4])+Integer.parseInt(students[j][5]))/3;
                int avg2 =(Integer.parseInt(students[j+1][3])+Integer.parseInt(students[j+1][4])+Integer.parseInt(students[j+1][5]))/3;
                //comparing average of 1st and 2nd averages
                if (avg1<avg2){
                    String[] temp =students[j];
                    students[j]= students[j+1];
                    students[j+1]=temp;
                }
            }
        }

        //iterating through array
        for(int i=0;i<student_count;i++){
            String student_id = students[i][0];
            String Name = students[i][1]+" "+students[i][2];
            int mark1 = Integer.parseInt(students[i][3]);
            int mark2 = Integer.parseInt(students[i][4]);
            int mark3 = Integer.parseInt(students[i][5]);
            int total = mark1 + mark2 +mark3;
            int avg = total/3;
            String Grade = students[i][6];
            //printing report
            System.out.println(
                    "Student Id:"+ student_id+"\n"+
                    "Name:"+Name+"\n"
                    +"Marks:"+mark1 +","+ mark2 +","+mark3+"\n"
                    +"Total:"+ total +"\n"
                    +"Average:"+ avg +"\n"
                    +"Grade:"+ Grade+"\n"
            );
        }
    }

}
