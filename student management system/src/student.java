class Student {
    private String id;
    private String firstName;
    private String lastName;
    private Module[] modules;

    public Student(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.modules = new Module[3]; // Assuming each student has 3 modules
        for (int i = 0; i < modules.length; i++) {
            modules[i] = new Module(); // Initialize modules
        }
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Module[] getModules() {
        return modules;
    }

    public void setModules(Module[] modules) {
        this.modules = modules;
    }

    // Method to calculate grade for all modules
    public void calculateGrades() {
        for (Module module : modules) {
            module.calculateGrade();
        }
    }
}