class Module {
    private int mark;
    private String grade;

    public Module() {
        this.mark = 0;
        this.grade = "";
    }

    // Getters and setters
    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Method to calculate grade based on mark
    public void calculateGrade() {
        if (mark >= 80) {
            grade = "Distinction";
        } else if (mark >= 70) {
            grade = "Merit";
        } else if (mark >= 40) {
            grade = "Pass";
        } else {
            grade = "Fail";
        }
    }
}
